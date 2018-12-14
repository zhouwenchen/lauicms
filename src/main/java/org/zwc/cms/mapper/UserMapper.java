package org.zwc.cms.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.zwc.cms.bean.User;

@Mapper
public interface UserMapper {
    
    public List<User> findAll();

	public User getUserInfoByUserId(int id);
}