package com.android.newgeneration.dandisnap.Search;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.android.newgeneration.dandisnap.R;

public class FragmentSearch extends Fragment implements View.OnFocusChangeListener, View.OnClickListener {

    EditText mSearchEditSearch;
    Button mSearchBtnBacksign;
    RelativeLayout mSearchRlBacksign;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        mSearchBtnBacksign = (Button) v.findViewById(R.id.search_btn_backsign);
        mSearchBtnBacksign.setOnClickListener(this);
        mSearchEditSearch = (EditText) v.findViewById((R.id.search_edit_search));
        mSearchEditSearch.setOnFocusChangeListener(this);
        mSearchRlBacksign = (RelativeLayout) v.findViewById(R.id.search_rl_backsign);
        return v;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        if(v.getId() == R.id.search_edit_search)
        {
            if(hasFocus)
            {
                mSearchRlBacksign.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.search_btn_backsign :
                mSearchRlBacksign.setVisibility(View.GONE);
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mSearchEditSearch.getWindowToken(), 0);
                mSearchEditSearch.clearFocus();

                break;
        }

    }

}
