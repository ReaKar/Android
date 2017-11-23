package com.example.giannis.anaptiksi.Tabs;

import android.app.Activity;

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
import android.widget.Toast;

import com.example.giannis.anaptiksi.Activities.TabActivity;
import com.example.giannis.anaptiksi.Pojo.AndroidResults;
import com.example.giannis.anaptiksi.Pojo.SaState;
import com.example.giannis.anaptiksi.R;
import com.example.giannis.anaptiksi.Requests.HttpRequestResults;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thetida on 1/22/16.
 */
public class SaResults extends Fragment implements View.OnClickListener {

    ArrayList<String> headersArrayList = new ArrayList<String>();
    Button button;
    EditText text;
    ListView list;
    ArrayAdapter<String> adapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.saresult, container, false);


        // add headers values
       button=(Button)rootView.findViewById(R.id.resbutton);
        button.setOnClickListener(this);
        text=(EditText)rootView.findViewById(R.id.numofres);
        list=(ListView)rootView.findViewById(R.id.sares);
        this.adapter= new ArrayAdapter<>(getContext(),
                R.layout.rowlayout, R.id.label, new ArrayList());
        list.setAdapter(this.adapter);
        return rootView;
    }
    public void onClick(View v){
        Log.e("ela", "leme");
        if(v.getId()==R.id.resbutton){
            Log.e("ela", "leme");
            int tex=Integer.parseInt(text.getText().toString());
            HttpRequestResults results=new HttpRequestResults(){
                public void onPostExecute(List<AndroidResults> res) {
                    if (res != null) {
                        for (AndroidResults sa : res) {
                            adapter.add(sa.toString());
                        }
                    }
                }
            };
            results.setNumberofRes(tex,TabActivity.getPressed() );
            results.execute();
            adapter.notifyDataSetChanged();
        }
    }
}

