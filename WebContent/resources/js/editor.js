$(function () {
    changeWin();
    $('.rightCont').css({'width':$('.rightCommon').width()*$('.rightCommon').length});
    $('.btnone').on('click', function () {
        $('.btnone').removeClass('active')
        $(this).addClass('active')
    })
});
function changeWin() {
    $('.box').css({
        'width': $(window).width(),
        'height': $(window).height()
    })
}
$(window).resize(changeWin);