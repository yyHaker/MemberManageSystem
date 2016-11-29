package cn.com.shiro.mapper;

import cn.com.shiro.entity.UserToRoleExample;
import cn.com.shiro.entity.UserToRoleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserToRoleMapper {
    int countByExample(UserToRoleExample example);

    int deleteByExample(UserToRoleExample example);

    int deleteByPrimaryKey(UserToRoleKey key);

    int insert(UserToRoleKey record);

    int insertSelective(UserToRoleKey record);

    List<UserToRoleKey> selectByExample(UserToRoleExample example);

    int updateByExampleSelective(@Param("record") UserToRoleKey record, @Param("example") UserToRoleExample example);

    int updateByExample(@Param("record") UserToRoleKey record, @Param("example") UserToRoleExample example);
}