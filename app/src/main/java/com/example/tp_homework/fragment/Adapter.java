package com.example.tp_homework.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_homework.R;

public class Adapter extends RecyclerView.Adapter<Adapter.MyHolder> {
    private int countOfNumbers;
    private final OnNumberListener numberListener;
    private final int colorIfNumberEven;
    private final int colorIfNumberOdd;

    public Adapter(int count, OnNumberListener onNumberListener, int colorBlue, int colorRed) {
        this.countOfNumbers = count;
        this.numberListener = onNumberListener;
        this.colorIfNumberEven = colorBlue;
        this.colorIfNumberOdd = colorRed;
    }

    public void additionNumber() {
        countOfNumbers++;
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
        holder.text.setTextColor(position % 2 == 1 ? this.colorIfNumberOdd : this.colorIfNumberEven);
    }

    @Override
    public int getItemCount() {
        return countOfNumbers;
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
