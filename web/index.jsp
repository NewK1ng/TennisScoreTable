<%--
  Created by IntelliJ IDEA.
  User: Computer
  Date: 8/25/2024
  Time: 11:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Main page</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
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

<%--<nav class="navbar navbar-dark bg-primary">--%>
<%--  <div class="container-fluid">--%>
<%--    <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">Home</a>--%>
<%--      <ul class="navbar-nav">--%>
<%--        <li class="nav-item">--%>
<%--          <a class="nav-link" href="${pageContext.request.contextPath}/new-match.jsp">New match</a>--%>
<%--        </li>--%>
<%--        <li class="nav-item">--%>
<%--          <a class="nav-link" href="${pageContext.request.contextPath}/matches">Matches</a>--%>
<%--        </li>--%>
<%--      </ul>--%>
<%--  </div>--%>
<%--</nav>--%>

<div class="container-fluid bg-secondary  min-vh-100 d-flex justify-content-center align-items-center">

  <div class="container text-center bg-dark p-3 d-flex flex-column align-items-center border border-white border-2" style="width: 30%">
    <h1 class="display-1 text-light fw-bold p-4">Tennis scoreboard</h1>
    <a href="new-match.jsp" class="btn btn-secondary btn-lg my-2 p-3 fw-bold fs-3">New match</a>
    <a href="${pageContext.request.contextPath}/matches" class="btn btn-success btn-lg my-2 p-3 fw-bold fs-3">Completed</a>
  </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

</body>

</html>