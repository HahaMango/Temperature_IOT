var TPJson;

$("#cnt").click(function () {
    $('#tp_draw').remove();
    $('#inner').append('<canvas id="tp_draw" height="685" width="970"><p>请更换支持H5的浏览器</p></canvas>')
    drawTpView(TOP, BLOW, LEFT, RIGHT, NUMBER, CENTER);
    var year = $("#one").children("input[name='year']");
    var month = $("#one").children("input[name='month']");
    var day = $("#one").children("input[name='day']");

    year = year.val();
    month = parseInt(month.val());
    day = parseInt(day.val());
    month = (month < 9) ? "0" + month : month;
    day = (day < 9) ? "0" + day : day;

    var startTime = year + "-" + month + "-" + day + " 00:00:00";

    //获取显示的日期数
    var number = $("#two").children("select[name='daynumber']");
    number=number.val();

    if(number!=1){
        alert('该功能未开发');
    }
    else if (year != '' && month != '' && day != '') {
        $.getJSON("jsoninterface", { start: startTime, daynumber: number }, function (json) {
            TPJson = json;
            name();
        });
    }
});

