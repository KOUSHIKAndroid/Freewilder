package freewilder.rockme.com.freewilder.Json;

import android.os.AsyncTask;
import android.util.Log;

import java.util.concurrent.TimeUnit;
import freewilder.rockme.com.freewilder.Utils.AppLog;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by su on 6/19/17.
 */

public class URLPaser {

    String BaseUrl="https://freewilder.com/";


    public interface JSONResPonse{
        void OnSucess(String Response);
        void OnExecption(Exception ex);
        void OnFailed(String error);
    }


    public void OnGetRequest(final String Parama, final JSONResPonse jsonResPonse){
        new AsyncTask<Void, Void, Void>() {

            private String respose = null;
            private Exception exception=null;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    if (!isCancelled()) {

                        OkHttpClient client = new OkHttpClient.Builder().retryOnConnectionFailure(true).connectTimeout(5000, TimeUnit.MILLISECONDS).build();
                        Request request = new Request.Builder().url(BaseUrl+Parama).build();
                        Response response = client.newCall(request).execute();

                        respose = response.body().string();


                        AppLog.info(getClass().getName(), "respose_::" + respose);
//                        Loger.MSG("response", "respose_ww_message::" + response.message());
//                        Loger.MSG("response", "respose_ww_headers::" + response.headers());
//                        Loger.MSG("response", "respose_ww_isRedirect::" + response.isRedirect());
//                       Loger.MSG("response", "respose_ww_body::" + response.body().string());
                    }
                } catch (Exception e) {
                    this.exception=e;
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (!isCancelled() && exception==null) {
                    jsonResPonse.OnSucess(respose);

                }else {
                    jsonResPonse.OnExecption(exception);
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }





    public void onPostMethod(final String url,final String params[],final String value[], final JSONResPonse jsonResPonse){
        new AsyncTask<Void,Void,Void>()
        {
            private String respose = null;
            private Exception exception=null;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    if (!isCancelled()) {

                        OkHttpClient client = new OkHttpClient.Builder().retryOnConnectionFailure(true).connectTimeout(5000, TimeUnit.MILLISECONDS).build();

                        MultipartBody.Builder buildernew = new MultipartBody.Builder()
                                .setType(MultipartBody.FORM);
//                                .addFormDataPart("title", title);   //Here you can add the fix number of data.

                        for (int i = 0; i < params.length; i++) {
                            buildernew.addFormDataPart(params[i], value[i]);
                            Log.i("params["+i+"]",value[i]);
                        }
                        MultipartBody requestBody = buildernew.build();
                        Request request = new Request.Builder().url(BaseUrl+url).post(requestBody).build();
                        Log.i("url",BaseUrl+url);
                        Response response = client.newCall(request).execute();

                        respose = response.body().string();

                        AppLog.info(getClass().getName(), "respose_::" + respose);
//                        Loger.MSG("response", "respose_ww_message::" + response.message());
//                        Loger.MSG("response", "respose_ww_headers::" + response.headers());
//                        Loger.MSG("response", "respose_ww_isRedirect::" + response.isRedirect());
//                       Loger.MSG("response", "respose_ww_body::" + response.body().string());

                    }
                } catch (Exception e) {
                    this.exception=e;
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (!isCancelled() && exception==null) {
                    jsonResPonse.OnSucess(respose);

                }else {
                    jsonResPonse.OnExecption(exception);
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
