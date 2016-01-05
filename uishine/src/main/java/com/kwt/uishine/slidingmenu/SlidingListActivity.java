package com.kwt.uishine.slidingmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.kwt.uishine.R;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class SlidingListActivity extends AppCompatActivity {
    ListView mlvSliding;
    List<String> mSlidings;
    ListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_list);
        initData();
        setItemClickListener();
    }

    private void setItemClickListener() {
        mlvSliding.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent ;
                switch (position){
                    case 0 :
                        intent = new Intent(SlidingListActivity.this,WxSlidingActivity.class);
                        startActivity(intent);
                        break;
                    case 1 :
                       // Toast.makeText(SlidingListActivity.this,"亲，此功能尚未开发",Toast.LENGTH_SHORT).show();
                        intent = new Intent(SlidingListActivity.this,QQSlidingActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initData() {
        mSlidings = Arrays.asList("微信demo","QQdemo");
        mlvSliding = (ListView) findViewById(R.id.lvSliding);
        mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,mSlidings);
        mlvSliding.setAdapter(mAdapter);
    }

}
