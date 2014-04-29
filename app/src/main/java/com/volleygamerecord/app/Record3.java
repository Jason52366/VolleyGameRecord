package com.volleygamerecord.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by user on 2014/3/18.
 */
public class Record3  extends Activity {

    String wayA ="未記錄";
    String wayB ="未記錄";
    String wayC = null;
    String losePointWay = null;
    RadioButton rdButtonA1 = null;
    RadioButton rdButtonA2 = null;
    RadioButton rdButtonA3 = null;
    RadioButton rdButtonA4 = null;
    RadioButton rdButtonB1 = null;
    RadioButton rdButtonB2 = null;
    RadioButton rdButtonB3 = null;
    RadioButton rdButtonB4 = null;
    RadioButton rdButtonOther1 = null;
    RadioButton rdButtonOther2 = null;
    RadioButton rdButtonOther3 = null;
    RadioGroup radioGroup1 = null;
    RadioGroup radioGroup2 = null;
    RadioGroup radioGroup3 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_record3);

        Button btn_record3Confirm= (Button)findViewById(R.id.btn_record3Confirm);
        btn_record3Confirm.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick (View v){

                if(wayC == null){
                    losePointWay = wayA+wayB;
                }else{
                    losePointWay = wayC;
                }
                ScoreCenter.getInstance().setScoreArray(false,losePointWay,"人");
                setResult(3);
                Record3.this.finish();

            }
        });

        //radioGroup FindViewById區
        radioGroup1=(RadioGroup)findViewById(R.id.record3rdg1);
            rdButtonA1 =(RadioButton)findViewById(R.id.radioButton_record3a1);
            rdButtonA2 =(RadioButton)findViewById(R.id.radioButton_record3a2);
            rdButtonA3 =(RadioButton)findViewById(R.id.radioButton_record3a3);
            rdButtonA4 =(RadioButton)findViewById(R.id.radioButton_record3a4);
        radioGroup2=(RadioGroup)findViewById(R.id.record3rdg2);
            rdButtonB1 =(RadioButton)findViewById(R.id.radioButton_record3b1);
            rdButtonB2 =(RadioButton)findViewById(R.id.radioButton_record3b2);
            rdButtonB3 =(RadioButton)findViewById(R.id.radioButton_record3b3);
            rdButtonB4 =(RadioButton)findViewById(R.id.radioButton_record3b4);
        radioGroup3=(RadioGroup)findViewById(R.id.record3rdg3);
            rdButtonOther1 =(RadioButton)findViewById(R.id.radioButton_record3c1);
            rdButtonOther2 =(RadioButton)findViewById(R.id.radioButton_record3c2);
            rdButtonOther3 =(RadioButton)findViewById(R.id.radioButton_record3c3);
        //---
        radioGroup1.setOnCheckedChangeListener(listenA);
        radioGroup2.setOnCheckedChangeListener(listenB);
        radioGroup3.setOnCheckedChangeListener(listenOther);

    }


    //radioGroup A區
    private RadioGroup.OnCheckedChangeListener listenA=new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int id= group.getCheckedRadioButtonId();
            switch (id) {
                case R.id.radioButton_record3a1:
                    wayA = rdButtonA1.getText().toString();
                    radioGroup3.check(-1);
                    break;
                case R.id.radioButton_record3a2:
                    wayA = rdButtonA2.getText().toString();
                    radioGroup3.check(-1);
                    break;
                case R.id.radioButton_record3a3:
                    wayA = rdButtonA3.getText().toString();
                    radioGroup3.check(-1);
                    break;
                case R.id.radioButton_record3a4:
                    wayA = rdButtonA4.getText().toString();
                    radioGroup3.check(-1);
                    break;
                default:
                    wayA = "未記錄";
                    break;
            }
        }
    };
    //radioGroup B區
    private RadioGroup.OnCheckedChangeListener listenB=new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int id= group.getCheckedRadioButtonId();
            switch (id) {
                case R.id.radioButton_record3b1:
                    wayB = rdButtonB1.getText().toString();
                    radioGroup3.check(-1);
                    break;
                case R.id.radioButton_record3b2:
                    wayB = rdButtonB2.getText().toString();
                    radioGroup3.check(-1);
                    break;
                case R.id.radioButton_record3b3:
                    wayB = rdButtonB3.getText().toString();
                    radioGroup3.check(-1);
                    break;
                case R.id.radioButton_record3b4:
                    wayB = rdButtonB4.getText().toString();
                    radioGroup3.check(-1);
                    break;
                default:
                    wayB = "未記錄";
                    break;
            }
        }
    };

    //radioGroup Other區
    private RadioGroup.OnCheckedChangeListener listenOther=new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int id= group.getCheckedRadioButtonId();
            switch (id) {
                case R.id.radioButton_record3c1:
                    wayC = rdButtonOther1.getText().toString();
                    radioGroup1.check(-1);
                    radioGroup2.check(-1);
                    break;
                case R.id.radioButton_record3c2:
                    wayC = rdButtonOther2.getText().toString();
                    radioGroup1.check(-1);
                    radioGroup2.check(-1);
                case R.id.radioButton_record3c3:
                    wayC = rdButtonOther3.getText().toString();
                    radioGroup1.check(-1);
                    radioGroup2.check(-1);
                default:
                    wayC = null;
                    break;
            }
        }
    };
}

