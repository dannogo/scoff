package ua.com.scoff;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by oleh on 12/4/15.
 */
public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {

    private LayoutInflater inflater;
    Context context;

    public ProductsAdapter(Context context){
        inflater = LayoutInflater.from(context);
        this.context = context;
    }


    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.products_row, parent, false);
        ProductsViewHolder holder = new ProductsViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ProductsViewHolder extends RecyclerView.ViewHolder{
        public ProductsViewHolder(View itemView) {
            super(itemView);
        }
    }

}
