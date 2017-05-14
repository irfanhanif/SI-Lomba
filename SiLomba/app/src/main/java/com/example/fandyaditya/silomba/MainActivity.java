package com.example.fandyaditya.silomba;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.example.fandyaditya.silomba.Bimbingan.BimbinganFragment;
import com.example.fandyaditya.silomba.ListLomba.ListLombaFragment;
import com.example.fandyaditya.silomba.PengaturanTim.ListTim.PengaturanTimFragment;
import com.example.fandyaditya.silomba.Profile.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private String[] tabsTitles = {"Lomba","Tim","Bimbingan","Profil"};
    private int[] iconSelected = {R.drawable.lomba_black,R.drawable.tim_black,R.drawable.bimbingan_black,R.drawable.profile_black};
    private int[] iconNotSelected = {R.drawable.lomba_white,R.drawable.tim_white,R.drawable.bimbingan_white,R.drawable.profile_white};
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(opcl);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.getTabAt(0).setIcon(iconSelected[0]);
        tabLayout.getTabAt(1).setIcon(iconNotSelected[1]);
        tabLayout.getTabAt(2).setIcon(iconNotSelected[2]);
        tabLayout.getTabAt(3).setIcon(iconNotSelected[3]);
        setTitle("Lomba");
    }

    ViewPager.OnPageChangeListener opcl = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setTitle(tabsTitles[position]);

            if(position==0){
                tabLayout.getTabAt(0).setIcon(iconSelected[0]);
                tabLayout.getTabAt(1).setIcon(iconNotSelected[1]);
                tabLayout.getTabAt(2).setIcon(iconNotSelected[2]);
                tabLayout.getTabAt(3).setIcon(iconNotSelected[3]);
            }
            else if(position==1){
                tabLayout.getTabAt(0).setIcon(iconNotSelected[0]);
                tabLayout.getTabAt(1).setIcon(iconSelected[1]);
                tabLayout.getTabAt(2).setIcon(iconNotSelected[2]);
                tabLayout.getTabAt(3).setIcon(iconNotSelected[3]);
            }
            else if(position==2){
                tabLayout.getTabAt(0).setIcon(iconNotSelected[0]);
                tabLayout.getTabAt(2).setIcon(iconSelected[2]);
                tabLayout.getTabAt(3).setIcon(iconNotSelected[3]);
                tabLayout.getTabAt(1).setIcon(iconNotSelected[1]);
            }
            else if(position==3){
                tabLayout.getTabAt(0).setIcon(iconNotSelected[0]);
                tabLayout.getTabAt(3).setIcon(iconSelected[3]);
                tabLayout.getTabAt(1).setIcon(iconNotSelected[1]);
                tabLayout.getTabAt(2).setIcon(iconNotSelected[2]);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

//        private Bundle bundle;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
           switch (position){
               case 0 :
                   ListLombaFragment listLombaFragment = new ListLombaFragment();
//                   listLombaFragment.setArguments(bundle);
                   return listLombaFragment;
               case 1 :
                   PengaturanTimFragment pengaturanTim = new PengaturanTimFragment();
//                   pengaturanTim.setArguments(bundle);
                   return pengaturanTim;
               case 2 :
                   BimbinganFragment bimbinganFragment = new BimbinganFragment();
//                   bimbinganFragment.setArguments(bundle);
                   return bimbinganFragment;
               case 3:
                   ProfileFragment profileFragment = new ProfileFragment();
//                   profileFragment.setArguments(bundle);
                   return profileFragment;
               default:return null;
           }
        }


        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return null;
        }
    }
}
