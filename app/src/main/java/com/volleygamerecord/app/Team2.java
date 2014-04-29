package com.volleygamerecord.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Jay on 2014/3/18.
 */
public class Team2 extends Activity {


    ListView listInput;
    ArrayList<String> items;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team2);

        EditText teamName = (EditText)findViewById(R.id.editText_team2TeamName);
        listInput = (ListView) findViewById(R.id.listview_team2List);
        adapter = new ArrayAdapter<String>(this, R.layout.listcontent_team2, items);
        items = new ArrayList<String>();




    }

}
