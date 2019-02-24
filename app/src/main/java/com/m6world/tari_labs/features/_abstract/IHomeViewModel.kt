package com.m6world.tari_labs.features._abstract

import com.m6world.tari_labs.api.contracts.IAuthInteractor
import com.m6world.tari_labs.api.models.AuthResponse
import com.m6world.tari_labs.features._abstract.BaseViewModel
import io.reactivex.Observable
import javax.inject.Inject

interface IHomeViewModel {
    fun getAuthToken(): Observable<AuthResponse>
    fun getAuthToken(accessToken: String, refreshToken: String): Observable<AuthResponse>
}