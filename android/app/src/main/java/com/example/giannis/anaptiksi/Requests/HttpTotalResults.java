package com.example.giannis.anaptiksi.Requests;

import android.os.AsyncTask;
import android.util.Log;

import com.example.giannis.anaptiksi.Pojo.AndroidResults;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by giannis on 2/8/16.
 */
public class HttpTotalResults extends AsyncTask<Void, Void, List<AndroidResults>> {
    int numberofresults;
    List<AndroidResults> res;
    public List<AndroidResults> doInBackground(Void... params) {
        try {


            final String url = "http://192.168.1.2:8081/myapp/android/getres/"+numberofresults;
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            List<LinkedHashMap> sahash = restTemplate.getForObject(url, List.class);
            res=new LinkedList<>();
            for(LinkedHashMap map : sahash){
                res.add(new AndroidResults((String)map.get("verboseLevel"),(String)map.get("debuggingLevel"),(String)map.get("status")
                        ,(String)map.get("address"),(String)map.get("hostnames"),(String)map.get("ports"),(int)map.get("nmapjobs_idnmapjobs"),(String)map.get("sa_hashkey")));
            }
            Log.i("totalres ", "Results: " + res.size());

        } catch (Exception e) {
            Log.e("totalres", e.getMessage(), e);
        }

        return res;
    }

    public void setNumberofRes(int numberofRes){
        this.numberofresults=numberofRes;
    }
}
