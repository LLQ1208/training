package com.acsm.training.controller;/**
 * Created by lq on 2018/2/26.
 */

import com.acsm.training.dao.BasesDao;
import com.acsm.training.enums.UserType;
import com.acsm.training.manage.AreaManage;
import com.acsm.training.model.Base;
import com.acsm.training.model.CourseSchedule;
import com.acsm.training.model.TecentAreaInfo;
import com.acsm.training.model.UserInfo;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.model.page.ClassModel;
import com.acsm.training.model.page.CourseModel;
import com.acsm.training.service.BasesService;
import com.acsm.training.service.CourseService;
import com.acsm.training.service.TecentAreaInfoService;
import com.acsm.training.util.DateUtil;
import com.acsm.training.util.QRCodeUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author lianglinqiang
 * @create 2018-02-26
 */
@Controller("courseController")
@RequestMapping("course")
public class CourseController extends BaseController{
    @Autowired
    TecentAreaInfoService tecentAreaInfoService;
    @Autowired
    CourseService courseService;
    @Autowired
    BasesService basesService;

    private static final int PAGE_SIZE = 10;

    private static final String URL = "http://192.168.202.1:8080/training/";
    /**
     * 跳转添加
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/courseAdd")
    public String courseAdd(HttpServletRequest request, HttpServletResponse response){
        //默认展示一周
        String beginTime = DateUtil.format(new Date(), "yyyy/MM/dd");
        String endTime = DateUtil.format(DateUtil.getDate(new Date(), 6), "yyyy/MM/dd");
        request.setAttribute("beginTime",beginTime);
        request.setAttribute("endTime",endTime);
        request.setAttribute("proviceList",AreaManage.getProviceList());
        request.setAttribute("courseList",courseService.queryAddCourseList());
        return "course/courseAdd";
    }

    /**
     * 省-市联动
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/getCityList",method= RequestMethod.GET)
    @ResponseBody
    public String getCityList(HttpServletRequest request,HttpServletResponse response){
        Integer provinceId = ServletRequestUtils.getIntParameter(request,"provinceId",0);
        Map<Integer,List<TecentAreaInfo>> cityMap = AreaManage.getCityMap();
        List<TecentAreaInfo> cityList = cityMap.get(provinceId);
        return new Gson().toJson(cityList);
    }

    /**
     * 市-县区联动
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/getAreaList",method= RequestMethod.GET)
    @ResponseBody
    public String getAreaList(HttpServletRequest request,HttpServletResponse response){
        Integer cityId = ServletRequestUtils.getIntParameter(request,"cityId",0);
        Map<Integer,List<TecentAreaInfo>> areaMap = AreaManage.getAreaMap();
        List<TecentAreaInfo> areaList = areaMap.get(cityId);
        return new Gson().toJson(areaList);
    }

    /**
     * 根据县区获取基地
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/getBaseList",method= RequestMethod.GET)
    @ResponseBody
    public String getBaseList(HttpServletRequest request,HttpServletResponse response){
        Integer provinceAreaId = ServletRequestUtils.getIntParameter(request, "provinceAreaId", 0);
        Integer cityAreaId = ServletRequestUtils.getIntParameter(request,"cityAreaId",0);
        Integer countyAreaId = ServletRequestUtils.getIntParameter(request,"countyAreaId",0);
        List<Base> baseList = basesService.queryBaseList(provinceAreaId, cityAreaId, countyAreaId);
        return new Gson().toJson(baseList);
    }

    /**
     * 保存课程
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    @ResponseBody
    public String save(HttpServletRequest request,HttpServletResponse response){
        UserInfo user = getUser(request);
        Integer provinceAreaId = ServletRequestUtils.getIntParameter(request, "provinceAreaId", 0);
        Integer cityAreaId = ServletRequestUtils.getIntParameter(request,"cityAreaId",0);
        Integer countyAreaId = ServletRequestUtils.getIntParameter(request, "countyAreaId", 0);
        Integer baseId = ServletRequestUtils.getIntParameter(request, "baseId", 0);
        String beginDate = ServletRequestUtils.getStringParameter(request,"beginDate",null);
        String endDate = ServletRequestUtils.getStringParameter(request,"endDate",null);
        String className = ServletRequestUtils.getStringParameter(request,"className",null);
        String courseArr = ServletRequestUtils.getStringParameter(request, "courseArr", null);
        JSONArray jsonArray = JSONArray.parseArray(courseArr);
        System.out.println(jsonArray.toString());
        courseService.saveCourse(provinceAreaId,cityAreaId,countyAreaId,baseId,beginDate,endDate,
                                    className,jsonArray,user);
        JSONObject json = new JSONObject();
        json.put("status", "success");
        return json.toString();
    }

    /**
     * 课程列表页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/courseList")
    public String courseList(HttpServletRequest request, HttpServletResponse response){
        UserInfo user = getUser(request);
        List<TecentAreaInfo> provinceList = new ArrayList<>();
        provinceList.addAll(AreaManage.getProviceList());
        Integer provinceAreaId = null;
        if(user.getUserType() == UserType.ADMIN.getCODE()){
            TecentAreaInfo tecentAreaInfo = new TecentAreaInfo();
            tecentAreaInfo.setAreaId(0);
            tecentAreaInfo.setName("全部");
            provinceList.add(0,tecentAreaInfo);
        }else{
            provinceAreaId = user.getBase().getProvinceAreaId();
        }
        PageHelper<ClassModel> pageHelper = courseService.getClassList("", provinceAreaId, user, PAGE_SIZE, 1);
        request.setAttribute("provinceAreaId",provinceAreaId);
        request.setAttribute("provinceList",provinceList);
        request.setAttribute("user",user);
        request.setAttribute("pageHelper", pageHelper);
        return "course/courseList";
    }

    /**
     * 分页查询
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/searchByPage",method= RequestMethod.POST)
    @ResponseBody
    public String searchByPage(HttpServletRequest request,HttpServletResponse response){
        UserInfo user = getUser(request);
        String searchKey = ServletRequestUtils.getStringParameter(request, "searchKey", null);
        Integer provinceAreaId = ServletRequestUtils.getIntParameter(request,"provinceAreaId",0);
        Integer page = ServletRequestUtils.getIntParameter(request,"page",1);
        PageHelper<ClassModel> pageHelper = courseService.getClassList(searchKey, provinceAreaId, user, PAGE_SIZE, page);
        return new Gson().toJson(pageHelper);
    }


    /**
     * 课程详情
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/courseDetail")
    public String courseDetail(HttpServletRequest request, HttpServletResponse response){
        Integer classScheduleId = Integer.valueOf(request.getParameter("id"));
        List<CourseModel> courseModelList = courseService.queryCourseDetail(classScheduleId);
        request.setAttribute("classScheduleId",classScheduleId);
        request.setAttribute("courseModelList",courseModelList);
        return "course/courseDetail";
    }

    /**
     *删除班次
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/deleteClass",method= RequestMethod.POST)
    @ResponseBody
    public String deleteClass(HttpServletRequest request,HttpServletResponse response){
        Integer classScheduleId = ServletRequestUtils.getIntParameter(request, "classScheduleId", 0);
        courseService.deleteClass(classScheduleId);
        JSONObject json = new JSONObject();
        json.put("status", "success");
        return json.toString();
    }

    @RequestMapping(value = "/getTwoDimension",method={RequestMethod.POST,RequestMethod.GET})
    public void getTwoDimensionForIOSs(HttpServletRequest request,HttpServletResponse response){
        Integer classScheduleId = Integer.valueOf(request.getParameter("classScheduleId"));
        QRCodeUtil matrixToImageWriters = new QRCodeUtil();
        try {
            matrixToImageWriters.getTwoDimension(URL + "weChat/evaluate.html?classScheduleId="+classScheduleId, response, 200, 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转添编辑
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/courseEdit")
    public String courseEdit(HttpServletRequest request, HttpServletResponse response){
        Integer classScheduleId = Integer.valueOf(request.getParameter("id"));
        CourseSchedule courseSchedule = courseService.queryCourseScheduleById(classScheduleId);
        List<CourseModel> courseModelList = courseService.queryCourseDetail(classScheduleId);
        List<Base> baseList = basesService.queryBaseList(courseSchedule.getBase().getProvinceAreaId(),
                courseSchedule.getBase().getCityAreaId(), courseSchedule.getBase().getCountyAreaId());
        request.setAttribute("provinceList", AreaManage.getProviceList());
        request.setAttribute("cityList",AreaManage.getCityMap().get(courseSchedule.getProvinceAreaId()));
        request.setAttribute("countyList",AreaManage.getAreaMap().get(courseSchedule.getCityAreaId()));
        request.setAttribute("classScheduleId",classScheduleId);
        request.setAttribute("courseModelList",courseModelList);
        request.setAttribute("courseSchedule",courseSchedule);
        request.setAttribute("baseList",baseList);
        return "course/courseEdit";
    }


    /**
     * 时间改变---编辑获取课程列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/editGetCourseList",method= RequestMethod.POST)
    @ResponseBody
    public String editGetCourseList(HttpServletRequest request,HttpServletResponse response){
        UserInfo user = getUser(request);
        String beginTime = ServletRequestUtils.getStringParameter(request, "beginTime", null);
        String endTime = ServletRequestUtils.getStringParameter(request, "endTime", null);
        Integer courseScheduleId = ServletRequestUtils.getIntParameter(request,"courseScheduleId",0);
        List<CourseModel> courseModelList = courseService.queryEditCourseList(courseScheduleId,beginTime,endTime);
        return new Gson().toJson(courseModelList);
    }

    /**
     * 编辑课程
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/edit",method= RequestMethod.POST)
    @ResponseBody
    public String edit(HttpServletRequest request,HttpServletResponse response){
        UserInfo user = getUser(request);
        Integer courseScheduleId = ServletRequestUtils.getIntParameter(request,"courseScheduleId",0);
        Integer provinceAreaId = ServletRequestUtils.getIntParameter(request,"provinceAreaId",0);
        Integer cityAreaId = ServletRequestUtils.getIntParameter(request,"cityAreaId",0);
        Integer countyAreaId = ServletRequestUtils.getIntParameter(request, "countyAreaId", 0);
        Integer baseId = ServletRequestUtils.getIntParameter(request, "baseId", 0);
        String beginDate = ServletRequestUtils.getStringParameter(request,"beginDate",null);
        String endDate = ServletRequestUtils.getStringParameter(request,"endDate",null);
        String className = ServletRequestUtils.getStringParameter(request,"className",null);
        String courseArr = ServletRequestUtils.getStringParameter(request, "courseArr", null);
        JSONArray jsonArray = JSONArray.parseArray(courseArr);
        System.out.println(jsonArray.toString());
        courseService.saveEditCourse(courseScheduleId,provinceAreaId, cityAreaId, countyAreaId, baseId, beginDate, endDate,
                className,jsonArray,user);
        JSONObject json = new JSONObject();
        json.put("status","success");
        return json.toString();
    }

    /**
     * 测试
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value="/debugPrivice",method= RequestMethod.GET)
    @ResponseBody
    public String test(HttpSession session,HttpServletRequest request){
        JSONObject json = new JSONObject();
        json.put("provice", AreaManage.proviceList);
        json.put("city",AreaManage.cityMap);
        json.put("area",AreaManage.areaMap);
        return json.toString();
    }
}
