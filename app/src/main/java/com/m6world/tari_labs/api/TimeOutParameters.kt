package com.m6world.tari_labs.api

class TimeOutParameters {
    var connect = CONNECT_DEFAULT
    var write = WRITE_DEFAULT
    var read = READ_DEFAULT

    constructor(connect: Int, write: Int, read: Int) : this(connect, write) {
        if (read > 0) this.read = read
    }

    constructor(connect: Int, write: Int) : this(connect) {
        if (write > 0) this.write = write
    }

    constructor(connect: Int) {
        if (connect > 0) this.connect = connect
    }

    companion object {
        val CONNECT_DEFAULT = 8
        val WRITE_DEFAULT = 32
        val READ_DEFAULT = 96
    }
}