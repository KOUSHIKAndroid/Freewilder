package freewilder.rockme.com.freewilder.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import freewilder.rockme.com.freewilder.R;
import freewilder.rockme.com.freewilder.pojo.SetGetDashboard;

/**
 * Created by su on 6/20/17.
 */

public class DashboardRecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<SetGetDashboard> dashboardArrayList;

    public DashboardRecyclerViewAdapter(Context context,ArrayList<SetGetDashboard> dashboardArrayList) {
        this.context=context;
        this.dashboardArrayList=dashboardArrayList;
    }


    @Override
    public int getItemCount() {
        return 10;
    }


    class ViewHolder0 extends RecyclerView.ViewHolder {
        ImageView img_profile;
        TextView tv_name;
        EditText edit_search;
        CardView card_view_search;

        public ViewHolder0(View itemView) {
            super(itemView);
            img_profile= (ImageView) itemView.findViewById(R.id.img_profile);
            tv_name= (TextView) itemView.findViewById(R.id.tv_name);
            edit_search= (EditText) itemView.findViewById(R.id.edit_search);
            card_view_search= (CardView) itemView.findViewById(R.id.card_view_search);
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView tv_header,tv_name,tv_type;
        ImageView img_product_service;
        public ViewHolder2(View itemView) {
            super(itemView);
            tv_header= (TextView) itemView.findViewById(R.id.tv_header);
            img_product_service= (ImageView) itemView.findViewById(R.id.img_product_service);
            tv_name= (TextView) itemView.findViewById(R.id.tv_name);
            tv_type= (TextView) itemView.findViewById(R.id.tv_type);
        }
    }

        @Override
        public int getItemViewType(int position) {
            // Just as an example, return 0 or 2 depending on position
            // Note that unlike in ListView adapters, types don't have to be contiguous
            return position;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if(viewType==0) {
                return new ViewHolder0(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_dashboard_header_child, parent, false));
            }
                else {
                return new ViewHolder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_dashboard_child, parent, false));
            }
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

            switch (holder.getItemViewType()) {
                case 0:
                    ViewHolder0 viewHolder0 = (ViewHolder0) holder;
                    break;

                default:
                    ViewHolder2 viewHolder2 = (ViewHolder2) holder;

                    if (position==1){
                        viewHolder2.tv_header.setVisibility(View.VISIBLE);
                    }
                    else {
                        viewHolder2.tv_header.setVisibility(View.GONE);
                    }
                    break;
            }
        }
}
