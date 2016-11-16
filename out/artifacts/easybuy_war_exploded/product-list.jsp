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
<div id="position" class="wrap">
	您现在的位置：<a href="pca">易买网</a> &gt;<a href="product-list.jsp">${parentName}</a>&gt;${name}

</div>
<div id="main" class="wrap">
	<jsp:include page="include/left.jsp"/>
	<div class="main">
		<div class="product-list">
			<h2>全部商品</h2>
			<%--<div class="pager">--%>
				<%--<ul class="clearfix">--%>
					<%--<c:if test="${pageIndex-1!=0}">--%>
						<%--<li><a href="/pca?action=details&pageIndex=${pageIndex-1}">上一页</a></li>--%>
					<%--</c:if>--%>
					<%--<c:forEach var="page" begin="1" end="${totalPage}">--%>
						<%--<c:if test="${page==1}">--%>
							<%--<li><a href="/pca?action=details&pageIndex=1">首页</a></li>--%>
						<%--</c:if>--%>
						<%--<c:if test="${page!=1&&page!=totalPage}">--%>
							<%--<li><a href="/pca?action=details&pageIndex=${page}">${page}</a></li>--%>
						<%--</c:if>--%>
						<%--<c:if test="${page==totalPage}">--%>
							<%--<li><a href="/pca?action=details&pageIndex=${totalPage}">末页</a></li>--%>
						<%--</c:if>--%>
					<%--</c:forEach>--%>
					<%--<c:if test="${pageIndex+1<=totalPage}">--%>
						<%--<li><a href="/pca?action=details&pageIndex=${pageIndex+1}">下一页</a></li>--%>
					<%--</c:if>--%>
				<%--</ul>--%>
			<%--</div>--%>
			<div class="clear"></div>
			<ul class="product clearfix">
				<c:forEach var="pro" items="${proDetailsList}">
					<li>
						<dl>
							<dt><a href="/product?id=${pro.epId}&action=details" target="_blank"><img src="${pro.epFileName}" /></a></dt>
							<dd class="title"><a href="/product?id=${pro.epId}&action=details" target="_blank">${pro.epName}</a></dd>
							<dd class="price">￥${pro.epPrice}</dd>
						</dl>
					</li>
				</c:forEach>
			</ul>
			<div class="clear"></div>
			<%--<div class="pager">--%>
				<%--<ul class="clearfix">--%>
					<%--<c:if test="${pageIndex-1!=0}">--%>
						<%--<li><a href="/pca?action=details&pageIndex=${pageIndex-1}">上一页</a></li>--%>
					<%--</c:if>--%>
					<%--<c:forEach var="page" begin="1" end="${totalPage}">--%>
						<%--<c:if test="${page==1}">--%>
							<%--<li><a href="/pca?action=details&pageIndex=1">首页</a></li>--%>
						<%--</c:if>--%>
						<%--<c:if test="${page!=1&&page!=totalPage}">--%>
							<%--<li><a href="/pca?action=details&pageIndex=${page}">${page}</a></li>--%>
						<%--</c:if>--%>
						<%--<c:if test="${page==totalPage}">--%>
							<%--<li><a href="/pca?action=details&pageIndex=${totalPage}">末页</a></li>--%>
						<%--</c:if>--%>
					<%--</c:forEach>--%>
					<%--<c:if test="${pageIndex+1<=totalPage}">--%>
						<%--<li><a href="/pca?action=details&pageIndex=${pageIndex+1}">下一页</a></li>--%>
					<%--</c:if>--%>
				<%--</ul>--%>
			<%--</div>--%>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010 北风教育 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
