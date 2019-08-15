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

    <title>Admin Control</title>

</head>
<body>

<form method="post" action="editrequests">


    <div class="table">

        <div class="table-header">
            <div class="cell">ID</div>
            <div class="cell">REQUEST DATE</div>
            <div class="cell">STATUS</div>
            <div class="cell">COST</div>
            <div class="cell">NAME</div>
            <div class="cell">PHONE NUMBER</div>
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

                <c:forEach var="client" items="${clients}" varStatus="status">

                    <c:if test="${request.client_id==client.client_id}">

                        <div class="cell" title="Client #${client.client_id}">${client.surname}</div>
                        <div class="cell">${client.phone_number}</div>

                    </c:if>
                </c:forEach>

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

    <a href="../admin">Back to admin menu</a>
    <br>

    <input name="client_id" placeholder="Enter client id">
    <input name="car_id" placeholder="Enter car id">
    <input name="track_id" placeholder="Enter track id">
    <input name="request_status" placeholder="Enter request status">
    <input name="cost" placeholder="Enter request cost">

    <button type="submit" value="add" name="add">ADD</button>
    <button type="submit" value="update" name="update">UPDATE</button>
    <button type="submit" value="delete" name="delete">DELETE</button>

    <c:if test="${not empty message}">
        <h1>${message}</h1>
    </c:if>

</form>

</body>
</html>
