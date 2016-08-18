package com.sheenline.muis.main;

import com.sheenline.muis.R;
import com.sheenline.muis.common.Constant;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MetalSysConfig extends Fragment {

    private View view;


    int pre_sp_70pluszheng, pre_sp_70pluswai, pre_sp_70plusnei, pre_sp_70minuszheng;
    int pre_sp_70minuswai, pre_sp_70minusnei, pre_sp_zerochubo, pre_sp_zeroshibo, pre_sp_zeroshuangbo;
    int pre_sp_37pluschubo, pre_sp_37plusshuangbo, pre_sp_37minuschubo, pre_sp_37minusshuangbo;
    int pre_sp_70pluszhengyanse, pre_sp_70pluswaiyanse, pre_sp_70plusneiyanse, pre_sp_70minuszhengyanse;
    int pre_sp_70minuswaiyanse, pre_sp_70minusneiyanse, pre_sp_37plusyanse, pre_sp_37minusyanse, pre_sp_zeroshuangboyanse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.metalsysconfig, container, false);

        SharedPreferences share = getActivity().getSharedPreferences("metalperference", 0);

        pre_sp_70pluszheng = share.getInt("pre_sp_70pluszheng",0);
        pre_sp_70pluswai = share.getInt("pre_sp_70pluswai",0);
        pre_sp_70plusnei = share.getInt("pre_sp_70plusnei",0);

        pre_sp_70minuszheng = share.getInt("pre_sp_70minuszheng",0);
        pre_sp_70minuswai = share.getInt("pre_sp_70minuswai",0);
        pre_sp_70minusnei = share.getInt("pre_sp_70minusnei",0);

        pre_sp_zerochubo = share.getInt("pre_sp_zerochubo",0);
        pre_sp_zeroshibo = share.getInt("pre_sp_zeroshibo",0);
        pre_sp_zeroshuangbo = share.getInt("pre_sp_zeroshuangbo",0);

        pre_sp_37pluschubo = share.getInt("pre_sp_37pluschubo",0);
        pre_sp_37plusshuangbo = share.getInt("pre_sp_37plusshuangbo",0);

        pre_sp_37minuschubo = share.getInt("pre_sp_37minuschubo",0);
        pre_sp_37minusshuangbo = share.getInt("pre_sp_37minusshuangbo",0);

        pre_sp_70pluszhengyanse = share.getInt("pre_sp_70pluszhengyanse",0);
        pre_sp_70pluswaiyanse = share.getInt("pre_sp_70pluswaiyanse",0);
        pre_sp_70plusneiyanse = share.getInt("pre_sp_70plusneiyanse",0);

        pre_sp_70minuszhengyanse = share.getInt("pre_sp_70minuszhengyanse",0);
        pre_sp_70minuswaiyanse = share.getInt("pre_sp_70minuswaiyanse",0);
        pre_sp_70minusneiyanse = share.getInt("pre_sp_70minusneiyanse",0);

        pre_sp_37plusyanse = share.getInt("pre_sp_37plusyanse",0);
        pre_sp_37minusyanse = share.getInt("pre_sp_37minusyanse",0);
        pre_sp_zeroshuangboyanse = share.getInt("pre_sp_zeroshuangboyanse",0);



        Spinner spn70pluszheng = (Spinner) view.findViewById(R.id.pre_sp_70pluszheng);
        ArrayAdapter adp70pluszheng = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

        adp70pluszheng.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn70pluszheng.setAdapter(adp70pluszheng);
        spn70pluszheng.setSelection(pre_sp_70pluszheng);

        spn70pluszheng.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spn70pluswai = (Spinner) view.findViewById(R.id.pre_sp_70pluswai);
        ArrayAdapter adp70pluswai = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

        adp70pluswai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn70pluswai.setAdapter(adp70pluswai);
        spn70pluswai.setSelection(pre_sp_70pluswai);

        spn70pluswai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spn70plusnei = (Spinner) view.findViewById(R.id.pre_sp_70plusnei);
        ArrayAdapter adp70plusnei = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

        adp70plusnei.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn70plusnei.setAdapter(adp70plusnei);
        spn70plusnei.setSelection(pre_sp_70plusnei);

        spn70plusnei.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spn70minuszheng = (Spinner) view.findViewById(R.id.pre_sp_70minuszheng);
        ArrayAdapter adp70minuszheng = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

        adp70minuszheng.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn70minuszheng.setAdapter(adp70minuszheng);
        spn70minuszheng.setSelection(pre_sp_70minuszheng);

        spn70minuszheng.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spn70minuswai = (Spinner) view.findViewById(R.id.pre_sp_70minuswai);
        ArrayAdapter adp70minuswai = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

        adp70minuswai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn70minuswai.setAdapter(adp70minuswai);
        spn70minuswai.setSelection(pre_sp_70minuswai);

        spn70minuswai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spn70minusnei = (Spinner) view.findViewById(R.id.pre_sp_70minusnei);
        ArrayAdapter adp70minusnei = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

        adp70minusnei.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn70minusnei.setAdapter(adp70minusnei);
        spn70minusnei.setSelection(pre_sp_70minusnei);

        spn70minusnei.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spnzerochubo = (Spinner) view.findViewById(R.id.pre_sp_zerochubo);
        ArrayAdapter adpzerochubo = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

        adpzerochubo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnzerochubo.setAdapter(adpzerochubo);
        spnzerochubo.setSelection(pre_sp_zerochubo);

        spnzerochubo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spnzeroshibo = (Spinner) view.findViewById(R.id.pre_sp_zeroshibo);
        ArrayAdapter adpzeroshibo = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

        adpzeroshibo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnzeroshibo.setAdapter(adpzeroshibo);
        spnzeroshibo.setSelection(pre_sp_zeroshibo);

        spnzeroshibo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spnzeroshuangbo = (Spinner) view.findViewById(R.id.pre_sp_zeroshuangbo);
        ArrayAdapter adpzeroshuangbo = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

        adpzeroshuangbo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnzeroshuangbo.setAdapter(adpzeroshuangbo);
        spnzeroshuangbo.setSelection(pre_sp_zeroshuangbo);

        spnzeroshuangbo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spn37pluschubo = (Spinner) view.findViewById(R.id.pre_sp_37pluschubo);
        ArrayAdapter adp37pluschubo = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

        adp37pluschubo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn37pluschubo.setAdapter(adp37pluschubo);
        spn37pluschubo.setSelection(pre_sp_37pluschubo);

        spn37pluschubo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spn37plusshuangbo = (Spinner) view.findViewById(R.id.pre_sp_37plusshuangbo);
        ArrayAdapter adp37plusshuangbo = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

        adp37plusshuangbo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn37plusshuangbo.setAdapter(adp37plusshuangbo);
        spn37plusshuangbo.setSelection(pre_sp_37plusshuangbo);

        spn37plusshuangbo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spn37minuschubo = (Spinner) view.findViewById(R.id.pre_sp_37minuschubo);
        ArrayAdapter adp37minuschubo = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

        adp37minuschubo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn37minuschubo.setAdapter(adp37minuschubo);
        spn37minuschubo.setSelection(pre_sp_37minuschubo);

        spn37minuschubo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spn37minusshuangbo = (Spinner) view.findViewById(R.id.pre_sp_37minusshuangbo);
        ArrayAdapter adp37minusshuangbo = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mPre);

        adp37minusshuangbo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn37minusshuangbo.setAdapter(adp37minusshuangbo);
        spn37minusshuangbo.setSelection(pre_sp_37minusshuangbo);

        spn37minusshuangbo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spn70pluszhengyanse = (Spinner) view.findViewById(R.id.pre_sp_70pluszhengyanse);
        ArrayAdapter adp70pluszhengyanse = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

        adp70pluszhengyanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn70pluszhengyanse.setAdapter(adp70pluszhengyanse);
        spn70pluszhengyanse.setSelection(pre_sp_70pluszhengyanse);

        spn70pluszhengyanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spn70pluswaiyanse = (Spinner) view.findViewById(R.id.pre_sp_70pluswaiyanse);
        ArrayAdapter adp70pluswaiyanse = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

        adp70pluswaiyanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn70pluswaiyanse.setAdapter(adp70pluswaiyanse);
        spn70pluswaiyanse.setSelection(pre_sp_70pluswaiyanse);

        spn70pluswaiyanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spn70plusneiyanse = (Spinner) view.findViewById(R.id.pre_sp_70plusneiyanse);
        ArrayAdapter adp70plusneiyanse = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

        adp70plusneiyanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn70plusneiyanse.setAdapter(adp70plusneiyanse);
        spn70plusneiyanse.setSelection(pre_sp_70plusneiyanse);

        spn70plusneiyanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spn70minuszhengyanse = (Spinner) view.findViewById(R.id.pre_sp_70minuszhengyanse);
        ArrayAdapter adp70minuszhengyanse = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

        adp70minuszhengyanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn70minuszhengyanse.setAdapter(adp70minuszhengyanse);
        spn70minuszhengyanse.setSelection(pre_sp_70minuszhengyanse);

        spn70minuszhengyanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spn70minuswaiyanse = (Spinner) view.findViewById(R.id.pre_sp_70minuswaiyanse);
        ArrayAdapter adp70minuswaiyanse = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

        adp70minuswaiyanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn70minuswaiyanse.setAdapter(adp70minuswaiyanse);
        spn70minuswaiyanse.setSelection(pre_sp_70minuswaiyanse);

        spn70minuswaiyanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spn70minusneiyanse = (Spinner) view.findViewById(R.id.pre_sp_70minusneiyanse);
        ArrayAdapter adp70minusneiyanse = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

        adp70minusneiyanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn70minusneiyanse.setAdapter(adp70minusneiyanse);
        spn70minusneiyanse.setSelection(pre_sp_70minusneiyanse);

        spn70minusneiyanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spn37plusyanse = (Spinner) view.findViewById(R.id.pre_sp_37plusyanse);
        ArrayAdapter adp37plusyanse = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

        adp37plusyanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn37plusyanse.setAdapter(adp37plusyanse);
        spn37plusyanse.setSelection(pre_sp_37plusyanse);

        spn37plusyanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spn37minusyanse = (Spinner) view.findViewById(R.id.pre_sp_37minusyanse);
        ArrayAdapter adp37minusyanse = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

        adp37minusyanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spn37minusyanse.setAdapter(adp37minusyanse);
        spn37minusyanse.setSelection(pre_sp_37minusyanse);

        spn37minusyanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spnzeroshuangboyanse = (Spinner) view.findViewById(R.id.pre_sp_zeroshuangboyanse);
        ArrayAdapter adpzeroshuangboyanse = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constant.ConValue.mConfigshiboyanse);

        adpzeroshuangboyanse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnzeroshuangboyanse.setAdapter(adpzeroshuangboyanse);
        spnzeroshuangboyanse.setSelection(pre_sp_zeroshuangboyanse);

        spnzeroshuangboyanse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button btnmetalsysbutton1 = (Button) view.findViewById(R.id.metalsysbutton1);


        btnmetalsysbutton1.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Spinner spnpre_sp_70pluszheng = (Spinner) view.findViewById(R.id.pre_sp_70pluszheng);
                pre_sp_70pluszheng = spnpre_sp_70pluszheng.getSelectedItemPosition();

                Spinner spnpre_sp_70pluswai = (Spinner) view.findViewById(R.id.pre_sp_70pluswai);
                pre_sp_70pluswai = spnpre_sp_70pluswai.getSelectedItemPosition();

                Spinner spnpre_sp_70plusnei = (Spinner) view.findViewById(R.id.pre_sp_70plusnei);
                pre_sp_70plusnei = spnpre_sp_70plusnei.getSelectedItemPosition();

                Spinner spnpre_sp_70minuszheng = (Spinner) view.findViewById(R.id.pre_sp_70minuszheng);
                pre_sp_70minuszheng = spnpre_sp_70minuszheng.getSelectedItemPosition();


                Spinner spnpre_sp_70minuswai = (Spinner) view.findViewById(R.id.pre_sp_70minuswai);
                pre_sp_70minuswai = spnpre_sp_70minuswai.getSelectedItemPosition();

                Spinner spnpre_sp_70minusnei = (Spinner) view.findViewById(R.id.pre_sp_70minusnei);
                pre_sp_70minusnei = spnpre_sp_70minusnei.getSelectedItemPosition();

                Spinner spnpre_sp_zerochubo = (Spinner) view.findViewById(R.id.pre_sp_zerochubo);
                pre_sp_zerochubo = spnpre_sp_zerochubo.getSelectedItemPosition();

                Spinner spnpre_sp_zeroshibo = (Spinner) view.findViewById(R.id.pre_sp_zeroshibo);
                pre_sp_zeroshibo = spnpre_sp_zeroshibo.getSelectedItemPosition();

                Spinner spnpre_sp_zeroshuangbo = (Spinner) view.findViewById(R.id.pre_sp_zeroshuangbo);
                pre_sp_zeroshuangbo = spnpre_sp_zeroshuangbo.getSelectedItemPosition();

                Spinner spnpre_sp_37pluschubo = (Spinner) view.findViewById(R.id.pre_sp_37pluschubo);
                pre_sp_37pluschubo = spnpre_sp_37pluschubo.getSelectedItemPosition();

                Spinner spnpre_sp_37plusshuangbo = (Spinner) view.findViewById(R.id.pre_sp_37plusshuangbo);
                pre_sp_37plusshuangbo = spnpre_sp_37plusshuangbo.getSelectedItemPosition();


                Spinner spnpre_sp_37minuschubo = (Spinner) view.findViewById(R.id.pre_sp_37minuschubo);
                pre_sp_37minuschubo = spnpre_sp_37minuschubo.getSelectedItemPosition();


                Spinner spnpre_sp_37minusshuangbo = (Spinner) view.findViewById(R.id.pre_sp_37minusshuangbo);
                pre_sp_37minusshuangbo = spnpre_sp_37minusshuangbo.getSelectedItemPosition();

                Spinner spnpre_sp_70pluszhengyanse = (Spinner) view.findViewById(R.id.pre_sp_70pluszhengyanse);
                pre_sp_70pluszhengyanse = spnpre_sp_70pluszhengyanse.getSelectedItemPosition();


                Spinner spnpre_sp_70pluswaiyanse = (Spinner) view.findViewById(R.id.pre_sp_70pluswaiyanse);
                pre_sp_70pluswaiyanse = spnpre_sp_70pluswaiyanse.getSelectedItemPosition();


                Spinner spnpre_sp_70plusneiyanse = (Spinner) view.findViewById(R.id.pre_sp_70plusneiyanse);
                pre_sp_70plusneiyanse = spnpre_sp_70plusneiyanse.getSelectedItemPosition();

                Spinner spnpre_sp_70minuszhengyanse = (Spinner) view.findViewById(R.id.pre_sp_70minuszhengyanse);
                pre_sp_70minuszhengyanse = spnpre_sp_70minuszhengyanse.getSelectedItemPosition();


                Spinner spnpre_sp_70minuswaiyanse = (Spinner) view.findViewById(R.id.pre_sp_70minuswaiyanse);
                pre_sp_70minuswaiyanse = spnpre_sp_70minuswaiyanse.getSelectedItemPosition();

                Spinner spnpre_sp_70minusneiyanse = (Spinner) view.findViewById(R.id.pre_sp_70minusneiyanse);
                pre_sp_70minusneiyanse = spnpre_sp_70minusneiyanse.getSelectedItemPosition();

                Spinner spnpre_sp_37plusyanse = (Spinner) view.findViewById(R.id.pre_sp_37plusyanse);
                pre_sp_37plusyanse = spnpre_sp_37plusyanse.getSelectedItemPosition();

                Spinner spnpre_sp_37minusyanse = (Spinner) view.findViewById(R.id.pre_sp_37minusyanse);
                pre_sp_37minusyanse = spnpre_sp_37minusyanse.getSelectedItemPosition();


                Spinner spnpre_sp_zeroshuangboyanse = (Spinner) view.findViewById(R.id.pre_sp_zeroshuangboyanse);
                pre_sp_zeroshuangboyanse = spnpre_sp_zeroshuangboyanse.getSelectedItemPosition();


                SharedPreferences share = getActivity().getSharedPreferences("metalperference", 0);
                SharedPreferences.Editor editor = share.edit();


                editor.putInt("pre_sp_70pluszheng", pre_sp_70pluszheng);
                editor.putInt("pre_sp_70pluswai", pre_sp_70pluswai);
                editor.putInt("pre_sp_70plusnei", pre_sp_70plusnei);

                editor.putInt("pre_sp_70minuszheng", pre_sp_70minuszheng);
                editor.putInt("pre_sp_70minuswai", pre_sp_70minuswai);
                editor.putInt("pre_sp_70minusnei", pre_sp_70minusnei);

                editor.putInt("pre_sp_zerochubo", pre_sp_zerochubo);
                editor.putInt("pre_sp_zeroshibo", pre_sp_zeroshibo);
                editor.putInt("pre_sp_zeroshuangbo", pre_sp_zeroshuangbo);

                editor.putInt("pre_sp_37pluschubo", pre_sp_37pluschubo);
                editor.putInt("pre_sp_37plusshuangbo", pre_sp_37plusshuangbo);

                editor.putInt("pre_sp_37minuschubo", pre_sp_37minuschubo);
                editor.putInt("pre_sp_37minusshuangbo", pre_sp_37minusshuangbo);

                editor.putInt("pre_sp_70pluszhengyanse", pre_sp_70pluszhengyanse);
                editor.putInt("pre_sp_70pluswaiyanse", pre_sp_70pluswaiyanse);
                editor.putInt("pre_sp_70plusneiyanse", pre_sp_70plusneiyanse);


                editor.putInt("pre_sp_70minuszhengyanse", pre_sp_70minuszhengyanse);
                editor.putInt("pre_sp_70minuswaiyanse", pre_sp_70minuswaiyanse);
                editor.putInt("pre_sp_70minusneiyanse", pre_sp_70minusneiyanse);


                editor.putInt("pre_sp_37plusyanse", pre_sp_37plusyanse);
                editor.putInt("pre_sp_37minusyanse", pre_sp_37minusyanse);
                editor.putInt("pre_sp_zeroshuangboyanse", pre_sp_zeroshuangboyanse);


                editor.commit();

                Toast.makeText(getActivity(),"预设参数保存成功！",Toast.LENGTH_SHORT).show();





            }
        });

        return view;
    }

}
