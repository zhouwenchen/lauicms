package org.zwc.cms.service;


import java.util.List;

import org.zwc.cms.bean.User;


public interface UserService {
    
    /**
     * 根据接口查询所用的用户
     */
    public List<User> findAllUser();

    /**
     * 根据用户的id，查询用户的信息
     * @param i
     * @return
     */
	public User getUserInfoByUserId(int i);
}