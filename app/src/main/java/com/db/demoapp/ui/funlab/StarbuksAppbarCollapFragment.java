package com.db.demoapp.ui.funlab;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.db.demoapp.R;

public class StarbuksAppbarCollapFragment extends Fragment {

    private static final String ARG_TEXT = "label";

    public static StarbuksAppbarCollapFragment newInstance(String text) {
        StarbuksAppbarCollapFragment fragment = new StarbuksAppbarCollapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String label = getArguments() != null ? getArguments().getString(ARG_TEXT) : "";

        if ("home".equals(label)) {
            return inflater.inflate(R.layout.starbuks_home_screen, container, false);
        } else {
            TextView textView = new TextView(getContext());
            textView.setText(label);
            textView.setTextSize(24f);
            textView.setGravity(Gravity.CENTER);
            return textView;
        }
    }
}
