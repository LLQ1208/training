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
});
function changeWin() {
    $('.box').css({
        'width': $(window).width(),
        'height': $(window).height()
    })
}
$(window).resize(changeWin);