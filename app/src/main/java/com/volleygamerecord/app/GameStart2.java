package com.volleygamerecord.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by A on 2014/3/18.
 */
public class GameStart2  extends Activity {
    ArrayList choosenPlayer = new ArrayList();
    ArrayList<String> items;
    ArrayList<String> objectsId;
    ArrayAdapter<String> adapter;
    ListView listInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamestart2);

        //button_gamestart2Sure
        Button btn_gamestart2Sure = (Button)findViewById(R.id.button_gamestart2Sure);
        btn_gamestart2Sure.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                SendChoosenPlayer();


                Intent intent = new Intent();
                intent.setClass(GameStart2.this, Record1.class);
                startActivity(intent);
                onPause();
            }
        });
        //召喚了datacenter YOOOOOOO
        //ArrayList abc = DataCenter.getInstance().getTeamArray();
        //Log.e("!!!!!", abc.toString() );
    }

    public void SendChoosenPlayer (){
        choosenPlayer.add("32號");
        choosenPlayer.add("03號");
        choosenPlayer.add("08號");
        choosenPlayer.add("18號");
        choosenPlayer.add("10號");
        choosenPlayer.add("22號");
        DataCenter.getInstance().setPlayerArray(choosenPlayer);
        Log.d("GameStart2", "" + choosenPlayer.toString());
    }
}
