package com.volleygamerecord.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


/**
 * Created by A on 2014/3/18.
 */
public class Record2 extends Activity {

    String getPointWay = null;
    String wayA = "未記錄";
    String wayB = "未記錄";
    String wayC = "未記錄";
    String wayD = null;
    RadioButton rdButtonA1 = null;
    RadioButton rdButtonA2 = null;
    RadioButton rdButtonA3 = null;
    RadioButton rdButtonA4 = null;
    RadioButton rdButtonB1 = null;
    RadioButton rdButtonB2 = null;
    RadioButton rdButtonC1 = null;
    RadioButton rdButtonC2 = null;
    RadioButton rdButtonC3 = null;
    RadioButton rdButtonC4 = null;
    RadioButton rdButtonOther1 = null;
    RadioButton rdButtonOther2 = null;
    RadioButton rdButtonOther3 = null;
    RadioGroup radioGroup1 = null;
    RadioGroup radioGroup2 = null;
    RadioGroup radioGroup3 = null;
    RadioGroup radioGroup4 = null;
    int checkclose ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_record2);
        Button btn_record2Confirm= (Button)findViewById(R.id.button_record2confirm);
        btn_record2Confirm.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick (View v){
                GetPointWayCombiner();
                ScoreCenter.getInstance().setScoreArray(true,getPointWay,"人");
                setResult(2);
                Record2.this.finish();
                Log.d("point","得分方法："+ getPointWay);
            }
        });

        //radioGroup
       radioGroup1=(RadioGroup)findViewById(R.id.record2rdg1);
          rdButtonA1 =(RadioButton)findViewById(R.id.radioButton_record2a1);
          rdButtonA2 =(RadioButton)findViewById(R.id.radioButton_record2a2);
          rdButtonA3 =(RadioButton)findViewById(R.id.radioButton_record2a3);
          rdButtonA4 =(RadioButton)findViewById(R.id.radioButton_record2a4);
       radioGroup2=(RadioGroup)findViewById(R.id.record2rdg2);
          rdButtonB1 =(RadioButton)findViewById(R.id.radioButton_record2b1);
          rdButtonB2 =(RadioButton)findViewById(R.id.radioButton_record2b2);
       radioGroup3=(RadioGroup)findViewById(R.id.record2rdg3);
          rdButtonC1 =(RadioButton)findViewById(R.id.radioButton_record2c1);
          rdButtonC2 =(RadioButton)findViewById(R.id.radioButton_record2c2);
          rdButtonC3 =(RadioButton)findViewById(R.id.radioButton_record2c3);
          rdButtonC4 =(RadioButton)findViewById(R.id.radioButton_record2c4);
       radioGroup4=(RadioGroup)findViewById(R.id.record2rdg4);
          rdButtonOther1 =(RadioButton)findViewById(R.id.radioButton_record2other1);
          rdButtonOther2 =(RadioButton)findViewById(R.id.radioButton_record2other2);
          rdButtonOther3 =(RadioButton)findViewById(R.id.radioButton_record2other3);
        //---
        radioGroup1.setOnCheckedChangeListener(listenA);
        radioGroup2.setOnCheckedChangeListener(listenB);
        radioGroup3.setOnCheckedChangeListener(listenC);
        radioGroup4.setOnCheckedChangeListener(listenOther);


        //radio_clear=(Button)findViewById(R.id.radio_clear);
        //radio_clear.setOnClickListener(onClick);

    }

    //radioGroup A區
    private RadioGroup.OnCheckedChangeListener listenA=new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int id= group.getCheckedRadioButtonId();
            switch (id) {
                case R.id.radioButton_record2a1:
                    wayA = rdButtonA1.getText().toString();
                    CloseRadioGroup(1);
                break;
                case R.id.radioButton_record2a2:
                    wayA = rdButtonA2.getText().toString();
                    CloseRadioGroup(1);
                    break;
                case R.id.radioButton_record2a3:
                    wayA = rdButtonA3.getText().toString();
                    CloseRadioGroup(1);
                    break;
                case R.id.radioButton_record2a4:
                    wayA = rdButtonA4.getText().toString();
                    CloseRadioGroup(1);
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
                case R.id.radioButton_record2b1:
                    wayB = rdButtonB1.getText().toString();
                    CloseRadioGroup(2);
                    break;
                case R.id.radioButton_record2b2:
                    wayB = rdButtonB2.getText().toString();
                    CloseRadioGroup(2);
                    break;
                default:
                    wayB = "未記錄";
                    break;
            }
        }
    };


    //radioGroup C區
    private RadioGroup.OnCheckedChangeListener listenC=new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int id= group.getCheckedRadioButtonId();
            switch (id) {
                case R.id.radioButton_record2c1:
                    wayC = rdButtonC1.getText().toString();
                    CloseRadioGroup(3);
                    break;
                case R.id.radioButton_record2c2:
                    wayC = rdButtonC2.getText().toString();
                    CloseRadioGroup(3);
                    break;
                case R.id.radioButton_record2c3:
                    wayC = rdButtonC3.getText().toString();
                    CloseRadioGroup(3);
                    break;
                case R.id.radioButton_record2c4:
                    wayC = rdButtonC4.getText().toString();
                    CloseRadioGroup(3);
                    break;
                default:
                    wayC = "未記錄";
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
                case R.id.radioButton_record2other1:
                    wayD = rdButtonOther1.getText().toString();
                    CloseRadioGroup(4);
                    break;
                case R.id.radioButton_record2other2:
                    wayD = rdButtonOther2.getText().toString();
                    CloseRadioGroup(4);
                    break;
                case R.id.radioButton_record2other3:
                    wayD = rdButtonOther3.getText().toString();
                    CloseRadioGroup(4);
                    break;
                default:
                    wayD = null;
                    break;
            }
        }
    };

    public void CloseRadioGroup(int checkclose) {
        if (checkclose == 4) {
            radioGroup1.check(-1);
            radioGroup2.check(-1);
            radioGroup3.check(-1);
        }else{
            radioGroup4.check(-1);
        }
    }

    private void GetPointWayCombiner (){
        if (checkclose != 4){
            getPointWay = wayA + wayB + wayC;
        }else
        {
            getPointWay = wayD;
        }
    }
}

