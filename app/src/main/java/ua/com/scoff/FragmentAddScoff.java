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
public class FragmentAddScoff extends Fragment {

    RecyclerView scoffList;
    ScoffAdapter scoffAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_scoff, container, false);

        scoffList = (RecyclerView) rootView.findViewById(R.id.scoffList);
        scoffList.addItemDecoration(new DividerItemDecoration(getActivity(), null, true, true));
        float offsetPx = getResources().getDimension(R.dimen.bottom_offset_dp);
        StaticUtils.BottomOffsetDecoration bottomOffsetDecoration = new StaticUtils.BottomOffsetDecoration((int)offsetPx);
        scoffList.addItemDecoration(bottomOffsetDecoration);
        scoffList.setLayoutManager(new LinearLayoutManager(getActivity()));
        scoffAdapter = new ScoffAdapter(getActivity());
        scoffList.setAdapter(scoffAdapter);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab_add_scoff);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return rootView;
    }
}
