package freewilder.rockme.com.freewilder.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

import freewilder.rockme.com.freewilder.Activity.ProfileActivity;
import freewilder.rockme.com.freewilder.R;
import freewilder.rockme.com.freewilder.pojo.SetGetProfileVideo;

/**
 * Created by su on 6/7/17.
 */

public class ProfileVideoAdapter extends RecyclerView.Adapter<ProfileVideoAdapter.MyViewHolder> {

    Context context;
    ArrayList<SetGetProfileVideo> profileVideoArrayList;
    DisplayMetrics dmetrics;

    public ProfileVideoAdapter(Context context, ArrayList<SetGetProfileVideo> profileVideoArrayList){

        this.context=context;
        this.profileVideoArrayList=profileVideoArrayList;
        dmetrics = new DisplayMetrics();
        ((ProfileActivity)context).getWindowManager().getDefaultDisplay().getMetrics(dmetrics);
    }
    @Override
    public ProfileVideoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_of_adapter_profile_video, parent, false);
        return new ProfileVideoAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProfileVideoAdapter.MyViewHolder holder, int position) {

        int widthPixels=dmetrics.widthPixels;
        int heightPixels=dmetrics.heightPixels;



        RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.height =  widthPixels/3;
        layoutParams.width = widthPixels/3;
        holder.img.setLayoutParams(layoutParams);

        Glide.with(context).load(profileVideoArrayList.get(position).getUrl()).placeholder(R.drawable.ic_perm_identity_black_24dp).into(holder.img);

    }

    @Override
    public int getItemCount() {
        Log.i("size",""+profileVideoArrayList.size());
        return profileVideoArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public MyViewHolder(View itemView) {
            super(itemView);
            img= (ImageView) itemView.findViewById(R.id.img);
        }
    }
}
