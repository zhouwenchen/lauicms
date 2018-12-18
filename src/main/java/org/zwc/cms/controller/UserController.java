package org.zwc.cms.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zwc.cms.bean.NewsInfo;
import org.zwc.cms.bean.User;
import org.zwc.cms.service.UserService;

/**
 * 返回json数据
* @author zhouwenchen
* @date 2018年12月18日 下午3:56:32
 */
@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    @RequestMapping("list")
    public List<User> list(){
        List<User> list = userService.findAllUser();
        return list;
    }
    
    /**
     * 根据id，查询用户的信息
     * @return
     */
    @RequestMapping("/getUserInfo/{id}")
    public Map<String,Object> getUserInfo(@PathVariable("id")Integer id){
    	User user = userService.getUserInfoByUserId(id);
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("data", user);
    	map.put("status", "success");
    	return map;
    }
}