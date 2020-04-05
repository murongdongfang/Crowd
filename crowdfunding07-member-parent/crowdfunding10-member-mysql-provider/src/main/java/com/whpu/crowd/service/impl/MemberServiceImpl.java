package com.whpu.crowd.service.impl;

import com.whpu.crowd.entity.po.MemberPO;
import com.whpu.crowd.entity.po.MemberPOExample;
import com.whpu.crowd.mapper.MemberPOMapper;
import com.whpu.crowd.service.api.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


// 在类上使用@Transactional所有的方法都增加事务
// @Transactional(readOnly = true)针对查询操作设置事务属性
//@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberPOMapper memberPOMapper;

	@Transactional(
		propagation = Propagation.REQUIRES_NEW,
		rollbackFor = Exception.class,
		readOnly = false)
	@Override
	public int saveMember(MemberPO memberPO) {

		return memberPOMapper.insertSelective(memberPO);
	}

	/**
	 * timeout  int
	 * 设置超时时间，单位是秒
	 *
	 * rollbackFor  Class[]
	 * rollbackForClassName  String[]
	 * noRollbackFor  Class[]
	 * noRollbackForClassName String[]
	 * 在Java中有两类异常，一类是编译期异常，一类是运行时异常
	 * Spring事务中如果发生编译器异常那么事务不会回滚，如果发生运行时异常事务将回滚
	 * 我们可以通过以上四个属性设置什么异常回滚，什么异常不会滚
	 *
	 *
	 * readOnly boolean
	 * 如果事务方法只是对数据库查询，没有设计增删改操作，设置readOnly的Spring事务会对查询有优化
	 *
	 *
	 * Propagation  Propagation
	 */
	@Override
	@Transactional(readOnly = true)
	public MemberPO getMemberPOByLoginAcct(String loginacct) {
		
		// 1.创建Example对象
		MemberPOExample example = new MemberPOExample();
		
		// 2.创建Criteria对象
		MemberPOExample.Criteria criteria = example.createCriteria();
		
		// 3.封装查询条件
		criteria.andLoginacctEqualTo(loginacct);
		
		// 4.执行查询
		List<MemberPO> list = memberPOMapper.selectByExample(example);
		if(list == null || list.size() == 0){
			return null;
		}
		
		// 5.获取结果
		return list.get(0);
	}

}
