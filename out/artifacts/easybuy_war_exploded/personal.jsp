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
    您现在的位置：<a href="pca">易买网</a> &gt; 个人信息
</div>
<div id="main" class="wrap">
    <jsp:include page="include/left.jsp"/>
    <div class="manage">
        <form action="user?action=personUpdate&userId=${user.euUserId}" method="post">
            <table class="form">
                <tr>
                    <td class="field">用户ID：</td>
                    <td>${user.euUserId}</td>
                </tr>
                <tr>
                    <td class="field">姓名：</td>
                    <td><input type="text" class="text" name="name" value="${user.euUserName}"/></td>
                </tr>
                <tr>
                    <td class="field">密码：</td>
                    <td><input type="text" class="text" name="passWord" value="${user.euUserPassword}"/></td>
                </tr>
                <tr>
                    <td class="field">性别：</td>
                    <c:if test="${user.euUserSex=='男'}">
                    <td>
                        <input type="radio" name="sex" value="男" checked="checked"/>男
                        <input type="radio" name="sex" value="女"/>女
                    </td>
                    </c:if>
                    <c:if test="${user.euUserSex=='女'}">
                    <td>
                        <input type="radio" name="sex" value="男"/>男
                        <input type="radio" name="sex" value="女" checked="checked"/>女
                    </td>
                    </c:if>
                <tr>
                    <td class="field">出生日期：</td>
                    <td>
                        <input type="text" name="birthday" value="${user.euBrithday}">
                    </td>
                </tr>
                <tr>
                    <td class="field">手机号码：</td>
                    <td><input type="text" class="text" name="mobile" value="${user.euMobile}"/></td>
                </tr>
                <tr>
                    <td class="field">邮箱：</td>
                    <td><input type="text" class="text" name="email" value="${user.euEmail}"/></td>
                </tr>
                <tr>
                    <td class="field">身份证号：</td>
                    <td><input type="text" class="text" name="identityCode" value="${user.euIdentityCode}"/></td>
                </tr>
                <tr>
                    <td class="field">送货地址：</td>
                    <td><input type="text" class="text" name="address" value="${user.euAddress}"/></td>
                </tr>
                <tr>
                    <td><input type="hidden" class="text" name="status" value="${user.euStatus}"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><label class="ui-blue"><input type="submit" name="submit" value="修改"/></label></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<div id="footer">
    Copyright &copy; 2010 北风教育 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
