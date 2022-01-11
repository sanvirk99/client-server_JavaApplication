package ca.cmpt213.a4.client.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/***
 * Establishes Connection with server via HttpUrlConnection
 * Receives and process payload from serveR
 * payload formatted into JsonArray
 * Responsible for getting the list of items from server
 * https://stackoverflow.com/questions/21404252/post-request-send-json-data-java-httpurlconnection
 */
public class GetRequest {

    private static HttpURLConnection connection;
    private JsonArray listArray;

    public GetRequest() {
    }


    public JsonArray getJsonArray() {

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try {

            URL url = new URL("http://localhost:8080/listAll");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            //https://www.youtube.com/watch?v=qzRKa8I36Ww&t=133s&ab_channel=CodingMaster-ProgrammingTutorials
            //request setup
            int status = connection.getResponseCode();

            //System.out.println(status);

            if (status > 299) {

                reader = new BufferedReader((new InputStreamReader(connection.getErrorStream())));
            } else {
                reader = new BufferedReader((new InputStreamReader(connection.getInputStream())));

            }
            while ((line = reader.readLine()) != null) {

                responseContent.append(line);
            }

            String s = responseContent.toString();
            JsonElement list = JsonParser.parseString(s);
            listArray = list.getAsJsonArray();


        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {
            connection.disconnect();
        }

        return listArray;
    }

}
