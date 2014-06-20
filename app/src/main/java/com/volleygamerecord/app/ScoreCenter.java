package com.volleygamerecord.app;

/**
 * Created by Jay on 2014/4/22.
 */

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Jay on 2014/3/25.
 */
public class ScoreCenter {

    private static final ScoreCenter sharedInstance = new ScoreCenter();
    protected String LOG_TAG = "ScoreCenter";
    private Context context = null;

    private ArrayList ScoreArray = new ArrayList();
    private ArrayList WayArray = new ArrayList();
    private ArrayList PlayerArray = new ArrayList();


    public static ScoreCenter getInstance() {
        return sharedInstance;
    }

    //////////////////////////////////////////////////
    public void setScoreArray(Boolean point, String way, String player){
        ScoreArray.add(point);
        WayArray.add(way);
        PlayerArray.add(player);
    }

    public void backSpaceScoreArray(){
        ScoreArray.remove(ScoreArray.size()-1);
        WayArray.remove(WayArray.size()-1);
        PlayerArray.remove(PlayerArray.size()-1);

    }

    public ArrayList getScoreArray(){
        ArrayList ret = ScoreArray;
        ArrayList aaa = new ArrayList();
        return ret != null ? ret : aaa;
    }

    public ArrayList getWayArray(){
        ArrayList ret = WayArray;
        ArrayList aaa = new ArrayList();
        return ret != null ? ret : aaa;
    }

    public ArrayList getPlayerArray(){
        ArrayList ret = PlayerArray;
        ArrayList aaa = new ArrayList();
        return ret != null ? ret : aaa;
    }


    public void cleanArrays(){
        Log.d("ScoreCenter","清理完成！");
        ScoreArray = null;
        WayArray = null;
        PlayerArray = null;
    }
}

// FILE OPERATION //////////////////////////////////////////////////////////


