package com.example.tp_homework.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tp_homework.R;

public class FragmentOfNumber extends Fragment {

    private static final String POSITION = "position";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_of_number, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        String number = "no value supplied";
        Bundle arguments = getArguments();
        if (arguments != null) {
            number = arguments.getString(POSITION);
        }
        TextView myView = view.findViewById(R.id.display_number);
        myView.setText(number);
        myView.setTextColor(Integer.parseInt(number) % 2 == 1 ? getActivity().getResources().getColor(R.color.blue) : getActivity().getResources().getColor(R.color.red));
    }

    public static FragmentOfNumber newInstance(int param) {
        FragmentOfNumber fragment = new FragmentOfNumber();
        Bundle bundle = new Bundle();
        bundle.putString(POSITION, String.valueOf(param));
        fragment.setArguments(bundle);
        return fragment;
    }
}