package com.volleygamerecord.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by A on 2014/4/1.
 */



public class Count1 extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count1);
        Button btn_next_game = (Button)findViewById(R.id.btn_next_game);
        btn_next_game.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick (View v){

                Intent intent = new Intent();
                intent.setClass(Count1.this, Record1.class);
                startActivity(intent);
            }

        });


        Button btn_end_game = (Button)findViewById(R.id.btn_end_game);
        btn_end_game.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick (View v){

                Intent intent = new Intent();
                intent.setClass(Count1.this, Count2.class);
                startActivity(intent);
            }

        });
    }}
