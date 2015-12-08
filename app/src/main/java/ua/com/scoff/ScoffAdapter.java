package ua.com.scoff;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by oleh on 12/3/15.
 */
public class ScoffAdapter extends RecyclerView.Adapter<ScoffAdapter.ScoffViewHolder> {

    private LayoutInflater inflater;
    Context context;
    protected ArrayList<String> recordIDsList = new ArrayList<>();
    protected ArrayList<String> denominationsList = new ArrayList<>();
    protected ArrayList<String> proteinsList = new ArrayList<>();
    protected ArrayList<String> fatsList = new ArrayList<>();
    protected ArrayList<String> carbohydratesList = new ArrayList<>();
    protected ArrayList<String> caloriesList = new ArrayList<>();
    protected ArrayList<Integer> frequenciesList = new ArrayList<>();
    protected ArrayList<Integer> weightsList = new ArrayList<>();

    public ScoffAdapter(Context context){
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ScoffViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.scoff_row, parent, false);
        ScoffViewHolder holder = new ScoffViewHolder(view);



        return holder;
    }

    @Override
    public void onBindViewHolder(ScoffViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }


    class ScoffViewHolder extends RecyclerView.ViewHolder{

        TextView recordId, denomination, proteins, fats, carbohydrates, weight, calories;

        public ScoffViewHolder(View itemView) {
            super(itemView);
            recordId = (TextView) itemView.findViewById(R.id.recordId);
            proteins = (TextView) itemView.findViewById(R.id.scoff_proteins);
            fats = (TextView) itemView.findViewById(R.id.scoff_fats);
            carbohydrates = (TextView) itemView.findViewById(R.id.scoff_carbohydrates);
            weight = (TextView) itemView.findViewById(R.id.scoff_weight);
            calories = (TextView) itemView.findViewById(R.id.scoff_calories);
            denomination = (TextView) itemView.findViewById(R.id.scoff_denomination);
        }
    }
}
