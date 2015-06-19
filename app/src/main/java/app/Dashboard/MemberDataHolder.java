package app.Dashboard;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import lionsclub.com.directoryapp.R;

/**
 * Created by ISSLT115-PC on 4/9/2015.
 */
public class MemberDataHolder extends RecyclerView.ViewHolder {
    public CardView mCardView;
    TextView memberName, memberClub, memberMobile;
    ImageView memberProfile;
    ProgressBar mProgressBar;

    public MemberDataHolder(View view) {
        super(view);


    }
}
