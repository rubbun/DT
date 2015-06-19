package app.admin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.Dashboard.MemberDataHolder;
import app.Member.MemberActivity;
import app.Repositories.CRequestRepository;
import app.utils.ConnectionDetector;
import lionsclub.com.directoryapp.CredentialRequestQueue;
import lionsclub.com.directoryapp.Member;
import lionsclub.com.directoryapp.R;

/**
 * Created by ISSLT115-PC on 4/11/2015.
 */
public class CredentialRequestRecyclerAdapter extends RecyclerView.Adapter<CredentialRequestRecyclerAdapter.CredentialRequestHolder> {

    Context mContext;
    List<CredentialRequestQueue> credentialRequestQueues = new ArrayList<>();
    HashMap<Long,Boolean> isMemberProfileDownloadedSparseArray=new HashMap<>();
    File memberPhotoDirectory = new File(Environment.getExternalStorageDirectory(), "DirectoryApp/MemberPhoto/");


    public CredentialRequestRecyclerAdapter(Context context, List<CredentialRequestQueue> credentialRequestQueues) {
        this.mContext = context;
        this.credentialRequestQueues = credentialRequestQueues;

        if (!memberPhotoDirectory.isDirectory())
            memberPhotoDirectory.mkdirs();

    }


    @Override
    public CredentialRequestRecyclerAdapter.CredentialRequestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.credential_request_list, parent, false);

        CredentialRequestHolder holder = new CredentialRequestHolder(view);
        holder.setIsRecyclable(true);
        return holder;
    }

    @Override
    public void onBindViewHolder(CredentialRequestRecyclerAdapter.CredentialRequestHolder holder, int position) {

        CredentialRequestQueue cr = credentialRequestQueues.get(position);
       // holder.credentialRequestDate.setText(cr.getDateTimeRequested());
        final Member member = CRequestRepository.getCredentialRequestByMInt(mContext, cr.getMemInt());

        if(!isMemberProfileDownloadedSparseArray.containsKey(cr.getMemInt()))
        {
            isMemberProfileDownloadedSparseArray.put(cr.getMemInt(),false);
        }

        Log.e("AAAA",""+isMemberProfileDownloadedSparseArray.get(member.getMIntNum()));

        if (new ConnectionDetector(mContext).isConnectingToInternet() && !isMemberProfileDownloadedSparseArray.get(member.getMIntNum())) {

            new DownloadProfileImage(member.getProfileImage(),member.getMIntNum(),position).execute();
        }else {

            File mProfileImage = new File(memberPhotoDirectory, member.getProfileImage());

            if (mProfileImage.exists()) {
                BitmapFactory.Options bmpOptions = new BitmapFactory.Options();
                bmpOptions.inSampleSize = 2;
                Bitmap imageBitmap = BitmapFactory.decodeFile(mProfileImage.getAbsolutePath(), bmpOptions);
                if (imageBitmap != null) {
                    holder.memberImage.setImageBitmap(imageBitmap);
                }

            }
            holder.mProgressBar.setVisibility(View.GONE);
        }


        holder.memberName.setText(member.getName() + "");
       // holder.memberClubName.setText(member.getClubName() + "");
       // holder.memberMobileNumber.setText(member.getMobile() + "");

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, MemberActivity.class);
                intent.putExtra("memberData",member.getId());
                intent.putExtra("cre",true);
                mContext.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return credentialRequestQueues.size();
    }

    protected class CredentialRequestHolder extends RecyclerView.ViewHolder {

        CardView mCardView;
        ImageView memberImage;
        TextView memberName;
        /*TextView memberClubName;
        TextView memberMobileNumber;
        TextView credentialRequestDate;
        ImageView requestProcessedStatus;
      */  ProgressBar mProgressBar;

        public CredentialRequestHolder(View v) {
            super(v);
            mCardView=(CardView)v.findViewById(R.id.cr_cardview);
            memberImage = (ImageView) v.findViewById(R.id.cr_member_profile_image);
            memberName = (TextView) v.findViewById(R.id.mname);
          /*  memberClubName = (TextView) v.findViewById(R.id.mClubName);
            memberMobileNumber = (TextView) v.findViewById(R.id.mMobileNumber);
            requestProcessedStatus = (ImageView) v.findViewById(R.id.cr_processed);
            credentialRequestDate = (TextView) v.findViewById(R.id.mRequestDate);
         */   mProgressBar=(ProgressBar)v.findViewById(R.id.picProgress);

        }

    }

    private class DownloadProfileImage extends AsyncTask<Void, Void, Bitmap> {
        String imagePath;
        Long mInt;
        int position;


        public DownloadProfileImage(String imagePath,Long mInt,int position) {
            this.imagePath = imagePath;
            this.mInt=mInt;
            this.position=position;
        }

        @Override
        protected Bitmap doInBackground(Void... memberDataHolders) {

            return getBitmapFromURL("http://alphatest.in/uploads/MemberPhotos/" + imagePath);
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
                File mProfileImage = new File(memberPhotoDirectory, imagePath);


                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(mProfileImage);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                    fileOutputStream.flush();
                    ;
                    fileOutputStream.close();
                    isMemberProfileDownloadedSparseArray.put(mInt,true);
                    View v = LayoutInflater.from(mContext).inflate(R.layout.credential_request_list, null, false);
                    bindViewHolder(new CredentialRequestHolder(v),position);


                } catch (IOException ioe) {

                } catch (Exception ex) {

                }


                /*memberDataHolder.mProgressBar.setVisibility(View.GONE);*/
            }

        }
    }
}
