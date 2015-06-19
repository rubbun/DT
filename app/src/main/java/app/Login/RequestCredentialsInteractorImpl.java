package app.Login;

import android.os.AsyncTask;

import app.WebService.BDService;

/**
 * Created by ISSLT115-PC on 4/6/2015.
 */
public class RequestCredentialsInteractorImpl implements RequestCredentialInteractor {

    OnFinishRequestCredentials listener;
    String mInt;
    @Override
    public void requestCredentials(String mInt, OnFinishRequestCredentials onFinishRequestCredentials) {
        this.mInt = mInt;
        this.listener = onFinishRequestCredentials;

        new RequestCredentialTask().execute();
    }


    private class RequestCredentialTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            return new BDService().GUCREToADM(mInt);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                listener.onRequestCredentialsSuccess(s);
            } else {
                listener.onRequestCredentialsFailure("Your request could not be processed! Please try after some time");
            }
        }
    }
}
