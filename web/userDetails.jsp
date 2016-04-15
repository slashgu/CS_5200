<%--
  Created by IntelliJ IDEA.
  User: chenggu
  Date: 4/8/16
  Time: 8:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import = "dao.*"
         import = "models.*" import = "java.util.*"%>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <title>User Details</title>
</head>
<body>
<%
    Integer id = null;
    String idStr = null;
    if(request.getParameter("id") != null) {
        idStr = request.getParameter("id");
        id = Integer.parseInt(idStr);
    }
    userDAO dao = new userDAO();

    String action = request.getParameter("action");
//    String first = request.getParameter("firstName");
//    String last = request.getParameter("lastName");
    if("create".equals(action)) {
//        Likes likes = new Likes();
//        dao.addLikes(id, likes);
    }
    User user = dao.readUserById(id);
%>
<div class="container">
    <h1>User: <%= user.getFirstName()%></h1>
    <h2>Favorites</h2>
    <form action="userDetails.jsp">
        <input type="hidden" name="id" value="<%= idStr%>"/>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Title</th>
                <th>Rating</th>
                <th>Comments</th>
            </tr>
            <tr>
                <th><input class="form-control" name="title"/></th>
                <th><input class="form-control" name="rating"/></th>
                <th><input class="form-control" name="comments"/></th>
                <th>
                    <button name="action" value="create" type="submit" class="btn btn-primary">Add</button>
                </th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Movie> movies = user.getLikes();
                for(Movie movie : movies) {
            %>
            <tr>
                <td><%= movie.getName()%></td>
                <td><%= movie.getRating()%></td>
                <td><%= movie.getComments()%></td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>
