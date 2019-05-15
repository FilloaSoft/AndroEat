package com.filloasoft.android.androeat.product;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.filloasoft.android.androeat.MainActivity;
import com.filloasoft.android.androeat.R;
import com.filloasoft.android.androeat.model.Recipe;
import com.filloasoft.android.androeat.recipe.FavouriteListAdapter;

public class FavouriteFragment extends Fragment {

    private RecyclerView recyclerView;
    private FavouriteListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View favouritesView = (View) inflater.inflate(R.layout.fragment_favourites, container , false);


        recyclerView = favouritesView.findViewById(R.id.recycler_favourites);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        this.mAdapter = ((MainActivity) getActivity()).getFavouritesAdapter();
        recyclerView.setAdapter(mAdapter);


        // Do something in response to button
//        toast.show();
        enableSwipeToDeleteAndUndo();

        return favouritesView;
    }


    private void enableSwipeToDeleteAndUndo() {
        SwipeToDelete swipeToDeleteCallback = new SwipeToDelete(this.getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final Recipe item = mAdapter.getData().get(position);

                mAdapter.removeItemByPosition(position);


                Snackbar snackbar = Snackbar
                        .make(getView(), "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mAdapter.restoreItem(item, position);
                        recyclerView.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(getResources().getColor(R.color.colorWhite));
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }

}

