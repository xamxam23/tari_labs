package com.m6world.tari_labs.api.interactors;

import com.androidnetworking.common.ANResponse;
import com.m6world.tari_labs.api.TimeOutParameters;
import com.m6world.tari_labs.api.models.ErrorBody;
import com.m6world.tari_labs.commons.JacksonMapper;

import java.net.SocketTimeoutException;

public abstract class ANetworkInteractor {
    private TimeOutParameters timeOutParameters = new TimeOutParameters(4, 8, 16);
    protected JacksonMapper jacksonMapper = new JacksonMapper();
    protected String baseUrl = "https://beta.bigneon.com/api/";

    public String getBaseUrl() {
        return baseUrl;
    }

    public ANetworkInteractor setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public TimeOutParameters getTimeOutParameters() {
        return timeOutParameters;
    }

    public ANetworkInteractor setTimeOutParameters(TimeOutParameters timeOutParameters) {
        this.timeOutParameters = timeOutParameters;
        return this;
    }

    protected  <T> T onFailure(ANResponse<T> responseEntity) throws Exception {
        if (responseEntity.getError() != null && responseEntity.getError().getErrorBody() != null) {
            String message = responseEntity.getError().getErrorBody();
            ErrorBody errorBody = jacksonMapper.toObject(message, ErrorBody.class);
            throw new Exception(errorBody.getError());
        } else if (responseEntity.getError() != null
                && responseEntity.getError().getCause() != null
                && responseEntity.getError().getCause().getCause() instanceof SocketTimeoutException) {
            throw new Exception(responseEntity.getError().getCause().getCause().getMessage());
        } else if (responseEntity.getError() != null)
            throw new Exception(responseEntity.getError().getMessage());
        else if (responseEntity.getOkHttpResponse() != null) {
            throw new Exception(responseEntity.getOkHttpResponse().message());
        } else {
            throw new Exception("Unknown error");
        }
    }
}