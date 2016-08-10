package com.sheenline.muis.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.sheenline.muis.R;
import com.sheenline.muis.common.Constant;

public class WeldSysConfig extends Fragment
	{

		private View view;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
			{
				view = inflater.inflate(R.layout.weldsysconfig, container, false);

				Spinner spnzero= (Spinner) view.findViewById(R.id.pre_sp_zero);
				ArrayAdapter adpzero= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

				adpzero.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnzero.setAdapter(adpzero);
				spnzero.setSelection(0);

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
				spnguitoukscan.setSelection(0);

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
				spnguitouchuanlie.setSelection(0);

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
				spnguidikscan.setSelection(0);

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
				spnzuojiao70.setSelection(0);

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
				spnyoujiao70.setSelection(0);

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
				spnzeroyanse.setSelection(0);

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
				spnguitoukscanyanse.setSelection(0);

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
				spnguitouchuanlieyanse.setSelection(0);

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
				spnguidikscanyanse.setSelection(0);

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
				spnzuojiao70yanse.setSelection(0);

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
				spnyuojiao70yanse.setSelection(0);

				spnyuojiao70yanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});
				return view;
			}

	}
