package freewilder.rockme.com.freewilder.helper;

/**
 * Created by su on 8/7/15.
 */
import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import freewilder.rockme.com.freewilder.pojo.SetGetContact;


public class AppController extends Application {

    //    public static String SITEURL = "http://esolz.co.in/lab6/freewilder/";

    public static int Curency = 1;
    public static int Lang_id = 1;
    public static String userid = "30";

    private static AppController mInstance;
    public ArrayList<SetGetContact> setGetContactWithEmailID;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        String timeStamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + "";
        Log.e("MAIN/ APPLICATION---->"," "+timeStamp);
        setGetContactWithEmailID =new ArrayList<>();
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

}