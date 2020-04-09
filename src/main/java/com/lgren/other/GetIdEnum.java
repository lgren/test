package com.lgren.other;

public enum GetIdEnum {
    BUILD;
    SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
    public long getId() {
        return idWorker.nextId();
    }
}
