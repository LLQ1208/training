<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/reset.css">
	<link rel="stylesheet" href="${ctx}/resources/css/login.css">
</head>
<body>
	<div class="banner">
		<div align="center">
			<span style="color:#fff;font-size:120px;font-weight:bold;">403</span>
		</div>
		<div align="center" style="padding-top:100px;font-size:40px;color:#fff;">很遗憾!您没有访问此URL的权限。</div>	
		<div align="center" style="padding-top:50px;font-size:20px;color:#fff;"><span id="time">5</span> 秒后返回上一页面。</div>
		<script>
			window.onload=function(){		
				setInterval(function(){
					var time=document.getElementById('time').innerHTML;
					if(time==1)
					{
						window.history.back();
						return;
					}
					document.getElementById('time').innerHTML=time-1;
					
				},1000);
			};		
		</script>
	</div>
</body>
</html>