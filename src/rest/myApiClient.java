package rest;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by chenggu on 4/9/16.
 */
public class myApiClient {

    private final String FIND_MOVIE_BY_TITLE = "http://www.myapifilms.com/imdb/idIMDB?title=MOVIE_TITLE&token=56752bff-55f8-48dc-aa4e-f4c31fb011b9&format=json&language=en-us&aka=0&business=0&seasons=0&seasonYear=0&technical=0&filter=2&exactFilter=0&limit=1&forceYear=0&trailers=0&movieTrivia=0&awards=0&moviePhotos=0&movieVideos=0&actors=0&biography=0&uniqueName=0&filmography=0&bornAndDead=0&starSign=0&actorActress=0&actorTrivia=0&similarMovies=0&adultSearch=0&goofs=0&quotes=0&fullSize=0";
    private final String FIND_MOVIE_BY_ID = "http://www.myapifilms.com/imdb/idIMDB?idIMDB=ID_IMDB&token=56752bff-55f8-48dc-aa4e-f4c31fb011b9&format=json&language=en-us&aka=0&business=0&seasons=0&seasonYear=0&technical=0&trailers=0&movieTrivia=0&awards=0&moviePhotos=0&movieVideos=0&actors=0&biography=0&uniqueName=0&filmography=0&bornAndDead=0&starSign=0&actorActress=0&actorTrivia=0&similarMovies=0&goofs=0&quotes=0&fullSize=0";

    public MovieRest findMovieById(String id) {
        String url = FIND_MOVIE_BY_ID.replace("ID_IMDB", id);

        MovieRest movieRest = new MovieRest();

        JsonFactory factory = new JsonFactory();
//        ObjectMapper mapper = new ObjectMapper();
        String json = getJasonStringForUrl(url);
        try {
            JsonParser parser = factory.createParser(json);
            while(!parser.isClosed()) {
                JsonToken jsonToken = parser.nextToken();
                if(JsonToken.FIELD_NAME.equals(jsonToken)) {
                    String fieldName = parser.getCurrentName();
//                    System.out.println(fieldName);

                    jsonToken = parser.nextToken();

                    if("idIMDB".equals(fieldName)) {
                        movieRest.setIdIMDB(parser.getValueAsString());
                    }
                    else if("plot".equals(fieldName)) {
                        movieRest.setPlot(parser.getValueAsString());
                    }
                    else if("urlPoster".equals(fieldName)) {
                        movieRest.setUrlPoster(parser.getValueAsString());
                    }
                    else if("title".equals(fieldName)) {
                        movieRest.setTitle(parser.getValueAsString());
                    }
                }
            }
//            System.out.println(movieRest.getPlot());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            // TODO: mapper doesn't work well
//            movieRest = mapper.readValue(json, MovieRest.class);
//            System.out.println(movieRest.getPlot());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return movieRest;
    }

    public void findMovieByTitle(String title) {
        String urlStr = FIND_MOVIE_BY_TITLE.replace("MOVIE_TITLE", title);

        System.out.println(getJasonStringForUrl(urlStr));
    }

    private String getJasonStringForUrl(String urlStr) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream in = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader reader = new BufferedReader(isr);
            String out;
            StringBuffer json = new StringBuffer();
            while((out = reader.readLine()) != null) {
                json.append(out);
            }
            return json.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        myApiClient client = new myApiClient();

//        MovieRest movieRest = client.findMovieByTitle("Avatar");
        MovieRest movieRest = client.findMovieById("tt0499549");
        System.out.println(movieRest.getPlot());
//        System.out.println(movieRest.getIdIMDB());
//        System.out.println(movieRest.getTitle());
//        System.out.println(movieRest.getUrlPoster());

    }
}
