package com.example.e_waste_app;

import org.json.JSONObject;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiHelper {

    private static final String BASE_URL = "https://your-api-url.com/";

    public static boolean signup(String email, String password) {
        return sendPost("signup", email, password);
    }

    public static boolean login(String email, String password) {
        return sendPost("login", email, password);
    }

    private static boolean sendPost(String endpoint, String email, String password) {
        try {
            URL url = new URL(BASE_URL + endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            JSONObject json = new JSONObject();
            json.put("email", email);
            json.put("password", password);

            OutputStream os = conn.getOutputStream();
            os.write(json.toString().getBytes());
            os.flush();
            os.close();

            int responseCode = conn.getResponseCode();
            if (responseCode == 200 || responseCode == 201) {
                // Optionally read response
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) response.append(line);
                in.close();
                conn.disconnect();
                return true;
            } else {
                conn.disconnect();
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
