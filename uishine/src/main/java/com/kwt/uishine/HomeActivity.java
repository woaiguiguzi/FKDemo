package com.kwt.uishine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button btn_control,btn_service,btn_demo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        setListener();
    }
    public void initView(){
        btn_control = (Button) findViewById(R.id.btn_control);
        btn_service = (Button) findViewById(R.id.btn_service);
        btn_demo = (Button) findViewById(R.id.btn_demo);
    }
    public void setListener(){
        setControlClickListener();
    }

    private void setControlClickListener() {
        btn_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,ControlhomeActivity.class);
                startActivity(intent);
              //  finish();
            }
        });
    }
}
