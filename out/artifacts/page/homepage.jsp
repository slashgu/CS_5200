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
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <%
            projectDAO dao = new projectDAO();
            String action = request.getParameter("action");
            String idStr = request.getParameter("id");
            String title = request.getParameter("title");
            String country = request.getParameter("country");
            String comments = request.getParameter("comments");
            Integer year = null;
            if(request.getParameter("year") != null) {
                year = Integer.parseInt(request.getParameter("year"));
            }
            Double rating = null;
            if(request.getParameter("rating") != null) {
                rating = Double.parseDouble(request.getParameter("rating"));
            }
//            String genra = request.getParameter("genra");
            if("create".equals(action)) {
                Movie movie = new Movie(title, year, null, country, rating, comments);
                dao.createMovie(movie);
            }
            else if("delete".equals(action)) {
                Integer id = Integer.parseInt(idStr);
                dao.deleteMovie(id);
            }

            List<Movie> movies = dao.readAllMovies();
        %>
        <h1>
            Movies
        </h1>
        <form action="homepage.jsp">
        <table class="table table-striped">
            <tr>
                <th>Title</th>
                <th>Country</th>
                <th>Year</th>
                <th>Rating</th>
                <th>Comments</th>
                <th>&nbsp;</th>
            </tr>
            <tr>
                <td><input name = "title" class="form-control"></td>
                <td><input name = "country" class="form-control"></td>
                <td><input name = "year" class="form-control"></td>
                <td><input name = "rating" class="form-control"></td>
                <td><input name = "comments" class="form-control"></td>
                <td>
                    <button class="btn btn-primary" type="submit" name="action" value="create">Create</button>
                </td>
            </tr>
        <%
            for(Movie movie : movies) {

        %>
            <tr>
                <td>
                    <a href="movieDetails.jsp?id=<%= movie.getId()%>">
                    <%= movie.getName() %>
                    </a>
                </td>
                <td><%= movie.getCountry() %></td>
                <td><%= movie.getYear() %></td>
                <td><%= movie.getRating() %></td>
                <td><%= movie.getComments() %></td>
                <td>
                    <a href="movies.jsp?action=delete&id=<%= movie.getId()%>" class="btn btn-danger">Delete</a>
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