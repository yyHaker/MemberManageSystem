package cn.com.core.store.service.impl;

import cn.com.core.store.Constants;
import cn.com.core.store.entity.Store;
import cn.com.core.store.entity.StoreExample;
import cn.com.core.store.mapper.StoreMapper;
import cn.com.core.store.request.StoresPagesRequest;
import cn.com.core.store.request.impl.StoreAddRequest;
import cn.com.core.store.response.StoresPagesResponse;
import cn.com.core.store.response.entity.StoreEntity;
import cn.com.core.store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Zhang Siqi on 2016/11/6.
 */

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreMapper storeMapper;

    @Override
    public String addStore(StoreAddRequest storeAddRequest) {
        Store store = pasreStoreAddRequest(storeAddRequest);
        Store storeExist = storeMapper.selectByPrimaryKey(store.getId());
        if (storeExist != null){
            return Constants.STORE_EXIST;
        }

        storeMapper.insert(store);
        return Constants.STORE_NOT_EXIST;
    }

    @Override
    public String addManager(String storeId, String managerId){
        Store store = storeMapper.selectByPrimaryKey(storeId);

        String managerIds = store.getManagerId();

        if (managerIds.contains(managerId)){
            return Constants.MANAGER_EXIST;
        }

        List<String> managerIdList = Arrays.asList(managerIds.split(Constants.MANAGER_SPLIT));
        managerIdList.add(managerId);

        managerIds = managerIdList.get(0);
        for (int i = 1; i < managerIdList.size(); i ++){
            managerIds += Constants.MANAGER_SPLIT + managerIdList.get(i);
        }
        store.setManagerId(managerIds);

        storeMapper.updateByPrimaryKeySelective(store);

        return Constants.MANAGER_NOT_EXIST;
    }

    @Override
    public String deleteManager(String storeId, String managerId){
        Store store = storeMapper.selectByPrimaryKey(storeId);

        String managerIds = store.getManagerId();

        if (!managerIds.contains(managerId)){
            return Constants.MANAGER_NOT_EXIST;
        }

        List<String> managerIdList = Arrays.asList(managerIds.split(Constants.MANAGER_SPLIT));
        managerIdList.add(managerId);

        managerIds = "";
        for (int i = 0; i < managerIdList.size(); i ++){
            if (managerIdList.get(i).equals(managerId))
                continue;
            managerIds += Constants.MANAGER_SPLIT + managerIdList.get(i);
        }
        store.setManagerId(managerIds.substring(1));

        storeMapper.updateByPrimaryKeySelective(store);

        return Constants.MANAGER_EXIST;
    }


    @Override
    public void deleteStore(String storeId){
        storeMapper.deleteByPrimaryKey(storeId);
    }

    @Override
    public void enableStore(String storeId){
        Store store = storeMapper.selectByPrimaryKey(storeId);
        store.setFlag(Constants.STORE_IS_UNLOCK);
        storeMapper.updateByPrimaryKeySelective(store);
    }

    @Override
    public void disableStore(String storeId){
        Store store = storeMapper.selectByPrimaryKey(storeId);
        store.setFlag(Constants.STORE_IS_LOCK);
        storeMapper.updateByPrimaryKeySelective(store);
    }

    @Override
    public void  changeStore(Store store){
        storeMapper.updateByPrimaryKeySelective(store);
    }

    @Override
    public List<Store> selectStoreSelective(Store store){
        store = pasreStore(store);
        StoreExample example = new StoreExample();
        example.createCriteria()
                .andIdLike(store.getId())
                .andNameLike(store.getName())
                .andCityLike(store.getCity())
                .andManagerIdLike(store.getManagerId())
                .andAddressLike(store.getAddress())
                .andTelephoneLike(store.getTelephone())
                .andFlagLike(store.getFlag());
        List<Store> storeList = storeMapper.selectByExample(example);
        return storeList;
    }

    @Override
    public Store getStore(String storeId){
        Store store = storeMapper.selectByPrimaryKey(storeId);
        return store;
    }
    

	@Override
	public StoresPagesResponse selectStoreSelectivePage(StoresPagesRequest request) {
		Store store = pasreStore(request.getStore());
        StoreExample example = new StoreExample();
         StoreExample.Criteria criteria=example.createCriteria();
        criteria.andIdLike(store.getId())
                .andNameLike(store.getName())
                .andCityLike(store.getCity())
                .andManagerIdLike(store.getManagerId())
                .andAddressLike(store.getAddress())
                .andTelephoneLike(store.getTelephone())
                .andFlagLike(store.getFlag());
        
        PageHelper.startPage(request.getBeginPageIndex(), request.getPageSize());
        List<Store> stores = storeMapper.selectByExample(example);
        PageInfo<Store> pageInfo = new PageInfo<>(stores);
        StoresPagesResponse response = new StoresPagesResponse();
        List<StoreEntity> entities = new ArrayList<>();
        for(Store s: stores){
        	StoreEntity entity = new StoreEntity(s);
        	entities.add(entity);
        }
        response.setStores(entities);
        response.setPageInfo(pageInfo);

        return response;
	}

    private Store pasreStore(Store store) {
        store.setId(store.getId() == null ? "%" : "%" + store.getId() + "%");
        store.setName(store.getName() == null ? "%" : "%" + store.getName() + "%");
        store.setCity(store.getCity() == null ? "%" : "%" + store.getCity() + "%");
        store.setManagerId(store.getManagerId() == null ? "%" : "%" + store.getManagerId() + "%");
        store.setAddress(store.getAddress() == null ? "%" : "%" + store.getAddress() + "%");
        store.setTelephone(store.getTelephone() == null ? "%" : "%" + store.getTelephone() + "%");
        store.setFlag(store.getFlag() == null ? "%" : "%" + store.getFlag() + "%");
        return store;
    }

    private Store pasreStoreAddRequest(StoreAddRequest storeAddRequest) {
        Store store = new Store();
        store.setId(storeAddRequest.getId());
        store.setNumber(storeAddRequest.getId());
        store.setAddress(storeAddRequest.getAddress());
        store.setCity(storeAddRequest.getCity());
        store.setName(storeAddRequest.getName());
        store.setTelephone(storeAddRequest.getTelephone());

        store.setFlag(Constants.STORE_IS_UNLOCK);
        store.setCreateTime(new Date());
        store.setUpdateTime(store.getCreateTime());
        store.setManagerId(storeAddRequest.getManagerId());
        return store;
    }

}
