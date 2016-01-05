package com.kwt.uishine.actionbar;

import android.app.Activity;
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

import com.kwt.uishine.R;

public class ActionList extends Activity {
    ListView mlvAction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_list);
        initView();
        setItemListener();
    }

    private void setItemListener() {
        mlvAction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position){
                    case 0 :
                        intent = new Intent(ActionList.this,ActionHome.class);
                        startActivity(intent);
                        break;
                    case 1 :
                        Toast.makeText(ActionList.this,"亲，此功能尚未开发",Toast.LENGTH_SHORT).show();
                        break;
                    case 2 :
                        Toast.makeText(ActionList.this,"亲，此功能尚未开发",Toast.LENGTH_SHORT).show();
                        break;
                    case 3 :
                        Toast.makeText(ActionList.this,"亲，此功能尚未开发",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initView() {
        mlvAction = (ListView) findViewById(R.id.lvAction);
        mlvAction.setAdapter(new ArrayAdapter<String>(ActionList.this,android.R.layout.simple_list_item_1,new String[]{"ActionHome","ActionBar_TabNav","ActionBar_DropDownNav","ActionBar_SwipeNav"}));
    }

}
