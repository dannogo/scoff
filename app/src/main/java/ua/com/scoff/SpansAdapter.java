package ua.com.scoff;

import android.content.ContentValues;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by oleh on 12/9/15.
 */
public class SpansAdapter extends RecyclerView.Adapter<SpansAdapter.SpansViewHolder> {

    private LayoutInflater inflater;

    public SpansAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    @Override
    public SpansViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.spans_row, parent, false);
        SpansViewHolder holder = new SpansViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(SpansViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class SpansViewHolder extends RecyclerView.ViewHolder{

        public SpansViewHolder(View itemView) {
            super(itemView);
        }
    }
}
