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

public class StarbuksCardViewPageIndicatorOrderAdapter extends RecyclerView.Adapter<StarbuksCardViewPageIndicatorOrderAdapter.ViewHolder>{

    private final List<DrinkItem> drinkItems;

    public StarbuksCardViewPageIndicatorOrderAdapter(List<StarbuksCardViewPageIndicatorOrderAdapter.DrinkItem> drinkItems) {
        this.drinkItems = drinkItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.starbuks_home_screen_cardview_order_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(drinkItems.get(position));
    }

    @Override
    public int getItemCount() {
        return drinkItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCoffee;
        TextView txtCoffeeTitle;
        TextView txtCoffeeSub;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCoffee = itemView.findViewById(R.id.imgCoffee);
            txtCoffeeTitle = itemView.findViewById(R.id.txtCoffeName);
            txtCoffeeSub = itemView.findViewById(R.id.txtCoffeSub);
        }

        public void bind(DrinkItem item) {
            imgCoffee.setImageResource(item.getImageResId());
            txtCoffeeTitle.setText(item.getTitle());
            txtCoffeeSub.setText(item.getSub());
        }
    }

    // 데이터 모델 클래스
    public static class DrinkItem {
        private final int imageResId;
        private final String title;
        private final String sub;

        public DrinkItem(int imageResId, String title, String sub) {
            this.imageResId = imageResId;
            this.title = title;
            this.sub = sub;
        }

        public int getImageResId() {
            return imageResId;
        }

        public String getTitle() {
            return title;
        }

        public String getSub() {
            return sub;
        }
    }
}
