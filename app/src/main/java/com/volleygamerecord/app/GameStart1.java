package com.volleygamerecord.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by A on 2014/3/18.
 */
public class GameStart1  extends Activity {
    ArrayAdapter<String> cupArrayList;
    ArrayAdapter<String> ourTeamArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamestart1);

        /*Date*/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt=new Date();
        String dts=sdf.format(dt);
        TextView date = (TextView)findViewById(R.id.textView_gamestart1Date) ;
        date.setText(dts);
        DataCenter.getInstance().setValue("Date",dts);


        //button_gamestart1Sure
        Button btn_gamestart1Sure = (Button)findViewById(R.id.button_gamestart1Sure);
        btn_gamestart1Sure.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(GameStart1.this, GameStart2.class);
                startActivity(intent);
                onPause();
            }

        });



        //spinner_gamestart1Cup
        String[] lunch = {"台大盃", "校長盃",};
        Spinner spinner = (Spinner)findViewById(R.id.spinner_gamestart1Cup);
        cupArrayList = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lunch);
        spinner.setAdapter(cupArrayList);


        //spinner_gamestart1OurTeam
        String[] lunch2 = {"台大心理", "台大球雀",};
        Spinner spinner2 = (Spinner)findViewById(R.id.spinner_gamestart1OurTeam);
        ourTeamArrayList = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lunch2);
        spinner2.setAdapter(ourTeamArrayList);



    }


}
