package com.volleygamerecord.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Created by A on 2014/3/18.
 */
public class Record2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_record2);
        Button btn_record2Confirm= (Button)findViewById(R.id.button_record2confirm);
        btn_record2Confirm.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick (View v){

                ScoreCenter.getInstance().setScoreArray(true,"方法","人");
                Intent intent = new Intent();
                intent.setClass(Record2.this, Record1.class);
                startActivity(intent);
            }

        }
        );
    }



}
