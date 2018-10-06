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
<%
    if (("wrongUser").equals(request.getParameter("action"))) {
%>
<div class="alert alert-danger" role="alert">
    Not autorized! Wrong pair of Login and Password
</div>

<%}%>
<div style="width:400px; position: fixed; top: 50%; left: 50%;">
    <form action="/login" method="post">
        <div class="form-group">
            <input class="form-control" type="text" name="login" id="login" placeholder="Login"><BR>
            <input class="form-control" type="password" name="password" id="password" placeholder="Password"><BR>
            <button type="submit" class="btn btn-primary mb-2">Login</button>
        </div>
    </form>
    <br>
    <button type="submit" class="btn btn-primary mb-2" name="back" onclick="history.back()">back</button>
</div>
</body>
</html>
