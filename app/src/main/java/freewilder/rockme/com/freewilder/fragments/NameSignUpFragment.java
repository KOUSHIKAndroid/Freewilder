package freewilder.rockme.com.freewilder.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import freewilder.rockme.com.freewilder.Activity.SignUpActivity;
import freewilder.rockme.com.freewilder.R;

/**
 * Created by su on 6/13/17.
 */

public class NameSignUpFragment extends Fragment {

    TextView tv_next;
    AppCompatEditText input_first_name,input_last_name;
    TextInputLayout input_layout_first_name,input_layout_last_name;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_name_sign_up, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_next= (TextView) view.findViewById(R.id.tv_next);

        input_first_name= (AppCompatEditText) view.findViewById(R.id.input_first_name);
        input_last_name= (AppCompatEditText) view.findViewById(R.id.input_last_name);

        input_layout_first_name= (TextInputLayout) view.findViewById(R.id.input_layout_first_name);
        input_layout_last_name= (TextInputLayout) view.findViewById(R.id.input_layout_last_name);


        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                input_layout_first_name.setErrorEnabled(false);
                input_layout_last_name.setErrorEnabled(false);

                if(input_first_name.getText().toString().equals("")){

                    input_layout_first_name.setErrorEnabled(true);
                    input_layout_first_name.setError("Enter first name...");
                    input_first_name.requestFocus();

                }else {

                    input_layout_first_name.setErrorEnabled(false);

                    if(input_last_name.getText().toString().equals("")){

                        input_layout_last_name.setErrorEnabled(true);
                        input_layout_last_name.setError("Enter Last name...");
                        input_last_name.requestFocus();

                    }else {

                        input_layout_last_name.setErrorEnabled(false);
                        ((SignUpActivity)getActivity()).nextPage(1);
                    }
                }
            }
        });
    }
}
