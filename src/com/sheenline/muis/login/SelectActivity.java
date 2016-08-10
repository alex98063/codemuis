package com.sheenline.muis.login;

import com.sheenline.muis.R;
import com.sheenline.muis.common.Constant;
import com.sheenline.muis.main.MetalActivity;
import com.sheenline.muis.main.WeldActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SelectActivity extends Activity {

    // 设置服务器IP和端口

    boolean logedin = false;

    private Register mregisterSQL;

    public void addUser(String a, String b, String c, String d) {

        if (a.equals("") || b.equals("")) {
            Toast.makeText(this, "注册失败！", Toast.LENGTH_SHORT).show();
            return;
        }

        Boolean boolean1 = mregisterSQL.insert(a, b, c, d);

        if (boolean1) {

            Toast.makeText(this, "注册成功!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "注册失败,用户名已存在!", Toast.LENGTH_SHORT).show();
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

        onUpdateview();


    }

    private void onUpdateview() {

        setContentView(R.layout.muislogin);
        setUpViews();


        Button btnlogin = (Button) findViewById(R.id.login_bt_login);
        btnlogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                EditText etUser = (EditText) (SelectActivity.this.findViewById(R.id.login_et_username));
                EditText etPassWord = (EditText) (SelectActivity.this.findViewById(R.id.login_et_userpassword));
                String[] bString = findUser(etUser.getText().toString());

                try

                {
                    if (etUser.getText() == null || etPassWord.getText() == null) {
                        logedin = false;
                        Toast.makeText(getApplicationContext(), "输入为空！", Toast.LENGTH_SHORT).show();
                    } else if (bString == null) {
                        logedin = false;
                        Toast.makeText(getApplicationContext(), "用户不存在！", Toast.LENGTH_SHORT).show();

                    } else if (bString[0].equals(etPassWord.getText().toString())) {
                        logedin = true;

                        setContentView(R.layout.muisselect);

                        showEditMenu();

                        Intent replyintent = getIntent(); // 获取已有的intent对象

                        logedin = replyintent.getBooleanExtra("LOGIN", true);


                        Toast.makeText(getApplicationContext(), etUser.getText().toString() + "登录成功！", Toast.LENGTH_SHORT).show();
                    } else {

                        logedin = false;
                        Toast.makeText(getApplicationContext(), "密码不正确！", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    logedin = false;
                    Toast.makeText(getApplicationContext(), "用户不存在！", Toast.LENGTH_SHORT).show();
                }


            }

        });


        Button btnregister = (Button) findViewById(R.id.login_bt_register);
        btnregister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                setContentView(R.layout.muisregister);


                Button btnreyes = (Button) findViewById(R.id.re_register_yes);
                btnreyes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        EditText etUser = (EditText) (SelectActivity.this.findViewById(R.id.re_account_user));
                        EditText etPassWord = (EditText) (SelectActivity.this.findViewById(R.id.re_password_user));
                        EditText etbureau = (EditText) (SelectActivity.this.findViewById(R.id.re_bureau_et));
                        EditText etsmallbureau = (EditText) (SelectActivity.this.findViewById(R.id.re_smallbureau_et));
                        try {
                            addUser(etUser.getText().toString(), etPassWord.getText().toString(), etbureau.getText().toString(), etsmallbureau.getText().toString());
                            Toast.makeText(getApplicationContext(), "注册成功！", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "注册失败！", Toast.LENGTH_SHORT).show();
                        } finally {
                            onUpdateview();
                        }


                    }

                });

                Button btnreno = (Button) findViewById(R.id.re_register_no);
                btnreno.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onUpdateview();
                    }
                });

            }

        });


    }

    Menu mMenu;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        getMenuInflater().inflate(R.menu.mainmenu, menu);

        hiddenEditMenu();
        return true;
    }

    private void hiddenEditMenu() {
        if (null != mMenu) {
//      MenuInflater menuInflater = getMenuInflater();
//      menuInflater.inflate(R.menu.activity_main, menu);
            //hidden when first time
            for (int i = 0; i < mMenu.size(); i++) {
                mMenu.getItem(i).setVisible(false);
                mMenu.getItem(i).setEnabled(false);
            }
        }
    }

    private void showEditMenu() {
        if (null != mMenu) {
            for (int i = 0; i < mMenu.size(); i++) {
                mMenu.getItem(i).setVisible(true);
                mMenu.getItem(i).setEnabled(true);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log.d("testsys", "system exit!");


            onUpdateview();
            hiddenEditMenu();
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
            Log.d("testsys", "Metal Mode selected.");
        } else {
            Toast.makeText(this, "请先登录！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        LayoutInflater factory = LayoutInflater.from(this);

        switch (item.getItemId()) {

            case R.id.menu_option:


                setContentView(R.layout.muisconfig);


                Button btncancel = (Button) findViewById(R.id.sysbutton2);
                btncancel.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        setContentView(R.layout.muisselect);

                    }

                });


                Spinner spnawangge = (Spinner) findViewById(R.id.configspinner_awangge);
                ArrayAdapter adpawangge = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constant.ConValue.mConfigawangge);

                adpawangge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spnawangge.setAdapter(adpawangge);
                spnawangge.setSelection(0);

                spnawangge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                Spinner spnaboxing = (Spinner) findViewById(R.id.configspinner_aboxing);
                ArrayAdapter adpaboxing = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constant.ConValue.mConfigaboxing);

                adpaboxing.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spnaboxing.setAdapter(adpaboxing);
                spnaboxing.setSelection(0);

                spnaboxing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                Spinner spnyanse = (Spinner) findViewById(R.id.configspinner_yanse);
                ArrayAdapter adpyanse = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constant.ConValue.mConfigyanse);

                adpyanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spnyanse.setAdapter(adpyanse);
                spnyanse.setSelection(0);

                spnyanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                Spinner spnbxiangdian = (Spinner) findViewById(R.id.configspinner_bxiangdian);
                ArrayAdapter adpbxiangdian = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constant.ConValue.mConfigyanse);

                adpbxiangdian.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spnbxiangdian.setAdapter(adpbxiangdian);
                spnbxiangdian.setSelection(0);

                spnbxiangdian.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                Spinner spnshiboyanse = (Spinner) findViewById(R.id.configspinner_shiboyanse);
                ArrayAdapter adpshiboyanse = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

                adpshiboyanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spnshiboyanse.setAdapter(adpshiboyanse);
                spnshiboyanse.setSelection(0);

                spnshiboyanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                Spinner spnzuoyouche = (Spinner) findViewById(R.id.configspinner_zuoyouche);
                ArrayAdapter adpzuoyouche = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constant.ConValue.mConfigZuoyouche);

                adpzuoyouche.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spnzuoyouche.setAdapter(adpzuoyouche);
                spnzuoyouche.setSelection(0);

                spnzuoyouche.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                Spinner spnchuboyanse = (Spinner) findViewById(R.id.configspinner_chuboyanse);
                ArrayAdapter adpchuboyanse = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

                adpchuboyanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spnchuboyanse.setAdapter(adpchuboyanse);
                spnchuboyanse.setSelection(0);

                spnchuboyanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                Spinner spnshuangboyanse = (Spinner) findViewById(R.id.configspinner_shuangboyanse);
                ArrayAdapter adpshuangboyanse = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

                adpshuangboyanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spnshuangboyanse.setAdapter(adpshuangboyanse);
                spnshuangboyanse.setSelection(0);

                spnshuangboyanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                Spinner spnchaosubaojing = (Spinner) findViewById(R.id.configspinner_chaosubaojing);
                ArrayAdapter adpchaosubaojing = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constant.ConValue.mConfigchaosubaojing);

                adpchaosubaojing.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spnchaosubaojing.setAdapter(adpchaosubaojing);
                spnchaosubaojing.setSelection(0);

                spnchaosubaojing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                Spinner spnchaosuyuzhi = (Spinner) findViewById(R.id.configspinner_chaosuyuzhi);
                ArrayAdapter adpchaosuyuzhi = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constant.ConValue.mConfigchaosuyuzhi);

                adpchaosuyuzhi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spnchaosuyuzhi.setAdapter(adpchaosuyuzhi);
                spnchaosuyuzhi.setSelection(0);

                spnchaosuyuzhi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                Spinner spndianliangbaojing = (Spinner) findViewById(R.id.configspinner_dianliangbaojing);
                ArrayAdapter adpdianliangbaojing = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constant.ConValue.mConfigdianliangbaojing);

                adpdianliangbaojing.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spndianliangbaojing.setAdapter(adpdianliangbaojing);
                spndianliangbaojing.setSelection(0);

                spndianliangbaojing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                Spinner spndianliangyuzhi = (Spinner) findViewById(R.id.configspinner_dianliangyuzhi);
                ArrayAdapter adpdianliangyuzhi = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constant.ConValue.mConfigdianliangyuzhi);

                adpdianliangyuzhi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spndianliangyuzhi.setAdapter(adpdianliangyuzhi);
                spndianliangyuzhi.setSelection(0);

                spndianliangyuzhi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                break;

        }

        return true;
    }


    public void OnInfoClick(View v) {
        setContentView(R.layout.muisjob);

        Button btncancel = (Button) findViewById(R.id.jobsysbutton2);
        btncancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                setContentView(R.layout.muisselect);

            }

        });


        Spinner spnxianbie = (Spinner) findViewById(R.id.jobspinner_xianbie);
        ArrayAdapter adpxianbie = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constant.ConValue.mInfoXianbie);

        adpxianbie.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnxianbie.setAdapter(adpxianbie);
        spnxianbie.setSelection(0);

        spnxianbie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spnhangbie = (Spinner) findViewById(R.id.jobspinner_hangbie);
        ArrayAdapter adphangbie = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constant.ConValue.mInfoHangbie);

        adphangbie.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnhangbie.setAdapter(adphangbie);
        spnhangbie.setSelection(0);

        spnhangbie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spngubie = (Spinner) findViewById(R.id.jobspinner_gubie);
        ArrayAdapter adpgubie = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constant.ConValue.mInfoGubie);

        adpgubie.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spngubie.setAdapter(adpgubie);
        spngubie.setSelection(0);

        spngubie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spnguixing = (Spinner) findViewById(R.id.jobspinner_guixing);
        ArrayAdapter adpguixing = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constant.ConValue.mInfoGuixing);

        adpguixing.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnguixing.setAdapter(adpguixing);
        spnguixing.setSelection(0);

        spnguixing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spnzengjian = (Spinner) findViewById(R.id.jobspinner_zengjian);
        ArrayAdapter adpzengjian = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constant.ConValue.mInfoZengjian);

        adpzengjian.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnzengjian.setAdapter(adpzengjian);
        spnzengjian.setSelection(0);

        spnzengjian.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    // 焊缝探伤模块
    public void OnWeldClick(View v) {
        if (logedin) {

            Intent intent = new Intent();
            intent.setClass(SelectActivity.this, WeldActivity.class);
            intent.putExtra("type", "Weld");
            startActivity(intent);
            Log.d("testsys", "Weld Mode selected.");
        } else {
            Toast.makeText(this, "请先登录！", Toast.LENGTH_SHORT).show();
        }
    }

    public void setUpViews() {
        mregisterSQL = new Register(this);
        mregisterSQL.select();

    }

    public void updateUser() {

        mregisterSQL.update(0, "ddd", "eee", "fff", "hhh");

        Toast.makeText(this, "Update Successed!", Toast.LENGTH_SHORT).show();
    }

}
