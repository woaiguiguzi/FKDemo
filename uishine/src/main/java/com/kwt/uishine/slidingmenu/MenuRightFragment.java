package com.kwt.uishine.slidingmenu;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kwt.uishine.R;

public class MenuRightFragment extends Fragment {

    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        if(mView == null)
        {
            mView = inflater.inflate(R.layout.fragment_menu_right, container, false);
        }
        return mView ;
    }

}
