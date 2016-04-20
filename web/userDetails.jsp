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
    String idStr = request.getParameter("id");
    Integer id = Integer.parseInt(idStr);

    userDAO dao = new userDAO();
    projectDAO movieDao = new projectDAO();

    String action = request.getParameter("action");
    String title = request.getParameter("title");
    String country = request.getParameter("country");
    Double rating = null;
    if(request.getParameter("rating") != null) {
        rating = Double.parseDouble(request.getParameter("rating"));
    }
    Integer year = null;
    if(request.getParameter("year") != null) {
        year = Integer.parseInt(request.getParameter("year"));
    }
    String comments = request.getParameter("comments");

    if("create".equals(action)) {
        Movie movie_like = new Movie(title, year, null, country, rating, comments);
        movie_like = movieDao.createMovie(movie_like);
        // TODO: update likes
        Likes likes = new Likes(id, movie_like.getId());
        dao.createLikes(likes);
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
                <th>Country</th>
                <th>Year</th>
                <th>Rating</th>
                <th>Comments</th>
            </tr>
            <tr>
                <th><input class="form-control" name="title"/></th>
                <th><input class="form-control" name="contry"/></th>
                <th><input class="form-control" name="year"/></th>
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
                <td><%= movie.getCountry()%></td>
                <td><%= movie.getYear()%></td>
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
