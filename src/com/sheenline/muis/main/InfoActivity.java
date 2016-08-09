package com.sheenline.muis.main;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.sheenline.muis.R;
import com.sheenline.muis.common.Constant;
import com.sheenline.muis.common.DrawWaveViewByA;
import com.sheenline.muis.common.DrawWaveViewByAll;
import com.sheenline.muis.common.DrawWaveViewByB;
import com.sheenline.muis.common.TestJNI;
import com.sheenline.muis.common.Tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

// 主界面
public class InfoActivity extends FragmentActivity {
    // 启动设备
    private ActionBar actionBar; // 声明ActionBar
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onSetFullScreen();
        actionBar = getActionBar(); // 得<span></span>到ActionBar
        actionBar.hide();
        setContentView(R.layout.muisjob);

        Button btncancel = (Button) findViewById(R.id.jobsysbutton2);
        btncancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

               System.exit(0);

            }

        });

    }
    // 退出
    @Override
    protected void onDestroy() {

        super.onDestroy();

    }




    // 返回物理键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

                System.exit(0);
                return true;

        }
        return false;
    }

    // 设置全屏显示
    private void onSetFullScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}
