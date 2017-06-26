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

public class EmailPasswordSignUpFragment extends Fragment {
    TextView tv_next;
    TextInputLayout input_layout_email,input_layout_password,input_layout_confirm_password;
    AppCompatEditText input_email,input_password,input_confirm_password;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_email_password_sign_up, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_next= (TextView) view.findViewById(R.id.tv_next);

        input_layout_email= (TextInputLayout) view.findViewById(R.id.input_layout_email);
        input_layout_password= (TextInputLayout) view.findViewById(R.id.input_layout_password);
        input_layout_confirm_password= (TextInputLayout) view.findViewById(R.id.input_layout_confirm_password);

        input_email= (AppCompatEditText) view.findViewById(R.id.input_email);
        input_password= (AppCompatEditText) view.findViewById(R.id.input_password);
        input_confirm_password= (AppCompatEditText) view.findViewById(R.id.input_confirm_password);


        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                input_layout_email.setErrorEnabled(false);
                input_layout_password.setErrorEnabled(false);
                input_layout_confirm_password.setErrorEnabled(false);

                if(input_email.getText().toString().equals("")){
                    input_layout_email.setErrorEnabled(true);
                    input_layout_email.setError("Enter email address..");
                    input_layout_email.requestFocus();

                }else {
                    input_layout_email.setErrorEnabled(false);

                    if(!isValidEmail(input_email.getText().toString())){
                        input_layout_email.setErrorEnabled(true);
                        input_layout_email.setError("Enter a valid email address..");
                        input_layout_email.requestFocus();
                    }
                    else {
                        input_layout_email.setErrorEnabled(false);

                        if(input_password.getText().toString().equals("")){

                            input_layout_password.setErrorEnabled(true);
                            input_layout_password.setError("Enter your password..");
                            input_layout_password.requestFocus();

                        }else {

                            input_layout_password.setErrorEnabled(false);
                            if(input_confirm_password.getText().toString().equals("")){
                                input_layout_confirm_password.setErrorEnabled(true);
                                input_layout_confirm_password.setError("Enter your confirm password..");
                                input_layout_confirm_password.requestFocus();
                            }else {
                                input_layout_confirm_password.setErrorEnabled(false);
                                if(input_password.getText().toString().equals(input_confirm_password.getText().toString())) {
                                    ((SignUpActivity) getActivity()).nextPage(2);
                                }
                                else {
                                    input_layout_confirm_password.setErrorEnabled(true);
                                    input_layout_confirm_password.setError("password and confirm password should be same..");
                                    input_layout_confirm_password.requestFocus();
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
