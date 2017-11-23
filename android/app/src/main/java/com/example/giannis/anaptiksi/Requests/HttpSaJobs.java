package com.example.giannis.anaptiksi.Requests;

import android.os.AsyncTask;
import android.util.Log;

import com.example.giannis.anaptiksi.Pojo.AndroidJobs;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by giannis on 2/8/16.
 */
public class HttpSaJobs extends AsyncTask<Void, Void, List<AndroidJobs>> {
    String saname;
    List<AndroidJobs> res;
    public List<AndroidJobs> doInBackground(Void... params) {
        try {


            final String url = "http://192.168.1.2:8081/myapp/android/gethistoryjobs/"+saname;
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            List<LinkedHashMap> sahash = restTemplate.getForObject(url, List.class);
            res=new LinkedList<>();
            if(sahash!=null) {
                for (LinkedHashMap map : sahash) {
                    res.add(new AndroidJobs((int) map.get("idnmapjobs"), (String) map.get("nmapjobscol"), (String) map.get("flagperiodic")
                            , (int) map.get("timeperiodic"), (String) map.get("sa_hashkey")));
                }
            }
            Log.i("HttpRequestStatus ", "Results: " + res.size());

        } catch (Exception e) {
            Log.e("HttpRequestStatus", e.getMessage(), e);
        }

        return res;
    }
    public void setParams(String saname){
        this.saname=saname;
    }
}
