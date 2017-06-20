package freewilder.rockme.com.freewilder.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import freewilder.rockme.com.freewilder.pojo.SetGetDashboard;

/**
 * Created by su on 6/20/17.
 */

public class DashboardRecyclerViewAdapter  extends RecyclerView.Adapter<DashboardRecyclerViewAdapter.MyViewHolder>{

    Context context;
    ArrayList<SetGetDashboard> dashboardArrayList;

    public DashboardRecyclerViewAdapter(Context context,ArrayList<SetGetDashboard> dashboardArrayList){
        this.context=context;
        this.dashboardArrayList=dashboardArrayList;
    }

    @Override
    public DashboardRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(DashboardRecyclerViewAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dashboardArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
