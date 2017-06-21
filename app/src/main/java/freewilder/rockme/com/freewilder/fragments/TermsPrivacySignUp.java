package freewilder.rockme.com.freewilder.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import freewilder.rockme.com.freewilder.Activity.SignUpActivity;
import freewilder.rockme.com.freewilder.R;

/**
 * Created by su on 6/21/17.
 */

public class TermsPrivacySignUp extends Fragment {

    TextView tv_sign_up;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_terms_privacy_sign_up, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_sign_up= (TextView) view.findViewById(R.id.tv_sign_up);
        tv_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SignUpActivity) getActivity()).nextPage(5);
            }
        });
    }
}
