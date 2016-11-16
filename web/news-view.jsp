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
    您现在的位置：<a href="pca">易买网</a> &gt; 阅读新闻
</div>
<div id="main" class="wrap">
    <div class="left-side">
        <jsp:include page="include/right.jsp"/>
    </div>
    <div id="news" class="right-main">
        <h1>${news.enTitle}</h1>
        <div class="time">
            ${news.enCreateTime}
        </div>
        <div class="content">
            ${news.enContent}
        </div>
    </div>
    <div class="clear"></div>
</div>
<div id="footer">
    Copyright &copy; 2010 北风教育 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
