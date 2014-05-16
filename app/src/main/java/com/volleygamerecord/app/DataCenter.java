package com.volleygamerecord.app;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jay on 2014/3/25.
 */
public class DataCenter {

    private static final DataCenter sharedInstance = new DataCenter();

    protected String LOG_TAG = "DataCenter";
    private Context context = null;
    public static final String RECENT_LOGINS = "recent_logins";

    private String userPathPart = null;

    private Boolean doSwitchPosition = new Boolean(null);
    private Map<String, String> memPool = new HashMap<String, String>();
    private ArrayList dataArray = new ArrayList();
    private ArrayList playerArray = new ArrayList();

    public static DataCenter getInstance() {
        return sharedInstance;
    }

    // MEMORY OPERATION ////////////////////////////////////////////////////////
    public void setDataArray(ArrayList tempArray){
        dataArray = tempArray;
    }

    public void setPlayerArray(ArrayList tempArray){
        playerArray = tempArray;
    }

    public void setDoSwitchPosition(Boolean boolen){
        doSwitchPosition = boolen;
    }

    public void setValue(String key, String value) {
        memPool.remove(key);
        memPool.put(key, value);
    }
    public void setValue(String key, int value) {
        memPool.remove(key);
        memPool.put(key, String.valueOf(value));
    }


    public ArrayList getDataArray(){
        ArrayList ret = dataArray;
        ArrayList aaa = new ArrayList();
        return ret != null ? ret : aaa;
    }
    public ArrayList getPlayerArray(){
        ArrayList ret = playerArray;
        ArrayList aaa = new ArrayList();
        return ret != null ? ret : aaa;
    }

    public String getStringValue(String key) {
        String ret = memPool.get(key);
        return ret != null ? ret : "";
    }
    public int getIntValue(String key) {
        String ret = memPool.get(key);
        return ret != null ? Integer.valueOf(ret) : (int) (0);
    }

    public Boolean getdoSwitchPosition() {
        Boolean ret = doSwitchPosition;
        return ret != null ? ret: Boolean.FALSE;
    }


}

    // FILE OPERATION //////////////////////////////////////////////////////////
