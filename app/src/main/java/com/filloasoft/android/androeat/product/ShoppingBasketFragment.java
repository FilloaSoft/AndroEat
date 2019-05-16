package com.filloasoft.android.androeat.product;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;
import com.filloasoft.android.androeat.MainActivity;
import com.filloasoft.android.androeat.R;
import com.filloasoft.android.androeat.model.ProductListView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.content.Context.VIBRATOR_SERVICE;

public class ShoppingBasketFragment extends Fragment {

    private RecyclerView recyclerView;
    private ShoppingBasketListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View basketView = (View) inflater.inflate(R.layout.fragment_basket, container , false);


        recyclerView = basketView.findViewById(R.id.basketList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        this.mAdapter = ((MainActivity) getActivity()).getListAdapter();
        recyclerView.setAdapter(mAdapter);


        final FloatingActionsMenu menuFab = basketView.findViewById(R.id.menu_fab);

        final Button findButtom = basketView.findViewById(R.id.findButtonBasket);
        // Do something in response to button
//        toast.show();
        enableSwipeToDeleteAndUndo();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if ( dy > 5 ) {
                    // same height to same time translation
                    menuFab.collapse();
                    findButtom.animate().translationY(findButtom.getHeight() * 2);
                    menuFab.animate().translationY(findButtom.getHeight() * 2);
                } else if (dy < -10 ) {
                    findButtom.animate().translationY(0);
                    menuFab.animate().translationY(0);
                }
            }
        });

        final FloatingActionButton add = basketView.findViewById(R.id.accion_buscar);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                View layout = inflater.inflate(R.layout.popup_product, null);
                final PopupWindow pw;
                // create a 300px width and 470px height PopupWindow
                pw = new PopupWindow(layout);
                pw.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
                pw.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
                pw.setFocusable(true);

                final View containerView = getActivity().findViewById(R.id.container_main);
                final Animation fadeOut = new AlphaAnimation(1, 0.3f);

                fadeOut.setInterpolator(new AccelerateInterpolator());
                fadeOut.setFillAfter(true);
                pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                    containerView.clearAnimation();
                    }
                });

                containerView.startAnimation(fadeOut);
                pw.setAnimationStyle(android.R.style.Animation_Dialog);
                pw.showAtLocation(layout, Gravity.CENTER, 0, 5);

                Button inputButtom = pw.getContentView().findViewById(R.id.input_product_add_buttom);
                final EditText inputProductDescr = pw.getContentView().findViewById(R.id.input_product_description);
                final EditText inputProductName = pw.getContentView().findViewById(R.id.input_product_name);

                inputButtom.setOnClickListener( new View.OnClickListener()
                        {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            public void onClick(View view) {
                            if (inputProductName.getText().length() == 0 ){
                                if (ContextCompat.checkSelfPermission(getContext(),
                                        Manifest.permission.VIBRATE)
                                        == PackageManager.PERMISSION_GRANTED) {
                                    Vibrator vibrator = (Vibrator) getContext().getSystemService(VIBRATOR_SERVICE);
                                    vibrator.vibrate(VibrationEffect.createWaveform(new long[]{0, 100, 50, 100}, -1));
                                    ObjectAnimator
                                            .ofFloat(pw.getContentView(), "translationX", 0, 25, -25, 25, -25, 15, -15, 6, -6, 0)
                                            .setDuration(100)
                                            .start();
                                    inputProductName.requestFocus();
                                    inputProductName.getShowSoftInputOnFocus();
                                } else {
                                    Toast toast = Toast.makeText(getContext(), "Name required!", Toast.LENGTH_SHORT);
                                    toast.show();
                                    ObjectAnimator
                                            .ofFloat(pw.getContentView(), "translationX", 0, 25, -25, 25, -25, 15, -15, 6, -6, 0)
                                            .setDuration(100)
                                            .start();
                                    inputProductName.requestFocus();
                                    inputProductName.getShowSoftInputOnFocus();
                                }
                            }else {
                                mAdapter.addItem(new ProductListView(inputProductName.getText().toString(),inputProductDescr.getText().toString(), null, null, null));
                                pw.dismiss();
                            }
                            }
                        });

            }
        });

        return basketView;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
////        mAdapter.onResume();
//    }

    private void enableSwipeToDeleteAndUndo() {
        SwipeToDelete swipeToDeleteCallback = new SwipeToDelete(this.getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final ProductListView item = mAdapter.getData().get(position);

                mAdapter.removeItem(position);


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

    public List<String> getRecipes(){
        List<String> markedList = new ArrayList<>();
        markedList.clear();
        Map<Integer, Boolean> checked = mAdapter.getCheckedList();


        for (int i = 0; i < mAdapter.getItemCount() ; i++) {
            ProductListView product = (ProductListView) mAdapter.getCheckedItemAtPosition(i);
            if (checked.containsKey(i)) {
                if (checked.get(i)){
                    if(product.productName.contains(" ")){
                        String firstWord = product.productName.replace(" ", "+");
                        markedList.add(firstWord);
                    }else{
                        markedList.add(product.getProductName());
                    }
                }
            }
        }

        return markedList;
    }
}

