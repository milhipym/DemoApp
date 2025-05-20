package com.db.demoapp.ui.etc;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.db.demoapp.R;
import com.db.demoapp.ui.etc.healthconnect.test.HealthConnectTestActivity;
import com.db.demoapp.ui.modal.test.BottomSheetTestActivity;
import com.db.demoapp.ui.modal.test.ModalDialogTestActivity;
import com.db.demoapp.ui.modal.test.SnackbarTestActivity;

public class EtcActivity extends AppCompatActivity {

    String[] animationItemsTitle = {"구글헬스커넥트"};
    String[] animationItemsSubTitle = {
            "걸음 수 측정하기 API"
    };
    int[] icons = {
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
                    intent = new Intent(this, HealthConnectTestActivity.class);
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
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_animation, parent, false);
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
