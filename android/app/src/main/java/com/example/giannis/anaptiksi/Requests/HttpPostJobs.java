package com.example.giannis.anaptiksi.Requests;

import android.os.AsyncTask;
import android.util.Log;

import com.example.giannis.anaptiksi.Pojo.AndroidJobs;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by giannis on 2/8/16.
 */
public class HttpPostJobs extends AsyncTask<Void, Void, Void> {
    List<AndroidJobs> jobs;
    String saname;
    public Void doInBackground(Void... params) {
            try {

                final String url = "http://192.168.1.2:8081/myapp/android/sendjobs";

                for (AndroidJobs jbs : jobs) {
                    Log.e("Post", "mia-dyo-tries");
                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                    restTemplate.postForObject(url, jbs, AndroidJobs.class);
                }

            } catch (Exception e) {
                Log.e("HttpRequestStatus", e.getMessage(), e);
            }


        return null;
    }
    public void sendParams(String saname,List<AndroidJobs> jobs){
        this.jobs=jobs;
        this.saname=saname;
    }
}
