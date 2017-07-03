package freewilder.rockme.com.freewilder.Activity;

import android.app.Activity;
import android.app.Dialog;
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import freewilder.rockme.com.freewilder.R;
import freewilder.rockme.com.freewilder.adapters.AmenitiesFilterRecyclerViewAdapter;
import freewilder.rockme.com.freewilder.adapters.CategoryFilterRecyclerViewAdapter;
import freewilder.rockme.com.freewilder.pojo.SetGetFilterAmenities;
import freewilder.rockme.com.freewilder.pojo.SetGetFilterCategory;

/**
 * Created by su on 6/30/17.
 */

public class SearchFilterActivity extends AppCompatActivity {

    RelativeLayout RLcategory,RLkeywords,RLaminities;

    AmenitiesFilterRecyclerViewAdapter amenitiesFilterRecyclerViewAdapter;
    CategoryFilterRecyclerViewAdapter categoryFilterRecyclerViewAdapter;
    CategoryFilterRecyclerViewAdapter keywordsFilterRecyclerViewAdapter;

    ArrayList<SetGetFilterAmenities> amenitiesFilterArrayList;

    ArrayList<SetGetFilterCategory> filterCategoryArrayList;
    ArrayList<SetGetFilterCategory> filterKeywordsArrayList;

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
                showDialogAminities(SearchFilterActivity.this);
            }
        });

        RLcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogCategory(SearchFilterActivity.this);
            }
        });

        RLkeywords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogKeyword(SearchFilterActivity.this);
            }
        });


        amenitiesFilterArrayList=new ArrayList<>();
        filterCategoryArrayList=new ArrayList<>();
        filterKeywordsArrayList=new ArrayList<>();

        for(int i=0;i<20;i++){
            SetGetFilterAmenities setGetFilterAmenities = new SetGetFilterAmenities();
            setGetFilterAmenities.setCheck(false);
            setGetFilterAmenities.setName("Amenities"+i);
            amenitiesFilterArrayList.add(setGetFilterAmenities);
        }

        for(int i=0;i<20;i++){
            SetGetFilterCategory setGetFilterCategory = new SetGetFilterCategory();
            setGetFilterCategory.setCheck(false);
            setGetFilterCategory.setCategoryName("Category"+i);
            filterCategoryArrayList.add(setGetFilterCategory);
        }

        for(int i=0;i<20;i++){
            SetGetFilterCategory setGetFilterKeywords = new SetGetFilterCategory();
            setGetFilterKeywords.setCheck(false);
            setGetFilterKeywords.setCategoryName("Keywords"+i);
            filterKeywordsArrayList.add(setGetFilterKeywords);
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

        amenitiesFilterRecyclerViewAdapter = new AmenitiesFilterRecyclerViewAdapter(this, amenitiesFilterArrayList);
        rcv_amenities.setAdapter(amenitiesFilterRecyclerViewAdapter);

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

        categoryFilterRecyclerViewAdapter =new CategoryFilterRecyclerViewAdapter(this,filterCategoryArrayList);
        rcv_category.setAdapter(categoryFilterRecyclerViewAdapter);



        img_cross = (ImageView) dialog.findViewById(R.id.img_cross);
        img_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void showDialogKeyword(Activity activity){

        RecyclerView rcv_category;
        ImageView img_cross;
        TextView tv_toolbar;

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

        tv_toolbar= (TextView) dialog.findViewById(R.id.tv_toolbar);
        tv_toolbar.setText("Keywords");
        rcv_category= (RecyclerView) dialog.findViewById(R.id.rcv_category);
        rcv_category.setLayoutManager(new LinearLayoutManager(this));

        keywordsFilterRecyclerViewAdapter =new CategoryFilterRecyclerViewAdapter(this,filterKeywordsArrayList);
        rcv_category.setAdapter(keywordsFilterRecyclerViewAdapter);



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
