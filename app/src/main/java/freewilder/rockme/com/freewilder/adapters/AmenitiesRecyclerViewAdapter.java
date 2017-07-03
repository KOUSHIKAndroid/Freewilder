package freewilder.rockme.com.freewilder.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import freewilder.rockme.com.freewilder.R;
import freewilder.rockme.com.freewilder.pojo.SetGetAmenitiesFilter;

/**
 * Created by su on 6/30/17.
 */

public class AmenitiesRecyclerViewAdapter extends RecyclerView.Adapter<AmenitiesRecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<SetGetAmenitiesFilter> amenitiesFilterArrayList;
    public AmenitiesRecyclerViewAdapter(Context context,ArrayList<SetGetAmenitiesFilter> amenitiesFilterArrayList){
        this.context=context;
        this.amenitiesFilterArrayList=amenitiesFilterArrayList;
    }

    @Override
    public AmenitiesRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AmenitiesRecyclerViewAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_filter_amenities_child_view, parent, false));
    }

    @Override
    public void onBindViewHolder(AmenitiesRecyclerViewAdapter.MyViewHolder holder, final int position) {
        holder.tv_name.setText(amenitiesFilterArrayList.get(position).getName());

        if(amenitiesFilterArrayList.get(position).isCheck()){
            holder.cb_value.setChecked(true);
        }else {
            holder.cb_value.setChecked(false);
        }


        holder.totalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(amenitiesFilterArrayList.get(position).isCheck()){
                    amenitiesFilterArrayList.get(position).setCheck(false);
                }
                else {
                    amenitiesFilterArrayList.get(position).setCheck(true);
                }
                notifyDataSetChanged();
            }
        });

        holder.cb_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amenitiesFilterArrayList.get(position).isCheck()){
                    amenitiesFilterArrayList.get(position).setCheck(false);
                }
                else {
                    amenitiesFilterArrayList.get(position).setCheck(true);
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return amenitiesFilterArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        View totalView;
        TextView tv_name;
        CheckBox cb_value;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name= (TextView) itemView.findViewById(R.id.tv_name);
            cb_value= (CheckBox) itemView.findViewById(R.id.cb_value);
            totalView=itemView;
        }
    }
}
