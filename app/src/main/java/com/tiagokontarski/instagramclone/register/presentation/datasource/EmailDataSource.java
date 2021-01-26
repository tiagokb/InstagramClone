package com.tiagokontarski.instagramclone.register.presentation.datasource;

import com.tiagokontarski.instagramclone.commons.presenter.Presenter;

public interface EmailDataSource {
    void register(String username, String email, String password);
}
