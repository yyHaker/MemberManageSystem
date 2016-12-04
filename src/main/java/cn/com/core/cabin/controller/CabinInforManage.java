package cn.com.core.cabin.controller;

import cn.com.core.cabin.entity.Cabin;
import cn.com.core.cabin.request.CabinJson;
import cn.com.core.cabin.request.CabinPage;
import cn.com.core.cabin.service.CabinService;
import cn.com.core.cabin.utils.BeanUtils;
import cn.thinking.common.response.MessageJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * CabinInforManage
 *
 * @author Le Yuan
 * @date 2016/11/6
 */

@Controller
@RequestMapping("/Cabin")
public class CabinInforManage {

    @Autowired
    private CabinService cabinService;

    /**
     * 根据主键id获得飞行舱
     * @param id
     * @return
     */
     @ResponseBody
     @RequestMapping("/getCabin/{id}")
      public CabinJson getCabinById(@PathVariable("id") String id){
         Cabin cabin=cabinService.getCabinById(id);
         return BeanUtils.toCabinJson(cabin);
     }

    /**
     * 根据主键id删除飞行舱
     * @param id
     */
    @ResponseBody
    @RequestMapping("/deleteCabin/{id}")
     public MessageJson deleteCabinById(@PathVariable("id")String id){
         int deleteCount=cabinService.deleteCabinById(id);
        if (deleteCount==1){
            return new MessageJson("success");
        }else {
            return new MessageJson("failure");
        }
     }


    /**
     * 添加新的飞行舱
     * @param cabinJson
     * @return
     */
     @ResponseBody
     @RequestMapping(value = "/addNewCabin",method = RequestMethod.POST)
     public Cabin addNewCabin(@RequestBody CabinJson cabinJson){
         Cabin cabin=BeanUtils.toCabin(cabinJson);
          Cabin cabinExits=cabinService.getCabinById(cabin.getId());
          if (cabinExits!=null){
              return cabinExits;
          }
          cabinService.addNewCabin(cabin);
         return null;
     }


    /**
     * 更新飞行舱的信息
     * @param cabinJson
     */
    @ResponseBody
    @RequestMapping(value="/updateCabin",method = RequestMethod.POST)
      public MessageJson updateOldCabin(@RequestBody CabinJson cabinJson){
          int changeCount=0;
         Cabin cabin=BeanUtils.toCabin(cabinJson);
          changeCount=cabinService.updateOldCabin(cabin);
         if (changeCount==1){
             return new MessageJson("success");
         }else {
             return new MessageJson("failure");
         }
     }


    /**
     * 查询飞行舱信息并分页
     * @param cabinJson
     * @return
     */
    @ResponseBody
     @RequestMapping(value = "/searchCabinsListPage",method = RequestMethod.POST)
     public CabinPage searchCabinListPage(@RequestBody CabinJson cabinJson){
         Cabin cabin=BeanUtils.toCabin(cabinJson);
         return cabinService.searchCabinListPage(cabin,cabinJson.getBeginPageIndex(),cabinJson.getPageSize());
     }


   /* @RequestMapping("/addData")
    public void addData(){
         for (int i=0;i<30;i++){
             cabinMapper.insert(new Cabin(i+1+"",GenerateDataHelper.produceNumbers(4),GenerateDataHelper.produceName(5)
                     ,GenerateDataHelper.produceNumbers(1),Float.parseFloat(GenerateDataHelper.produceNumbers(4)),
                     GenerateDataHelper.prduceDate(),GenerateDataHelper.prduceDate()));
         }
    }*/


}
