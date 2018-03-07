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
        <jsp:include page="menu.jsp" flush="false" >
            <jsp:param value="baseInfoAdd" name="menuName"/>
            <jsp:param value="1" name="group"/>
        </jsp:include>
    </div>

    <div class="container-box">
        <div class="top">
            <p>所在地</p>
            <select name="s_province" class="location" id="s_province" style="cursor: pointer;width: 70px">
                <option value="">省</option>
                <c:forEach items="${proviceList}" var="provice">
                    <option value="${provice.areaId}">${provice.name}</option>
                </c:forEach>
            </select>
            <select name="s_city" class="location" id="s_city" style="cursor: pointer;width: 70px">
                <option value="">市</option>
            </select>
            <select name="s_county" class="location" id="s_county" style="cursor: pointer;width: 70px">
                <option value="">县</option>
            </select>
        </div>
        <div class="top">
            <p>基地名称</p>
            <input type="text" class="name" maxlength="50" id="className">
        </div>
        <button class="editorBtn" type="button">保存</button>
    </div>
</div>
<input type="hidden" value="${ctx}" id="ctx"/>
<script src="${ctx}/resources/js/jquery-1.11.3.min.js"></script>
<script src="${ctx}/resources/js/bootstrap.min.js"></script>
<script src="${ctx}/resources/js/bootstrap-select.min.js"></script>
<script src="${ctx}/resources/js/bootstrap-datetimepicker.js"></script>
<script src="${ctx}/resources/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${ctx}/resources/js/area.js"></script>
<script src="${ctx}/resources/js/personnel/baseInfoAdd.js"></script>
<script type="javascript">

</script>
</body>
</html>