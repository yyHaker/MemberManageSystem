package cn.com.core.cabin.service;

import cn.com.core.cabin.entity.Cabin;
import cn.com.core.cabin.entity.CabinExample;
import cn.com.core.cabin.mapper.CabinMapper;
import cn.com.core.cabin.request.CabinJson;
import cn.com.core.cabin.request.CabinPage;
import cn.com.core.cabin.utils.BeanUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * CabinService
 *
 * @author Le Yuan
 * @date 2016/11/6
 */
@Component
public class CabinService {
    @Resource private CabinMapper cabinMapper;

    /**
     * 根据主键id查找飞行舱
     * @param id
     * @return
     */
     public Cabin getCabinById(String id){
         return cabinMapper.selectByPrimaryKey(id);
     }

    /**
     * 根据主键删除飞行舱
     * @param id
     * @return
     */
     public int deleteCabinById(String id){
         return cabinMapper.deleteByPrimaryKey(id);
     }

    /**
     * 根据组合条件查询飞行舱
     * @param cabin
     * @param beginIndex
     * @param pageSize
     * @return
     */
    public CabinPage searchCabinListPage(Cabin cabin,int beginIndex,int pageSize){
        CabinExample cabinExample=new  CabinExample();
        CabinExample.Criteria criteria=cabinExample.createCriteria();

        if (cabin.getId()!=null){
            criteria.andIdEqualTo(cabin.getId());
        }
        if (cabin.getNumber()!=null){
            criteria.andNumberEqualTo(cabin.getNumber());
        }
        if (cabin.getName()!=null){
            criteria.andNameLike("%"+cabin.getName()+"%");
        }
        if (cabin.getStoreId()!=null){
            criteria.andStoreIdEqualTo(cabin.getStoreId());
        }
        if (cabin.getRent()!=null){
            criteria.andRentGreaterThan(cabin.getRent());
        }
       cabinExample.setDistinct(true);
        PageHelper.startPage(beginIndex,pageSize);
        List<Cabin> cabinList=cabinMapper.selectByExample(cabinExample);

        PageInfo<Cabin> cabinPageInfo=new PageInfo<>(cabinList);

        return new CabinPage(BeanUtils.toCabinJsonList(cabinList),cabinPageInfo);
    }

    /**
     * 添加新的飞行舱
     * @param cabin
     * @return
     */
    public int addNewCabin(Cabin cabin){
       int changeCount=0;
        if (cabin!=null){
            cabin.setCreateTime(new Date());
            cabin.setUpdateTime(new Date());
            changeCount=cabinMapper.insert(cabin);
        }
        return changeCount;
    }

    /**
     * 更改飞行舱的信息
     * @param cabin
     * @return
     */
    public int updateOldCabin(Cabin cabin){
        int changeCount=0;
        if (cabin.getStoreId()!=null&&cabin.getName()!=null){
            CabinExample cabinExample=new CabinExample();
            CabinExample.Criteria criteria=cabinExample.createCriteria();
            criteria.andIdEqualTo(cabin.getId());
            cabin.setUpdateTime(new Date());
            changeCount=cabinMapper.updateByExampleSelective(cabin,cabinExample);
        }
        return  changeCount;
    }

}
