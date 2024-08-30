<jsp:useBean id="match" scope="request" type="model.Match"/>
<%--
  Created by IntelliJ IDEA.
  User: Computer
  Date: 8/30/2024
  Time: 7:57 PM
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
</head>


<body>

<div class="container-fluid bg-secondary  min-vh-100 d-flex justify-content-center align-items-center">

  <div class="container text-center bg-dark p-4 border border-white border-2 d-flex flex-column justify-content-center"
       style="width:50%">
    <h1 class="display-4 fw-bold text-info">Match finished! ${match.winner.name} wins!</h1>
    <div class="container w-50 pt-4">
      <table class="table table-light table-bordered border-dark fs-4">
        <thead class="table-dark table-bordered border-dark">
        <tr>
          <th scope="col">PLAYER</th>
          <th scope="col">SETS</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <th scope="row">${match.player1.name}</th>
          <td>${match.matchScore.getPlayerSets(1)}</td>
        </tr>
        <tr>
          <th scope="row">${match.player2.name}</th>
          <td>${match.matchScore.getPlayerSets(2)}</td>
        </tr>
        </tbody>
      </table>
    </div>
    <div class="container d-flex justify-content-center p-0 pt-4">
      <a href="index.jsp" class="btn btn-primary btn-lg my-2 p-3 fw-bold fs-3">Home</a>
    </div>
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