package com.tiagokontarski.instagramclone.register.presentation.fragments.presentation;

import com.tiagokontarski.instagramclone.R;
import com.tiagokontarski.instagramclone.commons.model.UserAuth;
import com.tiagokontarski.instagramclone.commons.presenter.Presenter;
import com.tiagokontarski.instagramclone.commons.utils.Strings;
import com.tiagokontarski.instagramclone.register.presentation.RegisterSteps;
import com.tiagokontarski.instagramclone.register.presentation.RegisterView;

public class RegisterPresenter implements Presenter {

    private RegisterView view;
    private RegisterView.EmailView emailView;
    private RegisterView.NamePasswaordView namePasswaordView;
    private RegisterView.PhotoView photoView;

    private String email;
    private String username;
    private String password;

    public void setView(RegisterView view) {
        this.view = view;
    }

    public void setEmailView(RegisterView.EmailView emailView) {
        this.emailView = emailView;
    }

    public void setNamePasswaordView(RegisterView.NamePasswaordView namePasswaordView) {
        this.namePasswaordView = namePasswaordView;
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
            namePasswaordView.onFailureForm(
                    null,
                    namePasswaordView.getContext().getString(R.string.password_error_too_short),
                    null);
            return;
        }

        if (!password.equals(passwordConfirm)) {
            namePasswaordView.onFailureForm(null,
                    null,
                    namePasswaordView.getContext().getString(R.string.password_not_equal));
            return;
        }

        this.username = username;
        this.password = password;
        view.showNextView(RegisterSteps.PHOTO);
    }

    @Override
    public void onSuccess(UserAuth userAuth) {
    }

    @Override
    public void onError(String message) {
    }

    @Override
    public void onComplete() {
    }
}
