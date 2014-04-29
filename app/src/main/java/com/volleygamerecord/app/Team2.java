package com.volleygamerecord.app;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.ArrayAdapter;
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
    List<PlayerInfo> items;
    Team2Adapter adapter;

    EditText teamName;
    EditText editname;
    EditText editnum;
    EditText editpos;
    Button   addnewteam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team2);

        teamName = (EditText)findViewById(R.id.editText_team2TeamName);

        editname = (EditText)findViewById(R.id.editText_team2Player);
        editnum = (EditText)findViewById(R.id.editText_team2Number);
        editpos = (EditText)findViewById(R.id.editText_team2Position);
        addnewteam = (Button) findViewById(R.id.button_team2AddNewTeam);


        editpos.setOnKeyListener(new View.OnKeyListener(){
            public boolean onKey(View v, int keyCode, KeyEvent event){
                if((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)){
                    AddItem();
                    return true;
                }
                return false;
            }
        });
        editname.setOnKeyListener(new View.OnKeyListener(){
            public boolean onKey(View v, int keyCode, KeyEvent event){
                if((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)){
                    AddItem();
                    return true;
                }
                return false;
            }
        });
        editnum.setOnKeyListener(new View.OnKeyListener(){
            public boolean onKey(View v, int keyCode, KeyEvent event){
                if((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)){
                    AddItem();
                    return true;
                }
                return false;
            }
        });
        addnewteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> nameList = new ArrayList<String>();
                List<String> numList = new ArrayList<String>();
                List<String> posList = new ArrayList<String>();
                for(int i = 0; i < items.size(); i++) {
                    nameList.add(items.get(i).getName());
                    numList.add(items.get(i).getNumber());
                    posList.add(items.get(i).getPosition());
                }

                ParseObject teamInfo = new ParseObject("Team");
                ParseUser user = ParseUser.getCurrentUser();

                teamInfo.put("userName", user.getUsername());
                teamInfo.put("user", user);
                teamInfo.put("teamName",teamName.getText().toString());
                teamInfo.put("playerNumber",numList);
                teamInfo.put("playerName",nameList);
                teamInfo.put("position",posList);
                teamInfo.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            Log.e("parseReturn", "成功上傳新隊伍");
                            Intent endNewTeam = new Intent();
                            endNewTeam.setClass(Team2.this, Team1.class);
                            startActivity(endNewTeam);
                        }else {
                            Log.e("parseReturn",e.toString());
                        }
                    }
                });

            }
        });

        listInput = (ListView) findViewById(R.id.listview_team2List);
        items = new ArrayList<PlayerInfo>();
        adapter = new Team2Adapter(this,items);

        listInput.setAdapter(adapter);

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
                                items.remove(pos);
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
    private void AddItem(){
        if(!editname.getText().toString().equals("") &&
           !editnum.getText().toString().equals("")  &&
           !editpos.getText().toString().equals("") ){
              items.add(new PlayerInfo(editnum.getText().toString(),editname.getText().toString(),editpos.getText().toString()));
              editnum.setText("");
              editname.setText("");
              editpos.setText("");
              editnum.requestFocus();  //cursor回到填number那邊
              listInput.setAdapter(adapter);
        }
    }
    public class PlayerInfo{
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
        public void setNumber(String n){
            number = n;
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
        public void setPosition(String p){
            position = p;
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

}
