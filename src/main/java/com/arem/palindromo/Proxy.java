package com.arem.palindromo;

import static spark.Spark.*;

public class Proxy {

    public static void main(String[] args) {
        port(getPort());
        staticFileLocation("/public");
        get("/espalindromo",(req, res) -> {
            String response = "Es palíndromo";
            String value = req.queryParams("value");
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

}
