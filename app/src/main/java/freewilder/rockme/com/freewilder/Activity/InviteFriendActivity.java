package freewilder.rockme.com.freewilder.Activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.TimeZone;
import freewilder.rockme.com.freewilder.Json.URLPaser;
import freewilder.rockme.com.freewilder.R;
import freewilder.rockme.com.freewilder.adapters.AdapterContactList;
import freewilder.rockme.com.freewilder.customlistview.IndexableListView;
import freewilder.rockme.com.freewilder.helper.AppController;
import freewilder.rockme.com.freewilder.helper.PermissionUtil;
import freewilder.rockme.com.freewilder.pojo.ContactList;

/**
 * Created by su on 6/26/17.
 */

public class InviteFriendActivity extends AppCompatActivity {

    AlertDialog alertDialog;

    private static final int REQUEST_CONTACTS = 1;

    int count=0;

    String InvitionEmailIDs = "";

    AdapterContactList Adapter;
    IndexableListView listviewContact;

    private static String[] PERMISSIONS_CONTACT = {
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private static final String[] PROJECTION = new String[]{
            ContactsContract.CommonDataKinds.Email.CONTACT_ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Email.DATA,
            ContactsContract.Contacts.PHOTO_THUMBNAIL_URI
    };

    static final String SELECTION = "((" +
            ContactsContract.Data.DISPLAY_NAME + " NOTNULL) AND (" +
            ContactsContract.Data.DISPLAY_NAME + " != '' ))";

    String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";

    ImageView img_more,serachA;

    private View mLayout;

    SharedPreferences preferences;

    LinearLayout LLSearch,back3;

    EditText EDXfind;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friend);
        img_more= (ImageView) findViewById(R.id.img_more);

        LLSearch = (LinearLayout) findViewById(R.id.LLSearch);

        mLayout = findViewById(R.id.background);

        listviewContact = (IndexableListView) findViewById(R.id.lvToDoList);

        serachA= (ImageView) findViewById(R.id.serachA);
        EDXfind = (EditText) findViewById(R.id.EDXfind);
        back3 = (LinearLayout) findViewById(R.id.back3);


        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LLSearch.setVisibility(View.GONE);
                EDXfind.setText("");

                // User has pressed Back key. So hide the keyboard
                InputMethodManager mgr = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(EDXfind.getWindowToken(), 0);
                // TODO: Hide your view as you do it in your activity
            }
        });


        serachA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LLSearch.setVisibility(View.VISIBLE);
                EDXfind.setText("");
                EDXfind.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(InviteFriendActivity.this.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,0);
            }
        });

        EDXfind.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.i("TOUCH",""+motionEvent.getAction());
                EDXfind.clearFocus();
                EDXfind.requestFocus();
                return false;
            }
        });


        EDXfind.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Adapter.getFilter().filter(s.toString());
            }


            @Override
            public void afterTextChanged(Editable s) {


            }
        });

        img_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popup = new PopupMenu(InviteFriendActivity.this, img_more);
                popup.getMenuInflater().inflate(R.menu.invite_frnd_menu, popup.getMenu());


                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().toString().equals(getResources().getString(R.string.select_all))) {
                            for (int i = 0; i < AppController.getInstance().ContactListWithEmailID.size(); i++) {
                                AppController.getInstance().ContactListWithEmailID.get(i).setCheck(true);
                            }
                            Adapter.notifyDataSetChanged();
                            //Toast.makeText(InviteFriendActivity.this,"Select all",Toast.LENGTH_SHORT).show();
                        }
                        else if(item.getTitle().toString().equals(getResources().getString(R.string.deselect_all))){
                            for (int i = 0; i < AppController.getInstance().ContactListWithEmailID.size(); i++) {
                                AppController.getInstance().ContactListWithEmailID.get(i).setCheck(false);
                            }
                            Adapter.notifyDataSetChanged();
                            //Toast.makeText(InviteFriendActivity.this,"Deselect all",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            //Toast.makeText(InviteFriendActivity.this,"Invite new Friend",Toast.LENGTH_SHORT).show();
                            createCustomeInviteAlert();
                        }
                        return true;
                    }
                });

                popup.show();

            }
        });

        if ((int) Build.VERSION.SDK_INT < 23) {

            RUNAPP_PROCESS();

            return;
        } else {
            Log.d("VERSION", "MARSHMALLOW");
            CheckContactPermission();
        }


    }

    public void createCustomeInviteAlert(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_invite_friend, null);
        dialogBuilder.setView(dialogView);

        final AppCompatEditText input_email= (AppCompatEditText) dialogView.findViewById(R.id.input_email);
        TextView tv_send_invite= (TextView) dialogView.findViewById(R.id.tv_send_invite);
        final TextInputLayout input_layout_email= (TextInputLayout) dialogView.findViewById(R.id.input_layout_email);

        ImageView img_cross= (ImageView) dialogView.findViewById(R.id.img_cross);

        alertDialog= dialogBuilder.create();
        //To prevent dialog box from getting dismissed on back key pressed use this
        alertDialog.setCancelable(false);

        //And to prevent dialog box from getting dismissed on outside touch use this
        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.show();

        img_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

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
                        SEND_INVITION_BY_MAIL(input_email.getText().toString()+",");
                    }
                }
            }
        });


    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private void RUNAPP_PROCESS() {
       // ProgresDilog.show();
        preferences = PreferenceManager.getDefaultSharedPreferences(InviteFriendActivity.this);
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, PROJECTION, SELECTION, null, sortOrder);
        int TotaContactNo = cursor.getCount();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("CONTACTNO", TotaContactNo);
        editor.apply();
        editor.commit();

        if (cursor != null) {
            try {
                final int contactIdIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.CONTACT_ID);
                final int displayNameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                final int emailIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA);
                final int photo = cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI);
                long contactId;
                String displayName, address, photouri;
                while (cursor.moveToNext()) {
                    contactId = cursor.getLong(contactIdIndex);
                    displayName = cursor.getString(displayNameIndex);
                    address = cursor.getString(emailIndex);
                    photouri = cursor.getString(photo);
                    ContactList contactList = new ContactList();
                    contactList.setEmailID(address);
                    contactList.setName(displayName);
                    contactList.setPhotoUri(photouri);
//                    DATA.EMAILLIST_insert();
                    AppController.getInstance().ContactListWithEmailID.add(contactList);
                    Log.e("LIST-", contactId + "\n" + displayName + "\n" + address + "\n" + photouri);
                    Log.e("ARARYSOZE-", "" + AppController.getInstance().ContactListWithEmailID.size());
                }
                Adapter = new AdapterContactList(this, 0, AppController.getInstance().ContactListWithEmailID);
                listviewContact.setAdapter(Adapter);
                listviewContact.setFastScrollEnabled(true);
                listviewContact.setFastScrollAlwaysVisible(true);

            } finally {
                cursor.close();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //ProgresDilog.dismiss();
                    }
                },200);
            }
        }
    }


    private void CheckContactPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Log.i("", "Contact permissions has NOT been granted. Requesting permissions.");
            requestContactsPermissions();

        } else {
            RUNAPP_PROCESS();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == REQUEST_CONTACTS) {
            Log.i("", "Received response for contact permissions request.");


            if (PermissionUtil.verifyPermissions(grantResults)) {

                Snackbar.make(mLayout, getResources().getString(R.string.read_contact_permission_available),
                        Snackbar.LENGTH_SHORT)
                        .show();

                RUNAPP_PROCESS();
            } else {
                Log.i("", "Contacts permissions were NOT granted.");
                Snackbar.make(mLayout, getResources().getString(R.string.need_to_contact_permission_for_this_app),
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction(getResources().getString(R.string.ok), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ActivityCompat
                                        .requestPermissions(InviteFriendActivity.this, PERMISSIONS_CONTACT,
                                                REQUEST_CONTACTS);
                            }
                        })
                        .show();
            }

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void requestContactsPermissions() {
        if (
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_CONTACTS)
                        || ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.WRITE_CONTACTS)
                        && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED)
        {

            Log.i("", "Displaying contacts permission rationale to provide additional context.");
            Snackbar.make(mLayout,getResources().getString(R.string.need_to_contact_permission_for_this_app),
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(getResources().getString(R.string.ok), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat
                                    .requestPermissions(InviteFriendActivity.this, PERMISSIONS_CONTACT,
                                            REQUEST_CONTACTS);
                        }
                    })
                    .show();
        } else {
            ActivityCompat.requestPermissions(this, PERMISSIONS_CONTACT, REQUEST_CONTACTS);
            Log.d("CALL FOR", "REQUEST");
        }
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }


    public void send_invite(View SendInvite)
    {
        InvitionEmailIDs="";
        count=0;

        if ( AppController.getInstance().ContactListWithEmailID!=null) {
            for (int i = 0; i <  AppController.getInstance().ContactListWithEmailID.size(); i++) {
                if( AppController.getInstance().ContactListWithEmailID.get(i).isCheck()) {
                    Log.d("CJE", "" + AppController.getInstance().ContactListWithEmailID.get(i).isCheck());
                    InvitionEmailIDs = InvitionEmailIDs + AppController.getInstance().ContactListWithEmailID.get(i).getEmailID() + ",";
                    count++;
                }
            }
            Log.d("EMAIL=", InvitionEmailIDs);
            if (count>0) {
                SEND_INVITION_BY_MAIL(InvitionEmailIDs);
            }else {
                Toast.makeText(InviteFriendActivity.this, getResources().getString(R.string.chose_contacts), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void SEND_INVITION_BY_MAIL(final String invitionEmailIDs) {
    ////////////////////////////////////add by suraj shaw//////////////////////////////////////

        TimeZone tz=TimeZone.getDefault();
        String url = "app_user_service/app_invite_email?"
                + "&cur_id="+AppController.Curency+"&lang_id="+AppController.Lang_id+"&time_zone="+tz.getID();

        String params[]={"email","user_id"};
        String values[]={"" + invitionEmailIDs.substring(0, (invitionEmailIDs.length() - 1)),AppController.userid};

        ///////////////////////////////////////////////////////////////////////////////////

        Log.d("URL", url + "\n" + invitionEmailIDs);
        new URLPaser().onPostMethod(url,params,values, new URLPaser.JSONResPonse(){

            @Override
            public void OnSucess(String Response) {
                try {
                    JSONObject jsonObject=new JSONObject(Response);
                    Log.i("jsonObject",""+jsonObject);

                    Toast.makeText(InviteFriendActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                    for (int i=0;i<AppController.getInstance().ContactListWithEmailID.size();i++) {
                        AppController.getInstance().ContactListWithEmailID.get(i).setCheck(false);
                    }
                    Adapter.notifyDataSetChanged();
                    InvitionEmailIDs = "";
                    count=0;



                    if(jsonObject.getString("response").equals("success"))
                    {
                        if (alertDialog != null) {
                            alertDialog.dismiss();
                            alertDialog = null;
                        }
                    }
                    else {

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("JSONException",""+e.getMessage());
                }
            }

            @Override
            public void OnExecption(Exception ex) {

            }

            @Override
            public void OnFailed(String error) {

            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        // User has pressed Back key. So hide the keyboard
        InputMethodManager mgr = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(EDXfind.getWindowToken(), 0);
        // TODO: Hide your view as you do it in your activity
    }
}
