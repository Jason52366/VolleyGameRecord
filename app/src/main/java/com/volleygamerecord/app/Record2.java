package com.volleygamerecord.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;


/**
 * Created by A on 2014/3/18.
 */
public class Record2 extends Activity {

    ArrayList playerList = new ArrayList();

    String getPointWay = null;
    String playerName = "未記錄球員";
    String wayA = "";
    String wayB = "";
    String wayC = "";
    String wayD = null;



    RadioButton rdButtonA1;
    RadioButton rdButtonA2;
    RadioButton rdButtonA3;
    RadioButton rdButtonA4;
    RadioButton rdButtonB1;
    RadioButton rdButtonB2;
    RadioButton rdButtonC1;
    RadioButton rdButtonC2;
    RadioButton rdButtonC3;
    RadioButton rdButtonC4;
    RadioButton rdButtonOther1;
    RadioButton rdButtonOther2;
    RadioButton rdButtonOther3;
    RadioGroup radioGroup1;
    RadioGroup radioGroup2;
    RadioGroup radioGroup3;
    RadioGroup radioGroup4;

    ArrayList<RadioButton> rdBtnPlayerList = null;
    RadioButton rdBtnPlayer1;
    RadioButton rdBtnPlayer2;
    RadioButton rdBtnPlayer3;
    RadioButton rdBtnPlayer4;
    RadioButton rdBtnPlayer5;
    RadioButton rdBtnPlayer6;


    int checkclose ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record2);

        FindRadioButtonId();
        LoadPlayerPosition();
        RadioGroupPlayers();

        Button btn_record2Confirm= (Button)findViewById(R.id.button_record2confirm);
        btn_record2Confirm.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick (View v){
                GetPointWayCombiner();
                ScoreCenter.getInstance().setScoreArray(true,getPointWay,playerName);
                setResult(2);
                Record2.this.finish();
            }
        });



        //radio_clear=(Button)findViewById(R.id.radio_clear);
        //radio_clear.setOnClickListener(onClick);

    }

    private void FindRadioButtonId (){
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
        //---
        rdBtnPlayer1 =(RadioButton)findViewById(R.id.radiobotton_record2player1);
        rdBtnPlayer2 =(RadioButton)findViewById(R.id.radiobotton_record2player2);
        rdBtnPlayer3 =(RadioButton)findViewById(R.id.radiobotton_record2player3);
        rdBtnPlayer4 =(RadioButton)findViewById(R.id.radiobotton_record2player4);
        rdBtnPlayer5 =(RadioButton)findViewById(R.id.radiobotton_record2player5);
        rdBtnPlayer6 =(RadioButton)findViewById(R.id.radiobotton_record2player6);

        rdBtnPlayerList = new ArrayList<RadioButton>();
        rdBtnPlayerList.add(rdBtnPlayer1);
        rdBtnPlayerList.add(rdBtnPlayer2);
        rdBtnPlayerList.add(rdBtnPlayer3);
        rdBtnPlayerList.add(rdBtnPlayer4);
        rdBtnPlayerList.add(rdBtnPlayer5);
        rdBtnPlayerList.add(rdBtnPlayer6);
    }

    //RadioGroup for Players
    private void RadioGroupPlayers(){

        for(RadioButton btn : rdBtnPlayerList)
        {
            btn.setOnCheckedChangeListener(rdBtnPlayerlistener);
        }

    }
    CompoundButton.OnCheckedChangeListener rdBtnPlayerlistener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView.isChecked()) {
                for (RadioButton btn : rdBtnPlayerList) {
                    if (btn != buttonView) {
                        btn.setChecked(false);
                    } else {
                    }
                }
                playerName = buttonView.getText().toString();
            }
        }
    };
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

    private void CloseRadioGroup(int checkclose) {
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
    //
    private  void LoadPlayerPosition(){
        playerList = DataCenter.getInstance().getPlayerArray();
        Log.d("Record2",""+playerList.toString());
        rdBtnPlayer1.setText(playerList.get(0).toString());
        rdBtnPlayer2.setText(playerList.get(1).toString());
        rdBtnPlayer3.setText(playerList.get(2).toString());
        rdBtnPlayer4.setText(playerList.get(3).toString());
        rdBtnPlayer5.setText(playerList.get(4).toString());
        rdBtnPlayer6.setText(playerList.get(5).toString());

    }

}

