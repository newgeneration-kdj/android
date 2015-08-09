package com.android.newgeneration.dandisnap.Profile;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.newgeneration.dandisnap.Login.ActivityLogin;
import com.android.newgeneration.dandisnap.Login.UserData;
import com.android.newgeneration.dandisnap.R;
import com.facebook.login.LoginManager;

public class FragmentProfile extends Fragment implements View.OnClickListener {

    Button mProfileBtnBacksign, mProfileBtnOption;
    FrameLayout mProfileFlProfile, mProfileFlOption;
    TextView mProfileTxtTitle, mProfileTxtLogout, mProfileTxtName;
    RelativeLayout mProfileRlBacksign, mProfileRlOption, mProfileRlBar;
    UserData mUserdata = UserData.getInstance();
    DisplayMetrics metrics;
    GridView mProfileGrdImage;
    ImageAdapter mImageAdapter;
    Cursor imageCursor;
    int imageColumIndex;
    int count;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_profile, container, false);
        mProfileTxtLogout = (TextView) v.findViewById(R.id.profile_txt_logout);
        mProfileTxtLogout.setOnClickListener(this);
        mProfileBtnBacksign = (Button) v.findViewById(R.id.profile_btn_backsign);
        mProfileBtnBacksign.setOnClickListener(this);
        mProfileBtnOption = (Button) v.findViewById(R.id.profile_btn_option);
        mProfileBtnOption.setOnClickListener(this);
        mProfileFlProfile = (FrameLayout) v.findViewById(R.id.profile_fl_profile);
        mProfileFlOption = (FrameLayout) v.findViewById(R.id.profile_fl_option);
        mProfileRlBar = (RelativeLayout) v.findViewById(R.id.profile_rl_bar);
        mProfileRlBacksign = (RelativeLayout) v.findViewById(R.id.profile_rl_backsign);
        mProfileRlOption = (RelativeLayout) v.findViewById(R.id.profile_rl_option);
        mProfileTxtTitle = (TextView) v.findViewById(R.id.profile_txt_title);
        mProfileTxtName = (TextView) v.findViewById(R.id.profile_txt_name);
        setText();
        init();
        return v;
    }

    public void init(){
        metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mProfileGrdImage = (GridView)v.findViewById(R.id.profile_grd_image);
        // 갤러리에서 사진 가져오기
        String[] img = {MediaStore.Images.Thumbnails._ID};
        imageCursor = getActivity().managedQuery(
                MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
                img, null, null, MediaStore.Images.Thumbnails.IMAGE_ID + ""
        );
        imageColumIndex = imageCursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID);
        count = imageCursor.getCount();
        mImageAdapter = new ImageAdapter(getActivity(),metrics.widthPixels,metrics.heightPixels,
                imageColumIndex, count, imageCursor);
        mProfileGrdImage.setAdapter(mImageAdapter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.profile_btn_backsign:
                mProfileRlBacksign.setVisibility(View.GONE);
                mProfileRlOption.setVisibility(View.VISIBLE);
                setText();
                mProfileFlProfile.setVisibility(View.VISIBLE);
                mProfileFlOption.setVisibility(View.GONE);
                mProfileRlBar.setVisibility(View.GONE);
                break;

            case R.id.profile_btn_option:
                mProfileRlBacksign.setVisibility(View.VISIBLE);
                mProfileRlOption.setVisibility(View.GONE);
                mProfileTxtTitle.setText(R.string.title_option);
                mProfileFlProfile.setVisibility(View.GONE);
                mProfileFlOption.setVisibility(View.VISIBLE);
                mProfileRlBar.setVisibility(View.VISIBLE);
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
                LoginManager.getInstance().logOut();
                startActivity(intent);
                getActivity().finish();
            }
        });
        alert_ask.show();
    }

    public void setText() {
        if (mUserdata.getUser_nickname() != null) {
            mProfileTxtTitle.setText(mUserdata.getUser_nickname());
        } else {
            mProfileTxtTitle.setText("MYNAME");
        }
        if (mUserdata.getUser_nickname() != null) {
            mProfileTxtName.setText(mUserdata.getUser_name());
        }
    }

}
