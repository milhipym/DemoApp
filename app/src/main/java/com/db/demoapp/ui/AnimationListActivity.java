package com.db.demoapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.db.demoapp.R;

public class AnimationListActivity extends AppCompatActivity {

    String[] animationItemsTitle = {"Micro Interaction", "Skeleton UI", "Infinite Scroll", "Modal UI"};
    String[] animationItemsSubTitle = {"사용자 행동에 반응하는 작고 스마트한 인터랙션", "Skeleton UI", "Infinite Scroll", "Modal UI"};
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

        ListView listView = findViewById(R.id.animationList);
        listView.setAdapter(new AnimationAdapter());

        listView.setOnItemClickListener((parent, view, position, id) -> {
            // SlideDownActivity만 예시로 연결
            Intent intent = new Intent(this, SlideDownActivity.class);
            startActivity(intent);
        });
    }

    class AnimationAdapter extends BaseAdapter {
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
                convertView = LayoutInflater.from(AnimationListActivity.this).inflate(R.layout.item_animation, parent, false);
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
