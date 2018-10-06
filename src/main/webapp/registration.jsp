<%@ page import="ru.innopolis.stc12.lab02.pojo.Role" %>
<%@ page import="ru.innopolis.stc12.lab02.pojo.User" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/bootstrap.min.js"></script>
    <title>Title</title>
</head>
<body>
<div style="width:400px; margin-right:auto;text-align:center; margin-left:auto;">
    <form action="/inner/registration" method="post">
        <div class="form-group">
            <input class="form-control" type="text" name="login" id="login" placeholder="Login"><BR>
            <input class="form-control" type="password" name="password" id="password" placeholder="Password"><BR>
            <input class="form-control" type="password" name="password2" id="password2"
                   placeholder="Password confirm"><BR>
            <label class="mr-sm-2" for="chief[]">Choose chief...</label>
            <select class="custom-select mr-sm-2" id="chief[]" name="chief[]">
                <%
                    List<User> list = (List<User>) request.getAttribute("allUsers");
                    for (User user : list) {
                %>
                <option value=<%=user.getId()%>><%=user.getUsername() + " " + user.getRole().getRoleDescription()%>
                </option>
                <%}%>
            </select><BR>
            <label class="mr-sm-2" for="roles[]">Choose role...</label>
            <select class="custom-select mr-sm-2" id="roles[]" name="roles[]">
                <%
                    List<Role> rolesList = (List<Role>) request.getAttribute("roles");
                    for (Role role : rolesList) {
                %>
                <option value=<%=role.getId()%>><%=role.getRoleDescription()%>
                </option>
                <%}%>
            </select><BR>

            <input class="form-control" type="text" name="salary" id="salary" placeholder="Salary"><BR>

            <button type="submit" class="btn btn-primary mb-2">Add user</button>
        </div>
    </form>
</div>
<div style="width:400px; margin-right:auto;text-align:center; margin-left:auto;">
    <br>
    <button type="submit" class="btn btn-primary mb-2" name="back" onclick="history.back()">back</button>
</div>
</body>
</html>
