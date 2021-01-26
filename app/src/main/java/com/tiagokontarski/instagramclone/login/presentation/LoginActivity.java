package com.tiagokontarski.instagramclone.login.presentation;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.tiagokontarski.instagramclone.commons.views.AbstractActivity;
import com.tiagokontarski.instagramclone.commons.LoadingButton;
import com.tiagokontarski.instagramclone.R;
import com.tiagokontarski.instagramclone.login.datasource.LoginDataSouce;
import com.tiagokontarski.instagramclone.login.datasource.LoginLocalDataSource;
import com.tiagokontarski.instagramclone.main.presentation.MainActivity;
import com.tiagokontarski.instagramclone.register.presentation.RegisterActivity;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class LoginActivity extends AbstractActivity implements LoginViews {


    @BindView(R.id.login_edit_text_email_input)
    TextInputLayout tilEmail;
    @BindView(R.id.login_edit_text_password_input)
    TextInputLayout tilPass;
    @BindView(R.id.login_edit_text_email)
    EditText etEmail;
    @BindView(R.id.login_edit_text_password)
    EditText etPassword;
    @BindView(R.id.login_buttom_enter)
    LoadingButton button;

    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarDark();

    }

    @Override
    protected void onInject() {
        LoginDataSouce dataSouce = new LoginLocalDataSource();
        presenter = new LoginPresenter(this, dataSouce);
    }

    @OnTextChanged({R.id.login_edit_text_email, R.id.login_edit_text_password})
    public void onTextChanged(CharSequence s) {
        button.setEnabled(
                !etEmail.getText().toString().isEmpty() &&
                        !etPassword.getText().toString().isEmpty());

        if (s.hashCode() == etEmail.getText().hashCode()) {
            etEmail.setBackground(findDrawable(R.drawable.edit_text_background));
            tilEmail.setError(null);
            tilEmail.setErrorEnabled(false);
        } else if (s.hashCode() == etPassword.getText().hashCode()) {
            etPassword.setBackground(findDrawable(R.drawable.edit_text_background));
            tilPass.setError(null);
            tilPass.setErrorEnabled(false);
        }
    }

    @Override
    public void onFailureForm(String emailError, String passwordError) {
        if (emailError != null) {
            tilEmail.setError(emailError);
            etEmail.setBackground(findDrawable(R.drawable.edit_text_background_error));
        }

        if (passwordError != null) {
            tilPass.setError(passwordError);
            etPassword.setBackground(findDrawable(R.drawable.edit_text_background_error));
        }
    }

    @Override
    public void onUserLogged() {
        MainActivity.launch(this);
    }

    @Override
    public void showProgressBar() {
        button.showProgress(true);
    }

    @Override
    public void hideProgressbar() {
        button.showProgress(false);
    }

    @OnClick(R.id.login_buttom_enter)
    public void onButtonEnterClick() {
        presenter.login(etEmail.getText().toString(), etPassword.getText().toString());
    }

    @OnClick(R.id.login_text_view_register)
    public void navToRegisterActivity(){
        RegisterActivity.launch(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }
}