package com.example.giannis.anaptiksi.Requests;

import android.os.AsyncTask;
import android.util.Log;

import com.example.giannis.anaptiksi.Pojo.AndroidJobs;
import com.example.giannis.anaptiksi.Pojo.AndroidResults;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by giannis on 2/8/16.
 */
public class HttpRequestAllJobs extends AsyncTask<Void, Void, List<AndroidJobs>> {

    List<AndroidJobs> res;
    public List<AndroidJobs> doInBackground(Void... params) {
        try {


            final String url = "http://192.168.1.2:8081/myapp/android/getalljobs";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            List<LinkedHashMap> sahash = restTemplate.getForObject(url, List.class);
            res=new LinkedList<>();
            for(LinkedHashMap map : sahash){
                res.add(new AndroidJobs((int)map.get("idnmapjobs"),(String)map.get("nmapjobscol"),(String)map.get("flagperiodic")
                        ,(int)map.get("timeperiodic"),(String)map.get("sa_hashkey")));
            }
            Log.i("HttpRequestStatus ", "Results: " + res.size());

        } catch (Exception e) {
            Log.e("HttpRequestStatus", e.getMessage(), e);
        }

        return res;
    }

}
