package com.sheenline.muis.main;

import com.sheenline.muis.R;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainTop extends Fragment {
	public class TimeThread extends Thread {
		@Override
		public void run() {
			do {
				try {
					Thread.sleep(1000);
					Message msg = new Message();
					msg.what = msgKey1;
					mHandler.sendMessage(msg);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			while (true);
		}
	}

	private static final int	msgKey1		= 1;

	@SuppressLint("HandlerLeak")
	private Handler				mHandler	= new Handler() {
												@Override
												public void handleMessage(Message msg) {
													super.handleMessage(msg);
													switch (msg.what) {
														case msgKey1:
															long sysTime = System.currentTimeMillis();
															CharSequence sysTimeStr = DateFormat
																	.format("yyyy-MM-dd HH:mm:ss", sysTime);
															tvDate.setText(sysTimeStr);
															break;

														default:
															break;
													}
												}
											};

	private TextView			tvDate;

	private View				view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.maintop, container, false);

		tvDate = (TextView) view.findViewById(R.id.tv_mmct_date);

		new TimeThread().start();

		return view;
	}

}
