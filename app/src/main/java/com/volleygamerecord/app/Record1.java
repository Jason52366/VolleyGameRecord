package com.volleygamerecord.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import java.util.Arrays;
import java.util.List;

/**
 * Created by A on 2014/3/18.
 */
public class Record1 extends Activity {

    ArrayList gamePointList = new ArrayList();
    ArrayList scoreList = new ArrayList();
    ArrayList offCourtPlayerList = new ArrayList();
    List<Button> playerButtonsList = new ArrayList<Button>();

    AlertDialog levelDialog;

    Integer ourScoreInt = 0 ;
    Integer rivalScoreInt = 0;

    Button btnPlayer1;
    Button btnPlayer2;
    Button btnPlayer3;
    Button btnPlayer4;
    Button btnPlayer5;
    Button btnPlayer6;
    Button btnPlayerL1;
    Button btnPlayerL2;

    Button btn_upLoadGame;
    Button btn_getPoint;
    Button btn_losePoint;
    Boolean switchPosition = false;

    //從datacenter拿資料
    String place = DataCenter.getInstance().getStringValue("place");
    String rival = DataCenter.getInstance().getStringValue("rival");
    String cup = DataCenter.getInstance().getStringValue("cup");
    String our = DataCenter.getInstance().getStringValue("team");
    String dts = DataCenter.getInstance().getStringValue("date");
    ArrayList ways = ScoreCenter.getInstance().getWayArray();
    ArrayList<PlayerInfo> infoItem = DataCenter.getInstance().getPlayerInfo();
    ArrayList playerList = DataCenter.getInstance().getPlayerArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record1);
        LoadButton();
        LoadPlayerPosition();
        LoadOffCourtPlayer();
        ClickChangePlayer();
        UploadGame_Btn();        //結束比賽
        GetPoint_Btn();          //得分了
        LosePoint_Btn();         //失分了

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
        gameScore.put("scoreDetail",ways);
        gameScore.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Log.e("parseReturn","Upload成功！");
                }else {
                    Log.e("parseReturn",e.toString());
                }
            }
        });
    }

    public void LoadButton(){
        btnPlayer1 = (Button)findViewById(R.id.button_record1player1);
        btnPlayer2 = (Button)findViewById(R.id.button_record1player2);
        btnPlayer3 = (Button)findViewById(R.id.button_record1player3);
        btnPlayer4 = (Button)findViewById(R.id.button_record1player4);
        btnPlayer5 = (Button)findViewById(R.id.button_record1player5);
        btnPlayer6 = (Button)findViewById(R.id.button_record1player6);
        btnPlayerL1 = (Button)findViewById(R.id.button_record1liberal1);
        btnPlayerL2 = (Button)findViewById(R.id.button_record1liberal2);

        playerButtonsList = Arrays.asList(btnPlayer1, btnPlayer2, btnPlayer3, btnPlayer4, btnPlayer5, btnPlayer6);

        btn_upLoadGame = (Button)findViewById(R.id.button_record1UploadGame);
        btn_getPoint = (Button)findViewById(R.id.button_record1GetPoint);
        btn_losePoint = (Button)findViewById(R.id.button_record1LosePoint);

        if (playerList.size() == 6){
            btnPlayerL1.setVisibility(View.INVISIBLE);
            btnPlayerL2.setVisibility(View.INVISIBLE);
        }else if(playerList.size() == 7){
            String a = playerList.get(6).toString();
            btnPlayerL1.setText("自由("+a+")");
            btnPlayerL2.setVisibility(View.INVISIBLE);
        }else{
            String a = playerList.get(6).toString();
            btnPlayerL1.setText("自由("+a+")");
            a = playerList.get(7).toString();
            btnPlayerL2.setText("自由("+a+")");
        }


    }

    public void LoadPlayerPosition(){
        if(switchPosition){
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

    private void ClickChangePlayer() {

        for(Button btn : playerButtonsList)
        {
            btn.setOnClickListener(ChangePlayerBtn);
        }

    }

    Button.OnClickListener ChangePlayerBtn = new Button.OnClickListener() {
        @Override
        public void onClick(final View v) {
            final List<String> tmpList = offCourtPlayerList;
            final CharSequence[] abc = tmpList.toArray(new CharSequence[tmpList.size()]);
            // Creating and Building the Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(Record1.this);
            builder.setTitle("選擇替換球員");
            builder.setSingleChoiceItems(abc, -1, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                    Button ClickedPlayerBTN = (Button)v;
                    String offNumber = ClickedPlayerBTN.getText().toString();
                    int a = playerList.indexOf(offNumber);
                    String b = tmpList.get(item);
                    ClickedPlayerBTN.setText(b);
                    offCourtPlayerList.remove(b);
                    offCourtPlayerList.add(offNumber);
                    playerList.set(a,b);
                    levelDialog.dismiss();
                }
            });
            levelDialog = builder.create();
            levelDialog.show();

        }
    };

    private void LoadOffCourtPlayer(){
        for(int x = 0; x < infoItem.size() ; x++ ){
            if(!infoItem.get(x).getOnCourt()){
                offCourtPlayerList.add(infoItem.get(x).getNumber());
            }

        }
    }

    private void UploadGame_Btn(){

        btn_upLoadGame.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

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
    }

    private void GetPoint_Btn(){
        btn_getPoint.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent 得分切換 = new Intent();
                得分切換.setClass(Record1.this, Record2.class);
                //有這行回來才會跑calculateScore
                startActivityForResult(得分切換, 0);
            }
        });

    }

    private void LosePoint_Btn(){
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
                    DataCenter.getInstance().setDoSwitchPosition(switchPosition);
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
