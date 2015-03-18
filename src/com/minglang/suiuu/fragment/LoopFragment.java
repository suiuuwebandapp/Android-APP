package com.minglang.suiuu.fragment;

import com.minglang.suiuu.R;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LoopFragment extends Fragment {

    private Button themeBtn, areaBtn;

    private FragmentManager fm;

    private ThemeFragment themeFragment;

    private AreaFragment areaFragment;

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_loop, null);

        initView(rootView);

        ViewAction();

        return rootView;
    }

    private void ViewAction() {
        themeBtn.setOnClickListener(new MyClick());
        areaBtn.setOnClickListener(new MyClick());
    }

    private void initView(View rootView) {

        themeBtn = (Button) rootView.findViewById(R.id.themeButton);
        areaBtn = (Button) rootView.findViewById(R.id.areaButton);

        fm = getFragmentManager();

        themeFragment = new ThemeFragment();
        areaFragment = new AreaFragment();

        LoadDefaultFragment();
    }

    private void LoadDefaultFragment() {
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.loadLayout, themeFragment);
        ft.commit();
    }


    class MyClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.themeButton:

                    FragmentTransaction ft1 = fm.beginTransaction();

                    if (areaFragment != null) {
                        if (areaFragment.isAdded()) {
                            ft1.hide(areaFragment);
                        }
                    }

                    if (themeFragment == null) {
                        themeFragment = new ThemeFragment();
                    }

                    if (themeFragment.isAdded()) {
                        ft1.show(themeFragment);
                    } else {
                        ft1.add(R.id.loadLayout, themeFragment);
                    }

                    ft1.commit();

                    break;
                case R.id.areaButton:

                    FragmentTransaction ft2 = fm.beginTransaction();

                    if (themeFragment != null) {
                        if (themeFragment.isAdded()) {
                            ft2.hide(themeFragment);
                        }
                    }

                    if (areaFragment == null) {
                        areaFragment = AreaFragment.newInstance("a", "b");
                    }

                    if (areaFragment.isAdded()) {
                        ft2.show(areaFragment);
                    } else {
                        ft2.add(R.id.loadLayout, areaFragment);
                    }

                    ft2.commit();

                    break;
            }
        }
    }
}
