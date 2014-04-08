package com.volleygamerecord.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by A on 2014/4/1.
 */
public class Loginsuccess extends Activity {

    String fbName = DataCenter.getInstance().getStringValue("fbName");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginsuccess);

        TextView loginsuccess = (TextView)findViewById(R.id.text_loginsuccess);

        Log.e("fbName", fbName);

        loginsuccess.setText("登入成功，歡迎"+fbName);

        Button btn_startManageTeam = (Button)findViewById(R.id.btn_enter_start);
        btn_startManageTeam.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent 企圖 = new Intent();
                企圖.setClass(Loginsuccess.this, Start.class);
                startActivity(企圖);

            }

        });
    }}
