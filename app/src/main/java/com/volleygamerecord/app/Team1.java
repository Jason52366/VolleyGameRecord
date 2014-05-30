package com.volleygamerecord.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay on 2014/3/18.
 */
public class Team1 extends Activity {

    ArrayList<String> items;
    ArrayList<String> objectsId;
    ArrayAdapter<String> adapter;
    ListView listInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team1);
        String userName =  DataCenter.getInstance().getStringValue("parseUserName");

        listInput = (ListView) findViewById(R.id.listview_teamName);
        //把資料放入LIST前必備的東西
        items = new ArrayList<String>();
        objectsId = new ArrayList<String>();

        final ProgressDialog dialog = ProgressDialog.show(Team1.this,"", "請等待...", true);

        //從parse拿自己隊伍的資料
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Team");
        query.whereEqualTo("userName",userName);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                if (e == null) {
                    for (int i = 0; i < parseObjects.size(); i++) {
                            items.add(parseObjects.get(i).getString("teamName"));
                            objectsId.add(parseObjects.get(i).getObjectId());
                    }
                    for (int i = 0; i < items.size(); i++){
                        Log.d("teamName",items.get(i));
                    }
                    adapter = new ArrayAdapter<String>(Team1.this, android.R.layout.simple_list_item_1, items);
                    listInput.setAdapter(adapter);
                    dialog.dismiss();

                } else {
                    Log.e("parseReturn", e.toString());
                    dialog.dismiss();

                }
            }
        });
        //增加球隊
        Button button_addTeam = (Button)findViewById(R.id.button_addTeam);
        button_addTeam.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Team1.this, Team2.class);
                startActivity(intent);
                Team1.this.finish();
            }
        });

        //球隊資料
        listInput.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String obID = objectsId.get(position);
                DataCenter.getInstance().setValue("objectsId",obID);
                DataCenter.getInstance().setValue("teamName",items.get(position));
                Intent intent = new Intent();
                intent.setClass(Team1.this, Team2_Editing.class);
                startActivity(intent);
                Team1.this.finish();

            }
        });

        //刪除隊伍
        listInput.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int pos = i;
                new AlertDialog.Builder(Team1.this)
                        .setTitle("刪除列")
                        .setMessage("你確定要刪除?")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                items.remove(pos);
                                listInput.setAdapter(adapter);
                                //delete object in parse
                                try {
                                    ParseObject.createWithoutData("Team",objectsId.get(pos)).deleteEventually();
                                    objectsId.remove(pos);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        })
                        .setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            //int i = -2 (不論選第幾格)
                            }
                        })
                        .show();
                return true;

            }
        });

    }


}
