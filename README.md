# 树莓派实现温度监控
***
通过树莓派读取DS18B20温度传感器的值，在固定的时间把温度的值上传到服务器上并放入数据库（MYSQL）中，在web端可以请求该数据库中的温度值并显示在前端页面中来，web端可以显示1天，7天，15天，一个月，半年，一年的温度。

##详细的技术说明
在树莓派中通过固定时间发送HTTP请求到相应的请求链接来提交数据到服务器MYSQL中，提交成功或失败都会返回相应的字符串到树莓派中。

服务器提供一个RequestMapping来返回一个前端页面来显示温度值（只返回页面），在请求了这个前端页面后，在前端页面的JS代码中发送一个请求温度值的请求到服务器中，服务器返回相应时间段内的温度值列表的JSON格式，然后前端页面负责处理这个返回的JSON数据来最终显示温度数值。这个JSON格式的列表与MYSQL内的温度表的列相对应。

## 组织架构
因为考虑到要减少项目开发周期，后台将用`Spring Boot`搭建，应用服务器为Tomcat。

数据库 `MYSQL`

### 控制层
***
控制层采用三个`RequestMapping`来完成请求的导向。

第一个映射到提交数据`public String send(String tp)`

第二个返回显示温度的前端页面`public String tpPage()`

第三个返回相应时间段的JSON格式的温度列表`public String getJSON(Date start,Date end)`

该项目不提供服务层，控制层直接和持久层松耦合。

### 持久层
***
持久层采用JDBC连接数据库，数据库连接池为Spring Boot默认使用的Tomcat JDBC连接池。

对MYSQL数据库的访问使用Spirng的JDBCTemplate来简化原本JDBC复杂的数据库访问。

对于持久层我们定义一个接口，接口有两个方法，一个是对插入温度数值在数据库中，第二个是获取对相应时间段的温度值的列表。

`public boolen insert(float tp)`

`public List<> getTp(Date start, Date end)`

在上面的第二个接口方法中返回的列表的内容为一个与数据库表中的列有相对应的私有成员的类，该类定义为

```
class tpRowMapping{
    //与数据库表的列相对应的私有成员
}
```

为此还要有一个转换为JSON格式的类，该类中有一个`count`成员，还有一个列表成员，列表成员存放`getTp()`方法返回的列表，该类定义为

```
class tp2JSON{
    private int count;

    private List<tpRowMapping> jsonList;
}
```

该类将最终转换成JSON格式并发送到客户端，具体将由客户端处理。

### WEB端设计
***