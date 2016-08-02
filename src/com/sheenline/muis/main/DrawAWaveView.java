package com.sheenline.muis.main;

import com.sheenline.muis.R;
import com.sheenline.muis.common.DrawWaveViewByA;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DrawAWaveView extends Fragment
	{
		private DrawWaveViewByA	mATypeView;
		private View			view;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
			{
				view = inflater.inflate(R.layout.drawatype, null);

				mATypeView = (DrawWaveViewByA) view.findViewById(R.id.area_atype_view);

				mATypeView.SetInfo(new String[]
					{ "0", "100", "200", "300", "400", "500", "600", "700" }, // X轴刻度
						null, 1, new String[]
					{ "0", "50", "100" }, // Y轴刻度
						null, // 数据
						"", "999", 0, 100, 0, 20);

				return view;
			}

	}
