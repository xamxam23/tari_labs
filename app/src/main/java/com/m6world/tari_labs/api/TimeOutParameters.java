package com.m6world.tari_labs.api;

public  class TimeOutParameters {
    public final static int CONNECT_DEFAULT = 8, WRITE_DEFAULT = 32, READ_DEFAULT = 96;
    public int connect = CONNECT_DEFAULT, write = WRITE_DEFAULT, read = READ_DEFAULT;

    public TimeOutParameters(int connect, int write, int read) {
        this(connect, write);
        if (read > 0) this.read = read;
    }

    public TimeOutParameters(int connect, int write) {
        this(connect);
        if (write > 0) this.write = write;
    }

    public TimeOutParameters(int connect) {
        if (connect > 0) this.connect = connect;
    }
}