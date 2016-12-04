package cn.com.core.store.controller;

import cn.com.core.store.Constants;
import cn.com.core.store.entity.Store;
import cn.com.core.store.request.StoreRequest;
import cn.com.core.store.request.StoresPagesRequest;
import cn.com.core.store.request.impl.ManagerRequest;
import cn.com.core.store.request.impl.StoreAddRequest;
import cn.com.core.store.request.impl.StoreOprationRequest;
import cn.com.core.store.response.MessageResponse;
import cn.com.core.store.response.StoresPagesResponse;
import cn.com.core.store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Zhang Siqi on 2016/11/11.
 */
@RequestMapping(value = "/store")
@Controller
public class StoreController {
    @Autowired
    private StoreService storeService;

    @ResponseBody
    @RequestMapping(value = "/addstore", method = RequestMethod.POST)
    public MessageResponse addStore(@RequestBody StoreAddRequest storeAddRequest){
        String msg = storeService.addStore(storeAddRequest);
    	MessageResponse response = new MessageResponse();
    	response.setMessage(msg);
    	if (msg.equals(Constants.STORE_NOT_EXIST)) {
			response.setCode(Constants.MESSAGE_RIGHT);
		}else {
			response.setCode(Constants.MESSAGE_ERROR);
		}
        return response;
    }
    @ResponseBody
    @RequestMapping(value = "/addmanager", method = RequestMethod.POST)
    public MessageResponse addManager(@RequestBody ManagerRequest request){
        String msg = storeService.addManager(request.getStoreId(), request.getManagerId());
    	MessageResponse response = new MessageResponse();
    	response.setMessage(msg);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/deletemanager", method = RequestMethod.POST)
    public MessageResponse deleteManager(@RequestBody ManagerRequest request){
        String msg = storeService.addManager(request.getStoreId(), request.getManagerId());
    	MessageResponse response = new MessageResponse();
    	response.setMessage(msg);
        return response;
    }
    @ResponseBody
    @RequestMapping(value = "/deletestore", method = RequestMethod.POST)
    public MessageResponse deleteStore(@RequestBody StoreOprationRequest request){
    	Store store = storeService.getStore(request.getStoreId());
		String msg = "";
    	if (store == null) {
    		msg = "该门店不存在";
		}else {
	        storeService.deleteStore(request.getStoreId());
    		msg = "删除成功";
		}
    	MessageResponse response = new MessageResponse();
    	
    	response.setMessage(msg);
        return response;
    }


    @ResponseBody
    @RequestMapping(value = "/enablestore", method = RequestMethod.POST)
    public MessageResponse enableStore(@RequestBody StoreOprationRequest request){
    	Store store = storeService.getStore(request.getStoreId());
		String msg = "";
    	if (store == null) {
    		msg = "该用户不存在";
		}else if(store.getFlag().equals(Constants.STORE_IS_UNLOCK)){
    		msg = "该用户已经激活";
		}else {
	        storeService.enableStore(request.getStoreId());
    		msg = "激活成功";
		}
    	MessageResponse response = new MessageResponse();
    	
    	response.setMessage(msg);
        return response;
    }


    @ResponseBody
    @RequestMapping(value = "/disablestore", method = RequestMethod.POST)
    public MessageResponse disableStore(@RequestBody StoreOprationRequest request){
    	Store store = storeService.getStore(request.getStoreId());
		String msg = "";
    	if (store == null) {
    		msg = "该用户不存在";
		}else if(store.getFlag().equals(Constants.STORE_IS_LOCK)){
    		msg = "该用户未激活";
		}else {
	        storeService.disableStore(request.getStoreId());
    		msg = "锁定成功";
		}
    	MessageResponse response = new MessageResponse();
    	
    	response.setMessage(msg);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/changestore", method = RequestMethod.POST)
    public MessageResponse  changeStore(@RequestBody Store store){
    	Store storeExist = storeService.getStore(store.getId());
		String msg = "";
    	if (storeExist == null) {
    		msg = "该门店不存在";
		}else {
	        storeService.changeStore(store);;
    		msg = "修改成功";
		}
    	MessageResponse response = new MessageResponse();
    	
    	response.setMessage(msg);
        return response;
    }

    @RequestMapping(value = "/showstores", method = RequestMethod.POST)
    public List<Store> showstores(@RequestBody Store store){
        List<Store> storeList = storeService.selectStoreSelective(store);
        return storeList;
    }
    
    @ResponseBody
    @RequestMapping(value = "/searchstorespages", method = RequestMethod.POST)
    public StoresPagesResponse searchstorespages(@RequestBody StoresPagesRequest request){
    	StoresPagesResponse response = new StoresPagesResponse();
    	response = storeService.selectStoreSelectivePage(request);
    	return response;
    }

    @ResponseBody
    @RequestMapping(value = "/getstore", method = RequestMethod.POST)
    public Store getStore(@RequestBody StoreOprationRequest request){
        Store store = storeService.getStore(request.getStoreId());
        return store;
    }

        @RequestMapping("/addData")
         public void addData() {
          for (int i = 2; i <=100; i++) {

          }
      }
}
