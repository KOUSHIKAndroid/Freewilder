package freewilder.rockme.com.freewilder.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import freewilder.rockme.com.freewilder.R;

/**
 * Created by su on 6/26/17.
 */

public class InviteFriendActivity extends AppCompatActivity {
    ImageView img_more;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friend);
        img_more= (ImageView) findViewById(R.id.img_more);

        img_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popup = new PopupMenu(InviteFriendActivity.this, img_more);
                popup.getMenuInflater().inflate(R.menu.invite_frnd_menu, popup.getMenu());


                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().toString().equals(getResources().getString(R.string.select_all))) {
                            Toast.makeText(InviteFriendActivity.this,"Select all",Toast.LENGTH_SHORT).show();
                        }
                        else if(item.getTitle().toString().equals(getResources().getString(R.string.deselect_all))){
                            Toast.makeText(InviteFriendActivity.this,"Deselect all",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(InviteFriendActivity.this,"Invite new Friend",Toast.LENGTH_SHORT).show();
                            createCustomeInviteAlert();
                        }
                        return true;
                    }
                });

                popup.show();

            }
        });
    }

    public void createCustomeInviteAlert(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_invite_friend, null);
        dialogBuilder.setView(dialogView);

        final AppCompatEditText input_email= (AppCompatEditText) dialogView.findViewById(R.id.input_email);
        TextView tv_send_invite= (TextView) dialogView.findViewById(R.id.tv_send_invite);
        final TextInputLayout input_layout_email= (TextInputLayout) dialogView.findViewById(R.id.input_layout_email);

        tv_send_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input_email.getText().toString().equals("")){
                    input_layout_email.setErrorEnabled(true);
                    input_layout_email.setError("Enter email address..");
                }
                else {
                    input_layout_email.setErrorEnabled(false);

                    if (!isValidEmail(input_email.getText().toString())){
                        input_layout_email.setErrorEnabled(true);
                        input_layout_email.setError("Enter right address..");
                    }else {
                        input_layout_email.setErrorEnabled(false);
                        Toast.makeText(InviteFriendActivity.this,"submit",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
