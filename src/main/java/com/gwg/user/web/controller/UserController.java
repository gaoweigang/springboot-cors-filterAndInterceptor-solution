package com.gwg.user.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gwg.user.common.Result;
import com.gwg.user.dto.UserDto;

/**
 * 自Spring4.3开始推荐使用注解来解决一个类中多个方法
 */
@Controller
@RequestMapping("/api/users")
public class UserController extends BaseController{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	/**
	 * 
	 */
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Result getList(){
		LOG.info("getList 获取用户信息列表...");
		return new Result(true, "获取用户列表成功", userDB.values(), "200");
	}
	
	@CrossOrigin(origins = "http://localhost:3003")
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody Result add(@RequestBody UserDto userDto) {		
		LOG.info("add 添加用户信息...");

        if (userDB.get(userDto.getAccount()) != null) {
        	LOG.info("add 添加用户信息失败");
            return new Result(false, "添加失败, 用户名" + userDto.getAccount() + "已存在", null, "500");
        }
        //为新增账号设置默认密码
        userDto.setPassword("111111");
        userDB.put(userDto.getAccount(), userDto);
        LOG.info("add 添加用户信息成功");
        return new Result(true, "添加成功", userDB.values(), "200");
    }
	
	

}
