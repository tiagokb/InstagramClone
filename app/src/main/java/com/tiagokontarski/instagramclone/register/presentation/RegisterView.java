package com.tiagokontarski.instagramclone.register.presentation;

import android.content.Context;

public interface RegisterView {

    void showNextView(RegisterSteps step);

    interface EmailView {

        Context getContext();

        void onFailureForm(String emailError);

        void showNextView();
    }

    interface NamePasswaordView {
        Context getContext();

        void onFailureForm(String nameError, String passwordError, String matchingPasswordError);
    }

    interface PhotoView {
        Context getContext();

        void onFailureForm(String nameError, String passwordError, String matchingPasswordError);
    }
}
