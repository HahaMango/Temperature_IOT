function name() {
    if (TPJson.dayNumber == 1) {
        var viewWidth = 970-RIGHT-LEFT;
        var widthStep = viewWidth/24;
        for(var i = 0;i<TPJson.jsonList.length;i++){
            var tpvalue = parseInt(TPJson.jsonList[i].temperature);
            var tpdate = TPJson.jsonList[i].date.split(" ")[1];
            var tphour = tpdate.split(":")[0]+"'";
            drawTpData(LEFT,CENTER,(i+1)*widthStep,tpvalue,tphour);
        }
    } else if (TPJson.dayNumber == 7) {
        
    } else if (TPJson.dayNumber == 15) {

    }
}