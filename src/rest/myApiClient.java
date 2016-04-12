package rest;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    private final String FIND_MOVIE_BY_TITLE = "http://www.myapifilms.com/imdb/idIMDB?title=MOVIE_TITLE&token=56752bff-55f8-48dc-aa4e-f4c31fb011b9&format=json&language=en-us&aka=0&business=0&seasons=0&seasonYear=0&technical=0&filter=2&exactFilter=0&limit=5&forceYear=0&trailers=0&movieTrivia=0&awards=0&moviePhotos=0&movieVideos=0&actors=0&biography=0&uniqueName=0&filmography=0&bornAndDead=0&starSign=0&actorActress=0&actorTrivia=0&similarMovies=0&adultSearch=0&goofs=0&quotes=0&fullSize=0";
    private final String FIND_MOVIE_BY_ID = "http://www.myapifilms.com/imdb/idIMDB?idIMDB=ID_IMDB&token=56752bff-55f8-48dc-aa4e-f4c31fb011b9&format=json&language=en-us&aka=0&business=0&seasons=0&seasonYear=0&technical=0&trailers=0&movieTrivia=0&awards=0&moviePhotos=0&movieVideos=0&actors=0&biography=0&uniqueName=0&filmography=0&bornAndDead=0&starSign=0&actorActress=0&actorTrivia=0&similarMovies=0&goofs=0&quotes=0&fullSize=0";

    public MovieRest findMovieById(String id) {
        String url = FIND_MOVIE_BY_ID.replace("ID_IMDB", id);

        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

        String json = getJasonStringForUrl(url);
//        System.out.println(json);
//        String json = "{\"plot\":\"test\"}\n";
//        String json = "{\"data\":{\"movies\":[{\"title\":\"Avatar\",\"originalTitle\":\"\",\"year\":\"2009\",\"releaseDate\":\"20091218\",\"directors\":[{\"name\":\"James Cameron\",\"id\":\"nm0000116\"}],\"writers\":[{\"name\":\"James Cameron\",\"id\":\"nm0000116\"}],\"runtime\":\"162 min\",\"urlPoster\":\"http://ia.media-imdb.com/images/M/MV5BMTYwOTEwNjAzMl5BMl5BanBnXkFtZTcwODc5MTUwMw@@._V1_UX182_CR0,0,182,268_AL_.jpg\",\"countries\":[\"USA\",\"UK\"],\"languages\":[\"English\",\"Spanish\"],\"genres\":[\"Action\",\"Adventure\",\"Fantasy\",\"Sci-Fi\"],\"plot\":\"When his brother is killed in a robbery, paraplegic Marine Jake Sully decides to take his place in a mission on the distant world of Pandora. There he learns of greedy corporate figurehead Parker Selfridge's intentions of driving off the native humanoid \\\"Na'vi\\\" in order to mine for the precious material scattered throughout their rich woodland. In exchange for the spinal surgery that will fix his legs, Jake gathers intel for the cooperating military unit spearheaded by gung-ho Colonel Quaritch, while simultaneously attempting to infiltrate the Na'vi people with the use of an \\\"avatar\\\" identity. While Jake begins to bond with the native tribe and quickly falls in love with the beautiful alien Neytiri, the restless Colonel moves forward with his ruthless extermination tactics, forcing the soldier to take a stand - and fight back in an epic battle for the fate of Pandora.\",\"simplePlot\":\"A paraplegic marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.\",\"idIMDB\":\"tt0499549\",\"urlIMDB\":\"http://www.imdb.com/title/tt0499549\",\"rating\":\"7,9\",\"metascore\":\"83\",\"filmingLocations\":[\"Hamakua Coast\",\"Hawaii\",\"USA\"],\"rated\":\"PG-13\",\"votes\":\"862 084\",\"type\":\"Movie\"}]},\"about\":{\"version\":\"2.17.1\",\"serverTime\":\"2016/04/10 19:35:45\"}}\n";
//        System.out.println(json);
        try {
            // TODO: mapper doesn't work well
            return mapper.readValue(json, MovieRest.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
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
//                 TODO: add \ in front of every "
//                CharSequence s1 = "\"";
//                CharSequence s2 = "\\\"";
//                out = out.replace(s1, s2);
//                System.out.println(out);
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
