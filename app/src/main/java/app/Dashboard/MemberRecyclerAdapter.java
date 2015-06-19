package app.Dashboard;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

import app.Member.MemberActivity;
import app.util.ImageLoader;
import app.utils.ConnectionDetector;
import lionsclub.com.directoryapp.Club;
import lionsclub.com.directoryapp.Member;
import lionsclub.com.directoryapp.R;

/**
 * Created by ISSLT115-PC on 4/9/2015.
 */



public class MemberRecyclerAdapter extends ArrayAdapter<Member> {

    private Dashboard activity;
    private ViewHolder mHolder;
    public List<Member> item = new ArrayList<Member>();
    File memberPhotoDirectory = new File(Environment.getExternalStorageDirectory(), "DirectoryOnline/MemberPhoto/");

    public MemberRecyclerAdapter(Dashboard activity, int textViewResourceId, List<Member> items) {
        super(activity, textViewResourceId, items);
        this.item = items;
        this.activity = activity;
        if (!memberPhotoDirectory.isDirectory())
            memberPhotoDirectory.mkdirs();


    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.member_list, null);
            mHolder = new ViewHolder();


            mHolder.memberName = (TextView) v.findViewById(R.id.memberName);
            mHolder.memberProfileImg = (ImageView)v.findViewById(R.id.memberProfileImg);
            mHolder.ll_main = (LinearLayout)v.findViewById(R.id.ll_main);
            v.setTag(mHolder);

        } else {
            mHolder = (ViewHolder) v.getTag();
        }

        final Member member = item.get(position);

        if (member != null) {
            mHolder.memberName.setText(member.getName());
            File mProfileImage = new File(memberPhotoDirectory, member.getProfileImage());

            if (mProfileImage.exists()) {
                BitmapFactory.Options bmpOptions = new BitmapFactory.Options();
                bmpOptions.inSampleSize = 2;
                Bitmap imageBitmap = BitmapFactory.decodeFile(mProfileImage.getAbsolutePath(), bmpOptions);
                if (imageBitmap != null) {
                    mHolder.memberProfileImg.setImageBitmap(imageBitmap);
                }

                // }
                //  memberDataHolder.mProgressBar.setVisibility(View.GONE);
            }else{
                mHolder.memberProfileImg.setImageResource(R.drawable.index);
            }
        }

        mHolder.ll_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                activity.onRowClick(item.get(position).getId());
               /* Intent intent=new Intent(activity, MemberActivity.class);
                intent.putExtra("memberData",item.get(position).getId());
                intent.putExtra("cre",false);
                Log.e("else", "" + item.get(position).getId());
                activity.startActivity(intent);*/
            }
        });


        return v;
    }

    class ViewHolder {
        public TextView memberName;
        public ImageView memberProfileImg;
        public LinearLayout ll_main;


    }

/*
public class MemberRecyclerAdapter extends RecyclerView.Adapter<MemberDataHolder> {

    Context mContext;
    File memberPhotoDirectory = new File(Environment.getExternalStorageDirectory(), "DirectoryOnline/MemberPhoto/");
    private List<Member> membersList = new ArrayList<Member>();
  //  HashMap<Long,Boolean> isMemberProfileDownloadedSparseArray=new HashMap<>();


    public MemberRecyclerAdapter(Context context, List<Member> members) {
        this.membersList = members;
        this.mContext = context;

        if (!memberPhotoDirectory.isDirectory())
            memberPhotoDirectory.mkdirs();

    }



    @Override
    public MemberDataHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.member_list, viewGroup, false);
        MemberDataHolder mh = new MemberDataHolder(v);
        mh.setIsRecyclable(false);
        return mh;
    }

    @Override
    public void onBindViewHolder(MemberDataHolder memberDataHolder, final int i) {

        memberDataHolder.memberName.setText(membersList.get(i).getName());
      //  memberDataHolder.memberClub.setText(membersList.get(i).getClubId());
        //memberDataHolder.memberMobile.setText(membersList.get(i).getMobile());
       */
/* if(!isMemberProfileDownloadedSparseArray.containsKey(membersList.get(i).getMIntNum()))
        {
         isMemberProfileDownloadedSparseArray.put(membersList.get(i).getMIntNum(),false);
        }*//*


        //if (new ConnectionDetector(mContext).isConnectingToInternet() && !isMemberProfileDownloadedSparseArray.get(membersList.get(i).getMIntNum())) {

            //new DownloadProfileImage(membersList.get(i).getProfileImage(),membersList.get(i).getMIntNum(),i).execute();
        //}else {

            File mProfileImage = new File(memberPhotoDirectory, membersList.get(i).getProfileImage());

            if (mProfileImage.exists()) {
                BitmapFactory.Options bmpOptions = new BitmapFactory.Options();
                bmpOptions.inSampleSize = 2;
                Bitmap imageBitmap = BitmapFactory.decodeFile(mProfileImage.getAbsolutePath(), bmpOptions);
                if (imageBitmap != null) {
                    memberDataHolder.memberProfile.setImageBitmap(imageBitmap);
                }

           // }
          //  memberDataHolder.mProgressBar.setVisibility(View.GONE);
        }

        memberDataHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, MemberActivity.class);
                intent.putExtra("memberData",membersList.get(i).getId());
                intent.putExtra("cre",false);
                Log.e("else", "" + membersList.get(i).getId());
                mContext.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return membersList.size();
    }

*/


}
