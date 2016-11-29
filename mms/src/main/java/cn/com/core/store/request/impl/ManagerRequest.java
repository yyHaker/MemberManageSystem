package cn.com.core.store.request.impl;

import cn.com.core.store.request.StoreRequest;

/**
 * Created by Zhang Siqi on 2016/11/11.
 */
public class ManagerRequest implements StoreRequest {
    private String storeId;
    private String managerId;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
}
