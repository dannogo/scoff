package ua.com.scoff;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    protected ArrayList<String> datetimesList = new ArrayList<>();
    protected ArrayList<String> denominationsList = new ArrayList<>();
    protected ArrayList<String> proteinsList = new ArrayList<>();
    protected ArrayList<String> fatsList = new ArrayList<>();
    protected ArrayList<String> carbohydratesList = new ArrayList<>();
    protected ArrayList<String> caloriesList = new ArrayList<>();
    protected ArrayList<Integer> weightsList = new ArrayList<>();

    public ScoffAdapter(Context context, int spanId){
        inflater = LayoutInflater.from(context);
        this.context = context;
        ArrayList<String[]> data = ((AddScoffActivity) this.context).databaseAdapter.getScoffRecordsData(spanId);
        if (data != null){
            for (int i=0; i<data.size(); i++){
                recordIDsList.add(data.get(i)[0]);
                datetimesList.add(data.get(i)[1]);
                weightsList.add(Integer.parseInt(data.get(i)[2]));
                denominationsList.add(data.get(i)[3]);
                proteinsList.add(data.get(i)[4]);
                fatsList.add(data.get(i)[5]);
                carbohydratesList.add(data.get(i)[6]);
                caloriesList.add(data.get(i)[7]);

//                Log.w("ScoffAdapter", ""
//                                +data.get(i)[0]+"\n"
//                                +data.get(i)[1]+"\nquantity: "
//                                +data.get(i)[2]+"\n"
//                                +data.get(i)[3]+"\n"
//                                +data.get(i)[4]+"\n"
//                                +data.get(i)[5]+"\n"
//                                +data.get(i)[6]+"\n"
//                                +data.get(i)[7]+"\n"
//                );
            }

        }

    }

    @Override
    public ScoffViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.scoff_row, parent, false);
        ScoffViewHolder holder = new ScoffViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ScoffViewHolder holder, int position) {
        holder.recordId.setText(recordIDsList.get(position));
        holder.proteins.setText(proteinsList.get(position));
        holder.fats.setText(fatsList.get(position));
        holder.carbohydrates.setText(carbohydratesList.get(position));
        holder.weight.setText(String.valueOf(weightsList.get(position)));
        holder.calories.setText(caloriesList.get(position));
        holder.denomination.setText(denominationsList.get(position));
    }

    @Override
    public int getItemCount() {
        return recordIDsList.size();
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
