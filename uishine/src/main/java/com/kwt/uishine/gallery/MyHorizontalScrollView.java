package com.kwt.uishine.gallery;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Auser on 2015/12/24.
 */
public class MyHorizontalScrollView extends HorizontalScrollView implements OnClickListener {
    /**
     * 图片滚动时的回调接口
     * @param
     */
    public interface CurrentImageChangeListener{
        void onCurrentImgChanged(int position,View viewIndicator);
    }

    /**
     * 条目点击时回调
     * @param
     */
    public interface  OnItemClickListener{
        void onClick(View view,int pos);
    }
    private CurrentImageChangeListener mListener;
    private OnItemClickListener mOnClickListener;
    private static final String TAG = "MyHorizontalScrollView";

    /**
     * HorizontalListView中的LinearLayout
     * @param
     */
    private LinearLayout mContainer;

    /**
     * 子元素的高度
     * @param
     */
    private int mChildHeight;

    /**
     * 子元素的宽度
     * @param
     */
    private int mChildWidth;

    /**
     * 当前第一张图片的下标
     * @param
     */
    private int mFirstIndex;

    /**
     * 当前最后一张图片的index
     * @param
     */
    private int mCurrentIndex;

    /**
     * 当前第一个view
     * @param v
     */
    private View mFirstView;

    /**
     * 数据适配器
     * @param v
     */
    private HorizontalScrollViewAdapter mAdapter;

    /**
     * 每屏最多显示的个数
     * @param v
     */
    private int mCountOneScreen;

    /**
     * 屏幕的宽度
     * @param v
     */
    private int mScreenWidth;

    /**
     * 保存view和位置的键值对
     * @param v
     */
    private Map<View,Integer> mViewPos = new HashMap<View,Integer>();

    public  MyHorizontalScrollView(Context context,AttributeSet attrs){
        super(context,attrs);
        //获得屏幕宽度
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth = outMetrics.widthPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mContainer = (LinearLayout) getChildAt(0);//获取当前能看见的第一个
    }

    /**
     * 加载下一张图片
     * @param
     */
    protected  void loadNextImg(){
        //数组边界值计算
        if(mCurrentIndex == mAdapter.getCount()-1){
            return;
        }
        //移除第一张图片，且将水平滚动位置置0
        scrollTo(0,0);
        mViewPos.remove(mContainer.getChildAt(0));
        mContainer.removeViewAt(0);
        //获取下一张图片，并且设置onclick事件，且加入容器中
        View view = mAdapter.getView(++mCurrentIndex,null,mContainer);
        view.setOnClickListener(this);
        mContainer.addView(view);
        mViewPos.put(view,mCurrentIndex);
        //当前第一张图片下标
        mFirstIndex++;
        //如果设置了滚动监听则触发
        if(mListener != null){
            notifyCurrentImgChanged();
        }
    }

    /**
     * 加载前一张图片
     */
    protected void loadPreImg(){
        //如果当前已是第一张，则返回
        if(mFirstIndex == 0){
            return;
        }
        //获得当前应该显示为第一张图片的下标
        int index = mCurrentIndex - mCountOneScreen;
        if(index >=0){
            //移除最后一张
            int oldViewPos = mContainer.getChildCount()-1;
            mViewPos.remove(mContainer.getChildAt(oldViewPos));
            mContainer.removeViewAt(oldViewPos);
            //将此view放入第一位置
            View view = mAdapter.getView(index,null,mContainer);
            view.setOnClickListener(this);
            mViewPos.put(view, index);
            mContainer.addView(view);
            //水平滚动位置向左移动view的宽度个像素
            scrollTo(mChildWidth,0);
            //当前最后一个，第一个显示的下标--，
            mCurrentIndex--;
            mFirstIndex--;
            //回调
            if(mListener != null){
                notifyCurrentImgChanged();
            }
        }
    }

    /**
     * 滑动时的回调
     */
    private void notifyCurrentImgChanged() {
        //先清除所有的背景色，点击时会设置为蓝色
        for(int i=0;i<mContainer.getChildCount();i++){
            mContainer.getChildAt(i).setBackgroundColor(Color.WHITE);
        }
        mListener.onCurrentImgChanged(mFirstIndex,mContainer.getChildAt(0));
    }

    /**
     * 初始化数据，设置数据适配器
     * @param
     */
    public void initDatas(HorizontalScrollViewAdapter mAdapter){
        this.mAdapter = mAdapter;
        mContainer = (LinearLayout) getChildAt(0);
        //获得适配器中第一个view
        final  View view  = mAdapter.getView(0,null,mContainer);
        mContainer.addView(view);

        //强制计算当前view的宽和高
        if(mChildWidth == 0 && mChildHeight == 0){
            //MeasureSpec封装了父布局传递给子布局的布局要求  UNSPECIFIED子元素可以得到任意想要的大小
            int w = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
            int h = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
            view.measure(w,h);
            mChildWidth = view.getMeasuredWidth();
            mChildHeight = view.getMeasuredHeight();
            Log.e(TAG,view.getMeasuredWidth()+","+view.getMeasuredHeight());
            mCountOneScreen =(mScreenWidth/mChildWidth == 0)?mScreenWidth/mChildWidth+1:mScreenWidth/mChildWidth+2;
            Log.e(TAG, "mCountOneScreen = " + mCountOneScreen
                    + " ,mChildWidth = " + mChildWidth);
            initFirstScreenChildren(mCountOneScreen);
        }
    }

    /**
     * 加载第一屏的view
     * @param mCountOneScreen
     */
    private void initFirstScreenChildren(int mCountOneScreen) {
        mContainer = (LinearLayout) getChildAt(0);
        mContainer.removeAllViews();
        mViewPos.clear();
        for(int i =0;i<mCountOneScreen;i++){
            View view = mAdapter.getView(i,null,mContainer);
            view.setOnClickListener(this);
            mContainer.addView(view);
            mViewPos.put(view, i);
            mCurrentIndex = i;
        }
        if(mListener != null){
            notifyCurrentImgChanged();
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_MOVE :
                int scrollX = getScrollX();
                //如果当前scrollX为view的宽度，加载下一张，移除第一张
                if(scrollX > mChildWidth){
                    loadNextImg();
                }
                //如果当前scroll = 0,往前设置一张，移除最后一张
                if(scrollX == 0){
                    loadPreImg();
                }
                break;
        }
        return super.onTouchEvent(ev);
    }
    @Override
    public void onClick(View v) {
        if(mOnClickListener != null){
            for(int i= 0;i<mContainer.getChildCount();i++){
                mContainer.getChildAt(i).setBackgroundColor(Color.WHITE);
            }
            mOnClickListener.onClick(v,mViewPos.get(v));
        }

    }
    public void setOnItemClickListener(OnItemClickListener mOnClickListener)
    {
        this.mOnClickListener = mOnClickListener;
    }

    public void setCurrentImageChangeListener(
            CurrentImageChangeListener mListener)
    {
        this.mListener = mListener;
    }


}
