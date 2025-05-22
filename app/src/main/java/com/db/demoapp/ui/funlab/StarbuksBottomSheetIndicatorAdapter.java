package com.db.demoapp.ui.funlab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.db.demoapp.R;

import java.util.List;

public class StarbuksBottomSheetIndicatorAdapter extends RecyclerView.Adapter<StarbuksBottomSheetIndicatorAdapter.CardViewHolder> {
    private final List<Integer> imageList;
    private final List<String> indicatorList;

    public StarbuksBottomSheetIndicatorAdapter(List<Integer> imageList, List<String> indicatorList) {
        this.imageList = imageList;
        this.indicatorList = indicatorList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.starbuks_pay_screen_bottomsheet_indicate_items, parent, false);
        return new StarbuksBottomSheetIndicatorAdapter.CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.cardImage.setImageResource(imageList.get(position));
        holder.pageIndicator.setText(indicatorList.get(position));
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView cardImage;
        TextView pageIndicator;

        CardViewHolder(@NonNull View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.cardImage);
            pageIndicator = itemView.findViewById(R.id.pageIndicator);
        }
    }
}
