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
        <h2>订单详情</h2>
        <div class="manage">
            <fieldset>
                <table class="form">
                    <tr>
                        <td>订单ID：${order.eoId}</td>
                    </tr>
                    <tr>
                        <td>订购人姓名：${order.eoUserName}</td>
                    </tr>
                    <tr>
                        <td>订购人地址：${order.eoUserAddress}</td>
                    </tr>
                    <tr>
                        <td width="40%">商品：</td>
                        <td>商品号</td>
                        <td>商品名</td>
                        <td>数量</td>
                        <td>价格</td>
                    </tr>
                    <c:forEach var="detail" items="${detailList}">
                        <tr>
                            <td></td>
                            <td>${detail.epId}</td>
                            <td>${detail.epName}</td>
                            <td>${detail.eodQuantity}</td>
                            <td>${detail.eodCost}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td>总金额：${order.eoCost}</td>
                    </tr>
                    <tr>
                        <c:if test="${order.eoStatus==1}">
                            <td>订单状态：发货</td>
                        </c:if>
                        <c:if test="${order.eoStatus==2}">
                            <td>订单状态：审核通过</td>
                        </c:if>
                        <c:if test="${order.eoStatus==3}">
                            <td>订单状态：配货</td>
                        </c:if>
                        <c:if test="${order.eoStatus==4}">
                            <td>订单状态：送货中</td>
                        </c:if>
                        <c:if test="${order.eoStatus==5}">
                            <td>订单状态：收获并确认</td>
                        </c:if>
                    </tr>
                </table>
            </fieldset>
        </div>
    </div>
    <div class="clear"></div>
</div>
<div id="footer">
    Copyright &copy; 2010 北风教育 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
