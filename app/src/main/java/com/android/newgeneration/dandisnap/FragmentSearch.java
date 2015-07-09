package com.android.newgeneration.dandisnap;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

public class FragmentSearch extends Fragment implements View.OnFocusChangeListener, View.OnClickListener {

    EditText fragment_search_edt;
    Button fragment_backsign_btn;
    FrameLayout layout1;
    FrameLayout layout2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        fragment_backsign_btn = (Button) v.findViewById(R.id.fragment_backsign_btn);
        fragment_backsign_btn.setOnClickListener(this);
        fragment_search_edt = (EditText) v.findViewById((R.id.fragment_search_edt));
        fragment_search_edt.setOnFocusChangeListener(this);
        layout1 = (FrameLayout) v.findViewById(R.id.layout1);
        layout2 = (FrameLayout) v.findViewById(R.id.layout2);
        return v;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        if(v.getId() == R.id.fragment_search_edt)
        {
            if(hasFocus)
            {
                fragment_backsign_btn.setVisibility(View.VISIBLE);
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.fragment_backsign_btn :
                fragment_backsign_btn.setVisibility(View.GONE);
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(fragment_search_edt.getWindowToken(), 0);
                fragment_search_edt.clearFocus();

                break;
        }

    }

}
