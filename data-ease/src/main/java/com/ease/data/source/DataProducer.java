package com.ease.data.source;

public class DataProducer implements Runnable {

    private CachedDataSource source;

    public void setSource(CachedDataSource source) {
        this.source = source;
    }

    @Override
    public void run() {

    }
}
