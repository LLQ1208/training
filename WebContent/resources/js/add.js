$(function () {

    changeWin();

    // 省市区
    _init_area();

    $('#s_county').on('change', function () {
        var area = $('#s_province').val() + '-' + $('#s_city').val() + '-' + $('#s_county').val();
        console.log(area)
    });


    $('.rightCont').css({'width': $('.rightCommon').width() * $('.rightCommon').length});
    $('.btnone').on('click', function () {
        $('.btnone').removeClass('active')
        $(this).addClass('active')
    });
    // $('.location').selectpicker();

    $('.start-time,.end-time').datetimepicker({
        format: 'yyyy/mm/dd',
        language: 'zh-CN',
        minView: 2,
        initialDate: new Date(),
        autoclose: true
    });

    var n, m;
    var startDate;
    var year, month, date;
    $('.start-time').on('changeDate', function (e) {
        n = e.date.getTime();
        year = e.date.getFullYear();
        month = e.date.getMonth() + 1;
        date = e.date.getDate();
        console.log(year + '-' + month + '-' + date)
    });
    $('.end-time').on('changeDate', function (e) {
        m = e.date.getTime();
        console.log(m)
        if (m < n) {
            alert('结束时间需大于开始时间');
            $('.end-time').val('');
            return;
        } else {
            var count = (m - n) / 1000 / 86400 + 1;
            var dateHtml = '';
            for (var i = 0; i < count; i++) {
                console.log(time(year, month, date, i));
                dateHtml += '<div class="rightCommon fl">' +
                    '<div>' + time(year, month, date, i) + '</div>' +
                    '<div>' +
                    '<img src="../img/icon_plus.png" alt="" class="addIcon">' +
                    '</div>' +
                    '<div>' +
                    '<img src="../img/icon_plus.png" alt="" class="addIcon">' +
                    '</div>' +
                    '</div>';
            }
            $('.rightCont').html(dateHtml);
            $('.rightCont .rightCommon:last').addClass('br');
            $('.rightCont').css({'width': $('.rightCommon').width() * $('.rightCommon').length});
        }

    });


});

// 时间戳
function time(year, month, date, i) {
    // console.log(year,month,date);
    var iDate = new Date();
    var oDate = new Date().setFullYear(year, month - 1, date);
    // console.log(oDate)
    iDate.setTime((oDate + i * 3600 * 24 * 1000));
    return (iDate.getMonth() + 1) + '月' + iDate.getDate() + '日';
}

function changeWin() {
    $('.box').css({
        'width': $(window).width(),
        'height': $(window).height()
    })
}
$(window).resize(changeWin);