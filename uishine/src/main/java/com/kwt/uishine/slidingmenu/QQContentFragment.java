package com.kwt.uishine.slidingmenu;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ListView;
import android.widget.Toast;

import com.kwt.uishine.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QQContentFragment extends Fragment {


    private View currentView;

    public void setCurrentViewPararms(LayoutParams layoutParams) {
        currentView.setLayoutParams(layoutParams);
    }

    public LayoutParams getCurrentViewParams() {
        return (LayoutParams) currentView.getLayoutParams();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        currentView = inflater.inflate(R.layout.qq_content_fragment, container, false);
        ListView listView = (ListView) currentView.findViewById(R.id.lvQQContent);
        listView.setAdapter(new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, new String[]{"qqqqqqqqq", "222222222222", "eeeeeeeeeeeeee"}));
        setItemClick(listView);

        return currentView;
    }

    private void setItemClick(ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "我被点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public View getCurrentView() {
        return currentView;
    }
}
