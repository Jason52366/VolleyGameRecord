package com.volleygamerecord.app;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by A on 2014/4/1.
 */
public class Easyrecord extends Activity {
    Button btn1 = null;
    Button btn2 = null;
    Button btn3 = null;
    Button btn4 = null;
    Button btn5 = null;
    Button btn6 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easyrecord);


        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        /*Date area*/
        //先行定義時間格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        //取得現在時間
        Date dt=new Date();
        //透過SimpleDateFormat的format方法將Date轉為字串
        String dts=sdf.format(dt);
        TextView date = (TextView)findViewById(R.id.date) ;
        date.setText(dts);


        /*button area*/
        btn1 = (Button) findViewById(R.id.button1) ;//從R.java中得到按鈕
        //button= 代表了按鈕為 (Button) Android layout端的按鈕，findViewById為指定Android端的某名稱按鈕
        View.OnClickListener btn1_listener = new ourscore(); //ourscore為函式
        btn1.setOnClickListener(btn1_listener); //button1按下去以後的觸發事件

        btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(btn1_listener);
        btn3 = (Button) findViewById(R.id.button3);
        btn3.setOnClickListener(btn1_listener);

        btn4 = (Button) findViewById(R.id.button4) ;
        View.OnClickListener btn4_listener = new theirscore();
        btn4.setOnClickListener(btn4_listener);

        btn5 = (Button) findViewById(R.id.button5);
        btn5.setOnClickListener(btn4_listener);

        btn6 = (Button) findViewById(R.id.button6);
        btn6.setOnClickListener(btn4_listener);

    }
    /*button 功能，己方加分*/
    private class ourscore implements View.OnClickListener {
        public void onClick(View v)
        {

            TextView a_score = (TextView)findViewById(R.id.ascore);
            int score = Integer.parseInt(a_score.getText().toString());
            score = score + 1 ;
            //a_score.setText(score);
            Log.e("tag", String.valueOf(score));
            a_score.setText(String.valueOf(score));
        }
    }

    /*button area*/
    /*button 功能，對方加分*/
    private class theirscore implements View.OnClickListener {
        public void onClick(View v)
        {

            TextView b_score = (TextView)findViewById(R.id.bscore);
            int score = Integer.parseInt(b_score.getText().toString());
            score = score + 1 ;
            //a_score.setText(score);
            Log.e("tag", String.valueOf(score));
            b_score.setText(String.valueOf(score));
        }
    }
    /*
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {

            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
*/
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();
            if (id == R.id.action_settings) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

        /**
         * A placeholder fragment containing a simple view.
         */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_easyrecord, container, false);
            return rootView;
        }
    }



}

