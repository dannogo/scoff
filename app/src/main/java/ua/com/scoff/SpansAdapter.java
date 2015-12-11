package ua.com.scoff;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by oleh on 12/9/15.
 */
public class SpansAdapter extends RecyclerView.Adapter<SpansAdapter.SpansViewHolder> {

    private LayoutInflater inflater;
    Context context;
    protected  ArrayList<String> spanIdsList = new ArrayList<>();
    protected  ArrayList<String> namesList = new ArrayList<>();
    protected  ArrayList<String> datetimesList = new ArrayList<>();
    protected  ArrayList<Boolean> isClosedList = new ArrayList<>();

    public SpansAdapter(Context context){
        inflater = LayoutInflater.from(context);
        this.context = context;
        ArrayList<String[]> result = ((MainActivity)context).databaseAdapter.getSpansData();
        if (!result.isEmpty()){
            for (int i=0; i<result.size(); i++){
                String[] currentRecord = result.get(i);
                spanIdsList.add(currentRecord[0]);
                namesList.add(currentRecord[1]);
                datetimesList.add(StaticUtils.sqliteDatetimeToProperString(currentRecord[2]));
                if (Integer.parseInt(currentRecord[3])>0) {
                    isClosedList.add(true);
                }else{
                    isClosedList.add(false);
                }
            }
        }
    }

    @Override
    public SpansViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.spans_row, parent, false);
        SpansViewHolder holder = new SpansViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(SpansViewHolder holder, int position) {
        holder.spanId.setText(spanIdsList.get(position));
        holder.spanName.setText(namesList.get(position));
        holder.spanDatetime.setText(datetimesList.get(position));
        if (isClosedList.get(position)) {
            holder.stateIcon.setImageResource(R.drawable.lock);
            holder.spanRow.setBackgroundColor(Color.parseColor("#CFD8DC"));
        }else{
            holder.stateIcon.setImageResource(R.drawable.unlock);
            holder.spanRow.setBackgroundColor(Color.parseColor("#fffafafa"));
        }
    }

    @Override
    public int getItemCount() {
        return spanIdsList.size();
    }

    class SpansViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView spanId, spanName, spanDatetime;
        ImageView stateIcon;
        LinearLayout spanRow;

        public SpansViewHolder(View itemView) {
            super(itemView);
            spanId = (TextView) itemView.findViewById(R.id.spanId);
            spanName = (TextView) itemView.findViewById(R.id.spanName);
            spanDatetime = (TextView) itemView.findViewById(R.id.spanDatetime);
            stateIcon = (ImageView) itemView.findViewById(R.id.stateIcon);
            spanRow = (LinearLayout) itemView.findViewById(R.id.spanRow);

            stateIcon.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == stateIcon.getId()){
                int count = ((MainActivity)context).databaseAdapter.closeOrOpenSpan(Integer.parseInt(spanId.getText().toString()), !isClosedList.get(getAdapterPosition()));
                if (count == 1){
                    boolean currentState = isClosedList.get(getAdapterPosition());
                    isClosedList.set(getAdapterPosition(), !currentState);
                    if (!currentState){
                        stateIcon.setImageResource(R.drawable.lock);
                        itemView.setBackgroundColor(Color.parseColor("#CFD8DC"));
                    }else{
                        stateIcon.setImageResource(R.drawable.unlock);
                        itemView.setBackgroundColor(Color.parseColor("#fffafafa"));
                    }
                }
            }
        }
    }
}
