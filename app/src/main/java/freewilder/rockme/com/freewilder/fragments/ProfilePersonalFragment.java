package freewilder.rockme.com.freewilder.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

import freewilder.rockme.com.freewilder.R;
import freewilder.rockme.com.freewilder.custom_front.OpenSansRegular;
import freewilder.rockme.com.freewilder.pojo.SetGetverified;

/**
 * Created by su on 5/3/17.
 */

public class ProfilePersonalFragment extends Fragment {

    //RecyclerView rcv_verified;
    ArrayList<SetGetverified> arrayList;
    ViewGroup insertPoint;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_personal, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //rcv_verified= (RecyclerView) view.findViewById(R.id.rcv_verified);

        insertPoint= (ViewGroup)view. findViewById(R.id.linear_verified_container);


        //rcv_verified.setLayoutManager(new LinearLayoutManager(getActivity()));
        //rcv_verified.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        //rcv_verified.setItemAnimator(new DefaultItemAnimator());


        arrayList=new ArrayList<>();

        SetGetverified sg1=new SetGetverified();
        sg1.setName("Email Id");
        sg1.setVerified(true);
        arrayList.add(sg1);

        SetGetverified sg2=new SetGetverified();
        sg2.setName("Reviewd");
        sg2.setVerified(true);
        arrayList.add(sg2);

        SetGetverified sg3=new SetGetverified();
        sg3.setName("Facebook");
        sg3.setVerified(true);
        arrayList.add(sg3);

        SetGetverified sg4=new SetGetverified();
        sg4.setName("Driving License");
        sg4.setVerified(true);
        arrayList.add(sg4);

        SetGetverified sg5=new SetGetverified();
        sg5.setName("Other");
        sg5.setVerified(true);
        arrayList.add(sg5);

        Log.i("size",""+arrayList.size());

        //VerifiedRecyclerViewAdapter adapter=new VerifiedRecyclerViewAdapter(getActivity(),arrayList);
        //rcv_verified.setAdapter(adapter);


        for(int i=0;i<arrayList.size();i++) {

            LayoutInflater vi = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = vi.inflate(R.layout.child_of_adapter_verified, null);

            // fill in any details dynamically here
            OpenSansRegular textView = (OpenSansRegular) v.findViewById(R.id.tv_name);
            textView.setText(arrayList.get(i).getName());

            // insert into main view

            insertPoint.addView(v);
        }

    }
}
