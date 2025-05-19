package com.db.demoapp.ui.modal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.db.demoapp.R;
import com.db.demoapp.ui.modal.test.BottomSheetTestActivity;
import com.db.demoapp.ui.modal.test.ModalDialogTestActivity;
import com.db.demoapp.ui.modal.test.SnackbarTestActivity;

public class ModalUIActivity extends AppCompatActivity {

    String[] animationItemsTitle = {"모달 팝업", "바텀 시트", "스낵바"};
    String[] animationItemsSubTitle = {
            "중요한 정보전달/결정 필요",
            "기존 화면과 관련된 내용/메뉴 제공",
            "Action에 대한 피드백/별도 액션 제공"
    };
    int[] icons = {
            R.drawable.ic_micro_interaction,
            R.drawable.ic_micro_interaction,
            R.drawable.ic_micro_interaction,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modal_uiactivity);

        ListView listView = findViewById(R.id.animationList);
        listView.setAdapter(new AnimationAdapter());

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = null;
            switch (position) {
                case 0:
                    intent = new Intent(this, ModalDialogTestActivity.class);
                    break;
                case 1:
                    intent = new Intent(this, BottomSheetTestActivity.class);
                    break;
                case 2:
                    intent = new Intent(this, SnackbarTestActivity.class);
                    break;
            }
            if (intent != null) startActivity(intent);
        });
    }

    class AnimationAdapter extends BaseAdapter {
        @Override
        public int getCount() { return animationItemsTitle.length; }
        @Override
        public Object getItem(int position) { return animationItemsTitle[position]; }
        @Override
        public long getItemId(int position) { return position; }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(ModalUIActivity.this).inflate(R.layout.item_animation, parent, false);
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
