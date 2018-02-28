<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>leftnav</title>
	<link rel="stylesheet" href="../../../resources/css/reset.css">
	<link rel="stylesheet" href="../../../resources/css/nav.css">
</head>
<body>
<div class="box clearfix">
	<div class="nav fl clearfix">
		<div class="user clearfix">
			<div class="usericon">
			</div>
			<span>总管理员</span>
		</div>
		<div class="list">
			<div class="listCommon">
				<div class="firstCommon">
					<span>权限管理</span>
					<span class="point"></span>
				</div>
				<div class="secondCommon">
					<div class="final" >省级管理员</div>
					<div class="final active">基地管理员</div>
				</div>
			</div>
			<div class="listCommon">
				<div class="firstCommon">
					<span>权限管理</span>
					<span class="point"></span>
				</div>
				<div class="secondCommon">
					<div class="final">添加课程</div>
					<div class="final">课程列表</div>
				</div>
			</div>
			<div class="listCommon">
				<div class="firstCommon">
					<span>权限管理</span>
					<span class="point"></span>
				</div>
				<div class="secondCommon">
					<div class="final">添加课程</div>
					<div class="final">课程列表</div>
				</div>
			</div>
			<div class="listCommon">
				<div class="firstCommon">
					<span>权限管理</span>
					<span class="point"></span>
				</div>
				<div class="secondCommon">
					<div class="final">添加课程</div>
					<div class="final">课程列表</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="ctx" value="${ctx}"/>
</div>

<script src="../../../resources/js/jquery-1.11.3.min.js"></script>
<script src="../../../resources/js/nav.js"></script>
</body>
</html>