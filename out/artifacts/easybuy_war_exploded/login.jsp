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
<div id="register" class="wrap">
    <div class="shadow">
        <em class="corner lb"></em>
        <em class="corner rt"></em>
        <div class="box">
            <h1>欢迎回到易买网</h1>
            <form id="loginForm" method="post" action="user?action=login" onsubmit="return checkForm(this)">
                <table>
                    <tr>
                        <td class="field">用户名：</td>
                        <td><input class="text" type="text" name="userName" onfocus="FocusItem(this)"
                                   onblur="CheckItem(this);"/><span></span></td>
                    </tr>
                    <tr>
                        <td class="field">登录密码：</td>
                        <td><input class="text" type="password" id="passWord" name="passWord" onfocus="FocusItem(this)"
                                   onblur="CheckItem(this);"/><span></span></td>
                    </tr>
                    <tr>
                        <td class="field">验证码：</td>
                        <td><input class="text verycode" type="text" name="veryCode" onfocus="FocusItem(this)"
                                   onblur="CheckItem(this);"/><img id="veryCode" src="PictureCheckCode"/><span></span>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><label class="ui-green"><input type="submit" name="submit" value="立即登录"/></label></td>
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
