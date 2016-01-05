package com.kwt.uishine.gallery;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kwt.uishine.R;
import com.kwt.uishine.common.ImageUtils;
import com.kwt.uishine.common.LocalImageHelper;
import com.kwt.uishine.widget.AlbumViewPager;
import com.kwt.uishine.widget.FilterImageView;
import com.kwt.uishine.widget.MatrixImageView;
import android.widget.LinearLayout.LayoutParams;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.toolsfinal.StringUtils;

public class DynamicPost extends Activity implements View.OnClickListener, MatrixImageView.OnSingleTapListener {
    private ImageView mBack;//返回键
    private View mSend;//发送
    private EditText mContent;//动态内容编辑框
    private InputMethodManager imm;//软键盘管理
    private TextView textRemain;//字数提示
    private TextView picRemain;//图片数量提示
    private ImageView add;//添加图片按钮
    private LinearLayout picContainer;//图片容器
    private List<LocalImageHelper.LocalFile> pictures = new ArrayList<>();//图片路径数组
    HorizontalScrollView scrollView;//滚动的图片容器
    View editContainer;//动态编辑部分
    View pagerContainer;//图片显示部分

    //显示大图的viewpager 集成到了Actvity中 下面是和viewpager相关的控件
    AlbumViewPager viewpager;//大图显示pager
    View mHeaderBar;//大图顶部栏
    ImageView mBackView;//返回/关闭大图
    TextView mCountView;//大图数量提示
    ImageView delete;//删除按钮 无保存按钮

    int size;//小图大小
    int padding;//小图间距
    DisplayImageOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_post);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        //设置ImageLoader参数
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(false)
                .showImageForEmptyUri(R.drawable.dangkr_no_picture_small)
                .showImageOnFail(R.drawable.dangkr_no_picture_small)
                .showImageOnLoading(R.drawable.dangkr_no_picture_small)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new SimpleBitmapDisplayer()).build();
        initViews();
        initData();
    }

    /**
     * 初始化views
     */
    private void initViews() {
        editContainer = findViewById(R.id.post_edit_container);
        mBack = (ImageView) findViewById(R.id.post_back);
        mSend = findViewById(R.id.post_send);
        mContent = (EditText) findViewById(R.id.post_content);
        textRemain = (TextView) findViewById(R.id.post_text_remain);
        scrollView = (HorizontalScrollView) findViewById(R.id.post_scrollview);
        picContainer = (LinearLayout) findViewById(R.id.post_pic_container);
        add = (ImageView) findViewById(R.id.post_add_pic);
        picRemain = (TextView) findViewById(R.id.post_pic_remain);

        pagerContainer = findViewById(R.id.pagerview);
        mHeaderBar = findViewById(R.id.album_item_header_bar);
        viewpager = (AlbumViewPager) findViewById(R.id.albumviewpager);
        mBackView = (ImageView) findViewById(R.id.header_bar_photo_back);
        mCountView = (TextView) findViewById(R.id.header_bar_photo_count);
        delete = (ImageView) findViewById(R.id.header_bar_photo_delete);
        delete.setVisibility(View.VISIBLE);

        viewpager.setOnPageChangeListener(pageChangeListener);
        viewpager.setOnSingleTapListener(this);//设置图片单击事件监听
        mBackView.setOnClickListener(this);
        mCountView.setOnClickListener(this);
        delete.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mSend.setOnClickListener(this);
        add.setOnClickListener(this);
        mContent.addTextChangedListener(textWatcher);
    }
    private void initData() {
        size = (int) getResources().getDimension(R.dimen.size_100);//若令 size = 100,则实际展现尺寸没100dp
        padding = (int) getResources().getDimension(R.dimen.padding_10);
    }
    //监控EditText中的输入内容并限制其输入字符个数
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub

        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
            // TODO Auto-generated method stub

        }

        @Override
        public void afterTextChanged(Editable content) {
            textRemain.setText(content.toString().length() + "/140");
        }
    };
    private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            if (viewpager.getAdapter() != null) {
                String text = (position + 1) + "/" + viewpager.getAdapter().getCount();
                mCountView.setText(text);
            } else {
                mCountView.setText("0/0");
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }
    };
    @Override
    public void onBackPressed() {
        if (pagerContainer.getVisibility() != View.VISIBLE) {
            //showSaveDialog();
        }
        else {
            hideViewPager();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.post_back:
                finish();
                break;
            case R.id.header_bar_photo_back:
            case R.id.header_bar_photo_count:
                hideViewPager();
                break;
            case R.id.header_bar_photo_delete:
                final int index = viewpager.getCurrentItem();
                new AlertDialog.Builder(this)
                        .setTitle("提示")
                        .setMessage("要删除这张照片吗?")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }

                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                pictures.remove(index);
                                if (pictures.size() == 9) {
                                    add.setVisibility(View.GONE);
                                } else {
                                    add.setVisibility(View.VISIBLE);
                                }
                                if (pictures.size() == 0) {
                                    hideViewPager();
                                }
                                picContainer.removeView(picContainer.getChildAt(index));
                                picRemain.setText(pictures.size() + "/9");
                                mCountView.setText((viewpager.getCurrentItem() + 1) + "/" + pictures.size());
                                viewpager.getAdapter().notifyDataSetChanged();
                                LocalImageHelper.getInstance().setCurrentSize(pictures.size());
                            }
                        }).show();

                break;
            case R.id.post_send:
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);//隐藏软键盘
                String content = mContent.getText().toString();
                if (StringUtils.isEmpty(content) && pictures.isEmpty()) {
                    Toast.makeText(this, "请添写动态内容或至少添加一张图片", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    //设置为不可点击，防止重复提交
                    v.setEnabled(false);
                }
                break;
            case R.id.post_add_pic:
                Intent intent = new Intent(DynamicPost.this, LocalAlbum.class);
                startActivityForResult(intent, ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP);
                break;
            default:
                if (v instanceof FilterImageView) {
                    for (int i = 0; i < picContainer.getChildCount(); i++) {
                        if (v == picContainer.getChildAt(i)) {
                            showViewPager(i);
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onSingleTap() {
        hideViewPager();
    }

    //显示大图pager
    private void showViewPager(int index) {
        pagerContainer.setVisibility(View.VISIBLE);
        editContainer.setVisibility(View.GONE);
        viewpager.setAdapter(viewpager.new LocalViewPagerAdapter(pictures));
        viewpager.setCurrentItem(index);
        mCountView.setText((index + 1) + "/" + pictures.size());
        AnimationSet set = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation((float) 0.9, 1, (float) 0.9, 1, pagerContainer.getWidth() / 2, pagerContainer.getHeight() / 2);
        scaleAnimation.setDuration(200);
        set.addAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation((float) 0.1, 1);
        alphaAnimation.setDuration(200);
        set.addAnimation(alphaAnimation);
        pagerContainer.startAnimation(set);
    }
    //关闭大图显示
    private void hideViewPager() {
        pagerContainer.setVisibility(View.GONE);
        editContainer.setVisibility(View.VISIBLE);
        AnimationSet set = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, (float) 0.9, 1, (float) 0.9, pagerContainer.getWidth() / 2, pagerContainer.getHeight() / 2);
        scaleAnimation.setDuration(200);
        set.addAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(200);
        set.addAnimation(alphaAnimation);
        pagerContainer.startAnimation(set);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP:
                if (LocalImageHelper.getInstance().isResultOk()) {
                    LocalImageHelper.getInstance().setResultOk(false);
                    //获取选中的图片
                    List<LocalImageHelper.LocalFile> files = LocalImageHelper.getInstance().getCheckedItems();
                    for (int i = 0; i < files.size(); i++) {
                        LayoutParams params = new LayoutParams(size, size);
                        params.rightMargin = padding;
                        FilterImageView imageView = new FilterImageView(this);
                        imageView.setLayoutParams(params);
                        imageView.setScaleType(ScaleType.CENTER_CROP);
                        ImageLoader.getInstance().displayImage(files.get(i).getThumbnailUri(), new ImageViewAware(imageView), options,
                                null, null, files.get(i).getOrientation());
                        imageView.setOnClickListener(this);
                        pictures.add(files.get(i));
                        if (pictures.size() == 9) {
                            add.setVisibility(View.GONE);
                        } else {
                            add.setVisibility(View.VISIBLE);
                        }
                        picContainer.addView(imageView, picContainer.getChildCount() - 1);
                        picRemain.setText(pictures.size() + "/9");
                        LocalImageHelper.getInstance().setCurrentSize(pictures.size());
                    }
                    //清空选中的图片
                    files.clear();
                    //设置当前选中的图片数量
                    LocalImageHelper.getInstance().setCurrentSize(pictures.size());
                    //延迟滑动至最右边
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            scrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
                        }
                    }, 50L);
                }
                //清空选中的图片
                LocalImageHelper.getInstance().getCheckedItems().clear();
                break;
            default:
                break;
        }
    }
}