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
    <title>课程后台</title>
    <link rel="stylesheet" href="../../resources/css/reset.css">
    <link rel="stylesheet" href="../../resources/css/bootstrap3.min.css">
    <link rel="stylesheet" href="../../resources/css/bootstrap-select.min.css">
    <link rel="stylesheet" href="../../resources/css/nav.css">
    <link rel="stylesheet" href="../../resources/css/personnel/addadmin.css">
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
            <div></div>
        </div>
    </div>

    <div class="container-box">
        <div class="list-block">
            <div class="common">
                <span class="common-title">地区：</span>
                <select name="" class="choose" id="proviceId">
                    <c:forEach items="${provincialList}" var="provice">
                        <option value="${provice.areaId}">${provice.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="common">
                <span class="common-title">管理员：</span>
                <select name="" class="choose">
                    <option value="">省级管理员</option>
                </select>
            </div>
            <div class="common">
                <span class="common-title">设置账号：</span>
                <input type="text" id="userName">
            </div>
            <div class="common">
                <span class="common-title">登录密码：</span>
                <input type="text" id="passWord">
            </div>
            <div class="submit-btn">确定添加</div>
        </div>
    </div>
    <input type="hidden" id="ctx" value="${ctx}"/>
</div>

<script src="../../resources/js/jquery-1.11.3.min.js"></script>
<script src="../../resources/js/bootstrap.min.js"></script>
<script src="../../resources/js/bootstrap-select.min.js"></script>
<script src="../../resources/js/personnel/provincialPersonnelAdd.js"></script>
</body>
</html>