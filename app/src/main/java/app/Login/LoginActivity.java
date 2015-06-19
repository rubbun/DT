/*
 *
 *  * Copyright (C) 2014 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package app.Login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.io.File;
import java.io.FilenameFilter;

import app.Dashboard.Dashboard;
import app.Repositories.ClubRepository;
import app.Repositories.DistrictRepository;
import app.Repositories.MemberRepository;
import app.utils.ConnectionDetector;
import app.utils.ShowMessage2User;
import lionsclub.com.directoryapp.R;


public class LoginActivity extends ActionBarActivity implements LoginView, View.OnClickListener {

    public ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private EditText mInt;

    private LoginPresenter presenter;

    private Button requestCredential;

    private Button login;

    private Dialog credentialRequestDialog;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private ViewFlipper mViewFlipper;
    private Animation.AnimationListener mAnimationListener;
    private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());
    public Boolean is2G = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        setContentView(R.layout.activity_login);
       if(getNetworkClass(LoginActivity.this).equalsIgnoreCase("2G")){
            is2G = true;
        }else{
            is2G = false;
        }


        if(getSharedPreferences(getString(R.string.app_name), 1).getBoolean("isLoggedIn",false))
        {
            navigateToHome1();

        }
        else {

        mViewFlipper = (ViewFlipper) findViewById(R.id.districtBanner);
//animation listener
        mAnimationListener = new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
                //animation started event
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                //TODO animation stopped event
            }
        };


        mViewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });

        setFlipperImage();


        mViewFlipper.setAutoStart(true);

        mViewFlipper.setFlipInterval(10000);


        mViewFlipper.startFlipping();

        progressBar = (ProgressBar) findViewById(R.id.progress);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login=(Button)findViewById(R.id.button);
        requestCredential=(Button)findViewById(R.id.requestCredential);

            DistrictRepository.clearDistrict(LoginActivity.this);
            MemberRepository.clearMember(LoginActivity.this);
            ClubRepository.clearClub(LoginActivity.this);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


            progressBar = (ProgressBar) findViewById(R.id.progress);
            username = (EditText) findViewById(R.id.username);
            password = (EditText) findViewById(R.id.password);
            login = (Button) findViewById(R.id.button);
            requestCredential = (Button) findViewById(R.id.requestCredential);


            login.setOnClickListener(this);
            requestCredential.setOnClickListener(this);


            presenter = new LoginPresenterImpl(this, LoginActivity.this);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }
    public AlertDialog OptionDialog;
    @Override
    public void showProgress() {

        if(is2G){
            progressBar.setVisibility(View.GONE);
            //Toast.makeText(getApplicationContext(), "Here11", Toast.LENGTH_LONG).show();
            OptionDialog = new AlertDialog.Builder(LoginActivity.this).create();
            OptionDialog.setMessage("Please Wait While the APP Collects Information.It is Recommended to Login While you are Connected to WiFi / 4G / 3G for Quick Processing.");
            OptionDialog.setCancelable(false);
            OptionDialog.show();


        }else{
           // Toast.makeText(getApplicationContext(), "Here", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        username.setError(getString(R.string.username_error));
    }

    @Override
    public void setPasswordError() {
        password.setError(getString(R.string.password_error));
    }

    @Override
    public void showActivityMessage(String message) {
        new AlertDialog.Builder(LoginActivity.this)
                .setTitle("Directory Online")
                .setMessage(message)
                .setCancelable(true)
                .setNegativeButton("Ok", new  DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                } )
                .show();
       // Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
    }


    @Override
    public void showCredentialRequestDialog()
    {

        credentialRequestDialog =new Dialog(LoginActivity.this);
        credentialRequestDialog.setTitle(R.string.credential_dialog_title);
        credentialRequestDialog.setContentView(R.layout.credential_request_form);
        mInt=(EditText)credentialRequestDialog.findViewById(R.id.mIntET);
        credentialRequestDialog.findViewById(R.id.cancel).setOnClickListener(this);
        credentialRequestDialog.findViewById(R.id.ok).setOnClickListener(this);
        credentialRequestDialog.setCancelable(false);
        credentialRequestDialog.show();

    }

    @Override
    public void dismissCredentialRequestDialog()
    {
        if(credentialRequestDialog!=null)
            credentialRequestDialog.dismiss();
    }

    @Override
    public void navigateToHome() {
        SharedPreferences.Editor editor=getSharedPreferences(getString(R.string.app_name), 1).edit();
        editor.putBoolean("isLoggedIn",true);
        editor.commit();
        Intent intent = new Intent(this, Dashboard.class);
        intent.putExtra("isBanner", false);
        startActivity(intent);
        finish();
    }

    public void navigateToHome1() {
        SharedPreferences.Editor editor=getSharedPreferences(getString(R.string.app_name), 1).edit();
        editor.putBoolean("isLoggedIn",true);
        editor.commit();
        Intent intent = new Intent(this, Dashboard.class);
        intent.putExtra("isBanner", true);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button:
                if (new ConnectionDetector(LoginActivity.this).isConnectingToInternet()) {
                    getSharedPreferences(getString(R.string.app_name), 1).edit().clear().commit();
                    presenter.validateCredentials(username.getText().toString().trim(), password.getText().toString().trim());
                } else {
                    ShowMessage2User.display(LoginActivity.this, getString(R.string.no_internet));
                }
                // presenter.validateCredentials("m444982","tj$k@xo7");
                //new ExportDatabaseFileTask(LoginActivity.this).execute();
                break;
            case R.id.requestCredential:

                showCredentialRequestDialog();

                break;


            case R.id.ok:
                if (new ConnectionDetector(LoginActivity.this).isConnectingToInternet()) {
                    if(mInt.getText().toString().trim().length()>0){
                        presenter.requestCredentials(mInt.getText().toString());
                    }

                } else {
                    ShowMessage2User.display(LoginActivity.this, getString(R.string.no_internet));
                }
            case R.id.cancel:
                dismissCredentialRequestDialog();
                break;

        }
    }

    class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                // right to left swipe
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(LoginActivity.this, R.anim.left_in));
                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(LoginActivity.this, R.anim.left_out));
                    // controlling animation
                    mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
                    mViewFlipper.showNext();
                    return true;
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(LoginActivity.this, R.anim.right_in));
                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(LoginActivity.this,R.anim.right_out));
                    // controlling animation
                    mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
                    mViewFlipper.showPrevious();
                    return true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }
    }

    private void setFlipperImage() {
        FilenameFilter filter = null;
        File[] imagesFileArray = listFiles(Environment.getExternalStorageDirectory().getAbsolutePath()+"/DirectoryOnline/BannerPhoto", filter);
        if (imagesFileArray != null && imagesFileArray.length > 0) {
            for (File imageFile : imagesFileArray) {
                try {
                    Bitmap mBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
                    if (mBitmap != null) {
                        ImageView image = new ImageView(getApplicationContext());
                        image.setImageBitmap(mBitmap);
                        mViewFlipper.addView(image);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public File[] listFiles(String directoryPath, FilenameFilter filter) {


        File[] fileArray = null;
        File file = new File( directoryPath);

        if (filter != null) {
            if (file.exists()) {
                fileArray = file.listFiles();
            }
        } else {
            if (file.exists()) {
                fileArray = file.listFiles();
            }
        }

        return fileArray;
    }

    @Override
    public void onProgressUpdate(int i) {

    }

    public static String getNetworkClass(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if(info==null || !info.isConnected())
            return "-"; //not connected
        if(info.getType() == ConnectivityManager.TYPE_WIFI)
            return "WIFI";
        if(info.getType() == ConnectivityManager.TYPE_MOBILE){
            int networkType = info.getSubtype();
            switch (networkType) {
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                    return "2G";
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                    return "3G";
                case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                    return "4G";
                default:
                    return "?";
            }
        }
        return "?";
    }
}
