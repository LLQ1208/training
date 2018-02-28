$(function () {

    changeWin();

    $('#s_county').on('change', function () {
        var area = $('#s_province').val() + '-' + $('#s_city').val() + '-' + $('#s_county').val();
        console.log(area)
    });


    $('.rightCont').css({'width': $('.rightCommon').width() * $('.rightCommon').length});
    $('.btnone').on('click', function () {
        $('.btnone').removeClass('active')
        $(this).addClass('active')
    });
    // $('.location').selectpicker();

    $('.start-time,.end-time').datetimepicker({
        format: 'yyyy/mm/dd',
        language: 'zh-CN',
        minView: 2,
        initialDate: new Date(),
        autoclose: true
    });



    var endDate = new Date();
    var m = endDate.setTime((n + 6 * 3600 * 24 * 1000));
    var startDate;
    var year = new Date().getFullYear();
    var month = new Date().getMonth()+1;
    var date = new Date().getDate();
    var n = new Date().setFullYear(year, month - 1, date);
    $('.start-time').on('changeDate', function (e) {
        n = e.date.getTime();
        year = e.date.getFullYear();
        month = e.date.getMonth() + 1;
        date = e.date.getDate();
        console.log(year + '-' + month + '-' + date)
    });
    $('.end-time').on('changeDate', function (e) {
        var beginTime = $(".start-time").val();
        var endTime = $(".end-time").val();
        m = e.date.getTime();
        console.log(m)
        if (m < n && beginTime != endTime) {
            var hideEndTime = $("#hideEndTime").val();
            $('.end-time').val('');
            setTimeout(function(){
                $('.end-time').val(hideEndTime);
            },100);
            alert('结束时间需大于开始时间');
            return false;
        } else {
            $("#hideEndTime").val(endTime);
            var ctx = $("#ctx").val();
            var courseScheduleId = $("#courseScheduleId").val();
            $.ajax({
                url:ctx + "/course/editGetCourseList",
                type:'post', //GET
                async:false,    //或false,是否异步
                data:{beginTime:beginTime,endTime:endTime,courseScheduleId:courseScheduleId},
                dataType:'json',
                success:function(data){
                    var html = "";
                    if(data != null && data != undefined && data.length > 0){
                        for(var i=0; i<data.length; i++){
                            var courseModel = data[i];
                            html += '<div class="rightCommon fl">'+
                                '<div class="'+((i+1)%2 == 0 ? 'date-line':'')+'">'+getTopTime(courseModel.showDate)+'</div>'+
                                '<div class="am course" id="courseAmDiv'+i+'">';
                            if(courseModel.courseAM != null && courseModel.courseAM != undefined
                                && courseModel.courseAM.id != null && courseModel.courseAM.id != undefined){
                                html += '<img src="'+ctx+'/resources/images/icon_plus.png" alt="" class="addIcon" style="display: none">';
                            }else{
                                html += '<img src="'+ctx+'/resources/images/icon_plus.png" alt="" class="addIcon">';
                            }
                            html += '<p class="content" style="cursor: pointer">';
                            if(courseModel.courseAM != null && courseModel.courseAM != undefined
                                && courseModel.courseAM.id != null && courseModel.courseAM.id != undefined){
                                html += courseModel.courseAM.courseName + courseModel.courseAM.teacher;
                            }
                            html += '</p>';
                            if(courseModel.courseAM != null && courseModel.courseAM != undefined
                                && courseModel.courseAM.id != null && courseModel.courseAM.id != undefined){
                               html += '<input type="hidden" class="courseName" value="'+courseModel.courseAM.courseName+'">'+
                                '<input type="hidden" class="teacher" value="'+courseModel.courseAM.teacher+'">'+
                                '<input type="hidden" class="courseId" value="'+courseModel.courseAM.id+'">';
                            }else{
                                '<input type="hidden" class="courseName" value="">'+
                                '<input type="hidden" class="teacher" value="">'+
                                '<input type="hidden" class="courseId" value="">';
                            }
                               html += '</div>'+
                                '<div class="pm course" id="coursePmDiv'+i+'">';
                            if(courseModel.coursePM != null && courseModel.coursePM != undefined
                                && courseModel.coursePM.id != null && courseModel.coursePM.id != undefined){
                                html += '<img src="'+ctx+'/resources/images/icon_plus.png" alt="" class="addIcon" style="display: none">';
                            }else{
                                html += '<img src="'+ctx+'/resources/images/icon_plus.png" alt="" class="addIcon">';
                            }
                            html += '<p class="content">';
                            if(courseModel.coursePM != null && courseModel.coursePM != undefined
                                && courseModel.coursePM.id != null && courseModel.coursePM.id != undefined){
                                html += courseModel.coursePM.courseName + courseModel.coursePM.teacher;
                            }
                            html += '</p>';
                            if(courseModel.coursePM != null && courseModel.coursePM != undefined
                                && courseModel.coursePM.id != null && courseModel.coursePM.id != undefined){
                                html +='<input type="hidden" class="courseName" value="'+courseModel.coursePM.courseName+'">'+
                                '<input type="hidden" class="teacher" value="'+courseModel.coursePM.teacher+'">'+
                                '<input type="hidden" class="courseId" value="'+courseModel.coursePM.id+'">';
                            }else{
                                html +='<input type="hidden" class="courseName" value="">'+
                                '<input type="hidden" class="teacher" value="">'+
                                '<input type="hidden" class="courseId" value="">';
                            }

                                html += '</div>'+
                                '<input type="hidden" class="courseDate" value="'+courseModel.showDate+'">'+
                                '</div>';
                        }
                    }
                    $('.rightCont').html(html);
                },
                error:function(){
                    alert("error");
                }
            });

            $('.rightCont .rightCommon:last').addClass('br');
            $('.rightCont').css({'width': $('.rightCommon').width() * $('.rightCommon').length});
        }

    });

    //省-市联动
    $("#s_province").on("change",function(){
        var provinceId = $(this).val();
        if(provinceId == null || provinceId == ""){
            $("#s_city").html('<option value="">市</option>');
        }else{
            var ctx = $("#ctx").val();
            $.ajax({
                url:ctx + "/course/getCityList",
                type:'get', //GET
                async:true,    //或false,是否异步
                data:{provinceId:provinceId},
                dataType:'json',
                success:function(data){
                    var cityHtml = '<option value="">市</option>';
                    if(null != data && undefined != data){
                        for(var i=0; i<data.length;i++){
                            var city = data[i];
                            cityHtml += '<option value="'+city.areaId+'">'+city.name+'</option>';
                        }
                    }
                    $("#s_city").html(cityHtml);
                    getBaseList();
                },
                error:function(){
                    alert("error");
                }
            });
        }
        $("#s_county").html('<option value="">县</option>');
    })

    //市-区县联动
    $("#s_city").on("change",function(){
        var cityId = $(this).val();
        if(cityId == null || cityId == ""){
            $("#s_county").html('<option value="">县</option>');
        }else{
            var ctx = $("#ctx").val();
            $.ajax({
                url:ctx + "/course/getAreaList",
                type:'get', //GET
                async:true,    //或false,是否异步
                data:{cityId:cityId},
                dataType:'json',
                success:function(data){
                    var areaHtml = '<option value="">县</option>';
                    if(null != data && undefined != data){
                        for(var i=0; i<data.length;i++){
                            var area = data[i];
                            areaHtml += '<option value="'+area.areaId+'">'+area.name+'</option>';
                        }
                    }
                    $("#s_county").html(areaHtml);
                    getBaseList();
                },
                error:function(){
                    alert("error");
                }
            });
        }
    })

    //基地
    $("#s_county").on("change",function(){
        getBaseList();
    })

    $(".editorBtn").on("click",function(){
        save();
    });

    // 弹框
    $('.addIcon').on('click', function () {
        $("#courseName").val("");
        $("#teacher").val("");
        var courseDivId = $(this).parents(".course").attr("id");
        $("#hideCourseDiv").val(courseDivId);
        $('.pop-box').fadeIn();
    });
    $('.content').on('click', function () {
        $("#courseName").val("");
        $("#teacher").val("");
        var courseDivId = $(this).parents(".course").attr("id");
        var courseName = $(this).parents(".course").find(".courseName").val();
        var teacher = $(this).parents(".course").find(".teacher").val();
        $("#courseName").val(courseName);
        $("#teacher").val(teacher);
        $("#hideCourseDiv").val(courseDivId);
        $('.pop-box').fadeIn();
    });
    $('.cancel').on('click', function () {
        $('.pop-box').fadeOut();
    });
    $('#popSure').on('click', function () {
        var courseDivId = $(this).parents(".pop-box").find("#hideCourseDiv").val();
        var courseName = $("#courseName").val().trim();
        var teacher = $("#teacher").val().trim();
        if(courseName == ""){
            alert("请输入课程名称");
            return false;
        }
        if(teacher == ""){
            alert("请输入主讲人");
            return false;
        }
        $("#"+courseDivId).find(".courseName").val(courseName);
        $("#"+courseDivId).find(".teacher").val(teacher);
        $("#"+courseDivId).find(".addIcon").hide();
        $("#"+courseDivId).find(".content").html(courseName + "-" + teacher);
        $('.pop-box').fadeOut();
    });
});

function getBaseList(){
    var provinceAreaId = $("#s_province").val();
    var cityAreaId = $("#s_city").val();
    var countyAreaId = $("#s_county").val();
    if(provinceAreaId == "" || cityAreaId == "" || countyAreaId == ""){
        $("#base").html('<option value="">请选择</option>');
    }else{
        var ctx = $("#ctx").val();
        $.ajax({
            url:ctx + "/course/getBaseList",
            type:'get', //GET
            async:true,    //或false,是否异步
            data:{provinceAreaId:provinceAreaId,cityAreaId:cityAreaId,countyAreaId:countyAreaId},
            dataType:'json',
            success:function(data){
                var areaHtml = '<option value="">请选择</option>';
                if(null != data && undefined != data){
                    for(var i=0; i<data.length;i++){
                        var base = data[i];
                        areaHtml += '<option value="'+base.id+'">'+base.baseName+'</option>';
                    }
                }
                $("#base").html(areaHtml);
            },
            error:function(){
                alert("error");
            }
        });
    }

}

// 时间戳
function time(year, month, date, i) {
    // console.log(year,month,date);
    var iDate = new Date();
    var oDate = new Date().setFullYear(year, month - 1, date);
    // console.log(oDate)
    iDate.setTime((oDate + i * 3600 * 24 * 1000));
    return (iDate.getMonth() + 1) + '月' + iDate.getDate() + '日';
}

// 时间戳
function time2(year, month, date, i) {
    // console.log(year,month,date);
    var iDate = new Date();
    var oDate = new Date().setFullYear(year, month - 1, date);
    // console.log(oDate)
    iDate.setTime((oDate + i * 3600 * 24 * 1000));
    return year + '/' + (iDate.getMonth() + 1) + '/' + iDate.getDate() + '/';
}
function changeWin() {
    $('.box').css({
        'width': $(window).width(),
        'height': $(window).height()
    })
}
$(window).resize(changeWin);



function save(){
    var ctx = $("#ctx").val();
    $(".editorBtn").attr("disabled",true);
    var courseScheduleId = $("#courseScheduleId").val();
    var provinceAreaId = $("#s_province").val();
    var cityAreaId = $("#s_city").val();
    var countyAreaId = $("#s_county").val();
    var baseId = $("#base").val();
    var beginDate = $(".start-time").val();
    var endDate = $(".end-time").val();
    var className = $("#className").val().trim();
    if(provinceAreaId == null || provinceAreaId == ""){
        $(".editorBtn").attr("disabled",false);
        alert("请选择省份");
        return false;
    }
    if(cityAreaId == null || cityAreaId == ""){
        $(".editorBtn").attr("disabled",false);
        alert("请选择地市");
        return false;
    }
    if(countyAreaId == null || countyAreaId == ""){
        $(".editorBtn").attr("disabled",false);
        alert("请选择县区");
        return false;
    }
    if(baseId == null || baseId == ""){
        $(".editorBtn").attr("disabled",false);
        alert("请选择基地");
        return false;
    }
    if(beginDate == null || beginDate == ""){
        $(".editorBtn").attr("disabled",false);
        alert("请选择开始时间");
        return false;
    }
    if(endDate == null || endDate == ""){
        $(".editorBtn").attr("disabled",false);
        alert("请选择结束时间");
        return false;
    }
    if(className == null || className == ""){
        $(".editorBtn").attr("disabled",false);
        alert("请输入班级名称");
        return false;
    }
    var courseDiv = $(".rightCont").find("div.rightCommon");
    var courseArr = [];
    var flag = false;
    $(".rightCont").find("div.rightCommon").each(function() {
        var courseModel = $(this);
        var date = courseModel.find(".courseDate").val().trim();
        var amCourseName = courseModel.find(".am").find(".courseName").val().trim();
        var amTeacher = courseModel.find(".am").find(".teacher").val().trim();
        var amCourseId = courseModel.find(".am").find(".courseId").val().trim();
        var pmCourseName = courseModel.find(".pm").find(".courseName").val().trim();
        var pmTeacher = courseModel.find(".pm").find(".teacher").val().trim();
        var pmCourseId = courseModel.find(".pm").find(".courseId").val().trim();
        var course = {
            "date":date,
            "amCourseName":amCourseName,
            "amTeacher":amTeacher,
            "amCourseId":amCourseId,
            "pmCourseName":pmCourseName,
            "pmTeacher":pmTeacher,
            "pmCourseId":pmCourseId
        };
        courseArr.push(course);
        if(amCourseName != "" || amTeacher != "" || pmCourseName != "" || pmTeacher != ""){
            flag = true;
        }
    });
    if(!flag){
        $(".editorBtn").attr("disabled",false);
        alert("请添加课程内容");
        return false;
    }
    var json = {courseScheduleId:courseScheduleId,provinceAreaId:provinceAreaId,cityAreaId:cityAreaId,countyAreaId:countyAreaId,baseId:baseId,
        beginDate:beginDate,endDate:endDate,className:className,courseArr:JSON.stringify(courseArr)};
    var param = JSON.stringify(json);
    $.ajax({
        url:ctx + "/course/edit",
        type:'post', //GET
        async:true,    //或false,是否异步
        data:json,
        dataType:'json',
        success:function(data){
            if(data.status == "success"){
                alert("编辑成功");
                window.location.href = ctx + "/course/courseDetail?id="+courseScheduleId;
            }
        },
        error:function(){
            $(".editorBtn").attr("disabled",false);
            console.log("error");
        }
    });
}

function getTopTime(date){
    var showDate = date.substring(5,date.length);
    showDate = showDate.replace("/","月");
    showDate = showDate + "日";
    return showDate;
}