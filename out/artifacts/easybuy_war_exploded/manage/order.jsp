<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>后台管理 - 易买网</title>
    <link type="text/css" rel="stylesheet" href="../css/style.css"/>
    <script type="text/javascript" src="../scripts/function-manage.js"></script>
</head>
<body>
<jsp:include page="../include/topMan.jsp"/>
<div id="main" class="wrap">
    <jsp:include page="../include/leftMan.jsp"/>
    <div class="main">
        <h2>订单管理</h2>
        <div class="manage">
            <div class="search">
                <form action="order">
                    订单号：<input type="text" class="text" name="orderId"/>
                    订货人：<input type="text" class="text" name="userName"/>
                    <label class="ui-blue"><input type="submit" name="action" value="查询"/></label>
                </form>
            </div>
            <div class="spacer"></div>
            <table class="list">
                <tr>
                    <th>订单号</th>
                    <th>姓名</th>
                    <th>发货地址</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="order" items="${orderList}">
                    <tr>
                        <td class="first w4 c"><a href="order?action=${"detail"}&orderId=${order.eoId}"> ${order.eoId}</a></td>
                        <td class="w1 c">${order.eoUserName}</td>
                        <td>${order.eoUserAddress}</td>
                        <c:if test="${order.eoStatus==1}">
                            <td class="w1 c">发货</td>
                        </c:if>
                        <c:if test="${order.eoStatus==2}">
                            <td class="w1 c">审核通过</td>
                        </c:if>
                        <c:if test="${order.eoStatus==3}">
                            <td class="w1 c">配货</td>
                        </c:if>
                        <c:if test="${order.eoStatus==4}">
                            <td class="w1 c">送货中</td>
                        </c:if>
                        <c:if test="${order.eoStatus==5}">
                            <td class="w4 c">收获并确认</td>
                        </c:if>

                        <td class="w1 c"><a
                                href="/manage/order-modify.jsp?id=${order.eoId}&name=${order.eoUserName}&address=${order.eoUserAddress}&cost=${order.eoCost}&status=${order.eoStatus}">修改</a>
                            <a href="javascript:deleteOrder(${order.eoId});">删除</a></td>
                    </tr>
                </c:forEach>
            </table>
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
        </div>
    </div>
    <div class="clear"></div>
</div>
<div id="footer">
    Copyright &copy; 2010 北风教育 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
