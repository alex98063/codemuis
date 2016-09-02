package com.sheenline.muis.main;

import com.sheenline.muis.R;
import com.sheenline.muis.common.Constant;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MetalUTParameter extends Fragment
	{

		private View view;

		int metal_ut_sp_chufa,metal_ut_sp_yasuo, metal_ut_rg_nr;

		int[] metal_ut_sp_tantouleixing = new int[9];
		int[] metal_ut_sp_tantoupinglv = new int[9];
		int[] metal_ut_sp_lvbo = new int[9];

		String metal_ut_et_yanchi,metal_ut_et_maikuan,metal_ut_et_yuzhi;
		String[] metal_ut_et_zero = new String[9];
		String[] metal_ut_et_zengyi = new String[9];
		int[] rgnr=new int[9];

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
			{
				view = inflater.inflate(R.layout.metalutparameter, container, false);

				RadioButton rb0= (RadioButton) view.findViewById(R.id.ut_rb_70pluswai);
				rgnr[0]= rb0.getId();



				RadioButton rb1= (RadioButton) view.findViewById(R.id.ut_rb_70pluszheng);
				rgnr[1]= rb1.getId();

				RadioButton rb2= (RadioButton) view.findViewById(R.id.ut_rb_70plusnei);
				rgnr[2] = rb2.getId();

				RadioButton rb3= (RadioButton) view.findViewById(R.id.ut_rb_70minuswai);
				rgnr[3] = rb3.getId();

				RadioButton rb4= (RadioButton) view.findViewById(R.id.ut_rb_70minuszheng);
				rgnr[4] = rb4.getId();

				RadioButton rb5= (RadioButton) view.findViewById(R.id.ut_rb_70minusnei);
				rgnr[5] = rb5.getId();

				RadioButton rb6= (RadioButton) view.findViewById(R.id.ut_rb_37plus);
				rgnr[6]= rb6.getId();

				RadioButton rb7= (RadioButton) view.findViewById(R.id.ut_rb_37minus);
				rgnr[7]= rb7.getId();

				RadioButton rb8= (RadioButton) view.findViewById(R.id.ut_rb_0);
				rgnr[8] = rb8.getId();



				SharedPreferences share = getActivity().getSharedPreferences("metalperference", 0);


				metal_ut_sp_chufa = share.getInt("metal_ut_sp_chufa",0);
				metal_ut_et_yanchi = share.getString("metal_ut_et_yanchi","");
				metal_ut_et_maikuan = share.getString("metal_ut_et_maikuan","");
				metal_ut_sp_yasuo = share.getInt("metal_ut_sp_yasuo",0);
				metal_ut_et_yuzhi = share.getString("metal_ut_et_yuzhi","");
				metal_ut_rg_nr = share.getInt("metal_ut_rg_nr",rgnr[0]);

				int rgindex = 0;

				for (int i=0;i<9;i++) {
					if (metal_ut_rg_nr == rgnr[i]) rgindex = i;
				}

				for (int i=0;i<9;i++) {
					metal_ut_sp_tantouleixing[i] = share.getInt("metal_ut_sp_tantouleixing"+String.valueOf(i), 0);
					metal_ut_sp_tantoupinglv[i] = share.getInt("metal_ut_sp_tantoupinglv"+String.valueOf(i), 0);
					metal_ut_sp_lvbo[i] = share.getInt("metal_ut_sp_lvbo"+String.valueOf(i), 0);
					metal_ut_et_zero[i] = share.getString("metal_ut_et_zero"+String.valueOf(i), "");
					metal_ut_et_zengyi[i] = share.getString("metal_ut_et_zengyi"+String.valueOf(i), "");
				}



				EditText etmetal_ut_et_yanchi = (EditText) view.findViewById(R.id.metal_ut_et_yanchi);
				etmetal_ut_et_yanchi.setText(metal_ut_et_yanchi);

				EditText etmetal_ut_et_maikuan = (EditText) view.findViewById(R.id.metal_ut_et_maikuan);
				etmetal_ut_et_maikuan.setText(metal_ut_et_maikuan);

				EditText etmetal_ut_et_yuzhi = (EditText) view.findViewById(R.id.metal_ut_et_yuzhi);
				etmetal_ut_et_yuzhi.setText(metal_ut_et_yuzhi);



				RadioGroup rgmetal_ut_rg = (RadioGroup) view.findViewById(R.id.metal_ut_rg);
				rgmetal_ut_rg.check(metal_ut_rg_nr);
                rgmetal_ut_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {

						int rgindex=0;
						for (int i=0;i<9;i++) {
							if (checkedId == rgnr[i]) rgindex = i;
						}

						EditText etmetal_ut_et_zero = (EditText) view.findViewById(R.id.metal_ut_et_zero);
						etmetal_ut_et_zero.setText(metal_ut_et_zero[rgindex]);

						EditText etmetal_ut_et_zengyi = (EditText) view.findViewById(R.id.metal_ut_et_zengyi);
						etmetal_ut_et_zengyi.setText(metal_ut_et_zengyi[rgindex]);

						Spinner spntantouleixing= (Spinner) view.findViewById(R.id.metal_ut_sp_tantouleixing);
						ArrayAdapter adptantouleixing= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mUtyasuo);

						adptantouleixing.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

						spntantouleixing.setAdapter(adptantouleixing);
						spntantouleixing.setSelection(metal_ut_sp_tantouleixing[rgindex]);

						spntantouleixing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
							@Override
							public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


							}

							@Override
							public void onNothingSelected(AdapterView<?> parent) {

							}
						});

						Spinner spntantoupinglv= (Spinner) view.findViewById(R.id.metal_ut_sp_tantoupinglv);
						ArrayAdapter adptantoupinglv= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mUttantoupinglv);

						adptantoupinglv.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

						spntantoupinglv.setAdapter(adptantoupinglv);
						spntantoupinglv.setSelection(metal_ut_sp_tantoupinglv[rgindex]);

						spntantoupinglv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
							@Override
							public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


							}

							@Override
							public void onNothingSelected(AdapterView<?> parent) {

							}
						});


						Spinner spnlvbo= (Spinner) view.findViewById(R.id.metal_ut_sp_lvbo);
						ArrayAdapter adplvbo= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mUtlvbo);

						adplvbo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

						spnlvbo.setAdapter(adplvbo);
						spnlvbo.setSelection(metal_ut_sp_lvbo[rgindex]);

						spnlvbo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
							@Override
							public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


							}

							@Override
							public void onNothingSelected(AdapterView<?> parent) {

							}
						});


					}
				});



				EditText etmetal_ut_et_zero = (EditText) view.findViewById(R.id.metal_ut_et_zero);
				etmetal_ut_et_zero.setText(metal_ut_et_zero[rgindex]);

				EditText etmetal_ut_et_zengyi = (EditText) view.findViewById(R.id.metal_ut_et_zengyi);
				etmetal_ut_et_zengyi.setText(metal_ut_et_zengyi[rgindex]);

				Spinner spnchufa = (Spinner) view.findViewById(R.id.metal_ut_sp_chufa);
				ArrayAdapter adpchufa= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mUtchufa);

				adpchufa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnchufa.setAdapter(adpchufa);
				spnchufa.setSelection(metal_ut_sp_chufa);

				spnchufa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spnyasuo = (Spinner) view.findViewById(R.id.metal_ut_sp_yasuo);
				ArrayAdapter adpyasuo= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mUtyasuo);

				adpyasuo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnyasuo.setAdapter(adpyasuo);
				spnyasuo.setSelection(metal_ut_sp_yasuo);

				spnyasuo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spntantouleixing= (Spinner) view.findViewById(R.id.metal_ut_sp_tantouleixing);
				ArrayAdapter adptantouleixing= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mUtyasuo);

				adptantouleixing.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spntantouleixing.setAdapter(adptantouleixing);
				spntantouleixing.setSelection(metal_ut_sp_tantouleixing[rgindex]);

				spntantouleixing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});

				Spinner spntantoupinglv= (Spinner) view.findViewById(R.id.metal_ut_sp_tantoupinglv);
				ArrayAdapter adptantoupinglv= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mUttantoupinglv);

				adptantoupinglv.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spntantoupinglv.setAdapter(adptantoupinglv);
				spntantoupinglv.setSelection(metal_ut_sp_tantoupinglv[rgindex]);

				spntantoupinglv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spnlvbo= (Spinner) view.findViewById(R.id.metal_ut_sp_lvbo);
				ArrayAdapter adplvbo= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mUtlvbo);

				adplvbo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnlvbo.setAdapter(adplvbo);
				spnlvbo.setSelection(metal_ut_sp_lvbo[rgindex]);

				spnlvbo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});

				Button btnmetal_ut_savebutton1= (Button) view.findViewById(R.id.metal_ut_savebutton1);


				btnmetal_ut_savebutton1.setOnClickListener(new View.OnClickListener() {


					@Override
					public void onClick(View v) {

						Spinner spnmetal_ut_sp_chufa = (Spinner) view.findViewById(R.id.metal_ut_sp_chufa);
						metal_ut_sp_chufa = spnmetal_ut_sp_chufa.getSelectedItemPosition();


						EditText etmetal_ut_et_yanchi = (EditText) view.findViewById(R.id.metal_ut_et_yanchi);
						metal_ut_et_yanchi = etmetal_ut_et_yanchi.getText().toString();

						EditText etmetal_ut_et_maikuan = (EditText) view.findViewById(R.id.metal_ut_et_maikuan);
						metal_ut_et_maikuan = etmetal_ut_et_maikuan.getText().toString();

						Spinner spnmetal_ut_sp_yasuo = (Spinner) view.findViewById(R.id.metal_ut_sp_yasuo);
						metal_ut_sp_yasuo = spnmetal_ut_sp_yasuo.getSelectedItemPosition();

						EditText etmetal_ut_et_yuzhi = (EditText) view.findViewById(R.id.metal_ut_et_yuzhi);
						metal_ut_et_yuzhi = etmetal_ut_et_yuzhi.getText().toString();

						RadioGroup rgmetal_ut_rg = (RadioGroup) view.findViewById(R.id.metal_ut_rg);
						metal_ut_rg_nr = rgmetal_ut_rg.getCheckedRadioButtonId();


						int rgindex = 0;

						for (int i=0;i<9;i++)
						{
							if (metal_ut_rg_nr == rgnr[i]) rgindex=i;
						}




						Spinner spnmetal_ut_sp_tantouleixing = (Spinner) view.findViewById(R.id.metal_ut_sp_tantouleixing);
						metal_ut_sp_tantouleixing[rgindex] = spnmetal_ut_sp_tantouleixing.getSelectedItemPosition();

						Spinner spnmetal_ut_sp_tantoupinglv = (Spinner) view.findViewById(R.id.metal_ut_sp_tantoupinglv);
						metal_ut_sp_tantoupinglv[rgindex] = spnmetal_ut_sp_tantoupinglv.getSelectedItemPosition();

						Spinner spnmetal_ut_sp_lvbo = (Spinner) view.findViewById(R.id.metal_ut_sp_lvbo);
						metal_ut_sp_lvbo[rgindex] = spnmetal_ut_sp_lvbo.getSelectedItemPosition();

						EditText etmetal_ut_et_zero = (EditText) view.findViewById(R.id.metal_ut_et_zero);
						metal_ut_et_zero[rgindex] = etmetal_ut_et_zero.getText().toString();

						EditText etmetal_ut_et_zengyi = (EditText) view.findViewById(R.id.metal_ut_et_zengyi);
						metal_ut_et_zengyi[rgindex] = etmetal_ut_et_zengyi.getText().toString();


						SharedPreferences share = getActivity().getSharedPreferences("metalperference", 0);
						SharedPreferences.Editor editor = share.edit();

						editor.putInt("metal_ut_sp_chufa",metal_ut_sp_chufa);
						editor.putString("metal_ut_et_yanchi",metal_ut_et_yanchi);
						editor.putString("metal_ut_et_maikuan",metal_ut_et_maikuan);
						editor.putInt("metal_ut_sp_yasuo",metal_ut_sp_yasuo);
						editor.putString("metal_ut_et_yuzhi",metal_ut_et_yuzhi);
						editor.putInt("metal_ut_rg_nr", metal_ut_rg_nr);




	editor.putInt("metal_ut_sp_tantouleixing" + String.valueOf(rgindex), metal_ut_sp_tantouleixing[rgindex]);
	editor.putInt("metal_ut_sp_tantoupinglv" + String.valueOf(rgindex), metal_ut_sp_tantoupinglv[rgindex]);
	editor.putInt("metal_ut_sp_lvbo" + String.valueOf(rgindex), metal_ut_sp_lvbo[rgindex]);
	editor.putString("metal_ut_et_zero" + String.valueOf(rgindex), metal_ut_et_zero[rgindex]);
	editor.putString("metal_ut_et_zengyi" + String.valueOf(rgindex), metal_ut_et_zengyi[rgindex]);






						editor.commit();
						Toast.makeText(getActivity(),"超声参数保存成功！",Toast.LENGTH_SHORT).show();
                        Log.d("testsys",String.valueOf(metal_ut_rg_nr));


					}
				});




				return view;
			}

	}
