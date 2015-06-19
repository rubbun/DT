package app.Repositories;

import android.content.Context;

import java.util.List;

import app.DirectoryApp;
import de.greenrobot.dao.query.Query;
import lionsclub.com.directoryapp.Member;
import lionsclub.com.directoryapp.MemberDao;



/**
 * Created by ISSLT115-PC on 4/8/2015.
 */
public class MemberRepository {

    public static void insertOrUpdate(Context context, Member member) {
        getMemberDao(context).insertOrReplace(member);
    }

    public static void clearMember(Context context) {
        getMemberDao(context).deleteAll();
    }

    public static void deleteMemberWithId(Context context, long id) {
        getMemberDao(context).delete(getMemberForId(context, id));
    }


    public static List<Member> getMembersForClubs(Context context, String clubId) {
        return getMemberDao(context)._queryClub_MemberList(clubId);
    }


    public static List<Member> getAllMembers(Context context) {
        return getMemberDao(context).loadAll();
    }

    public static Member getMemberForId(Context context, long id) {
        return getMemberDao(context).load(id);
    }

    /*public static app.WebService.Member getMemberForMInt(Context context, int mInt)

    {
        Query query = getMemberDao(context).queryBuilder().where(MemberDao.Properties.MIntNum.eq(mInt)).build();
        List<app.WebService.Member> memberList = query.list();
        if (memberList.size() > 0)
            return memberList.get(0);
        else
            return null;
    }*/

    public static Member getMemberForMInt(Context context, long mInt)

    {
        Query query = getMemberDao(context).queryBuilder().where(MemberDao.Properties.MIntNum.eq(mInt)).build();
        List<Member> memberList = query.list();
        if (memberList.size() > 0)
            return memberList.get(0);
        else
            return null;
    }
    private static MemberDao getMemberDao(Context context) {
        return ((DirectoryApp) context.getApplicationContext()).getDaoSession().getMemberDao();
    }
}
