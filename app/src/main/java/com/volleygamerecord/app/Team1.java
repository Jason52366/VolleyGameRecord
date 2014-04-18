package com.volleygamerecord.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay on 2014/3/18.
 */
public class Team1 extends Activity {

    ArrayList<String> items;
    ArrayList<String> objectsId;
    ArrayAdapter<String> adapter;
    ListView listInput;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team1);
        String userName =  DataCenter.getInstance().getStringValue("parseUserName");

        items = new ArrayList<String>();
        objectsId = new ArrayList<String>();

        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);


        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Team");
        query.whereEqualTo("userName",userName);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                if (e == null) {
                    for (int i = 0; i < parseObjects.size(); i++) {
                            items.add(parseObjects.get(i).getString("teamName"));
                            objectsId.add(parseObjects.get(i).getObjectId());
                    }
                    Log.d("Team1","ourteamName "+items.get(1));
                } else {
                    Log.e("parseReturn", e.toString());
                }
            }
        });



        Button button_addTeam = (Button)findViewById(R.id.button_addTeam);
        button_addTeam.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(Team1.this, Team2.class);
                startActivity(intent);

            }

        });

    }}
