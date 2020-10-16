package com.example.tp_homework.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_homework.R;

public class FragmentMain extends Fragment implements Adapter.OnNumberListener {

    private static final String COUNT_OF_NUMBERS = "count_of_numbers" ;
    private static final int COUNT_OF_COLUMNS_PORTRAIT = 3;
    private static final int COUNT_OF_COLUMNS_LANDSCAPE = 4;
    private Adapter adapter;
    private int count;

    @Override
    public void onNumberClick(int pos) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentMain, FragmentOfNumber.newInstance(pos + 1))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        count = (savedInstanceState != null) ? savedInstanceState.getInt(COUNT_OF_NUMBERS) : 100;
        adapter = new Adapter(count, this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView list = view.findViewById(R.id.list);
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            list.setLayoutManager(new GridLayoutManager(getContext(), COUNT_OF_COLUMNS_PORTRAIT));
        }
        else {
            list.setLayoutManager(new GridLayoutManager(getContext(), COUNT_OF_COLUMNS_LANDSCAPE));
        }
        list.setAdapter(adapter);
        Button addNumber = view.findViewById(R.id.addition_element);
        addNumber.setOnClickListener (v -> {
            count++;
            adapter.additionNumber();
            adapter.notifyItemInserted(count - 1);
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(COUNT_OF_NUMBERS, count);
    }
}