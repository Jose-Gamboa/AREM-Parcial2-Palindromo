package com.arem.palindromo;

import static spark.Spark.*;

/**
 *
 * @author jose.gamboa
 */
public class Palindromo {


    public static void main(String[] args) {
        port(getPort());
        get("/espalindromo",(req, res) -> {
            String response = validatePalindromo(req.queryParams("value"));
            res.type("application/json");
            return "{\"operation\":\"palíndromo\"," +
                    "\"output\":"+ "\"" + response +"\"}";
        });
    }

    public static String validatePalindromo(String value){
        int number = value.length()/2-1;
        for (int i = 0; i < number; i++){
            if (value.charAt(i) != value.charAt(value.length()-i-1)){
                return "No es un palíndromo";
            }
        }
        return "Es un palíndromo";
    }
    
    public static Integer getPort(){
         if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}
