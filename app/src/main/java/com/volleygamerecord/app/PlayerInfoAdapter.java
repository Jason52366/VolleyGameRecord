package com.volleygamerecord.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jay on 2014/5/13.
 */

public class PlayerInfoAdapter extends BaseAdapter {
    private LayoutInflater team2Inflater;
    private List<PlayerInfo> info;
    TextView occupy;


    public PlayerInfoAdapter(Context c,List<PlayerInfo> l){
        team2Inflater = LayoutInflater.from(c);
        info = l;
    }
    @Override
    public int getCount() {
        return info.size();
    }
    @Override
    public Object getItem(int pos){
        return info.get(pos);
    }
    @Override
    public long getItemId(int position){
        return info.indexOf(getItem(position));
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        convertView = team2Inflater.inflate(R.layout.listcontent_team2, null);
        TextView name = (TextView) convertView.findViewById(R.id.team2ListName);
        TextView number= (TextView) convertView.findViewById(R.id.team2ListNumber);
        TextView position= (TextView) convertView.findViewById(R.id.team2ListPosition);
        occupy = (TextView) convertView.findViewById(R.id.team2ListPosition);
        PlayerInfo playerinfo = (PlayerInfo) getItem(pos);
        name.setText(playerinfo.getName());
        number.setText(playerinfo.getNumber());
        position.setText(playerinfo.getPosition());

        return convertView;

    }

}
