/**
 * Created by anran on 2018/2/4.
 */
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
                        '<span>'+courseModel.showDate+'</span>'+
                        '</div>';
                    if(null != courseModel.courseAM && courseModel.courseAM != undefined
                        && null != courseModel.courseAM.id && courseModel.courseAM.id != undefined){
                        html += '<div class="classtypeCon blue">'+
                            courseModel.courseAM.courseName+'-'+courseModel.courseAM.teacher+
                            '</div>';
                    }else{
                        html += '<div class="classtypeCon white"></div>';
                    }
                    if(null != courseModel.coursePM && courseModel.coursePM != undefined
                        && null != courseModel.coursePM.id && courseModel.coursePM.id != undefined){
                        html += '<div class="classtypeCon blue mt">'+
                            courseModel.coursePM.courseName+'-'+courseModel.coursePM.teacher+
                            '</div>'
                    }else{
                        html += '<div class="classtypeCon white mt"></div>'
                    }
                    html += '</div>'+
                        '</div>';
                }
                $("#classList").html(html);
                $('.cont').css({'width': $('.day-one').width()*$('.day-one').length})
            }

        },
        error:function(){
            console.log("error");
        }
    });
    $('.cont').css({'width': $('.day-one').width()*$('.day-one').length})
})
