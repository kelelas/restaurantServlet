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
                    <c:if test="${sessionScope.lang eq 'ua'}">
                        <div class="name">${sessionScope.user.nameUkr}</div>
                        <button type="button" class="btn btn-outline-secondary" onclick="location.href='/web/logout'"><fmt:message key="button.logout"/></button>
                    </c:if>
                    <c:if test="${sessionScope.lang eq 'en'}">
                        <div class="name">${sessionScope.user.nameEng}</div>
                        <button type="button" class="btn btn-outline-secondary" onclick="location.href='/web/logout'"><fmt:message key="button.logout"/></button>
                    </c:if>
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
<h1> <fmt:message key="text.ingredients"/> </h1>
<div class="container">
    <section class="cart">
        <div class="row cart_item">
            <div class="col-3">
                <div><fmt:message key="field.name"/> </div>
            </div>
            <div class="col-3">
                <div><fmt:message key="field.amount"/> </div>
            </div>
            <div class="col-3">
                <div><fmt:message key="field.maxAmount"/> </div>
            </div>
            <div class="col-3">

            </div>
        </div>

            <c:forEach items="${requestScope.ingredients}" var="ingredient">
        <div class="row cart_item">
                <div class="col-3">
                    <c:out value="${ingredient.name}"/>
                </div>
                <div class="col-3">
                    <c:out value="${ingredient.amount}"/>
                </div>
                <div class="col-3">
                    <c:out value="${ingredient.maxAmount}"/>
                </div>
                <div class="col-3">
                    <form action="update" method="post">
                        <input type="hidden" name="ingredientId" id="ingredientId" value="${ingredient.id}"/>
                        <input type="submit" class="btn btn-danger" value="<fmt:message key="page.refill"/>">
                    </form>
                </div>
        </div>
            </c:forEach>


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