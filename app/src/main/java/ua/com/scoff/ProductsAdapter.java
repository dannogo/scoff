package ua.com.scoff;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by oleh on 12/4/15.
 */
public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {

    private LayoutInflater inflater;
    Context context;
    protected ArrayList<String> productIDsList = new ArrayList<>();
    protected ArrayList<String> denominationsList = new ArrayList<>();
    protected ArrayList<String> proteinsList = new ArrayList<>();
    protected ArrayList<String> fatsList = new ArrayList<>();
    protected ArrayList<String> carbohydratesList = new ArrayList<>();
    protected ArrayList<String> caloriesList = new ArrayList<>();
    protected ArrayList<Integer> frequenciesList = new ArrayList<>();

    public ProductsAdapter(Context context){
        inflater = LayoutInflater.from(context);
        this.context = context;
        ArrayList<String[]> result = ((AddScoffActivity) context).databaseAdapter.getProductsData();
        if (!result.isEmpty()){
            for (int i=0; i<result.size(); i++){
                String[] currentRecord = result.get(i);
                productIDsList.add(currentRecord[0]);
                denominationsList.add(currentRecord[1]);
                proteinsList.add(currentRecord[2]);
                fatsList.add(currentRecord[3]);
                carbohydratesList.add(currentRecord[4]);
                caloriesList.add(currentRecord[5]);
                frequenciesList.add(Integer.parseInt(currentRecord[6]));
            }
        }
    }


    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.products_row, parent, false);
        ProductsViewHolder holder = new ProductsViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder holder, int position) {
        holder.productId.setText(productIDsList.get(position));
        holder.denomination.setText(denominationsList.get(position));
        holder.proteins.setText(proteinsList.get(position));
        holder.fats.setText(fatsList.get(position));
        holder.carbohydrates.setText(carbohydratesList.get(position));
        holder.calories.setText(caloriesList.get(position));

    }

    @Override
    public int getItemCount() {
        return productIDsList.size();
    }

    class ProductsViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener{

        TextView productId, denomination, proteins, fats, carbohydrates, weight, calories;

        public ProductsViewHolder(View itemView) {
            super(itemView);
            productId = (TextView) itemView.findViewById(R.id.productId);
            proteins = (TextView) itemView.findViewById(R.id.product_proteins);
            fats = (TextView) itemView.findViewById(R.id.product_fats);
            carbohydrates = (TextView) itemView.findViewById(R.id.product_carbohydrates);
//            weight = (TextView) itemView.findViewById(R.id.product_weight);
            calories = (TextView) itemView.findViewById(R.id.product_calories);
            denomination = (TextView) itemView.findViewById(R.id.product_denomination);

            itemView.setOnClickListener(this);

        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }

        @Override
        public void onClick(View v) {
            StaticUtils.showPopupContextWindow(context, v);

        }
    }

}
