package com.gwg.user.web.controller;

import javax.servlet.http.HttpSession;

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
@RequestMapping("/api/user/login")
public class UserLoginController extends BaseController{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserLoginController.class);

	/**
	 * 
	 */
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody Result getInfo(HttpSession session) {
		LOG.info("getInfo 获取用户登录信息..., sessionId:{}", session.getId());
        Object object = session.getAttribute("loginer");
        if(object == null){
        	return new Result(false, "未登录", null, "500");
        }
        return new Result(true, null , object, "200");
    }

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody Result login(HttpSession session, @RequestBody UserDto userDto) {
		LOG.info("login 用户登录...,sessionId:{}", session.getId());

        UserDto entity = userDB.get(userDto.getAccount());
        if (entity != null) {
            session.setAttribute("loginer", entity);
            LOG.info("login 用户登录成功");
            return new Result(true, "登录成功", null, "200");
        }
        LOG.info("login 用户登录失败");
        return new Result(false, "登录失败，找不到用户名", null, "500");
    }

	
	

}
