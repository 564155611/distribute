package com.fanx.distribute.lock.whatislock;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class User {
    private Cabinet cabinet;
    private int storeNumber;

    public void useCabinet(){
        cabinet.setStoreNumber(storeNumber);
    }
}
