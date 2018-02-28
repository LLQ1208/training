$(function () {
    changeWin();
    $('.rightCont').css({'width':$('.rightCommon').width()*$('.rightCommon').length});
    $('.btnone').on('click', function () {
        $('.btnone').removeClass('active')
        $(this).addClass('active')
    })

    $("#editButton").on('click', function () {
        var ctx = $("#ctx").val();
        var classScheduleId = $("#classScheduleId").val();
        window.location.href = ctx + "/course/courseEdit?id=" + classScheduleId;
    })
    //返回
    $("#backButton").on('click', function () {
        var ctx = $("#ctx").val();
        var classScheduleId = $("#classScheduleId").val();
        window.location.href = ctx + "/course/courseList";
    })
    //删除
    $("#deletedButton").on('click', function () {
        var ctx = $("#ctx").val();
        var classScheduleId = $("#classScheduleId").val();
        $.ajax({
            url:ctx + "/course/deleteClass",
            type:'post', //GET
            async:true,    //或false,是否异步
            data:{classScheduleId:classScheduleId},
            dataType:'json',
            success:function(data){
                if(data.status == "success"){
                    window.location.href = ctx + "/course/courseList";
                }
            },
            error:function(){
                console.log("error");
            }
        });
    })
});
function changeWin() {
    $('.box').css({
        'width': $(window).width(),
        'height': $(window).height()
    })
}
$(window).resize(changeWin);