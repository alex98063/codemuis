package com.sheenline.muis.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sheenline.muis.R;

public class MainSaveData extends Fragment
	{

		private View view;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
			{
				view = inflater.inflate(R.layout.mainsavedata, container, false);

				return view;
			}

	}
