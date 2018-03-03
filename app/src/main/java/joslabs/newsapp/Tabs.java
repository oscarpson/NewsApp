package joslabs.newsapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import joslabs.newsapp.all_news.all_News;
import joslabs.newsapp.only_tech.only_Technology;
import joslabs.newsapp.tech_allSources.all_SourceTech;

/**
 * Created by OSCAR on 4/24/2017.
 */

public class Tabs extends Fragment {
   public static ViewPager viewPager;
    public static  TabLayout tabs;
    public static int  number_tabs=3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.tab_layout,null);
        tabs = (TabLayout) view.findViewById(R.id.tabs);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabs.post(new Runnable() {
            @Override
            public void run() {
                tabs.setupWithViewPager(viewPager);
            }
        });

        return view;

    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 : return new all_News();
                case 1 : return new only_Technology();
                case 2 : return new all_SourceTech();





            }
            return null;
        }

        @Override
        public int getCount() {

            return number_tabs;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "News Article";
                case 1 :
                    return "Tech Articles";
                case 2:
                    return "Tech All Sources";






            }
            return null;
        }
    }

}
