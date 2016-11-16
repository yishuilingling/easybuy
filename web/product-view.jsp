<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>易买网 - 首页</title>
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
    <script type="text/javascript" src="scripts/function.js"></script>
</head>
<body>
<jsp:include page="include/top.jsp"/>
<div id="position" class="wrap">
    您现在的位置：<a href="pca">易买网</a> &gt; <a href="product-list.jsp">图书音像</a> &gt; 图书
</div>
<div id="main" class="wrap">
    <jsp:include page="include/left.jsp"/>
    <div id="Product" class="main">
        <h1>${Product.epName}</h1>
        <div class="infos">
            <div class="thumb"><img src="${Product.epFileName}" width=200 height=200/></div>
            <div class="buy">
                <p>商城价：<span class="price">￥${Product.epPrice}</span></p>
                <p>名称：${Product.epName}</p>
                <c:if test="${Product.epStock==0}">
                    <p>库 存：无货</p>
                </c:if>
                <c:if test="${Product.epStock<10}">
                    <p>库 存：${Product.epStock}（紧张）</p>
                </c:if>
                <c:if test="${Product.epStock>9}">
                    <p>库 存：有货</p>
                </c:if>
                <p>点击量：${Product.epScan}</p>
                <div class="button">
                    <c:if test="${user==null}">
                        <input type="button" name="button" value="" onclick="goBuy1()"/>
                        <a href="login.jsp">放入购物车</a>
                    </c:if>
                    <c:if test="${user!=null}">
                        <input type="button" name="button" value="" onclick="goBuy(1,${Product.epId},${user.euUserId})"/>
                        <a href="shop?id=${Product.epId}&user=${user.euUserId}">放入购物车</a>
                    </c:if>
                </div>
            </div>
            <div class="clear"></div>
        </div>
        <div class="introduce">
            <h2><strong>商品详情</strong></h2>
            <div class="text">
                ${Product.epDescription}
            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>
<div id="footer">
    Copyright &copy; 2010 北风教育 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
