package com.android.newgeneration.dandisnap;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class FragmentProfile extends Fragment implements View.OnClickListener{

    Button fragment_backsign_btn, fragment_option_btn;
    FrameLayout layout1;
    FrameLayout layout2;
    TextView fragment_profile_title_txt, fragment_option_logout_txt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        fragment_option_logout_txt = (TextView) v.findViewById(R.id.fragment_option_logout_txt);
        fragment_option_logout_txt.setOnClickListener(this);
        fragment_backsign_btn = (Button) v.findViewById(R.id.fragment_backsign_btn);
        fragment_backsign_btn.setOnClickListener(this);
        fragment_option_btn = (Button) v.findViewById(R.id.fragment_option_btn);
        fragment_option_btn.setOnClickListener(this);
        layout1 = (FrameLayout) v.findViewById(R.id.layout1);
        layout2 = (FrameLayout) v.findViewById(R.id.layout2);
        fragment_profile_title_txt = (TextView) v.findViewById(R.id.fragment_profile_title_txt);
        return v;
    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.fragment_backsign_btn :
                fragment_backsign_btn.setVisibility(View.GONE);
                fragment_option_btn.setVisibility(View.VISIBLE);
                fragment_profile_title_txt.setText("MYNAME");
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);
                break;

            case R.id.fragment_option_btn :
                fragment_backsign_btn.setVisibility(View.VISIBLE);
                fragment_option_btn.setVisibility(View.GONE);
                fragment_profile_title_txt.setText(R.string.title_option);
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
                break;

            case R.id.fragment_option_logout_txt :
                //·Î±×¾Æ¿ô
                break;
        }

    }

}
