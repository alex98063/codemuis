package com.sheenline.muis.main;

import com.sheenline.muis.R;
import com.sheenline.muis.common.Constant;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MetalUTParameter extends Fragment
	{

		private View view;

		int metal_ut_sp_chufa,metal_ut_sp_yasuo,metal_ut_rg,metal_ut_sp_tantouleixing;
		int metal_ut_sp_lvbo,metal_ut_sp_tantoupinglv;
		String metal_ut_et_yanchi,metal_ut_et_maikuan,metal_ut_et_yuzhi,metal_ut_et_zero,metal_ut_et_zengyi;


		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
			{
				view = inflater.inflate(R.layout.metalutparameter, container, false);


				SharedPreferences share = getActivity().getSharedPreferences("metalperference", 0);

				metal_ut_sp_chufa = share.getInt("metal_ut_sp_chufa",0);
				metal_ut_et_yanchi = share.getString("metal_ut_et_yanchi","");
				metal_ut_et_maikuan = share.getString("metal_ut_et_maikuan","");
				metal_ut_sp_yasuo = share.getInt("metal_ut_sp_yasuo",0);
				metal_ut_et_yuzhi = share.getString("metal_ut_et_yuzhi","");
				metal_ut_rg = share.getInt("metal_ut_rg",0);
				metal_ut_sp_tantouleixing = share.getInt("metal_ut_sp_tantouleixing",0);
				metal_ut_sp_tantoupinglv = share.getInt("metal_ut_sp_tantoupinglv",0);
				metal_ut_sp_lvbo = share.getInt("metal_ut_sp_lvbo",0);
				metal_ut_et_zero = share.getString("metal_ut_et_zero","");
				metal_ut_et_zengyi = share.getString("metal_ut_et_zengyi","");

				EditText etmetal_ut_et_yanchi = (EditText) view.findViewById(R.id.metal_ut_et_yanchi);
				etmetal_ut_et_yanchi.setText(metal_ut_et_yanchi);

				EditText etmetal_ut_et_maikuan = (EditText) view.findViewById(R.id.metal_ut_et_maikuan);
				etmetal_ut_et_maikuan.setText(metal_ut_et_maikuan);

				EditText etmetal_ut_et_yuzhi = (EditText) view.findViewById(R.id.metal_ut_et_yuzhi);
				etmetal_ut_et_yuzhi.setText(metal_ut_et_yuzhi);

				EditText etmetal_ut_et_zero = (EditText) view.findViewById(R.id.metal_ut_et_zero);
				etmetal_ut_et_zero.setText(metal_ut_et_zero);

				EditText etmetal_ut_et_zengyi = (EditText) view.findViewById(R.id.metal_ut_et_zengyi);
				etmetal_ut_et_zengyi.setText(metal_ut_et_zengyi);

				RadioGroup rgmetal_ut_rg = (RadioGroup) view.findViewById(R.id.metal_ut_rg);
				rgmetal_ut_rg.check(metal_ut_rg);

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
				spntantouleixing.setSelection(metal_ut_sp_tantouleixing);

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
				spntantoupinglv.setSelection(metal_ut_sp_tantoupinglv);

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
				spnlvbo.setSelection(metal_ut_sp_lvbo);

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
						metal_ut_rg = rgmetal_ut_rg.getCheckedRadioButtonId();

						Spinner spnmetal_ut_sp_tantouleixing = (Spinner) view.findViewById(R.id.metal_ut_sp_tantouleixing);
						metal_ut_sp_tantouleixing = spnmetal_ut_sp_tantouleixing.getSelectedItemPosition();

						Spinner spnmetal_ut_sp_tantoupinglv = (Spinner) view.findViewById(R.id.metal_ut_sp_tantoupinglv);
						metal_ut_sp_tantoupinglv = spnmetal_ut_sp_tantoupinglv.getSelectedItemPosition();

						Spinner spnmetal_ut_sp_lvbo = (Spinner) view.findViewById(R.id.metal_ut_sp_lvbo);
						metal_ut_sp_lvbo = spnmetal_ut_sp_lvbo.getSelectedItemPosition();

						EditText etmetal_ut_et_zero = (EditText) view.findViewById(R.id.metal_ut_et_zero);
						metal_ut_et_zero = etmetal_ut_et_zero.getText().toString();

						EditText etmetal_ut_et_zengyi = (EditText) view.findViewById(R.id.metal_ut_et_zengyi);
						metal_ut_et_zengyi = etmetal_ut_et_zengyi.getText().toString();


						SharedPreferences share = getActivity().getSharedPreferences("metalperference", 0);
						SharedPreferences.Editor editor = share.edit();

						editor.putInt("metal_ut_sp_chufa",metal_ut_sp_chufa);
						editor.putString("metal_ut_et_yanchi",metal_ut_et_yanchi);
						editor.putString("metal_ut_et_maikuan",metal_ut_et_maikuan);
						editor.putInt("metal_ut_sp_yasuo",metal_ut_sp_yasuo);
						editor.putString("metal_ut_et_yuzhi",metal_ut_et_yuzhi);
						editor.putInt("metal_ut_rg",metal_ut_rg);
						editor.putInt("metal_ut_sp_tantouleixing",metal_ut_sp_tantouleixing);
						editor.putInt("metal_ut_sp_tantoupinglv",metal_ut_sp_tantoupinglv);
						editor.putInt("metal_ut_sp_lvbo",metal_ut_sp_lvbo);
						editor.putString("metal_ut_et_zero",metal_ut_et_zero);
						editor.putString("metal_ut_et_zengyi",metal_ut_et_zengyi);

						editor.commit();
						Toast.makeText(getActivity(),"超声参数保存成功！",Toast.LENGTH_SHORT).show();



					}
				});




				return view;
			}

	}
