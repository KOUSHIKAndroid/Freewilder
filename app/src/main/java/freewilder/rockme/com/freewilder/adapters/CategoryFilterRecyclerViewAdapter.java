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

public class CategoryFilterRecyclerViewAdapter extends RecyclerView.Adapter<CategoryFilterRecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<SetGetFilterCategory> filterCategoryArrayList;
    public CategoryFilterRecyclerViewAdapter(Context context, ArrayList<SetGetFilterCategory> filterCategoryArrayList ){
        this.context=context;
        this.filterCategoryArrayList=filterCategoryArrayList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoryFilterRecyclerViewAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_filter_category_child_view, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_name.setText(filterCategoryArrayList.get(position).getCategoryName());

        if(filterCategoryArrayList.get(position).isCheck()){
            holder.img_check.setVisibility(View.VISIBLE);
        }
        else {
            holder.img_check.setVisibility(View.GONE);
        }

        holder.totalview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(filterCategoryArrayList.get(position).isCheck()){
                    filterCategoryArrayList.get(position).setCheck(false);
                }
                else {
                    filterCategoryArrayList.get(position).setCheck(true);
                }
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return filterCategoryArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        View totalview;
        TextView tv_name;
        ImageView img_check;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name= (TextView) itemView.findViewById(R.id.tv_name);
            img_check= (ImageView) itemView.findViewById(R.id.img_check);
            totalview=itemView;
        }
    }
}
