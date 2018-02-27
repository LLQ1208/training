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
    <link rel="stylesheet" href="../../resources/css/personnel/administration.css">
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
            <div class="searchlist">
                <div class="searchone">
                    <!--<span></span>-->
                    <input type="text" id="searchUserName" placeholder="按姓名搜索">
                </div>
                <div class="searchbtn">搜索</div>
                <div class="direct">地区</div>
                <select name="" id="proviceId" class="directChange" >
                    <c:forEach items="${provincialList}" var="provice">
                        <option value="${provice.areaId}">${provice.name}</option>
                    </c:forEach>
                </select>
                <div class="addbtn">添加</div>
            </div>
            <div class="table">
                <div class="tableTitle">
                    <div>序号</div>
                    <div>地区</div>
                    <div>姓名</div>
                    <div>账号</div>
                    <div>管理员权限</div>
                    <div>详情</div>
                    <div>操作</div>
                </div>
                <div class="tcontainer">
                    <c:forEach items="${personnelList}" var="provice" varStatus="status">
                        <div class="tabCommon">
                            <div>${ status.index + 1}</div>
                            <div>${provice.areaName}</div>
                            <div>${provice.userName}</div>
                            <div>${provice.userName}</div>
                            <div>${provice.userTypeName}</div>
                            <input type="hidden" id="personnelId" value="${provice.id}">
                            <div><p class="tocheck">点击查看详情</p></div>
                            <div class="editPersonnel">编辑</div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <input type="hidden" id="ctx" value="${ctx}"/>
    </div>
<script src="../../resources/js/jquery-1.11.3.min.js"></script>
<script src="../../resources/js/bootstrap.min.js"></script>
<script src="../../resources/js/bootstrap-select.min.js"></script>
<script src="../../resources/js/personnel/provincialPersonnelList.js"></script>
</body>
</html>