<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="menuName" value="${pageContext.request.getParameter('menuName')}"/>
<c:set var="group" value="${pageContext.request.getParameter('group')}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>leftnav</title>
    <link rel="stylesheet" href="${ctx}/resources/css/reset.css">
    <link rel="stylesheet" href="${ctx}/resources/css/nav.css">
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
                    <div class="final">添加课程</div>
                    <div class="final active">课程列表</div>
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
</div>
<script src="${ctx}/resources/js/jquery-1.11.3.min.js"></script>
<script src="${ctx}/resources/js/nav.js"></script>
</body>
</html>