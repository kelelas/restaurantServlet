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
                    <div class="name">${sessionScope.user.nameEng}</div>
                    <button type="button" class="btn btn-outline-secondary" onclick="location.href='/web/logout'"><fmt:message key="button.logout"/></button>
                    <div class="name"> <fmt:message key="header.balance"/> ${sessionScope.user.balance} <fmt:message key="header.currency"/> </div>
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
<h1><fmt:message key="page.cart"/></h1>
<c:if test="${param.ok != null}">
    <div class="alert alert-info" role="alert"><fmt:message key="alert.cart"/></div>
</c:if>
<div class="container">
    <section class="cart">
        <div class="row cart_item">

    <div class="col-3">
        <div class="title" >
            <fmt:message key="field.img"/>
        </div>
    </div>
        <div class="col-4 info">
            <div class="title"  >
                <fmt:message key="field.order"/>
            </div>
        </div>
            <div class="col-1">
                <div class="price" ><fmt:message key="field.sum"/></div>
            </div>
            <div class="col-1">
            </div>
        </div>
</section>
</div>
<div class="container">
    <section class="cart">
<c:forEach items="${requestScope.order}" var="dish">
        <div class="row cart_item">
            <div class="col-3">
                <img src="${pageContext.request.contextPath}${dish.image}" alt="">
            </div>
            <div class="col-4 info">
                <div class="title">
                        ${dish.name}
                </div>
                <div class="ingredients">
                    <c:forEach items="${dish.ingredients}" var="ingredient">
                        <div>${ingredient.name} </div>
                    </c:forEach>
                </div>
            </div>
            <div class="col-4">
                <div class="price">${dish.price}</div>
            </div>

            <div class="col-1">
                <form action="deleteFromOrder" method="post" >
                    <input type="hidden" name="orderId" id="orderId" value="${dish.id}"/>
                    <input type="submit" class="btn btn-primary red-btn" value="<fmt:message key="button.delete"/>">
                </form>
            </div>
        </div>
</c:forEach>
        <div class="summary">
            <div class="title"><fmt:message key="text.summary"/></div>
            <div class="amount">${requestScope.sum}</div>
        </div>
        <c:if test="${requestScope.sum > 0}">
            <form action="confirm" method="post" >
                <input type="submit" class="btn btn-primary green-btn" value="<fmt:message key="button.ok"/>">
            </form>
        </c:if>
    </section>
</div>
<footer>
    <div class="container">
        <div class="row footer_info">
            <div class="col-4 footer-col logo">
                <fmt:message key="header.restaurant"/>
            </div>
            <div class="col-4 footer-col contacts">
                <div class="title"><fmt:message key="footer.contacts"/></div>
                <a href="tel:+380935947785" class="phone">+38 093 594 77 85</a>
                <a href="mailto:kelels2015@gmail.com" class="email">kelels2015@gmail.com</a>
                <a href="https://github.com/kelelas">github.com/kelelas</a>
            </div>
            <div class="col-4 footer-col version">
                <div class="title">
                    <fmt:message key="footer.version"/>
                </div>
            </div>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row copyright">
            <div class="col-12">
                Made by Osypchuk Vladyslav
            </div>
        </div>
    </div>
</footer>
</body>
</html>