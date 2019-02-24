package com.m6world.tari_labs.features._home

import android.util.Log
import com.m6world.tari_labs.api.contracts.IAuthInteractor
import com.m6world.tari_labs.api.models.AuthResponse
import com.m6world.tari_labs.features._abstract.BaseViewModel
import com.m6world.tari_labs.features._abstract.IHomeViewModel
import io.reactivex.Observable
import javax.inject.Inject

class HomeViewModel() : BaseViewModel(), IHomeViewModel {
    @set:Inject var authInteractor: IAuthInteractor? = null

    init {
        component.inject(this)
        Log.d("x-d", "[HomeViewModel]")
    }

    override fun getAuthToken(): Observable<AuthResponse> {
        var email = "doorperson@test.com"
        var pass = "password"
        return authInteractor!!.getAuthToken(email, pass)
    }

    override fun getAuthToken(accessToken: String, refreshToken: String): Observable<AuthResponse> {
        return authInteractor!!.getAuthRefreshToken(accessToken, refreshToken)
    }
}