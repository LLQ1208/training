/**
 * Created by anran on 2018/2/4.
 */
$(function () {
    changeWin();
    $('.directChange').selectpicker()
    //如果想要分页的哪个按钮有点击变色的效果，就给哪个加class='pageCom'
    $('.pageCom').on('click',function () {
        $('.pageCom').removeClass('active')
        $(this).addClass('active')
    })

    $('.searchbtn').on('click',function () {
       personnelListSearch();
    })

    $('.addbtn').on('click',function () {
        var ctx = $("#ctx").val();
        var userType = $("#userType").val().trim();
        window.location = ctx+"/personnelController/addInit?userType="+userType;
    })
    var userType = $("#userType").val().trim();

    $('.tocheck').on('click',function () {
        var ctx = $("#ctx").val();
        // var id = $(this).find('#personnelId').val();
        var el = $(this);
        var id = el.closest('.tabCommon').find('#personnelId').val();
        console.log('id  '+id);
        window.location = ctx+"/personnelController/personnelView?Id="+id+"&userType="+userType;
    })

    $('.editPersonnel').on('click',function () {
        var ctx = $("#ctx").val();
        var el = $(this);
        var id = el.closest('.tabCommon').find('#personnelId').val();
        console.log('id  '+id);
        window.location = ctx+"/personnelController/updatePersonnelInit?Id="+id+"&userType="+userType;
    })

    var userBaseId = $("#userBaseId").val();
    if(null != userBaseId && "" != userBaseId && typeof (userBaseId) != "undefined"){
        $("#proviceId").selectpicker("val", $("#userBaseId").val());
        $("#proviceId").attr("disabled","disabled");
    }

});
function changeWin() {
    $('.box').css({
        'width': $(window).width(),
        'height': $(window).height()
    })
}
$(window).resize(changeWin);

function personnelListSearch() {
    var ctx = $("#ctx").val();
    var proviceId = $("#proviceId").val();
    var searchUserName = $("#searchUserName").val().trim();
    var userType = $("#userType").val().trim();
    var data = {proviceId:proviceId,searchUserName:searchUserName,userType:userType};
    $.ajax({
        url: ctx + "/personnelController/personnelListSearch",
        type: 'POST', //POST
        async: true,    //或false,是否异步
        data: data,
        dataType: 'json',
        success: function (pager) {
            var list = pager.list;
            var htmls = ''
            $.each(list, function (i, provice) {
                htmls += ' <div class="tabCommon">';
                htmls += '<div>'+(i+1)+'</div>';
                htmls += ' <div>'+ provice.areaName +'</div>';
                htmls += ' <div>'+ provice.userName +'</div>';
                htmls += ' <div>'+ provice.userName +'</div>';
                htmls += ' <div>'+ provice.userTypeName +'</div>';
                htmls += '<input type="hidden" id="personnelId" value="' +provice.id + '"/>';
                htmls += ' <div><p class="tocheck">点击查看详情</p></div>';
                htmls += ' <div class="editPersonnel" >编辑</div>';
                htmls += ' </div>';
            });
            $(".tcontainer").html(htmls);

            $('.tocheck').on('click',function () {
                var el = $(this);
                var id = el.closest('.tabCommon').find('#personnelId').val();
                console.log('id  '+id);
                window.location = ctx+"/personnelController/personnelView?Id="+id+"&userType="+userType;
            })
            $('.editPersonnel').on('click',function () {
                var ctx = $("#ctx").val();
                var el = $(this);
                var id = el.closest('.tabCommon').find('#personnelId').val();
                console.log('id  '+id);
                window.location = ctx+"/personnelController/updatePersonnelInit?Id="+id+"&userType="+userType;
            })
        },
        error: function () {
            console.log("服务器错误，保存失败")
        }
    });
}