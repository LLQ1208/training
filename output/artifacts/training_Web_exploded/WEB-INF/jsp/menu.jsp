<%@ page import="com.acsm.training.model.UserInfo" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="menuName" value="${pageContext.request.getParameter('menuName')}"/>
<c:set var="group" value="${pageContext.request.getParameter('group')}"/>
<%
    UserInfo user = (UserInfo)request.getSession().getAttribute("currentUser");
    request.setAttribute("userType",user.getUserType());
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>leftnav</title>
    <link rel="stylesheet" href="${ctx}/resources/css/reset.css">
    <link rel="stylesheet" href="${ctx}/resources/css/nav.css">
</head>
<body>
<div class="clearfix">
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
                <div class="secondCommon" style="${group == 1 ? 'display:block':'display:none'}">
                    <%--<div class="final">总管理员</div>--%>
                    <div class="final ${menuName == 'personnelAdd' ? 'active':''}" href="${ctx}/personnelController/personnelList?userType=2">省级管理员</div>
                    <div class="final ${menuName == 'baseAdd' ? 'active':''}" href="${ctx}/personnelController/personnelList?userType=3">基地管理员</div>
                </div>
            </div>
            <div class="listCommon">
                <div class="firstCommon">
                    <span>课程管理</span>
                    <span class="point  ${group == 2 ? 'active':''}"></span>
                </div>
                <div class="secondCommon" style="${group == 2 ? 'display:block':'display:none'}">
                    <c:if test="${userType == 3}">
                        <div class="final ${menuName == 'courseAdd' ? 'active':''}" href="${ctx}/course/courseAdd">添加课程</div>
                    </c:if>
                    <div class="final ${menuName == 'classList' ? 'active':''}" href="${ctx}/course/courseList">课程列表</div>
                </div>
            </div>
            <div class="listCommon">
                <div class="firstCommon">
                    <span>评测管理</span>
                    <span class="point"></span>
                </div>
                <div class="secondCommon">
                    <div class="final">1</div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/resources/js/jquery-1.11.3.min.js"></script>
<script src="${ctx}/resources/js/nav.js"></script>
</body>
</html>