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
    <title>add</title>
    <link rel="stylesheet" href="${ctx}/resources/css/reset.css">
    <link rel="stylesheet" href="${ctx}/resources/css/bootstrap3.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/bootstrap-select.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/bootstrap-datetimepicker.css">
    <link rel="stylesheet" href="${ctx}/resources/css/nav.css">
    <link rel="stylesheet" href="${ctx}/resources/css/add.css">
</head>
<body>
<div class="box clearfix">
    <!--左侧导航占位-->
    <div class="nav fl clearfix" style="width: 151px;">
        <jsp:include page="../menu.jsp" flush="false" >
            <jsp:param value="courseAdd" name="menuName"/>
            <jsp:param value="2" name="group"/>
        </jsp:include>
    </div>

    <div class="container-box">
        <div class="top">
            <p>所在地</p>
            <select name="s_province" class="location" id="s_province" style="cursor: pointer;width: 70px">
                <option value="">省</option>
                <c:forEach items="${provinceList}" var="province">
                    <c:choose>
                        <c:when test="${courseSchedule.base.provinceAreaId == province.areaId}">
                            <option value="${province.areaId}" selected>${province.name}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${province.areaId}">${province.name}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <select name="s_city" class="location" id="s_city" style="cursor: pointer;width: 70px">
                <option value="">市</option>
                <c:forEach items="${cityList}" var="city">
                    <c:choose>
                        <c:when test="${courseSchedule.base.cityAreaId == city.areaId}">
                            <option value="${city.areaId}" selected>${city.name}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${city.areaId}">${city.name}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <select name="s_county" class="location" id="s_county" style="cursor: pointer;width: 70px">
                <option value="">县</option>
                <c:forEach items="${countyList}" var="county">
                    <c:choose>
                        <c:when test="${courseSchedule.base.countyAreaId == county.areaId}">
                            <option value="${courseSchedule.countyAreaId}" selected>${county.name}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${county.areaId}">${county.name}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <p>基地</p>
            <select name="base" class="location" id="base" style="cursor: pointer;width: 140px">
                <option value="">请选择</option>
                <c:forEach items="${baseList}" var="base">
                    <c:choose>
                        <c:when test="${courseSchedule.baseId == base.id}">
                            <option value="${base.id}" selected>${base.baseName}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${base.id}">${base.baseName}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

            </select>
            <p style="text-align: center;">培训时间</p>
            <div class="time-block">
                <input type="text" class="start-time" value="<fmt:formatDate value="${courseSchedule.beginDate}" pattern="yyyy/MM/dd"></fmt:formatDate>" placeholder="开始时间" readonly style="cursor: pointer">
                <span></span>
            </div>
            <p style="width: 30px;text-align: center;">至</p>
            <div class="time-block">
                <input type="text" class="end-time" value="<fmt:formatDate value="${courseSchedule.endDate}" pattern="yyyy/MM/dd"></fmt:formatDate>" placeholder="结束时间" readonly style="cursor: pointer">
                <input type="hidden" id="hideEndTime" value="<fmt:formatDate value="${courseSchedule.endDate}" pattern="yyyy/MM/dd"></fmt:formatDate>">
                <span></span>
            </div>
        </div>
        <div class="top">
            <p>班级名称</p>
            <input type="text" class="name" maxlength="50" id="className" value="${courseSchedule.className}">
        </div>

        <div class="table">
            <div class="left fl">
                <div></div>
                <div>上<br/>午</div>
                <div>下<br/>午</div>
            </div>
            <div class="right fl">
                <div class="rightCont fl">
                    <c:forEach items="${courseModelList}" var="courseModel" varStatus="index">
                        <div class="rightCommon fl">
                            <div class="${(index.index+1) %2 == 0 ? 'date-line':''}"><fmt:formatDate value="${courseModel.date}" pattern="MM月dd日"></fmt:formatDate></div>
                            <div class="am course" id="courseAmDiv${index.index}">
                                <c:choose>
                                    <c:when test="${courseModel.courseAM != null && courseModel.courseAM.id != null}">
                                        <img src="${ctx}/resources/images/icon_plus.png" alt="" class="addIcon" style="display: none">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="${ctx}/resources/images/icon_plus.png" alt="" class="addIcon">
                                    </c:otherwise>
                                </c:choose>
                                    <%--<p class="content">跟张教授学种瓜第1期-张金华</p>--%>
                                <p class="content" style="cursor: pointer">
                                    <c:if test="${courseModel.courseAM != null && courseModel.courseAM.id != null}">
                                            ${courseModel.courseAM.courseName}-${courseModel.courseAM.teacher}
                                    </c:if>
                                </p>
                                <input type="hidden" class="courseName" value="${courseModel.courseAM.courseName}">
                                <input type="hidden" class="teacher" value="${courseModel.courseAM.teacher}">
                                <input type="hidden" class="courseId" value="${courseModel.courseAM.id}">
                            </div>
                            <div class="pm course" id="coursePmDiv${index.index}">
                                <c:choose>
                                    <c:when test="${courseModel.coursePM != null && courseModel.coursePM.id != null}">
                                        <img src="${ctx}/resources/images/icon_plus.png" alt="" class="addIcon" style="display: none">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="${ctx}/resources/images/icon_plus.png" alt="" class="addIcon">
                                    </c:otherwise>
                                </c:choose>
                                <p class="content">
                                    <c:if test="${courseModel.coursePM != null && courseModel.coursePM.id != null}">
                                        ${courseModel.coursePM.courseName}-${courseModel.coursePM.teacher}
                                    </c:if>
                                </p>
                                <input type="hidden" class="courseName" value="${courseModel.coursePM.courseName}">
                                <input type="hidden" class="teacher" value="${courseModel.coursePM.teacher}">
                                <input type="hidden" class="courseId" value="${courseModel.coursePM.id}">
                            </div>
                            <input type="hidden" class="courseDate" value="<fmt:formatDate value="${courseModel.date}" pattern="yyyy/MM/dd"></fmt:formatDate>">
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <%--<div class="editorBtn">
            保存
        </div>--%>
        <button class="editorBtn" type="button">保存</button>

        <!-- 弹框 -->
        <div class="pop-box" style="display: none">
            <div class="pop-content">
                <h4>添加课程</h4>
                <div class="class-detail clearfix">
                    <p class="fl">课程名称：</p>
                    <input class="fl" type="text" maxlength="100" id="courseName">
                </div>
                <div class="class-detail clearfix">
                    <p class="fl">主讲人：</p>
                    <input class="fl" type="text" maxlength="20" id="teacher">
                </div>
                <input type="hidden" value="" id="hideCourseDiv">
                <div class="pop-btn clearfix">
                    <a href="javascript:;" class="cancel fl">取消</a>
                    <a href="javascript:;" class="add fl" id="popSure">添加</a>
                </div>
            </div>
        </div>
    </div>
</div>
<input type="hidden" value="${ctx}" id="ctx"/>
<input type="hidden" value="${courseSchedule.id}" id="courseScheduleId"/>
<script src="${ctx}/resources/js/jquery-1.11.3.min.js"></script>
<script src="${ctx}/resources/js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/bootstrap-select.min.js"></script>
<script src="${ctx}/resources/js/bootstrap-datetimepicker.js"></script>
<script src="${ctx}/resources/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${ctx}/resources/js/area.js"></script>
<script src="${ctx}/resources/js/courseEdit.js"></script>
<script type="javascript">

</script>
</body>
</html>