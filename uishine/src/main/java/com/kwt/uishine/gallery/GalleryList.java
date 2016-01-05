package com.kwt.uishine.gallery;

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

public class GalleryList extends AppCompatActivity {
    ListView mlvGallery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_list);
        initData();
        setItemClick();
    }

    private void setItemClick() {
        mlvGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position){
                    case 0 :
                        intent = new Intent(GalleryList.this,simpleGallery.class);
                        startActivity(intent);
                        break;
                    case 1 :
                        intent = new Intent(GalleryList.this,ImageSwitcherDemo.class);
                        startActivity(intent);
                        break;
                    case 2 :
                        intent = new Intent(GalleryList.this,PhotoGet.class);
                        startActivity(intent);
                        break;
                    case 3 :
                        Toast.makeText(GalleryList.this,"亲，此功能尚未开发",Toast.LENGTH_SHORT).show();
                        break;
                    case 4 :
                        intent = new Intent(GalleryList.this,HorizontalScrollDemo.class);
                        startActivity(intent);
                        break;
                    case 5 :
                        intent = new Intent(GalleryList.this,SimpleHorizontalScroll.class);
                        startActivity(intent);
                        break;
                    case 6 :
                        intent = new Intent(GalleryList.this,DynamicPost.class);
                        startActivity(intent);
                        break;
                    default :
                        break;
                }
            }
        });
    }

    private void initData() {
        mlvGallery = (ListView) findViewById(R.id.lvGallery);
        mlvGallery.setAdapter(new ArrayAdapter<String>(GalleryList.this,android.R.layout.simple_expandable_list_item_1,
                new String[]{"Gallery","ImageSwipe","PhoneCrop","3DGallery","HorizontalScroll","SimpleHorizontalScrool","仿微信QQ说说发布"}));
    }

}
