package app.admin;

import java.util.List;

import lionsclub.com.directoryapp.CredentialRequestQueue;
import lionsclub.com.directoryapp.Member;

/**
 * Created by ISSLT115-PC on 4/11/2015.
 */
public interface CredentialRequestView {

    public void searchMember();

    public void onLoadMemberData(List<CredentialRequestQueue> credentialRequestQueues);

    public void showProgress();

    public void hideProgress();


    public void showMessage();

    public void navigateToMemberDetail();

}
