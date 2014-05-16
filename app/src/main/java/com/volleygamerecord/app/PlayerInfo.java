package com.volleygamerecord.app;

/**
 * Created by Jay on 2014/5/13.
 */
public class PlayerInfo {

        private String number;
        private String name;
        private String position;
        //確定球員是否在場上
        private Boolean onCourt = false;

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
        public Boolean getOnCourt(){
        return onCourt;
        }
        public void setOnCourt(Boolean on){
        onCourt = on;
        }


}
