package com.volleygamerecord.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A on 2014/3/18.
 */
public class MyGame extends Activity {

    /*一種parse的LIST物件*/
    ArrayList<ParseObject> data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygame);


        ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> scoreList, ParseException e) {
                /*若沒有錯誤*/
                if (e == null) {
                    /*逐列資料一一掃過*/
                    data = new ArrayList<ParseObject>(scoreList);
                    /*要把gamelist_activity的資料傳給teamlist_page那邊的listView*/
                    TeamArrayAdapter teamAdapter = new TeamArrayAdapter(MyGame.this, R.layout.listcontent_game);
                    ListView teamListView = (ListView) findViewById(R.id.listView_mygameGameList);
                    /*藉由ADAPTER把資料放到listView*/
                    teamListView.setAdapter(teamAdapter);
                    //點到listview的item時Listener會通知
                    teamListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        //arg2是我選擇的那個
                        public void onItemClick(AdapterView<?> arg0, View view, int arg2,long arg3) {
                                Intent i = new Intent(getApplicationContext(), Count1_MyGame.class);
                                DataCenter.getInstance().setTempParseObject(data);
                                DataCenter.getInstance().setValue("itemNumber",arg2);
                                startActivity(i);


                        }
                    });
                    /*把teamadapter內的東西全部放到gamecupList內*/
                    teamAdapter.addAll(data);
                    /*還不知道功能為何*/
                    teamAdapter.notifyDataSetChanged();
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }

    /*這區是LIST要先貼好的部份*/
    public class TeamArrayAdapter extends ArrayAdapter<ParseObject> {
        private final Context context;
        private final int rowViewResourceId;
        LayoutInflater inflater;

        //private CardItemListener cardItemListener = null; // the response of UI action should be decided by activity
        private static final int HEIGHT_TABLE_ROW = 20;

        public TeamArrayAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.context = context;
            this.rowViewResourceId = textViewResourceId;
        }


        public View getView(int position, View convertView, ViewGroup parent) {
            String teamName = this.getItem(position).get("rivalTeam").toString();
            Log.e("YAAAAAAAA", this.getItem(position).getString("rivalTeam")+data.get(position).getString("date"));
            RelativeLayout rowView;
            if (convertView == null) {
                rowView = (RelativeLayout) inflater.inflate(rowViewResourceId, parent, false);
                assert rowView != null;
            } else {
                rowView = (RelativeLayout) convertView;
            }

            TextView teamNameText = (TextView) rowView.findViewById(R.id.row);
            teamNameText.setText(teamName);
            //印上日期
            TextView teamNameText_2 = (TextView) rowView.findViewById(R.id.row_2);
            teamNameText_2.setText(data.get(position).getString("date"));
            //Log.e("呵呵", this.getItem(position).getNumber("GameDate").toString());
            return rowView;


        }
    }
}
