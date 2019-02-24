package com.m6world.tari_labs.api.models

import com.fasterxml.jackson.annotation.JsonProperty

class AuthResponse {
    @field:JsonProperty("access_token") var accessToken: String? = null
    @field:JsonProperty("refresh_token") var refreshToken: String? = null
}
