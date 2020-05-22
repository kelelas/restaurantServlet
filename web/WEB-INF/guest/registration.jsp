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
<%--<div th:if="${param.error != null}">--%>
<%--    <div class="alert alert-danger" role="alert" th:text="#{message.user_exist}"></div>--%>
<%--</div>--%>
<%--<div th:if="${param.regex != null}">--%>
<%--    <div class="alert alert-danger" role="alert" th:text="#{text.regex1}">--%>
<%--    </div>--%>
<%--    <div class="alert alert-danger" role="alert" th:text="#{text.regex2}">--%>
<%--    </div>--%>
<%--    <div class="alert alert-danger" role="alert" th:text="#{text.regex3}">--%>
<%--    </div>--%>
<%--    <div class="alert alert-danger" role="alert" th:text="#{text.regex4}">--%>
<%--    </div>--%>
<%--    <div class="alert alert-danger" role="alert" th:text="#{text.regex5}">--%>
<%--    </div>--%>
<%--</div>--%>
<section class="login">
    <form action="reg" method="post">
        <label for="nameUkr"><fmt:message key="registration.name_ukr"/></label>
        <input type="text" name="nameUkr" id="nameUkr" placeholder="Іван">
        <label for="nameEng"><fmt:message key="registration.name_eng"/></label>
        <input type="text" name="nameEng" id="nameEng" placeholder="Ivan">
        <label for="email"><fmt:message key="registration.email"/></label>
        <input type="email" name="email" id="email" placeholder="ivanov@gmail.com">
        <label for="password" ><fmt:message key="registration.password"/></label>
        <input type="password" name="password" id="password" placeholder="********">
        <input type="submit" <fmt:message key="button.signup"/>>
    </form>
</section>
<footer>
    <div class="container">
        <div class="row">
            <div class="col-4" >
                <fmt:message key="footer.name"/>
            </div>
            <div class="col-4 date">
                15.03.2020
            </div>
            <div class="col-4 version" >
                <fmt:message key="footer.version"/>
            </div>
        </div>
    </div>
</footer>
</body>
</html>