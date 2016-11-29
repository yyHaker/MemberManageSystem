package cn.com.core.store.response;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.com.core.store.entity.Store;
import cn.com.core.store.response.entity.StoreEntity;

public class StoresPagesResponse {
	private List<StoreEntity> stores;
	private PageInfo<Store> pageInfo;

	public PageInfo<Store> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<Store> pageInfo) {
		this.pageInfo = pageInfo;
	}

	public List<StoreEntity> getStores() {
		return stores;
	}

	public void setStores(List<StoreEntity> stores) {
		this.stores = stores;
	}

	
}
