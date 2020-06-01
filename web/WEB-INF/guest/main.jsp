<!DOCTYPE HTML>
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<head>
    <meta http-equiv="Content-Type" content="text/html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restaurant</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <%--    <link rel="stylesheet"  href="../../static/test.css" type="text/css">--%>
    <style><%@include file="/resources/static/test.css"%></style>
</head>

<body>
<header>
    <div class="container">
        <div class="row">
            <div class="col-5">
                <div class="logo" > <fmt:message key="header.restaurant"/> </div>
            </div>
            <div class="col-4">
                <button type="submit" class="btn btn-light" onclick="location.href='?locale=ua'"> <fmt:message key="header.ukr"/> </button>
                <button type="submit" class="btn btn-light" onclick="location.href='?locale=en'" > <fmt:message key="header.eng"/></button>
            </div>
        </div>
    </div>
</header>

<nav class="menu">
    <div class="container">
        <ul>
            <li><a href="/web/" ><fmt:message key = "page.main"/></a></li>
            <li><a href="/web/login"><fmt:message key ="page.login"/></a></li>
            <li><a href="/web/registration"><fmt:message key = "page.signup"/></a></li>
        </ul>
    </div>
</nav>
<section class="title_with_img">
    <fmt:message key = "header.restaurant"/>
</section>
<section class="descr">
    <div class="container">
        21. Система Ресторан. Клиент осуществляет заказ из Меню.
        Администратор подтверждает Заказ и отправляет его на кухню для
        исполнения. Администратор выставляет Счет. Клиент производит его
        оплату.
    </div>
</section>
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