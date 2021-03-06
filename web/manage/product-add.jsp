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
	<jsp:include page="../include/leftMan.jsp"/>
	<div class="main">
		<h2>添加商品</h2>
		<div class="manage">
			<form action="/manageProduct?action=insert&productScan=0" method="post" >
				<table class="form">
					<tr>
						<td class="field">商品名称：</td>
						<td><input type="text" class="text" name="productName" /></td>
					</tr>
					<tr>
						<td class="field">所属分类：</td>
						<td>
							<select id="parentId" name="epcId">
								<option value="1" onclick="change()">图书音乐</option>
								<option value="2" onclick="change1()">百货</option>
							</select>
							<select id="chileId" name="epcChildId">
							</select>
						</td>
					</tr>
					<tr>
						<td class="field">商品描述：</td>
						<td><textarea  class="text" name="epDescription" ></textarea></td>
					</tr>
					<tr>
						<td class="field">商品图片：</td>
						<td><input type="file" class="text" name="photo" /></td>
					</tr>
					<tr>
						<td class="field">商品价格：</td>
						<td><input type="text" class="text tiny" name="productPrice" /> 元</td>
					</tr>
					<tr>
						<td class="field">库存：</td>
						<td><input type="text" class="text tiny" name="productStock"  /></td>
					</tr>
					<tr>
						<td class="field">是否特价：</td>
							<td>
								<input type="radio" name="discount" value="1" checked>是
								<input type="radio" name="discount" value="0" >否
							</td>
					</tr>

					<tr>
						<td class="field">  点击量：</td>
						<td >0</td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-blue"><input type="submit" name="submit" value="添加" /></label></td>
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
