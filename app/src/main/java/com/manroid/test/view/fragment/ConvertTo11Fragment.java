package com.manroid.test.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.manroid.test.R;
import com.manroid.test.base.BaseFragment;
import com.manroid.test.task.ConvertTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConvertTo11Fragment extends BaseFragment {

    private Button btn;

    public ConvertTo11Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_convert_to11, container, false);
        btn = view.findViewById(R.id.btn_change_to_11);
        return view;
    }


    @Override
    public void initData() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ConvertTask(getActivity()).execute(false);
            }
        });

    }

}
