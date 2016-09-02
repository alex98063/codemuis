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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.sheenline.muis.R;
import com.sheenline.muis.common.Constant;

public class WeldUTParameter extends Fragment
	{

		private View view;
		int weld_ut_sp_chufa,weld_ut_sp_yasuo, weld_ut_rg_nr;
		String weld_ut_et_yanchi,weld_ut_et_maikuan,weld_ut_et_yuzhi;


		int[] weld_ut_sp_tantouleixing = new int[6];
		int[] weld_ut_sp_tantoupinglv = new int[6];
		int[] weld_ut_sp_lvbo = new int[6];
		String[] weld_ut_et_tantouzero = new String[6];
		String[] weld_ut_et_zengyidb = new String[6];
		int[] rgnr=new int[6];

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
			{
				view = inflater.inflate(R.layout.weldutparameter, container, false);

				RadioButton rb0= (RadioButton) view.findViewById(R.id.ut_rb_70zerodu);
				rgnr[0]= rb0.getId();

				RadioButton rb1= (RadioButton) view.findViewById(R.id.ut_rb_guitoukscan);
				rgnr[1]= rb1.getId();

				RadioButton rb2= (RadioButton) view.findViewById(R.id.ut_rb_guiyaochuanlie);
				rgnr[2] = rb2.getId();

				RadioButton rb3= (RadioButton) view.findViewById(R.id.ut_rb_guidikscan);
				rgnr[3] = rb3.getId();

				RadioButton rb4= (RadioButton) view.findViewById(R.id.ut_rb_70zuojiao);
				rgnr[4] = rb4.getId();

				RadioButton rb5= (RadioButton) view.findViewById(R.id.ut_rb_70youjiao);
				rgnr[5] = rb5.getId();




				SharedPreferences share = getActivity().getSharedPreferences("weldperference", 0);
				weld_ut_sp_chufa =  share.getInt("weld_ut_sp_chufa",0);
				weld_ut_sp_yasuo = share.getInt("weld_ut_sp_yasuo",0);
				weld_ut_et_yanchi = share.getString("weld_ut_et_yanchi","");
				weld_ut_et_maikuan = share.getString("weld_ut_et_maikuan","");
				weld_ut_et_yuzhi = share.getString("weld_ut_et_yuzhi","");
				weld_ut_rg_nr = share.getInt("weld_ut_rg_nr",rgnr[0]);


				int rgindex = 0;

				for (int i=0;i<6;i++) {
					if (weld_ut_rg_nr == rgnr[i]) rgindex = i;
				}

				for (int i=0;i<6;i++) {
					weld_ut_sp_tantouleixing[i] = share.getInt("weld_ut_sp_tantouleixing"+String.valueOf(i), 0);
					weld_ut_sp_tantoupinglv[i] = share.getInt("weld_ut_sp_tantoupinglv"+String.valueOf(i), 0);
					weld_ut_sp_lvbo[i] = share.getInt("weld_ut_sp_lvbo"+String.valueOf(i), 0);
					weld_ut_et_tantouzero[i] = share.getString("weld_ut_et_tantouzero"+String.valueOf(i), "");
					weld_ut_et_zengyidb[i] = share.getString("weld_ut_et_zengyidb"+String.valueOf(i), "");
				}





				EditText etweld_ut_et_yanchi = (EditText) view.findViewById(R.id.weld_ut_et_yanchi);
				etweld_ut_et_yanchi.setText(weld_ut_et_yanchi);

				EditText etweld_ut_et_maikuan = (EditText) view.findViewById(R.id.weld_ut_et_maikuan);
				etweld_ut_et_maikuan.setText(weld_ut_et_maikuan);

				EditText etweld_ut_et_yuzhi = (EditText) view.findViewById(R.id.weld_ut_et_yuzhi);
				etweld_ut_et_yuzhi.setText(weld_ut_et_yuzhi);

				EditText etweld_ut_et_tantouzero = (EditText) view.findViewById(R.id.weld_ut_et_tantouzero);
				etweld_ut_et_tantouzero.setText(weld_ut_et_tantouzero[rgindex]);

				EditText etweld_ut_et_zengyidb = (EditText) view.findViewById(R.id.weld_ut_et_zengyidb);
				etweld_ut_et_zengyidb.setText(weld_ut_et_zengyidb[rgindex]);


				RadioGroup rgweld_ut_rg = (RadioGroup) view.findViewById(R.id.weld_ut_rg);
				rgweld_ut_rg.check(weld_ut_rg_nr);
                rgweld_ut_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {

						int rgindex=0;
						for (int i=0;i<6;i++) {
							if (checkedId == rgnr[i]) rgindex = i;
						}

						EditText etweld_ut_et_tantouzero = (EditText) view.findViewById(R.id.weld_ut_et_tantouzero);
						etweld_ut_et_tantouzero.setText(weld_ut_et_tantouzero[rgindex]);

						EditText etweld_ut_et_zengyidb = (EditText) view.findViewById(R.id.weld_ut_et_zengyidb);
						etweld_ut_et_zengyidb.setText(weld_ut_et_zengyidb[rgindex]);

						Spinner spntantouleixing= (Spinner) view.findViewById(R.id.weld_ut_sp_tantouleixing);
						ArrayAdapter adptantouleixing= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mUtyasuo);

						adptantouleixing.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

						spntantouleixing.setAdapter(adptantouleixing);
						spntantouleixing.setSelection(weld_ut_sp_tantouleixing[rgindex]);

						spntantouleixing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
							@Override
							public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


							}

							@Override
							public void onNothingSelected(AdapterView<?> parent) {

							}
						});

						Spinner spntantoupinglv= (Spinner) view.findViewById(R.id.weld_ut_sp_tantoupinglv);
						ArrayAdapter adptantoupinglv= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mUttantoupinglv);

						adptantoupinglv.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

						spntantoupinglv.setAdapter(adptantoupinglv);
						spntantoupinglv.setSelection(weld_ut_sp_tantoupinglv[rgindex]);

						spntantoupinglv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
							@Override
							public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


							}

							@Override
							public void onNothingSelected(AdapterView<?> parent) {

							}
						});

						Spinner spnlvbo= (Spinner) view.findViewById(R.id.weld_ut_sp_lvbo);
						ArrayAdapter adplvbo= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mUtlvbo);

						adplvbo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

						spnlvbo.setAdapter(adplvbo);
						spnlvbo.setSelection(weld_ut_sp_lvbo[rgindex]);

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




				Spinner spnchufa = (Spinner) view.findViewById(R.id.weld_ut_sp_chufa);
				ArrayAdapter adpchufa= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mUtchufa);

				adpchufa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnchufa.setAdapter(adpchufa);
				spnchufa.setSelection(weld_ut_sp_chufa);

				spnchufa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});





				Spinner spnyasuo = (Spinner) view.findViewById(R.id.weld_ut_sp_yasuo);
				ArrayAdapter adpyasuo= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mUtyasuo);

				adpyasuo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnyasuo.setAdapter(adpyasuo);
				spnyasuo.setSelection(weld_ut_sp_yasuo);

				spnyasuo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spntantouleixing= (Spinner) view.findViewById(R.id.weld_ut_sp_tantouleixing);
				ArrayAdapter adptantouleixing= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mUtyasuo);

				adptantouleixing.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spntantouleixing.setAdapter(adptantouleixing);
				spntantouleixing.setSelection(weld_ut_sp_tantouleixing[rgindex]);

				spntantouleixing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});

				Spinner spntantoupinglv= (Spinner) view.findViewById(R.id.weld_ut_sp_tantoupinglv);
				ArrayAdapter adptantoupinglv= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mUttantoupinglv);

				adptantoupinglv.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spntantoupinglv.setAdapter(adptantoupinglv);
				spntantoupinglv.setSelection(weld_ut_sp_tantoupinglv[rgindex]);

				spntantoupinglv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				Spinner spnlvbo= (Spinner) view.findViewById(R.id.weld_ut_sp_lvbo);
				ArrayAdapter adplvbo= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mUtlvbo);

				adplvbo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnlvbo.setAdapter(adplvbo);
				spnlvbo.setSelection(weld_ut_sp_lvbo[rgindex]);

				spnlvbo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});

				Button btnweldutyes = (Button) view.findViewById(R.id.weld_ut_savebutton1);


				btnweldutyes.setOnClickListener(new View.OnClickListener() {


					@Override
					public void onClick(View v) {


						Spinner spnweld_ut_sp_chufa = (Spinner) view.findViewById(R.id.weld_ut_sp_chufa);
						weld_ut_sp_chufa = spnweld_ut_sp_chufa.getSelectedItemPosition();

						EditText etweld_ut_et_yanchi = (EditText) view.findViewById(R.id.weld_ut_et_yanchi);
						weld_ut_et_yanchi = etweld_ut_et_yanchi.getText().toString();

						EditText etweld_ut_et_maikuan = (EditText) view.findViewById(R.id.weld_ut_et_maikuan);
						weld_ut_et_maikuan = etweld_ut_et_maikuan.getText().toString();

						Spinner spnweld_ut_sp_yasuo= (Spinner) view.findViewById(R.id.weld_ut_sp_yasuo);
						weld_ut_sp_yasuo = spnweld_ut_sp_yasuo.getSelectedItemPosition();

						EditText etweld_ut_et_yuzhi = (EditText) view. findViewById(R.id.weld_ut_et_yuzhi);
						weld_ut_et_yuzhi = etweld_ut_et_yuzhi.getText().toString();

						RadioGroup rgweld_ut_rg = (RadioGroup) view.findViewById(R.id.weld_ut_rg);
						weld_ut_rg_nr = rgweld_ut_rg.getCheckedRadioButtonId();


						int rgindex = 0;

						for (int i=0;i<6;i++)
						{
							if (weld_ut_rg_nr == rgnr[i]) rgindex=i;
						}


						Spinner spnweld_ut_sp_tantouleixing= (Spinner) view.findViewById(R.id.weld_ut_sp_tantouleixing);
						weld_ut_sp_tantouleixing[rgindex] = spnweld_ut_sp_tantouleixing.getSelectedItemPosition();

						Spinner spnweld_ut_sp_tantoupinglv= (Spinner) view.findViewById(R.id.weld_ut_sp_tantoupinglv);
						weld_ut_sp_tantoupinglv[rgindex] = spnweld_ut_sp_tantoupinglv.getSelectedItemPosition();

						Spinner spnweld_ut_sp_lvbo= (Spinner) view.findViewById(R.id.weld_ut_sp_lvbo);
						weld_ut_sp_lvbo[rgindex] = spnweld_ut_sp_lvbo.getSelectedItemPosition();


						EditText etweld_ut_et_tantouzero = (EditText) view.findViewById(R.id.weld_ut_et_tantouzero);
						weld_ut_et_tantouzero[rgindex] = etweld_ut_et_tantouzero.getText().toString();

						EditText etweld_ut_et_zengyidb = (EditText) view.findViewById(R.id.weld_ut_et_zengyidb);
						weld_ut_et_zengyidb[rgindex] = etweld_ut_et_zengyidb.getText().toString();

						SharedPreferences share = getActivity().getSharedPreferences("weldperference", 0);
						SharedPreferences.Editor editor = share.edit();

						editor.putInt("weld_ut_sp_chufa",weld_ut_sp_chufa);
						editor.putString("weld_ut_et_yanchi",weld_ut_et_yanchi);
						editor.putString("weld_ut_et_maikuan",weld_ut_et_maikuan);
						editor.putInt("weld_ut_sp_yasuo",weld_ut_sp_yasuo);
						editor.putString("weld_ut_et_yuzhi",weld_ut_et_yuzhi);
						editor.putInt("weld_ut_rg_nr", weld_ut_rg_nr);
						editor.putInt("weld_ut_sp_tantouleixing" + String.valueOf(rgindex),weld_ut_sp_tantouleixing[rgindex]);
						editor.putInt("weld_ut_sp_tantoupinglv" + String.valueOf(rgindex),weld_ut_sp_tantoupinglv[rgindex]);
						editor.putInt("weld_ut_sp_lvbo" + String.valueOf(rgindex),weld_ut_sp_lvbo[rgindex]);
						editor.putString("weld_ut_et_tantouzero" + String.valueOf(rgindex),weld_ut_et_tantouzero[rgindex]);
						editor.putString("weld_ut_et_zengyidb" + String.valueOf(rgindex),weld_ut_et_zengyidb[rgindex]);

						Log.d("testsys","weldutparameter saved");
						editor.commit();

						Toast.makeText(getActivity(),"超声参数保存成功！",Toast.LENGTH_SHORT).show();



					}
				});








				return view;
			}

	}
