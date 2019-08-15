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

<form method="post" action="editcars">

    <div class="table">

        <div class="table-header">
            <div class="cell">ID</div>
            <div class="cell">MODEL</div>
            <div class="cell">WHEEL DRIVE</div>
            <div class="cell">POWER</div>
            <div class="cell">AVAILABLE</div>
            <div class="cell">CLASS CAR</div>
        </div>


        <c:forEach var="car" items="${cars}" varStatus="status">




            <div class="row">

                <div class="cell-id">
                    <input type="radio" id=${car.car_id} name="car_id" value="${car.car_id}">
                    <label class="radio_id" for="${car.car_id}">${car.car_id}</label>
                </div>

                <div class="cell">${car.model}</div>
                <div class="cell">${car.wheel_drive}</div>
                <div class="cell">${car.power}</div>
                <div class="cell">${car.available}</div>
                <div class="cell">${car.class_car}</div>

            </div>

        </c:forEach>

    </div>

    <a href="../admin">Back to admin menu</a>
    <br>

    <input name="model" placeholder="Enter car model">
    <input name="wheel_drive" placeholder="Enter wheel drive">
    <input name="power" placeholder="Enter power [hp]">
    <input name="available" placeholder="Enter available status [0/1]">
    <input name="class_car" placeholder="Enter class car">

    <button type="submit" value="add" name="add">ADD</button>
    <button type="submit" value="update" name="update">UPDATE</button>
    <button type="submit" value="delete" name="delete">DELETE</button>

    <c:if test="${not empty message}">
        <h1>${message}</h1>
    </c:if>

</form>

</body>
</html>
