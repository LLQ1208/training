var ctx = $("#ctx").val();
$(function(){

    $("#userName").blur(function(){
        autoPwd();
    });

   $("#userName").keyup(function(){
      $("#showMesg").html("");
   });
    $("#password").keyup(function(){
        $("#showMesg").html("");
    });

    //点击登录按钮
    $("#loginBtn").click(function(){
        var userName = $("#userName").val();
        var password = $("#password").val();
        if(userName==""||userName==null||userName=="请输入用户名"||password==""||password==null||password=="请输入密码"){
            $(".login-status").html("请输入用户名密码");
            return false;
        }
        var param = {"userName":userName,"password":password};
        $.ajax({
            url: ctx+"/login",
            type: "POST",
            dataType:'json',
            data:param,
            async:false,
            beforeSend: function(){
                $("#showMesg").html("正在登录中.....");
            },
            success: function(rs){
                if(rs.result){
                    window.location = ctx+"/course/courseAdd";
                }else{
                    $("#showMesg").html(rs.msg);
                    console.log("login fail");
                }
            },
            error:function(XMLHttpRequest){
                console.log('服务器异常');
            }
        });
    });
})



