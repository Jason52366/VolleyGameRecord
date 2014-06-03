package com.volleygamerecord.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay on 2014/3/18.
 */
public class Team2 extends Activity {
    ListView listInput;
    ArrayList<PlayerInfo> infoItems;
    PlayerInfoAdapter infoListAdapter;

    EditText teamName;
    EditText editname;
    EditText editnum;
    EditText editpos;

    Button   addnewteam;
    Button   addnewPlayer;

    Boolean addOk =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team2);

        FindEachViewId();
        InfoSetOnKeyListener();
        SettingListView();
        AddNewPlayerToInfoItem();       //btn_listener
        AddNewTeam();                   //btn_listener
        DeleteTeam();                   //longClick_listener
    }

    private void FindEachViewId(){
        teamName = (EditText)findViewById(R.id.editText_team2TeamName);
        editname = (EditText)findViewById(R.id.editText_team2Player);
        editnum = (EditText)findViewById(R.id.editText_team2Number);
        editpos = (EditText)findViewById(R.id.editText_team2Position);
        addnewteam = (Button) findViewById(R.id.button_team2AddNewTeam);
        addnewPlayer = (Button)findViewById(R.id.button_team2AddNewPlayer);
    }

    private void InfoSetOnKeyListener(){
        teamName.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return true;
                }
                return false;
            }
        });
        editpos.setOnKeyListener(new View.OnKeyListener(){
            public boolean onKey(View v, int keyCode, KeyEvent event){
                if((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)){
                    return true;
                }
                return false;
            }
        });

        editname.setOnKeyListener(new View.OnKeyListener(){
            public boolean onKey(View v, int keyCode, KeyEvent event){
                if((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)){
                    return true;
                }
                return false;
            }
        });
        editnum.setOnKeyListener(new View.OnKeyListener(){
            public boolean onKey(View v, int keyCode, KeyEvent event){
                if((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)){
                    return true;
                }
                return false;
            }
        });
    }

    private void checkInfo(){
        String tmp = teamName.getText().toString();
        if(infoItems.size()!=0 && !(tmp.equals(""))){
            addOk = true;
        }
    }

    private void SettingListView(){
        listInput = (ListView) findViewById(R.id.listview_team2List);
        infoItems = new ArrayList<PlayerInfo>();
        infoListAdapter = new PlayerInfoAdapter(this, infoItems);
        listInput.setAdapter(infoListAdapter);

    }


    private void AddNewPlayerToInfoItem(){
        addnewPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //----
                String playerNum = editnum.getText().toString();
                String playerName = editname.getText().toString();
                String playerPosition = editpos.getText().toString();

                infoItems.add(new PlayerInfo(playerNum,playerName,playerPosition));

                editnum.setText("");
                editname.setText("");
                editpos.setText("");
                editnum.requestFocus();  //cursor回到填number那邊

                listInput.setAdapter(infoListAdapter);

            }
        });
    }

    private void AddNewTeam(){
        addnewteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog.show(Team2.this,"", "請稍等", true );
                checkInfo();
                if (addOk) {
                    AddNewTeamOnParse();
                    Team2.this.finish();
                }else{
                    new AlertDialog.Builder(Team2.this)
                            .setMessage("隊伍資料不可空白唷")
                            .setPositiveButton("了解", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            })
                            .show();
                }
            }
        });

    }

    private void AddNewTeamOnParse() {
        //----
        List<String> nameList = new ArrayList<String>();
        List<String> numList = new ArrayList<String>();
        List<String> posList = new ArrayList<String>();
        for(int i = 0; i < infoItems.size(); i++) {
            nameList.add(infoItems.get(i).getName());
            numList.add(infoItems.get(i).getNumber());
            posList.add(infoItems.get(i).getPosition());
        }
        //----  Parse物件新增
        ParseObject teamInfo = new ParseObject("Team");
        ParseUser user = ParseUser.getCurrentUser();
        teamInfo.put("userName", user.getUsername());
        teamInfo.put("user", user);
        teamInfo.put("teamName", teamName.getText().toString());
        teamInfo.put("playerNumber", numList);
        teamInfo.put("playerName", nameList);
        teamInfo.put("position", posList);
        //----  Parse
        teamInfo.pinInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.e("parseReturn", "成功上傳新隊伍");
                    Intent endNewTeam = new Intent();
                    endNewTeam.setClass(Team2.this, Team1.class);
                    startActivity(endNewTeam);
                } else {
                    Log.e("parseReturn", e.toString());
                }
            }
        });
    }

    private void DeleteTeam(){
        //長按可刪除
        listInput.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int pos = i;
                new AlertDialog.Builder(Team2.this)
                        .setTitle("刪除列")
                        .setMessage("你確定要刪除?")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                infoItems.remove(pos);
                                listInput.setAdapter(infoListAdapter);
                            }
                        })
                        .setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .show();
                return false;
            }
        });
    }
}
