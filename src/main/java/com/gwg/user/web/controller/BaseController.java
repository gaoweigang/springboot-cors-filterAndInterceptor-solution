package com.gwg.user.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gwg.user.dto.UserDto;


public class BaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

	/**
	 * 在这里为了简单起见，内存数据库
	 */
	protected static Map<String, UserDto> userDB = new HashMap<String, UserDto>(); 
	
	static {
		UserDto userOne = new UserDto();
		userOne.setAccount("13817191469");
		userOne.setPassword("111111");
		UserDto userTwo = new UserDto();
		userTwo.setAccount("admin");
		userTwo.setPassword("123456");
		userDB.put("13817191469", userOne);
		userDB.put("admin", userTwo);
	}

}
