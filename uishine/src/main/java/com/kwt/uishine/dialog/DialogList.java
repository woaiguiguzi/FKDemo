package com.kwt.uishine.dialog;

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

public class DialogList extends AppCompatActivity {
    private ListView mlvDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_list);
        initView();
        setItemListener();
    }

    private void setItemListener() {
        mlvDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch(position){
                    case 0 :
                       intent = new Intent(DialogList.this,AlertDialogDemo.class);
                        startActivity(intent);
                        break;
                    case 1 :
                        intent = new Intent(DialogList.this,PopProgressDialog.class);
                        startActivity(intent);
                    default:
                        break;
                }

            }
        });
    }

    private void initView() {
        mlvDialog = (ListView) findViewById(R.id.lvDialog);
        mlvDialog.setAdapter(new ArrayAdapter<String>(DialogList.this,android.R.layout.simple_expandable_list_item_1,new String[]{"AlertDialog","PopWindow ProgressDialog"}));
    }

}
