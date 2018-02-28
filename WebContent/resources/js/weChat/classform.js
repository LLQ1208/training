
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
            }
        },
        error:function(){
            console.log("error");
        }
    });
}

function submitInfo() {
    var classScheduleId = GetQueryString("classScheduleId");
    var url = "http://"+window.location.host+"/training";

    var studentName = $("#studentName").val();
    var studentPhone = $("#studentPhone").val();
    var areaId = $("#issue").val();
    var evaluate = $("#evaluate").val();

    $.ajax({
        url:url + "/weChat/getClassCity",
        type:'POST', //GET
        async:true,    //或false,是否异步
        data:{classScheduleId:classScheduleId},
        dataType:'text',
        success:function(pager){
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