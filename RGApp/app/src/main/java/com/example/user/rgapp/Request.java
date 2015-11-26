package com.example.user.rgapp;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by USER on 11/26/2015.
 */
public class Request  extends AsyncTask<List<NameValuePair>, Void, String> {

    Callback.JSONCallback callback;
    String url;
    String type;

    public Request(String type, String url, Callback.JSONCallback callback) {
        this.callback = callback;
        extension = url;
        this.type = type;
    }

    // What to do Async, in this case its POST/GET
    protected String doInBackground(List<NameValuePair>... pairs) {

        HttpClient httpClient = new DefaultHttpClient();

        if (type.equals("POST")) {
            HttpPost httpPost = new HttpPost(url);

            try {
                // Add your data
                httpPost.setEntity(new UrlEncodedFormEntity(pairs[0], "UTF-8"));

                // Execute HTTP Post Request
                HttpResponse response = httpClient.execute(httpPost);
                String result = EntityUtils.toString(response.getEntity());

                return result;

            } catch (Exception e) {
                Log.v("error", e.toString());
            }
        } else if (type.equals("GET")) {
            try {
                HttpGet httpGet = new HttpGet(url);
                HttpResponse response = httpClient.execute(httpGet);
                response.getStatusLine().getStatusCode();

                String result = EntityUtils.toString(response.getEntity());

                return result;

            } catch (Exception e) {
                Log.v("error", e.toString());
            }
        }

        return "";

    }

    // What to do after AsyncTask
    protected void onPostExecute(String feed) {
        JSONObject JSON = null;
        try {
            JSON = new JSONObject(feed);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        callback.call(JSON);
    }
}
