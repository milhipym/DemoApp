package com.db.demoapp.comm.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.db.demoapp.R;

import java.util.List;

public class CommonItemAdapter extends RecyclerView.Adapter<CommonItemAdapter.ViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    private final Context context;
    private final List<ItemData> items;
    private OnItemClickListener listener;

    public static class ItemData {
        public final int iconRes;
        public final String title;
        public final String subTitle;

        public ItemData(int iconRes, String title, String subTitle) {
            this.iconRes = iconRes;
            this.title = title;
            this.subTitle = subTitle;
        }
    }

    public CommonItemAdapter(Context context, List<ItemData> items) {
        this.context = context;
        this.items = items;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_animation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemData data = items.get(position);
        holder.icon.setImageResource(data.iconRes);
        holder.title.setText(data.title);
        holder.subtitle.setText(data.subTitle);
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onItemClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title, subtitle;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.itemIcon);
            title = itemView.findViewById(R.id.itemTitle);
            subtitle = itemView.findViewById(R.id.itemSubTitle);
        }
    }
}
