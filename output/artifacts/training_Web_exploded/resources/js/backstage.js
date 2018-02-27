/**
 * Created by anran on 2018/2/4.
 */
$(function () {
    changeWin();
    $('.directChange').selectpicker()
    //分页
    loadpage();
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
            var html = '';
            if(null != data && data != undefined){
                var pageHelper = data;
                if(pageHelper.list != null && pageHelper.list.length > 0){
                    for(var i=0;i<pageHelper.list.length;i++){
                        var template = pageHelper.list[i];
                        html += '<li class="clearfix">'+
                        '<p class="name fl"><input type="checkbox" class="select-one" value="'+template.memberCardId+'"><label onclick="toDetail(this)" class="over-hide" memberId="'+template.memberId+'"  boxid="'+template.box+'">'+template.memberName+'</label></p>'+
                        '<p class="sex-box fl">'+template.sexStr+'</p>'+
                        '<p class="birth fl">'+template.birthday+'</p>'+
                        '<p class="phone fl">'+template.phone+'</p>'+
                        '<p class="mail fl">'+template.email+'</p>'+
                        '<p class="run-card fl">'+template.firstOpenCardTime+'</p>'+
                        '<p class="renewal-card fl">'+template.continueCardTime+'</p>'+
                        '<p class="expire fl">'+template.cardEndTime+'</p>'+
                        '<p class="card-kind fl">'+template.cardTemplateName+'</p>'+
                        '<p class="card-type fl">'+template.memberCardType+'</p>'+
                        '<p class="count fl">'+template.totalCount+'</p>'+
                        '<p class="status fl">'+template.cardStatus+'</p>'+
                        '<p class="latest-class fl">'+template.lastCourseTime+'</p>'+
                        '<p class="add-up-num fl">'+template.orderCourseCount+'</p>'+
                        '<p class="rate fl">'+template.orderCourseRate+'</p>'+
                        '<p class="order-class fl">'+template.thisCardOrderCourseCount+'</p>'+
                        '<p class="order-rate fl">'+template.thisCardOrderCourseRate+'</p>'+
                        '<p class="attendance fl">'+template.attendanceRate+'</p>'+
                        '</li>';
                    }
                }
                $(".data-list").html(html);
                $('.select-all,.select-one').iCheck({
                    checkboxClass: 'icheckbox_minimal-blue',
                    increaseArea: '20%' // optional
                });
                $('.select-one').on('ifChanged', function () {
                    // console.log($(this).parents('li').index());
                    var n = 0;
                    for (var i = 0; i < $('.data-list li').length; i++) {
                        if ($('.data-list li').eq(i).find('.select-one').prop('checked') == true) {
                            n++;
                        }
                    }
                    if (n == $('.data-list li').length) {
                        $('.select-all').iCheck('check');
                    } else {
                        $('.select-all').iCheck('uncheck');
                    }
                });
                // 全选
                $('.select-all').on('ifClicked', function () {
                    // console.log($(this).prop('checked'));
                    if (!$(this).prop('checked') == true) {
                        $('.select-one').iCheck('check');
                    } else {
                        $('.select-one').iCheck('uncheck');
                    }
                });
                if(pageHelper.totalRow != 0){
                    $("#pagination").show();
                    $("#PageCount").val(pageHelper.totalRow);
                }else{
                    $("#pagination").hide();
                }

            }
            $("#nowPageIndex").val(page);
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