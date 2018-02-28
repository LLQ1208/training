<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false"%>
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
    <link rel="stylesheet" href="${ctx}/resources/css/reset.css">
    <link rel="stylesheet" href="${ctx}/resources/css/bootstrap3.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/bootstrap-select.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/nav.css">
    <link rel="stylesheet" href="${ctx}/resources/css/editor.css">
</head>
<body>
<div class="box clearfix">
    <div class="nav fl clearfix">
        <jsp:include page="../menu.jsp" flush="false" >
            <jsp:param value="classList" name="menuName"/>
            <jsp:param value="2" name="group"/>
        </jsp:include>
    </div>

    <div class="container-box">
        <div class="table">
            <div class="left fl">
                <div></div>
                <div>上<br/>午</div>
                <div>下<br/>午</div>
            </div>
            <div class="right fl">
                <div class="rightCont fl">
                    <c:forEach items="${courseModelList}" var="courseModel" >
                        <div class="rightCommon fl">
                            <div><fmt:formatDate value="${courseModel.date}" pattern="MM月dd日"></fmt:formatDate></div>
                            <div>
                                <c:if test="${courseModel.courseAM != null && courseModel.courseAM.id != null }">
                                    ${courseModel.courseAM.courseName}<br/>-${courseModel.courseAM.teacher}
                                </c:if>
                            </div>
                            <div>
                                <c:if test="${courseModel.coursePM != null && courseModel.coursePM.id != null }">
                                    ${courseModel.coursePM.courseName}<br/>-${courseModel.coursePM.teacher}
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="editorBtn">
            <div class="btnone" id="backButton">返回</div>
            <div class="btnone" id="editButton" style="margin-right: 26px;">编辑</div>
            <div class="btnone" id="deletedButton">删除</div>

        </div>
    </div>
</div>
<input type="hidden" id="classScheduleId" value="${classScheduleId}">
<input type="hidden" id="ctx" value="${ctx}">
<script src="${ctx}/resources/js/jquery-1.11.3.min.js"></script>
<script src="${ctx}/resources/js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/bootstrap-select.min.js"></script>
<script src="${ctx}/resources/js/editor.js"></script>
</body>
</html>