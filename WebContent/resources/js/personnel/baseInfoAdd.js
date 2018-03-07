
$(function () {
    changeWin();
    $('.editorBtn').on('click',function () {
        baseInfoAdd();
    })
});
function changeWin() {
    $('.box').css({
        'width': $(window).width(),
        'height': $(window).height()
    })
}
$(window).resize(changeWin);

//省-市联动
$("#s_province").on("change",function(){
    var provinceId = $(this).val();
    if(provinceId == null || provinceId == ""){
        $("#s_city").html('<option value="">市</option>');
    }else{
        var ctx = $("#ctx").val();
        $.ajax({
            url:ctx + "/course/getCityList",
            type:'get', //GET
            async:true,    //或false,是否异步
            data:{provinceId:provinceId},
            dataType:'json',
            success:function(data){
                var cityHtml = '<option value="">市</option>';
                if(null != data && undefined != data){
                    for(var i=0; i<data.length;i++){
                        var city = data[i];
                        cityHtml += '<option value="'+city.areaId+'">'+city.fullName+'</option>';
                    }
                }
                $("#s_city").html(cityHtml);
                getBaseList();
            },
            error:function(){
                alert("error");
            }
        });
    }
    $("#s_county").html('<option value="">县</option>');
})

//市-区县联动
$("#s_city").on("change",function(){
    var cityId = $(this).val();
    if(cityId == null || cityId == ""){
        $("#s_county").html('<option value="">县</option>');
    }else{
        var ctx = $("#ctx").val();
        $.ajax({
            url:ctx + "/course/getAreaList",
            type:'get', //GET
            async:true,    //或false,是否异步
            data:{cityId:cityId},
            dataType:'json',
            success:function(data){
                var areaHtml = '<option value="">县</option>';
                if(null != data && undefined != data){
                    for(var i=0; i<data.length;i++){
                        var area = data[i];
                        areaHtml += '<option value="'+area.areaId+'">'+area.fullName+'</option>';
                    }
                }
                $("#s_county").html(areaHtml);
            },
            error:function(){
                alert("error");
            }
        });
    }
})

function baseInfoAdd() {
    var ctx = $("#ctx").val();
    var baseName = $("#className").val();
    var province = $("#s_province").val();
    var city = $("#s_city").val();
    var county = $("#s_county").val();
    var data = {baseName:baseName,province:province,city:city,county:county};
    $.ajax({
        url: ctx + "/personnelController/baseInfoInsert",
        type: 'POST', //POST
        async: true,    //或false,是否异步
        data: data,
        dataType: 'text',
        success: function (data) {
        },
        error: function () {
            console.log("服务器错误，保存失败")
        }
    });
}

