package com.db.demoapp.ui.pagingitems;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PagingBottomFragment extends Fragment {
    private static final String ARG_TEXT = "label";

    public static PagingBottomFragment newInstance(String text) {
        PagingBottomFragment fragment = new PagingBottomFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getContext());
        textView.setText(getArguments().getString(ARG_TEXT));
        textView.setTextSize(24f);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
