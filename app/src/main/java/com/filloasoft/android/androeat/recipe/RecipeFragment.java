package com.filloasoft.android.androeat.recipe;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.filloasoft.android.androeat.R;
import com.filloasoft.android.androeat.model.Recipe;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class RecipeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private Recipe recipe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);

        Bundle bundle = getArguments();
        recipe = (Recipe) bundle.getSerializable("recipe");

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Details"));
        tabLayout.addTab(tabLayout.newTab().setText("How to"));
        tabLayout.setTabTextColors(Color.GRAY, Color.WHITE);

        final TextView recipeName = (TextView) view.findViewById(R.id.titleRecipeName);
        recipeName.setText(recipe.getRecipeName());

        final ImageView recipeImage = (ImageView) view.findViewById(R.id.titleRecipeImage);
        recipeImage.setImageBitmap(recipe.getRecipeBitmapImage());

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.my_view_pager);

        viewPager.setAdapter(new PagerAdapter
                (getFragmentManager(), tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }



        @Override
        public Fragment getItem(int position) {
            Fragment tabFragment = null;

            switch (position){
                case 0:
                    tabFragment = new RecipeDetailsFragment();
                    break;
                case 1:
                    tabFragment = new HowToFragment();
                    break;
            }
            Bundle args = new Bundle();
            args.putSerializable("recipe", recipe);
            tabFragment.setArguments(args);
            return tabFragment;
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }
}