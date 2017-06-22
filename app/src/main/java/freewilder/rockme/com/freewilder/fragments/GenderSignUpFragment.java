package freewilder.rockme.com.freewilder.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import freewilder.rockme.com.freewilder.Activity.SignUpActivity;
import freewilder.rockme.com.freewilder.R;

/**
 * Created by su on 6/13/17.
 */

public class GenderSignUpFragment extends Fragment implements CompoundButton.OnCheckedChangeListener{
    @Nullable

    TextView tv_next;
    RadioButton male_radio_button,female_radio_button;
    RelativeLayout RLMale,RLFemale;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_gender_sign_up, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_next= (TextView) view.findViewById(R.id.tv_next);

        RLMale= (RelativeLayout) view.findViewById(R.id.RLMale);
        RLFemale= (RelativeLayout) view.findViewById(R.id.RLFemale);


        RLMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male_radio_button.setChecked(true);
                female_radio_button.setChecked(false);
            }
        });


        RLFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                male_radio_button.setChecked(false);
                female_radio_button.setChecked(true);
            }
        });


        male_radio_button= (RadioButton) view.findViewById(R.id.male_radio_button);
        male_radio_button.setOnCheckedChangeListener(this);

        female_radio_button= (RadioButton) view.findViewById(R.id.female_radio_button);
        female_radio_button.setOnCheckedChangeListener(this);


        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SignUpActivity)getActivity()).nextPage(3);
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        Log.i("Log check",""+isChecked);

        if (isChecked) {
            if (buttonView.getId() == R.id.male_radio_button) {
                male_radio_button.setChecked(true);
                female_radio_button.setChecked(false);
            }
            if (buttonView.getId() == R.id.female_radio_button) {
                female_radio_button.setChecked(true);
                male_radio_button.setChecked(false);
            }
        }
    }
}
