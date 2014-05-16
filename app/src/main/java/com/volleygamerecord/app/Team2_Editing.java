package com.volleygamerecord.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay on 2014/5/16.
 */
public class Team2_Editing extends Activity {

    ListView listInput;
    ArrayList<PlayerInfo> infoItems;
    PlayerInfoAdapter infoListAdapter;

    ArrayList numberList = new ArrayList();
    ArrayList positionList = new ArrayList();
    ArrayList playerNameList = new ArrayList();
    String tName = DataCenter.getInstance().getStringValue("teamName");

    EditText teamName;
    EditText editname;
    EditText editnum;
    EditText editpos;

    Button addnewteam;
    Button addnewPlayer;

    Boolean addOk =false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team2);



        listInput = (ListView) findViewById(R.id.listview_team2List);
        infoItems = new ArrayList<PlayerInfo>();
        infoListAdapter = new PlayerInfoAdapter(this, infoItems);
        listInput.setAdapter(infoListAdapter);
        getPlayerFromParse();


    }

    private void getPlayerFromParse(){
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Team");
        query.whereEqualTo("teamName",tName);
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
                        Log.d("numberLALA", "" + num);
                        Log.d("positionLALA", "" + pos);
                        Log.d("nameLALA", "" + nam);
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
