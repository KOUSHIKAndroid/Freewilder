package freewilder.rockme.com.freewilder.Activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import freewilder.rockme.com.freewilder.R;

/**
 * Created by su on 6/19/17.
 */

public class LoginSignUpActivity extends AppCompatActivity {
    WebView LocalWeb;
    private static final String LOCAL_URL = "file:///android_asset/loginterms.html";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_signup);
        LocalWeb= (WebView) findViewById(R.id.LocalWeb);
        LocalWeb.setBackgroundColor(Color.TRANSPARENT);


        LocalWeb.getSettings().setJavaScriptEnabled(true);
        LocalWeb.getSettings().setDomStorageEnabled(true);
        LocalWeb.getSettings().getJavaScriptCanOpenWindowsAutomatically();
        LocalWeb.getSettings().setLoadWithOverviewMode(true);
        LocalWeb.addJavascriptInterface(new WebAppInterface(this), "Android");
        LocalWeb.loadUrl(LOCAL_URL);
        LocalWeb.setWebChromeClient(new MyWebViewClient());
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
        public void nextScreen(String pro_cat_id) {
             Toast.makeText(LoginSignUpActivity.this,"click", Toast.LENGTH_SHORT).show();
             Call_WEBview_forclick_terms(pro_cat_id);
        }
    }


    private void Call_WEBview_forclick_terms(String pro_cat_id) {
        String Title = "";
        if (pro_cat_id.equals("1"))
            Title = "" + getResources().getString(R.string.terms_of_service);
        else if (pro_cat_id.equals("2"))
            Title = "" + getResources().getString(R.string.payments_terms_of_service);
        else if (pro_cat_id.equals("3"))
            Title = "" + getResources().getString(R.string.privacy_policy);


        Log.i("Title","value:"+Title);

    }

    private class MyWebViewClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            Log.d("Progress", "" + newProgress);
            if (newProgress == 100) {
                view.loadUrl("javascript:callFromActivity(\"" + ("" + getResources().getString(R.string.by_tapping_login_with_facebook_sign_up_i_agree_to)) + "\",\"" + ("" + getResources().getString(R.string.freewilders)) + "\",\"" + ("" + getResources().getString(R.string.terms_of_service)) + "\",\"" + ("" + getResources().getString(R.string.payments_terms_of_service)) + "\",\"" + ("" + getResources().getString(R.string.privacy_policy)) +"\")");
            }
        }
    }

}
