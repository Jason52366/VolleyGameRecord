package com.volleygamerecord.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by A on 2014/4/1.
 */
public class Count2 extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count2);
        Button btn_back_menu = (Button) findViewById(R.id.btn_back_menu);
        btn_back_menu.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(Count2.this, Start.class);
                startActivity(intent);
            }

        });
    }}