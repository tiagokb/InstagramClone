package com.tiagokontarski.instagramclone.login.datasource;

import com.tiagokontarski.instagramclone.commons.presenter.Presenter;

public interface LoginDataSouce {
    void login(String email, String password, Presenter presenter);
}
