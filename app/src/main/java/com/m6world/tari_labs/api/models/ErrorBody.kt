package com.m6world.tari_labs.api.models

import com.fasterxml.jackson.annotation.JsonProperty

class ErrorBody(
    @field:JsonProperty("error") var error: String? = null
)