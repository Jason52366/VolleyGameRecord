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
public class PlayerInfo {

        private String number;
        private String name;
        private String position;

        public PlayerInfo(String num,String n, String pos){
            number = num;
            name = n;
            position = pos;
        }

        public String getNumber(){
            return number;
        }
        public void setNumber(String num){
            number = num;
        }
        public String getName(){
            return name;
        }
        public void setName(String n){
            name = n;
        }
        public String getPosition(){
            return position;
        }
        public void setPosition(String pos){
            position = pos;
        }



}
