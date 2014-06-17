package com.volleygamerecord.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
    ArrayList score = DataCenter.getInstance().getDataArray();

    Button btn_next_game = null;
    Button btn_end_game = null;

    TableLayout scoreTable;

    TextView WeAttack;
    TextView RivAttack;
    TextView WeServe;
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
    String a5 = "0";
    int b1 = 0;
    int b2 = 0;
    int b3 = 0;
    int b4 = 0;
    String b5 = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count1);
        FindEachViewById(); //------
        CalculatePoint();   //------算分數
        ShowDataInTable();  //------秀分數

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
        WeBlock = (TextView)findViewById(R.id.txV_count1_WeBlock);
        RivBlock = (TextView)findViewById(R.id.txV_count1_RivBlock);
        WeErr = (TextView)findViewById(R.id.txV_count1_WeErr);
        RivErr = (TextView)findViewById(R.id.txV_count1_RivErr);
        WeFoul = (TextView)findViewById(R.id.txV_count1_WeFoul);
        GetTotal = (TextView)findViewById(R.id.txV_count1_GetTotal);
        LoseTotal = (TextView)findViewById(R.id.txV_count1_LoseTotal);
    }

    private void CalculatePoint(){
        for (int i = 0; i < ways.size(); i ++) {
            String tmp = ways.get(i).toString();
            //------得分計算
            if(tmp.contains("我方") && tmp.contains("號位")){
                a1 = a1 + 1;
            }else if (tmp.contains("我方快攻") || tmp.contains("我方後排")){
                a1 = a1 + 1;
            }else if (tmp.contains("發球得分")){
                a2 = a2 + 1;
            }else if (tmp.contains("攔網得分")){
                a3 = a3 + 1;
            }else if (tmp.contains("對方失誤")){
                a4 = a4 + 1;
            }
            //------失分計算
            if(tmp.contains("對方") && tmp.contains("號位")){
                b1 = b1 + 1;
            }else if(tmp.contains("對方快攻") || tmp.contains("對方後排")){
                b1 = b1 + 1;
            }else if(tmp.contains("我方犯規")){
                b2 = b2 + 1;
            }else if(tmp.contains("對方攔網")){
                b3 = b3 + 1;
            }else if (tmp.contains("失誤") && !tmp.contains("對方")){
                b4 = b4 + 1;
            }
        }
        a5 = score.get(0).toString();
        b5 = score.get(1).toString();
    }

    private void ShowDataInTable(){
        WeAttack.setText(String.valueOf(a1));
        WeServe.setText(String.valueOf(a2));
        WeBlock.setText(String.valueOf(a3));
        RivErr.setText(String.valueOf(a4));
        GetTotal.setText(a5);
        RivAttack.setText(String.valueOf(b1));
        WeFoul.setText(String.valueOf(b2));
        RivBlock.setText(String.valueOf(b3));
        WeErr.setText(String.valueOf(b4));
        LoseTotal.setText(b5);
    }
}