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
        <h2>添加公告</h2>
    <div class="manage">
        <form action="../announces">
            <table class="form"  >
                <tr>
                    <td><input type="hidden"  name="action" value="add" /></td>
                </tr>
                <tr>
                    <td class="field">公告标题：</td>
                    <td><input type="text" class="text" name="title" value="" /></td>
                </tr>
                <tr>
                    <td class="field">公告内容：</td>
                    <td><textarea name="content"></textarea></td>
                </tr>
                <tr>
                    <td></td>
                    <td><label class="ui-green" ><input type="submit" name="submit" value="添加" /></label></td>
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
