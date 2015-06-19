/*
 *
 *  * Copyright (C) 2014 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package app.Login;

import android.app.Activity;
import android.content.Context;

public class LoginPresenterImpl implements LoginPresenter, OnLoginFinishedListener,OnFinishRequestCredentials {

    LoginActivity mContext;
    private LoginView loginView;
    private LoginInteractor loginInteractor;
    private RequestCredentialInteractor requestCredentialInteractor;

    public LoginPresenterImpl(LoginView loginView, LoginActivity context) {
        this.mContext = context;
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
        this.requestCredentialInteractor=new RequestCredentialsInteractorImpl();
    }


    @Override
    public void onRequestCredentialsSuccess(String message) {
        loginView.hideProgress();
        loginView.showActivityMessage(message);

    }

    @Override
    public void onRequestCredentialsFailure(String message) {
        loginView.hideProgress();
        loginView.showActivityMessage(message);
    }

    @Override
    public void validateCredentials(String username, String password) {
        loginView.showProgress();
        loginInteractor.login(username, password, this, mContext);
    }

    @Override
    public void requestCredentials(String mInt)
    {
        loginView.showProgress();
        requestCredentialInteractor.requestCredentials(mInt,this);
    }

    @Override
    public void onUsernameError() {
        loginView.setUsernameError();
        loginView.hideProgress();
    }

    @Override
    public void onPasswordError() {
        loginView.setPasswordError();
        loginView.hideProgress();
    }

    @Override
    public void onSuccess() {
        loginView.navigateToHome();
    }

    @Override
    public void onFailure(String errorMessage) {
        loginView.hideProgress();
        loginView.showActivityMessage(errorMessage);

    }

    @Override
    public void OnProgressUpdate(int i) {
        loginView.onProgressUpdate(i);
    }
}
