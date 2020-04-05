package com.whpu.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whpu.crowd.constant.CrowdConstant;
import com.whpu.crowd.entity.Admin;
import com.whpu.crowd.entity.AdminExample;
import com.whpu.crowd.exception.LoginAcctAlreadyInUseException;
import com.whpu.crowd.exception.LoginAcctAlreadyInUseForUpdateException;
import com.whpu.crowd.exception.LoginFailedException;
import com.whpu.crowd.mapper.AdminMapper;
import com.whpu.crowd.service.api.AdminService;
import com.whpu.crowd.util.CrowdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

/**
 * @author xxh
 * @date 2020/3/16
 * @discription: crowdfunding01-admin-parent
 */
@Service
public class AdminServiceImpl implements AdminService {

  @Autowired
  private AdminMapper adminMapper;

  @Override
  public int saveAdmin(Admin admin) {
    String userPswd = admin.getUserPswd();
    admin.setUserPswd(CrowdUtil.md5(userPswd));
    LocalDateTime now = LocalDateTime.now();
    String formatTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    admin.setCreateTime(formatTime);
    int res = 0;
    try {

       res = adminMapper.insert(admin);


    } catch (Exception e) {
      if(e instanceof DuplicateKeyException){
        throw new LoginAcctAlreadyInUseException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
      }

    }
    return res;
  }

  @Override
  public List<Admin> getAll() {
    return adminMapper.selectByExample(new AdminExample());
  }

  @Override
  public Admin getAdminByAcct(String loginAcct, String pswd) {
      AdminExample adminExample = new AdminExample();
      AdminExample.Criteria criteria = adminExample.createCriteria().andLoginAcctEqualTo(loginAcct);
      List<Admin> admins = adminMapper.selectByExample(adminExample);
    if(admins.size() == 0 || admins == null){
          throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
      }
      if(admins.size() > 1){
          throw new LoginFailedException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
      }

      Admin admin = admins.get(0);
      if(admin == null){
          throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
      }
      String passwordDB = admin.getUserPswd();
      String md5 = CrowdUtil.md5(pswd);
      if(!Objects.equals(md5,passwordDB)){
          throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
      }
      return admin;
  }

  @Override
  public PageInfo<Admin> getAdminPage(String keyword, Integer pageNum, Integer pageSize) {
    //使用PageHelper启动分页查询
    PageHelper.startPage(pageNum,pageSize);
    List<Admin> admins = adminMapper.selectAdminListByKeyword(keyword);
    //将查询的数据包装成为PageInfo对象，里边包含了每一页的详细信息
    PageInfo<Admin> pageInfo = new PageInfo<>(admins);
    return pageInfo;
  }

  @Override
  public Integer removeAdmin(Integer adminId) {

    return adminMapper.deleteByPrimaryKey(adminId);
  }



  @Override
  public Integer updateAdmin(Admin admin) {
    int res = 0;
    try {
      res = adminMapper.updateByPrimaryKeySelective(admin);
    } catch (Exception e) {
      if(e instanceof DuplicateKeyException){
        throw new LoginAcctAlreadyInUseForUpdateException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
      }
      e.printStackTrace();
    }
    return res;
  }

  @Override
  public void saveAdminRoleRelationShip(Integer adminId, List<Integer> roleIdList) {
    adminMapper.deleteOldRelationship(adminId);
    if(roleIdList != null && roleIdList.size() > 0){
       adminMapper.insertNewRelationship(adminId,roleIdList);
    }
  }

  @Override
  public Admin getAdminById(Integer adminId) {
    Admin admin = adminMapper.selectByPrimaryKey(adminId);
    return admin;
  }
}
