package app.Member;


/**
 * Created by ISSLT115-PC on 4/6/2015.
 */
public interface MemberView {

    public void showProgress();

    public void hideProgress();

    public void updatePassWord();

    public void updateProfile();

    public void showMessage(String message);
}
