package com.volleygamerecord.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by A on 2014/3/18.
 */
public class GameStart1  extends Activity {
    ArrayAdapter<String> cupArrayList;
    ArrayAdapter<String> ourTeamArrayList;
    List<String> teamList = new ArrayList<String>();

    Spinner spinner2 = null;
    Date dt=new Date();
    String dts = null;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamestart1);

        final ProgressDialog dialog = ProgressDialog.show(GameStart1.this,"", "請等待...", true);

        spinner2 = (Spinner)findViewById(R.id.spinner_gamestart1OurTeam);

        /*Date*/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dts=sdf.format(dt);
        TextView date = (TextView)findViewById(R.id.textView_gamestart1Date) ;
        date.setText(dts);
        DataCenter.getInstance().setValue("date",dts);

        Button btn_gamestart1Sure = (Button)findViewById(R.id.button_gamestart1Sure);
        btn_gamestart1Sure.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText placetxt =  (EditText)findViewById(R.id.editText_gamestart1Arena);
                EditText rivaltxt = (EditText)findViewById(R.id.editText_gamestart1RivalTeam);
                EditText cuptxt = (EditText)findViewById(R.id.editText_gamestart1Cup);
                String place = placetxt.getText().toString();
                String rival  = rivaltxt.getText().toString();
                String cup = cuptxt.getText().toString();
                //把比賽訊息傳到DATACENTER
                DataCenter.getInstance().setValue("cup",cup);
                DataCenter.getInstance().setValue("team",spinner2.getSelectedItem().toString());
                DataCenter.getInstance().setValue("place",place);
                DataCenter.getInstance().setValue("rival",rival);
                Intent intent = new Intent();
                intent.setClass(GameStart1.this, GameStart2.class);
                startActivity(intent);
                onPause();

            }

        });
        //spinner_gamestart1OurTeam
        String userName =  DataCenter.getInstance().getStringValue("parseUserName");

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Team");
        query.fromLocalDatastore();
        query.whereEqualTo("userName",userName);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                if (e == null) {
                    for (int i = 0; i < parseObjects.size(); i++) {
                        teamList.add(parseObjects.get(i).getString("teamName"));
                    }
                    ourTeamArrayList = new ArrayAdapter<String>(GameStart1.this,android.R.layout.simple_spinner_item, teamList);
                    spinner2.setAdapter(ourTeamArrayList);
                    dialog.dismiss();
                } else {
                    Log.e("parseReturn_GameStart1", e.toString());
                }
            }
        });

    }
}
