package org.zwc.cms.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zwc.cms.bean.User;
import org.zwc.cms.mapper.UserMapper;
import org.zwc.cms.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    
    public List<User> findAllUser() {
        List<User> list = userMapper.findAll();
        return list;
    }

	public User getUserInfoByUserId(int id) {
		return userMapper.getUserInfoByUserId(id);
	}
}