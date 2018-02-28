
$(function () {

    var courseId = GetQueryString("courseId");
    $.ajax({
        url:url + "/weChat/teachEval",
        type:'get', //GET
        async:true,    //或false,是否异步
        data:{courseId:courseId},
        dataType:'json',
        success:function(data){
            console.log(data);

            if(data != null && data != undefined){
                //省
                var provinceList = data.provinceList;
                var provinceHtml = '';
                for(var i=0; i<provinceList.length;i++){
                    var province = provinceList[i];
                    provinceHtml += '<option value="'+province.areaId+'">'+province.name+'</option>';
                }
                $("#province").html(provinceHtml);

                //评价
                var evalLevelList = data.evalLevelList;
                var evalLevelHtml = '';
                for(var i=0; i<evalLevelList.length;i++){
                    var evalLevel = evalLevelList[i];
                    evalLevelHtml += '<option value="'+evalLevel.code+'">'+evalLevel.desc+'</option>';
                }
                $("#eval").html(evalLevelHtml);
                $("#courseId").val(data.courseId);
                $("#className").html(data.course.courseName);
                $("#classScheduleId").val(data.course.courseScheduleId);
                $('.select').selectpicker();
            }
        },
        error:function(){
            console.log("error");
        }
    });

    $('.start1').on('click', function () {
        $('.start1').removeClass('active')
        var index = $(this).index()+1
        console.log(index)
        $('.start1:lt('+index+')').addClass('active')
    })
    $('.start2').on('click', function () {
        $('.start2').removeClass('active')
        var index = $(this).index()+1
        console.log(index)
        $('.start2:lt('+index+')').addClass('active')
    })
    $('.start3').on('click', function () {
        $('.start3').removeClass('active')
        var index = $(this).index()+1
        console.log(index)
        $('.start3:lt('+index+')').addClass('active')
    })

    $(".input-student").on("change",function(){
        var value = $(this).val().trim();
        if(value != ""){
            $(this).css("border-color","");
        }
    })

    $(".submit").on("click",function(){
        save();
    })
})

// 验证手机号
function isPhoneNo(phone) {
    var phoneReg = /^1[34578]\d{9}$/;
    var reg = new RegExp(phoneReg);
    return reg.test(phone);
}

function save(){
    var courseId = $("#courseId").val();
    var studentName = $("#studentName").val().trim();
    var phone = $("#phone").val().trim();
    var provinceAreaId = $("#province").val();
    var evalId = $("#eval").val();
    if(studentName == ""){
        $("#studentName").focus();
        $("#studentName").css("border-color","red");
    }
    if(phone == "" || !isPhoneNo(phone)){
        $("#phone").focus();
        $("#phone").css("border-color","red");
    }

    var starEvalOne = $(".star-eval-one").find(".active").length;
    var starEvalTwo = $(".star-eval-two").find(".active").length;
    var starEvalThree = $(".star-eval-three").find(".active").length;
    var json = {courseId:courseId,phone:phone,studentName:studentName,provinceAreaId:provinceAreaId,
        evalId:evalId,starEvalOne:starEvalOne,starEvalTwo:starEvalTwo,starEvalThree:starEvalThree};
    $.ajax({
        url:url + "/weChat/teachEvalSave",
        type:'post', //GET
        async:true,    //或false,是否异步
        data:json,
        dataType:'json',
        success:function(data){
            if(data.status == "-1"){
                alert(data.mesg);
                $("#phone").focus();
                $("#phone").css("border-color","red");
            }else{
                window.location.href = url + "weChat/evaluate.html?classScheduleId="+$("#classScheduleId").val();
            }
        },
        error:function(){
            console.log("error");
        }
    });
}