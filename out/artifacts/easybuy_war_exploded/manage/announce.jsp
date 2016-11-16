<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>后台管理 - 易买网</title>
    <link type="text/css" rel="stylesheet" href="../css/style.css" />
    <script type="text/javascript" src="../scripts/function-manage.js"></script>
</head>
<body>
<jsp:include page="../include/topMan.jsp"/>
<div id="main" class="wrap">
    <div id="menu-mng" class="lefter">
        <jsp:include page="../include/leftMan.jsp"/>
    </div>
    <div class="main">
        <h2>公告管理</h2>
        <div class="manage">
            <div class="pager">
                <ul class="clearfix">
                    <c:if test="${pageIndex-1!=0}">
                        <li><a href="/announces?action=manlist&pageIndex=${pageIndex-1}">上一页</a></li>
                    </c:if>
                    <c:forEach var="page" begin="1" end="${totalPage}">
                        <c:if test="${page==1}">
                            <li><a href="/announces?action=manlist&pageIndex=1">首页</a></li>
                        </c:if>
                        <c:if test="${page!=1&&page!=totalPage}">
                            <li><a href="/announces?action=manlist&pageIndex=${page}">${page}</a></li>
                        </c:if>
                        <c:if test="${page==totalPage}">
                            <li><a href="/announces?action=manlist&pageIndex=${totalPage}">末页</a></li>
                        </c:if>
                    </c:forEach>
                    <c:if test="${pageIndex+1<=totalPage}">
                        <li><a href="/announces?action=manlist&pageIndex=${pageIndex+1}">下一页</a></li>
                    </c:if>
                </ul>
            </div>
            <table class="list">
                <tr>
                    <th>ID</th>
                    <th>公告标题</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="announce" items="${announceList}">
                    <tr>
                        <td class="w1 c">${announce.eaId}</td>
                        <td>${announce.eaTitle}</td>
                        <td class="w1 c"><a href="manage/announce-modify.jsp?eaId=${announce.eaId}&eaTitle=${announce.eaTitle}&eaContent=${announce.eaContent}">修改</a> <a href="javascript:DeleteAnnounce(${announce.eaId});">删除</a></td>
                    </tr>
                </c:forEach>
                </tr>
            </table>
        </div>
    </div>
    <div class="clear"></div>
</div>
<div id="footer">
    Copyright &copy; 2010 北风教育 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
