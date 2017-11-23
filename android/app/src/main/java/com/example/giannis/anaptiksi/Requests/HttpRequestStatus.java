package com.example.giannis.anaptiksi.Requests;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.giannis.anaptiksi.Pojo.SaState;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by nikolas on 29/12/15.
 */

public class HttpRequestStatus extends AsyncTask<Void, Void, List<SaState>> {

    List<SaState> res;
    public List<SaState> doInBackground(Void... params) {
        try {


            final String url = "http://192.168.1.2:8081/myapp/android/getallsa";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            List<LinkedHashMap> sahash = restTemplate.getForObject(url, List.class);
            res=new LinkedList<>();
            for(LinkedHashMap map : sahash){
                res.add(new SaState((String)map.get("hashkey"),(String)map.get("state")));
            }
            Log.i("HttpRequestStatus ", "Results: " + res.size());

        } catch (Exception e) {
            Log.e("HttpRequestStatus", e.getMessage(), e);
        }

        return res;
    }






}