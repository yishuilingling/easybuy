<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>易买网 - 首页</title>
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
    <script type="text/javascript" src="scripts/function.js"></script>
</head>
<body>
<jsp:include page="include/top.jsp"/>
<div id="position" class="wrap">
    您现在的位置：<a href="pca">易买网</a> &gt; 我的订单
</div>
<div id="main" class="wrap">
    <jsp:include page="include/left.jsp"/>
    <div class="manage">
        <c:forEach var="pOrder" items="${pOrderList}">
            <table class="list">
                <tr>
                    <th>订单号</th>
                    <th>下单日期</th>
                    <th>商品</th>
                    <th>订单状态</th>
                    <th>总价</th>
                </tr>
                <tr>
                    <td class="first w1 c">${pOrder.eoId} </td>
                    <td class="w1 c" width="100px" style="font-size:12px;">${pOrder.eoCreateTime}</td>
                    <td>
                        <table>
                            <tr>
                                <td>商品名</td>
                                <td>购买数量</td>
                                <td>价格</td>
                            </tr>
                            <c:forEach var="pro" items="${pOrder.proList}">
                                <tr>
                                    <td>${pro.epName}</td>
                                    <td>${pro.eodQuantity}</td>
                                    <td>${pro.eodCost}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </td>
                    <c:if test="${pOrder.eoStatus==1}">
                        <td class="w1 c">发货</td>
                    </c:if>
                    <c:if test="${pOrder.eoStatus==2}">
                        <td class="w1 c">审核通过</td>
                    </c:if>
                    <c:if test="${pOrder.eoStatus==3}">
                        <td class="w1 c">配货</td>
                    </c:if>
                    <c:if test="${pOrder.eoStatus==4}">
                        <td class="w1 c">送货中</td>
                    </c:if>
                    <c:if test="${pOrder.eoStatus==5}">
                        <td class="w4 c">收获并确认</td>
                    </c:if>
                    <td>${pOrder.eoCost}</td>
                </tr>
            </table>
            <br><br>
        </c:forEach>
    </div>
</div>
<div id="footer">
    Copyright &copy; 2010 北风教育 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
