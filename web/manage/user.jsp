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
		<h2>用户管理</h2>
		<div class="manage">
			<div class="pager">
				<ul class="clearfix">
					<c:if test="${pageIndex-1!=0}">
						<li><a href="/user?action=showUsers&pageIndex=${pageIndex-1}">上一页</a></li>
					</c:if>
					<c:forEach var="page" begin="1" end="${totalPage}">
						<c:if test="${page==1}">
							<li><a href="/user?action=showUsers&pageIndex=1">首页</a></li>
						</c:if>
						<c:if test="${page!=1&&page!=totalPage}">
							<li><a href="/user?action=showUsers&pageIndex=${page}">${page}</a></li>
						</c:if>
						<c:if test="${page==totalPage}">
							<li><a href="/user?action=showUsers&pageIndex=${totalPage}">末页</a></li>
						</c:if>
					</c:forEach>
					<c:if test="${pageIndex+1<=totalPage}">
						<li><a href="/user?action=showUsers&pageIndex=${pageIndex+1}">下一页</a></li>
					</c:if>
				</ul>
			</div>
			<table class="list">
				<tr>
					<th>ID</th>
					<th>姓名</th>
					<th>性别</th>
					<th>出生日期</th>
					<th>Email</th>
					<th>地址</th>
					<th>操作</th>
				</tr>
				<c:forEach var="user" items="${userList}">
				<tr>
					<td class="first w4 c">${user.euUserId}</td>
					<td class="w1 c">${user.euUserName}</td>
					<td class="w2 c">${user.euUserSex}</td>
					<td>${user.euBrithday}</td>
					<td class="w4 c">${user.euEmail}</td>
					<td class="w4 c">${user.euAddress}</td>
					<td class="w1 c"><a href="user?action=showUser&userId=${user.euUserId}">修改</a> <a href="javascript:Delete(${user.euUserId});">删除</a></td>
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
