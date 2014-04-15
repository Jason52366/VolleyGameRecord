package com.volleygamerecord.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jay on 2014/3/18.
 */
public class Team1 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team1);


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
