/**
 * Created by anran on 2018/2/4.
 */
$(function () {
    changeWin();
    $('.choose').selectpicker()
    //如果想要分页的哪个按钮有点击变色的效果，就给哪个加class='pageCom'
    $('.pageCom').on('click',function () {
        $('.pageCom').removeClass('active')
        $(this).addClass('active')
    })

    $('.submit-btn').on('click',function () {
        personnelAdd();
    })

});
function changeWin() {
    $('.box').css({
        'width': $(window).width(),
        'height': $(window).height()
    })
}
$(window).resize(changeWin);


function personnelAdd() {
    var ctx = $("#ctx").val();
    var userName = $("#userName").val();
    var passWord =  $("#passWord").val();
    var proviceId =  $("#proviceId").val();
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

    var data = {userName:userName,passWord:passWord,proviceId:proviceId};

    $.ajax({
        url: ctx + "/personnelController/addPersonnel",
        type: 'POST', //POST
        async: true,    //或false,是否异步
        data: data,
        dataType: 'json',
        success: function (data) {
        },
        error: function () {
            console.log("服务器错误，保存失败")
        }
    });
}