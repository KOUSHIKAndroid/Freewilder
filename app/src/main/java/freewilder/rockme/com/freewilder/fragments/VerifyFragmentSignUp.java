package freewilder.rockme.com.freewilder.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import freewilder.rockme.com.freewilder.R;

/**
 * Created by su on 6/21/17.
 */

public class VerifyFragmentSignUp extends Fragment{

    AppCompatEditText input_value1,input_value2,input_value3,input_value4,input_value5;
    TextInputLayout input_layout_value1,input_layout_value2,input_layout_value3,input_layout_value4,input_layout_value5;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_verification_sign_up,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        input_value1= (AppCompatEditText) view.findViewById(R.id.input_value1);
        input_value2= (AppCompatEditText) view.findViewById(R.id.input_value2);
        input_value3= (AppCompatEditText) view.findViewById(R.id.input_value3);
        input_value4= (AppCompatEditText) view.findViewById(R.id.input_value4);
        input_value5= (AppCompatEditText) view.findViewById(R.id.input_value5);

        input_layout_value1= (TextInputLayout) view.findViewById(R.id.input_layout_value1);
        input_layout_value2= (TextInputLayout) view.findViewById(R.id.input_layout_value2);
        input_layout_value3= (TextInputLayout) view.findViewById(R.id.input_layout_value3);
        input_layout_value4= (TextInputLayout) view.findViewById(R.id.input_layout_value4);
        input_layout_value5= (TextInputLayout) view.findViewById(R.id.input_layout_value5);


    }
}
