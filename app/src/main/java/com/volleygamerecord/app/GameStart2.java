package com.volleygamerecord.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by A on 2014/3/18.
 */
public class GameStart2  extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamestart2);

        //button_gamestart2Sure
        Button btn_gamestart2Sure = (Button)findViewById(R.id.button_gamestart2Sure);
        btn_gamestart2Sure.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

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

}
