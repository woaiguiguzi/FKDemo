package com.kwt.uishine;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SeniorFragmnetActivity extends AppCompatActivity implements
        BookListFragment.Callbacks{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_fragmnet);
    }

    @Override
    public void onItemSelected(Integer id) {
        // 创建Bundle，准备向Fragment传入参数
        Bundle arguments = new Bundle();
        arguments.putInt(BookDetailFragment.ITEM_ID, id);
        // 创建BookDetailFragment对象
        BookDetailFragment fragment = new BookDetailFragment();
        // 向Fragment传入参数
        fragment.setArguments(arguments);
        // 使用fragment替换book_detail_container容器当前显示的Fragment
        getFragmentManager().beginTransaction()
                .replace(R.id.book_detail_container, fragment)
                .commit();  // ①
    }
}
