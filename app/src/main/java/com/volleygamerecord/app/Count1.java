package com.volleygamerecord.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by A on 2014/4/1.
 */



public class Count1 extends Activity {

    ArrayList ways = ScoreCenter.getInstance().getWayArray();

    Button btn_next_game = null;
    Button btn_end_game = null;

    TableLayout scoreTable;

    TextView WeAttack;
    TextView RivAttack;
    TextView WeServe;
    TextView RivServe;
    TextView WeBlock;
    TextView RivBlock;
    TextView RivErr;
    TextView WeErr;
    TextView WeFoul;
    TextView GetTotal;
    TextView LoseTotal;

    int a1 = 0;
    int a2 = 0;
    int a3 = 0;
    int a4 = 0;
    int a5 = 0;
    int b1 = 0;
    int b2 = 0;
    int b3 = 0;
    int b4 = 0;
    int b5 = 0;
    int b6 = 0;


    private String tableData[][] = new String[][] { { "攻擊", "發球", "得分", "攔網" },
            { "10", "6", "8","0" },
            { "2", "1", "7","2" } };
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count1);
        FindEachViewById();
        for (int i = 0; i < ways.size(); i ++) {
            String tmp = ways.get(i).toString();
            if(tmp.contains("4號位")){

            }else{
                Log.d("No","QQQ");
            }

        }

        btn_next_game.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick (View v){
                ScoreCenter.getInstance().cleanArrays();
                Intent intent = new Intent();
                intent.setClass(Count1.this, Record1.class);
                startActivity(intent);
                Count1.this.finish();
            }

        });

        btn_end_game.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick (View v){
                ScoreCenter.getInstance().cleanArrays();
                Intent intent = new Intent();
                intent.setClass(Count1.this, Count2.class);
               startActivity(intent);
            }

        });
    }


    private void FindEachViewById(){
        btn_end_game = (Button)findViewById(R.id.btn_end_game);
        btn_next_game = (Button)findViewById(R.id.btn_next_game);
        scoreTable = (TableLayout)findViewById(R.id.table_count1playerscore);
        WeAttack = (TextView)findViewById(R.id.txV_count1_WeAtt);
        RivAttack = (TextView)findViewById(R.id.txV_count1_RivAtt);
        WeServe = (TextView)findViewById(R.id.txV_count1_WeSer);
        RivServe = (TextView)findViewById(R.id.txV_count1_RivSer);
        WeBlock = (TextView)findViewById(R.id.txV_count1_WeBlock);
        RivBlock = (TextView)findViewById(R.id.txV_count1_RivBlock);
        WeErr = (TextView)findViewById(R.id.txV_count1_WeErr);
        RivErr = (TextView)findViewById(R.id.txV_count1_RivSer);
        WeFoul = (TextView)findViewById(R.id.txV_count1_WeFoul);
        GetTotal = (TextView)findViewById(R.id.txV_count1_GetTotal);
        LoseTotal = (TextView)findViewById(R.id.txV_count1_LoseTotal);
    }
}