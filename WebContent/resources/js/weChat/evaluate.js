/**
 * Created by anran on 2018/2/4.
 */

function getShowDate(showDate){
    return showDate.substring(5,showDate.length);
}
$(function () {
    var classScheduleId = GetQueryString("classScheduleId");
    $.ajax({
        url:url + "/weChat/getClassDetail",
        type:'get', //GET
        async:true,    //或false,是否异步
        data:{classScheduleId:classScheduleId},
        dataType:'json',
        success:function(data){
            console.log(data);
            if(null != data && data != undefined){
                $("#className").html(data.className);
                var html = '';
                for(var i=0; i<data.list.length; i++){
                    var courseModel = data.list[i];
                    html += '<div class="day-one fl">'+
                        '<div class="week clearfix">'+
                        '<span>'+courseModel.week+'</span>'+
                        '<span>'+getShowDate(courseModel.showDate)+'</span>'+
                        '</div>';
                    if(null != courseModel.courseAM && courseModel.courseAM != undefined
                        && null != courseModel.courseAM.id && courseModel.courseAM.id != undefined){
                        html += '<div class="classtypeCon blue course_eval" courseId="'+courseModel.courseAM.id+'">'+
                            courseModel.courseAM.courseName+'-'+courseModel.courseAM.teacher+
                            '</div>';
                    }else{
                        html += '<div class="classtypeCon white"></div>';
                    }
                    if(null != courseModel.coursePM && courseModel.coursePM != undefined
                        && null != courseModel.coursePM.id && courseModel.coursePM.id != undefined){
                        html += '<div class="classtypeCon blue mt course_eval" courseId="'+courseModel.coursePM.id+'">'+
                            courseModel.coursePM.courseName+'-'+courseModel.coursePM.teacher+
                            '</div>'
                    }else{
                        html += '<div class="classtypeCon white mt"></div>'
                    }
                    html += '<input type="hidden" class="courseDate" value="'+courseModel.showDate+'">'+
                        '</div>';
                }
                $("#classList").html(html);
                toEval();
                $('.cont').css({'width': $('.day-one').width()*$('.day-one').length})
            }

        },
        error:function(){
            console.log("error");
        }
    });
})

function toEval(){
    $(".course_eval").on("click",function(){
        var courseDate = $(this).parent().find(".courseDate").val();
        var year = new Date().getFullYear();
        var month = new Date().getMonth()+1;
        var date = new Date().getDate();
        var hour = new Date().getHours();
        var nowTime = new Date().setFullYear(year, month - 1, date);

        var courseYear = courseDate.substring(0,4);
        var courseMonth = courseDate.substring(5,7);
        var courseDay = courseDate.substring(8,10);
        var courseTime = new Date().setFullYear(courseYear, courseMonth - 1, courseDay);

        //是否是上午
        var isAm = true;
        if($(this).hasClass("mt")){
            isAm =false;
        }
        //判断时间
        var flag = false;
        if(isAm){
            if(courseTime < nowTime && courseDate != (year+"/"+month+"/"+date) && hour > 10){
                flag = true;
            }
        }else{
            if(courseTime < nowTime && courseDate != (year+"/"+month+"/"+date) && hour > 13){
                flag = ture;
            }
        }
        var courseId = $(this).attr("courseId");
        if(flag){
            window.location.href = url + 'weChat/teachtform.html?courseId='+courseId;
        }
    })



}