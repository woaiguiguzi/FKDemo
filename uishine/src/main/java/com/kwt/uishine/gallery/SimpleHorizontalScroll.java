package com.kwt.uishine.gallery;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kwt.uishine.R;

public class SimpleHorizontalScroll extends AppCompatActivity {
    private LinearLayout mGallery;
    private int[] mImgIds;
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_horizontal_scroll);
        mInflater = LayoutInflater.from(this);
        initData();
        initView();
    }
    private void initData()
    {
        mImgIds = new int[] { R.drawable.a, R.drawable.b, R.drawable.c,
                R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g,
                R.drawable.h};
    }
    private void initView() {
        mGallery = (LinearLayout) findViewById(R.id.ll_gallery);

        for (int i = 0; i < mImgIds.length; i++) {
            View view = mInflater.inflate(R.layout.gallery_item,mGallery,false);
            ImageView img = (ImageView) view.findViewById(R.id.gallery_item_image);
            img.setImageResource(mImgIds[i]);
            TextView text = (TextView) view.findViewById(R.id.gallery_item_text);
            text.setText("some info ");
            mGallery.addView(view);
        }
    }
}
