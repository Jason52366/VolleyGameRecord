package com.volleygamerecord.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Jay on 2014/4/11.
 */
public class Count1_MyGame extends Activity {
    List scoreList = null;
    String obID = null;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count1);

        //獲得從Mygame選擇的比賽objectID
        obID = DataCenter.getInstance().getStringValue("objectsId");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");
        query.getInBackground(obID, new GetCallback<ParseObject>() {
            public void done(ParseObject gameData, ParseException e) {
                if (e == null) {
                    String rivalName = gameData.getString("rivalTeam");
                    String date = gameData.getString("date");
                    scoreList = gameData.getList("score");
                    Log.d("Count1", "The game was held on "+date);
                    Log.d ("Count1","OurRival is "+rivalName);
                    Log.d ("Count1", "Our Score is "+scoreList.get(0));
                    Log.d("Count1", "Rival Score is "+scoreList.get(1));

                    //比賽資訊的顯示
                    TextView dataContent = (TextView)findViewById(R.id.textView_count1);
                    dataContent.setText("比賽| "+date+"\n"+"對手| "+rivalName+"\n"+"比分| "
                    +scoreList.get(0)+":"+scoreList.get(1));
                }
                else {
                    // something went wrong
                }
            }
        });

        TextView textView_count1 = (TextView)findViewById(R.id.textView_count1);
        Button btn_next_game = (Button)findViewById(R.id.btn_next_game);
        btn_next_game.setAlpha(0);


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

