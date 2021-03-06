package com.minglang.suiuu.fragment.remind;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.minglang.suiuu.R;

/**
 *
 * 新评论页面
 *
 * A simple {@link Fragment} subclass.
 * Use the {@link NewCommentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewCommentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListView newCommentList;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewCommentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewCommentFragment newInstance(String param1, String param2) {
        NewCommentFragment fragment = new NewCommentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public NewCommentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_new_comment, container, false);

        initView(rootView);

        return rootView;
    }

    private void initView(View rootView){
        newCommentList = (ListView) rootView.findViewById(R.id.newCommentList);
    }

}
