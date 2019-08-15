<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ?
          param.language : not empty language  ? language :
                        pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <title>Registration</title>
</head>
<body>

<form action="registration" method="post">

    <input type="text" name="username" placeholder="username"/>
    <input type="password" name="user_password" placeholder="password"/>
    <input type="text" name="user_email" placeholder="email address"/>
    <input type="submit" value="registrate">

</form>

<c:if test="${not empty message}">
    <h1>${message}</h1>
</c:if>

<p class="message">Already registered? <a href="login">Sign In</a></p>

</body>
</html>
