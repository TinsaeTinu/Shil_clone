package com.tinsaeLem.myapplication;

import android.os.Bundle;
import android.content.SharedPreferences;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tinsaeLem.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ScreenA extends AppCompatActivity {

    public ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

        SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);

        ArrayList<String> mylist = new ArrayList<String>();
        mylist.add( prefs.getString("name",""));
        mylist.add( prefs.getString("country_code",""));
        mylist.add( prefs.getString("phone_num",""));
        mylist.add( prefs.getString("Image_Src",""));

        lv = (ListView) findViewById(R.id.lv_first);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mylist);

        lv.setAdapter(adapter);

        Log.v("Cache Data :",  prefs.getString("name",""));
        Log.v("Cache Data :",  prefs.getString("country_code",""));
        Log.v("Cache Data :",  prefs.getString("phone_num",""));
        Log.v("Cache Data :",  prefs.getString("Image_Src",""));



    }
}