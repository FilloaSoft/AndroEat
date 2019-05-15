package com.filloasoft.android.androeat.recipe;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.filloasoft.android.androeat.R;
import com.filloasoft.android.androeat.model.Recipe;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class RecipeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private Recipe recipe;
    private OnRecipeFavouriteListener callback;
    private onGetRecipeFavouriteListener callbackFav;
    private Boolean isRecipeFavourite;

    public void setOnRecipeFavouriteListener(OnRecipeFavouriteListener callback, onGetRecipeFavouriteListener callbackFav) {
        this.callback = callback;
        this.callbackFav = callbackFav;
    }

    public interface OnRecipeFavouriteListener{
        void onFavouriteClicked(Recipe recipe, Boolean addFavourite);
    }

    public interface onGetRecipeFavouriteListener{
        Boolean onRecipeFavourite(Recipe recipe);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_recipe, container, false);

        Bundle bundle = getArguments();
        recipe = (Recipe) bundle.getSerializable("recipe");
        final FloatingActionButton favouriteButton = view.findViewById(R.id.favButton);
        isRecipeFavourite = callbackFav.onRecipeFavourite(recipe);

        int color = Color.parseColor("#FAF9F8");
        if (isRecipeFavourite){
            color = Color.parseColor("#ff0000");
        }
//        favouriteButton.setBackgroundTintList(ColorStateList.valueOf(color));
        favouriteButton.setImageTintList(ColorStateList.valueOf(color));

        favouriteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int color;
                isRecipeFavourite = callbackFav.onRecipeFavourite(recipe);

                if (isRecipeFavourite){
                    color = Color.parseColor("#FAF9F8");
                    callback.onFavouriteClicked(recipe, false);
                    favouriteButton.setImageTintList(ColorStateList.valueOf(color));
                }
                else{
                    color = Color.parseColor("#ff0000");
                    callback.onFavouriteClicked(recipe, true);
                    favouriteButton.setImageTintList(ColorStateList.valueOf(color));
                }
                favouriteButton.setImageTintList(ColorStateList.valueOf(color));
//                favouriteButton.setBackgroundTintList(ColorStateList.valueOf(color));
            }
        });


        final FloatingActionButton shareButton = view.findViewById(R.id.shareButton);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareCompat.IntentBuilder.from(getActivity())
                        .setType("text/plain")
                        .setChooserTitle("Share Recipe")
                        .setText(recipe.getRecipeURL())
                        .startChooser();
            }
        });

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Details"));
        tabLayout.addTab(tabLayout.newTab().setText("Ingredients"));
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
                    tabFragment = new IngredientsFragment();
                    break;
                case 2:
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