package com.kwt.uishine;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.kwt.uishine.dummy.BookContent;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BookDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BookDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookDetailFragment extends Fragment {
    public static final String ITEM_ID = "item_id";
    // 保存该Fragment显示的Book对象
    BookContent.Book book;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 如果启动该Fragment时包含了ITEM_ID参数
        if (getArguments().containsKey(ITEM_ID)) {
            book = BookContent.ITEM_MAP.get(getArguments()
                    .getInt(ITEM_ID)); // ①
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_book_detail, container, false);
        if (book != null)
        {
            // 让book_title文本框显示book对象的title属性
            ((TextView) rootView.findViewById(R.id.book_title))
                    .setText(book.title);
            // 让book_desc文本框显示book对象的desc属性
            ((TextView) rootView.findViewById(R.id.book_desc))
                    .setText(book.desc);
        }
        return rootView;
    }


}
