package com.volleygamerecord.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    Integer ourScore = 0 ;
    Integer rivalScore = 0;


    //從datacenter拿資料
    String place = DataCenter.getInstance().getStringValue("place");
    String rival = DataCenter.getInstance().getStringValue("rival");
    String cup = DataCenter.getInstance().getStringValue("cup");
    String ourteam = DataCenter.getInstance().getStringValue("team");
    String dts = DataCenter.getInstance().getStringValue("date");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record1);

        String aaa = "a";
        String bbb = "b";

        gamePointList.add(0);
        gamePointList.add(0);

        //結束比賽的按鈕
        Button btn_startManageTeam = (Button)findViewById(R.id.button_record1UploadGame);
        btn_startManageTeam.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.e("UPPPPP", gamePointList.toString());
                ParseObject gameScore = new ParseObject("GameScore");
                gameScore.put("score", gamePointList);
                ParseUser user = ParseUser.getCurrentUser();
                //把比賽訊息放入parseObject
                gameScore.put("user", user);
                gameScore.put("userName", user.getUsername());
                gameScore.put("gamePlace", place);
                gameScore.put("rivalTeam", rival);
                gameScore.put("cup", cup);
                gameScore.put("ourTeam",ourteam);
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
                startActivity(得分切換);
                Record1.this.finish();
            }
        });



    }
}
