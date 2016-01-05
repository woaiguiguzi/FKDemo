package com.kwt.uishine.actionbar;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.kwt.uishine.R;

public class ActionHome extends Activity {
    ActionBar mActionBar;
    TextView mtvAction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_home);
       mtvAction = (TextView) findViewById(R.id.tvAction);
       mActionBar = getActionBar();
//        mActionBar.setDisplayShowHomeEnabled(true);// 设置是否显示应用程序图标
//        // 将应用程序图标设置为可点击的按钮，并在图标上添加向左箭头
//        mActionBar.setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflator = new MenuInflater(this);
        // 状态R.menu.menu_main对应的菜单，并添加到menu中
        inflator.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    // 选项菜单的菜单项被单击后的回调方法
    public boolean onOptionsItemSelected(MenuItem mi)
    {
        if(mi.isCheckable())
        {
            mi.setChecked(true);
        }
        // 判断单击的是哪个菜单项，并有针对性地作出响应
        switch (mi.getItemId())
        {
            case R.id.font_10:
                mtvAction.setTextSize(10 * 2);
                break;
            case R.id.font_12:
                mtvAction.setTextSize(12 * 2);
                break;
            case R.id.font_14:
                mtvAction.setTextSize(14 * 2);
                break;
            case R.id.font_16:
                mtvAction.setTextSize(16 * 2);
                break;
            case R.id.font_18:
                mtvAction.setTextSize(18 * 2);
                break;
            case R.id.red_font:
                mtvAction.setTextColor(Color.RED);
                mi.setChecked(true);
                break;
            case R.id.green_font:
                mtvAction.setTextColor(Color.GREEN);
                mi.setChecked(true);
                break;
            case R.id.blue_font:
                mtvAction.setTextColor(Color.BLUE);
                mi.setChecked(true);
                break;
            case R.id.plain_item:
                Toast toast = Toast.makeText(ActionHome.this, "您单击了普通菜单项",
                        Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
        return true;
    }

}
