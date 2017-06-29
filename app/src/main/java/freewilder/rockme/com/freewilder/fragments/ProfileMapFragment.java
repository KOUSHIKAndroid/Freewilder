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
import freewilder.rockme.com.freewilder.pojo.SetGetProfileMapAvalivilityHour;

/**
 * Created by su on 5/3/17.
 */

public class ProfileMapFragment extends Fragment {
    ArrayList<SetGetProfileMapAvalivilityHour> profileMapAvalivilityHourArrayList;
    ViewGroup insertPoint;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_map, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profileMapAvalivilityHourArrayList=new ArrayList<>();

        for (int i=0;i<5;i++){
            SetGetProfileMapAvalivilityHour setGetProfileMapAvalivilityHour = new SetGetProfileMapAvalivilityHour();
            setGetProfileMapAvalivilityHour.setDay("Monday");
            setGetProfileMapAvalivilityHour.setWorkingTime("08:30 am-10:00 pm");
            setGetProfileMapAvalivilityHour.setBreakTime("None");
            profileMapAvalivilityHourArrayList.add(setGetProfileMapAvalivilityHour);
        }

        insertPoint= (ViewGroup)view. findViewById(R.id.linear_availibility_hour_container);



        for(int i=0;i<profileMapAvalivilityHourArrayList.size();i++) {

            LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = layoutInflater.inflate(R.layout.child_of_profilr_avalability_hour, null);

            if (v!=null) {

                // fill in any details dynamically here
                OpenSansRegular tv_day = (OpenSansRegular) v.findViewById(R.id.tv_day);
                OpenSansRegular tv_break_hour = (OpenSansRegular) v.findViewById(R.id.tv_break_hour);
                OpenSansRegular tv_work_hour = (OpenSansRegular) v.findViewById(R.id.tv_work_hour);

                tv_day.setText(profileMapAvalivilityHourArrayList.get(i).getDay());
                tv_work_hour.setText(profileMapAvalivilityHourArrayList.get(i).getWorkingTime());
                tv_break_hour.setText(profileMapAvalivilityHourArrayList.get(i).getBreakTime());

                // insert into main view

                insertPoint.addView(v);
            }
            else {
                Log.i("null","null");
            }
        }
    }
}
