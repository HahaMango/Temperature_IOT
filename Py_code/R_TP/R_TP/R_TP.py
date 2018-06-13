import http.client as HC
import urllib.parse
import datetime as DT
import time

def sendTp():
    tfile = open("/sys/bus/w1/devices/28-041700d63dff/w1_slave")
    #读取文件所有内容
    text = tfile.read()
    #关闭文件
    tfile.close()
    #用换行符分割字符串成数组，并取第二行
    secondline = text.split("\n")[1]
    #用空格分割字符串成数组，并取最后一个，即t=23000
    temperaturedata = secondline.split(" ")[9]
    #取t=后面的数值，并转换为浮点型
    temperature = float(temperaturedata[2:])
    #转换单位为摄氏度
    temperature = temperature / 1000
    #打印值

    #构造http请求
    params = urllib.parse.urlencode({'tp': temperature, 'local': 'china'})
    print(params)
    headers = {"Content-type": "application/x-www-form-urlencoded",
           "Accept": "text/plain"}
    sendData = HC.HTTPConnection("localhost",port=8080)
    sendData.request("POST","/send",params,headers)
    r2 = sendData.getresponse()
    #print(r2.read())

def test():
    print('test')

t1 = None
while True:
    if t1 is None:
        test()
        t1 = DT.datetime.now()
    else:
        t2 = DT.datetime.now()
        dt = t2-t1
        if dt.seconds>3600:      
            t1=DT.datetime.now()
            test()
        time.sleep(2);
