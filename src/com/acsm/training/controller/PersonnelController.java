package com.acsm.training.controller;

import com.acsm.training.enums.UserType;
import com.acsm.training.model.Base;
import com.acsm.training.model.PersonnelInfo;
import com.acsm.training.model.TecentAreaInfo;
import com.acsm.training.model.UserInfo;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.service.BasesService;
import com.acsm.training.service.TecentAreaInfoService;
import com.acsm.training.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RequestMapping("personnelController")
@Controller("personnelController")
public class PersonnelController extends BaseController{
	
	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private TecentAreaInfoService tecentAreaInfoService;

	@Autowired
	private BasesService basesService;

	@RequestMapping(value="/addInit")
	public String addInitPersonnel(HttpServletRequest request, HttpServletResponse response){
		List<TecentAreaInfo> areaInfoList = tecentAreaInfoService.queryProvinceList();
		Integer userType = null != request.getParameter("userType") ? Integer.valueOf(request.getParameter("userType")) : UserType.PROVINCEADMIN.CODE;
		Integer userBaseId = super.getUser(request).getBaseId();

		if(null != userBaseId){
			request.setAttribute("userBaseId",userBaseId);
			List<Base> baseList = basesService.queryListByProvinceArea(userBaseId);
			request.setAttribute("baseList",baseList);
		}else{
			List<Base> baseList = basesService.queryListByProvinceArea(110000);
			request.setAttribute("baseList",baseList);
		}


		request.setAttribute("provincialList",areaInfoList);
		request.setAttribute("userType",userType);
		return "provincialPersonnelAdd";
	}

	@RequestMapping(value="/updatePersonnelInit")
	public String updatePersonnelInit(HttpServletRequest request, HttpServletResponse response){
		List<TecentAreaInfo> areaInfoList = tecentAreaInfoService.queryProvinceList();
		request.setAttribute("provincialList",areaInfoList);
		Integer userInfoId = null != request.getParameter("Id") ? Integer.valueOf(request.getParameter("Id")) : null;
		Integer userType = null != request.getParameter("userType") ? Integer.valueOf(request.getParameter("userType")) : UserType.PROVINCEADMIN.CODE;
		request.setAttribute("userInfoType",super.getUser(request).getUserType());
		if(null != userInfoId){
			UserInfo userInfo =  userInfoService.queryById(userInfoId);
			request.setAttribute("userName",userInfo.getUserName());
			request.setAttribute("areaId",userInfo.getBaseId());
			request.setAttribute("userId",userInfoId);

			Integer userBaseId = userInfo.getBaseId();
			if(null != userBaseId){
				request.setAttribute("userBaseId",userBaseId);
				List<Base> baseList = basesService.queryListByProvinceArea(userBaseId);
				request.setAttribute("baseList",baseList);
			}
//			else{
//				List<Base> baseList = basesService.queryListByProvinceArea(110000);
//				request.setAttribute("baseList",baseList);
//			}
		}
		request.setAttribute("userType",userType);
		return "provincialPersonnelEdit";
	}

	@RequestMapping(value="/personnelView")
	public String personnelView(HttpServletRequest request, HttpServletResponse response){
		List<TecentAreaInfo> areaInfoList = tecentAreaInfoService.queryProvinceList();
		request.setAttribute("provincialList",areaInfoList);
		Integer userInfoId = null != request.getParameter("Id") ? Integer.valueOf(request.getParameter("Id")) : null;
		Integer userType = null != request.getParameter("userType") ? Integer.valueOf(request.getParameter("userType")) : UserType.PROVINCEADMIN.CODE;

		if(null != userInfoId){
			UserInfo userInfo =  userInfoService.queryById(userInfoId);
			if(null != userInfo.getBaseId()){
				TecentAreaInfo tecentAreaInfo = tecentAreaInfoService.queryByAreaId(userInfo.getBaseId());
				request.setAttribute("areaName",tecentAreaInfo.getFullName());
			}
			if(userType == 3){
				Base base = basesService.queryById(userInfo.getBaseInfoId());
				request.setAttribute("baseName",base.getBaseName());
			}

			request.setAttribute("userName",userInfo.getUserName());
		}
		request.setAttribute("userType",userType);
		return "provincialPersonnelView";
	}

	@RequestMapping(value="/addPersonnel", method= RequestMethod.POST)
	@ResponseBody
	public String addPersonnel(HttpServletRequest request, HttpServletResponse response){
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		Integer proviceId = Integer.valueOf(request.getParameter("proviceId"));
		Integer userType = Integer.valueOf(request.getParameter("userType"));

		if(userType != 3){
			userInfoService.addUserInfo(userName,passWord,proviceId,null,userType);
		}else{
			Integer baseListId = Integer.valueOf(request.getParameter("baseListId"));
			userInfoService.addUserInfo(userName,passWord,proviceId,baseListId,userType);
		}
		return "succeed";
	}

	@RequestMapping(value="/updatePersonnel", method= RequestMethod.POST)
	@ResponseBody
	public String updatePersonnel(HttpServletRequest request, HttpServletResponse response){
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		Integer proviceId = Integer.valueOf(request.getParameter("proviceId"));
		Integer userInfoId = null != request.getParameter("userId") ? Integer.valueOf(request.getParameter("userId")) : null;
		Integer userType = Integer.valueOf(request.getParameter("userType"));

		if(userType != 3){
			userInfoService.updateUserInfo(userInfoId,userName,passWord,proviceId,null);
		}else{
			Integer baseListId = Integer.valueOf(request.getParameter("baseListId"));
			userInfoService.updateUserInfo(userInfoId,userName,passWord,proviceId,baseListId);
		}
		return "succeed";
	}

	@RequestMapping(value="/personnelList")
	public String personnelList(HttpServletRequest request, HttpServletResponse response){
		List<TecentAreaInfo> areaInfoList = tecentAreaInfoService.queryProvinceList();
		request.setAttribute("provincialList",areaInfoList);
		String searchUserName = null != request.getParameter("searchUserName") ? request.getParameter("searchUserName") : null;
		Integer areaId = null != request.getParameter("areaId") ? Integer.valueOf(request.getParameter("areaId")) : 110000;
		Integer userType = null != request.getParameter("userType") ? Integer.valueOf(request.getParameter("userType")) : UserType.PROVINCEADMIN.CODE;
		List<PersonnelInfo> personnelList = userInfoService.personnelList(areaId,searchUserName,userType);
		request.setAttribute("userBaseId",super.getUser(request).getBaseId());
		request.setAttribute("personnelList",personnelList);
		request.setAttribute("userType",userType);
		return "provincialPersonnelList";
	}

	@RequestMapping(value="/personnelListSearch", method= RequestMethod.POST)
	@ResponseBody
	public  PageHelper<PersonnelInfo> personnelListSearch(HttpServletRequest request, HttpServletResponse response){
		String searchUserName = null != request.getParameter("searchUserName") ? request.getParameter("searchUserName") : null;
		Integer areaId = null != request.getParameter("proviceId") ? Integer.valueOf(request.getParameter("proviceId")) : 110000;
		Integer userType = null != request.getParameter("userType") ? Integer.valueOf(request.getParameter("userType")) : UserType.PROVINCEADMIN.CODE;
		List<PersonnelInfo> personnelList = userInfoService.personnelList(areaId,searchUserName,userType);
		PageHelper pageHelper = new PageHelper();
		pageHelper.setList(personnelList);
		return pageHelper;
	}

	@RequestMapping(value="/baseListChange", method= RequestMethod.POST)
	@ResponseBody
	public  PageHelper<Base> baseListChange(HttpServletRequest request, HttpServletResponse response){
		Integer areaId = null != request.getParameter("proviceId") ? Integer.valueOf(request.getParameter("proviceId")) : 110000;
		List<Base> baseList = basesService.queryListByProvinceArea(areaId);
		PageHelper pageHelper = new PageHelper();
		pageHelper.setList(baseList);
		return pageHelper;
	}
}
