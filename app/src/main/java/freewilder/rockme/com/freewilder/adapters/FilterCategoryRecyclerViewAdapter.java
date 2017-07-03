package freewilder.rockme.com.freewilder.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import freewilder.rockme.com.freewilder.R;
import freewilder.rockme.com.freewilder.pojo.SetGetFilterCategory;

/**
 * Created by su on 7/3/17.
 */

public class FilterCategoryRecyclerViewAdapter extends RecyclerView.Adapter<FilterCategoryRecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<SetGetFilterCategory> filterCategoryArrayList;
    public FilterCategoryRecyclerViewAdapter(Context context,ArrayList<SetGetFilterCategory> filterCategoryArrayList ){
        this.context=context;
        this.filterCategoryArrayList=filterCategoryArrayList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FilterCategoryRecyclerViewAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_filter_category_child_view, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return filterCategoryArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        ImageView img_check;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name= (TextView) itemView.findViewById(R.id.tv_name);
            img_check= (ImageView) itemView.findViewById(R.id.img_check);
        }
    }
}
