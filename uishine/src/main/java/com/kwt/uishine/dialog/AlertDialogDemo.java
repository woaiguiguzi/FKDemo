package com.kwt.uishine.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.Toast;

import com.kwt.uishine.R;

public class AlertDialogDemo extends Activity {
    Button simple,simpleList,singleChoice,muliChoice,customList,customView;
    String[] items = new String[] {
            "论语", "大学",
            "中庸",
            "孟子" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_dialog);
        initView();
        setClickListener();
    }

    private void setClickListener() {
        simpleClick();
        simpleListClick();
        singleChoiceClick();
        muliChoiceClick();
        customListClick();
        customViewClick();
    }

    private void customViewClick() {
        customView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableLayout loginForm = (TableLayout) getLayoutInflater().inflate(R.layout.alert_custom_view,null);
                 new AlertDialog.Builder(AlertDialogDemo.this)
                        .setTitle("自定义view对话框")
                        .setIcon(R.drawable.tools)
                        .setView(loginForm)
                        .setPositiveButton("登陆", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(AlertDialogDemo.this, "登陆成功", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               //
                            }
                        }).create()
                        .show();

            }
        });
    }

    private void customListClick() {
        customList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AlertDialogDemo.this)
                        .setTitle("自定义列表对话框")
                        .setIcon(R.drawable.tools)
                        .setAdapter(new ArrayAdapter<String>(AlertDialogDemo.this,R.layout.alert_items,items),null);
                setPositiveButton(builder);
                setNegativeButton(builder)
                        .create().show();
            }
        });
    }

    private void muliChoiceClick() {
        muliChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AlertDialogDemo.this)
                        .setTitle("多选列表对话框")
                        .setIcon(R.drawable.tools)
                                // 设置多选列表项，设置勾选第2项、第4项
                        .setMultiChoiceItems(items, new boolean[]{false, true, false, true}, null);
                setPositiveButton(builder);
                setNegativeButton(builder)
                        .create().show();
            }
        });
    }

    private void singleChoiceClick() {
        singleChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AlertDialogDemo.this)
                        .setTitle("单选列表对话框")
                        .setIcon(R.drawable.tools)
                                // 设置单选列表项，默认选中第二项（索引为1）
                        .setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(AlertDialogDemo.this, "您选中了《" + items[which] + "》", Toast.LENGTH_SHORT).show();
                            }
                        });
                setPositiveButton(builder);
                setNegativeButton(builder)
                        .create().show();
            }
        });
    }

    private void simpleListClick() {
        simpleList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AlertDialogDemo.this)
                        .setTitle("简单列表对话框")
                        .setIcon(R.drawable.tools)
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(AlertDialogDemo.this, "您选中了《" + items[which] + "》", Toast.LENGTH_SHORT).show();
                            }
                        });
                setPositiveButton(builder);
                setNegativeButton(builder)
                        .create().show();
            }
        });
    }

    private void simpleClick() {
        simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AlertDialogDemo.this)
                        .setTitle("简单对话框")
                        .setIcon(R.drawable.tools);
                setPositiveButton(builder);
                setNegativeButton(builder)
                .create().show();
            }
        });
    }


    private void initView() {
        simple = (Button) findViewById(R.id.simple);
        simpleList = (Button) findViewById(R.id.simpleList);
        singleChoice = (Button) findViewById(R.id.singleChoice);
        muliChoice = (Button) findViewById(R.id.multiChoice);
        customList= (Button) findViewById(R.id.customList);
        customView = (Button) findViewById(R.id.customView);
    }
    public AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder){
        return  builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AlertDialogDemo.this,"您点击了确定按钮",Toast.LENGTH_SHORT).show();

            }
        });
    }
    public AlertDialog.Builder setNegativeButton(AlertDialog.Builder builder){
        return  builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AlertDialogDemo.this, "您点击了取消按钮", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
