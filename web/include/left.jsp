<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/12
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="lefter">
    <div class="box">
        <h2>商品分类</h2>
        <dl>
            <dl>
                <c:forEach var="ca" items="${categoryList}">
                    <c:if test="${ca.epcParentId==0}">
                        <dt>${ca.epcName}</dt>
                    </c:if>
                    <c:forEach var="pca" items="${categoryList}">
                        <c:if test="${ca.epcId==pca.epcParentId}">
                            <dd>
                                <a href="/pca?action=details&id=${pca.epcId}&name=${pca.epcName}&parentName=${ca.epcName}"> ${pca.epcName}</a>
                            </dd>
                        </c:if>
                    </c:forEach>

                </c:forEach>

            </dl>

        </dl>
    </div>
    <div class="spacer"></div>
    <div class="last-view">
        <h2>最近浏览</h2>
        <dl class="clearfix">
            <c:forEach var="recent" items="${recent}">
                <dt><img src="${recent.epFileName} "width="45px" height="45px"/></dt>
                <dd><a href="product?id=${recent.epId}&action=${"details"}">${recent.epName}</a></dd>
            </c:forEach>
        </dl>
    </div>
</div>
</body>
</html>
