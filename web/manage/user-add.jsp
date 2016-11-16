<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
	<jsp:include page="../include/leftMan.jsp"/>
	<div class="main">
		<h2>新增用户</h2>
		<div class="manage">
				<form action="/user?action=insert" method="post">
					<table class="form">
						<%--<tr>--%>
							<%--<td class="field">用户ID：</td>--%>
							<%--<td><input type="text" class="text" name="userId" /></td>--%>
						<%--</tr>--%>
						<tr>
							<td class="field">姓名：</td>
							<td><input type="text" class="text" name="name"  /></td>
						</tr>
						<tr>
							<td class="field">密码：</td>
							<td><input type="text" class="text" name="passWord"  /></td>
						</tr>
						<tr>
							<td class="field">性别：</td>
							<td>
								<input type="radio" name="sex" value="男" checked="checked" />男
								<input type="radio" name="sex" value="女" />女
							</td>
						<tr>
							<td class="field">出生日期：</td>
							<td>
								<input type="text" name="birthday"/>
							</td>
						</tr>
						<tr>
							<td class="field">手机号码：</td>
							<td><input type="text" class="text" name="mobile"  /></td>
						</tr>
						<tr>
							<td class="field">邮箱：</td>
							<td><input type="text" class="text" name="email"  /></td>
						</tr>
						<tr>
							<td class="field">身份证号：</td>
							<td><input type="text" class="text" name="identituCode"  /></td>
						</tr>
						<tr>
							<td class="field">送货地址：</td>
							<td><input type="text" class="text" name="address"  /></td>
						</tr>
						<tr>
							<td class="field">权限：</td>
							<td><input type="text" class="text" name="status"  /></td>
						</tr>
						<tr>
							<td></td>
							<td><label class="ui-blue"><input type="submit" name="submit" value="增加" /></label></td>
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
