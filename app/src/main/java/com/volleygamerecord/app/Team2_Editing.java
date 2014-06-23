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

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

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
    ArrayList<ParseObject> theTeam;
    String tName = DataCenter.getInstance().getStringValue("teamName");
    String newtName = tName;
    String obID = DataCenter.getInstance().getStringValue("objectsId");

    EditText teamName;
    EditText editname;
    EditText editnum;
    EditText editpos;

    Button confirmTeamEdit;
    Button addnewPlayer;

    Boolean addOk =false;

    ProgressDialog dialog;

    int numInfoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team2);
        FindEachViewId();
        confirmTeamEdit.setVisibility(View.INVISIBLE);
        dialog = ProgressDialog.show(Team2_Editing.this,"", "請等待...", true);
        //InfoSetOnKeyListener();
        SettingListView();
        getPlayerFromParse();

        //Click Event
        EditTeamName();
        AddNewPlayerToInfoItem();  //addnewPlayer.setOnClickListener
        ConfirmEdit();             //by addnew
        DeleteTeam();              //by OnLongClickListener

    }

    private void FindEachViewId(){
        teamName = (EditText)findViewById(R.id.editText_team2TeamName);
        editname = (EditText)findViewById(R.id.editText_team2Player);
        editnum = (EditText)findViewById(R.id.editText_team2Number);
        editpos = (EditText)findViewById(R.id.editText_team2Position);
        confirmTeamEdit = (Button) findViewById(R.id.button_team2AddNewTeam);
        addnewPlayer = (Button)findViewById(R.id.button_team2AddNewPlayer);
        teamName.setText(tName);
        confirmTeamEdit.setText("確認編輯");

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
        String tmp1 = editname.getText().toString();
        String tmp2 = editnum.getText().toString();

        if(!tmp1.equals("") && !tmp2.equals("") && !teamName.equals("")){
            addOk = true;
        }
    }

    private void SettingListView(){
        listInput = (ListView) findViewById(R.id.listview_team2List);
        infoItems = new ArrayList<PlayerInfo>();
        infoListAdapter = new PlayerInfoAdapter(this, infoItems);
        listInput.setAdapter(infoListAdapter);

    }

    private void getPlayerFromParse(){
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Team");
        query.whereEqualTo("objectId",obID);
        //query.fromLocalDatastore();
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
                        numInfoItems = infoItems.size();

                        theTeam = (ArrayList)parseObjects;

                        dialog.dismiss();

                    }
                } else {
                    Log.e("parseReturn_Team2_Editing", e.toString());
                    dialog.dismiss();

                }
            }

        });
    }


    private void EditTeamName(){
        teamName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder editDialog = new AlertDialog.Builder(Team2_Editing.this);
                editDialog.setTitle("-----編輯隊名-----");
                final EditText editText = new EditText(Team2_Editing.this);
                editDialog.setView(editText);
                editText.setText(teamName.getText().toString());
                editDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                        newtName = (editText.getText().toString());
                        teamName.setText(newtName);
                    }
                });
                editDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                        //...
                    }
                });
                editDialog.show();
            }
        });
    }

    private void AddNewPlayerToInfoItem(){
            addnewPlayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkInfo();
                    if(addOk) {
                    //----
                    String playerNum = editnum.getText().toString();
                    String playerName = editname.getText().toString();
                    String playerPosition = editpos.getText().toString();

                    infoItems.add(new PlayerInfo(playerNum, playerName, playerPosition));

                    editnum.setText("");
                    editname.setText("");
                    editpos.setText("");
                    editnum.requestFocus();  //cursor回到填number那邊

                    listInput.setAdapter(infoListAdapter);
                    confirmTeamEdit.setVisibility(View.VISIBLE);
                    addOk = false;

                    }else {
                        new AlertDialog.Builder(Team2_Editing.this)
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

    private void ConfirmEdit(){
        confirmTeamEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!teamName.equals("")){
                    //ParseObject.createWithoutData("Team",objectsId.get(pos)).deleteEventually();
                    //----
                    List<String> nameList = new ArrayList<String>();
                List<String> numList = new ArrayList<String>();
                List<String> posList = new ArrayList<String>();
                if (infoItems.size() > 0) {
                    for (int i = 0; i < infoItems.size(); i++) {
                        nameList.add(infoItems.get(i).getName());
                        numList.add(infoItems.get(i).getNumber());
                        posList.add(infoItems.get(i).getPosition());
                    }

                    theTeam.get(0).put("playerName", nameList);
                    theTeam.get(0).put("playerNumber", numList);
                    theTeam.get(0).put("position", posList);
                    theTeam.get(0).put("teamName", teamName.getText().toString());
                    theTeam.get(0).saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Log.d("球員上傳", "上傳成功");

                        }
                    });
                }

                Intent intent = new Intent();
                intent.setClass(Team2_Editing.this, Team1.class);
                startActivity(intent);
                Team2_Editing.this.finish();
            }else{
                    new AlertDialog.Builder(Team2_Editing.this)
                            .setMessage("隊伍名稱不可空白唷")
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

    private void DeleteTeam(){
        //長按可刪除
        listInput.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int pos = i;
                new AlertDialog.Builder(Team2_Editing.this)
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
