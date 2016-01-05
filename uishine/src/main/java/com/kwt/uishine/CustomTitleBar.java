package com.kwt.uishine;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.view.Window;
import android.widget.Button;

/**
 * Created by Auser on 2015/12/1.
 */
public class CustomTitleBar  {
    private static Activity mActivity;

    /**
     * @see [自定义标题栏]
     * @param activity
     * @param title
     */
    public static void getTitleBar(Activity activity,String title) {
        mActivity = activity;
        activity.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        activity.setContentView(R.layout.titlebar);
        activity.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
                R.layout.titlebar);
        TextView textView = (TextView) activity.findViewById(R.id.head_center_text);
        textView.setText(title);
/*        Button titleBackBtn = (Button) activity.findViewById(R.id.TitleBackBtn);
        titleBackBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                KeyEvent newEvent = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK);
                mActivity.onKeyDown(KeyEvent.KEYCODE_BACK,newEvent);
            }
        });*/

    }

}
