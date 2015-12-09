package ua.com.scoff;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import serving.DividerItemDecoration;

/**
 * Created by oleh on 12/3/15.
 */
public class FragmentAddProducts extends Fragment {

    RecyclerView productsList;
    ProductsAdapter productsAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_products, container, false);

        productsList = (RecyclerView) rootView.findViewById(R.id.productsList);
        productsList.addItemDecoration(new DividerItemDecoration(getActivity(), null, true, true));
        float offsetPx = getResources().getDimension(R.dimen.bottom_offset_dp);
        StaticUtils.BottomOffsetDecoration bottomOffsetDecoration = new StaticUtils.BottomOffsetDecoration((int)offsetPx);
        productsList.addItemDecoration(bottomOffsetDecoration);
        productsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        productsAdapter = new ProductsAdapter(getActivity());

        productsList.setAdapter(productsAdapter);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab_add_product);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticUtils.showAddProductDialog(getActivity());
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

        return rootView;
    }
}
