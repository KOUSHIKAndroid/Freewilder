package freewilder.rockme.com.freewilder.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import freewilder.rockme.com.freewilder.R;
import freewilder.rockme.com.freewilder.fragments.BirthdaySignUpFragment;
import freewilder.rockme.com.freewilder.fragments.EmailPasswordSignUpFragment;
import freewilder.rockme.com.freewilder.fragments.GenderSignUpFragment;
import freewilder.rockme.com.freewilder.fragments.NameSignUpFragment;

public class SignUpActivity extends AppCompatActivity {

    TextView tv_toolText;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    AppCompatImageView img_back;

    LinearLayout LLToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        tv_toolText= (TextView) findViewById(R.id.tv_toolText);
        img_back= (AppCompatImageView) findViewById(R.id.img_back);
        LLToolbar= (LinearLayout) findViewById(R.id.LLToolbar);

        //LLToolbar.setPadding(0, getStatusBarHeight(), 0, 0);

        fragmentManager=getSupportFragmentManager();

        fragmentTransaction=fragmentManager.beginTransaction();
        NameSignUpFragment fragment = new NameSignUpFragment();
        tv_toolText.setText(getResources().getString(R.string.sign_up_name));
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void nextPage(int check){
        switch (check) {
            case 1:
                tv_toolText.setText(getResources().getString(R.string.sign_up_email_password));
                EmailPasswordSignUpFragment f1 = new EmailPasswordSignUpFragment();
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, f1);
                fragmentTransaction.commit();
                break;

            case 2:
                tv_toolText.setText(getResources().getString(R.string.sign_up_gender));
                GenderSignUpFragment f2 = new GenderSignUpFragment();
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, f2);
                fragmentTransaction.commit();
                break;
            case 3:
                tv_toolText.setText(getResources().getString(R.string.sign_up_birthday));
                BirthdaySignUpFragment f3 = new BirthdaySignUpFragment();
                fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, f3);
                fragmentTransaction.commit();
                break;
        }

    }
    // A method to find height of the status bar
//    public int getStatusBarHeight() {
//        int result = 0;
//        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
//        if (resourceId > 0) {
//            result = getResources().getDimensionPixelSize(resourceId);
//        }
//        return result;
//    }
}