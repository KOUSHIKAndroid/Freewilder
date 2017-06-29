package freewilder.rockme.com.freewilder.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

import freewilder.rockme.com.freewilder.R;
import freewilder.rockme.com.freewilder.adapters.ProfileReviewAdapter;
import freewilder.rockme.com.freewilder.custom_front.OpenSansRegular;
import freewilder.rockme.com.freewilder.pojo.SetGetProfileReview;


/**
 * Created by su on 5/3/17.
 */

public class ProfileReviewsFragment extends Fragment {
    RecyclerView rcv_review;
    OpenSansRegular tv_my_services,tv_from_seller;
    ProfileReviewAdapter profileReviewAdapter;

    ArrayList<SetGetProfileReview> profileReviewArrayList;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_reviews, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv_review= (RecyclerView) view.findViewById(R.id.rcv_review);

        profileReviewArrayList=new ArrayList<>();


        rcv_review.setLayoutManager(new LinearLayoutManager(getActivity()));

        tv_my_services= (OpenSansRegular) view.findViewById(R.id.tv_my_services);
        tv_from_seller= (OpenSansRegular) view.findViewById(R.id.tv_from_seller);


        showMyServices();


        tv_my_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMyServices();
            }
        });

        tv_from_seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFromSeller();
            }
        });
    }
    public void showMyServices(){

        tv_my_services.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorGreen));
        tv_from_seller.setTextColor(Color.parseColor("#555555"));

        profileReviewArrayList.clear();

        for(int i=0;i<10;i++) {

            SetGetProfileReview setGetProfileReview = new SetGetProfileReview();
            setGetProfileReview.setType("Fresh Bike1");
            setGetProfileReview.setName("Tom Garfield");
            setGetProfileReview.setDate("May 9,2016 | 11:21 am");
            setGetProfileReview.setRate("5");
            setGetProfileReview.setAddress("71 pilgrim avenue | chevy chase,MD20815");
            setGetProfileReview.setDetails("Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum has been the industry's standard dummy text ever since the 1500s...");

            profileReviewArrayList.add(setGetProfileReview);
        }

        profileReviewAdapter=new ProfileReviewAdapter(getActivity(),profileReviewArrayList,1);
        rcv_review.setAdapter(profileReviewAdapter);

    }

    public void showFromSeller(){

        tv_from_seller.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorGreen));
        tv_my_services.setTextColor(Color.parseColor("#555555"));

        profileReviewArrayList.clear();

        for(int i=0;i<10;i++) {

            SetGetProfileReview setGetProfileReview = new SetGetProfileReview();
            setGetProfileReview.setType("Fresh Bike2");
            setGetProfileReview.setName("Tom Garfield");
            setGetProfileReview.setDate("May 9,2016 | 11:21 am");
            setGetProfileReview.setRate("5");
            setGetProfileReview.setAddress("71 pilgrim avenue | chevy chase,MD20815");
            setGetProfileReview.setDetails("Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum has been the industry's standard dummy text ever since the 1500s...");
            setGetProfileReview.setImg("");

            profileReviewArrayList.add(setGetProfileReview);
        }

        profileReviewAdapter=new ProfileReviewAdapter(getActivity(),profileReviewArrayList,2);
        rcv_review.setAdapter(profileReviewAdapter);

    }
}
