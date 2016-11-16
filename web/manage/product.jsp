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
		<h2>商品管理</h2>
		<div class="manage">
			<div class="pager">
				<ul class="clearfix">
					<c:if test="${pageIndex-1!=0}">
						<li><a href="/manageProduct?action=showProducts&pageIndex=${pageIndex-1}">上一页</a></li>
					</c:if>
					<c:forEach var="page" begin="1" end="${totalPage}">
						<c:if test="${page==1}">
							<li><a href="/manageProduct?action=showProducts&pageIndex=1">首页</a></li>
						</c:if>
						<c:if test="${page!=1&&page!=totalPage}">
							<li><a href="/manageProduct?action=showProducts&pageIndex=${page}">${page}</a></li>
						</c:if>
						<c:if test="${page==totalPage}">
							<li><a href="/manageProduct?action=showProducts&pageIndex=${totalPage}">末页</a></li>
						</c:if>
					</c:forEach>
					<c:if test="${pageIndex+1<=totalPage}">
						<li><a href="/manageProduct?action=showProducts&pageIndex=${pageIndex+1}">下一页</a></li>
					</c:if>
				</ul>
			</div>
			<table class="list">
				<tr>
					<th>ID</th>
					<th>商品名称</th>
					<%--<th>商品内容</th>--%>
					<th>商品价格</th>
					<th>商品库存</th>
					<%--<th>商品一级目录</th>--%>
					<%--<th>商品二级目录</th>--%>
					<%--<th>商品图片</th>--%>
					<th>是否特价</th>
					<th>商品点击量</th>
					<th>操作</th>
				</tr>
				<c:forEach var="product" items="${productList}">
				<tr>
					<td class="first w4 c">${product.epId}</td>
					<td class="thumb"><a href="product?id=${product.epId}&user=${user.euUserId}&action=${"details"}" target="_blank">${product.epName}</a></td>
					<td class="first w4 c">${product.epPrice}</td>
					<td class="first w4 c">${product.epStock}</td>
					<c:if test="${product.ep_discount==1}">
					<td class="first w4 c">是</td>
					</c:if>
					<c:if test="${product.ep_discount==0}">
						<td class="first w4 c">否</td>
					</c:if>
					<td class="first w4 c">${product.epScan}</td>
					<td class="w1 c"><a href="manageProduct?action=showProduct&productId=${product.epId}">修改</a> <a href="javascript:DeleteProduct(${product.epId});">删除</a></td>
				</tr>
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
