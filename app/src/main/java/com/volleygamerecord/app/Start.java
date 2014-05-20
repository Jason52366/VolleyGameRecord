package com.volleygamerecord.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Jay on 2014/3/18.
 */
public class Start  extends Activity {
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button btn_startConfiguration = (Button)findViewById(R.id.button_startConfiguration);
        btn_startConfiguration.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

//                Intent 企圖 = new Intent();
//                企圖.setClass(Start.this, .Setting class);
//                startActivity(企圖);



            }
        });
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        //button_startManageTeam
        Button btn_startManageTeam = (Button)findViewById(R.id.button_startManageTeam);
        btn_startManageTeam.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent 企圖 = new Intent();
                企圖.setClass(Start.this, Team1.class);
                startActivity(企圖);

            }

        });



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
        Button btn_startOnlineRecord = (Button)findViewById(R.id.button_startOnlineRecord);
        btn_startOnlineRecord.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(Start.this, MyGame.class);
                startActivity(intent);

            }
        });

    }

    //
    private static Boolean isExit = false;
    private static Boolean hasTask = false;
    Timer tExit = new Timer();
    TimerTask task = new TimerTask() {

        @Override
        public void run() {
            isExit = false;
            hasTask = true;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        System.out.println("TabHost_Index.java onKeyDown");
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(isExit == false ) {
                isExit = true;
                Toast.makeText(this, "再按一次返回鍵退出程式",

                        Toast.LENGTH_SHORT).show();
                if(!hasTask) {
                    tExit.schedule(task, 0);
                }
            } else {
                finish();
                System.exit(0);
            }
        }
        return false;
    }
}
