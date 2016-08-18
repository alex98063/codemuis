package com.sheenline.muis.main;

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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.sheenline.muis.R;
import com.sheenline.muis.common.Constant;

public class WeldSysConfig extends Fragment
	{

		private View view;


		int pre_sp_zero,pre_sp_guitoukscan,pre_sp_guitouchuanlie,pre_sp_guidikscan,pre_sp_zuojiao70;
		int pre_sp_zeroyanse,pre_sp_guitoukscanyanse,pre_sp_guitouchuanlieyanse,pre_sp_guidikscanyanse;
		int pre_sp_zuojiao70yanse,pre_sp_yuojiao70yanse,pre_sp_youjiao70;



		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
			{
				view = inflater.inflate(R.layout.weldsysconfig, container, false);

				SharedPreferences share = getActivity().getSharedPreferences("weldperference", 0);
				pre_sp_zero= share.getInt("pre_sp_zero",0);
				pre_sp_guitoukscan = share.getInt("pre_sp_guitoukscan",0);
				pre_sp_guitouchuanlie = share.getInt("pre_sp_guitouchuanlie",0);
				pre_sp_guidikscan = share.getInt("pre_sp_guidikscan",0);
				pre_sp_zuojiao70 = share.getInt("pre_sp_zuojiao70",0);
				pre_sp_youjiao70 = share.getInt("pre_sp_youjiao70",0);
				pre_sp_zeroyanse = share.getInt("pre_sp_zeroyanse",0);
				pre_sp_guitoukscanyanse = share.getInt("pre_sp_guitoukscanyanse",0);
				pre_sp_guitouchuanlieyanse = share.getInt("pre_sp_guitouchuanlieyanse",0);
				pre_sp_guidikscanyanse = share.getInt("pre_sp_guidikscanyanse",0);
				pre_sp_zuojiao70yanse = share.getInt("pre_sp_zuojiao70yanse",0);
				pre_sp_yuojiao70yanse = share.getInt("pre_sp_yuojiao70yanse",0);



				Spinner spnzero= (Spinner) view.findViewById(R.id.pre_sp_zero);
				ArrayAdapter adpzero= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

				adpzero.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnzero.setAdapter(adpzero);
				spnzero.setSelection(pre_sp_zero);

				spnzero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});

				Spinner spnguitoukscan= (Spinner) view.findViewById(R.id.pre_sp_guitoukscan);
				ArrayAdapter adpguitoukscan= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

				adpguitoukscan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnguitoukscan.setAdapter(adpguitoukscan);
				spnguitoukscan.setSelection(pre_sp_guitoukscan);

				spnguitoukscan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spnguitouchuanlie= (Spinner) view.findViewById(R.id.pre_sp_guitouchuanlie);
				ArrayAdapter adpguitouchuanlie= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

				adpguitouchuanlie.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnguitouchuanlie.setAdapter(adpguitouchuanlie);
				spnguitouchuanlie.setSelection(pre_sp_guitouchuanlie);

				spnguitouchuanlie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});

				Spinner spnguidikscan= (Spinner) view.findViewById(R.id.pre_sp_guidikscan);
				ArrayAdapter adpguidikscan= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

				adpguidikscan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnguidikscan.setAdapter(adpguidikscan);
				spnguidikscan.setSelection(pre_sp_guidikscan);

				spnguidikscan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spnzuojiao70= (Spinner) view.findViewById(R.id.pre_sp_zuojiao70);
				ArrayAdapter adpzuojiao70= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

				adpzuojiao70.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnzuojiao70.setAdapter(adpzuojiao70);
				spnzuojiao70.setSelection(pre_sp_zuojiao70);

				spnzuojiao70.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spnyoujiao70= (Spinner) view.findViewById(R.id.pre_sp_youjiao70);
				ArrayAdapter adpyoujiao70= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

				adpyoujiao70.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnyoujiao70.setAdapter(adpyoujiao70);
				spnyoujiao70.setSelection(pre_sp_youjiao70);

				spnyoujiao70.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spnzeroyanse= (Spinner) view.findViewById(R.id.pre_sp_zeroyanse);
				ArrayAdapter adpzeroyanse= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

				adpzeroyanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnzeroyanse.setAdapter(adpzeroyanse);
				spnzeroyanse.setSelection(pre_sp_zeroyanse);

				spnzeroyanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});

				Spinner spnguitoukscanyanse= (Spinner) view.findViewById(R.id.pre_sp_guitoukscanyanse);
				ArrayAdapter adpguitoukscanyanse= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

				adpguitoukscanyanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnguitoukscanyanse.setAdapter(adpguitoukscanyanse);
				spnguitoukscanyanse.setSelection(pre_sp_guitoukscanyanse);

				spnguitoukscanyanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});

				Spinner spnguitouchuanlieyanse= (Spinner) view.findViewById(R.id.pre_sp_guitouchuanlieyanse);
				ArrayAdapter adpguitouchuanlieyanse= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

				adpguitouchuanlieyanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnguitouchuanlieyanse.setAdapter(adpguitouchuanlieyanse);
				spnguitouchuanlieyanse.setSelection(pre_sp_guitouchuanlieyanse);

				spnguitouchuanlieyanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});

				Spinner spnguidikscanyanse= (Spinner) view.findViewById(R.id.pre_sp_guidikscanyanse);
				ArrayAdapter adpguidikscanyanse= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

				adpguidikscanyanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnguidikscanyanse.setAdapter(adpguidikscanyanse);
				spnguidikscanyanse.setSelection(pre_sp_guidikscanyanse);

				spnguidikscanyanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spnzuojiao70yanse= (Spinner) view.findViewById(R.id.pre_sp_zuojiao70yanse);
				ArrayAdapter adpzuojiao70yanse= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

				adpzuojiao70yanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnzuojiao70yanse.setAdapter(adpzuojiao70yanse);
				spnzuojiao70yanse.setSelection(pre_sp_zuojiao70yanse);

				spnzuojiao70yanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spnyuojiao70yanse= (Spinner) view.findViewById(R.id.pre_sp_yuojiao70yanse);
				ArrayAdapter adpyuojiao70yanse= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

				adpyuojiao70yanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnyuojiao70yanse.setAdapter(adpyuojiao70yanse);
				spnyuojiao70yanse.setSelection(pre_sp_yuojiao70yanse);

				spnyuojiao70yanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Button btnweldsysbutton1 = (Button) view.findViewById(R.id.weldsysbutton1);


				btnweldsysbutton1.setOnClickListener(new View.OnClickListener() {


					@Override
					public void onClick(View v) {


						Spinner spnpre_sp_zero = (Spinner) view.findViewById(R.id.pre_sp_zero);
						pre_sp_zero = spnpre_sp_zero.getSelectedItemPosition();

						Spinner spnpre_sp_guitoukscan= (Spinner) view.findViewById(R.id.pre_sp_guitoukscan);
						pre_sp_guitoukscan = spnpre_sp_guitoukscan.getSelectedItemPosition();

						Spinner spnpre_sp_guitouchuanlie= (Spinner) view.findViewById(R.id.pre_sp_guitouchuanlie);
						pre_sp_guitouchuanlie = spnpre_sp_guitouchuanlie.getSelectedItemPosition();

						Spinner spnpre_sp_guidikscan= (Spinner) view.findViewById(R.id.pre_sp_guidikscan);
						pre_sp_guidikscan = spnpre_sp_guidikscan.getSelectedItemPosition();

						Spinner spnpre_sp_zuojiao70= (Spinner) view.findViewById(R.id.pre_sp_zuojiao70);
						pre_sp_zuojiao70 = spnpre_sp_zuojiao70.getSelectedItemPosition();

						Spinner spnpre_sp_youjiao70= (Spinner) view.findViewById(R.id.pre_sp_youjiao70);
						pre_sp_youjiao70 = spnpre_sp_youjiao70.getSelectedItemPosition();

						Spinner spnpre_sp_zeroyanse= (Spinner) view.findViewById(R.id.pre_sp_zeroyanse);
						pre_sp_zeroyanse = spnpre_sp_zeroyanse.getSelectedItemPosition();

						Spinner spnpre_sp_guitoukscanyanse= (Spinner) view.findViewById(R.id.pre_sp_guitoukscanyanse);
						pre_sp_guitoukscanyanse = spnpre_sp_guitoukscanyanse.getSelectedItemPosition();

						Spinner spnpre_sp_guitouchuanlieyanse= (Spinner) view.findViewById(R.id.pre_sp_guitouchuanlieyanse);
						pre_sp_guitouchuanlieyanse = spnpre_sp_guitouchuanlieyanse.getSelectedItemPosition();

						Spinner spnpre_sp_guidikscanyanse= (Spinner) view.findViewById(R.id.pre_sp_guidikscanyanse);
						pre_sp_guidikscanyanse = spnpre_sp_guidikscanyanse.getSelectedItemPosition();

						Spinner spnpre_sp_zuojiao70yanse= (Spinner) view.findViewById(R.id.pre_sp_zuojiao70yanse);
						pre_sp_zuojiao70yanse = spnpre_sp_zuojiao70yanse.getSelectedItemPosition();

						Spinner spnpre_sp_yuojiao70yanse= (Spinner) view.findViewById(R.id.pre_sp_yuojiao70yanse);
						pre_sp_yuojiao70yanse = spnpre_sp_yuojiao70yanse.getSelectedItemPosition();


						SharedPreferences share = getActivity().getSharedPreferences("weldperference", 0);
						SharedPreferences.Editor editor = share.edit();

						editor.putInt("pre_sp_zero",pre_sp_zero);
						editor.putInt("pre_sp_guitoukscan",pre_sp_guitoukscan);
						editor.putInt("pre_sp_guitouchuanlie",pre_sp_guitouchuanlie);
						editor.putInt("pre_sp_guidikscan",pre_sp_guidikscan);
						editor.putInt("pre_sp_zuojiao70",pre_sp_zuojiao70);
						editor.putInt("pre_sp_youjiao70",pre_sp_youjiao70);
						editor.putInt("pre_sp_zeroyanse",pre_sp_zeroyanse);
						editor.putInt("pre_sp_guitoukscanyanse",pre_sp_guitoukscanyanse);
						editor.putInt("pre_sp_guitouchuanlieyanse",pre_sp_guitouchuanlieyanse);
						editor.putInt("pre_sp_guidikscanyanse",pre_sp_guidikscanyanse);
						editor.putInt("pre_sp_zuojiao70yanse",pre_sp_zuojiao70yanse);
						editor.putInt("pre_sp_yuojiao70yanse",pre_sp_yuojiao70yanse);

						Log.d("testsys","weldsysconfig saved");
						editor.commit();

						Toast.makeText(getActivity(),"预设参数保存成功！",Toast.LENGTH_SHORT).show();



					}
				});


				return view;
			}

	}
