package ua.com.scoff;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by oleh on 12/3/15.
 */
public class ScoffAdapter extends RecyclerView.Adapter<ScoffAdapter.ScoffViewHolder> {

    private LayoutInflater inflater;
    Context context;

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
        public ScoffViewHolder(View itemView) {
            super(itemView);
        }
    }
}
