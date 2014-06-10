package com.volleygamerecord.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by A on 2014/4/1.
 */



public class Count1 extends Activity {

    private String tableData[][] = new String[][] { { "攻擊", "發球", "得分", "攔網" },
            { "球員1", "10", "6", "8","0" },
            { "球員2", "2", "1", "7","2" } };
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count1);
        Button btn_next_game = (Button)findViewById(R.id.btn_next_game);
        btn_next_game.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent intent = new Intent();
                intent.setClass(Count1.this, Record1.class);
                startActivity(intent);
                Count1.this.finish();
            }

        });

        Button btn_end_game = (Button)findViewById(R.id.btn_end_game);
        btn_end_game.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent intent = new Intent();
                intent.setClass(Count1.this, Count2.class);
               startActivity(intent);
            }

        });



    }

}