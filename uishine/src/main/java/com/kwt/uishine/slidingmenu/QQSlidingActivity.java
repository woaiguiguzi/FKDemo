package com.kwt.uishine.slidingmenu;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;

import com.kwt.uishine.R;

public class QQSlidingActivity extends AppCompatActivity {
    private SlidingPaneLayout slidingPaneLayout;
    private QQMenuFragment menuFragment;
    private QQContentFragment contentFragment;
    private DisplayMetrics displayMetrics = new DisplayMetrics();
    private int maxMargin = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        setContentView(R.layout.qq_main_layout);
        slidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.slidingpanellayout);
        menuFragment = new QQMenuFragment();
        contentFragment = new QQContentFragment();

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.slidingpane_menu, menuFragment);
        transaction.replace(R.id.slidingpane_content, contentFragment);
        transaction.commit();
        maxMargin = displayMetrics.heightPixels / 10;//10
        slidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {

            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                int contentMargin = (int) (slideOffset * maxMargin);
                FrameLayout.LayoutParams contentParams = contentFragment
                        .getCurrentViewParams();
                contentParams.setMargins(0, contentMargin, 0, contentMargin);
                contentFragment.setCurrentViewPararms(contentParams);

                float scale = 1 - ((1 - slideOffset) * maxMargin * 2)
                        / (float) displayMetrics.heightPixels;
                menuFragment.getCurrentView().setScaleX(scale);// 设置缩放的基准点
                menuFragment.getCurrentView().setScaleY(scale);// 设置缩放的基准点
                menuFragment.getCurrentView().setPivotX(0);// 设置缩放和选择的点
                menuFragment.getCurrentView().setPivotY(
                        displayMetrics.heightPixels / 2);
                menuFragment.getCurrentView().setAlpha(slideOffset);
            }

            @Override
            public void onPanelOpened(View panel) {

            }

            @Override
            public void onPanelClosed(View panel) {

            }
        });
    }
    /**
     * @return the slidingPaneLayout
     */
    public SlidingPaneLayout getSlidingPaneLayout() {
        return slidingPaneLayout;
    }
}
