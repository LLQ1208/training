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
        $('.final').removeClass('active')
        $(this).addClass('active')
    })
})