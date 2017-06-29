package freewilder.rockme.com.freewilder.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import freewilder.rockme.com.freewilder.Activity.SignUpActivity;
import freewilder.rockme.com.freewilder.R;
import freewilder.rockme.com.freewilder.Utils.AppLog;

/**
 * Created by su on 6/13/17.
 */

public class BirthdaySignUpFragment extends Fragment {

    DatePicker date_picker;
    TextView tv_next;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_birthday_sign_up, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_next= (TextView) view.findViewById(R.id.tv_next);
        date_picker= (DatePicker) view.findViewById(R.id.date_picker);

        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int day = date_picker.getDayOfMonth();
                int month = date_picker.getMonth();
                int year = date_picker.getYear();


                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month);
                cal.set(Calendar.DAY_OF_MONTH, day);
                Date dateRepresentation = cal.getTime();


                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
                String birthDate = dateFormatter.format(dateRepresentation);

                AppLog.info("birthDate",""+birthDate);

                ((SignUpActivity) getActivity()).dateOfBirth = birthDate;


                ((SignUpActivity) getActivity()).nextPage(4);
            }
        });
    }
}
