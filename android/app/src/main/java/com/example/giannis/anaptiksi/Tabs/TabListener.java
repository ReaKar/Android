package com.example.giannis.anaptiksi.Tabs;

/**
 * Created by giannis on 1/18/16.
 */


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.widget.Toast;

import com.example.giannis.anaptiksi.R;


public class TabListener implements ActionBar.TabListener {

    private Fragment fragment;

    // The contructor.
    public TabListener(Fragment fragment) {
        this.fragment = fragment;
    }

    // When a tab is tapped, the FragmentTransaction replaces
    // the content of our main layout with the specified fragment;
    // that's why we declared an id for the main layout.

    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

        ft.replace(R.id.act, fragment);
    }

    // When a tab is unselected, we have to hide it from the user's view.

    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        ft.remove(fragment);
    }

    // Nothing special here. Fragments already did the job.

    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}