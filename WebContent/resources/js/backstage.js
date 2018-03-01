
$(function () {
    changeWin();
    $('.directChange').selectpicker()
    //分页
    loadpage();
    //二维码预览
    qrDetail();
    //课程详情
    classDetail();
    //搜索
    $(".searchbtn").on("click",function(){
        search(1,1);
    })
});
function changeWin() {
    $('.box').css({
        'width': $(window).width(),
        'height': $(window).height()
    })
}

function loadpage() {
    var myPageCount = parseInt($("#PageCount").val());
    var myPageSize = parseInt($("#PageSize").val());
    var countindex = myPageCount % myPageSize > 0 ? (myPageCount / myPageSize) + 1 : (myPageCount / myPageSize);
    $("#countindex").val(countindex);

    $.jqPaginator('#pagination', {
        totalPages: parseInt($("#countindex").val()),
        visiblePages: parseInt($("#visiblePages").val()),
        currentPage: 1,
        first: '<li class="first"><a href="javascript:;">首页</a></li>',
        prev: '<li class="prev"><a href="javascript:;"><i class="arrow arrow2"></i>上一页</a></li>',
        next: '<li class="next"><a href="javascript:;">下一页<i class="arrow arrow3"></i></a></li>',
        last: '<li class="last"><a href="javascript:;">末页</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            if(type != 'init'){
                var ctx = $("#ctx").val();
                console.log(num);
                search(num,0);
            }
        }
    });
}


function search(page,type){
    var searchKey = $("#searchKey").val().trim();
    var provinceAreaId = $("#province").val();
    var ctx = $("#ctx").val();
    $.ajax({
        url: ctx + "/course/searchByPage",
        type: 'POST', //GET
        async: false,    //或false,是否异步
        data: {searchKey:searchKey,provinceAreaId:provinceAreaId,page:page},
        dataType: 'json',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success: function (data) {
            var html = '<div class="tableTitle">'+
                    '<div>序号</div>'+
                    '<div>地区</div>'+
                    '<div>班次</div>'+
                    '<div>基地管理员</div>'+
                    '<div>班次人数</div>'+
                    '<div>课节数</div>'+
                    '<div>详情</div>'+
                    '<div>二维码</div>'+
                    '</div>';
            if(null != data && data != undefined){
                var pageHelper = data;
                if(pageHelper.list != null && pageHelper.list.length > 0){
                    for(var i=0;i<pageHelper.list.length;i++){
                        var classModel = pageHelper.list[i];
                        html += '<div class="tabCommon">'+
                        '<div>'+ (i+1) + '</div>'+
                        '<div>'+classModel.area+'</div>'+
                        '<div>'+classModel.className+'</div>'+
                        '<div>'+classModel.baseAdmin+'</div>'+
                        '<div>'+classModel.classPersonNum+'</div>'+
                        '<div>'+classModel.courseNum+'</div>'+
                        '<div class="classDetail" href="'+ctx+'/course/courseDetail?id='+classModel.classScheduleId+'">点击查看详情</div>'+
                        '<div class="qrCodedetail" classScheduleId="'+classModel.classScheduleId+'"><span></span><span>下载</span></div>'+
                        '</div>';
                    }
                }
                $("#tableList").html(html);

                if(pageHelper.totalRow != 0){
                    $("#pagination").show();
                    $("#PageCount").val(pageHelper.totalRow);
                }else{
                    $("#pagination").hide();
                }
                $("#courseNum").html(pageHelper.tempParam1);
                $("#studentNum").html(pageHelper.tempParam2);
                $("#classNum").html(pageHelper.totalRow);
            }
            //二维码预览
            qrDetail();
            //课程详情
            classDetail();
        },
        error: function () {
            console.log("搜索会员错误")
        }
    });
    if(type == 1){
        loadpage();
    }
}
$(window).resize(changeWin);


function qrDetail(){
    $(".qrCodedetail").on("click",function(){
        var ctx = $("#ctx").val();
        var classScheduleId = $(this).attr("classScheduleId");
        window.open(ctx + "/course/getTwoDimension?classScheduleId=" + classScheduleId);
    });
}

function classDetail(){
    $(".classDetail").on("click",function(){
        var ctx = $("#ctx").val();
        var href = $(this).attr("href");
        window.location.href = href;
    });
}

