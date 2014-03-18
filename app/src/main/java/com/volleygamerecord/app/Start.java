package com.volleygamerecord.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


/**
 * Created by Jay on 2014/3/18.
 */
public class Start  extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button btn_startGameStart = (Button)findViewById(R.id.button_startGameStart);
        btn_startGameStart.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(Start.this, GameStart1.class);
                startActivity(intent);
                onPause();
            }

        });

        //button_startManageTeam
        Button btn_startManageTeam = (Button)findViewById(R.id.button_startManageTeam);
        btn_startManageTeam.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(Start.this, Team1.class);
                startActivity(intent);

            }

        });

        //button_startManageTeam
        Button btn_startOnlineRecord = (Button)findViewById(R.id.button_startOnlineRecord);
        btn_startOnlineRecord.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(Start.this, MyGame.class);
                startActivity(intent);

            }
        });

        //button_startConfiguration
//        Button btn_startConfiguration = (Button)findViewById(R.id.button_startConfiguration);
//        btn_startConfiguration.setOnClickListener(new Button.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent();
//                intent.setClass(Start.this, 沒有設定.class);
//                startActivity(intent);
//
//            }
//        });
    }
}
