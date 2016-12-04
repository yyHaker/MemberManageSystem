package cn.com.core.manager.utils;

import cn.com.core.manager.entity.Manager;
import cn.com.core.manager.request.ManagerJson;
import cn.thinking.common.util.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * BeanUtils
 *
 * @author Le Yuan
 * @date 2016/12/3
 */
public class BeanUtils {
    /**
     * 将Manager转换为ManagerJson
     */
    public static ManagerJson toManagerJson(Manager manager){
         ManagerJson managerJson=new ManagerJson();
        if (manager.getId()!=null){
            managerJson.setId(manager.getId());
        }
        if (manager.getName()!=null){
            managerJson.setName(manager.getName());
        }
        if (manager.getEmail()!=null){
            managerJson.setEmail(manager.getEmail());
        }
        if (manager.getTelephone()!=null){
            managerJson.setTelephone(manager.getTelephone());
        }
        if (manager.getCreateTime()!=null){
            managerJson.setCreateTime(Tools.date2Str(manager.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            //  cabinJson.setCreateTime(Tools.date2Str(cabin.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
        }
        if (manager.getUpdateTime()!=null){
            managerJson.setUpdateTime(Tools.date2Str(manager.getUpdateTime()));
        }
        return managerJson;
    }

    /**
     * 将ManagerJson转换为Manager
     */
    public static Manager toManager(ManagerJson managerJson){
           Manager manager=new Manager();
         if (managerJson.getId()!=null&&managerJson.getId()!=""){
             manager.setId(managerJson.getId());
         }
         if (managerJson.getName()!=null&&managerJson.getName()!=""){
             manager.setName(managerJson.getName());
         }
         if (managerJson.getEmail()!=null&&managerJson.getEmail()!=""){
             manager.setEmail(managerJson.getEmail());
         }
         if (managerJson.getTelephone()!=null&&managerJson.getTelephone()!=""){
             manager.setTelephone(managerJson.getTelephone());
         }
         //前台不会传递时间参数
        return manager;
    }

    /**
     * 将List<Manager>转换为List<ManagerJson>
     */
    public static List<ManagerJson> toManagerJsonList(List<Manager> managerList){
          List<ManagerJson> managerJsonList=new ArrayList<>();
          for (Manager manager:managerList){
              managerJsonList.add(toManagerJson(manager));
          }
          return managerJsonList;
    }
}
