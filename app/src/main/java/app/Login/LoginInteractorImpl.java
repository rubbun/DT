package app.Login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import app.Repositories.CRequestRepository;
import app.Repositories.ClubRepository;
import app.Repositories.DistrictRepository;
import app.Repositories.MemberRepository;
import app.WebService.BDService;
import app.WebService.BannerInfo;
import app.WebService.CReqMem;
import app.WebService.CredentialsRequestsListResponse;
import app.WebService.Member;
import app.WebService.MergedResponse;
import app.WebService.VectorBannerInfo;
import app.WebService.VectorString;
import app.WebService.xClub;
import lionsclub.com.directoryapp.CredentialRequestQueue;
import lionsclub.com.directoryapp.District;
import lionsclub.com.directoryapp.R;


public class LoginInteractorImpl implements LoginInteractor {

    OnLoginFinishedListener listener;
    LoginActivity mContext;
    ProgressDialog pd;

    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener, LoginActivity context) {
        this.mContext = context;
        this.listener = listener;
        boolean error = false;
        if (TextUtils.isEmpty(username)) {
            listener.onUsernameError();
            error = true;
        }
        if (TextUtils.isEmpty(password)) {
            listener.onPasswordError();
            error = true;
        }
        if (!error) {
            new LoginTask(username, password).execute();
        }
    }


    private class LoginTask extends AsyncTask<Void, Integer, Boolean> {

        String userName;
        String passWord;
        MergedResponse response;
        int j = 0;

        public LoginTask(String userName, String passWord) {
            this.userName = userName;
            this.passWord = passWord;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(mContext);
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setMessage("Downloading Members...");
            pd.setCancelable(false);


        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pd.setProgress((int) (values[0]));
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                BDService service = new BDService();
                response = service.MRGDRSPNS(userName, passWord);


                if (response != null && response.authenticated) {
                    SharedPreferences appPreference1 = mContext.getSharedPreferences(mContext.getString(R.string.app_name), 1);
                    SharedPreferences.Editor appPrefEditor1 = appPreference1.edit();
                    appPrefEditor1.putString("MemId", response.MemId);
                    appPrefEditor1.putString("u1", userName);
                    appPrefEditor1.putString("u2", passWord);

                    // System.out.println("MemId   "+ response.MemId);

                    appPrefEditor1.commit();
                    mContext.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mContext.is2G) {
                                if (mContext.OptionDialog != null) {
                                    mContext.OptionDialog.dismiss();
                                }
                            }

                        }
                    });

                    if (response.isAdmin) {

                        SharedPreferences appPreference = mContext.getSharedPreferences(mContext.getString(R.string.app_name), 1);
                        SharedPreferences.Editor appPrefEditor = appPreference.edit();
                        appPrefEditor.putBoolean("isAdmin", response.isAdmin);
                        appPrefEditor.commit();

                        CredentialsRequestsListResponse credentialsRequestsListResponse = service.GCREREQSLST(userName, passWord);

                        if (credentialsRequestsListResponse.success) {
                            System.out.println("credentialsRequestsListResponse.credentialRequestsList "+credentialsRequestsListResponse.credentialRequestsList.size());
                            for (CReqMem cReqMem : credentialsRequestsListResponse.credentialRequestsList) {
                                CredentialRequestQueue credentialRequest = new CredentialRequestQueue();

                                System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");

                                credentialRequest.setMemInt(Integer.parseInt(cReqMem.mIntNo));
                                credentialRequest.setDateTimeRequested(cReqMem.dT_Requested);
                                credentialRequest.setIsProcessed(cReqMem.processed);
                                CRequestRepository.insertOrUpdate(mContext, credentialRequest);

                            }

                        }

                    }
                    District district = new District();
                    district.setDistId("ld01");
                    district.setDistName("Adilabad");
                    DistrictRepository.insertOrUpdate(mContext, district);
                    int i = 0;

                    if (response.allClubs != null)
                        for (xClub club : response.allClubs) {
                            for (Member member : club.membersIn) {
                                j++;
                            }
                        }

               /* mContext.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pd.setMax(j);

                    }
                });*/

                    mContext.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mContext.progressBar.setVisibility(View.GONE);
                            pd.setMax(j);
                            pd.show();
                        }
                    });


                    for (xClub club : response.allClubs) {
                        lionsclub.com.directoryapp.Club clubEntity = new lionsclub.com.directoryapp.Club();
                        clubEntity.setDistId(district.getDistId());
                        clubEntity.setName(club.name);
                        clubEntity.setClubId(club.iD);
                        clubEntity.setMemcount(club.mems);
                        ClubRepository.insertOrUpdate(mContext, clubEntity);

                        for (Member member : club.membersIn) {

                            i++;
                            publishProgress(i);
                            lionsclub.com.directoryapp.Member entityMember = new lionsclub.com.directoryapp.Member();

                            entityMember.setClubId(member.clubId);
                            entityMember.setDistId(member.distID);
                            entityMember.setName(member.mName);
                            entityMember.setProfileImage(member.profile.photo);
                            entityMember.setMobile(member.mobile);
                            entityMember.setCanEdit(member.canEdit);
                            entityMember.setEmail(member.email);
                            entityMember.setOnline(member.oL);
                            entityMember.setAboutMe(member.profile.aboutMe);
                            entityMember.setAltEmail(member.profile.alternateEmail);
                            entityMember.setAltMobile(member.profile.alternateMobile);
                            entityMember.setAltPhone(member.profile.alternatePhone);
                            entityMember.setCity(member.profile.city);
                            entityMember.setClubId(member.clubId);
                            entityMember.setClubName(member.profile.clubName);
                            entityMember.setBusinessAddress(member.profile.businessAddress);
                            entityMember.setDistId(member.distID);
                            entityMember.setDistrict(member.profile.district);
                            entityMember.setDob(member.profile.dateOfBirth);
                            entityMember.setJoinDate(member.profile.joinDate);
                            entityMember.setMarriageDate(member.profile.marriageDate);
                            entityMember.setMIntNum(Long.parseLong(member.profile.memberNumber));
                            entityMember.setUserName(member.memId);
                           /* entityMember.setPassword(member.profile.password);*/
                            entityMember.setPassword(member.profile.userName + "~" + member.profile.password);
                            entityMember.setPhone(member.profile.phone);
                            entityMember.setProfession(member.profile.profession);
                            entityMember.setResidentialAddress(member.profile.residentialAddress);

                            entityMember.setSpouse(member.profile.spouse);
                            MemberRepository.insertOrUpdate(mContext, entityMember);

                        }

                    }

                    try {

                        dowloadBannerImage(service.BNR(userName, passWord));

                        // Log.v(" BNR result",r);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    mContext.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // listener.onFailure("Login Failed!!Please Check userID password combination");
                        }
                    });
                }
                return response.authenticated;
            }catch(Exception e){
                return false;
            }

        }


        @Override
        protected void onPostExecute(Boolean isAuthenticated) {
            super.onPostExecute(isAuthenticated);
            pd.dismiss();
            if (isAuthenticated != null && isAuthenticated) {
                listener.onSuccess();
            } else {
                listener.onFailure("Login Failed!!Please Check userID password combination");
            }


        }
    }

    private void dowloadBannerImage(VectorBannerInfo bnr) {
        if (bnr != null) {
            File dirFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DirectoryOnline/BannerPhoto");
            if (!dirFile.exists()) {
                dirFile.mkdirs();

            }

            JSONArray jArr = new JSONArray();
            for (BannerInfo binf : bnr)
            // for(int i=0;i<bnr.getPropertyCount();i++)
            {


                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("title", binf.title);
                    jsonObject.put("image", binf.image);
                    jsonObject.put("uRL", binf.uRL);
                    jsonObject.put("desc", binf.desc);

                    jArr.put(jsonObject);
                    System.out.println("banner   " + binf.uRL + "/" + binf.image);
                    URL url = new URL("http://alphatest.in/uploads/DTApp_slideimages/" + binf.image);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();

                    OutputStream output = new FileOutputStream(new File(dirFile, binf.image));
                    try {
                        byte[] buffer = new byte[2048];
                        int bytesRead = 0;
                        while ((bytesRead = input.read(buffer, 0, buffer.length)) >= 0) {
                            output.write(buffer, 0, bytesRead);
                        }
                    } finally {
                        output.close();
                        input.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ;
                }

            }

            SharedPreferences appPreference2 = mContext.getSharedPreferences(mContext.getString(R.string.app_name), 1);
            SharedPreferences.Editor appPrefEditor2 = appPreference2.edit();
            appPrefEditor2.putString("bnr", jArr.toString());


            appPrefEditor2.commit();

            System.out.println("bnrbnrbnrbnr  " + jArr.toString());
        }
    }


}