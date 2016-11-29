package cn.com.core.store.service;

import cn.com.core.store.entity.Store;
import cn.com.core.store.request.StoresPagesRequest;
import cn.com.core.store.request.impl.StoreAddRequest;
import cn.com.core.store.response.StoresPagesResponse;

import java.util.List;

/**
 * Created by Zhang Siqi on 2016/11/6.
 */
public interface StoreService {
    public String addStore(StoreAddRequest storeAddRequest);

    public String addManager(String storeId, String managerId);

    public String deleteManager(String storeId, String managerId);

    public void deleteStore(String storeId);

    public void enableStore(String storeId);

    public void disableStore(String storeId);

    public void  changeStore(Store store);

    public List<Store> selectStoreSelective(Store store);

    Store getStore(String storeId);

	public StoresPagesResponse selectStoreSelectivePage(StoresPagesRequest request);
}
