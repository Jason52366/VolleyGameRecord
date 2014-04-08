package com.volleygamerecord.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by A on 2014/3/18.
 */
public class Record1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_record1);
        Button btn_set_end= (Button)findViewById(R.id.btn_set_end);
        btn_set_end.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick (View v){

                Intent intent = new Intent();
                intent.setClass(Record1.this, Count1.class);
                startActivity(intent);
            }

        });


        setContentView(R.layout.activity_record1);
        Button btn_record1GetPoint= (Button)findViewById(R.id.btn_record1GetPoint);
        btn_record1GetPoint.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick (View v){

                Intent intent = new Intent();
                intent.setClass(Record1.this, Record2.class);
                startActivity(intent);
            }

        });


        setContentView(R.layout.activity_record1);
        Button btn_record1LosePoint= (Button)findViewById(R.id.btn_record1LosePoint);
        btn_record1LosePoint.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick (View v){

                Intent intent = new Intent();
                intent.setClass(Record1.this, Record3.class);
                startActivity(intent);
            }

        });
    }
}
