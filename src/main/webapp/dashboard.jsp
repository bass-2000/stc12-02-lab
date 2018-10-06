<%@ page import="ru.innopolis.stc12.lab02.pojo.User" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/bootstrap.min.js"></script>
    <title>Title</title>
</head>
<body>
<div class="alert alert-success" role="alert">
    You autorised as <%=request.getSession().getAttribute("login")%>
</div>

<div class="col">
    <%=request.getSession().getAttribute("login")%><br><br>
    <button onclick="location.href='/login?action=logout'" type="button" class="btn btn-primary">Log OUT</button>
    <BR><BR>

    <H4>DASHBOARD</H4>
    <input class="form-control" type="text" placeholder="Your salary is <%=request.getAttribute("currentUserSalary")%>"
           readonly>
</div>
<div class="col">
    <br>
    <button type="submit" class="btn btn-primary mb-2" name="refresh" onclick="javascript:history.go(0)">Refresh
    </button>
</div>

<br><br>
<label>Таблица подчинённых сотрудников</label>
<form action="inner/dashboard" method="get" id="employeeForm" role="form">

    <table class="table table-striped">

        <thead>
        <tr>
            <td>#</td>
            <td>Username</td>
            <td>Chief</td>
            <td>Role</td>
            <td>Salary</td>
        </tr>
        </thead>
        <%
            List<User> list = (List<User>) request.getAttribute("minions");
            for (User user : list) {
        %>
        <tr>
            <td><%=user.getId()%>
            </td>
            <td><%=user.getUsername()%>
            </td>
            <td><%=user.getChiefId()%>
            </td>
            <td><%=user.getRole().getRoleDescription()%>
            </td>
            <td><%=user.getSalary()%>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</form>

<button onclick="location.href='/inner/registration'" type="button" class="btn btn-primary">Registration</button>
</body>
</html>