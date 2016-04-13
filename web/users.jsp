<%--
  Created by IntelliJ IDEA.
  User: chenggu
  Date: 4/4/16
  Time: 10:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language= "java" import = "dao.*"
         import = "models.*" import = "java.util.*" %>
<%@ page import="javax.persistence.criteria.CriteriaBuilder" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Users</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <%
        userDAO dao = new userDAO();
        String action = request.getParameter("action");
        Integer id = null;
        if(request.getParameter("id") != null) {
            id = Integer.parseInt(request.getParameter("id"));
        }
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
//        String userName = request.getParameter("userName");
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
        if("create".equals(action)) {
            User user = new User(id, firstName, lastName, null, null, null);
            dao.createUser(user);
        }
        else if("delete".equals(action)) {
//            Integer id = Integer.parseInt(idStr);
            dao.deleteUser(id);
        }
        else if("update".equals(action)) {
//            Integer id = Integer.parseInt(idStr);
            User tmp = dao.readUserById(id);
            tmp.setFirstName(firstName);
            tmp.setLastName(lastName);
//            tmp.setUserName(userName);
//            tmp.setEmail(email);
//            tmp.setPassword(password);
            dao.updateUser(tmp);
        }

        List<User> users = dao.readAllUsers();
    %>
    <h1>
        Users
    </h1>
    <form action="users.jsp">
        <table class="table table-striped">
            <tr>
                <th>Id</th>
                <th>First Name</th>
                <th>Last Name</th>
                <%--<th>Rating</th>--%>
                <%--<th>Comments</th>--%>
                <th>&nbsp;</th>
            </tr>
            <tr>
                <td><input name = "id" placeholder="id" value="<%=id%>" readonly class="form-control"></td>
                <td><input name = "firstName" placeholder="firstName" value="<%=firstName%>" class="form-control"></td>
                <td><input name = "lastName" placeholder="lastName" value="<%=lastName%>" class="form-control"></td>
                <%--<td><input name = "year" placeholder="year" value="<%=year%>" class="form-control"></td>--%>
                <%--<td><input name = "rating" placeholder="rating" value="<%=rating%>" class="form-control"></td>--%>
                <%--<td><input name = "comments" placeholder="comments" value="<%=comments%>" class="form-control"></td>--%>
                <td>
                    <button class="btn btn-success" type="submit" name="action" value="create">Create</button>
                    <button class="btn btn-info" name="action" value="update">Update</button>
                </td>
            </tr>
            <%
                for(User user : users) {

            %>
            <tr>
                <td>
                    <%= user.getId() %>
                </td>
                <td><%= user.getFirstName() %></td>
                <td><%= user.getLastName() %></td>
                <%--<td><%= user.getRating() %></td>--%>
                <%--<td><%= user.getComments() %></td>--%>
                <td>
                    <a href="users.jsp?action=delete&id=<%= user.getId()%>" class="btn btn-danger">Delete</a>
                    <a href="users.jsp?action=select&id=<%= user.getId()%>&firstName=<%=user.getFirstName()%>&lastName=<%=user.getLastName()%>" class="btn btn-warning">Select</a>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </form>
</div>
</body>
</html>