package app.admin;

import android.content.Context;

import java.util.List;

import app.Repositories.CRequestRepository;
import lionsclub.com.directoryapp.CredentialRequestQueue;

/**
 * Created by ISSLT115-PC on 4/11/2015.
 */
public class CredentialRequestQueueInteractorImplementation implements CredentialRequestQueueInteractor {


    public OnLoadCredentialRequestQueueListner listner;
    Context mContext;

    public CredentialRequestQueueInteractorImplementation(final OnLoadCredentialRequestQueueListner listner, Context context) {
        this.listner = listner;
        this.mContext = context;
        listner.onLoadCredentialRequest(loadCredentialRequest());
    }

    @Override
    public List<CredentialRequestQueue> loadCredentialRequest() {

        System.out.println("SIZEEEEE "+ CRequestRepository.getAllRequest(mContext).size() );
        return CRequestRepository.getAllRequest(mContext);
    }
}
