package com.example.tp_homework.fragment;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_homework.R;

public class Adapter extends RecyclerView.Adapter<Adapter.MyHolder> {
    private int count_of_numbers;
    private final OnNumberListener numberListener;

    public Adapter(int count, OnNumberListener onNumberListener) {
        this.count_of_numbers = count;
        this.numberListener = onNumberListener;
    }

    public void additionNumber() {
        count_of_numbers++;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        return new MyHolder(v, numberListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        String text = String.valueOf(position + 1);
        holder.text.setText(text);
        holder.text.setTextColor(position % 2 == 1 ? Color.RED : Color.BLUE);
    }

    @Override
    public int getItemCount() {
        return count_of_numbers;
    }

    public static class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView text;
        OnNumberListener onNumberListener;

        public MyHolder(@NonNull View itemView, OnNumberListener onNumberListener) {
            super(itemView);
            text = itemView.findViewById(R.id.number);
            itemView.setOnClickListener(this);
            this.onNumberListener = onNumberListener;
        }

        @Override
        public void onClick(View v) {
            onNumberListener.onNumberClick(getAdapterPosition());
        }
    }

    public interface OnNumberListener {
        void onNumberClick(int position);
    }
}
