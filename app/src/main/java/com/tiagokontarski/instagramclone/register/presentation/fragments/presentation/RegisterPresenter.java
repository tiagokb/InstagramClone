package com.tiagokontarski.instagramclone.register.presentation.fragments.presentation;

import android.os.Handler;

import com.tiagokontarski.instagramclone.R;
import com.tiagokontarski.instagramclone.commons.model.UserAuth;
import com.tiagokontarski.instagramclone.commons.presenter.Presenter;
import com.tiagokontarski.instagramclone.commons.utils.Strings;
import com.tiagokontarski.instagramclone.register.presentation.RegisterSteps;
import com.tiagokontarski.instagramclone.register.presentation.RegisterView;
import com.tiagokontarski.instagramclone.register.presentation.datasource.RegisterDataSource;
import com.tiagokontarski.instagramclone.register.presentation.fragments.RegisterWellcomeFragment;

public class RegisterPresenter implements Presenter<UserAuth> {

    private RegisterDataSource dataSource;

    private RegisterView view;
    private RegisterView.EmailView emailView;
    private RegisterView.NamePasswaordView namePasswordView;
    private RegisterView.WelcomeView welcomeView;
    private RegisterView.PhotoView photoView;

    private String email;
    private String username;
    private String password;

    public void setDataSource(RegisterDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setView(RegisterView view) {
        this.view = view;
    }

    public void setEmailView(RegisterView.EmailView emailView) {
        this.emailView = emailView;
    }

    public void setNamePasswordView(RegisterView.NamePasswaordView namePasswordView) {
        this.namePasswordView = namePasswordView;
    }

    public void setWelcomeView(RegisterView.WelcomeView welcomeView) {
        this.welcomeView = welcomeView;
    }

    public void setPhotoView(RegisterView.PhotoView photoView) {
        this.photoView = photoView;
    }

    public void setEmail(String email) {
        if (!Strings.isEmailValid(email)) {
            //falar pra fragment que o email não é valido
            emailView.onFailureForm(emailView.getContext().getString(R.string.invalid_email));
            return;
        }

        this.email = email;
        view.showNextView(RegisterSteps.NAME_PASSWORD);
    }

    public void setNameAndPassword(String username, String password, String passwordConfirm) {
        if (password.length() < 6) {
            namePasswordView.onFailureForm(
                    null,
                    namePasswordView.getContext().getString(R.string.password_error_too_short),
                    null);
            return;
        }

        if (!password.equals(passwordConfirm)) {
            namePasswordView.onFailureForm(null,
                    null,
                    namePasswordView.getContext().getString(R.string.password_not_equal));
            return;
        }

        this.username = username;
        this.password = password;

        namePasswordView.showProgressBar();
        dataSource.register(this.username, this.email, this.password, this);
    }

    public String getUsername() {
        return username;
    }

    public void launchPhotoFragment() {
        welcomeView.showProgressBar();
        new Handler().postDelayed(() -> {
            welcomeView.hideProgressbar();
            view.showNextView(RegisterSteps.PHOTO);
        }, 3000);
    }

    public void onButtonJump() {
        view.onUserCreated();
    }

    @Override
    public void onSuccess(UserAuth userAuth) {
        view.showNextView(RegisterSteps.WELCOME);
    }

    @Override
    public void onError(String message) {
        namePasswordView.onCreateUserFailure(message);
    }

    @Override
    public void onComplete() {
        namePasswordView.hideProgressbar();
    }
}
