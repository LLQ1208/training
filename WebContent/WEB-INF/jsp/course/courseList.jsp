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
    <link rel="stylesheet" href="${ctx}/resources/css/backstage.css">
    <link rel="stylesheet" href="${ctx}/resources/css/myPage.css">
</head>
<body>
<div class="box clearfix">
    <div class="nav fl clearfix" style="width: 151px;">
        <jsp:include page="../menu.jsp" flush="false" >
            <jsp:param value="classList" name="menuName"/>
            <jsp:param value="2" name="group"/>
        </jsp:include>
    </div>

    <div class="container-box">
        <div class="searchlist">
            <div class="searchone">
                <span></span>
                <input type="text" placeholder="按地区、班次搜索" id="searchKey">
            </div>
            <div class="searchbtn" style="cursor: pointer;">搜索</div>
            <div class="direct">地区</div>
            <select name="" id="province" class="directChange">
                <c:forEach items="${provinceList}" var="province">
                    <c:choose>
                        <c:when test="${provinceAreaId == province.areaId}">
                            <option value="${province.areaId}" selected>${province.name}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${province.areaId}">${province.name}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
        <div class="table" id="tableList">
            <div class="tableTitle">
                <div>序号</div>
                <div>地区</div>
                <div>班次</div>
                <div>基地管理员</div>
                <div>班次人数</div>
                <div>课节数</div>
                <div>详情</div>
                <div>二维码</div>
            </div>
            <c:forEach items="${pageHelper.list}" var="classModel" varStatus="index">
                <div class="tabCommon">
                    <div>${index.index + 1}</div>
                    <div>${classModel.area}</div>
                    <div>${classModel.className}</div>
                    <div>${classModel.baseAdmin}</div>
                    <div>${classModel.classPersonNum}</div>
                    <div>${classModel.courseNum}</div>
                    <div class="classDetail" href="${ctx}/course/courseDetail?id=${classModel.classScheduleId}">点击查看详情</div>
                    <div class="qrCodedetail" classScheduleId="${classModel.classScheduleId}">
                        <span></span>
                        <span>下载</span>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="totle">
            <div class="fr">
                <span>课程：</span>
                <span id="courseNum">${pageHelper.tempParam1}</span>
                <span>节</span>
            </div>
            <div class="fr">
                <span>参与人数：</span>
                <span id="studentNum">${pageHelper.tempParam2}</span>
                <span>人</span>
            </div>
            <div class="fr">
                <span>共计：</span>
                <span id="classNum">${pageHelper.totalRow}</span>
                <span>班次</span>
            </div>
        </div>
        <div class="page" style="text-align: center;padding-top: 60px; margin: 0 auto;">
            <ul class="pagination" id="pagination">
            </ul>
            <input type="hidden" id="PageCount" runat="server" value="${pageHelper.totalRow}"/>
            <input type="hidden" id="PageSize" runat="server" value="10"/>
            <input type="hidden" id="countindex" runat="server" value="10"/>
            <!--设置最多显示的页码数 可以手动设置 默认为7-->
            <input type="hidden" id="visiblePages" runat="server" value="5"/>
        </div>
    </div>
</div>
<input type="hidden" value="${ctx}" id="ctx"/>
<script src="${ctx}/resources/js/jquery-1.11.3.min.js"></script>
<script src="${ctx}/resources/js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/bootstrap-select.min.js"></script>
<script src="${ctx}/resources/js/backstage.js"></script>
<script src="${ctx}/resources/js/jqPaginator.min.js"></script>
</body>
</html>