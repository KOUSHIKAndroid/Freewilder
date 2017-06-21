package freewilder.rockme.com.freewilder.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

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


        nextFocusCustomEditext(input_value1,input_value2);
        nextFocusCustomEditext(input_value2,input_value3);
        nextFocusCustomEditext(input_value3,input_value4);
        nextFocusCustomEditext(input_value4,input_value5);


    }

    public void nextFocusCustomEditext(final AppCompatEditText editText1, final AppCompatEditText editText2){

        final StringBuilder sb=new StringBuilder();

        editText1.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                Log.i("sb1"," "+sb.length());
                Log.i("editText1"," "+editText1.length());
                if(sb.length()==0 & editText1.length()==1)
                {
                    Log.i("horibol"," "+s+" "+editText1);
                    sb.append(s);
                    Log.i("sb1"," "+sb.length());
                    editText1.clearFocus();
                    editText2.requestFocus();
                    editText2.setCursorVisible(true);
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(sb.length()==1)
                {
                    sb.deleteCharAt(0);
                }
            }
            public void afterTextChanged(Editable s) {
                if(sb.length()==0)
                {
                    editText1.requestFocus();
                }
            }
        });
    }
}
