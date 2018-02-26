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
				||"index".equals(basePath) ){
			return true;
		}
		else{
			UserInfo user =(UserInfo)request.getSession().getAttribute("currentUser");
			if(user!=null){
//				if(user.getUserType()==UserType.COACH.getCode()){
//					if("member".equals(basePath)||"setting".equals(basePath)){
//						response.sendRedirect(request.getContextPath()+"/noPermission");
//						return false;
//					}
//				}else if(user.getUserType()==UserType.SUPPORTER.getCode()){
//					if("courseContent".equals(basePath)||"setting".equals(basePath)){
//						response.sendRedirect(request.getContextPath()+"/noPermission");
//						return false;
//					}
//				}else if(user.getUserType()==UserType.MEMBER.getCode()){
//					if("courseContent".equals(basePath)||"setting".equals(basePath)||"member".equals(basePath)){
//						response.sendRedirect(request.getContextPath()+"/noPermission");
//						return false;
//					}
//				}
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
