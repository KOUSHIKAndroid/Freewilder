package freewilder.rockme.com.freewilder.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import freewilder.rockme.com.freewilder.R;
import freewilder.rockme.com.freewilder.pojo.SetGetLocationHistory;

/**
 * Created by su on 7/3/17.
 */

public class SearchHistoryLocationAdapter extends RecyclerView.Adapter<SearchHistoryLocationAdapter.MyViewholder> {

    Context context;
    ArrayList<SetGetLocationHistory> locationHistoryArrayList;
    public SearchHistoryLocationAdapter(Context context,ArrayList<SetGetLocationHistory> locationHistoryArrayList){
        this.context=context;
        this.locationHistoryArrayList=locationHistoryArrayList;

    }
    @Override
    public SearchHistoryLocationAdapter.MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_location_search_history, parent, false);
        return new MyViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchHistoryLocationAdapter.MyViewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return locationHistoryArrayList.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView tv_name;
        public MyViewholder(View itemView) {
            super(itemView);
            tv_name= (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
