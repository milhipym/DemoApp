package com.db.demoapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.db.demoapp.R;
import com.db.demoapp.ui.microitems.SlideDownActivity;
import com.db.demoapp.ui.pagingitems.PagingAppbarTabLayoutActivity;
import com.db.demoapp.ui.pagingitems.PagingBottomActivity;

public class PagingUXListActivity extends AppCompatActivity {
    String[] animationItemsTitle = {"Micro Interaction", "Skeleton UI", "Infinite Scroll", "Modal UI"};
    String[] animationItemsSubTitle = {"사용자 액션에 대한 반응형 애니메이션",
            "콘텐츠 로딩시간의 체감상 감소를 위한 UI",
            "한 페이지에서 많은 컨텐츠를 담기 위한 UI",
            "사용자에게 효과적으로 정보 선택을 제공하는 UI"};
    int[] icons = {
            R.drawable.ic_micro_interaction,
            R.drawable.ic_skeleton_ui,
            R.drawable.ic_micro_interaction,
            R.drawable.ic_skeleton_ui
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_list);
        Log.e("YYYM", "PagingUXListActivity");
        ListView listView = findViewById(R.id.animationList);
        listView.setAdapter(new PagingUxAdapter());

        listView.setOnItemClickListener((parent, view, position, id) -> {

            if (position == 0) {
                Intent intent = new Intent(this, PagingAppbarTabLayoutActivity.class);
                startActivity(intent);
            } else if (position == 1) {
                Intent intent = new Intent(this, PagingBottomActivity.class);
                startActivity(intent);
            }
        });
    }

    class PagingUxAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return animationItemsTitle.length;
        }

        @Override
        public Object getItem(int position) {
            return animationItemsTitle[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(PagingUXListActivity.this).inflate(R.layout.item_animation, parent, false);
            }
            ImageView icon = convertView.findViewById(R.id.itemIcon);
            TextView title = convertView.findViewById(R.id.itemTitle);
            TextView subtitle = convertView.findViewById(R.id.itemSubTitle);

            icon.setImageResource(icons[position]);
            title.setText(animationItemsTitle[position]);
            subtitle.setText(animationItemsSubTitle[position]);
            return convertView;
        }
    }
}
