package com.tiagokontarski.instagramclone.login.datasource;

import com.tiagokontarski.instagramclone.commons.model.DataBase;
import com.tiagokontarski.instagramclone.commons.model.UserAuth;
import com.tiagokontarski.instagramclone.commons.presenter.Presenter;

public class LoginLocalDataSource implements LoginDataSouce {
    @Override
    public void login(String email, String password, Presenter presenter) {
        DataBase.getInstance().login(email, password)
                .addOnFailureListener(e -> {
                    presenter.onError(e.getMessage());
                })
                .addOnSuccessListener(response -> {
                    presenter.onSuccess((UserAuth) response);
                })
                .addOnCompleteListener(presenter::onComplete);
    }
}
