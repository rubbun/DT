package app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import lionsclub.com.directoryapp.DaoMaster;
import lionsclub.com.directoryapp.DaoSession;


/**
 * Created by ISSLT115-PC on 4/5/2015.
 */
public class DirectoryApp extends Application {

    public DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDatabase();
    }


    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "directory.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
