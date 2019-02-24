package com.m6world.tari_labs.api.interactors

import com.m6world.tari_labs.api.contracts.IAuthInteractor
import com.m6world.tari_labs.api.models.AuthResponse
import com.m6world.tari_labs.api.models.TokenRequest
import com.m6world.tari_labs.api.models.RefreshRequest
import io.reactivex.Observable

class AuthInteractor : IAuthInteractor {
    internal var postNetworkInteractor = PostNetworkInteractor()

    private val AUTH_TOKEN_ENDPOINT = "auth/token"
    private val AUTH_TOKEN_REFRESH_ENDPOINT = "auth/token/refresh"

    override fun getAuthToken(email: String, password: String): Observable<AuthResponse> {
        val request = TokenRequest(email, password)
        return postNetworkInteractor.createObservable(AUTH_TOKEN_ENDPOINT, request, AuthResponse::class.java)
    }

    override fun getAuthRefreshToken(accessToken: String, refreshToken: String): Observable<AuthResponse> {
        val request = RefreshRequest(accessToken, refreshToken)
        return postNetworkInteractor.createObservable(AUTH_TOKEN_REFRESH_ENDPOINT, request, AuthResponse::class.java)
    }
}