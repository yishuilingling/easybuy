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
        <h2>修改分类</h2>
        <div class="manage">
            <form action="../pca">
                <table class="form">
                    <tr>
                        <td class="field">分类ID：</td>
                        <td><input type="text" class="text" name="classId" value="${param.id}"/></td>
                    </tr>
                    <tr>
                        <td class="field">父分类：</td>
                        <td>
                            <select name="parentId">
                                <%--<option value="0" selected="selected">根栏目</option>--%>
                                <c:forEach var="ca" items="${categoryList}">
                                    <c:if test="${ca.epcParentId == 0}">
                                        <option value="${ca.epcId}">${ca.epcName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="field">分类名称：</td>
                        <td><input type="text" class="text" name="className" value="${param.name}"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><label class="ui-blue"><input type="submit" name="action" value="更新"/></label></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div class="clear"></div>
</div>
<div id="footer">
    Copyright &copy; 2010 北风教育 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
