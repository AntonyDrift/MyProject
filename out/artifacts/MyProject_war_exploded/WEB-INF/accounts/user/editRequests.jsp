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

    <title>User Control</title>

</head>
<body>

<form method="post" action="editrequests">


    <div class="table">

        <div class="table-header">
            <div class="cell">ID</div>
            <div class="cell">REQUEST DATE</div>
            <div class="cell">STATUS</div>
            <div class="cell">COST</div>
            <div class="cell">CAR MODEL</div>
            <div class="cell">TRACK NAME</div>
            <div class="cell">TYPE</div>
        </div>


        <c:forEach var="request" items="${requests}" varStatus="status">


            <div class="row">

                <div class="cell-id">
                    <input type="radio" id=${request.request_id} name="request_id" value="${request.request_id}">
                    <label class="radio_id" for="${request.request_id}">${request.request_id}</label>
                </div>

                <div class="cell">${request.request_date}</div>
                <div class="cell">${request.request_status}</div>
                <div class="cell">${request.cost}</div>


                <c:forEach var="car" items="${cars}" varStatus="status">

                    <c:if test="${request.car_id==car.car_id}">

                        <div class="cell"title="Car #${car.car_id}">${car.model}</div>

                    </c:if>
                </c:forEach>

                <c:forEach var="track" items="${tracks}" varStatus="status">

                    <c:if test="${request.track_id==track.track_id}">

                        <div class="cell" title="Track #${track.track_id}">${track.track_name}</div>
                        <div class="cell">${track.track_type}</div>

                    </c:if>
                </c:forEach>


            </div>


        </c:forEach>

    </div>

    <a href="../user">Back to user menu</a>
    <br>

</form>

</body>
</html>
