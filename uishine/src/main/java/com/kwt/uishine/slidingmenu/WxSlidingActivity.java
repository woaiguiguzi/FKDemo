package com.kwt.uishine.slidingmenu;

import android.os.Bundle;
import com.kwt.uishine.R;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;


import java.util.ArrayList;
import java.util.List;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
public class WxSlidingActivity extends SlidingFragmentActivity {
    ViewPager mViewPager;
    FragmentPagerAdapter mAdapter;
    List<Fragment> mFragments = new ArrayList<Fragment>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wx_sliding);
        // 初始化SlideMenu
        initRightMenu();
        // 初始化ViewPager
        initViewPager();
    }

    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        SidingTab1Fragment tab1Fragmentv =  new SidingTab1Fragment();
        SidingTab2Fragment tab2Fragmentv =  new SidingTab2Fragment();
        SidingTab3Fragment tab3Fragmentv =  new SidingTab3Fragment();
        mFragments.add(tab1Fragmentv);
        mFragments.add(tab2Fragmentv);
        mFragments.add(tab3Fragmentv);
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        mViewPager.setAdapter(mAdapter);
    }

    private void initRightMenu() {
        Fragment leftMenuFragment = new MenuLeftFragment();
        setBehindContentView(R.layout.left_fragment);//设置一个布局，此布局中只有一个FrameLayout，然后使用FragmentManager将Fragment替换掉此Fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.left_frame,leftMenuFragment).commit();
        SlidingMenu menu = getSlidingMenu();
        menu.setMode(SlidingMenu.LEFT_RIGHT);//设置侧滑菜单的位置
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);//设置触摸屏幕的模式 Margin边缘
        menu.setShadowWidthRes(R.dimen.shadow_width);//设置阴影宽度
        menu.setShadowDrawable(R.drawable.shadow);//设置侧滑菜单的阴影效果
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);//设置slidingMenu离屏幕的偏移量
        menu.setFadeDegree(0.35f);//设置渐入渐出的值
        menu.setSecondaryShadowDrawable(R.drawable.shadow);//设置右边（二级）侧滑菜单的阴影效果
        menu.setSecondaryMenu(R.layout.right_fragment);//设置右边（二级）侧滑菜单
        Fragment rightMenuFragment = new MenuRightFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.right_frame, rightMenuFragment).commit();


    }
    public void showLeftMenu(View view)
    {
        getSlidingMenu().showMenu();
    }

    public void showRightMenu(View view)
    {
        getSlidingMenu().showSecondaryMenu();
    }
}
