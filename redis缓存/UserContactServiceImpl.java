package com.xianjinxia.bigdata.personas.serviceImpl;

import java.util.List;
import java.util.Map;

import com.xianjinxia.bigdata.personas.entity.UserUploadLog;
import com.xianjinxia.bigdata.personas.readmapper.UserContactIntMapper;
import com.xianjinxia.bigdata.personas.writemapper.UserContactTrdCountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xianjinxia.bigdata.personas.controller.UserContactRestApiController;
import com.xianjinxia.bigdata.personas.entity.ContactTransactioninfo;
import com.xianjinxia.bigdata.personas.entity.NewContacts;

import com.xianjinxia.bigdata.personas.service.UserContactService;

@Service
@CacheConfig(cacheNames = "userLog")
public class UserContactServiceImpl implements UserContactService {
	private static final Logger logger = LoggerFactory.getLogger(UserContactServiceImpl.class);
	@Autowired
	UserContactTrdCountMapper writeMapper;

	@Autowired
	UserContactIntMapper readMapper;

	@Override
	public Map<String, Object> selectUserContactTrdCount(Integer userId) {
		return readMapper.selectUserContactTrdCount(userId);
	}

	@Cacheable(key="#p0")
	@Override
	public List<UserUploadLog> selectUserLog(Integer userId) {
		if (userId % 2 == 0) {
			logger.info("查询数据success,userId:{}", userId);
			return readMapper.selectBothUserLog(userId);
		} else {
			logger.info("查询数据success,userId:{}", userId);
			return readMapper.selectSingleUserLog(userId);
		}
	}

	 @Transactional
	@Override
	public void insertUserContact(NewContacts newContacts) {
		// TODO Auto-generated method stub
			ContactTransactioninfo contact = readMapper.selectUserContact(newContacts);
			if (contact != null) {
				contact.setUserId(newContacts.getUserId());
					// 如果存在就删除更新，不存在就插入repalce into
				Integer count=writeMapper.insertContact(contact);
				if (count>0){
					logger.info("更新userId成功,userId:{}", newContacts.getUserId());
				}else {
					logger.error("插入用户聚合信息时有问题,userId:{}", newContacts.getUserId());
				}
			}


	}

	@Transactional
	@Override
	public Integer insertUserLoadLog(UserUploadLog userUploadLog) {
		Integer count;
			if (userUploadLog.getUserId()%2==0){
				count=  writeMapper.insertBothUserLoadLog(userUploadLog);
				logger.info("插入数据success,userId:{}", userUploadLog.getUserId());
				return count;
			}else {
				count= writeMapper.insertSingleUserLoadLog(userUploadLog);
				logger.info("插入数据success,userId:{}", userUploadLog.getUserId());
				return  count;
			}
	}


}
