package com.whpu.crowd.service.impl;


import com.whpu.crowd.entity.po.AddressPO;
import com.whpu.crowd.entity.po.AddressPOExample;
import com.whpu.crowd.entity.vo.AddressVO;
import com.whpu.crowd.entity.vo.OrderProjectVO;
import com.whpu.crowd.mapper.AddressPOMapper;
import com.whpu.crowd.mapper.OrderPOMapper;
import com.whpu.crowd.mapper.OrderProjectPOMapper;
import com.whpu.crowd.service.api.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderProjectPOMapper orderProjectPOMapper;
	
	@Autowired
	private OrderPOMapper orderPOMapper;
	
	@Autowired
	private AddressPOMapper addressPOMapper;

	@Override
	public OrderProjectVO getOrderProjectVO(Integer projectId, Integer returnId) {
		return orderProjectPOMapper.selectOrderProjectVO(returnId).get(0);
	}

	@Override
	public List<AddressVO> getAddressVOList(Integer memberId) {
		
		AddressPOExample example = new AddressPOExample();
		
		example.createCriteria().andMemberIdEqualTo(memberId);
		
		List<AddressPO> addressPOList = addressPOMapper.selectByExample(example);
		
		List<AddressVO> addressVOList = new ArrayList<AddressVO>();
		
		for (AddressPO addressPO : addressPOList) {
			AddressVO addressVO = new AddressVO();
			BeanUtils.copyProperties(addressPO, addressVO);
			
			addressVOList.add(addressVO);
		}
		
		return addressVOList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	@Override
	public void saveAddress(AddressVO addressVO) {
		
		AddressPO addressPO = new AddressPO();
		
		BeanUtils.copyProperties(addressVO, addressPO);
		
		addressPOMapper.insert(addressPO);
		
	}

}
