package com.volleygamerecord.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    ArrayList<TextView> posList = new ArrayList<TextView>();

    ListView listInput;
    ArrayList<PlayerInfo> infoItems;
    PlayerInfoAdapter infoListAdapter;

    TextView Pos1;
    TextView Pos2;
    TextView Pos3;
    TextView Pos4;
    TextView Pos5;
    TextView Pos6;
    TextView PosL1;
    TextView PosL2;

    String teamName =  DataCenter.getInstance().getStringValue("team");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamestart2);
        FindEachViewById();
        SettingListView();
        getPlayerFromParse();
        ChoosePlayer();         //ItemClickListner
        SureBtn();              //Sure_btnListener
    }

    private void FindEachViewById(){
        Pos1 = (TextView)findViewById(R.id.textView_gamestart2Pos1);
        Pos2 = (TextView)findViewById(R.id.textView_gamestart2Pos2);
        Pos3 = (TextView)findViewById(R.id.textView_gamestart2Pos3);
        Pos4 = (TextView)findViewById(R.id.textView_gamestart2Pos4);
        Pos5 = (TextView)findViewById(R.id.textView_gamestart2Pos5);
        Pos6 = (TextView)findViewById(R.id.textView_gamestart2Pos6);
        PosL1 = (TextView)findViewById(R.id.textView_gamestart2PosL1);
        PosL2 = (TextView)findViewById(R.id.textView_gamestart2PosL2);
        posList.add(Pos1);
        posList.add(Pos2);
        posList.add(Pos3);
        posList.add(Pos4);
        posList.add(Pos5);
        posList.add(Pos6);
        posList.add(PosL1);
        posList.add(PosL2);
    }

    private void SettingListView(){
        listInput = (ListView) findViewById(R.id.listView_gamestart2Player);
        infoItems = new ArrayList<PlayerInfo>();
        infoListAdapter = new PlayerInfoAdapter(this, infoItems);
        listInput.setAdapter(infoListAdapter);
    }

    private void getPlayerFromParse(){
        final ProgressDialog dialog = ProgressDialog.show(GameStart2.this,"", "請等待...", true);
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Team");
        query.fromLocalDatastore();
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
                    dialog.dismiss();
                } else {
                    Log.e("parseReturn", e.toString());
                }
            }

        });
    }

    private void ChoosePlayer(){
        listInput.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if(!infoItems.get(position).getOnCourt() && choosenPlayer.size() < 9 ){
                    view.setBackgroundColor(Color.GREEN);
                    infoItems.get(position).setOnCourt(Boolean.TRUE);
                    if(choosenPlayer.contains("  ")){
                        int a = choosenPlayer.indexOf("  ");
                        String b = infoItems.get(position).getNumber();
                        choosenPlayer.set(a,b);
                        posList.get(a).setText(b);

                    }else {
                        choosenPlayer.add(infoItems.get(position).getNumber());
                        int a = choosenPlayer.size()-1;
                        posList.get(a).setText(infoItems.get(position).getNumber());
                    }

                }else if (infoItems.get(position).getOnCourt()){
                    view.setBackgroundColor(Color.TRANSPARENT);
                    infoItems.get(position).setOnCourt(Boolean.FALSE);
                    int i = choosenPlayer.indexOf(infoItems.get(position).getNumber());
                    choosenPlayer.set(i, "  ");
                    posList.get(i).setText("  ");
                }else {

                }
            }
        });
    }

    private void SureBtn(){
        Button btn_gamestart2Sure = (Button)findViewById(R.id.button_gamestart2Sure);
        btn_gamestart2Sure.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (choosenPlayer.size() >= 6 && choosenPlayer.indexOf("  ") > 5) {
                    DataCenter.getInstance().setPlayerArray(choosenPlayer);
                    Intent intent = new Intent();
                    intent.setClass(GameStart2.this, Record1.class);
                    startActivity(intent);
                    onPause();
                }else if(choosenPlayer.size() >= 6 && !choosenPlayer.contains("  ")){
                    DataCenter.getInstance().setPlayerArray(choosenPlayer);
                    Intent intent = new Intent();
                    intent.setClass(GameStart2.this, Record1.class);
                    startActivity(intent);
                    onPause();
                }else{
                    Toast.makeText(v.getContext(), "先發六名球員不得有缺", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
