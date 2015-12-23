package ua.com.scoff;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import serving.DividerItemDecoration;

/**
 * Created by oleh on 12/3/15.
 */
public class FragmentAddScoff extends Fragment {

    View rootView;
    protected RecyclerView scoffList;
    protected ScoffAdapter scoffAdapter;
    protected TextView totalProteins, totalFats, totalCarbohydrates, totalCalories;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_add_scoff, container, false);

        totalProteins = (TextView) rootView.findViewById(R.id.totalProteins);
        totalFats = (TextView) rootView.findViewById(R.id.totalFats);
        totalCarbohydrates = (TextView) rootView.findViewById(R.id.totalCarbohydrates);
        totalCalories = (TextView) rootView.findViewById(R.id.totalCalories);

        scoffList = (RecyclerView) rootView.findViewById(R.id.scoffList);
        scoffList.addItemDecoration(new DividerItemDecoration(getActivity(), null, true, true));
        scoffList.setLayoutManager(new LinearLayoutManager(getActivity()));
        scoffAdapter = new ScoffAdapter(getActivity(), Integer.parseInt(((AddScoffActivity) getActivity()).spanId));

        displayTotals();

        scoffList.setAdapter(scoffAdapter);

        return rootView;
    }

    protected String[] getSums(){
        float protsSum =0;
        float fatsSum =0;
        float carbsSum =0;
        float caloriesSum =0;
        for (int i=0; i< scoffAdapter.proteinsList.size(); i++) {
            protsSum += Float.parseFloat(scoffAdapter.proteinsList.get(i));
            fatsSum += Float.parseFloat(scoffAdapter.fatsList.get(i));
            carbsSum += Float.parseFloat(scoffAdapter.carbohydratesList.get(i));
            caloriesSum += Float.parseFloat(scoffAdapter.caloriesList.get(i));
        }

        String[] result = {String.valueOf((int)protsSum), String.valueOf((int)fatsSum), String.valueOf((int)carbsSum), String.valueOf((int)caloriesSum)};
        return result;
    }

    protected void displayTotals(){
//        String[] sums = ((AddScoffActivity)getActivity()).databaseAdapter.getSums(Integer.parseInt(((AddScoffActivity) getActivity()).spanId));
        String[] sums = getSums();
        totalProteins.setText(sums[0]);
        totalFats.setText(sums[1]);
        totalCarbohydrates.setText(sums[2]);
        totalCalories.setText(sums[3]);
    }
}
