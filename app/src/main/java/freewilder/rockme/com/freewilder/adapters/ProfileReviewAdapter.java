package freewilder.rockme.com.freewilder.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import freewilder.rockme.com.freewilder.R;
import freewilder.rockme.com.freewilder.custom_front.OpenSansExtraBold;
import freewilder.rockme.com.freewilder.custom_front.OpenSansRegular;
import freewilder.rockme.com.freewilder.pojo.SetGetProfileReview;

/**
 * Created by su on 6/7/17.
 */

public class ProfileReviewAdapter extends RecyclerView.Adapter<ProfileReviewAdapter.MyViewHolder> {
    int sellerOrServiceCheck=1;
    Context context;
    ArrayList <SetGetProfileReview> profileReviewArrayList;
    public ProfileReviewAdapter(Context context, ArrayList <SetGetProfileReview> profileReviewArrayList, int sellerOrServiceCheck){
        this.context=context;
        this.profileReviewArrayList=profileReviewArrayList;
        this.sellerOrServiceCheck=sellerOrServiceCheck;
    }
    @Override
    public ProfileReviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(sellerOrServiceCheck==1){
            return new ProfileReviewAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.child_of_adapter_profile_review_myservice, parent, false));
        }
        else {
            return new ProfileReviewAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.child_of_adapter_profile_review_fromseller, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(ProfileReviewAdapter.MyViewHolder holder, int position) {

        if(sellerOrServiceCheck==1) {
            holder.tv_profile_fresh_bike.setText(profileReviewArrayList.get(position).getType());
        }

        holder.tv_profile_name.setText(profileReviewArrayList.get(position).getName());
        holder.tv_profile_date.setText(profileReviewArrayList.get(position).getDate());
        holder.tv_profile_address.setText(profileReviewArrayList.get(position).getAddress());
        holder.tv_profile_description.setText(profileReviewArrayList.get(position).getDetails());

        Glide.with(context).load(profileReviewArrayList.get(position).getImg()).placeholder(R.drawable.ic_perm_identity_black_24dp).into(holder.img_profile);

        holder.rate_profile_review.setRating(Float.parseFloat(profileReviewArrayList.get(position).getRate()));

    }

    @Override
    public int getItemCount() {
        return profileReviewArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        OpenSansRegular tv_profile_name,tv_profile_date,tv_profile_address,tv_profile_description;
        OpenSansExtraBold tv_profile_fresh_bike;
        RatingBar rate_profile_review;
        ImageView img_profile;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_profile_name= (OpenSansRegular) itemView.findViewById(R.id.tv_profile_name);
            tv_profile_date= (OpenSansRegular) itemView.findViewById(R.id.tv_profile_date);
            tv_profile_address= (OpenSansRegular) itemView.findViewById(R.id.tv_profile_address);
            tv_profile_description= (OpenSansRegular) itemView.findViewById(R.id.tv_profile_description);

            if(sellerOrServiceCheck==1) {
                tv_profile_fresh_bike = (OpenSansExtraBold) itemView.findViewById(R.id.tv_profile_fresh_bike);
            }

            rate_profile_review= (RatingBar) itemView.findViewById(R.id.rate_profile_review);

            img_profile= (ImageView) itemView.findViewById(R.id.img_profile);
        }
    }
}
