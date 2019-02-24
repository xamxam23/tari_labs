package com.m6world.tari_labs.api.models

class EventCheckinsResponse {
    var data: Array<Any>? = null
    var paging: Paging? = null
}

class Paging {
    var page: Int = 0
    var limit: Int = 0
    var sort: String? = null
    var dir: String? = null
    var total: Int = 0
    var tags: Any? = null
}