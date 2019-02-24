package com.m6world.tari_labs.features._abstract

import com.m6world.tari_labs.MainApplication
import com.m6world.tari_labs._dagger.AppComponent
import com.m6world.tari_labs._dagger.DaggerAppComponent
import com.m6world.tari_labs.api.contracts.IAuthInteractor
import com.m6world.tari_labs.api.models.AuthResponse
import io.reactivex.Observable
import javax.inject.Inject

open class BaseViewModel() {
    internal val component: AppComponent

    init {
        component = DaggerAppComponent.builder().application(MainApplication.getInstance()).build()
    }
}