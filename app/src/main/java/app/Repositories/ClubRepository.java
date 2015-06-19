package app.Repositories;

import android.content.Context;

import java.util.List;

import app.DirectoryApp;
import lionsclub.com.directoryapp.Club;
import lionsclub.com.directoryapp.ClubDao;


/**
 * Created by ISSLT115-PC on 4/8/2015.
 */
public class ClubRepository {

    public static void insertOrUpdate(Context context, Club district) {
        getClubDao(context).insertOrReplace(district);
    }

    public static void clearClub(Context context) {
        getClubDao(context).deleteAll();
    }

    public static void deleteClubWithId(Context context, long id) {
        getClubDao(context).delete(getClubForId(context, id));
    }


    public static List<Club> getClubsByDist(Context context, String distid) {
        return getClubDao(context)._queryDistrict_Clubs(distid);
    }

    public static List<Club> getAllClubs(Context context) {
        return getClubDao(context).loadAll();
    }

    public static Club getClubForId(Context context, long id) {
        return getClubDao(context).load(id);
    }


    private static ClubDao getClubDao(Context context) {
        return ((DirectoryApp) context.getApplicationContext()).getDaoSession().getClubDao();
    }

}
