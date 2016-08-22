package com.sheenline.muis.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sheenline.muis.R;

public class WeldLeft extends Fragment {

	private View view;
String etweldnr;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.weldleft, container, false);


		Button btnweld_bt_weld= (Button) view.findViewById(R.id.weld_bt_weld);

		btnweld_bt_weld.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				LayoutInflater factory = LayoutInflater.from(getActivity());
				final View WeldView = factory.inflate(R.layout.mainweld, null);

				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setTitle("焊缝号").setView(WeldView);
				SharedPreferences share = getActivity().getSharedPreferences("weldperference", 0);
				etweldnr = share.getString("etweldnr","");

				EditText etetweldnr = (EditText) WeldView.findViewById(R.id.etweldnr) ;
				etetweldnr.setText(etweldnr);

				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {

						EditText etetweldnr = (EditText) WeldView.findViewById(R.id.etweldnr) ;
						etweldnr = etetweldnr.getText().toString();

						SharedPreferences share = getActivity().getSharedPreferences("weldperference", 0);
						SharedPreferences.Editor editor = share.edit();

						editor.putString("etweldnr",etweldnr);

						editor.commit();

						Toast.makeText(getActivity(),"焊缝号保存成功",Toast.LENGTH_SHORT).show();


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



		return view;
	}

}
