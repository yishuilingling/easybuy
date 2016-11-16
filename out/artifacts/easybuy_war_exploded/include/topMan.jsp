<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="../scripts/function-manage.js"></script>
</head>
<body>
<div id="header" class="wrap">
    <div id="logo"><img src="../images/logo.gif" /></div>
    <div class="help"><a href="../pca">返回前台页面</a></div>
    <div class="navbar">
        <ul class="clearfix">
            <li class="current"><a href="javascript:insert();">首页</a></li>
            <li><a href="javascript:insert1();">用户</a></li>
            <li><a href="javascript:insert2();">商品</a></li>
            <li><a href="javascript:insert3();">订单</a></li>
            <li><a href="javascript:insert4();">留言</a></li>
            <li><a href="javascript:insert5();">新闻</a></li>
            <li><a href="javascript:insert6();">公告</a></li>
        </ul>
    </div>
</div>
<div id="childNav">
    <div class="welcome wrap">
        <ul id="ur_id">
            <li><a href="/user?action=showUsers">用户管理</a></li>
            <li><a href="/pca?action=list">分类管理</a></li>
            <li><a href="/manageProduct?action=showProducts">商品管理</a></li>
            <li><a href="/order?action=list">订单管理</a></li>
            <li><a href="/comment?action=malist">留言管理</a></li>
            <li><a href="/news?action=malist">新闻管理</a></li>
            <li><a href="/announces?action=manlist">公告管理</a></li>
        </ul>
        <%
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            String date=df.format(new Date());
        %>
        管理员 ${user.euUserName}您好，今天<%=date %>，欢迎回到管理后台。
    </div>
</div>
<div id="position" class="wrap">
    您现在的位置：<a href="index.jsp">易买网</a> &gt; 管理后台
</div>
</body>
</html>
