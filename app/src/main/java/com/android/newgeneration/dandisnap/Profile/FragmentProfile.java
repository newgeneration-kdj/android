package com.android.newgeneration.dandisnap.Profile;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.newgeneration.dandisnap.Login.ActivityLogin;
import com.android.newgeneration.dandisnap.Login.UserData;
import com.android.newgeneration.dandisnap.R;

public class FragmentProfile extends Fragment implements View.OnClickListener {

    Button mProfileBtnBacksign, mProfileBtnOption;
    FrameLayout mProfileFlProfile,mProfileFlOption;
    TextView mProfileTxtTitle, mProfileTxtLogout;
    UserData mUserdata = UserData.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        mProfileTxtLogout = (TextView) v.findViewById(R.id.profile_txt_logout);
        mProfileTxtLogout.setOnClickListener(this);
        mProfileBtnBacksign = (Button) v.findViewById(R.id.profile_btn_backsign);
        mProfileBtnBacksign.setOnClickListener(this);
        mProfileBtnOption = (Button) v.findViewById(R.id.profile_btn_option);
        mProfileBtnOption.setOnClickListener(this);
        mProfileFlProfile = (FrameLayout) v.findViewById(R.id.profile_fl_profile);
        mProfileFlOption = (FrameLayout) v.findViewById(R.id.profile_fl_option);
        mProfileTxtTitle = (TextView) v.findViewById(R.id.profile_txt_title);
        return v;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.profile_btn_backsign:
                mProfileBtnBacksign.setVisibility(View.GONE);
                mProfileBtnOption.setVisibility(View.VISIBLE);
                mProfileTxtTitle.setText("MYNAME");
                mProfileFlProfile.setVisibility(View.VISIBLE);
                mProfileFlOption.setVisibility(View.GONE);
                break;

            case R.id.profile_btn_option:
                mProfileBtnBacksign.setVisibility(View.VISIBLE);
                mProfileBtnOption.setVisibility(View.GONE);
                mProfileTxtTitle.setText(R.string.title_option);
                mProfileFlProfile.setVisibility(View.GONE);
                mProfileFlOption.setVisibility(View.VISIBLE);
                break;

            case R.id.profile_txt_logout:
                //로그아웃
                askLogout();
                break;
        }

    }

    public void askLogout() {
        AlertDialog.Builder alert_ask = new AlertDialog.Builder(getActivity());
        alert_ask.setMessage("계속할까요?");
        alert_ask.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert_ask.setPositiveButton("로그아웃", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mUserdata.setOnlogin(0, getActivity());
                Intent intent = new Intent(getActivity().getApplicationContext(), ActivityLogin.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        alert_ask.show();
    }

}
