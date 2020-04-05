package com.whpu.crowd.service.api;

import com.whpu.crowd.entity.po.MemberPO;

public interface MemberService {

	MemberPO getMemberPOByLoginAcct(String loginacct);
	public int saveMember(MemberPO memberPO);
}
