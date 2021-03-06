
$(function () {
    changeWin();
    $('.choose').selectpicker()
    //如果想要分页的哪个按钮有点击变色的效果，就给哪个加class='pageCom'
    $('.pageCom').on('click',function () {
        $('.pageCom').removeClass('active')
        $(this).addClass('active')
    })

    $('.submit-btn').on('click',function () {
        checkOut();
    })
    var userBaseId = $("#userBaseId").val();
    if(null != userBaseId && "" != userBaseId && typeof (userBaseId) != "undefined"){
        $("#proviceId").selectpicker("val", $("#userBaseId").val());
        $("#proviceId").attr("disabled","disabled");
    }
    var userType = $("#userType").val();
    if(userType != 3){
        $("#baseList").hide();
    }else{
        $("#managerId").val('基地管理员');
    }
});
function changeWin() {
    $('.box').css({
        'width': $(window).width(),
        'height': $(window).height()
    })
}
$(window).resize(changeWin);

function checkOut() {
    var ctx = $("#ctx").val();
    var userName = $("#userName").val();
    var data = {userName:userName};

    $.ajax({
        url: ctx + "/personnelController/checkOut",
        type: 'POST', //POST
        async: true,    //或false,是否异步
        data: data,
        dataType: 'text',
        success: function (data) {
            if("succeed"== data){
                personnelAdd();
            }else{
                alert("账户名已被使用！")
            }
        },
        error: function () {
            console.log("服务器错误，保存失败")
        }
    });
}


function personnelAdd() {
    var ctx = $("#ctx").val();
    var userName = $("#userName").val();
    var passWord =  $("#passWord").val();
    var proviceId =  $("#proviceId").val();
    var userType = $("#userType").val();
    if(userName.trim() =='' ){
        alert("账号名称不能为空")
        return false;
    }
    if(userName.length < 6 || userName.length > 18){
        alert("账号名称长度应为6至18个字符")
        return false;
    }
    if(passWord.trim() =='' ){
        alert("登录密码不能为空")
        return false;
    }
    if(passWord.length < 6 || passWord.length > 16){
        alert("登陆密码长度应为6至16个字符")
        return false;
    }

    if(proviceId == ''|| typeof (proviceId) == 'undefined'){
        console.log("省市区编码获取失败");
        return false;
    }
    if(userType  != 3){
        var data = {userName:userName,passWord:passWord,proviceId:proviceId,userType:userType};
    }else{
        var baseListId = $("#baseListId").val();
        if(null == baseListId || baseListId == '' || typeof(baseListId) == "undefined"){
            alert("基地信息不能为空");
            return false;
        }
        var data = {userName:userName,passWord:passWord,proviceId:proviceId,userType:userType,baseListId:baseListId};
    }


    $.ajax({
        url: ctx + "/personnelController/addPersonnel",
        type: 'POST', //POST
        async: true,    //或false,是否异步
        data: data,
        dataType: 'text',
        success: function (data) {
            if("succeed"== data){
                if(userType == 2){
                    window.location.href = ctx+"/personnelController/personnelList?userType="+2;
                }
                if(userType == 3){
                    window.location.href = ctx+"/personnelController/personnelList?userType="+3;
                }
            }
        },
        error: function () {
            console.log("服务器错误，保存失败")
        }
    });
}

function baseListChange() {
    var ctx = $("#ctx").val();
    var proviceId =  $("#proviceId").val();
    var userType = $("#userType").val();
    if(userType == 3){
        var data = {proviceId:proviceId}
        $.ajax({
            url: ctx + "/personnelController/baseListChange",
            type: 'POST', //POST
            async: true,    //或false,是否异步
            data: data,
            dataType: 'json',
            success: function (pager) {
                var list = pager.list;
                var htmls = ''
                $.each(list, function (i, base) {
                    htmls += '<option value="'+base.id+'">'+base.baseName+'</option>';
                });
                $("#baseListId").html(htmls);
                $("#baseListId").selectpicker('refresh');
            },
            error: function () {
                console.log("服务器错误，保存失败")
            }
        });
    }
}