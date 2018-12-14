package org.zwc.cms.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.zwc.cms.bean.ResponseResult;
import org.zwc.cms.bean.User;
import org.zwc.cms.service.UserService;


@RestController/**自动返回的是json格式数据***/
public class UserController {
    
    @Autowired
    private UserService userService;

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}
    
    @RequestMapping("list")
    public List<User> list(){
        List<User> list = userService.findAllUser();
        return list;
    }
    
    /**
     * 获取用户的详细信息
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