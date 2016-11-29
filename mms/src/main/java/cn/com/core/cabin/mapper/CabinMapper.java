package cn.com.core.cabin.mapper;

import cn.com.core.cabin.entity.Cabin;
import cn.com.core.cabin.entity.CabinExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CabinMapper {
    int countByExample(CabinExample example);

    int deleteByExample(CabinExample example);

    int deleteByPrimaryKey(String id);

    int insert(Cabin record);

    int insertSelective(Cabin record);

    List<Cabin> selectByExample(CabinExample example);

    Cabin selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Cabin record, @Param("example") CabinExample example);

    int updateByExample(@Param("record") Cabin record, @Param("example") CabinExample example);

    int updateByPrimaryKeySelective(Cabin record);

    int updateByPrimaryKey(Cabin record);
}