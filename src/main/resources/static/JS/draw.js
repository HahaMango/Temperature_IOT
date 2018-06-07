drawTitle();
var TOP = 60;
var BLOW = 70;
var LEFT = 70;
var RIGHT = 50;
var NUMBER = 5;
var CENTER = 450;

drawTpView(TOP, BLOW, LEFT, RIGHT, NUMBER, CENTER);

//绘制标题函数
function drawTitle() {
    var canvas_m = document.getElementById('titlecanvas');
    var ctx = canvas_m.getContext("2d");

    ctx.strokeStyle = "rgb(0,0,200)";
    ctx.fillStyle = "#FF7373";
    ctx.lineWidth = 2;
    ctx.lineJoin = 'round';
    ctx.beginPath();
    ctx.moveTo(0, 0);
    ctx.quadraticCurveTo(30, 1, 40, 40);
    ctx.quadraticCurveTo(45, 75, 80, 80);
    ctx.lineTo(320, 80);
    ctx.quadraticCurveTo(355, 75, 360, 40);
    ctx.quadraticCurveTo(370, 1, 400, 0);
    
    ctx.fill();

}

//绘制基础温度显示底面
function drawTpView(top, blow, left, right, number, center) {
    var canvas_center = document.getElementById('tp_draw');
    var ctx_c = canvas_center.getContext("2d");

    ctx_c.strokeStyle = "rgb(129,129,129)";
    ctx_c.lineWidth = 2;
    ctx_c.beginPath();
    ctx_c.moveTo(left, center);
    ctx_c.lineTo(970 - right, center);
    ctx_c.moveTo(left, top);
    ctx_c.lineTo(left, 685 - blow);

    var centerTp=0;
    ctx_c.font="20px Arial";
    ctx_c.fillText(centerTp.toString(),left-30,center);

    ctx_c.stroke();

    ctx_c.beginPath();
    dive = center / number;
    ctx_c.strokeStyle = "rgb(200,200,200)";
    for (i = 1; i <= number; i++) {
        ctx_c.moveTo(left + 10, center - i * dive);
        ctx_c.lineTo(970 - right-10, center - i * dive);
        var tp = i*10;
        ctx_c.fillText(tp.toString(),left-40,center-i*dive);
    }
    for (a = 1; ((center + a * dive) < (685 - blow)); a++) {
        ctx_c.moveTo(left + 10, center + a * dive);
        ctx_c.lineTo(970 - right-10, center + a * dive);
        var tp = -10*a;
        ctx_c.fillText(tp.toString(),left-40,center+a*dive);
    }
    ctx_c.stroke();

}

//绘制温度数据函数
function drawTpData(left,center,offset,tpvalue,tplable) {
    var tp_offset=(tpvalue*dive/10)-5;


    var DataCanvas = document.getElementById("tp_draw");
    var DataContext = DataCanvas.getContext("2d");

    DataContext.strokeStyle = "rgb(49,49,49)";
    DataContext.fillStyle = "#rgb(49,49,49)";

    DataContext.lineWidth=2;
    DataContext.beginPath();
    DataContext.moveTo(left+offset,center);
    DataContext.lineTo(left+offset,center-tp_offset);
    DataContext.moveTo(left+offset+5,center-tp_offset-5);
    DataContext.arc(left+offset,center-tp_offset-5,5,0,2*Math.PI,true);
    DataContext.fillText(tpvalue,left+offset,center-tp_offset-30);
    DataContext.fillText(tplable,left+offset,center+30);

    DataContext.stroke();
    DataContext.fill();
}