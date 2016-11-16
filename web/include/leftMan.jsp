<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="menu-mng" class="lefter">
    <div class="box">
        <dl>
            <dt>用户管理</dt>
            <dd><em><a href="/manage/user-add.jsp">新增</a></em><a href="../user?action=showUsers">用户管理</a></dd>
            <dt>商品信息</dt>
            <dd><em><a href="/manage/productClass-add.jsp">新增</a></em><a href="../pca?action=${"list"}">分类管理</a></dd>
            <dd><em><a href="/manage/product-add.jsp">新增</a></em><a href="../manageProduct?action=showProducts">商品管理</a></dd>
            <dt>订单管理</dt>
            <dd><a href="../order?action=${"list"}">订单管理</a></dd>
            <dt>留言管理</dt>
            <dd><a href="/comment?action=${"malist"}">留言管理</a></dd>
            <dt>新闻管理</dt>
            <dd><em><a href="/manage/news-add.jsp">新增</a></em><a href="../news?action=${"malist"}">新闻管理</a></dd>
            <dt>公告管理</dt>
            <dd><em><a href="/manage/announce-add.jsp">新增</a></em><a href="../announces?action=${"manlist"}">公告管理</a></dd>
        </dl>
    </div>
</div>
</body>
</html>
