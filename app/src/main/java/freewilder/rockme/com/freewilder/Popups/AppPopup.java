package freewilder.rockme.com.freewilder.Popups;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import freewilder.rockme.com.freewilder.R;

/**
 * Created by su on 6/19/17.
 */

public class AppPopup {
    AlertDialog alertDialog;
    AlertDialog.Builder Builder;
    Activity context;

    public interface singleAlertWithoutTitle{
        void OnCancel(AlertDialog dailaog);
    }

    public interface singleAlertWithTitle{
        void OnCancel(AlertDialog dailaog);
    }

    public interface AlertWithOkAction{
        void OnCancel(AlertDialog dailaog);
        void OnOk(AlertDialog dialog,String Response);
    }

    public interface AlertListWithMultiSelect{
        void OnCancel(AlertDialog dailaog);
        void OnOk(AlertDialog dialog,String Response);
    }

    public AppPopup(Activity context) {
        this.context = context;
        Builder=new AlertDialog.Builder(context);

    }

    public void WithOutTitle_singleEvent(String Content, int ViewId, final singleAlertWithoutTitle singleAlertWithoutTitle){
        View view=context.getLayoutInflater().inflate(ViewId,null);
            View Cancel=(View)view.findViewById(R.id.Cancel);
        TextView text=(TextView)view.findViewById(R.id.Text);
        Builder.setView(view);
        text.setText(Html.fromHtml(Content));
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singleAlertWithoutTitle.OnCancel(alertDialog);
            }
        });
        alertDialog=Builder.create();
        alertDialog.show();
    }


}
