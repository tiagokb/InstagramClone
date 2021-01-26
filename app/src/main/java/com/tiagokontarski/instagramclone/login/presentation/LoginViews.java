package com.tiagokontarski.instagramclone.login.presentation;

import com.tiagokontarski.instagramclone.commons.views.Views;

public interface LoginViews extends Views {

    void onFailureForm(String emailError, String passwordError);

    void onUserLogged();

}
