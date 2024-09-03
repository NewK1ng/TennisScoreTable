<%--
  Created by IntelliJ IDEA.
  User: Computer
  Date: 8/26/2024
  Time: 1:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>New match</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
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

<div class="container-fluid bg-secondary min-vh-100 d-flex align-items-center justify-content-center">

  <div class="container bg-dark border border-white border-2 d-flex p-4 justify-content-center" style="width: 30%">
    <form class="m-5" method="post" action="new-match">
      <h1 class="display-1 text-light fw-bold text-center mb-5">Create match</h1>
      <div class="mb-3 row">
        <label for="inputPlayer1" class="col-sm-12 col-form-label text-light fs-2 fw-bold">Player 1</label>
        <div class="col-sm-12">
          <input type="text" class="form-control fs-4 fw-bold" id="inputPlayer1" name="player1">
        </div>
      </div>
      <div class="mb-5 row">
        <label for="inputPlayer2" class="col-sm-12 col-form-label text-light fs-2 fw-bold">Player2</label>
        <div class="col-sm-12">
          <input type="text" class="form-control fs-4 fw-bold" id="inputPlayer2" name="player2">
        </div>
      </div>
      <div class="p-2 text-center">
        <button type="submit" class="btn btn-secondary text-light fs-1 fw-bold">Create</button>
      </div>
    </form>
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
