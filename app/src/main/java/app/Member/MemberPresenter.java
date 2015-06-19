package app.Member;

/**
 * Created by ISSLT115-PC on 4/6/2015.
 */
public interface MemberPresenter {

    public void loadMemberDetails(String mInt);

    public void onUpdateProfileSucceess();

    public void onUpdateProfileFailure();

    public void onUpdatePassWordSuccess();

    public void onUpdatePassWordFailure();

}
