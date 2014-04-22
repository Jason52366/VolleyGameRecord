package com.volleygamerecord.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by user on 2014/3/18.
 */
public class Record3  extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_record3);

        Button btn_record3Confirm= (Button)findViewById(R.id.btn_record3Confirm);
        btn_record3Confirm.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick (View v){

                ScoreCenter.getInstance().setScoreArray(false,"方法","人");
                setResult(56);
                Record3.this.finish();

            }

        });


    }

}
