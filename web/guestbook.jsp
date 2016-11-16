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
	您现在的位置：<a href="pca">易买网</a> &gt; 在线留言
</div>
<div id="main" class="wrap">
<jsp:include page="include/left.jsp"/>
	<div class="main">
		<div class="guestbook">
			<h2>全部留言</h2>
			<ul>
				<c:forEach var="comment" items="${commentList}">
				<li>
					<dl>
						<dt>${comment.ecContent}</dt>
						<dd class="author">网友：${comment.ecNickName} <span class="timer">${comment.ecCreateTime}</span></dd>
						<c:if test="${comment.ecReply==null}"><dd></dd></c:if>
						<c:if test="${comment.ecReply!=null}"><dd>${comment.ecReply}</dd></c:if>
					</dl>
				</li>
					</c:forEach>
			</ul>
			<div class="clear"></div>
			<div class="pager">
				<ul class="clearfix">
					<li><a href="#">上一页</a></li>
					<li class="current">1</li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#">下一页</a></li>
				</ul>
			</div>
			<div id="reply-box">
				<form action="comment">
					<table>
						<tr>
							<td><input type="hidden"  name="action" value="add" /></td>
						</tr>
						<tr>
							<td class="field">昵称：</td>
							<td><input class="text" type="text" name="guestName" value="${user.euUserName}"/></td>
						</tr>
						<tr>
							<td class="field">留言内容：</td>
							<td><textarea name="guestContent"></textarea></td>
						</tr>
						<tr>
							<td></td>
							<td><label class="ui-blue"><input type="submit" name="submit" value="提交留言" /></label></td>
						</tr>
					</table>
				</form>
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
