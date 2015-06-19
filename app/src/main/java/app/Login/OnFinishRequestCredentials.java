package app.Login;

/**
 * Created by ISSLT115-PC on 4/6/2015.
 */
public interface OnFinishRequestCredentials {

    public void onRequestCredentialsSuccess(String message);

    public void onRequestCredentialsFailure(String message);
}
