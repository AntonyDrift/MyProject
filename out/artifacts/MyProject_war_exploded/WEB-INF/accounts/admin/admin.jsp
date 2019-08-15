<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ?
          param.language : not empty language  ? language :
                        pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/assests/index/style.css">
    <link href="https://fonts.googleapis.com/css?family=Comfortaa" rel="stylesheet">
    <title>admin</title>
</head>
<body>

<div class="content">

    <a href="admin/editrequests">requests</a>
    <a href="admin/editcars">cars</a>
    <a href="admin/edittracks">tracks</a>
    <a href="admin/editclients">clients</a><br>
    <a href="../">Back to main menu</a>
    <a href="/logout">logout</a>

</div>

</body>
</html>
