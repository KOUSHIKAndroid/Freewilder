package freewilder.rockme.com.freewilder.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.TimeZone;

import freewilder.rockme.com.freewilder.Activity.SignUpActivity;
import freewilder.rockme.com.freewilder.Json.URLPaser;
import freewilder.rockme.com.freewilder.R;
import freewilder.rockme.com.freewilder.Utils.AppLoader;
import freewilder.rockme.com.freewilder.Utils.AppLog;
import freewilder.rockme.com.freewilder.helper.AppController;

/**
 * Created by su on 6/21/17.
 */

public class TermsPrivacySignUp extends Fragment {

    TextView tv_sign_up;
    AppLoader Loader;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_terms_privacy_sign_up, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Loader = new AppLoader(getActivity());

        tv_sign_up = (TextView) view.findViewById(R.id.tv_sign_up);
        tv_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ////////////sign up api fire here////////////////////

                Loader.Show();

                TimeZone tz = TimeZone.getDefault();
                String urlJsonObj = null;
                try {

                    urlJsonObj = "verify_app_signup?name=" + URLEncoder.encode(((SignUpActivity) getActivity()).firstName + " " + ((SignUpActivity) getActivity()).lastName, "UTF-8") + "&email=" + URLEncoder.encode(((SignUpActivity) getActivity()).email, "UTF-8") + "&password=" + URLEncoder.encode(((SignUpActivity) getActivity()).password, "UTF-8") + "&confirm_password="
                            + URLEncoder.encode(((SignUpActivity) getActivity()).password, "UTF-8") + "&gender=" + URLEncoder.encode(((SignUpActivity) getActivity()).gender, "UTF-8") + "&birth_date=" + URLEncoder.encode(((SignUpActivity) getActivity()).dateOfBirth, "UTF-8") + "&user_timezone" + tz.getID()
                            + "&cur_id=" + AppController.Curency + "&lang_id=" + AppController.Lang_id;

                    new URLPaser().OnGetRequest(urlJsonObj, new URLPaser.JSONResPonse() {

                                @Override
                                public void OnSucess(String Response) {

                                    try {
                                        JSONObject jsonObject = new JSONObject(Response);
                                        AppLog.info("jsonObject", "" + jsonObject);

                                        if (!jsonObject.getString("response").equalsIgnoreCase("Error")) {
                                            ((SignUpActivity) getActivity()).nextPage(5);
                                        }
                                        Loader.Dissmiss();

                                        Toast.makeText(getActivity(), "" + jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                                    } catch (JSONException e) {
                                        Loader.Dissmiss();
                                        e.printStackTrace();
                                        AppLog.info("JSONException", "" + e.getMessage());
                                    }
                                }

                                @Override
                                public void OnExecption(Exception ex) {
                                    Loader.Dissmiss();
                                    ex.printStackTrace();
                                }
                                @Override
                                public void OnFailed(String error) {

                                }
                            }
                    );

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
