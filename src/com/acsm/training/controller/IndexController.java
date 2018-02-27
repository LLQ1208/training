package com.acsm.training.controller;


import com.acsm.training.model.CommonResult;
import com.acsm.training.service.UserInfoService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@RequestMapping("")
@Controller("indexController")
public class IndexController extends BaseController{
	
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * 登录
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/login",method= RequestMethod.POST)
	@ResponseBody
	public String login(HttpSession session,HttpServletRequest request){
		String userName = ServletRequestUtils.getStringParameter(request, "userName", "");
		String password = ServletRequestUtils.getStringParameter(request, "password", "");
		CommonResult result = userInfoService.login(session, userName, password);
		return JSON.toJSONString(result);
	}


	@RequestMapping(value="/index")
	public String toInformation(HttpServletRequest request, HttpServletResponse response){
		return "course/courseAdd";
	}
}
