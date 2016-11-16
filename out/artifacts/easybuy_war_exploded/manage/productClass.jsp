<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>后台管理 - 易买网</title>
    <link type="text/css" rel="stylesheet" href="../css/style.css"/>
    <script type="text/javascript" src="../scripts/function-manage.js"></script>
</head>
<body>
<jsp:include page="../include/topMan.jsp"/>
<div id="main" class="wrap">
    <jsp:include page="../include/leftMan.jsp"/>
    <div class="main">
        <h2>分类管理</h2>
        <div class="manage">
            <table class="list">
                <tr>
                    <th>ID</th>
                    <th>分类名称</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="ca" items="${categoryList}">
                    <%--大类--%>
                    <c:if test="${ca.epcParentId == 0}">
                        <tr>
                            <td class="first w4 c">${ca.epcId}</td>
                            <td>${ca.epcName}</td>
                            <td class="w1 c"><a
                                    href="/manage/productClass-modify.jsp?id=${ca.epcId}&name=${ca.epcName}">修改</a>
                                <a href="javascript:deleteCa(${ca.epcId},${ca.epcParentId});">删除</a>
                            </td>
                        </tr>
                    </c:if>
                    <c:forEach var="ca_" items="${categoryList}">
                        <c:if test="${ca.epcId==ca_.epcParentId}">
                            <tr>
                                <td class="first w4 c">${ca_.epcId}</td>
                                <td class="childClass">${ca_.epcName}</td>
                                <td class="w1 c"><a  href="/manage/productClass-modify.jsp?id=${ca_.epcId}&name=${ca_.epcName}">修改</a>
                                    <a href="javascript:deleteCa(${ca_.epcId},${ca_.epcParentId});">删除</a>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:forEach>
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
