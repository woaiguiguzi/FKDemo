package com.kwt.uishine.slidingmenu;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kwt.uishine.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QQMenuFragment extends Fragment implements View.OnClickListener {

    View currentView;
    Button btn1,btn2,btn3,btn4,btn5,btn6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        currentView = inflater.inflate(R.layout.qq_menu_fragment, container, false);
        btn1 = (Button) currentView.findViewById(R.id.bt1);
        btn2 = (Button) currentView.findViewById(R.id.bt2);
        btn3 = (Button) currentView.findViewById(R.id.bt3);
        btn4 = (Button) currentView.findViewById(R.id.bt4);
        btn5 = (Button) currentView.findViewById(R.id.bt5);
        btn6 = (Button) currentView.findViewById(R.id.bt6);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        return currentView;
    }

    public View getCurrentView() {
        return currentView;
    }
    @Override
    public void onClick(View v) {
        ((QQSlidingActivity) getActivity()).getSlidingPaneLayout().closePane();
    }
}
