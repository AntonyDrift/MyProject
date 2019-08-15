<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ?
          param.language : not empty language  ? language :
                        pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="locale"/>

<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="/assests/accounts/admin/style.css">
    <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">

    <title>Car list</title>

</head>
<body>

<form method="post" action="editclients">

    <div class="table">

        <div class="table-header">
            <div class="cell">ID</div>
            <div class="cell">SURNAME</div>
            <div class="cell">EMAIL</div>
            <div class="cell">PHONE NUMBER</div>

        </div>


        <c:forEach var="client" items="${clients}" varStatus="status">


            <div class="row">

                <div class="cell-id">
                    <input type="radio" id=${client.client_id} name="client_id" value="${client.client_id}">
                    <label class="radio_id" for="${client.client_id}">${client.client_id}</label>
                </div>

                <div class="cell">${client.surname}</div>
                <div class="cell">${client.email}</div>
                <div class="cell">${client.phone_number}</div>

            </div>

        </c:forEach>

    </div>

    <a href="../admin">Back to admin menu</a>
    <br>

    <input name="surname" placeholder="Enter surname">
    <input name="email" placeholder="Enter email">
    <input name="phone_number" placeholder="Enter phone number">

    <button type="submit" value="add" name="add">ADD</button>
    <button type="submit" value="update" name="update">UPDATE</button>
    <button type="submit" value="delete" name="delete">DELETE</button>


    <c:if test="${not empty message}">
        <h1>${message}</h1>
    </c:if>

</form>

</body>
</html>
