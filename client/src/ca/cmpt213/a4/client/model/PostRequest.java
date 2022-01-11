package ca.cmpt213.a4.client.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

/***
 * Establishes Connection with server via HttpUrlConnection
 * Packages payload into Plain json format to pass to server
 * Responsible for communicating Add ,remove and close operations to server
 * due to design of this program the returned list is not processed
 * that responsibility is given to Get request only when updating list
 * https://stackoverflow.com/questions/21404252/post-request-send-json-data-java-httpurlconnection
 */

public class PostRequest {


    private static HttpURLConnection connection;

    public PostRequest() {
    }

    ;

    public void PostRequestRemove(int index) {



        try {

            URL url = new URL("http://localhost:8080/removeItem");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoOutput(true);

            String jsonInputString = "{\n" +
                    "   \"index\": \"" +
                    index +
                    "\"\n}";
           // System.out.println(jsonInputString);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            //https://www.youtube.com/watch?v=qzRKa8I36Ww&t=133s&ab_channel=CodingMaster-ProgrammingTutorials
            //request setup
            int status = connection.getResponseCode();

           // System.out.println(status);



        } catch (
                MalformedURLException e) {

            e.printStackTrace();

        } catch (
                IOException e) {

            e.printStackTrace();

        } finally {
            connection.disconnect();
        }


    }


    public void PostRequestAdd(String type, String name, String notes, Double price, Double quantity, LocalDate date) {



        try {

            URL url = new URL("http://localhost:8080/addItem");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoOutput(true);

            String jsonInputString = "{" +
                    "            \"type\": \"" +
                    type +
                    "\",\n" +
                    "            \"name\": \"" +
                    name +
                    "\",\n" +
                    "            \"notes\": \"" +
                    notes +
                    "\",\n" +
                    "            \"price\": " +
                    price +
                    ",\n" +
                    "            \"quantity\": " +
                    quantity +
                    ",\n" +
                    "            \"date\": \"" +
                    date +
                    "\"\n" +
                    "        }";
            //System.out.println(jsonInputString);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            //https://www.youtube.com/watch?v=qzRKa8I36Ww&t=133s&ab_channel=CodingMaster-ProgrammingTutorials
            //request setup
            int status = connection.getResponseCode();

            //System.out.println(status);



        } catch (
                MalformedURLException e) {

            e.printStackTrace();

        } catch (
                IOException e) {

            e.printStackTrace();

        } finally {
            connection.disconnect();
        }
    }

    /***
     * is the only get method in Post object
     */
    public void PostRequestClose() {

        try {

            URL url = new URL("http://localhost:8080/exit");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int status = connection.getResponseCode();
           // System.out.println(status);

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {
            connection.disconnect();
        }
    }

}

