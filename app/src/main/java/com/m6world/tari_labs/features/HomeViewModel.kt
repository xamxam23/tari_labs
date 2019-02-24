package com.m6world.tari_labs.features

import com.m6world.tari_labs.api._contracts.IAuthInteractor
import com.m6world.tari_labs.api._interactors.AuthInteractor
import com.m6world.tari_labs.api.models.AuthResponse
import io.reactivex.Observable

class HomeViewModel {
    var authInteractor: IAuthInteractor = AuthInteractor()

    fun getAuthToken(): Observable<AuthResponse> {
        var email = "doorperson@test.com"
        var pass = "password"
        return authInteractor.getAuthToken(email, pass)
    }

    fun getAuthToken(accessToken: String, refreshToken: String): Observable<AuthResponse> {
        return authInteractor.getAuthRefreshToken(accessToken, refreshToken)
    }
}