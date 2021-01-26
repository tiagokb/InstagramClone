package com.tiagokontarski.instagramclone.register.presentation.datasource;

import com.tiagokontarski.instagramclone.commons.model.DataBase;
import com.tiagokontarski.instagramclone.commons.presenter.Presenter;

public class EmailLocalDataSource implements EmailDataSource {
    @Override
    public void register(String username, String email, String password) {
        DataBase.getInstance().register(username, email, password);
    }
}
