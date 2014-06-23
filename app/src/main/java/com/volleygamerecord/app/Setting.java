package com.volleygamerecord.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ViewFlipper;

/**
 * Created by A on 2014/5/13.
 */
/*public class Setting extends Activity {

    private ViewFlipper flipper;//ViewFlipper
    private GestureDetector detector;//觸摸監聽

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        detector = new GestureDetector(Setting.this);//起始化觸摸
        flipper = (ViewFlipper) this.findViewById(R.id.ViewFlipper01);//取得ViewFlipper
        flipper.addView(addTextView("step 1"));//將View增加到flipper陣列中
        flipper.addView(addTextView("step 2"));
        flipper.addView(addTextView("step 3"));
        flipper.addView(addTextView("step 4"));
        flipper.addView(addTextView("step 5"));
        addTextView方法如下：
        private View addTextView(String text) {
            TextView tv = new TextView(this);
            tv.setText(text);
            tv.setGravity(1);
            return tv;
        }
    }



}
*/