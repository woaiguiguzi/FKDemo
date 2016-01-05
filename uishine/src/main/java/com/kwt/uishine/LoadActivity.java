package com.kwt.uishine;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

public class LoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        initLoadActivty();
    }

    private void initLoadActivty() {
        ImageView loadImage = (ImageView) findViewById(R.id.loadImage);
        AlphaAnimation animation = new AlphaAnimation(0.1f,1.0f);
        animation.setDuration(2000);
        loadImage.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
 //               Toast.makeText(LoadActivity.this, "动画开始", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            //    Toast.makeText(LoadActivity.this, "动画结束", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoadActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


}
