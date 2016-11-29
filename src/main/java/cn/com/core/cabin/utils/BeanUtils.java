package cn.com.core.cabin.utils;

import cn.com.core.cabin.entity.Cabin;
import cn.com.core.cabin.request.CabinJson;
import cn.thinking.common.util.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * BeanUtils
 *转换cabin和cabinJson的类
 * @author Le Yuan
 * @date 2016/11/6
 */
public class BeanUtils {

    /**
     * convert CabinJaon to Cabin
     * @param cabinJson
     * @return
     */
     public static Cabin toCabin(CabinJson cabinJson){
         Cabin cabin=new Cabin();
         if (cabinJson.getId()!=null&&cabinJson.getId()!=""){
             cabin.setId(cabinJson.getId());
         }
          if (cabinJson.getNumber()!=null&&cabinJson.getNumber()!=""){
             cabin.setNumber(cabinJson.getNumber());
         }
         if (cabinJson.getName()!=null&&cabinJson.getName()!=""){
             cabin.setName(cabinJson.getName());
         }
         if (cabinJson.getStoreId()!=null&&cabinJson.getStoreId()!=""){
             cabin.setStoreId(cabinJson.getStoreId());
         }
         if (cabinJson.getRent()!=null){
             cabin.setRent(cabinJson.getRent());
         }
         return cabin;
     }

    /**
     * convert Cabin to Cabin Json
     * @param cabin
     * @return
     */
     public static CabinJson toCabinJson(Cabin cabin){
         CabinJson cabinJson=new CabinJson();
          if (cabin!=null){
              cabinJson.setId(cabin.getId());
              cabinJson.setNumber(cabin.getNumber());
              cabinJson.setName(cabin.getName());
              cabinJson.setStoreId(cabin.getStoreId());
              cabinJson.setRent(cabin.getRent());
              cabinJson.setCreateTime(Tools.date2Str(cabin.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
              cabinJson.setUpdateTime(Tools.date2Str(cabin.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
          }
         return cabinJson;
     }

    /**
     *convert List<Cabin> to List<CabinJson>
     * @param cabinList
     * @return
     */
     public static List<CabinJson> toCabinJsonList(List<Cabin> cabinList){
         List<CabinJson> cabinJsonList=new ArrayList<>();
         for (Cabin cabin:cabinList){
             cabinJsonList.add(toCabinJson(cabin));
         }
         return cabinJsonList;
     }




}
