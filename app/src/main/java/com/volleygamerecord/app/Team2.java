package com.volleygamerecord.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.inflate;

/**
 * Created by Jay on 2014/3/18.
 */
public class Team2 extends Activity {
    TextView teamName = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team2);

        teamName = (TextView) findViewById(R.id.teamName);
        teamName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(Team2.this);
                final View changeTeamName = inflater.inflate(R.layout.listcontent_team2,null);

                new AlertDialog.Builder(Team2.this)
                        .setTitle("請輸入隊名")
                        .setView(changeTeamName)
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText editText = (EditText)  changeTeamName.findViewById(R.id.teamNameText);
                                if(editText.getText().toString() != null && !editText.getText().toString().equals(""))
                                    teamName.setText(editText.getText().toString());
                            }
                        })
                        .show();
                return false;
            }
        });
    }
}
