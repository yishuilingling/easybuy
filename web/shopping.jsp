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
    您现在的位置：<a href="pca">易买网</a> &gt; 购物车
</div>
<div class="wrap">
    <div id="shopping">
        <form action="order">
            <table>
                <tr>
                    <th>商品名称</th>
                    <th>商品价格</th>
                    <th>购买数量</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="cart" items="${cart.items}" varStatus="status">
                    <tr id="product_id_1">
                        <td class="thumb"><img src="images/product/${cart.product.epId}.jpg"/>
                            <a href="product-view.jsp">${cart.product.epName}</a></td>

                        <td class="price" id="price_id_1">
                            <span>￥${cart.cost}</span>
                            <input type="hidden" value="99"/>
                        </td>
                        <td class="number">
                            <dl>
                                <dt><input id="number_id_${cart.product.epId}" type="text" name="number" value="${cart.quantity}"/></dt>
                                <dd onclick="modifyQuantity(${cart.product.epId},${status.index});">修改</dd>
                            </dl>
                        </td>
                        <td class="delete"><a href="javascript:removeShopping(${cart.product.epId},${status.index});">删除</a></td>
                    </tr>
                </c:forEach>
            </table>
            <div class="button"><input type="submit" name="action" value=""/></div>
        </form>
    </div>
</div>
<div id="footer">
    Copyright &copy; 2010 北风教育 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
