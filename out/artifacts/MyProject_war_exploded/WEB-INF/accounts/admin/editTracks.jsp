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
    <link href="https://fonts.googleapis.com/css?family=Comfortaa" rel="stylesheet">

    <title>Car list</title>

</head>
<body>

<form method="post" action="edittracks">

    <div class="table">

        <div class="table-header">
            <div class="cell">ID</div>
            <div class="cell">TRACK NAME</div>
            <div class="cell">LENGTH</div>
            <div class="cell">TYPE</div>
        </div>


        <c:forEach var="track" items="${tracks}" varStatus="status">




            <div class="row">

                <div class="cell-id">
                    <input type="radio" id=${track.track_id} name="track_id" value="${track.track_id}">
                    <label class="radio_id" for="${track.track_id}">${track.track_id}</label>
                </div>

                <div class="cell">${track.track_name}</div>
                <div class="cell">${track.track_length}</div>
                <div class="cell">${track.track_type}</div>

            </div>

        </c:forEach>

    </div>

    <a href="../admin">Back to admin menu</a>
    <br>

    <input name="track_name" placeholder="Enter track name">
    <input name="track_length" placeholder="Enter track length">
    <input name="track_type" placeholder="Enter track type">

    <button type="submit" value="add" name="add">ADD</button>
    <button type="submit" value="update" name="update">UPDATE</button>
    <button type="submit" value="delete" name="delete">DELETE</button>


    <c:if test="${not empty message}">
        <h1>${message}</h1>
    </c:if>

</form>

</body>
</html>
