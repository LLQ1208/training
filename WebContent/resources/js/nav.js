/**
 * Created by anran on 2018/2/4.
 */
$(function () {
    $('.firstCommon').on('click',function(){
        $('.secondCommon').slideUp()
        if ($(this).siblings('.secondCommon').css('display')=='none'){
            $(this).siblings('.secondCommon').slideDown()
        }
    })
    $('.final').on('click',function () {
        var ctx = $("#ctx").val();
        console.log('--'+$(this).html()+'--');
        var title = $(this).html();
        if(title == '省级管理员'){
            window.location.href = ctx+"/personnelController/personnelList?userType="+2;
        }
        if(title == '基地管理员'){
            window.location.href = ctx+"/personnelController/personnelList?userType="+3;
        }
        // window.location.href = $(this).attr("href");
    })
})