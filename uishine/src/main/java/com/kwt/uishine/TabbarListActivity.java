package com.kwt.uishine;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TabbarListActivity extends AppCompatActivity {
    ListView mlvTabBar;
    ArrayAdapter<CharSequence> mAdapter;
    String[] names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbar_list);
        initView();
        setItemClick();
    }

    private void setItemClick() {
        mlvTabBar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position){
                    case 0 :
                        intent = new Intent(TabbarListActivity.this,SinaTabbarActivity.class);
                        startActivity(intent);
                        break;
                    case 1 :
                        intent = new Intent(TabbarListActivity.this,SimpleTabhostActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private void initView() {
        mlvTabBar = (ListView) findViewById(R.id.lvTabBar);
        mAdapter = ArrayAdapter.createFromResource(this,R.array.tabbar_name,android.R.layout.simple_expandable_list_item_1);
        mlvTabBar.setAdapter(mAdapter);
    }



}
