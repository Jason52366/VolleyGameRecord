package com.volleygamerecord.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;

/**
 * Created by Jay on 2014/4/11.
 */
public class Count1_MyGame extends Activity {
    String obID;

    ArrayList ways = new ArrayList();
    ArrayList score = new ArrayList();

    Button btn_next_game = null;
    Button btn_end_game = null;
    Button btn_ErrorDetail = null;

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
    int er1 = 0;
    int er2 = 0;
    int er3 = 0;
    int er4 = 0;
    int er5 = 0;
    int er6 = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count1);
        obID = DataCenter.getInstance().getStringValue("objectsId");
        FindEachViewById(); //------
        GetDataFromParse(); //------用objectID拿資料，拿完之後會自動算分跑資料
        ErrorDetail_btn();  //------顯示失誤細節
        EndGame_Btn();      //------返回按鈕
        btn_next_game.setVisibility(View.INVISIBLE);

    }

    private void FindEachViewById() {
        btn_end_game = (Button) findViewById(R.id.btn_end_game);
        btn_next_game = (Button) findViewById(R.id.btn_next_game);
        scoreTable = (TableLayout) findViewById(R.id.table_count1roughrecord);
        WeAttack = (TextView) findViewById(R.id.txV_count1_WeAtt);
        RivAttack = (TextView) findViewById(R.id.txV_count1_RivAtt);
        WeServe = (TextView) findViewById(R.id.txV_count1_WeSer);
        WeBlock = (TextView) findViewById(R.id.txV_count1_WeBlock);
        RivBlock = (TextView) findViewById(R.id.txV_count1_RivBlock);
        WeErr = (TextView) findViewById(R.id.txV_count1_WeErr);
        RivErr = (TextView) findViewById(R.id.txV_count1_RivErr);
        WeFoul = (TextView) findViewById(R.id.txV_count1_WeFoul);
        GetTotal = (TextView) findViewById(R.id.txV_count1_GetTotal);
        LoseTotal = (TextView) findViewById(R.id.txV_count1_LoseTotal);

        btn_end_game = (Button) findViewById(R.id.btn_end_game);
        btn_next_game = (Button) findViewById(R.id.btn_next_game);
        btn_ErrorDetail = (Button) findViewById(R.id.button_count1ErrorDetail);

    }


    private void GetDataFromParse() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");
        query.getInBackground(obID, new GetCallback<ParseObject>() {
            public void done(ParseObject gameData, ParseException e) {
                if (e == null) {
                    score = (ArrayList) gameData.get("score");
                    ways = (ArrayList) gameData.get("scoreDetail");
                    CalculatePoint();   //------算分數
                    ShowDataInTable();  //------秀分數
                } else {
                    Log.d("QQQ", "ERROR:" + e);
                }
            }
        });
    }

    private void CalculatePoint() {
        for (int i = 0; i < ways.size(); i++) {
            String tmp = ways.get(i).toString();
            //------得分計算
            if (tmp.contains("我方") && tmp.contains("號位")) {
                a1 = a1 + 1;
            } else if (tmp.contains("我方快攻") || tmp.contains("我方後排")) {
                a1 = a1 + 1;
            } else if (tmp.contains("發球得分")) {
                a2 = a2 + 1;
            } else if (tmp.contains("攔網得分")) {
                a3 = a3 + 1;
            } else if (tmp.contains("對方失誤")) {
                a4 = a4 + 1;
            }
            //------失分計算
            if (tmp.contains("對方") && tmp.contains("號位")) {
                b1 = b1 + 1;
            } else if (tmp.contains("對方快攻") || tmp.contains("對方後排")) {
                b1 = b1 + 1;
            } else if (tmp.contains("我方犯規")) {
                b2 = b2 + 1;
            } else if (tmp.contains("對方攔網")) {
                b3 = b3 + 1;
            } else if (tmp.contains("失誤") && !tmp.contains("對方")) {
                b4 = b4 + 1;
            }
            //------失誤計算
            if(tmp.contains("失誤")){
                if (tmp.contains("舉球")){
                    er1 = er1 + 1;
                }else if(tmp.contains("攔網")){
                    er2 = er2 + 1;
                }else if(tmp.contains("接發")){
                    er3 = er3 + 1;
                }else if(tmp.contains("攻擊")){
                    er4 = er4 + 1;
                }else if(tmp.contains("防守")){
                    er5 = er5 + 1;
                }else if(tmp.contains("發球")){
                    er6 = er6 + 1;
                }
            }
        }
        a5 = score.get(0).toString();
        b5 = score.get(1).toString();
    }

    private void ShowDataInTable() {
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


    private void ErrorDetail_btn() {
        btn_ErrorDetail.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Count1_MyGame.this)
                        .setTitle("失誤記錄")
                        .setMessage("舉球失誤：" + er1 + "\n攔網失誤：" + er2 + "\n接發失誤：" + er3 +
                                "\n攻擊失誤：" + er4 + "\n防守失誤：" + er5 + "\n發球失誤：" + er6)
                        .setPositiveButton("了解", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create()
                        .show()
                ;
            }
        });

    }


    private void EndGame_Btn() {
        btn_end_game.setText("返回");
        btn_end_game.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Count1_MyGame.this, Count2.class);
                startActivity(intent);
            }
        });
    }
}

