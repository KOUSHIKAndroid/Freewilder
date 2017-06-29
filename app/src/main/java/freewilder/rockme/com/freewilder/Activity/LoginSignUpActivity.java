package freewilder.rockme.com.freewilder.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import freewilder.rockme.com.freewilder.Json.URLPaser;
import freewilder.rockme.com.freewilder.Popups.AppPopup;
import freewilder.rockme.com.freewilder.R;
import freewilder.rockme.com.freewilder.Utils.AppLoader;
import freewilder.rockme.com.freewilder.Utils.AppLog;

/**
 * Created by su on 6/19/17.
 */

public class LoginSignUpActivity extends AppCompatActivity {
    WebView LocalWeb;
    AppPopup appPopup;
    AppLoader loader;
    LinearLayout LLEmailSignUp;
    RelativeLayout RLFacebookLogin;
    private static final String LOCAL_URL = "file:///android_asset/loginterms.html";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_signup);

        LLEmailSignUp= (LinearLayout) findViewById(R.id.LLEmailSignUp);
        RLFacebookLogin= (RelativeLayout) findViewById(R.id.RLFacebookLogin);

        LocalWeb = (WebView) findViewById(R.id.LocalWeb);
        LocalWeb.setBackgroundColor(Color.TRANSPARENT);
        appPopup = new AppPopup(this);
        loader = new AppLoader(this);
        LocalWeb.getSettings().setJavaScriptEnabled(true);
        LocalWeb.getSettings().setDomStorageEnabled(true);
        LocalWeb.getSettings().getJavaScriptCanOpenWindowsAutomatically();
        LocalWeb.getSettings().setLoadWithOverviewMode(true);
        LocalWeb.addJavascriptInterface(new WebAppInterface(this), "Android");
        LocalWeb.loadUrl(LOCAL_URL);
        LocalWeb.setWebChromeClient(new MyWebViewClient());

        LLEmailSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginSignUpActivity.this,SignUpActivity.class));
            }
        });

        RLFacebookLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginSignUpActivity.this,DashBoardActivity.class));
            }
        });
    }



    public class WebAppInterface {

        Context mContext;

        /**
         * Instantiate the interface and set the context
         */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /**
         * Show a toast from the web page
         */
        @JavascriptInterface
        public void nextScreen(final String pro_cat_id) {
            Toast.makeText(LoginSignUpActivity.this, "click", Toast.LENGTH_SHORT).show();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    switch (pro_cat_id) {
                        case "1":

                            loader.Show();
                            String Data = "";
                            new URLPaser().OnGetRequest("app_category/app_static_page?page_id=5&lang_id=1", new URLPaser.JSONResPonse() {
                                @Override
                                public void OnSucess(String Response) {
                                    loader.Dissmiss();
                                    try {
                                        appPopup.WithOutTitle_singleEvent(URLDecoder.decode(new JSONObject(Response).getString("page"),"UTF-8"), R.layout.popup_without_title, new AppPopup.singleAlertWithoutTitle() {
                                            @Override
                                            public void OnCancel(AlertDialog dailaog) {
                                                dailaog.dismiss();
                                            }
                                        });
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void OnExecption(Exception ex) {
                                    loader.Dissmiss();
                                }

                                @Override
                                public void OnFailed(String error) {
                                    loader.Dissmiss();
                                }
                            });


                            break;
                        case "2":
                            break;
                    }
                }
            });

        }
    }


    private class MyWebViewClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            AppLog.info("Progress", "" + newProgress);
            if (newProgress == 100) {
                view.loadUrl("javascript:callFromActivity(\"" + ("" + getResources().getString(R.string.by_tapping_login_with_facebook_sign_up_i_agree_to)) + "\",\"" + ("" + getResources().getString(R.string.freewilders)) + "\",\"" + ("" + getResources().getString(R.string.terms_of_service)) + "\",\"" + ("" + getResources().getString(R.string.payments_terms_of_service)) + "\",\"" + ("" + getResources().getString(R.string.privacy_policy)) + "\")");
            }
        }
    }
}
