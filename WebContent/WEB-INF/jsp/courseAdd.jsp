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
    <link rel="stylesheet" href="{}">
    <link rel="stylesheet" href="../css/bootstrap3.min.css">
    <link rel="stylesheet" href="../css/bootstrap-select.min.css">
    <link rel="stylesheet" href="../css/bootstrap-datetimepicker.css">
    <link rel="stylesheet" href="../css/nav.css">
    <link rel="stylesheet" href="../css/add.css">
</head>
<body>
<div class="box clearfix">
    <!--左侧导航占位-->
    <div class="nav fl clearfix">

    </div>

    <div class="container-box">
        <div class="top">
            <p>所在地</p>
            <select name="s_province" class="location" id="s_province">
                <option value="">省</option>
            </select>
            <select name="s_city" class="location" id="s_city">
                <option value="">市</option>
            </select>
            <select name="s_county" class="location" id="s_county">
                <option value="">县</option>
            </select>
            <p style="text-align: center;">培训时间</p>
            <div class="time-block">
                <input type="text" class="start-time" placeholder="开始时间" readonly>
                <span></span>
            </div>
            <p style="width: 30px;text-align: center;">至</p>
            <div class="time-block">
                <input type="text" class="end-time" placeholder="结束时间" readonly>
                <span></span>
            </div>
        </div>
        <div class="top">
            <p>班级名称</p>
            <input type="text" class="name">
        </div>

        <div class="table">
            <div class="left fl">
                <div></div>
                <div>上<br/>午</div>
                <div>下<br/>午</div>
            </div>
            <div class="right fl">
                <div class="rightCont fl">
                    <div class="rightCommon fl">
                        <div>5月21日</div>
                        <div>
                            <!--<img src="../img/icon_plus.png" alt="" class="addIcon">-->
                            <p>跟张教授学种瓜第1期-张金华</p>
                        </div>
                        <div>
                            <img src="../img/icon_plus.png" alt="" class="addIcon">
                        </div>
                    </div>
                    <div class="rightCommon fl">
                        <div class="date-line">5月21日</div>
                        <div>
                            <img src="../img/icon_plus.png" alt="" class="addIcon">
                        </div>
                        <div>
                            <img src="../img/icon_plus.png" alt="" class="addIcon">
                        </div>
                    </div>
                    <div class="rightCommon fl">
                        <div>5月21日</div>
                        <div>
                            <img src="../img/icon_plus.png" alt="" class="addIcon">
                        </div>
                        <div>
                            <img src="../img/icon_plus.png" alt="" class="addIcon">
                        </div>
                    </div>
                    <div class="rightCommon fl">
                        <div>5月21日</div>
                        <div>
                            <img src="../img/icon_plus.png" alt="" class="addIcon">
                        </div>
                        <div>
                            <img src="../img/icon_plus.png" alt="" class="addIcon">
                        </div>
                    </div>
                    <div class="rightCommon fl">
                        <div>5月21日</div>
                        <div>
                            <img src="../img/icon_plus.png" alt="" class="addIcon">
                        </div>
                        <div>
                            <img src="../img/icon_plus.png" alt="" class="addIcon">
                        </div>
                    </div>
                    <div class="rightCommon fl">
                        <div>5月21日</div>
                        <div>
                            <img src="../img/icon_plus.png" alt="" class="addIcon">
                        </div>
                        <div>
                            <img src="../img/icon_plus.png" alt="" class="addIcon">
                        </div>
                    </div>
                    <div class="rightCommon br fl">
                        <div>5月21日</div>
                        <div>
                            <img src="../img/icon_plus.png" alt="" class="addIcon">
                        </div>
                        <div>
                            <img src="../img/icon_plus.png" alt="" class="addIcon">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="editorBtn">
            保存
        </div>
    </div>
</div>

<script src="../js/jquery-1.11.3.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/bootstrap-select.min.js"></script>
<script src="../js/bootstrap-datetimepicker.js"></script>
<script src="../js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="../js/area.js"></script>
<script src="../js/add.js"></script>
</body>
</html>