/**
 * Created by anran on 2018/2/4.
 */
$(function () {
    $('.select').selectpicker()
    $('.start1').on('click', function () {
        $('.start1').removeClass('active')
        var index = $(this).index()+1
        console.log(index)
        $('.start1:lt('+index+')').addClass('active')
    })
    $('.start2').on('click', function () {
        $('.start2').removeClass('active')
        var index = $(this).index()+1
        console.log(index)
        $('.start2:lt('+index+')').addClass('active')
    })
    $('.start3').on('click', function () {
        $('.start3').removeClass('active')
        var index = $(this).index()+1
        console.log(index)
        $('.start3:lt('+index+')').addClass('active')
    })
    $('.start4').on('click', function () {
        $('.start4').removeClass('active')
        var index = $(this).index()+1
        console.log(index)
        $('.start4:lt('+index+')').addClass('active')
    })
    $('.start5').on('click', function () {
        $('.start5').removeClass('active')
        var index = $(this).index()+1
        console.log(index)
        $('.start5:lt('+index+')').addClass('active')
    })
})
