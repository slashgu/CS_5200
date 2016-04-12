<%--
  Created by IntelliJ IDEA.
  User: chenggu
  Date: 4/12/16
  Time: 1:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
</head>
<body>
    <div>
        <h1>
            Log In
        </h1>
        <form action="logIn.jsp">
            <table>
                <tr>
                    <th>User Name</th>
                    <th>Password</th>
                </tr>
                <tr>
                    <td><input name = "userName" class="form-control"></td>
                    <td><input name = "password" class="form-control"></td>
                    <td>
                        <a href="homepage.jsp" class="btn btn-primary">Log In</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
