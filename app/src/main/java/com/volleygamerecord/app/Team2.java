package com.volleygamerecord.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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
    Team2Adapter adapter;

    String userName =  DataCenter.getInstance().getStringValue("parseUserName");

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


        listInput = (ListView) findViewById(R.id.listview_team2List);
        infoItems = new ArrayList<PlayerInfo>();
        adapter = new Team2Adapter(this,infoItems);
        listInput.setAdapter(adapter);

        addnewPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewPlayerToInfoItem();
            }
        });

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
                            .setMessage("隊五資料不可空白唷")
                            .setPositiveButton("了解", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            })
                            .show();
                }
            }
        });

        //長按刪除
        DeleteTeam();
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
                    AddNewPlayerToInfoItem();
                    return true;
                }
                return false;
            }
        });
        editpos.setOnKeyListener(new View.OnKeyListener(){
            public boolean onKey(View v, int keyCode, KeyEvent event){
                if((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)){
                    AddNewPlayerToInfoItem();
                    return true;
                }
                return false;
            }
        });
        editname.setOnKeyListener(new View.OnKeyListener(){
            public boolean onKey(View v, int keyCode, KeyEvent event){
                if((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)){
                    AddNewPlayerToInfoItem();
                    return true;
                }
                return false;
            }
        });
        editnum.setOnKeyListener(new View.OnKeyListener(){
            public boolean onKey(View v, int keyCode, KeyEvent event){
                if((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)){
                    AddNewPlayerToInfoItem();
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
            Log.d("OK","true");
        }
    }



    private void AddNewPlayerToInfoItem(){
        //----
        String playerNum = editnum.getText().toString();
        String playerName = editname.getText().toString();
        String playerPosition = editpos.getText().toString();

        infoItems.add(new PlayerInfo(playerNum,playerName,playerPosition));

        editnum.setText("");
        editname.setText("");
        editpos.setText("");
        editnum.requestFocus();  //cursor回到填number那邊

        listInput.setAdapter(adapter);

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
        //----  Parse傳送成功確認
        teamInfo.saveInBackground(new SaveCallback() {
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

    private class PlayerInfo{
        private String number;
        private String name;
        private String position;

        public PlayerInfo(String num,String n, String pos){
            number = num;
            name = n;
            position = pos;
        }
        public String getNumber(){
            return number;
        }
        public void setNumber(String num){
            number = num;
        }
        public String getName(){
            return name;
        }
        public void setName(String n){
            name = n;
        }
        public String getPosition(){
            return position;
        }
        public void setPosition(String pos){
            position = pos;
        }
    }


    public class Team2Adapter extends BaseAdapter{
        private LayoutInflater team2Inflater;
        private List<PlayerInfo> info;

        public Team2Adapter(Context c,List<PlayerInfo> l){
            team2Inflater = LayoutInflater.from(c);
            info = l;
        }
        @Override
        public int getCount() {
            return info.size();
        }
        @Override
        public Object getItem(int pos){
            return info.get(pos);
        }
        @Override
        public long getItemId(int position){
            return info.indexOf(getItem(position));
        }

        @Override
        public View getView(int pos, View convertView, ViewGroup parent){
            convertView = team2Inflater.inflate(R.layout.listcontent_team2, null);
            TextView name = (TextView) convertView.findViewById(R.id.team2ListName);
            TextView number= (TextView) convertView.findViewById(R.id.team2ListNumber);
            TextView position= (TextView) convertView.findViewById(R.id.team2ListPosition);

            PlayerInfo playerinfo = (PlayerInfo) getItem(pos);
            name.setText(playerinfo.getName());
            number.setText(playerinfo.getNumber());
            position.setText(playerinfo.getPosition());

            return convertView;
        }

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
                                listInput.setAdapter(adapter);
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
