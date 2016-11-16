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
		<h2>留言管理</h2>
		<div class="manage">

			<table class="list">
				<tr>
					<th>ID</th>
					<th>姓名</th>
					<th>留言内容</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
                <c:forEach var="comment" items="${commentList}">
				<tr>
					<td class="first w4 c">${comment.ecId}</td>
					<td class="w1 c">${comment.ecNickName}</td>
					<td>${comment.ecContent}</td>
                    <c:if test="${comment.ecReply==null}">
					<td class="w1 c">待回复</td>
					<td class="w1 c"><a href="manage/guestbook-modify.jsp?ecId=${comment.ecId}&ecName=${comment.ecNickName}&ecComment=${comment.ecContent}">回复</a> <a href="javascript:DeleteComment(${comment.ecId});">删除</a></td>
					</c:if>
					<c:if test="${comment.ecReply!=null}">
						<td class="w1 c">已回复</td>
						<td class="w1 c"><a href="manage/guestbook-modify.jsp?ecId=${comment.ecId}&ecName=${comment.ecNickName}&ecComment=${comment.ecContent}&ecRecply=${comment.ecReply}">修改</a> <a href="javascript:DeleteComment(${comment.ecId});">删除</a></td>
					</c:if>
				</tr>
					</c:forEach>
			</table>
			<div class="pager">
				<ul class="clearfix">
					<c:if test="${pageIndex-1!=0}">
						<li><a href="/comment?action=${"malist"}&pageIndex=${pageIndex-1}">上一页</a></li>
					</c:if>
					<c:forEach var="page" begin="1" end="${totalPage}">
						<c:if test="${page==1}">
							<li><a href="/comment?action=${"malist"}&pageIndex=1">首页</a></li>
						</c:if>
						<c:if test="${page!=1&&page!=totalPage}">
							<li><a href="/comment?action=${"malist"}&pageIndex=${page}">${page}</a></li>
						</c:if>
						<c:if test="${page==totalPage}">
							<li><a href="/comment?action=${"malist"}&pageIndex=${totalPage}">末页</a></li>
						</c:if>
					</c:forEach>
					<c:if test="${pageIndex+1<=totalPage}">
						<li><a href="/comment?action=${"malist"}&pageIndex=${pageIndex+1}">下一页</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010 北风教育 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
