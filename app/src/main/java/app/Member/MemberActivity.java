package app.Member;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.regex.Pattern;

import app.Dashboard.MemberDataHolder;
import app.Login.HttpClientGet;
import app.Login.LoginActivity;
import app.Repositories.MemberRepository;
import app.WebService.BDService;
import app.WebService.GCPResponse;
import app.WebService.MergedResponse;
import app.util.ImageLoader;
import app.utils.ConnectionDetector;
import lionsclub.com.directoryapp.Member;
import lionsclub.com.directoryapp.MemberDao;
import lionsclub.com.directoryapp.R;


//http://www.onlinesmslogin.com/quicksms/api.php?username=rrredy&password=arun007&to=9804578348&from=9676160002&message=hiiiiii
public class MemberActivity extends Activity implements MemberView, View.OnClickListener {
    EditText individual_memberName, individual_clubName, individual_mobileno, individual_intnumber, individual_joinDate, individual_profession, individual_dob, individual_marriageDay,
            individual_spouce, individual_residenceAdd, individual_businessAdd, individual_phone, individual_altPhone, individual_altMobile,
            individual_email, individual_altEmail, individual_city, individual_lDistrict, individual_aboutMe;
    long memberId = 0;
    Button updateProfile, editProfile;
    Member member_details;
    ImageView iv_user_image;
    ImageLoader inImageLoader;
    File memberPhotoDirectory = new File(Environment.getExternalStorageDirectory(), "DirectoryOnline/MemberPhoto/");
    LinearLayout ll_arrow;
    private boolean isEditEnable = false;
    private DatePickerDialog datePickerDialog, datePickerDialog1, datePickerDialog2;
    Calendar newCalendar;
    LinearLayout ll_username, ll_password;
    boolean isCredential = false;
    private EditText individual_username, individual_password;
    boolean isAdmin;
    app.WebService.MemberProfile memberProfile;
    // String MemId;
    public static final int REQUEST_CODE_GALLERY = 0x1;
    public static final int REQUEST_CODE_TAKE_PICTURE = 0x2;

    public boolean isEditModeOn = false;
    public String base64Image = "";
    public ImageView ivCaptcha;
    public Button BtnRefresh;
    private LinearLayout llCaptcha;
    private View view4;
    private EditText etCaptchaValue;
    private String captaId;
    private String dob_formated, marrage_date_formated;
    private Bitmap prifileImage;
    private String username, password;
    private Button btnSendCred;
    private LinearLayout ll_updatecred;
    private  Button btnUserName,btnPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member1);


        username = getSharedPreferences(getString(R.string.app_name), 1).getString("u1", "1234");
        password = getSharedPreferences(getString(R.string.app_name), 1).getString("u2", "1234");
        newCalendar = Calendar.getInstance();
        if (getIntent().getExtras().get("memberData") != null) {
            memberId = getIntent().getExtras().getLong("memberData", 0);
            isCredential = getIntent().getExtras().getBoolean("cre", false);
        }
        //  MemId=  getSharedPreferences(getString(R.string.app_name), 1).getString("MemId", "1234");
        llCaptcha = (LinearLayout) findViewById(R.id.llCaptcha);
        view4 = (View) findViewById(R.id.view4);
        llCaptcha.setVisibility(View.GONE);
        view4.setVisibility(View.GONE);
        etCaptchaValue = (EditText) findViewById(R.id.etCaptchaValue);
        ivCaptcha = (ImageView) findViewById(R.id.ivCaptcha);
        BtnRefresh = (Button) findViewById(R.id.BtnRefresh);
        BtnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetCaptcha().execute();
            }
        });
        isAdmin = getSharedPreferences(getString(R.string.app_name), 1).getBoolean("isAdmin", false);
        inImageLoader = new ImageLoader(MemberActivity.this);
        individual_memberName = (EditText) findViewById(R.id.individual_memberName);
        individual_clubName = (EditText) findViewById(R.id.individual_clubName);
        individual_mobileno = (EditText) findViewById(R.id.individual_mobileno);
        individual_intnumber = (EditText) findViewById(R.id.individual_intnumber);
        individual_joinDate = (EditText) findViewById(R.id.individual_joinDate);
        individual_profession = (EditText) findViewById(R.id.individual_profession);
        individual_dob = (EditText) findViewById(R.id.individual_dob);

        individual_marriageDay = (EditText) findViewById(R.id.individual_marriageDay);
        individual_spouce = (EditText) findViewById(R.id.individual_spouce);
        individual_residenceAdd = (EditText) findViewById(R.id.individual_residenceAdd);
        individual_businessAdd = (EditText) findViewById(R.id.individual_businessAdd);
        individual_phone = (EditText) findViewById(R.id.individual_phone);
        individual_altPhone = (EditText) findViewById(R.id.individual_altPhone);
        individual_altMobile = (EditText) findViewById(R.id.individual_altMobile);
        individual_email = (EditText) findViewById(R.id.individual_email);
        individual_altEmail = (EditText) findViewById(R.id.individual_altEmail);
        individual_city = (EditText) findViewById(R.id.individual_city);
        individual_lDistrict = (EditText) findViewById(R.id.individual_lDistrict);
        individual_aboutMe = (EditText) findViewById(R.id.individual_aboutMe);

        iv_user_image = (ImageView) findViewById(R.id.iv_user_image);

        ll_arrow = (LinearLayout) findViewById(R.id.ll_arrow);

        ll_updatecred = (LinearLayout)findViewById(R.id.ll_updatecred);
        btnUserName = (Button)findViewById(R.id.btnUserName);
        btnPassword = (Button)findViewById(R.id.btnPassword);
        btnUserName.setOnClickListener(this);
        btnPassword.setOnClickListener(this);
        ll_arrow.setOnClickListener(this);

        btnSendCred = (Button) findViewById(R.id.btnSendCred);

        memberProfile = new app.WebService.MemberProfile();
        updateProfile = (Button) findViewById(R.id.updateProfile);
        editProfile = (Button) findViewById(R.id.editProfile);
        updateProfile.setOnClickListener(this);
        editProfile.setOnClickListener(this);
        member_details = MemberRepository.getMemberForId(MemberActivity.this, memberId);
        Log.e("PATH ", "http://alphatest.in/uploads/MemberPhotos/" + member_details.getProfilePicUrl());

        File mProfileImage = new File(memberPhotoDirectory, member_details.getProfileImage());

        if (mProfileImage.exists() && !isOnline()) {
            BitmapFactory.Options bmpOptions = new BitmapFactory.Options();
            bmpOptions.inSampleSize = 2;
            Bitmap imageBitmap = BitmapFactory.decodeFile(mProfileImage.getAbsolutePath(), bmpOptions);
            if (imageBitmap != null) {
                iv_user_image.setImageBitmap(imageBitmap);
            }

        }

        //Log.e("username",member_details.getUserName());
        //Log.e("username",member_details.getPassword());

        //  inImageLoader.DisplayImage("http://alphatest.in/uploads/MemberPhotos/"+member_details.getProfileImage(),iv_user_image);
        individual_memberName.setText(member_details.getName() == null ? "Not Available" : member_details.getName());
        individual_clubName.setText(member_details.getClubName() == null ? "Not Available" : member_details.getClubName());
        individual_clubName.setEnabled(false);
        individual_mobileno.setText(member_details.getMobile() == null ? "Not Available" : member_details.getMobile());
        individual_intnumber.setText(member_details.getMIntNum() == null ? "Not Available" : "" + member_details.getMIntNum());
        individual_joinDate.setText(member_details.getJoinDate() == null ? "Not Available" : member_details.getJoinDate());
        individual_profession.setText(member_details.getProfession() == null ? "Not Available" : member_details.getProfession());
        individual_dob.setText(member_details.getDob().equalsIgnoreCase("2/29/2012") ? "Not Available" : member_details.getDob());


        dob_formated = member_details.getDob().equalsIgnoreCase("2/29/2012") ? "2/29/2012" : member_details.getDob();

        System.out.println("dob_formated " + dob_formated);
        individual_marriageDay.setText(member_details.getMarriageDate().equalsIgnoreCase("2/29/2012") ? "Not Available" : member_details.getMarriageDate());
        marrage_date_formated = member_details.getMarriageDate().equalsIgnoreCase("2/29/2012") ? "2/29/2012" : member_details.getMarriageDate();


        individual_spouce.setText(member_details.getSpouse() == null ? "Not Available" : member_details.getSpouse().equalsIgnoreCase("null") ? "Not Available" : member_details.getSpouse());


        individual_residenceAdd.setText(member_details.getResidentialAddress() == null ? "Not Available" : member_details.getResidentialAddress().equalsIgnoreCase("null") ? "Not Available" : member_details.getResidentialAddress());
        individual_businessAdd.setText(member_details.getBusinessAddress() == null ? "Not Available" : member_details.getBusinessAddress());
        individual_phone.setText(member_details.getPhone() == null ? "Not Available" : member_details.getPhone());
        individual_altPhone.setText(member_details.getAltPhone() == null ? "Not Available" : member_details.getAltPhone());
        individual_altMobile.setText(member_details.getAltMobile() == null ? "Not Available" : member_details.getAltMobile());
        individual_email.setText(member_details.getEmail() == null ? "Not Available" : member_details.getEmail());
        individual_altEmail.setText(member_details.getAltEmail() == null ? "Not Available" : member_details.getAltEmail());
        individual_city.setText(member_details.getCity() == null ? "Not Available" : member_details.getCity());
        individual_lDistrict.setText(member_details.getDistrict() == null ? "Not Available" : member_details.getDistrict());
        individual_aboutMe.setText(member_details.getAboutMe() == null ? "Not Available" : member_details.getAboutMe());


        individual_marriageDay.setKeyListener(null);
        individual_joinDate.setKeyListener(null);
        individual_dob.setKeyListener(null);


        if (!member_details.getCanEdit()) {
            editProfile.setVisibility(View.GONE);
        }

        individual_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEditEnable) {
                    datePickerDialog.setTitle("DOB");
                    datePickerDialog.show();
                }
            }
        });

        individual_marriageDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datePickerDialog1.setTitle("Marriage Day");
                datePickerDialog1.show();
            }
        });

        individual_joinDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              /*  datePickerDialog2.setTitle("Join Day");
                datePickerDialog2.show();*/
            }
        });

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {


            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                //toDateEtxt.setText(dateFormatter.format(newDate.getTime()));
                String s = getDay(dayOfMonth) + " " + getMonth(monthOfYear + 1) + " " + year;
                individual_dob.setText(s);
                dob_formated = (monthOfYear + 1) + "/" + dayOfMonth + "/" + year;
            }


        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                String s = getDay(dayOfMonth) + " " + getMonth(monthOfYear + 1) + " " + year;
                individual_marriageDay.setText(s);
                marrage_date_formated = (monthOfYear + 1) + "/" + dayOfMonth + "/" + year;
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog2 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                String s = getDay(dayOfMonth) + " " + getMonth(monthOfYear + 1) + " " + year;
                individual_joinDate.setText(s);
                //toDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        ll_username = (LinearLayout) findViewById(R.id.ll_username);
        ll_password = (LinearLayout) findViewById(R.id.ll_password);
        individual_username = (EditText) findViewById(R.id.individual_username);
        individual_password = (EditText) findViewById(R.id.individual_password);
        /*if(!isCredential){
            ll_username.setVisibility(View.GONE);
            ll_password.setVisibility(View.GONE);
            ((View)findViewById(R.id.view1)).setVisibility(View.GONE);
            ((View)findViewById(R.id.view2)).setVisibility(View.GONE);
        }
        isProfileEditable(false);

        if(isCredential){
            editProfile.setVisibility(View.VISIBLE);
        }

        if(isAdmin){
            editProfile.setVisibility(View.VISIBLE);
            btnSendCred.setVisibility(View.VISIBLE);
        }else{
            editProfile.setVisibility(View.GONE);
            btnSendCred.setVisibility(View.GONE);
        }*/


        if (!isCredential) {
            ll_username.setVisibility(View.GONE);
            ll_password.setVisibility(View.GONE);
            ((View) findViewById(R.id.view1)).setVisibility(View.GONE);
            ((View) findViewById(R.id.view2)).setVisibility(View.GONE);
        }
        isProfileEditable(false);

        if (isCredential) {
            editProfile.setVisibility(View.VISIBLE);
        }

        if (isAdmin) {
            btnSendCred.setVisibility(View.VISIBLE);
            editProfile.setVisibility(View.VISIBLE);
        } else {
            btnSendCred.setVisibility(View.GONE);
        }

        File mProfileImage1 = new File(memberPhotoDirectory, member_details.getProfileImage());

        if (mProfileImage1.exists()) {
            BitmapFactory.Options bmpOptions = new BitmapFactory.Options();
            bmpOptions.inSampleSize = 2;
            Bitmap imageBitmap = BitmapFactory.decodeFile(mProfileImage.getAbsolutePath(), bmpOptions);
            if (imageBitmap != null) {
                iv_user_image.setImageBitmap(imageBitmap);
            }

            if (new ConnectionDetector(MemberActivity.this).isConnectingToInternet() ) {
                if(!getNetworkClass(MemberActivity.this).equalsIgnoreCase("2G")){
                    mProfileImage1.delete();
                    new DownloadProfileImage(member_details.getProfileImage(), member_details.getMIntNum()).execute();
                }

            }


        } else {
            if (new ConnectionDetector(MemberActivity.this).isConnectingToInternet()) {
                if(!getNetworkClass(MemberActivity.this).equalsIgnoreCase("2G")){
                    new DownloadProfileImage(member_details.getProfileImage(), member_details.getMIntNum()).execute();
                }

            }
        }

        iv_user_image.setOnClickListener(this);

        String ss[] = member_details.getPassword().split(Pattern.quote("~"));
        System.out.println(ss[0] + "    " + ss[1]);

        colorChange();
        colorChangeFaterUpdate();

        btnSendCred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!isOnline()){
                    new AlertDialog.Builder(MemberActivity.this)
                            .setCancelable(false)
                            .setTitle("Alert")
                            .setMessage("You do not have internet connection")
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .show();
                    return;
                }

                new AlertDialog.Builder(MemberActivity.this)
                        .setCancelable(false)
                        .setTitle("Alert")
                        .setMessage("Do you want to send credential by SMS?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                if ((individual_mobileno.getText().toString().trim().length() >= 10)&& (!individual_mobileno.getText().toString().contains("Not"))) {
                                    Smsusername = getSharedPreferences(getString(R.string.app_name), 1).getString("su1", "");
                                    Smspassword = getSharedPreferences(getString(R.string.app_name), 1).getString("su2", "");

                                    if (Smsusername.length() > 0) {
                                        String s[] = member_details.getPassword().split("~");
                                        String s1 = Smsusername;
                                        String s2 = Smspassword;
                                        String s3 = member_details.getMobile();
                                        String s4 = "Dear " + member_details.getName() + ", your User name: " + s[0] + " and password: " + s[1];
                                        new SendSMS().execute(s1, s2, s3, s4);


                                    } else {
                                        View v = View.inflate(MemberActivity.this, R.layout.sms_dlg, null);
                                        final EditText sms_edit = (EditText) v.findViewById(R.id.sms_user_name);
                                        final EditText sms_pass = (EditText) v.findViewById(R.id.sms_passowrd);

                                        new AlertDialog.Builder(MemberActivity.this)
                                                .setCancelable(false)
                                                .setView(v)
                                                .setTitle("SMS CREDENTIAL")
                                                .setNegativeButton("Submit", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        if (sms_edit.getText().toString().trim().length() > 0) {
                                                            if (sms_pass.getText().toString().trim().length() > 0) {
                                                                SharedPreferences appPreference1 = getSharedPreferences(getString(R.string.app_name), 1);
                                                                SharedPreferences.Editor appPrefEditor1 = appPreference1.edit();
                                                                appPrefEditor1.putString("su1", sms_edit.getText().toString().trim());
                                                                appPrefEditor1.putString("su2", sms_pass.getText().toString().trim());
                                                                appPrefEditor1.commit();


                                                                String s[] = member_details.getPassword().split("~");
                                                                String s1 = sms_edit.getText().toString();
                                                                String s2 = sms_pass.getText().toString();
                                                                String s3 = member_details.getMobile();
                                                                String s4 = "Dear " + member_details.getName() + ", your User name: " + s[0] + " and password: " + s[1];

                                                                dialogInterface.dismiss();

                                                                new SendSMS().execute(s1, s2, s3, s4);

                                                            } else {
                                                                Toast.makeText(getApplicationContext(), "Please enter SMS Gateway password", Toast.LENGTH_LONG).show();
                                                            }

                                                        } else {
                                                            Toast.makeText(getApplicationContext(), "Please enter SMS Gateway username", Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                })
                                                .show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "Member Mobile No. Invalid ", Toast.LENGTH_LONG).show();
                                }

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .show();


            }
        });

        System.out.println("iiiii "+member_details.getUserName());


        String s[] = member_details.getPassword().split("~");
        if(s[0].equalsIgnoreCase(username)){
            ll_updatecred.setVisibility(View.VISIBLE);
        }else{
            ll_updatecred.setVisibility(View.GONE);
        }

        btnUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOnline()){
                    Intent intent = new Intent(MemberActivity.this, UpdateUsernameActivity.class);
                    startActivity(intent);
                }else{
                    new AlertDialog.Builder(MemberActivity.this)
                            .setCancelable(false)
                            .setTitle("Error")
                            .setMessage("Please connect to internet")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();

                                }
                            })
                            .show();
                }



            }
        });

        btnPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOnline()) {
                    Intent intent = new Intent(MemberActivity.this, UpdatePasswordActivity.class);
                    startActivity(intent);
                }else{
                    new AlertDialog.Builder(MemberActivity.this)
                            .setCancelable(false)
                            .setTitle("Error")
                            .setMessage("Please connect to internet")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();

                                }
                            })
                            .show();
                }
            }
        });



    }

    public String Smsusername, Smspassword;

    public void isProfileEditable(boolean canEdit) {
        individual_memberName.setEnabled(canEdit);
        individual_clubName.setEnabled(canEdit);
        individual_clubName.setEnabled(canEdit);
        individual_mobileno.setEnabled(canEdit);
        individual_intnumber.setEnabled(canEdit);
        individual_joinDate.setEnabled(canEdit);
        individual_profession.setEnabled(canEdit);
        individual_dob.setEnabled(canEdit);
        individual_marriageDay.setEnabled(canEdit);
        individual_spouce.setEnabled(canEdit);
        individual_residenceAdd.setEnabled(canEdit);
        individual_businessAdd.setEnabled(canEdit);
        individual_phone.setEnabled(canEdit);
        individual_altPhone.setEnabled(canEdit);
        individual_altMobile.setEnabled(canEdit);
        individual_email.setEnabled(canEdit);
        individual_altEmail.setEnabled(canEdit);
        individual_city.setEnabled(canEdit);
        individual_lDistrict.setEnabled(canEdit);
        individual_aboutMe.setEnabled(canEdit);
        individual_username.setEnabled(canEdit);
        individual_password.setEnabled(canEdit);

        individual_intnumber.setEnabled(false);
        individual_joinDate.setEnabled(false);
        individual_clubName.setEnabled(false);
        individual_lDistrict.setEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_member, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void updatePassWord() {

    }

    @Override
    public void updateProfile() {
        // MemberRepository.insertOrUpdate(MemberActivity.this, entityMember);
    }

    @Override
    public void showMessage(String message) {

    }

    Member memberDataToBeUpdated;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_user_image:
                if (isEditModeOn) {
                    captureImage();
                }
                break;
            case R.id.updateProfile:

                if (isvalid()) {
                   /* updatedProf();*/
                    llCaptcha.setVisibility(View.GONE);
                    view4.setVisibility(View.GONE);
                    editProfile.setVisibility(View.VISIBLE);
                    updateProfile.setVisibility(View.GONE);



                    isEditModeOn = false;
                    isProfileEditable(false);
                    new Update().execute();
                }

                break;
            case R.id.editProfile:

                if(!isOnline()){
                    new AlertDialog.Builder(MemberActivity.this)
                            .setCancelable(false)
                            .setTitle("Alert")
                            .setMessage("You do not have internet connection")
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .show();
                    return;
                }

                new GetCaptcha().execute();
                etCaptchaValue.setText("");
                llCaptcha.setVisibility(View.VISIBLE);
                view4.setVisibility(View.VISIBLE);
                isEditModeOn = true;
                isProfileEditable(true);
                isEditEnable = true;
                editProfile.setVisibility(View.GONE);
                updateProfile.setVisibility(View.VISIBLE);
                colorChange();
                break;

            case R.id.ll_arrow:

                finish();

                break;
        }
    }

    public void colorChange() {
        individual_memberName.setBackgroundColor(Color.parseColor("#b3b3b3"));
        // individual_clubName.setBackgroundColor(Color.parseColor("#b3b3b3"));

        individual_mobileno.setBackgroundColor(Color.parseColor("#b3b3b3"));
        //individual_intnumber.setBackgroundColor(Color.parseColor("#b3b3b3"));
        // individual_joinDate.setBackgroundColor(Color.parseColor("#b3b3b3"));
        individual_profession.setBackgroundColor(Color.parseColor("#b3b3b3"));
        individual_dob.setBackgroundColor(Color.parseColor("#b3b3b3"));

        individual_marriageDay.setBackgroundColor(Color.parseColor("#b3b3b3"));
        individual_spouce.setBackgroundColor(Color.parseColor("#b3b3b3"));
        individual_residenceAdd.setBackgroundColor(Color.parseColor("#b3b3b3"));
        individual_businessAdd.setBackgroundColor(Color.parseColor("#b3b3b3"));
        individual_phone.setBackgroundColor(Color.parseColor("#b3b3b3"));
        individual_altPhone.setBackgroundColor(Color.parseColor("#b3b3b3"));
        individual_altMobile.setBackgroundColor(Color.parseColor("#b3b3b3"));
        individual_email.setBackgroundColor(Color.parseColor("#b3b3b3"));
        individual_altEmail.setBackgroundColor(Color.parseColor("#b3b3b3"));
        individual_city.setBackgroundColor(Color.parseColor("#b3b3b3"));
        // individual_lDistrict.setBackgroundColor(Color.parseColor("#b3b3b3"));
        individual_aboutMe.setBackgroundColor(Color.parseColor("#b3b3b3"));
        individual_username.setBackgroundColor(Color.parseColor("#b3b3b3"));
        individual_password.setBackgroundColor(Color.parseColor("#b3b3b3"));


        individual_memberName.setText(individual_memberName.getText().toString().equalsIgnoreCase("Not Available") ? "" : individual_memberName.getText().toString());
        individual_clubName.setText(individual_clubName.getText().toString().equalsIgnoreCase("Not Available") ? "" : individual_clubName.getText().toString());

        individual_mobileno.setText(individual_mobileno.getText().toString().equalsIgnoreCase("Not Available") ? "" : individual_mobileno.getText().toString());


        individual_intnumber.setText(individual_intnumber.getText().toString().equalsIgnoreCase("Not Available") ? "" : individual_intnumber.getText().toString());

        individual_joinDate.setText(individual_joinDate.getText().toString().equalsIgnoreCase("Not Available") ? "" : individual_joinDate.getText().toString());

        individual_profession.setText(individual_profession.getText().toString().equalsIgnoreCase("Not Available") ? "" : individual_profession.getText().toString());

        individual_dob.setText(individual_dob.getText().toString().equalsIgnoreCase("Not Available") ? "" : individual_dob.getText().toString());


        individual_marriageDay.setText(individual_marriageDay.getText().toString().equalsIgnoreCase("Not Available") ? "" : individual_marriageDay.getText().toString());

        individual_spouce.setText(individual_spouce.getText().toString().equalsIgnoreCase("Not Available") ? "" : individual_spouce.getText().toString());

        individual_residenceAdd.setText(individual_residenceAdd.getText().toString().equalsIgnoreCase("Not Available") ? "" : individual_residenceAdd.getText().toString());


        individual_businessAdd.setText(individual_businessAdd.getText().toString().equalsIgnoreCase("Not Available") ? "" : individual_businessAdd.getText().toString());

        individual_phone.setText(individual_phone.getText().toString().equalsIgnoreCase("Not Available") ? "" : individual_phone.getText().toString());

        individual_altPhone.setText(individual_altPhone.getText().toString().equalsIgnoreCase("Not Available") ? "" : individual_altPhone.getText().toString());

        individual_altMobile.setText(individual_altMobile.getText().toString().equalsIgnoreCase("Not Available") ? "" : individual_altMobile.getText().toString());

        individual_email.setText(individual_email.getText().toString().equalsIgnoreCase("Not Available") ? "" : individual_email.getText().toString());

        individual_altEmail.setText(individual_altEmail.getText().toString().equalsIgnoreCase("Not Available") ? "" : individual_altEmail.getText().toString());

        individual_city.setText(individual_city.getText().toString().equalsIgnoreCase("Not Available") ? "" : individual_city.getText().toString());
        individual_lDistrict.setText(individual_lDistrict.getText().toString().equalsIgnoreCase("Not Available") ? "" : individual_lDistrict.getText().toString());
        individual_aboutMe.setText(individual_aboutMe.getText().toString().equalsIgnoreCase("Not Available") ? "" : individual_aboutMe.getText().toString());


    }

    public void colorChangeFaterUpdate() {
        individual_memberName.setBackgroundColor(Color.TRANSPARENT);
        individual_clubName.setBackgroundColor(Color.TRANSPARENT);

        individual_mobileno.setBackgroundColor(Color.TRANSPARENT);
        individual_intnumber.setBackgroundColor(Color.TRANSPARENT);
        individual_joinDate.setBackgroundColor(Color.TRANSPARENT);
        individual_profession.setBackgroundColor(Color.TRANSPARENT);
        individual_dob.setBackgroundColor(Color.TRANSPARENT);

        individual_marriageDay.setBackgroundColor(Color.TRANSPARENT);
        individual_spouce.setBackgroundColor(Color.TRANSPARENT);
        individual_residenceAdd.setBackgroundColor(Color.TRANSPARENT);
        individual_businessAdd.setBackgroundColor(Color.TRANSPARENT);
        individual_phone.setBackgroundColor(Color.TRANSPARENT);
        individual_altPhone.setBackgroundColor(Color.TRANSPARENT);
        individual_altMobile.setBackgroundColor(Color.TRANSPARENT);
        individual_email.setBackgroundColor(Color.TRANSPARENT);
        individual_altEmail.setBackgroundColor(Color.TRANSPARENT);
        individual_city.setBackgroundColor(Color.TRANSPARENT);
        individual_lDistrict.setBackgroundColor(Color.TRANSPARENT);
        individual_aboutMe.setBackgroundColor(Color.TRANSPARENT);
        individual_username.setBackgroundColor(Color.TRANSPARENT);
        individual_password.setBackgroundColor(Color.TRANSPARENT);
        individual_memberName.setText(individual_memberName.getText().toString().equalsIgnoreCase("") ? "Not Available" : individual_memberName.getText().toString());
        individual_clubName.setText(individual_clubName.getText().toString().equalsIgnoreCase("") ? "Not Available" : individual_clubName.getText().toString());

        individual_mobileno.setText(individual_mobileno.getText().toString().equalsIgnoreCase("") ? "Not Available" : individual_mobileno.getText().toString());


        individual_intnumber.setText(individual_intnumber.getText().toString().equalsIgnoreCase("") ? "Not Available" : individual_intnumber.getText().toString());

        individual_joinDate.setText(individual_joinDate.getText().toString().equalsIgnoreCase("") ? "Not Available" : individual_joinDate.getText().toString());

        individual_profession.setText(individual_profession.getText().toString().equalsIgnoreCase("") ? "Not Available" : individual_profession.getText().toString());

        individual_dob.setText(individual_dob.getText().toString().equalsIgnoreCase("") ? "Not Available" : individual_dob.getText().toString());


        individual_marriageDay.setText(individual_marriageDay.getText().toString().equalsIgnoreCase("") ? "Not Available" : individual_marriageDay.getText().toString());

        individual_spouce.setText(individual_spouce.getText().toString().equalsIgnoreCase("") ? "Not Available" : individual_spouce.getText().toString());

        individual_residenceAdd.setText(individual_residenceAdd.getText().toString().equalsIgnoreCase("") ? "Not Available" : individual_residenceAdd.getText().toString());


        individual_businessAdd.setText(individual_businessAdd.getText().toString().equalsIgnoreCase("") ? "Not Available" : individual_businessAdd.getText().toString());

        individual_phone.setText(individual_phone.getText().toString().equalsIgnoreCase("") ? "Not Available" : individual_phone.getText().toString());

        individual_altPhone.setText(individual_altPhone.getText().toString().equalsIgnoreCase("") ? "Not Available" : individual_altPhone.getText().toString());

        individual_altMobile.setText(individual_altMobile.getText().toString().equalsIgnoreCase("") ? "Not Available" : individual_altMobile.getText().toString());

        individual_email.setText(individual_email.getText().toString().equalsIgnoreCase("") ? "Not Available" : individual_email.getText().toString());

        individual_altEmail.setText(individual_altEmail.getText().toString().equalsIgnoreCase("") ? "Not Available" : individual_altEmail.getText().toString());

        individual_city.setText(individual_city.getText().toString().equalsIgnoreCase("") ? "Not Available" : individual_city.getText().toString());
        individual_lDistrict.setText(individual_lDistrict.getText().toString().equalsIgnoreCase("") ? "Not Available" : individual_lDistrict.getText().toString());
        individual_aboutMe.setText(individual_aboutMe.getText().toString().equalsIgnoreCase("") ? "Not Available" : individual_aboutMe.getText().toString());


    }


    public String getMonth(int val) {
        String st = null;
        switch (val) {
            case 1:
                st = "January";
                break;
            case 2:
                st = "February";
                break;
            case 3:
                st = "March";
                break;
            case 4:
                st = "April";
                break;
            case 5:
                st = "May";
                break;
            case 6:
                st = "June";
                break;
            case 7:
                st = "July";
                break;
            case 8:
                st = "August";
                break;
            case 9:
                st = "September";
                break;
            case 10:
                st = "October";
                break;
            case 11:
                st = "November";
                break;
            case 12:
                st = "December";
                break;


        }
        return st;
    }

    public String getDay(int val) {
        String st = "" + val;
        if (st.length() == 1) {
            return "0" + st;
        } else {
            return st;
        }

    }

    public class Update extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            doShowLoading();
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                updatedProf();
                BDService service = new BDService();
                String st[] = member_details.getProfileImage().split(Pattern.quote("."));
                String s = service.VLDRF(username, password, member_details.getUserName(), memberProfile);

                Log.e("ffff", s);
                if (s.contains("Succ")) {
                    MemberRepository.insertOrUpdate(MemberActivity.this, memberDataToBeUpdated);
                }
                return s;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String val) {
            super.onPostExecute(val);
            doRemoveLoading();
            takePhotoFile = null;
            colorChangeFaterUpdate();
            if (val != null) {

                if (val.contains("Succ")) {
                 /*   if (new ConnectionDetector(MemberActivity.this).isConnectingToInternet()) {
                        File mProfileImage1 = new File(memberPhotoDirectory, member_details.getProfileImage());

                        if (mProfileImage1.exists()) {
                            mProfileImage1.delete();
                        }




                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(mProfileImage1);
                            prifileImage.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                            fileOutputStream.flush();
                            ;
                            fileOutputStream.close();



                        } catch (IOException ioe) {

                        } catch (Exception ex) {

                        }


                    }*/

                    new AlertDialog.Builder(MemberActivity.this)
                            .setTitle("Success")
                            .setMessage("Successfully UPdated")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .setCancelable(false)
                            .show();
                } else {
                    new AlertDialog.Builder(MemberActivity.this)
                            .setTitle("Error")
                            .setMessage(val)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .setCancelable(false)
                            .show();

                }

            }
        }
    }

    private class DownloadProfileImage extends AsyncTask<Void, Void, Bitmap> {
        String imagePath;
        Long mInt;
        int position;


        /*public DownloadProfileImage(String imagePath, MemberDataHolder memberDataHolder) {
            this.imagePath = imagePath;
         //   this.memberDataHolder = memberDataHolder;
        }*/


        public DownloadProfileImage(String imagePath, Long mInt) {
            this.imagePath = imagePath;
            this.mInt = mInt;
            this.position = position;
            //   this.memberDataHolder = memberDataHolder;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            doShowLoading();;
        }

        @Override
        protected Bitmap doInBackground(Void... memberDataHolders) {

            Bitmap b = getBitmapFromURL("http://alphatest.in/uploads/MemberPhotos/" + imagePath);
            File mProfileImage = new File(memberPhotoDirectory, imagePath);


            try {
                FileOutputStream fileOutputStream = new FileOutputStream(mProfileImage);
                b.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.flush();
                ;
                fileOutputStream.close();
                //memberDataHolder.memberProfile.setImageBitmap(bitmap);
                // isMemberProfileDownloadedSparseArray.put(mInt,true);
                // View v = LayoutInflater.from(mContext).inflate(R.layout.member_list, null, false);
                //  bindViewHolder(new MemberDataHolder(v),position);
                //doRemoveLoading();

            } catch (IOException ioe) {
               // doRemoveLoading();
            } catch (Exception ex) {
                //doRemoveLoading();
            }

            return b;/*getBitmapFromURL("http://alphatest.in/uploads/MemberPhotos/" + imagePath);*/
        }

        public Bitmap getBitmapFromURL(String src) {
            try {
                URL url = new URL(src);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                // Log exception
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);


            if (bitmap != null) {
                iv_user_image.setImageBitmap(bitmap);
                doRemoveLoading();
                /*File mProfileImage = new File(memberPhotoDirectory, imagePath);


                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(mProfileImage);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                    fileOutputStream.flush();
                    ;
                    fileOutputStream.close();
                    //memberDataHolder.memberProfile.setImageBitmap(bitmap);
                    // isMemberProfileDownloadedSparseArray.put(mInt,true);
                    // View v = LayoutInflater.from(mContext).inflate(R.layout.member_list, null, false);
                    //  bindViewHolder(new MemberDataHolder(v),position);
                    doRemoveLoading();

                } catch (IOException ioe) {
                    doRemoveLoading();
                } catch (Exception ex) {
                    doRemoveLoading();
                }
*/

                /*memberDataHolder.mProgressBar.setVisibility(View.GONE);*/
            }else{
                doRemoveLoading();
            }

        }
    }

    public File takePhotoFile = null;

    private void takePhoto() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePhotoFile = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(takePhotoFile));
        startActivityForResult(intent, REQUEST_CODE_TAKE_PICTURE);
    }

    public void pickFileFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(intent, REQUEST_CODE_GALLERY);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_CANCELED) {

            return;
        }


        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_TAKE_PICTURE) {

                iv_user_image.setImageBitmap(BitmapFactory
                        .decodeFile(takePhotoFile.getAbsolutePath()));
                prifileImage = BitmapFactory
                        .decodeFile(takePhotoFile.getAbsolutePath());


                //Bitmap original = BitmapFactory
                     //   .decodeFile(takePhotoFile.getAbsolutePath());
                //ByteArrayOutputStream out = new ByteArrayOutputStream();
               // original.compress(Bitmap.CompressFormat.PNG, 60, out);
               // Bitmap decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
               // iv_user_image.setImageBitmap(decoded);
                Bitmap resized = Bitmap.createScaledBitmap(prifileImage, 100, 100, true);
                base64Image = encodeTobase64(resized);
                uploadImage();
            } else if (requestCode == REQUEST_CODE_GALLERY) {
                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();


                // Toast.makeText(getApplicationContext(),picturePath, Toast.LENGTH_LONG)
                iv_user_image.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                base64Image = encodeTobase64(BitmapFactory.decodeFile(picturePath));
                takePhotoFile = new File(picturePath);

                uploadImage();

            }
        }

    }

    public void uploadImage() {

    }

    private void captureImage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MemberActivity.this);
        builder.setTitle("Take Picture");

        builder.setCancelable(true);

        builder.setPositiveButton("Camera",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        takePhoto();
                    }
                });
        builder.setNegativeButton("Gallery",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        pickFileFromGallery();
                    }
                });
        builder.create().show();
    }

    public String encodeTobase64(Bitmap image) {
        Bitmap immagex = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.e("LOOK", imageEncoded);
        return imageEncoded;
    }

/*    public void moveImage(Bitmap bitmap){
        if (bitmap != null) {
            iv_user_image.setImageBitmap(bitmap);
            File mProfileImage = new File(memberPhotoDirectory, member_details.getProfileImage());


            try {
                FileOutputStream fileOutputStream = new FileOutputStream(mProfileImage);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.flush();
                ;
                fileOutputStream.close();
                //memberDataHolder.memberProfile.setImageBitmap(bitmap);
                // isMemberProfileDownloadedSparseArray.put(mInt,true);
                // View v = LayoutInflater.from(mContext).inflate(R.layout.member_list, null, false);
                //  bindViewHolder(new MemberDataHolder(v),position);


            } catch (IOException ioe) {

            } catch (Exception ex) {

            }


                *//*memberDataHolder.mProgressBar.setVisibility(View.GONE);*//*
        }

    }*/

    public class GetCaptcha extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            BDService bdService = new BDService();
            return bdService.GCP("e006e035-2c01-48cf-92c2-f058116bb946");

        }

        @Override
        protected void onPostExecute(String gcpResponse) {
            super.onPostExecute(gcpResponse);

            if (gcpResponse != null) {
                System.out.println(gcpResponse);

                String st[] = gcpResponse.split("~");
                String s = member_details.getPassword();
                captaId = st[2];
                byte[] decodedString = Base64.decode(st[1], Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                ivCaptcha.setImageBitmap(decodedByte);


            }
        }
    }


    public boolean isvalid() {
        boolean flag = true;
        if (individual_memberName.getText().toString().trim().length() == 0) {
            new AlertDialog.Builder(MemberActivity.this)
                    .setTitle("Error")
                    .setMessage("Please enter Member Name")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .setCancelable(false)
                    .show();
            flag = false;
        } /*else if (individual_email.getText().toString().trim().length() == 0) {
            new AlertDialog.Builder(MemberActivity.this)
                    .setTitle("Error")
                    .setMessage("Please enter Email address")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .setCancelable(false)
                    .show();
            flag = false;
        } else if (individual_dob.getText().toString().trim().length() == 0) {
            new AlertDialog.Builder(MemberActivity.this)
                    .setTitle("Error")
                    .setMessage("Please enter Date of Birth ")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .setCancelable(false)
                    .show();
            flag = false;
        } */else if (individual_mobileno.getText().toString().trim().length() == 0) {
            new AlertDialog.Builder(MemberActivity.this)
                    .setTitle("Error")
                    .setMessage("Please enter Mobile No ")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .setCancelable(false)
                    .show();
            flag = false;
        } else if (individual_residenceAdd.getText().toString().trim().length() == 0) {
            new AlertDialog.Builder(MemberActivity.this)
                    .setTitle("Error")
                    .setMessage("Please enter Res Add ")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .setCancelable(false)
                    .show();
            flag = false;
        }else if (etCaptchaValue.getText().toString().trim().length() == 0) {

            new AlertDialog.Builder(MemberActivity.this)
                    .setTitle("Error")
                    .setMessage("Please Enter CAPTCHA in the text box ")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .setCancelable(false)
                    .show();
            flag = false;
        }
        return flag;
    }

    public ProgressDialog dialog;

    public void doShowLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog = new ProgressDialog(MemberActivity.this);
                dialog.setMessage("Please wait..........");
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.setIndeterminate(true);
                dialog.setCancelable(false);
                dialog.show();
            }
        });
    }

    public void doRemoveLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.cancel();
            }
        });
    }

    public void updatedProf() {
       /* llCaptcha.setVisibility(View.GONE);
        view4.setVisibility(View.GONE);*/
        memberDataToBeUpdated = new Member();
        memberDataToBeUpdated.setId(member_details.getId());

        memberDataToBeUpdated.setAboutMe(member_details.getAboutMe());
        memberDataToBeUpdated.setAltEmail(individual_altEmail.getText().toString());

        memberDataToBeUpdated.setAltPhone(individual_altPhone.getText().toString());
        memberDataToBeUpdated.setAltMobile(individual_altMobile.getText().toString());

        memberDataToBeUpdated.setBusinessAddress(individual_businessAdd.getText().toString());
        memberDataToBeUpdated.setCanEdit(member_details.getCanEdit());

        memberDataToBeUpdated.setCity(individual_city.getText().toString());

        memberDataToBeUpdated.setClubId(member_details.getClubId());


        memberDataToBeUpdated.setClubName(individual_clubName.getText().toString());

        memberDataToBeUpdated.setDistId(member_details.getDistId());

        memberDataToBeUpdated.setDistrict(member_details.getDistrict());

        memberDataToBeUpdated.setDob(dob_formated);

        memberDataToBeUpdated.setEmail(individual_email.getText().toString());

        memberDataToBeUpdated.setIsProfileDownloaded(member_details.getIsProfileDownloaded());

        memberDataToBeUpdated.setJoinDate(individual_joinDate.getText().toString());

        memberDataToBeUpdated.setMIntNum(Long.valueOf(individual_intnumber.getText().toString()));


        memberDataToBeUpdated.setMarriageDate(marrage_date_formated);


        memberDataToBeUpdated.setName(individual_memberName.getText().toString());
        memberDataToBeUpdated.setMobile(individual_mobileno.getText().toString());

        memberDataToBeUpdated.setOnline(member_details.getOnline());


        memberDataToBeUpdated.setPassword(member_details.getPassword());

        memberDataToBeUpdated.setPhone(individual_phone.getText().toString());

        memberDataToBeUpdated.setProfession(individual_profession.getText().toString());

        memberDataToBeUpdated.setProfileImage(member_details.getProfileImage());

        memberDataToBeUpdated.setProfilePicUrl(member_details.getProfilePicUrl());


        memberDataToBeUpdated.setSpouse(individual_spouce.getText().toString());
        memberDataToBeUpdated.setResidentialAddress(individual_residenceAdd.getText().toString());

        memberDataToBeUpdated.setDistrict(individual_lDistrict.getText().toString());
        memberDataToBeUpdated.setAboutMe(individual_aboutMe.getText().toString());


        memberDataToBeUpdated.setUserName(member_details.getUserName());




       /* editProfile.setVisibility(View.VISIBLE);
        updateProfile.setVisibility(View.GONE);*/
        isEditEnable = false;


        memberProfile.setProperty(0, "");
        memberProfile.setProperty(1, true);
        memberProfile.setProperty(2, individual_memberName.getText().toString());
        memberProfile.setProperty(3, individual_intnumber.getText().toString());
        memberProfile.setProperty(4, individual_profession.getText().toString());
        memberProfile.setProperty(5, individual_spouce.getText().toString());
        memberProfile.setProperty(6, individual_aboutMe.getText().toString());
        memberProfile.setProperty(7, individual_joinDate.getText().toString());
        memberProfile.setProperty(8, individual_city.getText().toString());
        memberProfile.setProperty(9, individual_phone.getText().toString());
        memberProfile.setProperty(10, individual_altPhone.getText().toString());


        memberProfile.setProperty(11, individual_mobileno.getText().toString());
        memberProfile.setProperty(12, individual_altMobile.getText().toString());
        memberProfile.setProperty(13, individual_email.getText().toString());
        memberProfile.setProperty(14, individual_altEmail.getText().toString());
        memberProfile.setProperty(15, individual_residenceAdd.getText().toString());


        memberProfile.setProperty(16, individual_businessAdd.getText().toString());

        memberProfile.setProperty(17, captaId);

        memberProfile.setProperty(18, etCaptchaValue.getText().toString().trim());

        memberProfile.setProperty(19, dob_formated);
        memberProfile.setProperty(20, marrage_date_formated);

       /* if(takePhotoFile!=null){
            Bitmap original = BitmapFactory
                    .decodeFile(takePhotoFile.getAbsolutePath());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            original.compress(Bitmap.CompressFormat.PNG, 60, out);
            Bitmap decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
            System.out.println("base64Image "+base64Image);
            // iv_user_image.setImageBitmap(decoded);
            base64Image = encodeTobase64(BitmapFactory.decodeFile(takePhotoFile.getAbsolutePath()));
        }*/



        //

        memberProfile.setProperty(21, base64Image);

        memberProfile.setProperty(22, member_details.getUserName());//user name
        memberProfile.setProperty(23, member_details.getPassword()); // password
        memberProfile.setProperty(24, member_details.getPassword());//conpass
        memberProfile.setProperty(25, individual_clubName.getText().toString());//
        memberProfile.setProperty(26, individual_lDistrict.getText().toString());
        memberProfile.setProperty(27, member_details.getClubId());
        memberProfile.setProperty(28, member_details.getDistId());//dist id
        //colorChangeFaterUpdate();
    }

    public class SendSMS extends AsyncTask<String, Void, Boolean> {
        protected void onPreExecute() {
            super.onPreExecute();
            doShowLoading();
        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            super.onPostExecute(aVoid);
            doRemoveLoading();
        }

        @Override
        protected Boolean doInBackground(String... voids) {
            BDService bdService = new BDService();
            String url = "http://www.onlinesmslogin.com/quicksms/api.php?username=" + voids[0] + "&password=" + voids[1] + "&to=" + voids[2] + "&from=RRREDY&message=" + voids[3].replaceAll(" ", "%20");
            System.out.println(url);
            HttpClientGet.SendHttpGet(url);
            return true;

        }


    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
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
//cId : ac2f826c-5819-4a49-a21f-103b9d108e0b