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
 * Created by user on 2014/3/18.
 */
public class Record3  extends Activity {

    String wayA ="未記錄";
    String wayB ="未記錄";
    String wayC = null;
    String playerName = null;
    String losePointWay = null;
    RadioButton rdButtonA1 = null;
    RadioButton rdButtonA2 = null;
    RadioButton rdButtonA3 = null;
    RadioButton rdButtonA4 = null;
    RadioButton rdButtonB1 = null;
    RadioButton rdButtonB2 = null;
    RadioButton rdButtonB3 = null;
    RadioButton rdButtonB4 = null;

    ArrayList radioBtnGroupC =null;
    RadioButton rdButtonC1 = null;
    RadioButton rdButtonC2 = null;
    RadioButton rdButtonC3 = null;
    RadioButton rdButtonC4 = null;
    RadioButton rdButtonC5 = null;
    RadioButton rdButtonC6 = null;
    RadioButton rdButtonC7 = null;

    RadioGroup radioGroup1 = null;
    RadioGroup radioGroup2 = null;

    ArrayList<RadioButton> rdBtnPlayerList = null;

    RadioButton rdBtnPlayer1 = null;
    RadioButton rdBtnPlayer2 = null;
    RadioButton rdBtnPlayer3 = null;
    RadioButton rdBtnPlayer4 = null;
    RadioButton rdBtnPlayer5 = null;
    RadioButton rdBtnPlayer6 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_record3);

        LoadRadioItems();
        LoadPlayerPosition();
        RadioGroupPlayers();



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


    }

    private void LoadRadioItems (){
        //---
        radioGroup1=(RadioGroup)findViewById(R.id.record3rdg1);
        rdButtonA1 =(RadioButton)findViewById(R.id.radioButton_record3a1);
        rdButtonA2 =(RadioButton)findViewById(R.id.radioButton_record3a2);
        rdButtonA3 =(RadioButton)findViewById(R.id.radioButton_record3a3);
        rdButtonA4 =(RadioButton)findViewById(R.id.radioButton_record3a4);
        //---
        radioGroup2=(RadioGroup)findViewById(R.id.record3rdg2);
        rdButtonB1 =(RadioButton)findViewById(R.id.radioButton_record3b1);
        rdButtonB2 =(RadioButton)findViewById(R.id.radioButton_record3b2);
        rdButtonB3 =(RadioButton)findViewById(R.id.radioButton_record3b3);
        rdButtonB4 =(RadioButton)findViewById(R.id.radioButton_record3b4);
        //---
        rdButtonC1 =(RadioButton)findViewById(R.id.radioButton_record3c1);
        rdButtonC2 =(RadioButton)findViewById(R.id.radioButton_record3c2);
        rdButtonC3 =(RadioButton)findViewById(R.id.radioButton_record3c3);
        rdButtonC4 =(RadioButton)findViewById(R.id.radioButton_record3c4);
        rdButtonC5 =(RadioButton)findViewById(R.id.radioButton_record3c5);
        rdButtonC6 =(RadioButton)findViewById(R.id.radioButton_record3c6);
        rdButtonC7 =(RadioButton)findViewById(R.id.radioButton_record3c7);

        //---
        radioGroup1.setOnCheckedChangeListener(listenA);
        radioGroup2.setOnCheckedChangeListener(listenB);

        //---

        rdBtnPlayer1 = (RadioButton)findViewById(R.id.radioButton_record3player1);
        rdBtnPlayer2 = (RadioButton)findViewById(R.id.radioButton_record3player2);
        rdBtnPlayer3 = (RadioButton)findViewById(R.id.radioButton_record3player3);
        rdBtnPlayer4 = (RadioButton)findViewById(R.id.radioButton_record3player4);
        rdBtnPlayer5 = (RadioButton)findViewById(R.id.radioButton_record3player5);
        rdBtnPlayer6 = (RadioButton)findViewById(R.id.radioButton_record3player6);
        rdBtnPlayerList = new ArrayList<RadioButton>();
        rdBtnPlayerList.add(rdBtnPlayer1);
        rdBtnPlayerList.add(rdBtnPlayer2);
        rdBtnPlayerList.add(rdBtnPlayer3);
        rdBtnPlayerList.add(rdBtnPlayer4);
        rdBtnPlayerList.add(rdBtnPlayer5);
        rdBtnPlayerList.add(rdBtnPlayer6);


    }

    private void LoadPlayerPosition(){
        ArrayList playerList = DataCenter.getInstance().getPlayerArray();
        rdBtnPlayer1.setText(playerList.get(0).toString());
        rdBtnPlayer2.setText(playerList.get(1).toString());
        rdBtnPlayer3.setText(playerList.get(2).toString());
        rdBtnPlayer4.setText(playerList.get(3).toString());
        rdBtnPlayer5.setText(playerList.get(4).toString());
        rdBtnPlayer6.setText(playerList.get(5).toString());

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
                //for(RadioButton btn : rdBtnPlayerList)
                for (int i = 0; i < rdBtnPlayerList.size(); i++) {
                    RadioButton btn = rdBtnPlayerList.get(i);
                    if (btn != buttonView) {
                        Log.d("how many times", btn.getText().toString());
                        btn.setChecked(false);
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
                case R.id.radioButton_record3a1:
                    wayA = rdButtonA1.getText().toString();
                    break;
                case R.id.radioButton_record3a2:
                    wayA = rdButtonA2.getText().toString();
                    break;
                case R.id.radioButton_record3a3:
                    wayA = rdButtonA3.getText().toString();
                    break;
                case R.id.radioButton_record3a4:
                    wayA = rdButtonA4.getText().toString();
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
                    Log.d("wayB",wayB);
                    break;
                case R.id.radioButton_record3b2:
                    wayB = rdButtonB2.getText().toString();
                    break;
                case R.id.radioButton_record3b3:
                    wayB = rdButtonB3.getText().toString();
                    break;
                case R.id.radioButton_record3b4:
                    wayB = rdButtonB4.getText().toString();
                    break;
                default:
                    wayB = "未記錄";
                    break;
            }
        }
    };

    //radioGroup Other區

}

