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

public class MetalSysConfig extends Fragment
	{

		private View view;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
			{
				view = inflater.inflate(R.layout.metalsysconfig, container, false);


				Spinner spn70pluszheng= (Spinner) view.findViewById(R.id.pre_sp_70pluszheng);
				ArrayAdapter adp70pluszheng= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

				adp70pluszheng.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spn70pluszheng.setAdapter(adp70pluszheng);
				spn70pluszheng.setSelection(0);

				spn70pluszheng.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spn70pluswai= (Spinner) view.findViewById(R.id.pre_sp_70pluswai);
				ArrayAdapter adp70pluswai= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

				adp70pluswai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spn70pluswai.setAdapter(adp70pluswai);
				spn70pluswai.setSelection(0);

				spn70pluswai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});

				Spinner spn70plusnei= (Spinner) view.findViewById(R.id.pre_sp_70plusnei);
				ArrayAdapter adp70plusnei= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

				adp70plusnei.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spn70plusnei.setAdapter(adp70plusnei);
				spn70plusnei.setSelection(0);

				spn70plusnei.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spn70minuszheng= (Spinner) view.findViewById(R.id.pre_sp_70minuszheng);
				ArrayAdapter adp70minuszheng= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

				adp70minuszheng.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spn70minuszheng.setAdapter(adp70minuszheng);
				spn70minuszheng.setSelection(0);

				spn70minuszheng.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spn70minuswai= (Spinner) view.findViewById(R.id.pre_sp_70minuswai);
				ArrayAdapter adp70minuswai= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

				adp70minuswai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spn70minuswai.setAdapter(adp70minuswai);
				spn70minuswai.setSelection(0);

				spn70minuswai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spn70minusnei= (Spinner) view.findViewById(R.id.pre_sp_70minusnei);
				ArrayAdapter adp70minusnei= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

				adp70minusnei.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spn70minusnei.setAdapter(adp70minusnei);
				spn70minusnei.setSelection(0);

				spn70minusnei.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spnzerochubo= (Spinner) view.findViewById(R.id.pre_sp_zerochubo);
				ArrayAdapter adpzerochubo= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

				adpzerochubo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnzerochubo.setAdapter(adpzerochubo);
				spnzerochubo.setSelection(0);

				spnzerochubo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spnzeroshibo= (Spinner) view.findViewById(R.id.pre_sp_zeroshibo);
				ArrayAdapter adpzeroshibo= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

				adpzeroshibo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnzeroshibo.setAdapter(adpzeroshibo);
				spnzeroshibo.setSelection(0);

				spnzeroshibo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});



				Spinner spnzeroshuangbo= (Spinner) view.findViewById(R.id.pre_sp_zeroshuangbo);
				ArrayAdapter adpzeroshuangbo= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

				adpzeroshuangbo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnzeroshuangbo.setAdapter(adpzeroshuangbo);
				spnzeroshuangbo.setSelection(0);

				spnzeroshuangbo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spn37pluschubo= (Spinner) view.findViewById(R.id.pre_sp_37pluschubo);
				ArrayAdapter adp37pluschubo= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

				adp37pluschubo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spn37pluschubo.setAdapter(adp37pluschubo);
				spn37pluschubo.setSelection(0);

				spn37pluschubo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spn37plusshuangbo= (Spinner) view.findViewById(R.id.pre_sp_37plusshuangbo);
				ArrayAdapter adp37plusshuangbo= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

				adp37plusshuangbo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spn37plusshuangbo.setAdapter(adp37plusshuangbo);
				spn37plusshuangbo.setSelection(0);

				spn37plusshuangbo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spn37minuschubo= (Spinner) view.findViewById(R.id.pre_sp_37minuschubo);
				ArrayAdapter adp37minuschubo= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

				adp37minuschubo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spn37minuschubo.setAdapter(adp37minuschubo);
				spn37minuschubo.setSelection(0);

				spn37minuschubo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});

				Spinner spn37minusshuangbo= (Spinner) view.findViewById(R.id.pre_sp_37minusshuangbo);
				ArrayAdapter adp37minusshuangbo= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

				adp37minusshuangbo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spn37minusshuangbo.setAdapter(adp37minusshuangbo);
				spn37minusshuangbo.setSelection(0);

				spn37minusshuangbo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spn70pluszhengyanse= (Spinner) view.findViewById(R.id.pre_sp_70pluszhengyanse);
				ArrayAdapter adp70pluszhengyanse= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

				adp70pluszhengyanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spn70pluszhengyanse.setAdapter(adp70pluszhengyanse);
				spn70pluszhengyanse.setSelection(0);

				spn70pluszhengyanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spn70pluswaiyanse= (Spinner) view.findViewById(R.id.pre_sp_70pluswaiyanse);
				ArrayAdapter adp70pluswaiyanse= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

				adp70pluswaiyanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spn70pluswaiyanse.setAdapter(adp70pluswaiyanse);
				spn70pluswaiyanse.setSelection(0);

				spn70pluswaiyanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});

				Spinner spn70plusneiyanse= (Spinner) view.findViewById(R.id.pre_sp_70plusneiyanse);
				ArrayAdapter adp70plusneiyanse= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

				adp70plusneiyanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spn70plusneiyanse.setAdapter(adp70plusneiyanse);
				spn70plusneiyanse.setSelection(0);

				spn70plusneiyanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});

				Spinner spn70minuszhengyanse= (Spinner) view.findViewById(R.id.pre_sp_70minuszhengyanse);
				ArrayAdapter adp70minuszhengyanse= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

				adp70minuszhengyanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spn70minuszhengyanse.setAdapter(adp70minuszhengyanse);
				spn70minuszhengyanse.setSelection(0);

				spn70minuszhengyanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spn70minuswaiyanse= (Spinner) view.findViewById(R.id.pre_sp_70minuswaiyanse);
				ArrayAdapter adp70minuswaiyanse= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

				adp70minuswaiyanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spn70minuswaiyanse.setAdapter(adp70minuswaiyanse);
				spn70minuswaiyanse.setSelection(0);

				spn70minuswaiyanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});

				Spinner spn70minusneiyanse= (Spinner) view.findViewById(R.id.pre_sp_70minusneiyanse);
				ArrayAdapter adp70minusneiyanse= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

				adp70minusneiyanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spn70minusneiyanse.setAdapter(adp70minusneiyanse);
				spn70minusneiyanse.setSelection(0);

				spn70minusneiyanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spn37plusyanse= (Spinner) view.findViewById(R.id.pre_sp_37plusyanse);
				ArrayAdapter adp37plusyanse= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

				adp37plusyanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spn37plusyanse.setAdapter(adp37plusyanse);
				spn37plusyanse.setSelection(0);

				spn37plusyanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spn37minusyanse= (Spinner) view.findViewById(R.id.pre_sp_37minusyanse);
				ArrayAdapter adp37minusyanse= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

				adp37minusyanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spn37minusyanse.setAdapter(adp37minusyanse);
				spn37minusyanse.setSelection(0);

				spn37minusyanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spnzeroshuangboyanse= (Spinner) view.findViewById(R.id.pre_sp_zeroshuangboyanse);
				ArrayAdapter adpzeroshuangboyanse= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

				adpzeroshuangboyanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnzeroshuangboyanse.setAdapter(adpzeroshuangboyanse);
				spnzeroshuangboyanse.setSelection(0);

				spnzeroshuangboyanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
