package com.arem.palindromo;

import static spark.Spark.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Proxy {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL = "http://localhost:4567/espalindromo?value=";

    public static void main(String[] args) {
        port(getPort());
        staticFileLocation("/public");
        get("/espalindromo",(req, res) -> {
            String value = req.queryParams("value");
            String response = validationInBack(value);
            res.type("application/json");
            return "{\"operation\":\"palíndromo\"," +
                    "\"input\":" + "\"" + value + "\"," +
                    "\"output\":" + "\"" + response +"\"}";
        });
    }

    public static Integer getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080;
    }

    public static String validationInBack(String value) throws IOException {
        URL obj = new URL(GET_URL+value);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println();
            return response.toString().split(":")[1].replace("}","");
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        return "";
    }

}
