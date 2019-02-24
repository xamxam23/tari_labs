package com.m6world.tari_labs.api._interactors;

import com.m6world.tari_labs.api._contracts.IAuthInteractor;
import com.m6world.tari_labs.api.models.AuthResponse;
import com.m6world.tari_labs.api.models.TokenRequest;
import com.m6world.tari_labs.api.models.RefreshRequest;
import io.reactivex.Observable;

public class AuthInteractor implements IAuthInteractor {
    PostNetworkInteractor postNetworkInteractor = new PostNetworkInteractor();

    private final String AUTH_TOKEN_ENDPOINT = "auth/token";
    private final String AUTH_TOKEN_REFRESH_ENDPOINT = "auth/token/refresh";

    public Observable<AuthResponse> getAuthToken(String email, String password) {
        TokenRequest request = new TokenRequest(email, password);
        return postNetworkInteractor.createObservable(AUTH_TOKEN_ENDPOINT, request, AuthResponse.class);
    }

    public Observable<AuthResponse> getAuthRefreshToken(String accessToken, String refreshToken) {
        RefreshRequest request = new RefreshRequest(accessToken, refreshToken);
        return postNetworkInteractor.createObservable(AUTH_TOKEN_REFRESH_ENDPOINT, request, AuthResponse.class);
    }
}