package com.ek.requestexample;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestTask extends AsyncTask<String, Integer, String> {

    @Override
    protected String doInBackground(String... params) {
        String url_param = params[0];
        String login = params[1];
        String pass = params[2];

        try {
            URL url = new URL(url_param);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");

            int response_code = con.getResponseCode();

            Log.d("REQUEST--->", "Response code: " + response_code);

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();

            JSONObject jsonObject = new JSONObject(response.toString());
            JSONArray users = jsonObject.getJSONArray("results");

            Log.d("REQUEST--->", searchUser(users, login, pass));

            return searchUser(users, login, pass);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    String searchUser(JSONArray users, String login, String pass) throws JSONException {
        for (int i = 0; i < users.length(); i++) {
            JSONObject user = (JSONObject) users.get(i);
            if (login.equals(user.getString("name"))) {
                if (pass.equals(user.getString("status"))) {
                    return "Нашли";
                } else {
                    return "Не тот пароль";
                }
            }
        }
        return "Не нашли";
    }
}
