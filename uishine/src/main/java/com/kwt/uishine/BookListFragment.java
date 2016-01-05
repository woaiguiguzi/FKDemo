package com.kwt.uishine;

import android.app.Activity;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.kwt.uishine.dummy.BookContent;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class BookListFragment extends ListFragment {

    private Callbacks mCallbacks;
    public interface Callbacks
    {
        public void onItemSelected(Integer id);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 为该ListFragment设置Adapter
        setListAdapter(new ArrayAdapter<BookContent.Book>(getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1, BookContent.ITEMS));  //①

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // 如果Activity没有实现Callbacks接口，抛出异常
        if (!(activity instanceof Callbacks))
        {
            throw new IllegalStateException(
                    "BookListFragment所在的Activity必须实现Callbacks接口!");
        }
        // 把该Activity当成Callbacks对象
        mCallbacks = (Callbacks)activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // 将mCallbacks赋为null。
        mCallbacks = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // 激发mCallbacks的onItemSelected方法
        mCallbacks.onItemSelected(BookContent
                .ITEMS.get(position).id);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }
    public void setActivateOnItemClick(boolean activateOnItemClick)
    {
        getListView().setChoiceMode(
                activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
                        : ListView.CHOICE_MODE_NONE);
    }
}
