
$(function () {
    $('.firstCommon').on('click',function(){
        $('.point').removeClass('active');
        $('.secondCommon').slideUp()
        if ($(this).siblings('.secondCommon').css('display')=='none'){
            $(this).siblings('.secondCommon').slideDown();
            $(this).find('.point').addClass('active');
        }
    })
    // $('.final').on('click',function () {
    //     var ctx = $("#ctx").val();
    //     console.log('--'+$(this).html()+'--');
    //     var title = $(this).html();
    //     if(title == '省级管理员'){
    //         window.location.href = ctx+"/personnelController/personnelList?userType="+2;
    //     }
    //     if(title == '基地管理员'){
    //         window.location.href = ctx+"/personnelController/personnelList?userType="+3;
    //     }
    //     // window.location.href = $(this).attr("href");
    // })
    $('.final').on('click',function () {
        var ctx = $("#ctx").val();
        console.log('--'+$(this).html()+'--');
        var title = $(this).html();
        if(title == '省级管理员'){
            window.location.href = ctx+"/personnelController/personnelList?userType=2";
        }
        if(title == '基地管理员'){
            window.location.href = ctx+"/personnelController/basePersonnelList?userType=3";
        }
        if(title != '省级管理员' && title != '基地管理员'){
            window.location.href = $(this).attr("href");
        }
    })
})