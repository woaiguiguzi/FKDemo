package com.kwt.uishine.gallery;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.kwt.uishine.R;

public class simpleGallery extends AppCompatActivity {
    Gallery  gallery;
    private Integer[] mImageIds = { R.mipmap.b, R.mipmap.c,
            R.mipmap.d, R.mipmap.f, R.mipmap.g };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_gallery);
         initView();
    }

    private void initView() {
        gallery = (Gallery) findViewById(R.id.simpleGallery);
        gallery.setAdapter(new ImageAdapter(simpleGallery.this));
    }
    public class ImageAdapter extends BaseAdapter{
        private Context mContext;

        public ImageAdapter(Context context) {
            mContext = context;
        }
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object getItem(int position) {
            return mImageIds[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView i = new ImageView(mContext);

            i.setImageResource(mImageIds[position%mImageIds.length]);
            i.setScaleType(ImageView.ScaleType.FIT_XY);
            i.setLayoutParams(new Gallery.LayoutParams(400, 530));

            return i;
        }
    }
}
