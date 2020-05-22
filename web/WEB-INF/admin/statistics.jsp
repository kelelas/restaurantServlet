<!DOCTYPE HTML>
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restaurant</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style><%@include file="/resources/static/test.css"%></style>
</head>
<body>
<header>
    <div class="container">
        <div class="row">
            <div class="col-4 logo">
                <div ><fmt:message key="header.restaurant"/></div>
            </div>
            <div class="col-4 buttons">
                <button type="submit" class="btn btn-light" onclick="location.href='?locale=ua'" ><fmt:message key="header.ukr"/></button>
                <button type="submit" class="btn btn-light" onclick="location.href='?locale=en'" ><fmt:message key="header.eng"/></button>
            </div>
            <div class="col-4 logout">
                <div>
                    <div class="name">${sessionScope.name}</div>
                    <button type="button" class="btn btn-outline-secondary" onclick="location.href='/web/logout'"><fmt:message key="button.logout"/></button>
                </div>
            </div>
        </div>
    </div>
</header>
<nav class="menu">
    <div class="container">
        <ul>
            <li><a href="/web/admin/orders_list"><fmt:message key="page.listOfOrders"/> </a></li>
            <!--            <li><a href="/admin/bill_confirm">Отправить счет</a></li>-->
            <li><a href="/web/admin/statistics"><fmt:message key="page.statistics"/></a></li>
            <li><a href="/web/admin/update_ingredients"><fmt:message key="page.updateIngredients"/></a></li>
        </ul>
    </div>
</nav>
<h1><fmt:message key="text.history"/></h1>
<%--<form method="post" action="/admin/filter">--%>
<%--    <input type="text" name="filter" id="filter" th:value="${filterValue}">--%>
<%--    <input type="submit" value="FilterByUserId">--%>
<%--</form>--%>
<div class="container">
    <section class="cart">
        <div class="row cart_item" >
            <div class="col-1" ><fmt:message key="field.id"/></div>
            <div class="col-1"><fmt:message key="field.name"/></div>
            <div class="col-4">
                <div class="customer"><fmt:message key="field.order"/></div>
            </div>
            <div class="col-1 info">
                <div class="title"><fmt:message key="field.sum"/>
                </div>
            </div>
            <div class="col-2">
                <div class="status"><fmt:message key="field.status"/></div>
            </div>
            <div class="col-2">
                <div class="date_time">
                    <span ><fmt:message key="field.date"/></span>
                </div>
            </div>
        </div>

    </section>
</div>
<div class="container">
    <section class="cart">
<c:forEach items="${requestScope.items}" var="item">
        <div class="row cart_item" >
            <div class="col-1">${item.id}</div>
            <div class="col-1">${item.userName}</div>
            <div class="col-4 in_column">
    <c:forEach items="${item.dishes}" var="dish">
                <div class="customer">${dish.name}</div>
    </c:forEach>
            </div>
            <div class="col-1 info">
                <div class="title">${item.price}
                </div>
            </div>
            <div class="col-2">
                <div class="status">${item.status}</div>
            </div>
            <div class="col-2">
                <div class="date_time">
                    <span>${item.date}</span>
                </div>
            </div>
        </div>
</c:forEach>
    </section>
</div>
<nav class="pagination">
    <ul>
            <c:if test="${requestScope.currentPage != 1}">
                <li class="page-item"><a class="page-link" href="?page=${requestScope.currentPage-1}">Previous</a>
                </li>
            </c:if>
        <c:forEach begin="1" end="${requestScope.numberOfPage}" var="i">
            <c:choose>
                <c:when test="${requestScope.currentPage eq i}">
                    <li class="page-item active"><a class="page-link">
                            ${i} <span class="sr-only">(current)</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="?page=${i}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
            <c:if test="${requestScope.currentPage lt requestScope.numberOfPage}">
                <li class="page-item"><a class="page-link"
                                         href="?page=${requestScope.currentPage+1}">Next</a>
                </li>
            </c:if>
    </ul>
</nav>
<footer>
    <div class="container">
        <div class="row">
            <div class="col-4" >
                <fmt:message key="footer.name"/>
            </div>
            <div class="col-4 date">
                16.04.2020
            </div>
            <div class="col-4 version" >
                <fmt:message key="footer.version"/>
            </div>
        </div>
    </div>
</footer>
</body>
</html>