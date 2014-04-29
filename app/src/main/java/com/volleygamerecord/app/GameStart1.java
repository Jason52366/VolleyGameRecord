package com.volleygamerecord.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by A on 2014/3/18.
 */
public class GameStart1  extends Activity {
    ArrayAdapter<String> cupArrayList;
    ArrayAdapter<String> ourTeamArrayList;

    Spinner spinner = null;
    Spinner spinner2 = null;
    Date dt=new Date();
    String dts = null;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamestart1);

        spinner = (Spinner)findViewById(R.id.spinner_gamestart1Cup);
        spinner2 = (Spinner)findViewById(R.id.spinner_gamestart1OurTeam);

        /*Date*/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dts=sdf.format(dt);
        TextView date = (TextView)findViewById(R.id.textView_gamestart1Date) ;
        date.setText(dts);
        DataCenter.getInstance().setValue("date",dts);

        Button btn_gamestart1Sure = (Button)findViewById(R.id.button_gamestart1Sure);
        btn_gamestart1Sure.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText arena =  (EditText)findViewById(R.id.editText_gamestart1Arena);
                EditText rivaltxt = (EditText)findViewById(R.id.editText_gamestart1RivalTeam);
                String place = arena.getText().toString();
                String rival  = rivaltxt.getText().toString();
                Log.d("TAG", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                Log.d("cup", spinner.getSelectedItem().toString());
                Log.d("team",spinner2.getSelectedItem().toString());
                Log.d("place", place);
                Log.d("rival", rival);
                Log.d("TAG", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                //把比賽訊息傳到DATACENTER
                DataCenter.getInstance().setValue("cup",spinner.getSelectedItem().toString());
                DataCenter.getInstance().setValue("team",spinner2.getSelectedItem().toString());
                DataCenter.getInstance().setValue("place",place);
                DataCenter.getInstance().setValue("rival",rival);
                Intent intent = new Intent();
                intent.setClass(GameStart1.this, GameStart2.class);
                startActivity(intent);
                onPause();
            }

        });


        //spinner_gamestart1Cup
        String[] lunch = {"台大盃", "校長盃",};
        cupArrayList = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lunch);
        spinner.setAdapter(cupArrayList);

        //spinner_gamestart1OurTeam
        String[] lunch2 = {"台大心理", "台大球雀","台大什麼","台大校長"};
        ourTeamArrayList = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lunch2);
        spinner2.setAdapter(ourTeamArrayList);



    }


}
