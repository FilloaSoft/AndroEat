package com.filloasoft.android.androeat.recipe;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.filloasoft.android.androeat.R;
import com.filloasoft.android.androeat.model.Recipe;

import java.util.ArrayList;

public class FavouriteListAdapter extends RecyclerView.Adapter<FavouriteListAdapter.MyViewHolder> {
    private static ArrayList<Recipe> mDatasetFavourites = new ArrayList<>();
//    private Map<Integer, Boolean> checkedList = new HashMap<>();
    private OnItemRecipeClickedListener mItemClickListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public RelativeLayout listView;
        public MyViewHolder(RelativeLayout v) {
            super(v);
            listView = v;
        }
    }


    public FavouriteListAdapter() {
    }

    @Override
    public FavouriteListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favourites_list_row, parent, false);

        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }

    public void setOnItemClickedListener(OnItemRecipeClickedListener l) {
        mItemClickListener = l;
    }

    public interface OnItemRecipeClickedListener {
        void onItemRecipeClicked(Recipe recipe);
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        holder.listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recipe product = mDatasetFavourites.get(position);

                mItemClickListener.onItemRecipeClicked(product);
            }
        });

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        TextView textView = holder.listView.findViewById(R.id.productName);
        ImageView imageView = holder.listView.findViewById(R.id.productImage);


        textView.setText(mDatasetFavourites.get(position).getRecipeName());

        if (mDatasetFavourites.get(position).getRecipeBitmapImage() != null){
            imageView.setImageBitmap(mDatasetFavourites.get(position).getRecipeBitmapImage());
        }
    }

    Recipe getCheckedItemAtPosition(int position){
        return mDatasetFavourites.get(position);
    }

    public void removeItemByPosition(int position) {
        mDatasetFavourites.remove(position);
        notifyItemRemoved(position);
    }

    public void removeItemByRecipe(Recipe recipe){
        Recipe r = existsRecipe(recipe);
        if (r != null) {
            mDatasetFavourites.remove(r);
            notifyDataSetChanged();
        }
    }

    public void addItem(Recipe item) {
        if (existsRecipe(item) == null){
            mDatasetFavourites.add(item);
            notifyItemInserted(mDatasetFavourites.size());
            notifyDataSetChanged();
        }
    }

    public void restoreItem(Recipe item, int position) {
        mDatasetFavourites.add(position, item);
        notifyItemInserted(position);
    }

    public Boolean isRecipeFavourite(Recipe recipe){
        return existsRecipe(recipe) != null;
    }

    public ArrayList<Recipe> getData() {
        return mDatasetFavourites;
    }

    public Recipe existsRecipe(Object other){
        if (other == null) return null;
        if (other == this) return null;
        if (!(other instanceof Recipe))return null;
        Recipe recipeFav = (Recipe) other;
        for (Recipe r: mDatasetFavourites) {
            if (r.getRecipeID().equals(recipeFav.getRecipeID())){
                return r;
            }
        }
        return null;
    }



//    Map<Integer, Boolean> getCheckedList(){
//        return checkedList;
//    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDatasetFavourites.size();
    }



}