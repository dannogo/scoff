package ua.com.scoff;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import serving.SlidingTabLayout;

/**
 * Created by oleh on 12/3/15.
 */
public class AddScoffActivity extends AppCompatActivity {

    private ViewPager pager;
    private SlidingTabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_scoff);


        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new ScoffPagerAdapter(getSupportFragmentManager()));
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout = (SlidingTabLayout) findViewById(R.id.tabLayout);
        tabLayout.setDistributeEvenly(true);

        tabLayout.setViewPager(pager);
    }

    class ScoffPagerAdapter extends FragmentPagerAdapter{

        String[] tabTitles;

        public ScoffPagerAdapter(FragmentManager fm) {
            super(fm);
            tabTitles = getResources().getStringArray(R.array.addScoffTabs);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0){
                fragment = new FragmentAddScoff();
            }else if (position == 1){
                fragment = new FragmentAddProducts();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
