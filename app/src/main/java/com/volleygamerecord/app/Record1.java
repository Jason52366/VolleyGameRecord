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
    Integer ourScoreInt = 0 ;
    Integer rivalScoreInt = 0;


    //ScoreCenter拿資料
    ArrayList scoreList = new ArrayList();

    //從datacenter拿資料
    String place = DataCenter.getInstance().getStringValue("place");
    String rival = DataCenter.getInstance().getStringValue("rival");
    String cup = DataCenter.getInstance().getStringValue("cup");
    String our = DataCenter.getInstance().getStringValue("team");
    String dts = DataCenter.getInstance().getStringValue("date");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("restart","restart");
        setContentView(R.layout.activity_record1);


        Log.d("Record1","!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        Log.d("Record1",scoreList.toString());
        Log.d("Record1",ScoreCenter.getInstance().getPlayerArray().toString());
        Log.d("Record1",ScoreCenter.getInstance().getWayArray().toString());
        Log.d("Record1","!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        //結束比賽的按鈕
        Button btn_startManageTeam = (Button)findViewById(R.id.button_record1UploadGame);
        btn_startManageTeam.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.e("UPPPPP", gamePointList.toString());

                gamePointList.add(ourScoreInt);
                gamePointList.add(rivalScoreInt);

                ParseObject gameScore = new ParseObject("GameScore");
                gameScore.put("score", gamePointList);
                ParseUser user = ParseUser.getCurrentUser();
                //把比賽訊息放入parseObject
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
                ScoreCenter.getInstance().cleanArrays();
                Intent 企圖 = new Intent();
                企圖.setClass(Record1.this, Count1.class);
                startActivity(企圖);
                Record1.this.finish();
            }
        });


        Button btn_getPoint = (Button)findViewById(R.id.button_record1GetPoint);
        btn_getPoint.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent 得分切換 = new Intent();
                得分切換.setClass(Record1.this, Record2.class);
                startActivityForResult(得分切換, 77);
            }
        });

        Button btn_losePoint = (Button)findViewById(R.id.button_record1LosePoint);
        btn_losePoint.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent 失分切換 = new Intent();
                失分切換.setClass(Record1.this, Record3.class);
                startActivityForResult(失分切換, 76);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("requestcode",""+requestCode);
        Log.d("resultcode",""+resultCode);
        calculateScore();



        }

    private void calculateScore(){
    ourScoreInt = 0;
    rivalScoreInt = 0;
        scoreList = ScoreCenter.getInstance().getScoreArray();
        for(int i = 0; i < scoreList.size(); i++)
        {
            if ((Boolean)scoreList.get(i))
            {
                ourScoreInt = ourScoreInt + 1;
            }else{
                rivalScoreInt = rivalScoreInt + 1;
            }
        }
        TextView ourScore = (TextView)findViewById(R.id.textView_record1OurScore);
        TextView rivalSocre = (TextView)findViewById(R.id.textView_record1RivalScore);
        rivalSocre.setText(String.valueOf(rivalScoreInt));
        ourScore.setText(String.valueOf(ourScoreInt));
    }
}
