package app.Member;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import app.Login.LoginActivity;
import app.WebService.BDService;
import lionsclub.com.directoryapp.R;

/**
 * Created by Yaju on 6/17/2015.
 */
public class UpdateUsernameActivity extends Activity implements View.OnClickListener{

    private EditText etCurrent,etNew,etPassword,etCaptcha;
    private Button btnSubmit;
    private ImageView ivCaptcha;
    private String captaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_username);

        etCurrent = (EditText)findViewById(R.id.etCurrent);
        etNew = (EditText)findViewById(R.id.etNew);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etCaptcha = (EditText)findViewById(R.id.etCaptcha);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        ivCaptcha = (ImageView)findViewById(R.id.ivCaptcha);
        btnSubmit.setOnClickListener(this);

        new GetCaptcha().execute();

        etNew.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                new Available().execute();
            }
        });

        ((Button)findViewById(R.id.BtnRefresh)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetCaptcha().execute();
            }
        });

        btnSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSubmit:

                if(isValid()){
new UpdateUsernameAsynctask().execute();
                }
                break;
        }
    }


    public boolean isValid(){
        boolean flag = true;
        if(etCurrent.getText().toString().trim().length()==0){
            etCurrent.setError("Please enter current username");
            flag = false;
        }

        if(etNew.getText().toString().trim().length()==0){
            etNew.setError("Please enter new username");
            flag = false;
        }

        if(etPassword.getText().toString().trim().length()==0){
            etPassword.setError("Please enter password");
            flag = false;
        }

        if(etCaptcha.getText().toString().trim().length()==0){
            etCaptcha.setError("Please enter captcha");
            flag = false;
        }
        return flag;
    }

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

                captaId = st[2];
                byte[] decodedString = Base64.decode(st[1], Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                ivCaptcha.setImageBitmap(decodedByte);


            }
        }
    }


    public ProgressDialog dialog;

    public void doShowLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog = new ProgressDialog(UpdateUsernameActivity.this);
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



    public class UpdateUsernameAsynctask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            doShowLoading();
        }

        @Override
        protected String doInBackground(Void... voids) {
            BDService bdService = new BDService();
            String currentusername = etCurrent.getText().toString().trim();
            String newuser = etNew.getText().toString().trim();
            String newpass = etPassword.getText().toString().trim();
            String captcha = etCaptcha.getText().toString().trim();
            return  bdService.UUN(currentusername, newpass, newuser, captcha, captaId);


        }

        @Override
        protected void onPostExecute(String gcpResponse) {
            super.onPostExecute(gcpResponse);
            doRemoveLoading();



            if (gcpResponse != null) {
                String s[] = gcpResponse.split("~");
                if(s[0].equalsIgnoreCase("true")){
                    new AlertDialog.Builder(UpdateUsernameActivity.this)
                            .setCancelable(false)
                            .setTitle("Success")
                            .setMessage("Username successfully updated")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    getSharedPreferences(getString(R.string.app_name), 1).edit().clear().commit();
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    UpdateUsernameActivity.this.finish();
                                }
                            })
                            .show();

                }else{
                    new AlertDialog.Builder(UpdateUsernameActivity.this)
                            .setCancelable(false)
                            .setTitle("Error")
                            .setMessage(s[1])
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();

                                }
                            })
                            .show();
                }


            }
        }
    }


    public class Available extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           // doShowLoading();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            BDService bdService = new BDService();

            String newuser = etNew.getText().toString().trim();

            return  bdService.IDE(newuser);


        }

        @Override
        protected void onPostExecute(Boolean gcpResponse) {
            super.onPostExecute(gcpResponse);
            //doRemoveLoading();

            if (gcpResponse != null) {
                if(gcpResponse){
                    ((TextView)findViewById(R.id.tvisavail)).setText("Already exist");
                    ((TextView)findViewById(R.id.tvisavail)).setTextColor(Color.RED);

                }else{
                    ((TextView)findViewById(R.id.tvisavail)).setText("Available");
                    ((TextView)findViewById(R.id.tvisavail)).setTextColor(Color.GREEN);
                }


            }
        }
    }
}
