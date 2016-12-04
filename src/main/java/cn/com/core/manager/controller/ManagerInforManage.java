package cn.com.core.manager.controller;

import cn.com.core.manager.entity.Manager;
import cn.com.core.manager.request.ManagerJson;
import cn.com.core.manager.request.ManagerPage;
import cn.com.core.manager.service.ManagerService;
import cn.com.core.manager.utils.BeanUtils;
import cn.thinking.common.response.MessageJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * ManagerInforManage
 *
 * @author Le Yuan
 * @date 2016/12/3
 */
@Controller
@RequestMapping("/Manager")
public class ManagerInforManage {
    @Autowired
    private ManagerService managerService;

    /**
     * 根据主键id获取manager信息
     */
    @ResponseBody
    @RequestMapping("/getManager/{id}")
    public ManagerJson getManagerById(@PathVariable("id")String id){
        Manager manager=managerService.getManagerById(id);
        return BeanUtils.toManagerJson(manager);
    }

    /**
     * 根据主键id删除管理员信息
     */
    @ResponseBody
    @RequestMapping("/deleteManager/{id}")
    public MessageJson deleteManagerById(@PathVariable("id") String id){
        int deleteCount=managerService.deleteManagerById(id);
        if (deleteCount==1){
            return new MessageJson("success");
        }else {
            return new MessageJson("failure");
        }
    }



    /**
     * 根据组合条件查询管理员信息
     */
    @ResponseBody
    @RequestMapping(value = "/searchManagerListPage",method = RequestMethod.POST)
    public ManagerPage searchManagerListPage(@RequestBody ManagerJson managerJson){
          Manager manager=BeanUtils.toManager(managerJson);
         return managerService.searchManagerListPage(manager,managerJson.getBeginIndex(),managerJson.getPageSize());
    }

    /**
     * 添加新的管理员
     */
    @ResponseBody
    @RequestMapping(value="/addNewManager",method = RequestMethod.POST)
    public MessageJson addNewManager(@RequestBody ManagerJson managerJson){
          Manager manager=BeanUtils.toManager(managerJson);
          Manager managerExist=managerService.getManagerById(manager.getId());
         if (managerExist!=null){
             return new MessageJson("failure");
         }
          managerService.addNewManager(manager);
          return new MessageJson("success");
    }

    /**
     * 更改管理员信息
     */
    @ResponseBody
     @RequestMapping(value = "/updateOldManager",method = RequestMethod.POST)
     public MessageJson updateOldManager(@RequestBody ManagerJson managerJson ){
         int updateCount=0;
          Manager manager=BeanUtils.toManager(managerJson);
         updateCount=managerService.updateOldManager(manager);
         if (updateCount==1){
             return new MessageJson("success");
         }else {
             return new MessageJson("failure");
         }

    }

}
