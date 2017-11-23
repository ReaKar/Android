package com.example.giannis.anaptiksi.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.giannis.anaptiksi.Activities.TabActivity;
import com.example.giannis.anaptiksi.Pojo.AndroidJobs;
import com.example.giannis.anaptiksi.R;
import com.example.giannis.anaptiksi.Requests.HttpPostJobs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by giannis on 1/18/16.
 */


public class StopJobs extends Fragment implements View.OnClickListener {

    ArrayList<String> headersArrayList = new ArrayList<String>();
    ListView mylistview;
    Button button;
    String jobname;
    int jobposition;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.stopjobs, container, false);
        mylistview=(ListView)rootView.findViewById(R.id.stopj);
        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Object o = mylistview.getItemAtPosition(position);
                jobname = "" + o;
                jobposition = position;
            }
        });
        mylistview.setAdapter(TabActivity.getjobadapter());
        button=(Button)rootView.findViewById(R.id.stopbutton);
        button.setOnClickListener(this);

        return rootView;
    }
    public void onClick(View v){
        if(v.getId()==R.id.stopbutton){
            TabActivity.getJobsList().remove(jobposition);
            TabActivity.getjobadapter().notifyDataSetChanged();
            String[] array=jobname.split(",");
            List<AndroidJobs> jobs=new LinkedList<>();
            jobs.add(new AndroidJobs(Integer.parseInt(array[0]),"Stop","true",0,TabActivity.getPressed()));
            HttpPostJobs stopjob=new HttpPostJobs();
            stopjob.sendParams(TabActivity.getPressed(),jobs);
            stopjob.execute();
        }
    }
}