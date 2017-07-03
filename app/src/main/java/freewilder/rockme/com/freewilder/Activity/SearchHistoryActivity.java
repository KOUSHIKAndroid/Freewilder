package freewilder.rockme.com.freewilder.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import freewilder.rockme.com.freewilder.R;
import freewilder.rockme.com.freewilder.adapters.SearchHistoryLocationAdapter;
import freewilder.rockme.com.freewilder.pojo.SetGetLocationHistory;

/**
 * Created by su on 6/30/17.
 */

public class SearchHistoryActivity extends AppCompatActivity {

    ArrayList<SetGetLocationHistory> locationHistoryArrayList;
    RecyclerView rcv_search_history;
    SearchHistoryLocationAdapter searchHistoryLocationAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_location_history);

        locationHistoryArrayList=new ArrayList<>();

        rcv_search_history= (RecyclerView) findViewById(R.id.rcv_search_history);
        rcv_search_history.setLayoutManager(new LinearLayoutManager(SearchHistoryActivity.this));

        for (int p=0;p<10;p++){

            SetGetLocationHistory setGetLocationHistory=new SetGetLocationHistory();
            setGetLocationHistory.setName("Location Name"+p);
            locationHistoryArrayList.add(setGetLocationHistory);
        }

        searchHistoryLocationAdapter=new SearchHistoryLocationAdapter(this,locationHistoryArrayList);
        rcv_search_history.setAdapter(searchHistoryLocationAdapter);

    }
}
