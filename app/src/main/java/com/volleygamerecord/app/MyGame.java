package com.volleygamerecord.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A on 2014/3/18.
 */
public class MyGame extends Activity {
    ListView listInput;
    ArrayList<String> items;
    ArrayList<String> objectsId;
    ArrayAdapter<String> adapter;
    Button back = null;

    protected void onCreate(Bundle savedInstanceState) {
        NetworkOnMainThreadException();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygame);
        back = (Button)findViewById(R.id.button_MyGame_Back);
        SettingTableView();//---製作表格前置
        GetDataFromParse();//---拿Parse資料
        ShowGameData();    //---看比賽資料
        DeleteGameData();  //---刪比賽資料
        Back_Btn();        //---返回

    }

    private void NetworkOnMainThreadException(){
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }

    private void SettingTableView(){
        listInput = (ListView) findViewById(R.id.listView);
        items = new ArrayList<String>();
        objectsId = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

    }

    private void GetDataFromParse(){
        final ProgressDialog dialog = ProgressDialog.show(MyGame.this,"", "請等待...", true);
        //get all objects from local data store
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("GameScore");
        query.fromLocalDatastore();
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                if (e == null) {
                    for(int i = 0; i < parseObjects.size(); i++) {
                        if(parseObjects.get(i).getString("userName").equals(DataCenter.getInstance().getStringValue("parseUserName"))) {
                            items.add(parseObjects.get(i).getString("ourTeam") + " v.s " +
                                    parseObjects.get(i).getString("rivalTeam"));
                            objectsId.add(parseObjects.get(i).getObjectId());
                        }
                    }
                    listInput.setAdapter(adapter);
                    //如果完成抓取資料的動作，將請等待的對話框關閉
                    dialog.dismiss();
                } else {
                    Parse.initialize(MyGame.this, "OOyy4I805eCgkyEGCiZtAH2RybkVl2tWi4qulbkw", "AOXZIHWss8wAiupkyTQuhEelITKfQ3LUeXAdHVTL");
                    ParseFacebookUtils.initialize("1393614940913937");
                    MyGame.this.recreate();
                }
            }
        });
    }

    private void ShowGameData(){
        listInput.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String obID = objectsId.get(position);
                DataCenter.getInstance().setValue("objectsId",obID);
                Intent intent = new Intent();
                intent.setClass(MyGame.this, Count1_MyGame.class);
                startActivity(intent);
            }
        });
    }

    private void DeleteGameData(){
        listInput.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int pos = i;
                new AlertDialog.Builder(MyGame.this)
                        .setTitle("刪除列")
                        .setMessage("你確定要刪除?")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                items.remove(pos);
                                listInput.setAdapter(adapter);
                                //delete object in parse
                                try {
                                    ParseObject.createWithoutData("GameScore",objectsId.get(pos)).deleteEventually();
                                    objectsId.remove(pos);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .show();
                return true;

            }
        });

    }

    private void Back_Btn() {
        back.setText("返回");
        back.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent();
                //intent.setClass(MyGame.this, MyGame.class);
                //startActivity(intent);
                MyGame.this.recreate();

            }
        });
    }
}
