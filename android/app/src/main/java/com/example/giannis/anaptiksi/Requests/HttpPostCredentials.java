package com.example.giannis.anaptiksi.Requests;

import android.os.AsyncTask;
import android.util.Log;

import com.example.giannis.anaptiksi.Pojo.AndroidClient;
import com.example.giannis.anaptiksi.Pojo.AndroidJobs;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by giannis on 2/8/16.
 */
public class HttpPostCredentials extends AsyncTask<Void, Void, Void> {
    AndroidClient client;
    public Void doInBackground(Void... params) {
        try {


            final String url = "http://192.168.1.2:8081/myapp/android/register";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.postForObject(url,this.client, AndroidClient.class);


        } catch (Exception e) {
            Log.e("HttpRequestStatus", e.getMessage(), e);
        }

    return null;
    }
    public void setParams(AndroidClient client){
        this.client=client;
    }
}
