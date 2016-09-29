package com.sheenline.muis.main;

import android.app.AlertDialog;
import com.sheenline.muis.R;
import com.sheenline.muis.common.Constant;

import android.content.DialogInterface;
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

public class MetalLeft extends Fragment {

	private View view;


	String et_th_aqidian,et_th_akuandu,et_th_agaodu;
	String et_th_bqidian,et_th_bkuandu,et_th_bgaodu;
	String et_th_cqidian,et_th_ckuandu,et_th_cgaodu;
	String et_th_dqidian,et_th_dkuandu,et_th_dgaodu;
	String etmetalturnout;
    int probe;
	int sp_th_abaojing,sp_th_bbaojing,sp_th_cbaojing,sp_th_dbaojing;

	int metal_rg_nr;
	int[] metalrgnr =new int[10];
	int itemprobe;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.metalleft, container, false);

		RadioButton rbleft0= (RadioButton) view.findViewById(R.id.metal_rb_70pluswai);
		metalrgnr[0]= rbleft0.getId();

		RadioButton rbleft1= (RadioButton) view.findViewById(R.id.metal_rb_70pluszheng);
		metalrgnr[1]= rbleft1.getId();

		RadioButton rbleft2= (RadioButton) view.findViewById(R.id.metal_rb_70plusnei);
		metalrgnr[2]= rbleft2.getId();

		RadioButton rbleft3= (RadioButton) view.findViewById(R.id.metal_rb_70minuswai);
		metalrgnr[3]= rbleft3.getId();

		RadioButton rbleft4= (RadioButton) view.findViewById(R.id.metal_rb_70minuszheng);
		metalrgnr[4]= rbleft4.getId();

		RadioButton rbleft5= (RadioButton) view.findViewById(R.id.metal_rb_70minusnei);
		metalrgnr[5]= rbleft5.getId();

		RadioButton rbleft6= (RadioButton) view.findViewById(R.id.metal_rb_37plus);
		metalrgnr[6]= rbleft6.getId();

		RadioButton rbleft7= (RadioButton) view.findViewById(R.id.metal_rb_37minus);
		metalrgnr[7]= rbleft7.getId();

		RadioButton rbleft8= (RadioButton) view.findViewById(R.id.metal_rb_0);
		metalrgnr[8]= rbleft8.getId();

		RadioButton rbleft9= (RadioButton) view.findViewById(R.id.metal_rb_all);
		metalrgnr[9]= rbleft9.getId();


		SharedPreferences share = getActivity().getSharedPreferences("metalperference", 0);
		metal_rg_nr = share.getInt("metal_rg_nr",metalrgnr[8]);
		RadioGroup rgmetal_rg = (RadioGroup) view.findViewById(R.id.metal_rg);
		rgmetal_rg.check(metal_rg_nr);

		probe = metal_rg_nr;

		for (int i=0;i<10;i++)
		{
			if (probe == metalrgnr[i])
			{
				itemprobe=i;
				break;
			}
		}

		Log.d("testsys",String.valueOf(itemprobe));

		rgmetal_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {


				metal_rg_nr =  group.getCheckedRadioButtonId();

                SharedPreferences share = getActivity().getSharedPreferences("metalperference", 0);
                SharedPreferences.Editor editor = share.edit();

                editor.putInt("metal_rg_nr",metal_rg_nr);
                editor.commit();

				probe = metal_rg_nr;

               for (int i=0;i<10;i++)
			   {
				  if (probe == metalrgnr[i])
				  {
					  itemprobe=i;
					  break;
				  }
			   }




			}

		});
//

		Button btnmetal_bt_turnout= (Button) view.findViewById(R.id.metal_bt_turnout);
		btnmetal_bt_turnout.setOnClickListener(new View.OnClickListener() {
												   @Override
												   public void onClick(View v) {

													   LayoutInflater factory = LayoutInflater.from(getActivity());
													   final View TurnoutView = factory.inflate(R.layout.mainturnout, null);

													   AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
													   builder.setTitle("道岔号").setView(TurnoutView);
													   SharedPreferences share = getActivity().getSharedPreferences("metalperference", 0);
													   etmetalturnout = share.getString("etmetalturnout","");

													     EditText etetmetalturnout = (EditText) TurnoutView.findViewById(R.id.etmetalturnout) ;
													     etetmetalturnout.setText(etmetalturnout);

													    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

															@Override
															public void onClick(DialogInterface arg0, int arg1) {

																EditText etetmetalturnout = (EditText) TurnoutView.findViewById(R.id.etmetalturnout) ;
																etmetalturnout = etetmetalturnout.getText().toString();

																SharedPreferences share = getActivity().getSharedPreferences("metalperference", 0);
																SharedPreferences.Editor editor = share.edit();

																editor.putString("etmetalturnout",etmetalturnout);

																editor.commit();

																Toast.makeText(getActivity(),"道岔号保存成功",Toast.LENGTH_SHORT).show();


															}
														}).setNegativeButton("取消", new DialogInterface.OnClickListener()
													   {

														   @Override
														   public void onClick(DialogInterface arg0, int arg1)
														   {



														   }

													   });

													   builder.create();
													   builder.show();

												   }
											   });

		Button btnmetal_bt_threshold = (Button) view.findViewById(R.id.metal_bt_threshold);

		btnmetal_bt_threshold.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				LayoutInflater factory = LayoutInflater.from(getActivity());
				final View DialogView = factory.inflate(R.layout.mainthreadhold, null);

				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setTitle("闸门设置").setView(DialogView);


				SharedPreferences share = getActivity().getSharedPreferences("metalperference", 0);
				et_th_aqidian = share.getString("et_th_aqidian","");
				et_th_akuandu = share.getString("et_th_akuandu","");
				et_th_agaodu = share.getString("et_th_agaodu","");
				sp_th_abaojing = share.getInt("sp_th_abaojing",0);

				et_th_bqidian = share.getString("et_th_bqidian","");
				et_th_bkuandu = share.getString("et_th_bkuandu","");
				et_th_bgaodu = share.getString("et_th_bgaodu","");
				sp_th_bbaojing = share.getInt("sp_th_bbaojing",0);

				et_th_cqidian = share.getString("et_th_cqidian","");
				et_th_ckuandu = share.getString("et_th_ckuandu","");
				et_th_cgaodu = share.getString("et_th_cgaodu","");
				sp_th_cbaojing = share.getInt("sp_th_cbaojing",0);

				et_th_dqidian = share.getString("et_th_dqidian","");
				et_th_dkuandu = share.getString("et_th_dkuandu","");
				et_th_dgaodu = share.getString("et_th_dgaodu","");
				sp_th_dbaojing = share.getInt("sp_th_dbaojing",0);

				EditText etet_th_aqidian = (EditText) DialogView.findViewById(R.id.et_th_aqidian) ;
				etet_th_aqidian.setText(et_th_aqidian);

				EditText etet_th_akuandu = (EditText) DialogView.findViewById(R.id.et_th_akuandu) ;
				etet_th_akuandu.setText(et_th_akuandu);

				EditText etet_th_agaodu = (EditText) DialogView.findViewById(R.id.et_th_agaodu) ;
				etet_th_agaodu.setText(et_th_agaodu);

				Spinner spnsp_th_abaojing = (Spinner) DialogView.findViewById(R.id.sp_th_abaojing);
				ArrayAdapter adpsp_th_abaojing= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mBaojing);

				adpsp_th_abaojing.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnsp_th_abaojing.setAdapter(adpsp_th_abaojing);
				spnsp_th_abaojing.setSelection(sp_th_abaojing);

				spnsp_th_abaojing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				EditText etet_th_bqidian = (EditText) DialogView.findViewById(R.id.et_th_bqidian) ;
				etet_th_bqidian.setText(et_th_bqidian);

				EditText etet_th_bkuandu = (EditText) DialogView.findViewById(R.id.et_th_bkuandu) ;
				etet_th_bkuandu.setText(et_th_bkuandu);

				EditText etet_th_bgaodu = (EditText) DialogView.findViewById(R.id.et_th_bgaodu) ;
				etet_th_bgaodu.setText(et_th_bgaodu);

				Spinner spnsp_th_bbaojing = (Spinner) DialogView.findViewById(R.id.sp_th_bbaojing);
				ArrayAdapter adpsp_th_bbaojing= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mBaojing);

				adpsp_th_bbaojing.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnsp_th_bbaojing.setAdapter(adpsp_th_bbaojing);
				spnsp_th_bbaojing.setSelection(sp_th_bbaojing);

				spnsp_th_bbaojing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});



				EditText etet_th_cqidian = (EditText) DialogView.findViewById(R.id.et_th_cqidian) ;
				etet_th_cqidian.setText(et_th_cqidian);

				EditText etet_th_ckuandu = (EditText) DialogView.findViewById(R.id.et_th_ckuandu) ;
				etet_th_ckuandu.setText(et_th_ckuandu);

				EditText etet_th_cgaodu = (EditText) DialogView.findViewById(R.id.et_th_cgaodu) ;
				etet_th_cgaodu.setText(et_th_cgaodu);

				Spinner spnsp_th_cbaojing = (Spinner) DialogView.findViewById(R.id.sp_th_cbaojing);
				ArrayAdapter adpsp_th_cbaojing= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mBaojing);

				adpsp_th_cbaojing.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnsp_th_cbaojing.setAdapter(adpsp_th_cbaojing);
				spnsp_th_cbaojing.setSelection(sp_th_cbaojing);

				spnsp_th_cbaojing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});



				EditText etet_th_dqidian = (EditText) DialogView.findViewById(R.id.et_th_dqidian) ;
				etet_th_dqidian.setText(et_th_dqidian);

				EditText etet_th_dkuandu = (EditText) DialogView.findViewById(R.id.et_th_dkuandu) ;
				etet_th_dkuandu.setText(et_th_dkuandu);

				EditText etet_th_dgaodu = (EditText) DialogView.findViewById(R.id.et_th_dgaodu) ;
				etet_th_dgaodu.setText(et_th_dgaodu);

				Spinner spnsp_th_dbaojing = (Spinner) DialogView.findViewById(R.id.sp_th_dbaojing);
				ArrayAdapter adpsp_th_dbaojing= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mBaojing);

				adpsp_th_dbaojing.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				spnsp_th_dbaojing.setAdapter(adpsp_th_dbaojing);
				spnsp_th_dbaojing.setSelection(sp_th_dbaojing);

				spnsp_th_dbaojing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {

					}
				});


				builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
						{

							@Override
							public void onClick(DialogInterface arg0, int arg1)
							{

								EditText etet_th_aqidian = (EditText) DialogView.findViewById(R.id.et_th_aqidian) ;
								et_th_aqidian = etet_th_aqidian.getText().toString();


								EditText etet_th_akuandu = (EditText) DialogView.findViewById(R.id.et_th_akuandu) ;
								et_th_akuandu= etet_th_akuandu.getText().toString();

								EditText etet_th_agaodu = (EditText) DialogView.findViewById(R.id.et_th_agaodu) ;
								et_th_agaodu = etet_th_agaodu.getText().toString();

								Spinner spnsp_th_abaojing = (Spinner) DialogView.findViewById(R.id.sp_th_abaojing) ;
								sp_th_abaojing = spnsp_th_abaojing.getSelectedItemPosition();

								EditText etet_th_bqidian = (EditText) DialogView.findViewById(R.id.et_th_bqidian) ;
								et_th_bqidian = etet_th_bqidian.getText().toString();


								EditText etet_th_bkuandu = (EditText) DialogView.findViewById(R.id.et_th_bkuandu) ;
								et_th_bkuandu= etet_th_bkuandu.getText().toString();

								EditText etet_th_bgaodu = (EditText) DialogView.findViewById(R.id.et_th_bgaodu) ;
								et_th_bgaodu = etet_th_bgaodu.getText().toString();

								Spinner spnsp_th_bbaojing = (Spinner) DialogView.findViewById(R.id.sp_th_bbaojing) ;
								sp_th_bbaojing = spnsp_th_bbaojing.getSelectedItemPosition();

								EditText etet_th_cqidian = (EditText) DialogView.findViewById(R.id.et_th_cqidian) ;
								et_th_cqidian = etet_th_cqidian.getText().toString();


								EditText etet_th_ckuandu = (EditText) DialogView.findViewById(R.id.et_th_ckuandu) ;
								et_th_ckuandu= etet_th_ckuandu.getText().toString();

								EditText etet_th_cgaodu = (EditText) DialogView.findViewById(R.id.et_th_cgaodu) ;
								et_th_cgaodu = etet_th_cgaodu.getText().toString();

								Spinner spnsp_th_cbaojing = (Spinner) DialogView.findViewById(R.id.sp_th_cbaojing) ;
								sp_th_cbaojing = spnsp_th_cbaojing.getSelectedItemPosition();

								EditText etet_th_dqidian = (EditText) DialogView.findViewById(R.id.et_th_dqidian) ;
								et_th_dqidian = etet_th_dqidian.getText().toString();


								EditText etet_th_dkuandu = (EditText) DialogView.findViewById(R.id.et_th_dkuandu) ;
								et_th_dkuandu= etet_th_dkuandu.getText().toString();

								EditText etet_th_dgaodu = (EditText) DialogView.findViewById(R.id.et_th_dgaodu) ;
								et_th_dgaodu = etet_th_dgaodu.getText().toString();

								Spinner spnsp_th_dbaojing = (Spinner) DialogView.findViewById(R.id.sp_th_dbaojing) ;
								sp_th_dbaojing = spnsp_th_dbaojing.getSelectedItemPosition();


								SharedPreferences share = getActivity().getSharedPreferences("metalperference", 0);
								SharedPreferences.Editor editor = share.edit();

                                editor.putString("et_th_aqidian",et_th_aqidian);
								editor.putString("et_th_akuandu",et_th_akuandu);
								editor.putString("et_th_agaodu",et_th_agaodu);
								editor.putInt("sp_th_abaojing",sp_th_abaojing);

								editor.putString("et_th_bqidian",et_th_bqidian);
								editor.putString("et_th_bkuandu",et_th_bkuandu);
								editor.putString("et_th_bgaodu",et_th_bgaodu);
								editor.putInt("sp_th_bbaojing",sp_th_bbaojing);

								editor.putString("et_th_cqidian",et_th_cqidian);
								editor.putString("et_th_ckuandu",et_th_ckuandu);
								editor.putString("et_th_cgaodu",et_th_cgaodu);
								editor.putInt("sp_th_cbaojing",sp_th_cbaojing);


								editor.putString("et_th_dqidian",et_th_dqidian);
								editor.putString("et_th_dkuandu",et_th_dkuandu);
								editor.putString("et_th_dgaodu",et_th_dgaodu);
								editor.putInt("sp_th_dbaojing",sp_th_dbaojing);



								editor.commit();

								Toast.makeText(getActivity(),"阈值参数保存成功",Toast.LENGTH_SHORT).show();


							}

						})
						.setNegativeButton("取消", new DialogInterface.OnClickListener()
						{

							@Override
							public void onClick(DialogInterface arg0, int arg1)
							{



							}

						});


				builder.create();

				builder.show();

			}
		});

		Button btnmetal_bt_plus= (Button) view.findViewById(R.id.metal_bt_plus);


		btnmetal_bt_plus.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				Log.d("testsys","longclick");
				return true;
			}
		});


		btnmetal_bt_plus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("testsys","click");

			}
		});

		Button btnmetal_bt_minus= (Button) view.findViewById(R.id.metal_bt_minus);


		btnmetal_bt_minus.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				Log.d("testsys","---longclick");
				return true;
			}
		});



		btnmetal_bt_minus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("testsys","---click");

			}
		});


		return view;
	}

}
