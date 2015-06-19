package app.admin;

import android.content.Context;

import java.util.List;

import lionsclub.com.directoryapp.CredentialRequestQueue;

/**
 * Created by ISSLT115-PC on 4/11/2015.
 */
public class CredentialRequestPresenterImplementation implements CredentialRequestPresenter, OnLoadCredentialRequestQueueListner {


    Context mContext;
    CredentialRequestView credentialRequestView;
    CredentialRequestQueueInteractor interactor;


    public CredentialRequestPresenterImplementation(CredentialRequestView view, Context context) {
        this.credentialRequestView = view;
        mContext = context;
        interactor = new CredentialRequestQueueInteractorImplementation(this, mContext);
    }

    @Override
    public void onLoadCredentialRequest(List<CredentialRequestQueue> credentialRequestQueueList) {
        credentialRequestView.onLoadMemberData(credentialRequestQueueList);
        /*credentialRequestView.hideProgress();*/

    }

    @Override
    public void onResume() {
       /* credentialRequestView.showProgress();*/
        interactor.loadCredentialRequest();
    }

    @Override
    public void onMemberDetailRequest() {

    }

    @Override
    public void onCredentialRequestProcessed() {

    }

}
