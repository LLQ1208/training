package com.acsm.training.controller;/**
 * Created by lq on 2018/2/28.
 */

import com.acsm.training.manage.AreaManage;
import com.acsm.training.model.CourseSchedule;
import com.acsm.training.model.TecentAreaInfo;
import com.acsm.training.model.page.CourseModel;
import com.acsm.training.service.CourseService;
import com.acsm.training.service.WetChatService;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Author lianglinqiang
 * @create 2018-02-28
 */
@Controller("weChatController")
@RequestMapping("weChat")
public class WeChatController {

    @Autowired
    WetChatService wetChatService;
    @Autowired
    CourseService courseService;
    /**
     * 省-市联动
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/getClassDetail",method= RequestMethod.GET)
    @ResponseBody
    public String getClassDetail(HttpServletRequest request,HttpServletResponse response){
        Integer classScheduleId = Integer.valueOf(request.getParameter("classScheduleId"));
        CourseSchedule courseSchedule = courseService.queryCourseScheduleById(classScheduleId);
        List<CourseModel> courseModelList = wetChatService.queryCourseDetail(classScheduleId);
        JSONObject json = new JSONObject();
        json.put("id",classScheduleId);
        json.put("className",courseSchedule.getClassName());
        json.put("list",courseModelList);
        return json.toString();
    }
}
