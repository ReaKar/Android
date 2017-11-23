package com.example.giannis.anaptiksi.Tabs;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.giannis.anaptiksi.DatabaseHandler;
import com.example.giannis.anaptiksi.Pojo.AndroidJobs;
import com.example.giannis.anaptiksi.R;
import com.example.giannis.anaptiksi.Activities.TabActivity;
import com.example.giannis.anaptiksi.Requests.HttpPostJobs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by giannis on 1/18/16.
 */

public class AddJobs extends Fragment implements View.OnClickListener {
    Button buttonadd;
    Button buttondel;
    Button buttonsend;
    EditText text;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> oldadapter;
    ListView list;
    ListView old;
    List<String> jobs;
    int pos;
    int jobposition;
    String jobname;
    public AddJobs(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.addjobslayout, container, false);
         buttonadd=(Button)rootView.findViewById(R.id.add);
        buttonadd.setOnClickListener(this);
        buttondel=(Button)rootView.findViewById(R.id.delete);
        buttondel.setOnClickListener(this);
        buttonsend=(Button)rootView.findViewById(R.id.send);
        buttonsend.setOnClickListener(this);
        text=(EditText)rootView.findViewById(R.id.jobs);
        //buttonadd.setOnClickListener(this);
         jobs=new ArrayList<>();
        this.adapter= new ArrayAdapter<>(getContext(),
                R.layout.rowlayout, R.id.label, jobs);
        old=(ListView)rootView.findViewById(R.id.listView2);
        oldadapter= TabActivity.getadapt();
        old.setAdapter(oldadapter);
        old.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Object o = old.getItemAtPosition(position);
                text.setText("");
                text.setText("" + o);
                pos = position;
            }
        });
        list=(ListView)rootView.findViewById(android.R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Object o = list.getItemAtPosition(position);
                jobname=""+o;
                jobposition=position;
            }
        });
        list.setAdapter(this.adapter);

        return rootView;
    }

    public void onClick(View v){
        if(v.getId()==R.id.add){
            Log.d("ela", "leme");
            String tex=text.getText().toString();

            this.adapter.add(tex);
            this.adapter.notifyDataSetChanged();
        }
        else if(v.getId()==R.id.delete){


            jobs.remove(jobposition);
            this.adapter.notifyDataSetChanged();
        }
        else if(v.getId()==R.id.send){

            List<AndroidJobs> mylist=new LinkedList<>();
            for(int i=0;i<this.adapter.getCount();i++){
                String job=this.adapter.getItem(i);
                String[] array=job.split(",");
                if(array.length==3) {
                    mylist.add(new AndroidJobs(i, array[0], array[1], Integer.parseInt(array[2]), TabActivity.getPressed()));
                }
            }


                HttpPostJobs post = new HttpPostJobs();
                post.sendParams(TabActivity.getPressed(), mylist);
                post.execute();
                this.adapter.clear();
                this.adapter.notifyDataSetChanged();

            }

        }
    }


