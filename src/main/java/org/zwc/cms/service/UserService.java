package org.zwc.cms.service;


import java.util.List;

import org.zwc.cms.bean.User;


public interface UserService {
    
    /**
     */
    public List<User> findAllUser();

    /**
     * @param i
     * @return
     */
	public User getUserInfoByUserId(int i);
}