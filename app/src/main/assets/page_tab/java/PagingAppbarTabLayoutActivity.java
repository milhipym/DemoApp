package com.db.demoapp.ui.pagingitems;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.db.demoapp.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PagingAppbarTabLayoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paging_appbar_tabayout);

        ImageButton fab = findViewById(R.id.fabCode);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, com.db.demoapp.code.DynamicTabbedCodeViewActivity.class);
            intent.putExtra("feature", "slide_down");
            startActivity(intent);
        });

        // 탭 + ViewPager 연결
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        viewPager.setAdapter(new SimplePagerAdapter(this));
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) ->
                tab.setText("탭 " + (position + 1))
        ).attach();
    }
    class SimplePagerAdapter extends FragmentStateAdapter {
        public SimplePagerAdapter(FragmentActivity activity) {
            super(activity);
        }

        @Override
        public Fragment createFragment(int position) {
            Fragment fragment = new Fragment();
            return fragment;
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }

}
