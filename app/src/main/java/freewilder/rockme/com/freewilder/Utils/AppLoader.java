package freewilder.rockme.com.freewilder.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import freewilder.rockme.com.freewilder.R;

/**
 * Created by su on 6/20/17.
 */

public class AppLoader {
    Activity activity;
    AlertDialog Dialog;

    public AppLoader(Activity activity) {
        this.activity = activity;
        View view=activity.getLayoutInflater().inflate(R.layout.loaderview,null);
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        builder.setView(view);
        Dialog=builder.create();
        Dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Dialog.setCanceledOnTouchOutside(false);

    }
    public void Show(){
        Dialog.show();
    }
    public void Dissmiss(){
        Dialog.dismiss();
    }
}
