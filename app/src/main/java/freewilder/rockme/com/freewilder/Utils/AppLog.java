package freewilder.rockme.com.freewilder.Utils;

import android.util.Log;

/**
 * Created by su on 6/20/17.
 */

public class AppLog {
    public static void info(String TAG,String Message){
        Log.d(TAG,Message);
    }

    public static void Error(String TAG,String Message){
        Log.e(TAG,Message);
    }
}
