package com.m6world.tari_labs.api.contracts

import com.m6world.tari_labs.api.models.AuthResponse
import io.reactivex.Observable

interface IAuthInteractor {
    fun getAuthToken(email: String, password: String): Observable<AuthResponse>
    fun getAuthRefreshToken(accessToken: String, refreshToken: String): Observable<AuthResponse>
}