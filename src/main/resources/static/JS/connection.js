var TPJson = 'sdfsdf';

$("#cnt").click(function () {
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
    if (year != '' && month != '' && day != '') {
        $.getJSON("http://localhost:8080/jsoninterface", { start: startTime, daynumber: number }, function (json) {
            TPJson = json;
            name();
        });
    }
});

