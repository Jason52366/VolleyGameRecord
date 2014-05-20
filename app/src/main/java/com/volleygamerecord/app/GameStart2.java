package com.volleygamerecord.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
    ArrayList numberList = new ArrayList();
    ArrayList positionList = new ArrayList();
    ArrayList playerNameList = new ArrayList();

    ArrayList choosenPlayer = new ArrayList();

    ListView listInput;
    ArrayList<PlayerInfo> infoItems;
    PlayerInfoAdapter infoListAdapter;

    String teamName =  DataCenter.getInstance().getStringValue("team");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamestart2);
        listInput = (ListView) findViewById(R.id.listView_gamestart2Player);
        infoItems = new ArrayList<PlayerInfo>();
        infoListAdapter = new PlayerInfoAdapter(this, infoItems);
        listInput.setAdapter(infoListAdapter);
        getPlayerFromParse();

        //選擇要上場的球員
        listInput.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    if(!infoItems.get(position).getOnCourt() && choosenPlayer.size() < 6 ){
                    view.setBackgroundColor(Color.GREEN);
                    infoItems.get(position).setOnCourt(Boolean.TRUE);
                    choosenPlayer.add(infoItems.get(position));
                    Log.d("!!!!!OOOO!!!!!",""+choosenPlayer.size());
                    }else{
                    view.setBackgroundColor(Color.TRANSPARENT);
                    infoItems.get(position).setOnCourt(Boolean.FALSE);
                    int i = choosenPlayer.indexOf(infoItems.get(position));
                    choosenPlayer.remove(i);
                }

            }
        });

        //button_Sure
        Button btn_gamestart2Sure = (Button)findViewById(R.id.button_gamestart2Sure);
        btn_gamestart2Sure.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                if ((choosenPlayer.size() <7)){
                    DataCenter.getInstance().setPlayerArray(choosenPlayer);
                    Intent intent = new Intent();
                    intent.setClass(GameStart2.this, Record1.class);
                    startActivity(intent);
                    onPause();
                }
            }
        });
        //召喚了datacenter YOOOOOOO
        //ArrayList abc = DataCenter.getInstance().getTeamArray();
        //Log.e("!!!!!", abc.toString() );
    }



    private void getPlayerFromParse(){
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Team");
        query.whereEqualTo("teamName",teamName);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                if (e == null) {
                        numberList = (ArrayList)parseObjects.get(0).get("playerNumber");
                        positionList =  (ArrayList) parseObjects.get(0).get("position");
                        playerNameList =  (ArrayList)parseObjects.get(0).get("playerName");
                    for(int i = 0;i < numberList.size() ; i++ ) {
                        String num = numberList.get(i).toString();
                        String pos = positionList.get(i).toString();
                        String nam = playerNameList.get(i).toString();
                        infoItems.add(new PlayerInfo(num, nam, pos));
                        listInput.setAdapter(infoListAdapter);

                    }
                } else {
                    Log.e("parseReturn", e.toString());
                }
            }

        });
    }

}
