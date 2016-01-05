package com.kwt.uishine;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

import com.kwt.uishine.actionbar.ActionList;
import com.kwt.uishine.dialog.DialogList;
import com.kwt.uishine.gallery.GalleryList;
import com.kwt.uishine.slidingmenu.SlidingListActivity;
import com.kwt.uishine.slidingmenu.WxSlidingActivity;
import com.kwt.uishine.thirdsdk.SimpleShare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControlhomeActivity extends Activity {

    ListView mcontrolList;
    List<Map<String, String>> mControls = new ArrayList<Map<String, String>>();
    String[] names;
    String[] details;
    Adapter madapter;
    Button titleBackBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomTitleBar.getTitleBar(this, "控件类");
        setContentView(R.layout.activity_controlhome);
        initData();
        initView();
        setListener();
     }
    public void setListener(){
        setBackCliclListener();
        setControlItemClickListener();
    }

    private void setBackCliclListener() {
        titleBackBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                KeyEvent newEvent = new KeyEvent(KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_BACK);
                onKeyDown(KeyEvent.KEYCODE_BACK, newEvent);//必须重写
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // 按下的如果是BACK，同时没有重复
            finish();
           //  askForOut();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    private void askForOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("确定退出").setMessage("确定退出？").setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).setCancelable(false).show();
    }
    public void initData(){
        names = new String[]{"List", "Framgent", "actionBar", "SliddingMenu","Gellary","tabBar","Menu","Dialog","ThirdSDK"};
        details = new String[]{"列表展示", "多页展示", "actionBar", "QQ,微信Slidding Menu","画廊","tabBar导航","普通，列表，单选，多选，pop","标准，pop,自定义","第三方插件 如分享和支付"};
        for(int i=0;i<names.length;i++){
            Map<String ,String> map = new HashMap<String,String>();
            map.put("name",names[i]);
            map.put("detail",details[i]);
            mControls.add(map);
        }
    }
    public void initView(){
        mcontrolList = (ListView) findViewById(R.id.controlList);
        titleBackBtn = (Button) findViewById(R.id.TitleBackBtn);
        ControlAdapter madapter = new ControlAdapter(this,mControls);
        mcontrolList.setAdapter(madapter);
    }
    public void  setControlItemClickListener(){
        mcontrolList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent ;
                switch (mControls.get(position).get("name")){
                    case "List":
                        Toast.makeText(ControlhomeActivity.this,"亲，此功能尚未开发",Toast.LENGTH_SHORT).show();
                        break;
                    case "Framgent":
                        intent = new Intent(ControlhomeActivity.this,FragmentListActivity.class);
                        startActivity(intent);
                        break;
                    case "actionBar":
                        intent = new Intent(ControlhomeActivity.this,ActionList.class);
                        startActivity(intent);
                        break;
                    case "SliddingMenu":
                        intent = new Intent(ControlhomeActivity.this, SlidingListActivity.class);
                        startActivity(intent);
                        break;
                    case "Gellary":
                        intent = new Intent(ControlhomeActivity.this, GalleryList.class);
                        startActivity(intent);
                        break;
                    case "tabBar":
                        intent = new Intent(ControlhomeActivity.this,TabbarListActivity.class);
                        startActivity(intent);
                        break;
                    case "Menu":
                        intent = new Intent(ControlhomeActivity.this, menuDemo.class);
                        startActivity(intent);
                        break;
                    case "Dialog":
                        intent = new Intent(ControlhomeActivity.this, DialogList.class);
                        startActivity(intent);
                        break;
                    case "ThirdSDK":
                        intent = new Intent(ControlhomeActivity.this, SimpleShare.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }

            }
        });
    }
    class ControlAdapter extends BaseAdapter{
       List<Map<String,String>> controls;
        ControlhomeActivity context;

        public ControlAdapter(ControlhomeActivity context, List<Map<String, String>> controls) {
            super();
            this.context = context;
            this.controls = controls;
        }

        @Override
        public int getCount() {
            return controls.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(convertView ==null){
                convertView = View.inflate(context,R.layout.control_item,null);
                holder = new ViewHolder();
                holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
                holder.tvDetail = (TextView) convertView.findViewById(R.id.tvDetail);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder)convertView.getTag();
            }
            Map<String,String> map = controls.get(position);
            holder.tvName.setText(map.get("name"));
            holder.tvDetail.setText(map.get("detail"));
            return convertView;
        }
        class ViewHolder{
           TextView  tvName,tvDetail;

        }
    }

}
