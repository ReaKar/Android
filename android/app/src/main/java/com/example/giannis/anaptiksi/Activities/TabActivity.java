package com.example.giannis.anaptiksi.Activities;


import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.giannis.anaptiksi.DatabaseHandler;
import com.example.giannis.anaptiksi.ListAdapterExpandable;
import com.example.giannis.anaptiksi.Pojo.AndroidJobs;
import com.example.giannis.anaptiksi.Pojo.SaState;
import com.example.giannis.anaptiksi.R;
import com.example.giannis.anaptiksi.Requests.HttpPostCredentials;
import com.example.giannis.anaptiksi.Requests.HttpPostJobs;
import com.example.giannis.anaptiksi.Requests.HttpRequestAllJobs;
import com.example.giannis.anaptiksi.Requests.HttpRequestStatus;
import com.example.giannis.anaptiksi.Requests.HttpSaJobs;
import com.example.giannis.anaptiksi.Tabs.AddJobs;

import com.example.giannis.anaptiksi.Tabs.SaResults;
import com.example.giannis.anaptiksi.Tabs.StopJobs;
import com.example.giannis.anaptiksi.Tabs.TabListener;
import com.example.giannis.anaptiksi.Tabs.TotalResults;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TabActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    public TabActivity(){}
    static String pressed;
    private static ArrayAdapter adapterford;
    private static ArrayAdapter jobadapter;
    private ListAdapterExpandable adapter;
    private Button buttondel;
    private int childpos;

    private SwipeRefreshLayout swipeRefreshLayout;
    // declare array List for all headers in list
    private static ArrayList<String> jobslist=new ArrayList<>();
    ArrayList<String> headersArrayList = new ArrayList<String>();
    private ArrayList<SaState> daysOfWeekArrayList;
    // Declare Hash map for all headers and their corresponding values
    HashMap<String, ArrayList<SaState>> childArrayList = new HashMap<String, ArrayList<SaState>>();

    ActionBar.Tab totresTab, addjobTab, saresTab,stopjobsTab;

    Fragment totalresultsfrag = new TotalResults();
    Fragment stopjobsfrag = new StopJobs();
    Fragment saresultsfrag = new SaResults();

    Fragment addjobsfrag = new AddJobs();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        // Asking for the default ActionBar element that our platform supports.
        ActionBar actionBar = getSupportActionBar();

        // Screen handling while hiding ActionBar icon.
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        // Screen handling while hiding ActionBar icon.
        actionBar.setDisplayShowHomeEnabled(false);

        actionBar.setDisplayShowTitleEnabled(false);
        buttondel =(Button) findViewById(R.id.buttondel);
        buttondel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<AndroidJobs> jobs=new LinkedList<>();
                jobs.add(new AndroidJobs(0,"exit(0)","true",0,pressed));
                HttpPostJobs stopjob=new HttpPostJobs();
                stopjob.sendParams(pressed,jobs);
                stopjob.execute();
                childArrayList.get("SAs Results").remove(childpos);
                adapter.notifyDataSetChanged();
            }
        });
        this.adapterford = new ArrayAdapter<>(getApplicationContext(),
                R.layout.rowlayout, R.id.label, new ArrayList());

        this.jobadapter=new ArrayAdapter<>(getApplicationContext(),
                R.layout.rowlayout, R.id.label, jobslist);
        final ExpandableListView listv=(ExpandableListView)findViewById(R.id.expListView);
        headersArrayList.add("SAs Results");
        // headersArrayList.add("Delete SA");

        swipeRefreshLayout.setOnRefreshListener(this);

        new HttpPostCredentials().execute();
        // add add child content

         daysOfWeekArrayList = new ArrayList<SaState>();
       HttpRequestStatus task= new HttpRequestStatus(){
           public void onPostExecute(List<SaState> res) {
               if (res != null) {
                   for (SaState sa : res) {
                       daysOfWeekArrayList.add(sa);
                   }

                   Toast.makeText(TabActivity.this, "Updated", Toast.LENGTH_LONG).show();
               }
               swipeRefreshLayout.setRefreshing(false);
           }
            };
        task.execute();


        childArrayList.put("SAs Results", daysOfWeekArrayList);
        adapter = new ListAdapterExpandable(this, headersArrayList,
                childArrayList);
        listv.setAdapter(adapter);


        // Screen handling while hiding Actionbar title.


        // Creating ActionBar tabs.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Setting custom tab icons.
        stopjobsTab=actionBar.newTab().setIcon(R.drawable.bmw_logo);
        totresTab = actionBar.newTab().setIcon(R.drawable.bmw_logo);
        saresTab = actionBar.newTab().setIcon(R.drawable.toyota_logo);
        addjobTab = actionBar.newTab().setIcon(R.drawable.ford_logo);

        // Setting tab listeners.
        totresTab.setTabListener(new TabListener(totalresultsfrag));
        saresTab.setTabListener(new TabListener(saresultsfrag));
        addjobTab.setTabListener(new TabListener(addjobsfrag));
        stopjobsTab.setTabListener(new TabListener(stopjobsfrag));

        // Adding tabs to the ActionBar.
        actionBar.addTab(totresTab);
        actionBar.addTab(saresTab);
        actionBar.addTab(stopjobsTab);
        Log.d("4", "4");

        actionBar.addTab(addjobTab);



        listv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                SaState o = (SaState)adapter.getChild(0, childPosition);
                childpos=childPosition;
                pressed =o.getHashkey();
                HttpSaJobs getjobs=new HttpSaJobs(){
                    public void onPostExecute(List<AndroidJobs> res) {
                        if (res != null) {
                            for (AndroidJobs sa : res) {
                                jobadapter.add(sa.getIdnmapjobs()+","+sa.getNmapjobscol()+","+sa.getFlagperiodic());
                            }
                            jobadapter.notifyDataSetChanged();

                            Toast.makeText(TabActivity.this, "Updated", Toast.LENGTH_LONG).show();
                        }

                    }
                };
                getjobs.setParams(pressed);
                getjobs.execute();
                Log.e("TabAct", pressed);
                return false;
            }
        });

        listv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // TODO: Do your stuff

                Toast.makeText(getApplicationContext(), "Group is Clicked", Toast.LENGTH_LONG).show();
                return false;
            }
        });
        listv.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                // TODO: Do your stuff
                Toast.makeText(getApplicationContext(), "Child is Collapsed", Toast.LENGTH_LONG).show();
            }
        });

        listv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                // TODO: Do your stuff

                Toast.makeText(getApplicationContext(), "Child is Expanded", Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onRefresh() {


            List<AndroidJobs> mylist=new DatabaseHandler(getApplicationContext()).getAndroidJobs();
            if(mylist!=null){
                HttpPostJobs post=new HttpPostJobs();
                post.sendParams(pressed,mylist);
                post.execute();

        HttpRequestStatus task= new HttpRequestStatus(){
            public void onPostExecute(List<SaState> res) {
                daysOfWeekArrayList.clear();
                adapter.notifyDataSetChanged();
                if (res != null) {
                    for (SaState sa : res) {
                        daysOfWeekArrayList.add(sa);
                    }
                    adapter.notifyDataSetChanged();

                    Toast.makeText(TabActivity.this, "Updated", Toast.LENGTH_LONG).show();
                }

            }
        };
        task.execute();
        HttpRequestAllJobs jobs=new HttpRequestAllJobs(){
            public void onPostExecute(List<AndroidJobs> res) {
                if (res != null) {
                    for (AndroidJobs sa : res) {
                        adapterford.add(sa.getNmapjobscol());
                    }
                    adapterford.notifyDataSetChanged();

                    Toast.makeText(TabActivity.this, "Updated", Toast.LENGTH_LONG).show();
                }

            }
        };

        jobs.execute();
        }
        swipeRefreshLayout.setRefreshing(false);

    }
    public static ArrayList getJobsList(){
        return jobslist;
    }
    public static ArrayAdapter getjobadapter(){return jobadapter;}
    public static ArrayAdapter getadapt(){
        return adapterford;
    }
    public static String getPressed(){
        return pressed;
    }
}
