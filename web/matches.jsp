<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:useBean id="finishedMatchesPage" scope="request" type="model.FinishedMatchesPage"/>
<%--
  Created by IntelliJ IDEA.
  User: Computer
  Date: 8/31/2024
  Time: 1:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <style>

        .table {
            table-layout: fixed;
        }

        .table td,
        .table th {
            overflow: auto;
            text-overflow: ellipsis;
            white-space: nowrap;
        }

    </style>

</head>

<body>

<nav class="navbar navbar-dark bg-dark navbar-expand-lg">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/index.jsp">HOME</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/new-match.jsp">New match</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/matches">Matches</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid bg-secondary  min-vh-100 d-flex justify-content-center align-items-center">

    <div class="container text-center bg-dark p-4 border border-white border-2 d-flex flex-column justify-content-center w-50">
        <h1 class="display-5 fw-bold text-info pb-2">Finished matches</h1>

        <div class="container m-0 p-2 d-flex justify-content-center">

            <label for="filter_by_player_name" class="text-light fs-2 fw-bold">Name:</label>

            <form class="d-flex align-items-center justify-content-center w-100" method="get" action="${pageContext.request.contextPath}/matches">
                <input type="text" class="form-control m-2" id="filter_by_player_name" name="filter_by_player_name" value="${finishedMatchesPage.filterPlayerName}">
                <button type="submit" class="btn btn-primary text-light fw-bold w-25">Search</button>
            </form>

        </div>

        <div class="container pt-4 d-flex justify-content-center">
            <table class="table table-secondary table-bordered border-default fs-4">
                <thead class="table-dark table-bordered border-light fs-3">
                <tr>
                    <th scope="col" class="px-0" style="width: 10%">ID</th>
                    <th scope="col" style="width: 30%">Player 1</th>
                    <th scope="col" style="width: 30%">Player 2</th>
                    <th scope="col" style="width: 30%">Winner</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${finishedMatchesPage.matchesList}" var="match" varStatus="loop">
                <tr>
                    <th scope="row">${loop.count+finishedMatchesPage.pageElementStartIndex}</th>
                    <td>${match.player1.name}</td>
                    <td>${match.player2.name}</td>
                    <td>${match.winner.name}</td>
                </tr>
                </c:forEach>
            </table>
        </div>

        <c:if test="${not empty finishedMatchesPage.filterPlayerName}">
            <c:set var="playerFilter" value="&filter_by_player_name=${finishedMatchesPage.filterPlayerName}"/>
        </c:if>

        <c:set var="prevUrl" value="${pageContext.request.contextPath}/matches?page=${finishedMatchesPage.pageNumber-1}${playerFilter}"/>
        <c:set var="nextUrl" value="${pageContext.request.contextPath}/matches?page=${finishedMatchesPage.pageNumber+1}${playerFilter}"/>

        <c:if test="${finishedMatchesPage.numberOfMatches > finishedMatchesPage.pageSize}">
            <div class="container d-flex justify-content-center pt-3 align-items-center w-25 p-0">
                <a href="${prevUrl}" class=" btn btn-primary text-light fw-bold w-50
                <c:if test="${finishedMatchesPage.pageNumber == 1}">disabled</c:if>">
                    Prev
                </a>

                <a class="btn btn-primary fw-bold disabled mx-2"
                   style="opacity: 1">${finishedMatchesPage.pageNumber}</a>

                <a href="${nextUrl}" class=" btn btn-primary text-light fw-bold w-50
                <c:if test="${finishedMatchesPage.pageNumber == finishedMatchesPage.totalPages}">disabled</c:if>">
                    Next
                </a>
            </div>
        </c:if>

    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
        integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
        crossorigin="anonymous"></script>

</body>

</html>
