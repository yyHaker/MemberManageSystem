package cn.com.core.store.request.impl;

import cn.com.core.store.request.StoreRequest;

/**
 * Created by Zhang Siqi on 2016/11/11.
 */
public class StoreOprationRequest implements StoreRequest{
    private String storeId;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
