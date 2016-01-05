package com.kwt.uishine.gallery;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.kwt.uishine.R;

public class ImageSwitcherDemo extends Activity {
    private ImageSwitcher is;
    private Gallery gallery;
    //mipmaps 是一种可视化技术渲染算法，速度相当快
    private Integer[] mThumbIds = { R.mipmap.b, R.mipmap.c,
            R.mipmap.d, R.mipmap.f, R.mipmap.g,
    };

    private Integer[] mImageIds = { R.mipmap.b, R.mipmap.c,
            R.mipmap.d, R.mipmap.f, R.mipmap.g, };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_switcher);
        initView();
        setOnItemClick();
    }

    private void setOnItemClick() {
        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                is.setImageResource(mImageIds[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initView() {
        is = (ImageSwitcher) findViewById(R.id.switcher);
        is.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(ImageSwitcherDemo.this);
                imageView.setBackgroundColor(0xFF000000);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);//填充效果
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                 return imageView; }
        });
        gallery = (Gallery) findViewById(R.id.gallery);
        gallery.setAdapter(new ImageAdapter(ImageSwitcherDemo.this));

    }
    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return mThumbIds[position];
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView i = new ImageView(mContext);
            i.setImageResource(mThumbIds[position]);
            i.setAdjustViewBounds(true);
            i.setLayoutParams(new Gallery.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            i.setBackgroundResource(R.mipmap.e);
            return i;
        }


    }
}
