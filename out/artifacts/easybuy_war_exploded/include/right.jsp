<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="me">


    <div class="news-list">
        <h4>最新公告</h4>
        <ul>

            <c:forEach var="announce" items="${announceList}">
                <li><a href="announces?id=${announce.eaId}&action=${"details"}&user=${user.euUserId}"
                       target="_blank">${announce.eaTitle}</a></li>
            </c:forEach>

        </ul>
    </div>
    <div class="spacer"></div>
    <div class="news-list">
        <h4>新闻动态</h4>
        <ul>

            <c:forEach var="news" items="${newList}">
                <li><a href="news?id=${news.enId}&action=${"details"}&user=${user.euUserId}"
                       target="_blank">${news.enTitle}</a></li>
            </c:forEach>
        </ul>
    </div>
    </div>
</body>
</html>
