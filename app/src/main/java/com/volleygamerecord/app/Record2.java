package com.volleygamerecord.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


/**
 * Created by A on 2014/3/18.
 */
public class Record2 extends Activity {

    ArrayList scoreList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_record2);
        Button btn_record2Confirm= (Button)findViewById(R.id.button_record2confirm);
        btn_record2Confirm.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick (View v){

                ScoreCenter.getInstance().setScoreArray(true,"方法","人");
                setResult(2);
                Record2.this.finish();

            }

        }
        );
    }
/*
 private void CheckScore(){
        //ScoreCenter拿資料
        scoreList = ScoreCenter.getInstance().getScoreArray();
        int i = (scoreList.size()-2);
        if ((Boolean) scoreList.get(i)) {
        }else{
            i = (scoreList.size() - 1);
            if ((Boolean)scoreList.get(i))
            {}
        }

    }
*/

}
