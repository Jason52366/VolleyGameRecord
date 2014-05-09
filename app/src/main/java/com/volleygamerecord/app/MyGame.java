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
import android.widget.ListView;

import com.parse.FindCallback;
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygame);

        listInput = (ListView) findViewById(R.id.listView);
        items = new ArrayList<String>();
        objectsId = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        final ProgressDialog dialog = ProgressDialog.show(MyGame.this,"", "請等待...", true);

        //get all objects from Parse
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("GameScore");
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
                    Log.e("parseReturn", e.toString());
                    dialog.dismiss();
                }
            }
        });
        /*
        listInput.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("数据操作");
                menu.add(0, 0, 0, "增加");
                menu.add(0, 1, 0, "删除");
                menu.add(0, 2, 0, "修改");
            }
        });*/


        //比賽資料
        listInput.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String obID = objectsId.get(position);
                Log.d("MyGame!!!!!!","The objectID is ''"+obID+"''");
                DataCenter.getInstance().setValue("objectsId",obID);

                Intent intent = new Intent();
                intent.setClass(MyGame.this, Count1_MyGame.class);
                startActivity(intent);
                MyGame.this.finish();
            }
        });

        //刪除比賽資料
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
}
