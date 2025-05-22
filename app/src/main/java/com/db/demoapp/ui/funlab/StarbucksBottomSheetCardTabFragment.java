package com.db.demoapp.ui.funlab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ImageView;
import android.widget.TextView;

import com.db.demoapp.R;

import java.util.Arrays;
import java.util.List;

public class StarbucksBottomSheetCardTabFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.starbuks_pay_screen_cardtab, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.cardRecyclerView);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );

        // 카드 데이터 예시
        List<CardItem> cardList = Arrays.asList(
                new CardItem(R.drawable.starbucks_card1, "Starbucks Card 1", "대표카드"),
                new CardItem(R.drawable.starbucks_card2, "Starbucks Card 2", "신규혜택"),
                new CardItem(R.drawable.starbucks_card3, "Starbucks Card 3", "적립 UP"),
                new CardItem(R.drawable.starbucks_card4, "Starbucks Card 4", "골드회원"),
                new CardItem(R.drawable.starbucks_card2, "Starbucks Card 5", "생일쿠폰")
        );
        CardAdapter adapter = new CardAdapter(cardList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    // 카드 데이터 모델
    static class CardItem {
        int imgRes;
        String title;
        String desc;

        CardItem(int imgRes, String title, String desc) {
            this.imgRes = imgRes;
            this.title = title;
            this.desc = desc;
        }
    }

    // 카드 어댑터
    static class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

        private final List<CardItem> data;

        CardAdapter(List<CardItem> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.starbuks_pay_screen_cardtab_item, parent, false);
            return new CardViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
            CardItem item = data.get(position);
            holder.imgCard.setImageResource(item.imgRes);
            holder.txtTitle.setText(item.title);
            holder.txtDesc.setText(item.desc);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        static class CardViewHolder extends RecyclerView.ViewHolder {
            ImageView imgCard;
            TextView txtTitle, txtDesc;

            CardViewHolder(@NonNull View itemView) {
                super(itemView);
                imgCard = itemView.findViewById(R.id.imgCard);
                txtTitle = itemView.findViewById(R.id.txtTitle);
                txtDesc = itemView.findViewById(R.id.txtDesc);
            }
        }
    }
}
