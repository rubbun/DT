package app.Dashboard;

import android.content.Context;

/**
 * Created by ISSLT115-PC on 4/6/2015.
 */
public interface MemberDataInteractor {

    public void getMemberData(OnFinishedListener onFinishedListener, Context context);

    public void getClubList(OnFinishedListener onFinishedListener, Context context);

}
