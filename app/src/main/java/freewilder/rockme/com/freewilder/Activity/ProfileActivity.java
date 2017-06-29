package freewilder.rockme.com.freewilder.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.LinkedList;
import freewilder.rockme.com.freewilder.R;
import freewilder.rockme.com.freewilder.adapters.FragmentviewPager;
import freewilder.rockme.com.freewilder.fragments.ProfileMapFragment;
import freewilder.rockme.com.freewilder.fragments.ProfilePersonalFragment;
import freewilder.rockme.com.freewilder.fragments.ProfileReviewsFragment;
import freewilder.rockme.com.freewilder.fragments.ProfileServiceFragment;
import freewilder.rockme.com.freewilder.fragments.ProfileVideoFragment;


public class ProfileActivity extends AppCompatActivity{

    LinkedList<Fragment> fragmentList;
    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        findViewById(R.id.back2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method

//            tabLayout.addTab(tabLayout.newTab().setText("Personal"));
//            tabLayout.addTab(tabLayout.newTab().setText("Service"));
//            tabLayout.addTab(tabLayout.newTab().setText("Map"));
//            tabLayout.addTab(tabLayout.newTab().setText("Video"));
//            tabLayout.addTab(tabLayout.newTab().setText("Reviews"));

        //TextIconTabLayout tabLayout = (TextIconTabLayout) findViewById(R.id.sliding_tabs);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        fragmentList = new LinkedList<>();


        fragmentList.add(new ProfilePersonalFragment());
        fragmentList.add(new ProfileServiceFragment());
        fragmentList.add(new ProfileMapFragment());
        fragmentList.add(new ProfileVideoFragment());
        fragmentList.add(new ProfileReviewsFragment());

        //Creating our pager adapter
        FragmentviewPager adapter=new FragmentviewPager(ProfileActivity.this,getSupportFragmentManager(),fragmentList);

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        createTabIcons();


//        for(int i=0; i < tabLayout.getTabCount(); i++) {
//            View tab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
//            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
//            p.setMargins(0, 20, 0, 20);
//            tab.requestLayout();
//        }

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //Adding onTabSelectedListener to swipe views
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ((TextView)tab.getCustomView().findViewById(R.id.text)).setTextColor(Color.parseColor("#39B481"));

                if(tab.getPosition()==0){
                    ((ImageView)tab.getCustomView().findViewById(R.id.icon)).setImageResource(R.drawable.ic_personal_identity_green_24dp);
                }else if(tab.getPosition()==1){
                    ((ImageView)tab.getCustomView().findViewById(R.id.icon)).setImageResource(R.drawable.ic_services_green_24dp);
                }else if(tab.getPosition()==2){
                    ((ImageView)tab.getCustomView().findViewById(R.id.icon)).setImageResource(R.drawable.ic_map_green_placeholder);
                }else if(tab.getPosition()==3){
                    ((ImageView)tab.getCustomView().findViewById(R.id.icon)).setImageResource(R.drawable.ic_video_green_camera);
                }else if(tab.getPosition()==4){
                    ((ImageView)tab.getCustomView().findViewById(R.id.icon)).setImageResource(R.drawable.ic_star_green);
                }
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ((TextView)tab.getCustomView().findViewById(R.id.text)).setTextColor(Color.parseColor("#555555"));

                if(tab.getPosition()==0){
                    ((ImageView)tab.getCustomView().findViewById(R.id.icon)).setImageResource(R.drawable.ic_perm_identity_black_24dp);
                }else if(tab.getPosition()==1){
                    ((ImageView)tab.getCustomView().findViewById(R.id.icon)).setImageResource(R.drawable.ic_services_black_24dp);
                }else if(tab.getPosition()==2){
                    ((ImageView)tab.getCustomView().findViewById(R.id.icon)).setImageResource(R.drawable.ic_map_placeholder);
                }else if(tab.getPosition()==3){
                    ((ImageView)tab.getCustomView().findViewById(R.id.icon)).setImageResource(R.drawable.ic_video_camera);
                }else if(tab.getPosition()==4){
                    ((ImageView)tab.getCustomView().findViewById(R.id.icon)).setImageResource(R.drawable.ic_star);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void createTabIcons() {

        LinearLayout linearLayout1=(LinearLayout) LayoutInflater.from(this).inflate(R.layout.profile_custom_tab, null);

//        LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//        buttonLayoutParams.setMargins(0, 20, 0, 20);
//        linearLayout1.setLayoutParams(buttonLayoutParams);

        ((TextView)linearLayout1.findViewById(R.id.text)).setText(getResources().getString(R.string.profile_personal));
        ((TextView)linearLayout1.findViewById(R.id.text)).setTextColor(Color.parseColor("#39B481"));
        ((ImageView)linearLayout1.findViewById(R.id.icon)).setImageResource(R.drawable.ic_personal_identity_green_24dp);
        tabLayout.getTabAt(0).setCustomView(linearLayout1);


        LinearLayout linearLayout2=(LinearLayout) LayoutInflater.from(this).inflate(R.layout.profile_custom_tab, null);
        ((TextView)linearLayout2.findViewById(R.id.text)).setText(getResources().getString(R.string.profile_service));
        ((ImageView)linearLayout2.findViewById(R.id.icon)).setImageResource(R.drawable.ic_services_black_24dp);
        tabLayout.getTabAt(1).setCustomView(linearLayout2);


        LinearLayout linearLayout3=(LinearLayout) LayoutInflater.from(this).inflate(R.layout.profile_custom_tab, null);
        ((TextView)linearLayout3.findViewById(R.id.text)).setText(getResources().getString(R.string.profile_map));
        ((ImageView)linearLayout3.findViewById(R.id.icon)).setImageResource(R.drawable.ic_map_placeholder);
        tabLayout.getTabAt(2).setCustomView(linearLayout3);

        LinearLayout linearLayout4=(LinearLayout) LayoutInflater.from(this).inflate(R.layout.profile_custom_tab, null);
        ((TextView)linearLayout4.findViewById(R.id.text)).setText(getResources().getString(R.string.profile_video));
        ((ImageView)linearLayout4.findViewById(R.id.icon)).setImageResource(R.drawable.ic_video_camera);
        tabLayout.getTabAt(3).setCustomView(linearLayout4);

        LinearLayout linearLayout5=(LinearLayout) LayoutInflater.from(this).inflate(R.layout.profile_custom_tab, null);
        ((TextView)linearLayout5.findViewById(R.id.text)).setText(getResources().getString(R.string.profile_reviews));
        ((ImageView)linearLayout5.findViewById(R.id.icon)).setImageResource(R.drawable.ic_star);
        tabLayout.getTabAt(4).setCustomView(linearLayout5);

    }
}
