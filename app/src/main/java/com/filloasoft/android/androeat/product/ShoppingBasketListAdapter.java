package com.filloasoft.android.androeat.product;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.filloasoft.android.androeat.R;
import com.filloasoft.android.androeat.model.ProductListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShoppingBasketListAdapter extends RecyclerView.Adapter<ShoppingBasketListAdapter.MyViewHolder> {
    private static ArrayList<ProductListView> mDataset = new ArrayList<>();
    private Map<Integer, Boolean> checkedList = new HashMap<>();

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


    public ShoppingBasketListAdapter() {
    }

    @Override
    public ShoppingBasketListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_row, parent, false);

        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }

    private OnItemClickedListener mItemClickListener;

    public void setOnItemClickedListener(OnItemClickedListener l) {
        mItemClickListener = l;
    }

    public interface OnItemClickedListener {
        void onItemClicked(ProductListView productListView);
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        holder.listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductListView product = mDataset.get(position);

                mItemClickListener.onItemClicked(product);
            }
        });

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        TextView textView = holder.listView.findViewById(R.id.productName);
        TextView textViewDescr = holder.listView.findViewById(R.id.productDescription);
        ImageView imageView = holder.listView.findViewById(R.id.productImage);
        CheckBox checkBoxView = holder.listView.findViewById(R.id.checkBox);

        checkBoxView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkedList.put(position, true);
                }else{
                    checkedList.put(position, false);
                }

            }
        });

        textView.setText(mDataset.get(position).getProductName());
        textViewDescr.setText(mDataset.get(position).getProductDescription());
        imageView.setImageBitmap(mDataset.get(position).getProductImage());
    }

        ProductListView getCheckedItemAtPosition(int position){
            return mDataset.get(position);
        }

        void removeItem(int position) {
            mDataset.remove(position);
            notifyItemRemoved(position);
        }

        void addItem(ProductListView item) {
            mDataset.add(item);
            notifyItemInserted(mDataset.size());
            notifyDataSetChanged();
        }

        void restoreItem(ProductListView item, int position) {
            mDataset.add(position, item);
            notifyItemInserted(position);
        }

        ArrayList<ProductListView> getData() {
            return mDataset;
        }

        Map<Integer, Boolean> getCheckedList(){
            return checkedList;
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }



}