package com.whpu.crowd.mapper;

import com.whpu.crowd.entity.Auth;
import com.whpu.crowd.entity.AuthExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AuthMapper {
    int countByExample(AuthExample example);

    int deleteByExample(AuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Auth record);

    int insertSelective(Auth record);

    List<Auth> selectByExample(AuthExample example);

    Auth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByExample(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    List<Integer> selectAssignedAuthIdByRoleId(@Param("roleId") Integer roledId);

    Integer deleteOldRelationShip(Integer roleId);

    Integer insertNewRelationShip(@Param("roleId") Integer roleId, @Param("authIdList") List<Integer> authIdList);
}