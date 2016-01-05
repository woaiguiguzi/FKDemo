package com.kwt.uishine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FragmentListActivity extends Activity {
    ListView mlvFragment;
    List<Map<String,String>> fragments;
    SimpleAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomTitleBar.getTitleBar(this, "Fragment");
        setContentView(R.layout.activity_fragment_list);
        initData();
        initView();
        setListener();
    }

    private void setListener() {
        mlvFragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (fragments.get(position).get("name")){
                    case "SimpleFragment":
                         intent = new Intent(FragmentListActivity.this,ForgotActivity.class);
                        startActivity(intent);
                        break;
                    case "SeniorFragment":
                         intent = new Intent(FragmentListActivity.this,SeniorFragmnetActivity.class);
                        startActivity(intent);
                        break;
                }

            }
        });
    }

    private void initData() {
        String[] ids ={"1","2"};
        String[] names = new String[]{
                "SimpleFragment","SeniorFragment"
        };
        fragments = new ArrayList<Map<String, String>>();
        for (int i=0;i<names.length;i++){
            Map<String,String> map = new HashMap<String, String>();
            map.put("id",ids[i]);
            map.put("name",names[i]);
            fragments.add(map);
        }
    }

    public void initView(){
        mlvFragment = (ListView) findViewById(R.id.lvFragment);
        mAdapter = new SimpleAdapter(this,fragments,R.layout.item_fragment,new String[]{"id","name"},
                new int[]{R.id.tvFid,R.id.tvName});
        mlvFragment.setAdapter(mAdapter);
    }
}
