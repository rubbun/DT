package app.Repositories;

import android.content.Context;

import java.util.List;

import app.DirectoryApp;
import lionsclub.com.directoryapp.District;
import lionsclub.com.directoryapp.DistrictDao;

/**
 * Created by ISSLT115-PC on 4/8/2015.
 */
public class DistrictRepository {


    public static void insertOrUpdate(Context context, District district) {
        getDistrictDao(context).insertOrReplace(district);
    }

    public static void clearDistrict(Context context) {
        getDistrictDao(context).deleteAll();
    }

    public static void deleteDistrictWithId(Context context, long id) {
        getDistrictDao(context).delete(getDistrictForId(context, id));
    }

    public static List<District> getAllDistrict(Context context) {
        return getDistrictDao(context).loadAll();
    }

    public static District getDistrictForId(Context context, long id) {
        return getDistrictDao(context).load(id);
    }


    private static DistrictDao getDistrictDao(Context context) {
        return ((DirectoryApp) context.getApplicationContext()).getDaoSession().getDistrictDao();
    }

}
