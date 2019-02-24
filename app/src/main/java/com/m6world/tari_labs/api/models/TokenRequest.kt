package com.m6world.tari_labs.api.models

import com.fasterxml.jackson.annotation.JsonProperty

class TokenRequest(
    @field:JsonProperty("email") var email: String?,
    @field:JsonProperty("password") var password: String?
)