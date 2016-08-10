package com.sheenline.muis.main;

import com.sheenline.muis.R;
import com.sheenline.muis.common.Constant;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MetalUTParameter extends Fragment
	{

		private View view;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
			{
				view = inflater.inflate(R.layout.metalutparameter, container, false);

				Spinner spnchufa = (Spinner) view.findViewById(R.id.ut_sp_chufa);
				ArrayAdapter adpchufa= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mUtchufa);

				adpchufa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnchufa.setAdapter(adpchufa);
				spnchufa.setSelection(0);

				spnchufa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spnyasuo = (Spinner) view.findViewById(R.id.ut_sp_yasuo);
				ArrayAdapter adpyasuo= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mUtyasuo);

				adpyasuo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnyasuo.setAdapter(adpyasuo);
				spnyasuo.setSelection(0);

				spnyasuo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spntantouleixing= (Spinner) view.findViewById(R.id.ut_sp_tantouleixing);
				ArrayAdapter adptantouleixing= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mUtyasuo);

				adptantouleixing.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spntantouleixing.setAdapter(adptantouleixing);
				spntantouleixing.setSelection(0);

				spntantouleixing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});

				Spinner spntantoupinglv= (Spinner) view.findViewById(R.id.ut_sp_tantoupinglv);
				ArrayAdapter adptantoupinglv= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mUttantoupinglv);

				adptantoupinglv.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spntantoupinglv.setAdapter(adptantoupinglv);
				spntantoupinglv.setSelection(0);

				spntantoupinglv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spnlvbo= (Spinner) view.findViewById(R.id.ut_sp_lvbo);
				ArrayAdapter adplvbo= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mUtlvbo);

				adplvbo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnlvbo.setAdapter(adplvbo);
				spnlvbo.setSelection(0);

				spnlvbo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
