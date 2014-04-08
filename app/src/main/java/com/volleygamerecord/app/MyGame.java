package com.volleygamerecord.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by A on 2014/3/18.
 */
public class MyGame extends Activity {
    ListView listInput;
    ArrayList<String> items;
    ArrayAdapter<String> adapter;

    String place = DataCenter.getInstance().getStringValue("place");
    String rival = DataCenter.getInstance().getStringValue("rival");
    String cup = DataCenter.getInstance().getStringValue("cup");
    String ourteam = DataCenter.getInstance().getStringValue("team");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygame);

        listInput = (ListView) findViewById(R.id.listView);
        items = new ArrayList<String>();

        items.add(place+" " +rival + " " + cup +" " +ourteam);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listInput.setAdapter(adapter);
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
