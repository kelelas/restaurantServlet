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
<section class="rest_menu">
    <div class="container">
        <div class="row">
            <c:forEach items="${requestScope.dishes}" var="dish">
                <div class="col-4">

                    <div class="card" style="width: 18rem;">
                        <img src="${pageContext.request.contextPath}${dish.image}" class="card-img-top" alt="">
                        <div class="card-body">
                            <h5 class="card-title">${dish.name}</h5>
                            <!--                            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>-->
                            <p class="card-text">
                                <c:forEach items="${dish.ingredients}" var="ingredient">
                            <div>${ingredient.name}</div>
                                </c:forEach>
                            </p>
                            <span class="price">${dish.price}</span>
                            <a href="?id=${dish.id}" class="btn btn-primary"><fmt:message key="button.basket"/></a>
                        </div>
                    </div>

                </div>
            </c:forEach>


        </div>
    </div>
</section>
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