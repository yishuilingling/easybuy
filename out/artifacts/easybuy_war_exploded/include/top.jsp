<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/12
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="../scripts/function.js"></script>
</head>
<body>
<div id="header" class="wrap">
    <div id="logo"><img src="images/logo.gif"/></div>
    <div class="help">
        <c:if test="${user==null}">
            <a href="login.jsp" class="shopping">购物车</a>
            <a href="register.jsp">注册</a>
            <p>欢迎来到易买网请<a href="login.jsp">登录</a></p>
        </c:if>
        <c:if test="${user!=null}">
            <span>Hi,<a href="personal.jsp"> ${user.euUserName}</a></span>
            <a href="order?action=${"person"}&userId=${user.euUserId}">我的订单</a>
            <a href="shopping.jsp" class="shopping">购物车</a>
            <a href="/comment?action=${"list"}">留言</a>
            <c:if test="${user.euStatus==2}">
                <a href="/manage/index.jsp">后台</a>
            </c:if>
            <a href="user?action=${"logout"}">退出</a>
        </c:if>
    </div>
    <div class="navbar">
        <ul class="clearfix">
            <li class="current"><a href="javascript:maTop(this);">首页</a></li>
            <li><a href="javascript:maTop1(this)">图书音像</a></li>
            <li><a href="javascript:maTop2(this)">百货</a></li>
        </ul>
    </div>
</div>
<div id="childNav">
    <div class="wrap">
        <ul class="clearfix" id="top">

            <li><a href="/pca?action=details&id=3&name=音像&parentName=图书音像">音像</a></li>

             <li><a href='/pca?action=details&id=4&name=图书&parentName=图书音像'>图书</a></li>
            <li><a href='/pca?action=details&id=5&name=运动健康&parentName=百货'>运动健康</a></li>
            <li><a href='/pca?action=details&id=6&name=服装&parentName=百货'>服装</a></li>
            <li><a href='/pca?action=details&id=7&name=家居&parentName=百货'>家居</a></li>
            <li><a href='/pca?action=details&id=8&name=美妆&parentName=百货'>美妆</a></li>
            <li><a href='/pca?action=details&id=9&name=母婴&parentName=百货'>母婴</a></li>
            <li><a href='/pca?action=details&id=10&name=食品&parentName=百货'>食品</a></li>
             <li><a href='/pca?action=details&id=11&name=手机数码&parentName=百货'>手机数码</a></li>
            <li><a href='/pca?action=details&id=12&name=家具首饰&parentName=百货'>家具首饰</a></li>
            <li><a href='/pca?action=details&id=13&name=手表饰品&parentName=百货'>手表饰品</a></li>
            <li><a href='/pca?action=details&id=14&name=鞋包&parentName=百货'>鞋包</a></li>
            <li><a href='/pca?action=details&id=15&name=家电&parentName=百货'>家电</a></li>
            <li><a href='/pca?action=details&id=16&name=电脑办公&parentName=百货'>电脑办公</a></li>
            <li><a href='/pca?action=details&id=17&name=玩具文具&parentName=百货'>玩具文具</a></li>
            <li><a href='/pca?action=details&id=18&name=汽车用品&parentName=百货'>汽车用品</a></li>

        </ul>
    </div>
</div>
</body>
</html>
