package com.volleygamerecord.app;

import android.app.Activity;
import android.os.Bundle;
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

    String wayA ="失分";
    String wayB ="";
    String wayC ="";
    String playerName = "未記錄球員";
    String losePointWay = null;
    RadioButton rdButtonA1 = null;
    RadioButton rdButtonA2 = null;
    RadioButton rdButtonA3 = null;
    RadioButton rdButtonA4 = null;
    RadioButton rdButtonA5 = null;
    RadioButton rdButtonA6 = null;
    RadioButton rdButtonB1 = null;
    RadioButton rdButtonB2 = null;
    RadioButton rdButtonB3 = null;
    RadioButton rdButtonB4 = null;

    ArrayList<RadioButton> radioGroup3List =null;
    RadioButton rdButtonC1 = null;
    RadioButton rdButtonC2 = null;
    RadioButton rdButtonC3 = null;
    RadioButton rdButtonC4 = null;
    RadioButton rdButtonC5 = null;
    RadioButton rdButtonC6 = null;
    RadioButton rdButtonC7 = null;
    RadioButton rdButtonC8 = null;

    RadioGroup radioGroup1 = null;
    RadioGroup radioGroup2 = null;

    ArrayList<RadioButton> rdBtnPlayerList = null;

    RadioButton rdBtnPlayer1 = null;
    RadioButton rdBtnPlayer2 = null;
    RadioButton rdBtnPlayer3 = null;
    RadioButton rdBtnPlayer4 = null;
    RadioButton rdBtnPlayer5 = null;
    RadioButton rdBtnPlayer6 = null;
    RadioButton rdBtnPlayerL1 = null;
    RadioButton rdBtnPlayerL2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_record3);

        LoadRadioItems();
        LoadPlayerPosition();
        RadioGroupPlayers();
        Radiogroup3();


        Button btn_record3Confirm= (Button)findViewById(R.id.btn_record3Confirm);
        btn_record3Confirm.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick (View v){
                if(wayC.equals("")){
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
        rdButtonA5 =(RadioButton)findViewById(R.id.radioButton_record3a5);
        rdButtonA6 =(RadioButton)findViewById(R.id.radioButton_record3a6);
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
        rdButtonC8 =(RadioButton)findViewById(R.id.radioButton_record3c8);

        radioGroup3List = new ArrayList<RadioButton>();
        radioGroup3List.add(rdButtonC1);
        radioGroup3List.add(rdButtonC2);
        radioGroup3List.add(rdButtonC3);
        radioGroup3List.add(rdButtonC4);
        radioGroup3List.add(rdButtonC5);
        radioGroup3List.add(rdButtonC6);
        radioGroup3List.add(rdButtonC7);
        radioGroup3List.add(rdButtonC8);
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
        rdBtnPlayerL1 = (RadioButton)findViewById(R.id.radioButton_record3liberal1);
        rdBtnPlayerL2 = (RadioButton)findViewById(R.id.radioButton_record3liberal2);

        rdBtnPlayerList = new ArrayList<RadioButton>();
        rdBtnPlayerList.add(rdBtnPlayer1);
        rdBtnPlayerList.add(rdBtnPlayer2);
        rdBtnPlayerList.add(rdBtnPlayer3);
        rdBtnPlayerList.add(rdBtnPlayer4);
        rdBtnPlayerList.add(rdBtnPlayer5);
        rdBtnPlayerList.add(rdBtnPlayer6);
        rdBtnPlayerList.add(rdBtnPlayerL1);
        rdBtnPlayerList.add(rdBtnPlayerL2);

    }

    private void LoadPlayerPosition(){
        ArrayList playerList = DataCenter.getInstance().getPlayerArray();
        rdBtnPlayer1.setText(playerList.get(0).toString());
        rdBtnPlayer2.setText(playerList.get(1).toString());
        rdBtnPlayer3.setText(playerList.get(2).toString());
        rdBtnPlayer4.setText(playerList.get(3).toString());
        rdBtnPlayer5.setText(playerList.get(4).toString());
        rdBtnPlayer6.setText(playerList.get(5).toString());

        if (playerList.size() == 6){
            rdBtnPlayerL1.setVisibility(View.INVISIBLE);
            rdBtnPlayerL2.setVisibility(View.INVISIBLE);
        }else if(playerList.size() == 7){
            String a = playerList.get(6).toString();
            rdBtnPlayerL1.setText("自由("+a+")");
            rdBtnPlayerL2.setVisibility(View.INVISIBLE);
        }else{
            String a = playerList.get(6).toString();
            rdBtnPlayerL1.setText("自由("+a+")");
            a = playerList.get(7).toString();
            rdBtnPlayerL2.setText("自由("+a+")");
        }
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
                for (int i = 0; i < rdBtnPlayerList.size(); i++) {
                    RadioButton btn = rdBtnPlayerList.get(i);
                    if (btn != buttonView) {
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
                    wayC = "";
                    closeRadioGroupC();
                    break;
                case R.id.radioButton_record3a2:
                    wayA = rdButtonA2.getText().toString();
                    wayC = "";
                    closeRadioGroupC();
                    break;
                case R.id.radioButton_record3a3:
                    wayA = rdButtonA3.getText().toString();
                    wayC = "";
                    closeRadioGroupC();
                    break;
                case R.id.radioButton_record3a4:
                    wayA = rdButtonA4.getText().toString();
                    wayC = "";
                    closeRadioGroupC();
                    break;
                case R.id.radioButton_record3a5:
                    wayA = rdButtonA5.getText().toString();
                    wayC = "";
                    closeRadioGroupC();
                    break;
                case R.id.radioButton_record3a6:
                    wayA = rdButtonA6.getText().toString();
                    wayC = "";
                    closeRadioGroupC();
                    break;
                default:
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
                    closeRadioGroupC();
                    wayC = "";
                    break;
                case R.id.radioButton_record3b2:
                    wayB = rdButtonB2.getText().toString();
                    closeRadioGroupC();
                    wayC = "";
                    break;
                case R.id.radioButton_record3b3:
                    wayB = rdButtonB3.getText().toString();
                    closeRadioGroupC();
                    wayC = "";
                    break;
                case R.id.radioButton_record3b4:
                    wayB = rdButtonB4.getText().toString();
                    closeRadioGroupC();
                    wayC = "";
                    break;
                default:
                    break;
            }
        }
    };

    //radioGroup C區
    private void Radiogroup3(){
        for(RadioButton btn : radioGroup3List)
        {
            btn.setOnCheckedChangeListener(radioGroup3Listener);
        }
    }
    CompoundButton.OnCheckedChangeListener radioGroup3Listener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView.isChecked()) {
                for (int i = 0; i < radioGroup3List.size(); i++) {
                    RadioButton btn = radioGroup3List.get(i);
                    if (btn != buttonView) {
                        btn.setChecked(false);
                    }
                }
                //關閉GROUP12
                radioGroup1.check(-1);
                radioGroup2.check(-1);
                wayA = "";
                wayB = "";
                wayC = buttonView.getText().toString();
            }
        }
    };

    //關閉GROUPC
    public void closeRadioGroupC() {
        for (int i = 0; i < radioGroup3List.size(); i++) {
            RadioButton btn = radioGroup3List.get(i);
            btn.setChecked(false);
        }
        wayC = "";
    }

}

