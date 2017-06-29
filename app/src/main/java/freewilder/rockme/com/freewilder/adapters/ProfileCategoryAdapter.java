package freewilder.rockme.com.freewilder.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import org.json.JSONObject;
import java.util.ArrayList;
import freewilder.rockme.com.freewilder.R;
import freewilder.rockme.com.freewilder.custom_front.OpenSansBold;
import freewilder.rockme.com.freewilder.custom_front.OpenSansRegular;
import freewilder.rockme.com.freewilder.fragments.ProfileServiceFragment;
import freewilder.rockme.com.freewilder.pojo.SetGetProfileCategoryWiseData;

/**
 * Created by su on 6/8/17.
 */

public class ProfileCategoryAdapter extends RecyclerView.Adapter<ProfileCategoryAdapter.MyViewHolder> {
    ProfileServiceFragment profileServiceFragment;
    Context context;
    ArrayList<SetGetProfileCategoryWiseData> profileCategoryArrayList;

    public ProfileCategoryAdapter(Context context, ProfileServiceFragment profileServiceFragment, ArrayList<SetGetProfileCategoryWiseData> profileCategoryArrayList){
        this.context=context;
        this.profileServiceFragment = profileServiceFragment;
        this.profileCategoryArrayList=profileCategoryArrayList;
    }
    @Override
    public ProfileCategoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_of_adapter_profile_category, parent, false);
        return new ProfileCategoryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProfileCategoryAdapter.MyViewHolder holder, final int position) {

        Log.i("position",""+position);
        try {
            JSONObject jsonObject=profileCategoryArrayList.get(position).getJsonObject();
            holder.tv_product_name.setText(jsonObject.getString("name"));
        }
        catch (Exception e){
            e.printStackTrace();
        }


        if(profileCategoryArrayList.get(position).isHeader()){
            holder.tv_header.setVisibility(View.VISIBLE);
            holder.tv_header.setText(profileCategoryArrayList.get(position).getCategoryType());
        }
        else {
            holder.tv_header.setVisibility(View.GONE);
        }


        if(profileCategoryArrayList.get(position).isFooter()){
            holder.tv_view_more.setVisibility(View.VISIBLE);
        }
        else {
            holder.tv_view_more.setVisibility(View.GONE);
        }



        holder.tv_view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ////////////////////add more work here/////////////////////
               // Toast.makeText(context,"view more",Toast.LENGTH_SHORT).show();

                profileServiceFragment.insertDataIntoListFromAdapter(profileCategoryArrayList.get(position).getCategoryType(),
                        profileCategoryArrayList.get(position).getNthPosition(),position+1);

                //headerCurrent=profileCategoryArrayList.get(position).getCategoryType();
                profileCategoryArrayList.get(position).setFooter(false);

                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return profileCategoryArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        OpenSansRegular tv_book_now,tv_sold_or_sell,tv_profile_category_details,tv_profile_address,tv_header,tv_view_more;
        RatingBar rate_profile_review;
        OpenSansBold tv_product_name,tv_product_price;

        public MyViewHolder(View itemView) {
            super(itemView);

            img= (ImageView) itemView.findViewById(R.id.img);

            tv_book_now= (OpenSansRegular) itemView.findViewById(R.id.tv_book_now);
            tv_sold_or_sell= (OpenSansRegular) itemView.findViewById(R.id.tv_sold_or_sell);
            tv_profile_category_details= (OpenSansRegular) itemView.findViewById(R.id.tv_profile_category_details);
            tv_profile_address= (OpenSansRegular) itemView.findViewById(R.id.tv_profile_address);
            tv_header= (OpenSansRegular) itemView.findViewById(R.id.tv_header);
            tv_view_more= (OpenSansRegular) itemView.findViewById(R.id.tv_view_more);

            rate_profile_review= (RatingBar) itemView.findViewById(R.id.rate_profile_review);

            tv_product_name= (OpenSansBold) itemView.findViewById(R.id.tv_product_name);
            tv_product_price= (OpenSansBold) itemView.findViewById(R.id.tv_product_price);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
