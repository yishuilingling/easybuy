<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/function.js"></script>
</head>
<body>
<jsp:include page="include/top.jsp"/>
<div id="register" class="wrap">
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		<div class="box">
			<h1>欢迎注册易买网</h1>
			<ul class="steps clearfix">
				<li class="current"><em></em>填写注册信息</li>
				<li class="last"><em></em>注册成功</li>
			</ul>
			<form id="regForm" method="post" action="user?action=register" onsubmit="return checkForm(this);">
				<table>
					<tr>
						<td class="field">用户名：</td>
						<td><input class="text" type="text" name="userName" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span></td>
					</tr>
					<tr>
						<td class="field">登录密码：</td>
						<td><input class="text" type="password" id="passWord" name="passWord" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span></td>
					</tr>
					<tr>
						<td class="field">确认密码：</td>
						<td><input class="text" type="password" name="rePassWord" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span></td>
					</tr>
					<tr>
						<td class="field">性别：</td>
						<td>
							<input type="radio" name="sex" value="男"/>男
							<input type="radio" name="sex" value="女" checked="checked"/>女
						</td>
					<tr>
						<td class="field">出生日期：</td>
						<td>
							<input type="text" name="birthday" onfocus="FocusItem(this)" onblur="CheckItem(this);">
						</td>
					</tr>
					<tr>
						<td class="field">手机号码：</td>
						<td><input type="text" class="text" name="mobile" onfocus="FocusItem(this)" onblur="CheckItem(this);"/></td>
					</tr>
					<tr>
						<td class="field">邮箱：</td>
						<td><input type="text" class="text" name="email" onfocus="FocusItem(this)" onblur="CheckItem(this);"/></td>
					</tr>
					<tr>
						<td class="field">身份证号：</td>
						<td><input type="text" class="text" name="identityCode"onfocus="FocusItem(this)" onblur="CheckItem(this);"/></td>
					</tr>
					<tr>
						<td class="field">地址：</td>
						<td><input type="text" class="text" name="address" onfocus="FocusItem(this)" onblur="CheckItem(this);"/></td>
					</tr>
					<tr>
						<td><input type="hidden" class="text" name="status" value="1" /></td>
					</tr>
					<tr>
						<td class="field">验证码：</td>
						<td><input class="text verycode" type="text" name="veryCode" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><img id="veryCode" src="PictureCheckCode" /><span></span></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-green"><input type="submit" name="submit" value="提交注册" /></label></td>
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
