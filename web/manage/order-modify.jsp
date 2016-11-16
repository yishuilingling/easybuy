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
		<h2>修改订单</h2>
		<div class="manage">
			<form action="../order">
				<table class="form">
					<tr>
						<td class="field">订单ID：</td>
						<td><input type="text" class="text" name="orderId" value="${param.id}" readonly="readonly" /></td>
					</tr>
					<tr>
						<td class="field">订购人姓名：</td>
						<td><input type="text" class="text" name="name" value="${param.name}" /></td>
					</tr>
					<tr>
						<td class="field">订购人地址：</td>
						<td><input type="text" class="text" name="address" value="${param.address}" /></td>
					</tr>
					<tr>
						<td class="field">总金额：</td>
						<td><input type="text" class="text" name="cost" value="${param.cost}" /></td>
					</tr>
					<tr>
						<td class="field">订单状态：</td>
						<td><label><input type="radio" name="status" value="1" />待发货</label>
							<label><input type="radio" name="status" value="2" checked="checked"/>审核通过</label>
							<label><input type="radio" name="status" value="3" />配货</label>
							<label><input type="radio" name="status" value="4" />送货中</label>
							<label><input type="radio" name="status" value="5" />收货并确认</label>
						</td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-blue"><input type="submit" name="action" value="更新" /></label></td>
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
