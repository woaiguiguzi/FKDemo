package com.kwt.uishine.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kwt.uishine.R;

import java.util.Timer;
import java.util.TimerTask;

public class PopProgressDialog extends Activity {
    PopupWindow popup;
    Button button,custButton,cricleButton, btnProgress;
    int  screenWidth = 0 ;
    Dialog dialog,circleDialog;
    final Timer timer = new Timer(true);
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            int msgId = msg.what;
            switch (msgId) {
                case 1:
                    dialog.dismiss();
                    break;
                case 2:
                    circleDialog.dismiss();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private void setTimerTask() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = Message.obtain();
                message.what = 1;
                handler.sendMessage(message);
            }
        }, 2000);
    }
    private void setCircleTimerTask() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = Message.obtain();
                message.what = 2;
                handler.sendMessage(message);
            }
        }, 3000);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_progress_dialog);
        initView();
        setClickListenr();
    }

    private void initView() {
        View pop = this.getLayoutInflater().inflate(R.layout.pop_window,null);
        screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        popup = new PopupWindow(pop,520,100);
        button = (Button) findViewById(R.id.btnPop);
        custButton = (Button) findViewById(R.id.btnProgressCust);
        cricleButton = (Button) findViewById(R.id.btnProgressCircle);
        btnProgress = (Button) findViewById(R.id.btnProgress);

    }

    private void setClickListenr() {
        setPopClickListener();
        setCustLoadClickListener();
        setCricLoadClickListener();
        setProgressClickListener();
    }

    private void setProgressClickListener() {
        btnProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog.show(PopProgressDialog.this, "任务执行中"
                        , "任务执行中，请等待", false, true);//false 不显示进度  true 能用取消按钮关闭
            }
        });
    }

    private void setCricLoadClickListener() {
        cricleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            View view = LayoutInflater.from(PopProgressDialog.this).inflate(R.layout.circle_loading, null);// 得到加载view
            LinearLayout layout = (LinearLayout) view.findViewById(R.id.criLoading);// 加载布局
            ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.goLoad);
            progressBar.setBackgroundResource(R.drawable.load_progressbar_style);
             circleDialog = new Dialog(PopProgressDialog.this, R.style.loading_dialog);// 创建自定义样式dialog
            circleDialog.setCancelable(false);// 不可以用“返回键”取消
            circleDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));// 设置布局
            circleDialog.show();
                setCircleTimerTask();
            }
        });
    }

    private void setCustLoadClickListener() {
        custButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = createLoadingDialog(PopProgressDialog.this,"加载中...");
                dialog.show();
                setTimerTask();
            }

        });
    }

    private void setPopClickListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击pop之外的地方pop消失(或者给所显示的activity添加onTouch事件)
                popup.setFocusable(true);
                popup.setOutsideTouchable(true);
                // 实例化一个ColorDrawable颜色为半透明
                ColorDrawable dw = new ColorDrawable(0xb0000000);
                // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
                popup.setBackgroundDrawable(dw);
                // 设置SelectPicPopupWindow弹出窗体动画效果
                // popup.setAnimationStyle(android.R.style.Animation_Dialog);
                //popup.setAnimationStyle(R.style.AppTheme_PopupOverlay);
                // popup.update();//刷新
                // 在控件下方展示
                //   popup.showAsDropDown(v);
                int[] location = new int[2];
                v.getLocationOnScreen(location);//获取控件在整个屏幕上的坐标位置
                //int height = v.getMeasuredHeight();获取控件的高度
                //int width = v.getMeasuredWidth();获取控件的宽度
                // 在控件上方展示（X坐标不变，Y坐标减去控件高度）
                //popup.showAtLocation(v, Gravity.NO_GRAVITY, location[0], location[1] - popup.getHeight());
                //在控件左边展示（X坐标减去高度，Y坐标不变）
                //popup.showAtLocation(v, Gravity.NO_GRAVITY, location[0]-popup.getWidth(), location[1]);
                //在控件右边展示（X坐标加上高度，Y坐标不变）
                // popup.showAtLocation(v, Gravity.NO_GRAVITY, location[0]+v.getWidth(), location[1]);
                // popup.showAtLocation(v, Gravity.NO_GRAVITY, location[0]+v.getWidth()/2, location[1]+popup.getHeight());
               //  popup.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                // popup.showAtLocation(v, Gravity.CENTER, 0, 0);
               popup.showAtLocation(v, Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
            }
        });
    }

    /**
     * 得到自定义的progressDialog
     * @param context
     * @param msg
     * @return
     */
    public static Dialog createLoadingDialog(Context context, String msg) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
        // main.xml中的ImageView
        ImageView wLoading = (ImageView) v.findViewById(R.id.img);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        // 加载动画
        //Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context, R.anim.load_animation);
        // 使用ImageView显示动画
       // spaceshipImage.startAnimation(hyperspaceJumpAnimation);  wLoading.setBackgroundResource(R.drawable.spinner_small);
        wLoading.setBackgroundResource(R.drawable.spinner_small);
        AnimationDrawable ad=(AnimationDrawable) wLoading.getBackground();
        ad.start();
        tipTextView.setText(msg);// 设置加载信息

        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog

        loadingDialog.setCancelable(false);// 不可以用“返回键”取消
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));// 设置布局
        return loadingDialog;

    }


}
