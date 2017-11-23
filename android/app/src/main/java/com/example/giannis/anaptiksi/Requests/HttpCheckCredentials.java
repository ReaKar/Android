package com.example.giannis.anaptiksi.Requests;

import android.os.AsyncTask;
import android.util.Log;

import com.example.giannis.anaptiksi.Pojo.AndroidClient;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by giannis on 2/8/16.
 */
public class HttpCheckCredentials extends AsyncTask<Void, Void, String> {
    AndroidClient client;
    public String doInBackground(Void... params) {
        try {


            final String url = "http://192.168.1.2:8081/myapp/android/checkregister/"+client.getUsername();
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            AndroidClient client=restTemplate.getForObject(url, AndroidClient.class);
            if(this.client.getPassword().equals(client.getPassword())){
                return "true";
            }
        } catch (Exception e) {
            Log.e("HttpRequestStatus", e.getMessage(), e);
        }

        return "false";
    }
    public void setParams(AndroidClient client){
        this.client=client;
    }
}
