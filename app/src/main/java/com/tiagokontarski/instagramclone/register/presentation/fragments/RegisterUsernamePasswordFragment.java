package com.tiagokontarski.instagramclone.register.presentation.fragments;

import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.tiagokontarski.instagramclone.R;
import com.tiagokontarski.instagramclone.commons.views.LoadingButton;
import com.tiagokontarski.instagramclone.commons.views.AbstractFragment;
import com.tiagokontarski.instagramclone.register.presentation.RegisterView;
import com.tiagokontarski.instagramclone.register.presentation.datasource.RegisterDataSource;
import com.tiagokontarski.instagramclone.register.presentation.datasource.RegisterLocalDataSource;
import com.tiagokontarski.instagramclone.register.presentation.fragments.presentation.RegisterPresenter;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class RegisterUsernamePasswordFragment extends AbstractFragment<RegisterPresenter> implements RegisterView.NamePasswaordView {

    @BindView(R.id.register_edit_text_username_input)
    TextInputLayout tilUsername;

    @BindView(R.id.register_edit_text_password_input)
    TextInputLayout tilPassword;

    @BindView(R.id.register_edit_text_password_confirm_input)
    TextInputLayout tilConfirm;

    @BindView(R.id.register_edit_text_username)
    EditText etUsername;

    @BindView(R.id.register_edit_text_password)
    EditText etPassword;

    @BindView(R.id.register_edit_text_password_confirm)
    EditText etConfirm;

    @BindView(R.id.register_name_buttom_continuee)
    LoadingButton btnNext;

    private RegisterUsernamePasswordFragment() {
    }

    public static RegisterUsernamePasswordFragment newInstance(RegisterPresenter presenter) {
        RegisterUsernamePasswordFragment fragment = new RegisterUsernamePasswordFragment();
        fragment.setPresenter(presenter);
        presenter.setNamePasswordView(fragment);
        return fragment;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_register_username_password;
    }

    @Override
    public void showProgressBar() {
        btnNext.showProgress(true);
    }

    @Override
    public void hideProgressbar() {
        btnNext.showProgress(false);
    }

    @OnClick(R.id.register_name_buttom_continuee)
    public void onButtonNext() {
        presenter.setNameAndPassword(etUsername.getText().toString(), etPassword.getText().toString(), etConfirm.getText().toString());
    }

    @OnClick(R.id.register_name_text_view_login)
    public void onButtonCancel() {
        if (isAdded() && getActivity() != null) {
            getActivity().finish();
        }
    }

    @OnTextChanged({R.id.register_edit_text_username, R.id.register_edit_text_password, R.id.register_edit_text_password_confirm})
    public void onTextChanged(CharSequence s) {
        btnNext.setEnabled(
                !etUsername.getText().toString().isEmpty() &&
                        !etPassword.getText().toString().isEmpty() &&
                        !etConfirm.getText().toString().isEmpty());

        if (s.hashCode() == etUsername.getText().hashCode()) {
            etUsername.setBackground(findDrawable(R.drawable.edit_text_background));
            tilUsername.setError(null);
            tilUsername.setErrorEnabled(false);
        } else if (s.hashCode() == etPassword.getText().hashCode()) {
            tilPassword.setErrorEnabled(false);
            tilPassword.setError(null);
            etPassword.setBackground(findDrawable(R.drawable.edit_text_background));
        } else if (s.hashCode() == etConfirm.getText().hashCode()) {
            etConfirm.setBackground(findDrawable(R.drawable.edit_text_background));
            tilConfirm.setError(null);
            tilConfirm.setErrorEnabled(false);
        }

    }


    @Override
    public void onFailureForm(String nameError, String passwordError, String matchingPasswordError) {
        if (nameError != null) {
            tilUsername.setErrorEnabled(true);
            tilUsername.setError(nameError);
            etUsername.setBackground(findDrawable(R.drawable.edit_text_background_error));
        }

        if (passwordError != null) {
            tilPassword.setErrorEnabled(true);
            tilPassword.setError(passwordError);
            etPassword.setBackground(findDrawable(R.drawable.edit_text_background_error));
        }

        if (matchingPasswordError != null) {
            tilConfirm.setErrorEnabled(true);
            tilConfirm.setError(matchingPasswordError);
            etConfirm.setBackground(findDrawable(R.drawable.edit_text_background_error));
        }
    }

    @Override
    public void onCreateUserFailure(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
