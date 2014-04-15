package com.volleygamerecord.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.ArrayList;

/**
 * Created by Jay on 2014/4/11.
 */
public class Count1_MyGame extends Activity {

    ArrayList<ParseObject> parseObjects = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count1);

        Integer itemNumber = DataCenter.getInstance().getIntValue("itemNumber");
        ArrayList<ParseObject> parseObjects = DataCenter.getInstance().getTempParseObject();

        Log.d("??!!@@$$%%", ""+itemNumber);

        TextView textView_count1 = (TextView)findViewById(R.id.textView_count1);
//        textView_count1.setText();

        Button btn_next_game = (Button)findViewById(R.id.btn_next_game);
        btn_next_game.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick (View v){

                Intent intent = new Intent();
                intent.setClass(Count1_MyGame.this, Record1.class);
                startActivity(intent);
            }

        });


        Button btn_end_game = (Button)findViewById(R.id.btn_end_game);
        btn_end_game.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick (View v){

                Intent intent = new Intent();
                intent.setClass(Count1_MyGame.this, Count2.class);
                startActivity(intent);
            }

        });
    }}

