package com.android.newgeneration.dandisnap;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public class FragmentActivity extends Fragment implements View.OnClickListener {



    TextView you_txt;
    TextView following_txt;

    FrameLayout layout1;

    FrameLayout layout2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_activity, container, false);

        following_txt  = (TextView) v.findViewById(R.id.fragment_following_txt);
        following_txt.setOnClickListener(this);
       you_txt  = (TextView) v.findViewById(R.id.fragment_you_txt);
        you_txt.setOnClickListener(this);
        layout1 = (FrameLayout) v.findViewById(R.id.layout1);
        layout2 = (FrameLayout) v.findViewById(R.id.layout2);
        return v;
    }




    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.fragment_following_txt:
                following_txt.setTextColor(Color.parseColor("#3399FF"));
                you_txt.setTextColor(Color.parseColor("#353535"));
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);
                break;
            case R.id.fragment_you_txt:
                you_txt.setTextColor(Color.parseColor("#3399FF"));
                following_txt.setTextColor(Color.parseColor("#353535"));
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
                break;
        }

    }

}
