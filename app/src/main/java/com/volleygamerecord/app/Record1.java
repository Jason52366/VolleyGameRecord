package com.volleygamerecord.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.parse.ParseObject;

import java.util.ArrayList;

/**
 * Created by A on 2014/3/18.
 */
public class Record1 extends Activity {

    ArrayList gameScore = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record1);

        String aaa = "a";
        String bbb = "b";

        gameScore.add(aaa);
        gameScore.add(bbb);
        Button btn_startManageTeam = (Button)findViewById(R.id.button_record1UploadGame);
        btn_startManageTeam.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                Log.e("UPPPPP", gameScore.toString());
                ParseObject gameScore = new ParseObject("GameScore");
                gameScore.put("score", gameScore);
                gameScore.put("userName", "ABC");
                gameScore.saveInBackground();

                Intent 企圖 = new Intent();
                企圖.setClass(Record1.this, Start.class);
                startActivity(企圖);

            }

        });





    }
}
