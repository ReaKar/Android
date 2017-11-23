package com.example.giannis.anaptiksi;

/**
 * Created by thetida on 1/18/16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.giannis.anaptiksi.Pojo.SaState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ListAdapterExpandable extends BaseExpandableListAdapter {

    private Context context;
    private List<String> headerArray;
    private HashMap<String, ArrayList<SaState>> childArray;
    private LayoutInflater infalInflater;

    public ListAdapterExpandable(){

    }
    // Initialize constructor for array list
    public ListAdapterExpandable(Context context, ArrayList<String> headerArray,
                                 HashMap<String, ArrayList<SaState>> listChildData) {
        this.context = context;
        this.headerArray = headerArray;
        this.childArray = listChildData;
        infalInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.childArray.get(this.headerArray.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    // Inflate child view

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final SaState childText = (SaState) getChild(groupPosition, childPosition);

        if (convertView == null) {

            convertView = infalInflater.inflate(R.layout.list_sub_child, null);
        }
        ImageView redgreen = (ImageView) convertView.findViewById(R.id.redgreen );

        TextView textViewChild = (TextView) convertView
                .findViewById(R.id.textViewChild);
        ArrayList<SaState> arr=childArray.get("SAs Results");

            textViewChild.setText(childText.getHashkey());
            if (childText.getState().equals("offline")) {
                redgreen.setImageResource(R.drawable.red);
            } else {
                redgreen.setImageResource(R.drawable.green);
            }

        return convertView;
    }

    // return number of headers in list
    @Override
    public int getChildrenCount(int groupPosition) {
        return this.childArray.get(this.headerArray.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.headerArray.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.headerArray.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    // inflate header view

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {

            convertView = infalInflater.inflate(R.layout.list_group_header,
                    null);
        }

        TextView textViewHeader = (TextView) convertView
                .findViewById(R.id.textViewHeader);
        textViewHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}