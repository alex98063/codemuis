package com.sheenline.muis.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.Vector;

import com.sheenline.muis.R;
import com.sheenline.muis.common.Constant;
import com.sheenline.muis.common.DrawWaveViewByA;
import com.sheenline.muis.common.DrawWaveViewByAll;
import com.sheenline.muis.common.DrawWaveViewByB;
import com.sheenline.muis.common.TestJNI;
import com.sheenline.muis.common.Tools;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

// 主界面
public class MetalActivity extends FragmentActivity {

	int trgger = 0;
	int divmode = 6;
	int sampletime = 170;

	public class StartMetalThread extends Thread {

		@Override
		public void run() {

			try {

				testJNI.SetMode(Device_Modle, 2);// 设置母材模式,第二个参数无效
				sleep(100);

				int[] channel = new int[3];
				channel[0] = 0;
				channel[1] = 1;
				channel[2] = 2;

				for (int i = 0; i < 3; i++) {

					testJNI.setCrystaltype(Device_Modle, channel[i], PCorTR);// 设置单晶双晶

					sleep(100);
					testJNI.SetPulseWidth(Device_Modle, channel[i], 200);// 设置发射脉宽

					sleep(100);

					testJNI.SetDelay(Device_Modle, channel[i], 0);// 设置延迟

					sleep(100);

					switch (i) {
					case 0:
						testJNI.SetGain(Device_Modle, channel[i], 16);// 设置增益
						// testJNI.SetGain(Device_Modle, channel[i],
						// definegainarr[0]);// 设置增益
						break;
					case 1:
						testJNI.SetGain(Device_Modle, channel[i], 16);// 设置增益
						// testJNI.SetGain(Device_Modle, channel[i],
						// definegainarr[2]);// 设置增益
						break;
					case 2:
						testJNI.SetGain(Device_Modle, channel[i], 16);// 设置增益
						// testJNI.SetGain(Device_Modle, channel[i],
						// definegainarr[4]);// 设置增益
						break;

					default:
						break;
					}

				}

				channel[0] = 16;
				channel[1] = 17;

				for (int i = 0; i < 2; i++) {
					testJNI.SetPulseWidth(Device_Modle, channel[i], 100);// 设置发射脉宽

					sleep(100);

					testJNI.SetDelay(Device_Modle, channel[i], 0);

					sleep(100);

					testJNI.SetGain(Device_Modle, channel[i], 32);// 未完成

					sleep(100);
				}

				int ret = 0;
				ret = testJNI.SetFocusLaw(Device_Modle, focuslawarr, 3, 16, focuslawarr, 3, 16);
				Log.d("testsys", String.valueOf(ret));
				sleep(100);

				trgger = utpFragment.triggmode;
			

				PA_channel = 16;
				if (trgger == 0)
				 testJNI.SetTriggerMode(1, divmode);
				if (trgger==1)
					 testJNI.SetTriggerMode(0, divmode);
				
				
				testJNI.StartWork(Device_Modle, 3);

				isbootfirst = true;
				Log.d("testsys", "Metal work started!");
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}
	}

	private static TreeMap<String, float[]> treeMapfocuslaw = new TreeMap<String, float[]>(); // 数字字典
	float[] focuslawarr;
	int facuschannel = 16;

	// 读取Focuslaw文件
	private void onReadfocuslawfile(String filename) {

		File file = new File(filename);

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			Log.d("testsys", filename);

			int i = 0;
			int j = 0;

			float[] tempfloat = new float[facuschannel];

			while ((tempString = reader.readLine()) != null) {
				try {
					String[] temparr = tempString.split(",");

					tempfloat[j] = Float.parseFloat(temparr[1]);

					j++;

					if (j == facuschannel) {

						treeMapfocuslaw.put(String.valueOf(i), tempfloat);
						j = 0;
						i++;

					}
				} catch (Exception e) {
				}

			}

			focuslawarr = new float[treeMapfocuslaw.size() * facuschannel];
			int k = 0;
			for (int m = 0; m < treeMapfocuslaw.size(); m++) {
				for (int n = 0; n < facuschannel; n++) {
					focuslawarr[k] = treeMapfocuslaw.get(String.valueOf(m))[n];

					k++;
				}
			}

			reader.close();

			Log.d("testsys", "focuslaw success");
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	// 增益步长
	private static double definegainstep;

	// 闸门长度
	private static int definethlength = 100;

	// 闸门起点
	private static int definethstart = 0;

	// 闸门步长
	private static int definethsteplength = 10;

	private static int intDefineZero = 0;

	// 零点步长
	private static int intDefineZeroStep = 0;

	private static int intThresoldValue = 0; // 阈值

	// 判断是否启动完成
	private static boolean isbootfirst = false; // 判断设备是否启动

	private String strDefineChannelKey = "512"; // 通道号

	private String usetype = "Metal"; // 通道号

	private static TreeMap<String, int[]> treeMapAWaveData = new TreeMap<String, int[]>(); // 数字字典

	private static TreeMap<String, int[]> treeMapAWaveDataB = new TreeMap<String, int[]>(); // 数字字典

	static {
		// 加载动态库
		System.loadLibrary("TestJNI");
	}
	// 数据计算线程

	// 状态栏
	private ActionBar actionBar; // 声明ActionBar

	// adapters
	private ArrayAdapter<String> adpGain = null, adpChannel = null, adpThreshold = null, adpCrystalmode = null;

	private ArrayAdapter<String> adpspinnerchannelnr = null, adpspinnermode = null, adpspinnergain = null;

	private ArrayAdapter<String> adpspinnerDigtalFiter = null;

	private ArrayAdapter<String> adpspinnerpachannelnr = null, adpspinnerpacaonr = null, adpspinnerpapuls = null,
			adpspinnerpagain = null, adpspinnerpaADdelay = null;

	private ArrayAdapter<String> adpspinnerpaDigtalFiter = null, adpspinnerpaswitcher = null;

	final int ASCAN_LENGTH = 626;

	// 增益加减按钮
	private Button btnGainPlus, btnGainMinus;

	// 左侧保存按钮
	private Button btnSaveParameterLeft;

	// 右侧保存按钮
	private Button btnSaveParameterRight;

	private Button btnThresholdMoveLeft;

	private Button btnThresholdMoveRight;

	private Button btnZeroMoveLeft;

	private Button btnZeroMoveRight;

	int Threashod = 20;

	int channelnrgainminus = 0;

	int channelnrgainplus = 0;

	int crystalchannel = 0;

	int addelaychannel = 0;

	int Chennal;

	int[] defineutaddelayarr = new int[9];

	int[] defineutplusarr = new int[9];

	int Device_Modle = 2;
	int crystaltype = 0;

	int addelaytime = 0;

	int[] m_AData;

	private FragmentManager manager; // fragment管理

	final int MAX_PKT_LEN = 182 * 626 * 2;

	final int MAX_PKT_LEN_TEMP = 450720;
	int PA_channel = 0;
	int PA_slot;
	char[] Package_Data = new char[MAX_PKT_LEN];
	int PCorTR = 1;
	int choosedkey = 0;

	double sendgainminus = 0;

	double sendgainplus = 0;

	private Spinner spnGain, spnChannel, spnThreshold, spnCrystalmode;

	private Spinner spnspinnerchannelnr, spnspinnermode, spnspinnergain;
	private Spinner spnspinnerDigtalFiter;

	private Spinner spnspinnerpachannelnr, spnspinnerpacaonr, spnspinnerpapuls, spnspinnerpagain, spnspinnerpaADdelay;
	private Spinner spnspinnerpaDigtalFiter, spnspinnerpaswitcher;

	Vector<Integer> tempmapB = new Vector<Integer>();
	int tempmapBindex = 2000;

	TimerTask taskMetal = new TimerTask() {
		@Override
		public void run() {

			

			switch (leftMetalFragment.itemprobe) {
			case 6:
				strDefineChannelKey = "0";
				break;
			case 7:
				strDefineChannelKey = "256";
				break;
			case 8:
				strDefineChannelKey = "512";
				break;
			case 9:
				strDefineChannelKey = "999";
				break;
			default:
				break;
			}

//			if (strDefineChannelKey != "999") {
//
//				int testi = -1;
//
//				testi = testJNI.OpenDataStream(Package_Data);
//
//				if (testi <= 10) {
//
//					return;
//				} else {
//
//					int slot = 0;
//
//					switch (strDefineChannelKey) {
//					case "0":
//						PA_channel = 0;
//						break;
//					case "256":
//						PA_channel = 1;
//						break;
//					case "512":
//						PA_channel = 2;
//						break;
//
//					default:
//						PA_channel = 0;
//						break;
//					}
//
//					if (PA_channel < 16) // todo:PA通道号
//					{
//						slot = 0;
//					} else {
//						slot = PA_slot;
//					}
//
//					String key = strDefineChannelKey;
//					int[] channel_data = new int[12480];
//
//					channel_data = testJNI.getData(Device_Modle, Package_Data, PA_channel, slot, MAX_PKT_LEN, 0, 20);
//
//					try {
//						Thread.sleep(100);
//					} catch (Exception e) {
//
//					}
//
//					
//
//					for (int j = 0; j < 20; j++) {
//
//						int[] channel_datatemp = new int[624];
//
//						for (int k = 0; k < 624; k++) {
//							channel_datatemp[k] = channel_data[k+j*624];
//
//						}
//
//						treeMapAWaveData.put(key, channel_datatemp);
//						onDrawAWave(treeMapAWaveData);
////						Log.d("testsys", "Melta Mode data recieving...");
//
//					}
//				}
//					
//					
//					
//				
//
//				
//
//			} 
//			else 			
			{

				int testi = -1;

				testi = testJNI.OpenDataStream(Package_Data);

				if (testi <= 10) {

					return;
				} else {

					int slot = 0;

					switch (strDefineChannelKey) {
					case "0":
						PA_channel = 0;
						break;
					case "256":
						PA_channel = 1;
						break;
					case "512":
						PA_channel = 2;
						break;

					default:
						PA_channel = 0;
						break;
					}

					if (PA_channel < 16) // todo:PA通道号
					{
						slot = 0;
					} else {
						slot = PA_slot;
					}

					String key = strDefineChannelKey;

					for (int i = 0; i < 3; i++) {

						PA_channel = i;

					

						int[] channel_data_more = new int[12480];

						channel_data_more = testJNI.getData(Device_Modle, Package_Data, PA_channel, slot, MAX_PKT_LEN,
								0, 20);

						try {
							Thread.sleep(100);
						} catch (Exception e) {

						}

						switch (i) {
						case 0:
							key = "0";
							break;
						case 1:
							key = "256";
							break;
						case 2:
							key = "512";
							break;
						default:
							break;
						}


						for (int j = 0; j < 20; j++) {

							int[] channel_datatemp = new int[624];

							for (int k = 0; k < 624; k++) {
								channel_datatemp[k] = channel_data_more[k+j*624];

							}

							treeMapAWaveData.put(key, channel_datatemp);

							int[] tempmax = Tools.getMax(channel_datatemp, 100);

							
							
							tempmapB.add(tempmax[0]);
							tempmapB.add((int) Math.round((tempmax[1])*295.5 / 624));
						//Log.d("testsys", String.valueOf(tempmax[1]*295.5 / 624));
							
							
							if (tempmapB.size() >= tempmapBindex) {
								tempmapB.remove(0);
								tempmapB.remove(1);
							}
							
							
							Object[] obj = tempmapB.toArray();
							int[] tempbdata = new int[tempmapB.size()];
							for (int k = 0; k < obj.length; k++) {
								tempbdata[k] = (int) obj[k];
							}

							treeMapAWaveDataB.put(key, tempbdata);

							onDrawAWave(treeMapAWaveData);
							onDrawBWave(treeMapAWaveDataB);
						}
						
						
						
						
					}

				

				}

				
			}

		}
	};

	TestJNI testJNI = new TestJNI();

	Timer timer = new Timer();

	private FragmentTransaction transaction;

	private int slot_number = 3;

	public final void onDrawBWave(TreeMap<String, int[]> aTreeMap) {
		if (isbootfirst == true) {
			try {
				if (aTreeMap != null) {

					View tabBview = MetalActivity.this.findViewById(R.id.main_common_right_down);
					DrawWaveViewByB mBTypeView = (DrawWaveViewByB) tabBview.findViewById(R.id.area_btype_view);
					mBTypeView.setinfo(new String[] { "0", "100", "200", "300", "400", "500", "600", "700" }, null, 1, //
							new String[] { "0", "50", "100" }, // Y轴刻度
							aTreeMap, // 数据
							"", strDefineChannelKey, definethstart, definethlength, intDefineZero, intThresoldValue);

				}

			} catch (Exception e) {
			}
		}
	}

	public final void onDrawAllWave() {
		if (isbootfirst == true) {
			View tabAllview = MetalActivity.this.findViewById(R.id.main_wave);

			tabAllview.findViewById(R.id.area_alltype_view);

		}
	}

	public final void onDrawAllWaveAxs() {

		View tabview = MetalActivity.this.findViewById(R.id.main_common_right_up);

		DrawWaveViewByAll mTypeView = (DrawWaveViewByAll) tabview.findViewById(R.id.area_alltype_view);

		mTypeView.setinfoaxs(new String[] { "0", "100", "200", "300", "400", "500", "600", "700" },
				new int[] { 0, 100, 200, 300, 400, 500, 600, 700 }, 1, new String[] { "0", "50", "100" },
				treeMapAWaveData, strDefineChannelKey);

	}

	public final void onDrawAWaveAxs() {

		View tabview = MetalActivity.this.findViewById(R.id.main_common_right_up);

		DrawWaveViewByA mTypeView = (DrawWaveViewByA) tabview.findViewById(R.id.area_atype_view);

		mTypeView.setinfoaxs(new String[] { "0", "100", "200", "300", "400", "500", "600", "700" },
				new int[] { 0, 100, 200, 300, 400, 500, 600, 700 }, 1, new String[] { "0", "50", "100" });

	}

	public final void onDrawBWaveAxs() {

		View tabview = MetalActivity.this.findViewById(R.id.main_common_right_down);

		DrawWaveViewByB mTypeView = (DrawWaveViewByB) tabview.findViewById(R.id.area_btype_view);

		mTypeView.setinfoaxs(new String[] { "0", "100", "200", "300", "400", "500", "600", "700" },
				new int[] { 0, 100, 200, 300, 400, 500, 600, 700 }, 1, new String[] { "0", "50", "100" });

	}

	// 绘制A显
	public final void onDrawAWave(TreeMap<String, int[]> aTreeMap) {

		if (isbootfirst == true) {
			View tabAview = MetalActivity.this.findViewById(R.id.main_common_right_up);

			DrawWaveViewByA mATypeView = (DrawWaveViewByA) tabAview.findViewById(R.id.area_atype_view);

			try {
				if (aTreeMap != null) {

					switch (strDefineChannelKey) {
					case "512":
						mATypeView.setinfo(new String[] { "0", "100", "200", "300", "400", "500", "600", "700" },
								new int[] { 100, 212, 200, 423, 300, 634 }, 624 / 295.5,
								new String[] { "0", "50", "100" }, // Y轴刻度
								aTreeMap, // 数据
								"", strDefineChannelKey, definethstart, definethlength, intDefineZero,
								intThresoldValue);

						break;
					case "0":
					case "256":
						mATypeView.setinfo(new String[] { "0", "100", "200", "300", "400", "500", "600", "700" },
								new int[] { 100, 212, 200, 423, 300, 634 }, 624 / 324.5,
								new String[] { "0", "50", "100" }, // Y轴刻度
								aTreeMap, // 数据
								"", strDefineChannelKey, definethstart, definethlength, intDefineZero,
								intThresoldValue);
						break;
					default:

						mATypeView.setinfo(new String[] { "0", "100", "200", "300", "400", "500", "600", "700" }, null,
								1, //
								new String[] { "0", "50", "100" }, // Y轴刻度
								aTreeMap, // 数据
								"", strDefineChannelKey, definethstart, definethlength, intDefineZero,
								intThresoldValue);

						break;
					}

				}

			} catch (Exception e) {

			}

		}

	}

	double[] definegainarr = new double[9];

	// 初始化滑动界面
	public void onIniSlideMenu() {

		SharedPreferences share = this.getSharedPreferences("perference", 0);

		String thstart = share.getString("thstart", "200");
		String thleng = share.getString("thleng", "50");
		String thstep = share.getString("thstep", "10");
		String zerostep = share.getString("zerostep", "0");

		int thresholdnr = share.getInt("thresholdnr", 0);
		int crystalnr = share.getInt("crystalnr", 0);
		int gainstepnr = share.getInt("gainstepnr", 0);
		int channelsnr = share.getInt("channelsnr", 0);

		for (int i = 0; i < definegainarr.length; i++) {

			definegainarr[i] = Double.valueOf(share.getString("definegain" + String.valueOf(i), "30.0"));

		}

		TextView tvgain = (TextView) findViewById(R.id.tv_mmct_gain);

		if (channelsnr == 0) {
			tvgain.setText("");
		} else {
			tvgain.setText(String.valueOf(definegainarr[channelsnr - 1]));
		}

		for (int i = 0; i < defineutplusarr.length; i++) {

			defineutplusarr[i] = Integer.valueOf(share.getString("utplus" + String.valueOf(i), "80"));

		}

		EditText edutplus = (EditText) findViewById(R.id.ut_ed_puls);

		if (channelsnr == 0) {
			edutplus.setText("");
		} else {
			edutplus.setText(String.valueOf(defineutplusarr[channelsnr - 1]));
		}

		for (int i = 0; i < defineutaddelayarr.length; i++) {

			defineutaddelayarr[i] = Integer.valueOf(share.getString("addelay" + String.valueOf(i), "10"));

		}

		EditText edutaddelay = (EditText) findViewById(R.id.ut_ed_ADdelay);

		if (channelsnr == 0) {
			edutaddelay.setText("");
		} else {
			edutaddelay.setText(String.valueOf(defineutaddelayarr[channelsnr - 1]));
		}

		EditText edthstart = (EditText) findViewById(R.id.et_th_start);
		edthstart.setText(thstart);
		definethstart = Integer.valueOf(thstart);

		EditText edthleng = (EditText) findViewById(R.id.et_th_length);
		edthleng.setText(thleng);
		definethlength = Integer.valueOf(thleng);

		EditText edthstep = (EditText) findViewById(R.id.et_th_steplength);
		edthstep.setText(thstep);
		definethsteplength = Integer.valueOf(thstep);

		EditText edzerostep = (EditText) findViewById(R.id.et_zero_steplength);
		edzerostep.setText(zerostep);
		intDefineZeroStep = Integer.valueOf(zerostep);

		spnGain = (Spinner) findViewById(R.id.spn_mmcb_gain);

		adpGain = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				Constant.ConValue.mMetalCommonGainStep);

		adpGain.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spnGain.setAdapter(adpGain);
		spnGain.setSelection(gainstepnr);

		spnGain.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

				onGainStepSelected(id);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		spnChannel = (Spinner) findViewById(R.id.spn_mmcb_channels);

		adpChannel = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				Constant.ConValue.mMetalCommonChannels);

		adpChannel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spnChannel.setAdapter(adpChannel);
		spnChannel.setSelection(channelsnr);

		spnChannel.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

				onChannelsSelected(id);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		spnThreshold = (Spinner) findViewById(R.id.spn_mmcb_threshold2);

		adpThreshold = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				Constant.ConValue.mMetalCommonThreshold);

		adpThreshold.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spnThreshold.setAdapter(adpThreshold);
		spnThreshold.setSelection(thresholdnr);

		spnThreshold.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

				onThresholdSelected(id);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		spnCrystalmode = (Spinner) findViewById(R.id.spn_mmcb_crystalmode);

		adpCrystalmode = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				Constant.ConValue.mMetalCommonCrystalmode);

		adpCrystalmode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnCrystalmode.setAdapter(adpCrystalmode);
		spnCrystalmode.setSelection(crystalnr);

		spnCrystalmode.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

				onCrystalmodeselected(id);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		btnGainPlus = (Button) findViewById(R.id.button_content_1);
		btnGainPlus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				onGainPlusClick();
			}
		});

		btnGainMinus = (Button) findViewById(R.id.button_content_2);
		btnGainMinus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				onGainMinusClick();

			}
		});

		btnSaveParameterRight = (Button) findViewById(R.id.bt_so_save_right);
		btnSaveParameterRight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				onSlideMenubtnSaveClick();

			}
		});

		btnThresholdMoveLeft = (Button) findViewById(R.id.bt_th_moveleft);
		btnThresholdMoveLeft.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (definethstart - definethsteplength >= 0) {
					definethstart = definethstart - definethsteplength;
				}

				EditText edthstart = (EditText) findViewById(R.id.et_th_start);

				edthstart.setText(String.valueOf(definethstart));

			}
		});

		btnThresholdMoveRight = (Button) findViewById(R.id.bt_th_moveright);
		btnThresholdMoveRight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (definethstart + definethsteplength <= 600) {
					definethstart = definethstart + definethsteplength;
				}

				EditText edthstart = (EditText) findViewById(R.id.et_th_start);

				edthstart.setText(String.valueOf(definethstart));

			}
		});

		btnZeroMoveLeft = (Button) findViewById(R.id.bt_zero_moveleft);
		btnZeroMoveLeft.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (intDefineZero - intDefineZeroStep >= -600) {
					intDefineZero = intDefineZero - intDefineZeroStep;

				}

			}
		});

		btnZeroMoveRight = (Button) findViewById(R.id.bt_zero_moveright);
		btnZeroMoveRight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (intDefineZero + intDefineZeroStep <= 0) {
					intDefineZero = intDefineZero + intDefineZeroStep;
				}

			}
		});

	}

	// 更换通道
	protected void onCrystalmodeselected(long id) {
		if ((int) id == 0) {
			crystaltype = 0;
		}

		if ((int) id == 1) {
			crystaltype = 1;
		}
		int i = 0;
		switch (strDefineChannelKey) {
		case "0":
			i = 0;
			break;

		case "256":
			i = 1;
			break;

		case "512":
			i = 2;
			break;

		case "999":

			return;

		default:
			break;
		}

		crystalchannel = i;

		new Thread() {
			@Override
			public synchronized void run() {

				testJNI.setCrystaltype(Device_Modle, crystalchannel, crystaltype);

			}
		}.start();

	}

	protected void onAddelay(int channelnr, int value) {
		addelaytime = value;

		addelaychannel = channelnr;

		new Thread() {
			@Override
			public synchronized void run() {

				testJNI.SetDelay(Device_Modle, addelaychannel, addelaytime);

			}
		}.start();

	}

	int pulswidth = 0;
	int pulswidthchannel = 0;

	protected void onPulswidth(int channelnr, int value) {
		pulswidth = value;

		pulswidthchannel = channelnr;

		new Thread() {
			@Override
			public synchronized void run() {

				testJNI.SetPulseWidth(Device_Modle, pulswidthchannel, pulswidth);

			}
		}.start();

	}

	public void onChannelsSelected(long id) {
		LinearLayout llayout = (LinearLayout) findViewById(R.id.main_common_right_down);
		TextView tvchannels = (TextView) MetalActivity.this.findViewById(R.id.tv_mmct_gain);
		byte[] idbyte = new byte[2];
		switch ((int) id) {

		case 0:// 999
			strDefineChannelKey = "999";
			tvchannels.setText("");
			llayout.setVisibility(View.VISIBLE);
			break;
		case 1:// 0

			strDefineChannelKey = "0";

			llayout.setVisibility(View.GONE);
			break;
		case 2:// 256

			strDefineChannelKey = "256";

			llayout.setVisibility(View.GONE);
			break;
		case 3:/// 512

			strDefineChannelKey = "512";

			llayout.setVisibility(View.GONE);
			break;
		case 4:// 4097
			idbyte[0] = (byte) 1;
			idbyte[1] = (byte) 16;
			strDefineChannelKey = String.valueOf(Tools.byteToShort(idbyte));

			llayout.setVisibility(View.GONE);
			break;
		case 5:// 4098
			idbyte[0] = (byte) 2;
			idbyte[1] = (byte) 16;
			strDefineChannelKey = String.valueOf(Tools.byteToShort(idbyte));

			llayout.setVisibility(View.GONE);
			break;
		case 6:// 4099
			idbyte[0] = (byte) 3;
			idbyte[1] = (byte) 16;
			strDefineChannelKey = String.valueOf(Tools.byteToShort(idbyte));

			llayout.setVisibility(View.GONE);
			break;
		case 7:// 4353
			idbyte[0] = (byte) 1;
			idbyte[1] = (byte) 17;
			strDefineChannelKey = String.valueOf(Tools.byteToShort(idbyte));

			llayout.setVisibility(View.GONE);
			break;
		case 8:// 4354
			idbyte[0] = (byte) 2;
			idbyte[1] = (byte) 17;
			strDefineChannelKey = String.valueOf(Tools.byteToShort(idbyte));

			llayout.setVisibility(View.GONE);
			break;
		case 9:// 4355
			idbyte[0] = (byte) 3;
			idbyte[1] = (byte) 17;
			strDefineChannelKey = String.valueOf(Tools.byteToShort(idbyte));

			llayout.setVisibility(View.GONE);
			break;

		default:
			strDefineChannelKey = "999";
			llayout.setVisibility(View.VISIBLE);

			break;

		}

		if ((int) id == 0) {
		} else {

			tvchannels.setText(String.valueOf(definegainarr[(int) id - 1]));
		}

		Log.d("testsys", strDefineChannelKey);

	}

	MainTop topFragment;
	MetalLeft leftMetalFragment;
	MainWave waveFragment;
	DrawBWaveView bFragmentRight;
	DrawAllWaveView allFragmentRight;
	DrawAWaveView aFragmentRight;
	MetalUTParameter utpFragment;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		onSetFullScreen();
		actionBar = getActionBar(); // 得<span></span>到ActionBar
		actionBar.hide();

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		usetype = bundle.getString("type");

		Toast.makeText(this, "母材模式", Toast.LENGTH_SHORT).show();

		File dir = Environment.getExternalStorageDirectory();
		String path = dir.toString() + "/31AnglesSeclaw.csv";
		Log.d("testsys", path);
		onReadfocuslawfile(path);

		setContentView(R.layout.main);

		if (savedInstanceState == null) {
			topFragment = new MainTop();
			manager = getSupportFragmentManager();
			transaction = manager.beginTransaction();
			transaction.replace(R.id.main_top, topFragment, "topFragment");
			transaction.commit();
		}

		if (savedInstanceState == null) {
			leftMetalFragment = new MetalLeft();
			manager = getSupportFragmentManager();
			transaction = manager.beginTransaction();
			transaction.replace(R.id.main_left, leftMetalFragment, "leftMetalFragment");
			transaction.commit();

		}

		if (savedInstanceState == null) {

			TabHost m = (TabHost) findViewById(R.id.tabhost);
			m.setup();

			m.addTab(m.newTabSpec("tab1").setIndicator("波形显示").setContent(R.id.main_wave));
			m.addTab(m.newTabSpec("tab2").setIndicator("超声参数").setContent(R.id.main_utparameter));
			m.addTab(m.newTabSpec("tab3").setIndicator("预设参数").setContent(R.id.main_sysconfig));
			m.addTab(m.newTabSpec("tab4").setIndicator("存储参数").setContent(R.id.main_savedata));

			TabWidget tabWidget = m.getTabWidget();
			for (int i = 0; i < tabWidget.getChildCount(); i++) {

				TextView tv = (TextView) tabWidget.getChildAt(i).findViewById(android.R.id.title);
				tv.setTextSize(20);

			}

		}

		if (savedInstanceState == null) {
			waveFragment = new MainWave();

			manager = getSupportFragmentManager();

			transaction = manager.beginTransaction();
			transaction.replace(R.id.main_wave, waveFragment, "waveFragment");

			transaction.commit();

			aFragmentRight = new DrawAWaveView();
			FragmentManager fma = getSupportFragmentManager();
			FragmentTransaction txa = fma.beginTransaction();
			txa.add(R.id.main_common_right_up, aFragmentRight, "AFragmentRight");
			txa.commit();

			bFragmentRight = new DrawBWaveView();
			FragmentManager fmb = getSupportFragmentManager();
			FragmentTransaction txb = fmb.beginTransaction();
			txb.add(R.id.main_common_right_down, bFragmentRight, "BFragmentRight");
			txb.commit();

			// allFragmentRight = new DrawAllWaveView();
			// FragmentManager fmall = getSupportFragmentManager();
			// FragmentTransaction txall = fmall.beginTransaction();
			// txall.add(R.id.main_common_right_up, allFragmentRight,
			// "AllFragmentRight");
			// txall.commit();

			utpFragment = new MetalUTParameter();

			manager = getSupportFragmentManager();

			transaction = manager.beginTransaction();
			transaction.replace(R.id.main_utparameter, utpFragment, "utpFragment");
			transaction.commit();

			MetalSysConfig scFragment = new MetalSysConfig();

			manager = getSupportFragmentManager();

			transaction = manager.beginTransaction();
			transaction.replace(R.id.main_sysconfig, scFragment, "scFragment");

			transaction.commit();

			MainSaveData sdFragment = new MainSaveData();

			manager = getSupportFragmentManager();

			transaction = manager.beginTransaction();
			transaction.replace(R.id.main_savedata, sdFragment, "sdFragment");

			transaction.commit();

		}

		String ip = "192.168.1.10";
		int port = 6869;

		testJNI.InitXLTLib();

		Log.d("testsys", "TCP/IP connect...!");

		if (testJNI.OpenDevice(ip, port) != 1) {
			Log.d("testsys", "connect failed!");
			return;
		} else {
			Log.d("testsys", "connect successful!");
		}

		//
		// //
		// // onIniSlideMenu();
		// //
		//
		PCorTR = 1;
		Device_Modle = 1;
		slot_number = 3;
		for (int i=0;i<2000;i++)
		{
			tempmapB.add(0);
		}
		new StartMetalThread().start();
		timer.schedule(taskMetal, sampletime, 310);
		// timer.schedule(taskDraw, 710, 900);

		Toast.makeText(getApplicationContext(), "连接成功", Toast.LENGTH_SHORT).show();
		Log.d("testsys", "system start successful!");
	}

	// 退出
	@Override
	protected void onDestroy() {

		super.onDestroy();

	}

	// 减少增益

	public void onGainMinusClick() {
		TextView tvchannels = (TextView) MetalActivity.this.findViewById(R.id.tv_mmct_gain);

		int i = 0;
		switch (strDefineChannelKey) {
		case "0":

			i = 0;

			break;

		case "256":
			i = 1;

			break;

		case "512":
			i = 2;

			break;

		case "999":
			tvchannels.setText("");
			return;

		default:
			break;
		}

		if (definegainarr[i] - definegainstep <= 0) {
			return;
		}

		definegainarr[i] = definegainarr[i] - definegainstep;
		tvchannels.setText(String.valueOf(definegainarr[i]));
		sendgainminus = definegainarr[i];
		channelnrgainminus = i;

		new Thread() {
			@Override
			public synchronized void run() {

				testJNI.SetGain(Device_Modle, channelnrgainminus, sendgainminus);
				Log.d("testsys", String.valueOf(sendgainminus));
			}
		}.start();

	}

	public void onGainPlusClick() {

		TextView tvchannels = (TextView) MetalActivity.this.findViewById(R.id.tv_mmct_gain);

		int i = 0;
		switch (strDefineChannelKey) {

		case "0":
			i = 0;
			break;

		case "256":
			i = 1;
			break;

		case "512":
			i = 2;
			break;

		case "999":
			tvchannels.setText("");
			return;

		default:
			break;
		}

		if (definegainarr[i] + definegainstep >= 80) {
			return;
		}
		definegainarr[i] = definegainarr[i] + definegainstep;

		tvchannels.setText(String.valueOf(definegainarr[i]));

		sendgainplus = definegainarr[i];
		channelnrgainplus = i;

		new Thread() {
			@Override
			public synchronized void run() {

				testJNI.SetGain(Device_Modle, channelnrgainplus, sendgainplus);
				Log.d("testsys", String.valueOf(sendgainplus));
			}
		}.start();

	}

	// 改变增益步长

	public void onGainStepSelected(long id) {

		switch ((int) id) {
		case 0:
			definegainstep = 6;

			break;

		case 1:
			definegainstep = 1;
			break;

		case 2:
			definegainstep = 0.1;
			break;

		default:
			break;
		}

	}

	// 返回物理键
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			try {
				// int sw = testJNI.StopWork();
				// int cd = testJNI.CloseDevice();
				// Log.d("testsys", usetype + " Mode exit!" + String.valueOf(sw)
				// +" " + String.valueOf(cd));

			} catch (Exception e) {
			} finally {

				Log.d("testsys", "system exit!");
				System.exit(0);

				return true;
			}

		}
		return false;
	}

	// 保存设置参数
	public void onSlideMenubtnSaveClick() {

		onSaveParameter();

		Toast.makeText(getApplicationContext(), "设置保存", Toast.LENGTH_SHORT).show();

	}

	// 改变阈值

	public void onThresholdSelected(long id) {

		intThresoldValue = (int) id * 10;

	}

	// 保存设置参数
	private void onSaveParameter() {

		TextView tvgainnr = (TextView) MetalActivity.this.findViewById(R.id.tv_mmct_gain);
		String gainnr = tvgainnr.getText().toString();

		EditText edpulsnr = (EditText) MetalActivity.this.findViewById(R.id.ut_ed_puls);
		String pulsnr = edpulsnr.getText().toString();

		EditText edADdelaynr = (EditText) MetalActivity.this.findViewById(R.id.ut_ed_ADdelay);
		String ADdelaynr = edADdelaynr.getText().toString();

		EditText edthstart = (EditText) MetalActivity.this.findViewById(R.id.et_th_start);
		String thstart = edthstart.getText().toString();
		definethstart = Integer.valueOf(thstart);

		EditText edthleng = (EditText) MetalActivity.this.findViewById(R.id.et_th_length);
		String thleng = edthleng.getText().toString();
		definethlength = Integer.valueOf(thleng);

		EditText edthstep = (EditText) MetalActivity.this.findViewById(R.id.et_th_steplength);
		String thstep = edthstep.getText().toString();
		definethsteplength = Integer.valueOf(thstep);

		EditText edzerostep = (EditText) MetalActivity.this.findViewById(R.id.et_zero_steplength);
		String zerostep = edzerostep.getText().toString();
		intDefineZeroStep = Integer.valueOf(zerostep);

		Spinner spnthreshold = (Spinner) MetalActivity.this.findViewById(R.id.spn_mmcb_threshold2);
		int thresholdnr = spnthreshold.getSelectedItemPosition();

		Spinner spnCrystalmode = (Spinner) MetalActivity.this.findViewById(R.id.spn_mmcb_crystalmode);
		int crystalnr = spnCrystalmode.getSelectedItemPosition();

		Spinner spngainstep = (Spinner) MetalActivity.this.findViewById(R.id.spn_mmcb_gain);
		int gainstepnr = spngainstep.getSelectedItemPosition();

		Spinner spnchannels = (Spinner) MetalActivity.this.findViewById(R.id.spn_mmcb_channels);
		int channelsnr = spnchannels.getSelectedItemPosition();

		// 数据保存
		SharedPreferences share = MetalActivity.this.getSharedPreferences("perference", 0);
		Editor editor = share.edit();

		editor.putString("thstart", thstart);
		definethstart = Integer.valueOf(thstart);
		editor.putString("thleng", thleng);
		definethlength = Integer.valueOf(thleng);
		editor.putString("thstep", thstep);
		definethsteplength = Integer.valueOf(thstep);

		editor.putString("zerostep", zerostep);

		editor.putInt("thresholdnr", thresholdnr);
		editor.putInt("crystalnr", crystalnr);

		editor.putInt("gainstepnr ", gainstepnr);
		editor.putInt("channelsnr", channelsnr);

		if (channelsnr > 0) {
			int i = channelsnr - 1;
			definegainarr[i] = Double.valueOf(gainnr);
			defineutplusarr[i] = Integer.valueOf(pulsnr);
			defineutaddelayarr[i] = Integer.valueOf(ADdelaynr);
			editor.putString("definegain" + String.valueOf(i), String.valueOf(definegainarr[i]));
			editor.putString("utplus" + String.valueOf(i), String.valueOf(defineutplusarr[i]));
			editor.putString("addelay" + String.valueOf(i), String.valueOf(defineutaddelayarr[i]));

		}
		editor.commit();// 提交刷新数据

		if (channelsnr > 0) {
			onAddelay(channelsnr, Integer.valueOf(ADdelaynr));
			onPulswidth(channelsnr, Integer.valueOf(pulsnr));
		}

	}

	// 设置全屏显示
	private void onSetFullScreen() {
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

}
