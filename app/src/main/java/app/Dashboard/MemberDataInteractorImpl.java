package app.Dashboard;

import android.content.Context;
import android.os.Handler;

import java.util.List;

import app.Repositories.ClubRepository;
import app.Repositories.MemberRepository;
import lionsclub.com.directoryapp.Member;


/**
 * Created by ISSLT115-PC on 4/6/2015.
 */
public class MemberDataInteractorImpl implements MemberDataInteractor {

    Context mContext;
    @Override
    public void getMemberData(final OnFinishedListener listener, Context context) {

        mContext = context;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onFinished(createMemberList());
            }
        }, 2000);
    }

    @Override
    public void getClubList(OnFinishedListener onFinishedListener, Context context) {
        onFinishedListener.onLoadClubs(ClubRepository.getAllClubs(context));
    }

    private List<Member> createMemberList() {

        return MemberRepository.getAllMembers(mContext);
    }


}
