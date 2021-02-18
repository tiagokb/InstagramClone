package com.tiagokontarski.instagramclone.register.presentation.datasource;

import com.tiagokontarski.instagramclone.commons.model.DataBase;
import com.tiagokontarski.instagramclone.commons.model.UserAuth;
import com.tiagokontarski.instagramclone.commons.presenter.Presenter;

public class RegisterLocalDataSource implements RegisterDataSource {
    @Override
    public void register(String username, String email, String password, Presenter presenter) {
        DataBase.getInstance().register(username, email, password)
                .addOnFailureListener(e -> presenter.onError(e.getMessage()))
                .addOnSuccessListener((DataBase.OnSuccessListener<UserAuth>) presenter::onSuccess)
                .addOnCompleteListener(presenter::onComplete);
    }
}
