package com.acsm.training.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acsm.training.model.UserInfo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;





public class AuthenticationInterceptor implements HandlerInterceptor{
	
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object object, Exception exception)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,Object object, ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object object) throws Exception {
		String uri = request.getRequestURI().toString();
		String contextPath = request.getContextPath();
		String path = uri.replaceFirst(contextPath, "");
		String xrw = request.getHeader("X-Requested-With");
		String basePath = path.split("/")[1];
		if("interface".equals(basePath) ||"login".equals(basePath) ||"logout".equals(basePath)
				||"index".equals(basePath) || "weChat".equals(basePath)){
			return true;
		}
		else{
			UserInfo user =(UserInfo)request.getSession().getAttribute("currentUser");
			if(user!=null){
				return true;
			}else if("XMLHttpRequest".equalsIgnoreCase(xrw)&&user==null){
				response.setHeader("sessionstatus", "timeout");
				return false;
			}else{
				request.getRequestDispatcher("/").forward(request, response);
				return false;
			}
		}
	}
}
