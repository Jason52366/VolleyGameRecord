package com.volleygamerecord.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A on 2014/3/18.
 */
public class GameStart2  extends Activity {
    ArrayList choosenPlayer = new ArrayList();
    ArrayList numberList = new ArrayList();
    ArrayList positionList = new ArrayList();
    ArrayList playerNameList = new ArrayList();
    ArrayAdapter<String> adapter;

    ListView listInput;

    String teamName =  DataCenter.getInstance().getStringValue("team");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamestart2);

        getPlayerFromParse();

        //button_gamestart2Sure
        Button btn_gamestart2Sure = (Button)findViewById(R.id.button_gamestart2Sure);
        btn_gamestart2Sure.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                SendChoosenPlayer();


                Intent intent = new Intent();
                intent.setClass(GameStart2.this, Record1.class);
                startActivity(intent);
                onPause();
            }
        });
        //召喚了datacenter YOOOOOOO
        //ArrayList abc = DataCenter.getInstance().getTeamArray();
        //Log.e("!!!!!", abc.toString() );
    }

    public void SendChoosenPlayer (){
        choosenPlayer.add("32號");
        choosenPlayer.add("03號");
        choosenPlayer.add("08號");
        choosenPlayer.add("18號");
        choosenPlayer.add("10號");
        choosenPlayer.add("22號");
        DataCenter.getInstance().setPlayerArray(choosenPlayer);
        Log.d("GameStart2", "" + choosenPlayer.toString());
    }

    private void getPlayerFromParse(){

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Team");
        query.whereEqualTo("teamName",teamName);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                if (e == null) {
                        numberList.add(parseObjects.get(0).get("playerNumber"));
                        positionList.add(parseObjects.get(0).get("position"));
                        playerNameList.add(parseObjects.get(0).get("playerName"));

                    for(int i = 0;i < numberList.size() ; i++ ) {
                        String num = (String)numberList.get(i);
                        String pos = (String)positionList.get(i);
                        String nam = (String)playerNameList.get(i);

                    }

                } else {
                    Log.e("parseReturn", e.toString());
                }
            }

        });
    }
}
