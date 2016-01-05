package com.kwt.uishine;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

public class menuDemo extends AppCompatActivity {
    PopupMenu popup = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_demo);
        setPopClickListener();
    }

    private void setPopClickListener() {
        findViewById(R.id.btnPopMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup = new PopupMenu(menuDemo.this,v);
                // 将R.menu.popup_menu菜单资源加载到popup菜单中
                getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());	// 为popup菜单的菜单项单击事件绑定事件监听器
                popup.setOnMenuItemClickListener(
                        new PopupMenu.OnMenuItemClickListener()
                        {
                            @Override
                            public boolean onMenuItemClick(MenuItem item)
                            {
                                switch (item.getItemId())
                                {
                                    case R.id.exit:
                                        // 隐藏该对话框
                                        popup.dismiss();
                                        break;
                                    default:
                                        // 使用Toast显示用户单击的菜单项
                                        Toast.makeText(menuDemo.this,
                                                "您单击了【" + item.getTitle() + "】菜单项"
                                                , Toast.LENGTH_SHORT).show();
                                }
                                return true;
                            }
                        });
                popup.show();

            }
        });
    }

}
