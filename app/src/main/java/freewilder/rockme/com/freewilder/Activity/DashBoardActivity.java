package freewilder.rockme.com.freewilder.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;
import java.util.ArrayList;
import freewilder.rockme.com.freewilder.R;
import freewilder.rockme.com.freewilder.adapters.DashboardRecyclerViewAdapter;
import freewilder.rockme.com.freewilder.pojo.SetGetDashboard;


/**
 * Created by su on 6/20/17.
 */

public class DashBoardActivity extends AppCompatActivity {
    BottomNavigationView mBottomNav;
    RecyclerView rcv_dashboard;
    ArrayList<SetGetDashboard> dashboardArrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mBottomNav= (BottomNavigationView) findViewById(R.id.navigation);
        rcv_dashboard= (RecyclerView) findViewById(R.id.rcv_dashboard);
        rcv_dashboard.setLayoutManager(new LinearLayoutManager(this));
        rcv_dashboard.setItemAnimator(new DefaultItemAnimator());

        dashboardArrayList=new ArrayList<>();

        DashboardRecyclerViewAdapter adapter=new DashboardRecyclerViewAdapter(this,dashboardArrayList);
        rcv_dashboard.setAdapter(adapter);


        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // handle desired action here
                // One possibility of action is to replace the contents above the nav bar
                // return true if you want the item to be displayed as the selected item

                switch (item.getItemId()) {
                    case R.id.action_dashboard:
                        Toast.makeText(DashBoardActivity.this,"dashboard",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_search:
                        Toast.makeText(DashBoardActivity.this,"search",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_collection:
                        Toast.makeText(DashBoardActivity.this,"collection",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_message:
                        Toast.makeText(DashBoardActivity.this,"message",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_sidemenu:
                        Toast.makeText(DashBoardActivity.this,"sidemenu",Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
}
