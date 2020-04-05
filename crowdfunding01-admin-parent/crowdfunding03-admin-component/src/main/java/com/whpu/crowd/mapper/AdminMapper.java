package com.whpu.crowd.mapper;

import com.whpu.crowd.entity.Admin;
import com.whpu.crowd.entity.AdminExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AdminMapper {
    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);
    List<Admin> selectAdminListByKeyword(@Param("keyword") String keyword);

    /**
     * 根据关键词查询，再service层使用分页
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);


  Integer insertNewRelationship(@Param("adminId") Integer adminId, @Param("roleIdList") List<Integer> roleIdList);

    Integer deleteOldRelationship(@Param("adminId") Integer adminId);
}