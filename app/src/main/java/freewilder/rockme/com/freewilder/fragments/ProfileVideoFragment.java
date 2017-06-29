package freewilder.rockme.com.freewilder.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

import freewilder.rockme.com.freewilder.R;
import freewilder.rockme.com.freewilder.adapters.ProfileVideoAdapter;
import freewilder.rockme.com.freewilder.pojo.SetGetProfileVideo;

/**
 * Created by su on 5/3/17.
 */

public class ProfileVideoFragment extends Fragment {

    RecyclerView rcv_profile_video;
    ArrayList<SetGetProfileVideo> profileVideoArrayList;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_video, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcv_profile_video= (RecyclerView) view.findViewById(R.id.rcv_profile_video);
        rcv_profile_video.setLayoutManager(new GridLayoutManager(getActivity(),3));

        //rcv_profile_video.addItemDecoration(new GridDividerDecoration(getActivity()));

        profileVideoArrayList=new ArrayList<>();

        loadVideo();
    }

    public void loadVideo(){
        for (int i=0;i<10;i++){
            SetGetProfileVideo setGetProfileVideo=new SetGetProfileVideo();
            setGetProfileVideo.setUrl("");
            profileVideoArrayList.add(setGetProfileVideo);
        }
        ProfileVideoAdapter profileVideoAdapter=new ProfileVideoAdapter(getActivity(),profileVideoArrayList);
        rcv_profile_video.setAdapter(profileVideoAdapter);
    }
}
