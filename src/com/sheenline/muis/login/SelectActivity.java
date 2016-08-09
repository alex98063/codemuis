package com.sheenline.muis.login;

import com.sheenline.muis.R;
import com.sheenline.muis.main.InfoActivity;
import com.sheenline.muis.main.MetalActivity;
import com.sheenline.muis.main.WeldActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SelectActivity extends Activity {

    // 设置服务器IP和端口

    boolean logedin = false;

    private Register mregisterSQL;

    public void addUser(String a, String b,String c,String d) {

        if (a.equals("") || b.equals("")) {
            Toast.makeText(this, "注册失败！", Toast.LENGTH_LONG).show();
            return;
        }

        Boolean boolean1 = mregisterSQL.insert(a, b,c,d);

        if (boolean1) {

            Toast.makeText(this, "注册成功!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "注册失败,用户名已存在!", Toast.LENGTH_LONG).show();
        }
    }

    public void deleteUser() {

        mregisterSQL.delete(0);

        Toast.makeText(this, "Delete Successed!", Toast.LENGTH_SHORT).show();
    }

    public String[] findUser(String aString) {
        if (aString == null) {
            return null;
        }
        String[] bString;

        bString = mregisterSQL.find(aString);

        return bString;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.muisselect);

        setUpViews();

        Intent replyintent = getIntent(); // 获取已有的intent对象

        logedin = replyintent.getBooleanExtra("LOGIN", true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main1, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log.d("testsys", "system exit!");

            System.exit(0);
            return true;
        }
        return false;
    }

    // 母材探伤模块
    public void OnMetalClick(View v) {
        if (logedin) {

            Intent intent = new Intent();
            intent.setClass(SelectActivity.this, MetalActivity.class);
            intent.putExtra("type", "Metal");
            startActivity(intent);
            Log.d("testsys","Metal Mode selected.");
        } else {
            Toast.makeText(this, "请先登录！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        LayoutInflater factory = LayoutInflater.from(this);

        switch (item.getItemId()) {
            case R.id.menu_login:

                final View DialogView = factory.inflate(R.layout.muislogin, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("登陆框").setView(DialogView)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                EditText etUser1 = (EditText) (DialogView
                                        .findViewById(R.id.login_account_user));
                                EditText etPassWord1 = (EditText) (DialogView
                                        .findViewById(R.id.login_password_user));
                                String[] bString = findUser(etUser1.getText().toString());

                                if (bString == null)

                                {
                                    logedin = false;
                                    Toast.makeText(getApplicationContext(), "用户不存在！",
                                            Toast.LENGTH_LONG).show();

                                } else {
                                    if (bString[0].equals(etPassWord1.getText().toString())) {
                                        logedin = true;

                                        Toast.makeText(getApplicationContext(), etUser1.getText().toString()+"登录成功！",
                                                Toast.LENGTH_LONG).show();
                                    } else {
                                        logedin = false;
                                        Toast.makeText(getApplicationContext(), "密码不正确！",
                                                Toast.LENGTH_LONG).show();
                                    }
                                }

                            }

                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                logedin = false;

                            }

                        })
                        .create();

                builder.show();
                break;

            case R.id.menu_register:
                final View DialogView1 = factory.inflate(R.layout.muisregister, null);

                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("注册").setView(DialogView1)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                EditText etUser = (EditText) (DialogView1
                                        .findViewById(R.id.re_account_user));
                                EditText etPassWord = (EditText) (DialogView1
                                        .findViewById(R.id.re_password_user));
                                EditText etbureau = (EditText) (DialogView1
                                        .findViewById(R.id.re_bureau_et));
                                EditText etsmallbureau = (EditText) (DialogView1
                                        .findViewById(R.id.re_smallbureau_et));
                                addUser(etUser.getText().toString(),
                                        etPassWord.getText().toString(),
                                        etbureau.getText().toString(),
                                        etsmallbureau.getText().toString()
                                       );
                            }

                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                // System.exit(0);
                            }

                        })
                        .create();

                builder1.show();
                break;

        }

        return true;
    }


    public void OnInfoClick(View v) {
        if (logedin) {
            Intent intent = new Intent();
            intent.setClass(SelectActivity.this, InfoActivity.class);
            intent.putExtra("type", "Info");
            startActivity(intent);
            Log.d("testsys","Info Mode selected.");
        } else {
            Toast.makeText(this, "请先登录！", Toast.LENGTH_SHORT).show();
        }
    }

    // 焊缝探伤模块
    public void OnWeldClick(View v) {
        if (logedin) {

            Intent intent = new Intent();
            intent.setClass(SelectActivity.this, WeldActivity.class);
            intent.putExtra("type", "Weld");
            startActivity(intent);
            Log.d("testsys","Weld Mode selected.");
        } else {
            Toast.makeText(this, "请先登录！", Toast.LENGTH_SHORT).show();
        }
    }

    public void setUpViews() {
        mregisterSQL = new Register(this);
        mregisterSQL.select();

    }

    public void updateUser() {

        mregisterSQL.update(0, "ddd", "eee","fff","hhh");

        Toast.makeText(this, "Update Successed!", Toast.LENGTH_SHORT).show();
    }

}
