package com.kwt.uishine;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class SinaTabbarActivity extends TabActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup group;
    private TabHost tabHost;
    public static final String TAB_ITEM_1 = "tabItem1";
    public static final String TAB_ITEM_2 = "tabItem2";
    public static final String TAB_ITEM_3 = "tabItem3";
    public static final String TAB_ITEM_4 = "tabItem4";
    public static final String TAB_ITEM_5 = "tabItem5";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sina_tabbar);

        group = (RadioGroup) findViewById(R.id.tabRadio);
        group.setOnCheckedChangeListener(this);

        tabHost = this.getTabHost();

        TabSpec tab1 = tabHost.newTabSpec(TAB_ITEM_1)
                .setIndicator(TAB_ITEM_1)//设置标题
                .setContent(new Intent(this, SinaFirstActivity.class));//设置内容
        TabSpec tab2 = tabHost.newTabSpec(TAB_ITEM_2);
        TabSpec tab3 = tabHost.newTabSpec(TAB_ITEM_3);
        TabSpec tab4 = tabHost.newTabSpec(TAB_ITEM_4);
        TabSpec tab5 = tabHost.newTabSpec(TAB_ITEM_5);

        tab2.setIndicator(TAB_ITEM_2).setContent(new Intent(this, SinaSecondActivity.class));
        tab3.setIndicator(TAB_ITEM_3).setContent(new Intent(this, SinaFirstActivity.class));
        tab4.setIndicator(TAB_ITEM_4).setContent(new Intent(this, SinaSecondActivity.class));
        tab5.setIndicator(TAB_ITEM_5).setContent(new Intent(this, SinaFirstActivity.class));

        tabHost.addTab(tab1);//添加标签
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
        tabHost.addTab(tab4);
        tabHost.addTab(tab5);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId)
        {
            case R.id.radio_button1:
                tabHost.setCurrentTabByTag(TAB_ITEM_1);
                break;
            case R.id.radio_button2:
                tabHost.setCurrentTabByTag(TAB_ITEM_2);
                break;
            case R.id.radio_button3:
                tabHost.setCurrentTabByTag(TAB_ITEM_3);
                break;
            case R.id.radio_button4:
                tabHost.setCurrentTabByTag(TAB_ITEM_4);
                break;
            case R.id.radio_button5:
                tabHost.setCurrentTabByTag(TAB_ITEM_5);
                break;
            default:
                break;
        }
    }
}
