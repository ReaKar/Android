package com.example.giannis.anaptiksi.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.giannis.anaptiksi.Pojo.AndroidResults;
import com.example.giannis.anaptiksi.R;
import com.example.giannis.anaptiksi.Requests.HttpRequestResults;
import com.example.giannis.anaptiksi.Requests.HttpTotalResults;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giannis on 1/18/16.
 */


public class TotalResults extends Fragment implements View.OnClickListener {

    ArrayList<String> headersArrayList = new ArrayList<String>();
    Button button;
    EditText text;
    ListView list;
    ArrayAdapter<String> adapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.totalresults, container, false);


        // add headers values
        button=(Button)rootView.findViewById(R.id.totalbut);
        button.setOnClickListener(this);
        text=(EditText)rootView.findViewById(R.id.totaltext);
        list=(ListView)rootView.findViewById(R.id.totallist);
        this.adapter= new ArrayAdapter<>(getContext(),
                R.layout.rowlayout, R.id.label, new ArrayList());
        list.setAdapter(this.adapter);
        return rootView;
    }
    public void onClick(View v){
        if(v.getId()==R.id.totalbut){
            adapter.clear();
            Log.e("ela", "leme");
            int tex=Integer.parseInt(text.getText().toString());
            HttpTotalResults results=new HttpTotalResults(){
                public void onPostExecute(List<AndroidResults> res) {
                    if (res != null) {
                        for (AndroidResults sa : res) {
                            adapter.add(sa.toString());
                        }
                    }
                }
            };
            results.setNumberofRes(tex);
            results.execute();
            adapter.notifyDataSetChanged();

        }
    }
}
