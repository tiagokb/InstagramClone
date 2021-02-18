package com.tiagokontarski.instagramclone.login.presentation;

import com.tiagokontarski.instagramclone.R;
import com.tiagokontarski.instagramclone.commons.model.UserAuth;
import com.tiagokontarski.instagramclone.commons.presenter.Presenter;
import com.tiagokontarski.instagramclone.commons.utils.Strings;
import com.tiagokontarski.instagramclone.login.datasource.LoginDataSouce;

class LoginPresenter implements Presenter<UserAuth> {
    private final LoginViews view;
    private final LoginDataSouce dataSouce;

    LoginPresenter(LoginViews view, LoginDataSouce dataSouce) {
        this.view = view;
        this.dataSouce = dataSouce;
    }

    void login(String email, String password) {

        if (!Strings.isEmailValid(email)) {
            view.onFailureForm(view.getContext().getString(R.string.invalid_email), null);
            return;
        }

        view.showProgressBar();
        dataSouce.login(email, password, this);
    }

    @Override
    public void onSuccess(UserAuth userAuth) {
        view.onUserLogged();
    }

    @Override
    public void onError(String message) {
        view.onFailureForm(null, message);
    }

    @Override
    public void onComplete() {
        view.hideProgressbar();
    }
}
