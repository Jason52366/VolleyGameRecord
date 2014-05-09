package com.volleygamerecord.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;

/**
 * Created by A on 2014/3/18.
 */
public class Record1 extends Activity {

    ArrayList gamePointList = new ArrayList();
    ArrayList scoreList = new ArrayList();
    ArrayList playerList = new ArrayList();


    Integer ourScoreInt = 0 ;
    Integer rivalScoreInt = 0;


    Button btnPlayer1;
    Button btnPlayer2;
    Button btnPlayer3;
    Button btnPlayer4;
    Button btnPlayer5;
    Button btnPlayer6;

    Boolean switchPosition = false;

    //從datacenter拿資料
    String place = DataCenter.getInstance().getStringValue("place");
    String rival = DataCenter.getInstance().getStringValue("rival");
    String cup = DataCenter.getInstance().getStringValue("cup");
    String our = DataCenter.getInstance().getStringValue("team");
    String dts = DataCenter.getInstance().getStringValue("date");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record1);

        LoadPlayerButton();
        LoadPlayerPosition();
        //結束比賽的按鈕
        Button btn_startManageTeam = (Button)findViewById(R.id.button_record1UploadGame);
        btn_startManageTeam.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.e("UPPPPP", gamePointList.toString());

                gamePointList.add(ourScoreInt);
                gamePointList.add(rivalScoreInt);

                PutItemOnParse();

                ScoreCenter.getInstance().cleanArrays();
                Intent 結束比賽 = new Intent();
                結束比賽.setClass(Record1.this, Count1.class);
                startActivity(結束比賽);
                Record1.this.finish();
            }
        });

        //得分了
        Button btn_getPoint = (Button)findViewById(R.id.button_record1GetPoint);
        btn_getPoint.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent 得分切換 = new Intent();
                得分切換.setClass(Record1.this, Record2.class);
                //有這行回來才會跑calculateScore
                startActivityForResult(得分切換, 0);
            }
        });

        //失分了
        Button btn_losePoint = (Button)findViewById(R.id.button_record1LosePoint);
        btn_losePoint.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent 失分切換 = new Intent();
                失分切換.setClass(Record1.this, Record3.class);
                //有這行回來才會跑calculateScore
                startActivityForResult(失分切換, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("requestcode",""+requestCode);
        Log.d("resultcode",""+resultCode);
        calculateScore();
        LoadPlayerPosition();

    }

    private void PutItemOnParse(){

        ParseObject gameScore = new ParseObject("GameScore");
        ParseUser user = ParseUser.getCurrentUser();
        //把比賽訊息放入parseObject
        gameScore.put("score", gamePointList);
        gameScore.put("user", user);
        gameScore.put("userName", user.getUsername());
        gameScore.put("gamePlace", place);
        gameScore.put("rivalTeam", rival);
        gameScore.put("cup", cup);
        gameScore.put("ourTeam",our);
        gameScore.put("date", dts);
        gameScore.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Log.e("parseReturn","成功！");
                }else {
                    Log.e("parseReturn",e.toString());
                }
            }
        });
    }

    public void LoadPlayerButton(){
        btnPlayer1 = (Button)findViewById(R.id.button_record1player1);
        btnPlayer2 = (Button)findViewById(R.id.button_record1player2);
        btnPlayer3 = (Button)findViewById(R.id.button_record1player3);
        btnPlayer4 = (Button)findViewById(R.id.button_record1player4);
        btnPlayer5 = (Button)findViewById(R.id.button_record1player5);
        btnPlayer6 = (Button)findViewById(R.id.button_record1player6);
    }

    public void LoadPlayerPosition(){
        playerList = DataCenter.getInstance().getPlayerArray();
        if(switchPosition){
            Log.d("5566啦","準備修改PLAYERLIST");
            String tmp = playerList.get(5).toString();
            playerList.set(5,playerList.get(0).toString());
            playerList.set(0,playerList.get(1).toString());
            playerList.set(1,playerList.get(2).toString());
            playerList.set(2,playerList.get(3).toString());
            playerList.set(3,playerList.get(4).toString());
            playerList.set(4,tmp);
            DataCenter.getInstance().setPlayerArray(playerList);
            switchPosition = false;
        }
        btnPlayer1.setText(playerList.get(0).toString());
        btnPlayer2.setText(playerList.get(1).toString());
        btnPlayer3.setText(playerList.get(2).toString());
        btnPlayer4.setText(playerList.get(3).toString());
        btnPlayer5.setText(playerList.get(4).toString());
        btnPlayer6.setText(playerList.get(5).toString());
    }

    private void calculateScore(){
        scoreList = ScoreCenter.getInstance().getScoreArray();
        //避免在Record2,Record3按返回時重複加分
        if (ourScoreInt+rivalScoreInt != scoreList.size()) {
            //看最近的那筆資料是得分還失分
            int i = (scoreList.size());
            if ((Boolean) scoreList.get((i - 1))) {
                ourScoreInt = ourScoreInt + 1;

                //判斷是否該移位, i>=2是避免分數不到兩筆時，跑後面的（i-2）為負GET不到值
                if (i >= 2 && !(Boolean)scoreList.get((i - 2))) {
                    switchPosition = true;
                    DataCenter.getInstance().setDoSwitchPosition(true);
                }

            } else {
                rivalScoreInt = rivalScoreInt + 1;
            }
        }
        TextView ourScore = (TextView)findViewById(R.id.textView_record1OurScore);
        TextView rivalSocre = (TextView)findViewById(R.id.textView_record1RivalScore);
        rivalSocre.setText(String.valueOf(rivalScoreInt));
        ourScore.setText(String.valueOf(ourScoreInt));
    }


}
