package app.Repositories;

import android.content.Context;

import java.util.List;

import app.DirectoryApp;
import lionsclub.com.directoryapp.Member;
import lionsclub.com.directoryapp.CredentialRequestQueue;
import lionsclub.com.directoryapp.CredentialRequestQueueDao;
import lionsclub.com.directoryapp.MemberDao;


/**
 * Created by ISSLT115-PC on 4/8/2015.
 */
public class CRequestRepository {

    public static void insertOrUpdate(Context context, CredentialRequestQueue credentialRequestQueue) {
        getCredentialRequestQueueDao(context).insertOrReplace(credentialRequestQueue);
    }

    public static void clearQueue(Context context) {
        getCredentialRequestQueueDao(context).deleteAll();
    }


    public static List<CredentialRequestQueue> getAllRequest(Context context) {
        return getCredentialRequestQueueDao(context).loadAll();
    }


    public static Member getCredentialRequestByMInt(Context context, long mInt) {
        //return MemberRepository.getMemberForId(context, mInt);
        return MemberRepository.getMemberForMInt(context,mInt);
    }


    private static CredentialRequestQueueDao getCredentialRequestQueueDao(Context context) {
        return ((DirectoryApp) context.getApplicationContext()).getDaoSession().getCredentialRequestQueueDao();
    }
}
