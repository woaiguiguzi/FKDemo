package com.kwt.uishine;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ForgotActivity extends Activity {
    TextView mtvForgotPhone,mtvForgotEmail;
    Button titleBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomTitleBar.getTitleBar(this, "Fragment");
        setContentView(R.layout.activity_forgot);
        initView();
        setListener();
        startFragmentAdd(new ForgotPhoneFragment());

    }
    public void initView(){
        mtvForgotPhone = (TextView) findViewById(R.id.tvForgotPhone);
        mtvForgotEmail = (TextView) findViewById(R.id.tvForgotEmail);
        titleBackBtn = (Button) findViewById(R.id.TitleBackBtn);
    }
    public void setListener(){
        setPhoneClick();
        setEmailClick();
        setBackCliclListener();
    }
    private void setBackCliclListener() {
        titleBackBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                KeyEvent newEvent = new KeyEvent(KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_BACK);
                onKeyDown(KeyEvent.KEYCODE_BACK, newEvent);//必须重写
            }
        });
    }
    private void setEmailClick() {
        mtvForgotEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                referView(v);
                startFragmentAdd(new ForgotEmailFragment());
            }
        });
    }

    private void setPhoneClick() {
        mtvForgotPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                referView(v);
                startFragmentAdd(new ForgotPhoneFragment());
            }
        });
    }

    public void startFragmentAdd(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();  // ①
    }
    private void referView(View v) {
        mtvForgotPhone.setBackgroundResource(R.drawable.normal_arrows);
        mtvForgotEmail.setBackgroundResource(R.drawable.normal_arrows);

        v.setBackgroundResource(R.drawable.select_arrows);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // 按下的如果是BACK，同时没有重复
            finish();
            //  askForOut();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
