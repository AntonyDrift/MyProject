<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ?
          param.language : not empty language  ? language :
                        pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html >
<html lang="${language}">

<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/assests/index/style.css">
    <link href="https://fonts.googleapis.com/css?family=Comfortaa" rel="stylesheet">
    <title><fmt:message key="index.title"/></title>
</head>
<body>

<div class="container">

    <div class="header">

        <div class="locale">
            <form>
                <select id="language" title="<fmt:message key="header.locale.title" />" name="language"
                        class="locale-select" onchange="submit()">
                    <option value="en_US" ${language == 'en_US' ? 'selected' : ''} >US</option>
                    <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''} >RU</option>
                </select>
            </form>
        </div>

        <div class="logo" title="<fmt:message key="header.logo.title"/>">

            <a href="/index.jsp">
                <img class="img-logo" src="assests/index/img/logo.png" style="height:8vh" alt="Logo TRD">
            </a>
        </div>

        <div class="login" title="<fmt:message key="header.login.title"/>">

            <c:if test="${sessionScope.role==null}">
                <a href="/login" class="login-form">
                    <button class="but"><fmt:message key="header.login.button"/></button>
                </a>
            </c:if>

            <c:if test="${sessionScope.role!=null}">
                <a href="/logout" class="login-form">
                    <button class="but"><fmt:message key="header.logout.button"/></button>
                </a>
            </c:if>

        </div>
    </div>

    <div class="content">
        <div class="select-menu">

            <a href="/addrequest?classCar=circuit&typeTrack=circuit" class="trackdayURL">
                <div class="scroll_down"><p class="par-MAIN"><fmt:message key="index.track"/></p>
                </div>
            </a>
            <a href="/addrequest?classCar=rally&typeTrack=rally" class="rallyURL">
                <div class="scroll_down"><p class="par-MAIN"><fmt:message key="index.rally"/></p>
                </div>
            </a>
            <a href="/addrequest?classCar=drift&typeTrack=drift" class="driftURL">
                <div class="scroll_down"><p class="par-MAIN"><fmt:message key="index.drift"/></p>
                </div>
            </a>
            <div class="fake"></div>

        </div>


    </div>

    <div class="footer">
        Lorem ipsum dolor sit amet,
        consectetur adipisicing elit. Laboriosam
        assumenda odit illo et laudantium aspernatur
        nobis fuga in architecto cum.
    </div>


</div>

<script
        src="http://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>
<script src="assests/index/script.js" charset="utf-8"></script>
</body>
</html>
