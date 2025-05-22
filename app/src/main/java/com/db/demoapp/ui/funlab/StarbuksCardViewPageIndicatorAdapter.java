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

public class StarbuksCardViewPageIndicatorAdapter extends RecyclerView.Adapter<StarbuksCardViewPageIndicatorAdapter.CardViewHolder> {
    private final List<Integer> imageList;
    private final List<String> indicatorList;

    public StarbuksCardViewPageIndicatorAdapter(List<Integer> imageList, List<String> indicatorList) {
        this.imageList = imageList;
        this.indicatorList = indicatorList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.starbuks_home_screen_cardview_indicate_items, parent, false);
        return new CardViewHolder(view);
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

