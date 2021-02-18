package com.tiagokontarski.instagramclone.register.presentation;

import android.content.Context;

import com.tiagokontarski.instagramclone.commons.views.Views;

public interface RegisterView {

    void showNextView(RegisterSteps step);

    void onUserCreated();

    interface EmailView {

        Context getContext();

        void onFailureForm(String emailError);

        void showNextView();
    }

    interface NamePasswaordView extends Views {

        Context getContext();

        void onFailureForm(String nameError, String passwordError, String matchingPasswordError);

        void onCreateUserFailure(String message);
    }

    interface WelcomeView extends Views {
        Context getContext();
    }

    interface PhotoView {

        Context getContext();
    }
}
