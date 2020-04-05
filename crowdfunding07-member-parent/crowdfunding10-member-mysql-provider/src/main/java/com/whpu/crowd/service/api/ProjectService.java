package com.whpu.crowd.service.api;

import com.whpu.crowd.entity.vo.DetailProjectVO;
import com.whpu.crowd.entity.vo.PortalTypeVO;
import com.whpu.crowd.entity.vo.ProjectVO;

import java.util.List;

/**
 *@author xxh
 *@date 2020/4/2
 *@discription: crowdfunding07-member-parent
 */
public interface ProjectService {
  public void saveProject(ProjectVO projectVO, Integer memberId);
  public List<PortalTypeVO> getPortalTypeVO();
  DetailProjectVO getDetailProjectVO(Integer projectId);
}
