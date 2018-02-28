<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="stylesheet" href="${ctx}/resources/css/login.css">
	<script src="${ctx}/resources/js/jquery-1.11.3.min.js"></script>
	<title>政府在线问卷调查系统</title>
</head>
<body>

<div id="login" class="clearfix">
	<!--first login page start-->
	<div class="login">
		<div class="top">
			<div class="logo">政府在线问卷调查系统</div>
		</div>
		<div class="bottom clearfix">
			<div class="user">
				<span class="userIcon fl"></span>
				<input type="text" id="userName" class="userName fl" placeholder="输入用户名">
			</div>
			<div class="passWord">
				<span class="passWordIcon fl"></span>
				<input type="password" id="password" class="words fl" placeholder="输入登录密码">
			</div>
			<div id="showMesg" class="login-mesg"></div>
			<button class="loginBtn" id="loginBtn" >登录</button>
		</div>
	</div>

</div>
<input type="hidden" id="ctx" value="${ctx}"/>
<%--<script type="text/javascript" src="${ctx}/resources/js/jquery-1.11.3.min.js"></script>--%>
<script type="text/javascript" src="${ctx}/resources/js/login.js"></script>
<script type="text/javascript">
	$('#login').css({'height':$(window).height()})
</script>
</body>
</html>