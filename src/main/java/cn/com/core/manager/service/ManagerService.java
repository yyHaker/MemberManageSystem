package cn.com.core.manager.service;

import cn.com.core.manager.entity.Manager;
import cn.com.core.manager.entity.ManagerExample;
import cn.com.core.manager.mapper.ManagerMapper;
import cn.com.core.manager.request.ManagerPage;
import cn.com.core.manager.utils.BeanUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * ManagerService
 *门店管理员service业务
 * @author Le Yuan
 * @date 2016/12/2
 */
@Component
public class ManagerService {
    @Resource private ManagerMapper managerMapper;

    /**
     * 根据主键id查询管理员
     * @param id
     * @return
     */
    public Manager getManagerById(String id){
        return managerMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据主键id删除管理员
     */
    public int deleteManagerById(String id){
        return managerMapper.deleteByPrimaryKey(id);
    }


    /**
     * 根据组合条件查询管理员信息
     */
    public ManagerPage searchManagerListPage(Manager manager, int beginIndex, int pageSize){
        ManagerExample managerExample=new ManagerExample();
        ManagerExample.Criteria criteria=managerExample.createCriteria();

        if (manager.getId()!=null){
            criteria.andIdEqualTo(manager.getId());
        }
        if (manager.getName()!=null){
            criteria.andNameLike(manager.getName());
        }
        if (manager.getEmail()!=null){
            criteria.andTelephoneLike(manager.getTelephone());
        }
        if (manager.getTelephone()!=null){
            criteria.andTelephoneLike(manager.getTelephone());
        }

        managerExample.setDistinct(true);
        PageHelper.startPage(beginIndex,pageSize);
        List<Manager> managerList=managerMapper.selectByExample(managerExample);
        PageInfo<Manager> managerPageInfo=new PageInfo<>(managerList);
        return new ManagerPage(BeanUtils.toManagerJsonList(managerList),managerPageInfo);
    }

    /**
     * 添加新的管理员
     */
    public int addNewManager(Manager manager){
        int insertCount=0;
         if (manager!=null){
             manager.setCreateTime(new Date());
             manager.setUpdateTime(new Date());
             insertCount=managerMapper.insert(manager);
         }
         return insertCount;
    }


    /**
     * 更改管理员信息
     */
     public  int updateOldManager(Manager manager){
         int updateCount=0;
        if (manager.getId()!=null){
            ManagerExample managerExample=new ManagerExample();
            ManagerExample.Criteria criteria=managerExample.createCriteria();
            criteria.andIdEqualTo(manager.getId());
            manager.setUpdateTime(new Date());
            updateCount=managerMapper.updateByExampleSelective(manager,managerExample);
        }
        return updateCount;
     }

}
