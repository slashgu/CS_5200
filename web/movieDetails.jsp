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
    <title>Title</title>
</head>
<body>
    <%
        Integer id = null;
        String idStr = null;
        if(request.getParameter("id") != null) {
            idStr = request.getParameter("id");
            id = Integer.parseInt(idStr);
        }
        projectDAO dao = new projectDAO();

        String action = request.getParameter("action");
        String first = request.getParameter("firstName");
        String last = request.getParameter("lastName");
        // TODO: set dob to be the type Date
        String dob = request.getParameter("dob");
        if("create".equals(action)) {
            Actor actor = new Actor(first, last, new Date(), null);
            dao.addActor(id, actor);
        }
        Movie movieRest = dao.readMovieById(id);
    %>
    <div class="container">
        <h1>Movie: <%= movieRest.getName()%></h1>
        <h2>Actors</h2>
        <form action="movieDetails.jsp">
            <input type="hidden" name="id" value="<%= idStr%>"/>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Date of Birth</th>
                </tr>
                <tr>
                    <th><input class="form-control" name="firstName"/></th>
                    <th><input class="form-control" name="lastName"/></th>
                    <th><input class="form-control" name="dob"/></th>
                    <th>
                        <button name="action" value="create" type="submit" class="btn btn-primary">Add</button>
                    </th>
                </tr>
            </thead>
            <tbody>
            <%
                List<Actor> actors = movieRest.getActors();
                for(Actor actor : actors) {
            %>
            <tr>
                <td><%= actor.getFirstName()%></td>
                <td><%= actor.getLastName()%></td>
                <td><%= actor.getDob()%></td>
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
