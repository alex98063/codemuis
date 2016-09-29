package com.sheenline.muis.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sheenline.muis.R;
import com.sheenline.muis.common.DrawWaveViewByB;

public class DrawBWaveView extends Fragment {
    private DrawWaveViewByB mBTypeView;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.drawbtype, null);

        mBTypeView = (DrawWaveViewByB) view.findViewById(R.id.area_btype_view);

        mBTypeView.setinfo(new String[]{"0", "100", "200", "300", "400", "500", "600", "700"}, null, 1, new String[]{"0", "50", "100"}, null, "", "999", 0, 100, 0, 20);

        return view;
    }

}
