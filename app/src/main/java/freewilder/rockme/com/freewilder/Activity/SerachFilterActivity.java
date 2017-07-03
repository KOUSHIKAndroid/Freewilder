package freewilder.rockme.com.freewilder.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import freewilder.rockme.com.freewilder.R;
import freewilder.rockme.com.freewilder.adapters.AmenitiesRecyclerViewAdapter;
import freewilder.rockme.com.freewilder.adapters.FilterCategoryRecyclerViewAdapter;
import freewilder.rockme.com.freewilder.pojo.SetGetAmenitiesFilter;
import freewilder.rockme.com.freewilder.pojo.SetGetFilterCategory;

/**
 * Created by su on 6/30/17.
 */

public class SerachFilterActivity extends AppCompatActivity {

    RelativeLayout RLcategory,RLkeywords,RLaminities;

    AmenitiesRecyclerViewAdapter amenitiesRecyclerViewAdapter;
    FilterCategoryRecyclerViewAdapter filterCategoryRecyclerViewAdapter;

    ArrayList<SetGetAmenitiesFilter> amenitiesFilterArrayList;
    ArrayList<SetGetFilterCategory> filterCategoryArrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_filter);

        RLcategory= (RelativeLayout) findViewById(R.id.RLcategory);
        RLkeywords= (RelativeLayout) findViewById(R.id.RLkeywords);
        RLaminities= (RelativeLayout) findViewById(R.id.RLaminities);

        RLaminities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAminities(SerachFilterActivity.this);
            }
        });

        RLcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogCategory(SerachFilterActivity.this);
            }
        });


        amenitiesFilterArrayList=new ArrayList<>();
        filterCategoryArrayList=new ArrayList<>();

        for(int i=0;i<20;i++){
            SetGetAmenitiesFilter setGetAmenitiesFilter = new SetGetAmenitiesFilter();
            setGetAmenitiesFilter.setCheck(false);
            setGetAmenitiesFilter.setName("Amenities"+i);
            amenitiesFilterArrayList.add(setGetAmenitiesFilter);
        }

        for(int i=0;i<20;i++){
            SetGetFilterCategory setGetFilterCategory = new SetGetFilterCategory();
            setGetFilterCategory.setCheck(false);
            setGetFilterCategory.setCategoryName("Category"+i);
            filterCategoryArrayList.add(setGetFilterCategory);
        }
    }


    public void showDialogAminities(Activity activity) {

        RecyclerView rcv_amenities;
        ImageView img_cross;

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.search_filter_dialog_amenities);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        DisplayMetrics displayMetrics=getResources().getDisplayMetrics();
        int screenWidth=(int) (displayMetrics.widthPixels * 0.90);
        int screenHeight=(int) (displayMetrics.heightPixels * 0.70);


        lp.copyFrom(dialog.getWindow().getAttributes());
        //lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.width = screenWidth;
        //lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height =screenHeight;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);

        img_cross = (ImageView) dialog.findViewById(R.id.img_cross);
        rcv_amenities = (RecyclerView) dialog.findViewById(R.id.rcv_amenities);
        rcv_amenities.setLayoutManager(new LinearLayoutManager(this));

        amenitiesRecyclerViewAdapter = new AmenitiesRecyclerViewAdapter(this, amenitiesFilterArrayList);
        rcv_amenities.setAdapter(amenitiesRecyclerViewAdapter);

        img_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void showDialogCategory(Activity activity){

        RecyclerView rcv_category;
        ImageView img_cross;

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.search_filter_dialog_category);
        dialog.setCanceledOnTouchOutside(false);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        DisplayMetrics displayMetrics=getResources().getDisplayMetrics();
        int screenWidth=(int) (displayMetrics.widthPixels * 0.90);
        int screenHeight=(int) (displayMetrics.heightPixels * 0.70);


        lp.copyFrom(dialog.getWindow().getAttributes());
        //lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.width = screenWidth;
        //lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height =screenHeight;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);

        rcv_category= (RecyclerView) dialog.findViewById(R.id.rcv_category);
        rcv_category.setLayoutManager(new LinearLayoutManager(this));

        filterCategoryRecyclerViewAdapter=new FilterCategoryRecyclerViewAdapter(this,filterCategoryArrayList);
        rcv_category.setAdapter(filterCategoryRecyclerViewAdapter);



        img_cross = (ImageView) dialog.findViewById(R.id.img_cross);
        img_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
