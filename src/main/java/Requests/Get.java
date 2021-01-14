package Requests;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Get {
    public ArrayList<ArrayList> Response(URL myURL) {
        //ArrayList of ArrayList that stores all info from the DB
        ArrayList<ArrayList> AllItems = new ArrayList<ArrayList>();

        try {
            HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("charset", "utf-8");

            String jsonS = "";
            String inputLine;

            BufferedReader in = new BufferedReader(new InputStreamReader(myURL.openStream()));

            // Read the body of the response
            while ((inputLine = in.readLine()) != null) {
                jsonS += inputLine;
            }
            in.close();

            Gson gson = new Gson();
            AllItems = gson.fromJson(jsonS, ArrayList.class);
            System.out.println(AllItems);
        }
        catch (Exception e) {
            System.out.println("Something went wrong");
        }

        return AllItems;
    }
}
