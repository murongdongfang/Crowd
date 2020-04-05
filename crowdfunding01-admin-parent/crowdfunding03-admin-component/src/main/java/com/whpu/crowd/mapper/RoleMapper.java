package com.whpu.crowd.mapper;

import com.whpu.crowd.entity.Role;
import com.whpu.crowd.entity.RoleExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleMapper {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    public List<Role> selectRoleByKeyword(String keyword);

  List<Role> selectUnAsignedRole(@Param("adminId") Integer adminId);

    List<Role> selectAssignedRole(@Param("adminId") Integer adminId);
}