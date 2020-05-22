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
            <li><a href="/web/user/menu"><fmt:message key="page.menu"/> </a></li>
            <li><a href="/web/user/bill"><fmt:message key="page.bill"/></a></li>
            <li><a href="/web/user/history"><fmt:message key="page.history"/></a></li>
            <li><a href="/web/user/cart"><fmt:message key="page.cart"/></a></li>
            <li><a href="/web/user/refill"><fmt:message key="page.refill"/></a></li>
        </ul>
    </div>
</nav>
<h1><fmt:message key="text.history"/></h1>

<div class="container">
    <section class="cart">
        <div class="row cart_item">
            <div class="col-1" ><fmt:message key="field.id"/></div>
            <div class="col-2" ><fmt:message key="field.img"/></div>
            <div class="col-4">
                <div class="customer" ><fmt:message key="field.order"/></div>
            </div>
            <div class="col-1 info">
                <div class="title" >
                    <fmt:message key="field.sum"/>
                </div>
            </div>
            <div class="col-2">
                <span><fmt:message key="field.date"/></span>
            </div>
            <div class="col-1 info">
                <div class="title">
                    <fmt:message key="field.status"/>
                </div>
            </div>
        </div>
    </section>
</div>
<div class="container">
    <section class="cart">
<c:forEach items="${requestScope.stories}" var="story">
        <div class="row cart_item" >
            <div class="col-1" >${story.id}</div>
            <div class="col-2">
                <img src="${pageContext.request.contextPath}/resources/img/food.jpg" alt="">
            </div>
            <div class="col-4 info in_column">
                    <c:forEach items="${story.dishes}" var="dish">
                        <div class="title">
                        ${dish.name}
                        </div>
                    </c:forEach>

            </div>
            <div class="col-1">
                <div class="price">${story.price}</div>
            </div>

            <div class="col-2">
                <div class="date_time">
                    <span>${story.date}</span>
                </div>
            </div>
            <div class="col-1">
                <div class="date_time">
                    <span>${story.status}</span>
                </div>
            </div>
        </div>
</c:forEach>
    </section>
</div>
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