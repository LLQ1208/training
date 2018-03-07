
$(function () {
    $('.select').selectpicker()
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
    $('.start4').on('click', function () {
        $('.start4').removeClass('active')
        var index = $(this).index()+1
        console.log(index)
        $('.start4:lt('+index+')').addClass('active')
    })
    $('.start5').on('click', function () {
        $('.start5').removeClass('active')
        var index = $(this).index()+1
        console.log(index)
        $('.start5:lt('+index+')').addClass('active')
    })

    $('.submit').on('click', function () {
        checkout();
    });
    init();

});

function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}
function checkout() {
    var classScheduleId = GetQueryString("classScheduleId");
    var url = "http://"+window.location.host+"/training";
    var studentName = $("#studentName").val();
    var studentPhone = $("#studentPhone").val();
    var data = {classScheduleId:classScheduleId,studentName:studentName,studentPhone:studentPhone};
    $.ajax({
        url:url + "/weChat/getClassCheckOut",
        type:'POST', //GET
        async:true,    //或false,是否异步
        data:data,
        dataType:'text',
        success:function(pager){
            if(pager =="succeed"){
                console.log("--------------")
                submitInfo();
            }else if(pager == -1){
                alert("该同学已经评价过");
            }
        },
        error:function(){
            console.log("error");
        }
    });
}

function submitInfo() {
    var phoneReg = /^1[34578]\d{9}$/;
    var classScheduleId = GetQueryString("classScheduleId");
    var url = "http://"+window.location.host+"/training";
    var studentName = $("#studentName").val();
    var studentPhone = $("#studentPhone").val();
    var areaId = $("#issue").val();
    var satisfaction = $("#satisfaction").val();
    var accommodations = $("#accommodations").find(".active").length;
    var considerate = $("#considerate").find(".active").length;
    var rationality = $("#rationality").find(".active").length;
    var serviceAttitude = $("#serviceAttitude").find(".active").length;
    var gain = $("#gain").find(".active").length;

    var reg = new RegExp(phoneReg);
    if(studentPhone == null || "" == studentPhone || !reg.test(studentPhone.trim())){
        alert("请输入正确的电话号码");
        return false;
    }
    if(studentName == null || "" == studentName){
        alert("请输入正确用户名");
        return false;
    }
    var data = {classScheduleId:classScheduleId,studentName:studentName,studentPhone:studentPhone,areaId:areaId,satisfaction:satisfaction,accommodations:accommodations,considerate:considerate,rationality:rationality,serviceAttitude:serviceAttitude,gain:gain};

    $.ajax({
        url:url + "/weChat/classSave",
        type:'POST', //GET
        async:true,    //或false,是否异步
        data:data,
        dataType:'text',
        success:function(pager){
            window.location.href = "http://"+window.location.host+"/training/weChat/evaluate.html?classScheduleId="+classScheduleId;
        },
        error:function(){
            console.log("error");
        }
    });
}

function init() {
    var classScheduleId = GetQueryString("classScheduleId");
    var url = "http://"+window.location.host+"/training";
    $.ajax({
        url:url + "/weChat/getClassCity",
        type:'get', //GET
        async:true,    //或false,是否异步
        data:{classScheduleId:classScheduleId},
        dataType:'json',
        success:function(pager){
            $("#title").html(pager.title);
            var list = pager.list;
            var htmls = ''
            $.each(list, function (i, base) {
                htmls += '<option value="'+base.id+'">'+base.name+'</option>';
            });
            $("#issue").html(htmls);
            $("#issue").selectpicker('refresh');
        },
        error:function(){
            console.log("error");
        }
    });
}