package com.tiagokontarski.instagramclone.register.presentation.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputLayout;
import com.tiagokontarski.instagramclone.R;
import com.tiagokontarski.instagramclone.commons.LoadingButton;
import com.tiagokontarski.instagramclone.commons.utils.Drawables;
import com.tiagokontarski.instagramclone.commons.views.AbstractFragment;
import com.tiagokontarski.instagramclone.register.presentation.RegisterView;
import com.tiagokontarski.instagramclone.register.presentation.datasource.EmailDataSource;
import com.tiagokontarski.instagramclone.register.presentation.datasource.EmailLocalDataSource;
import com.tiagokontarski.instagramclone.register.presentation.fragments.presentation.RegisterPresenter;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class RegisterEmailFragment extends AbstractFragment<RegisterPresenter> implements RegisterView.EmailView {

    @BindView(R.id.register_edit_text_email_input)
    TextInputLayout tilEmail;
    @BindView(R.id.register_edit_text_email)
    EditText etEmail;
    @BindView(R.id.register_buttom_next)
    LoadingButton btnNext;

    public RegisterEmailFragment() {
    }

    public static RegisterEmailFragment newInstance(RegisterPresenter presenter) {
        RegisterEmailFragment fragment = new RegisterEmailFragment();
        fragment.setPresenter(presenter);
        presenter.setEmailView(fragment);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onFailureForm(String message) {
        if (message != null) {
            tilEmail.setErrorEnabled(true);
            tilEmail.setError(message);
        }
    }

    @Override
    public void showNextView() {

    }

    @OnClick(R.id.register_buttom_next)
    public void onButtonNextClick() {
        presenter.setEmail(etEmail.getText().toString());
    }

    @OnClick(R.id.register_text_view_login)
    public void onTextViewLoginPressed() {
        if (isAdded() && getActivity() != null) {
            getActivity().finish();
        }
    }

    @OnTextChanged(R.id.register_edit_text_email)
    public void onTextEmailChanged(CharSequence s) {
        btnNext.setEnabled(!etEmail.getText().toString().isEmpty());

        etEmail.setBackground(Drawables.getDrawable(getContext(), R.drawable.edit_text_background));
        tilEmail.setError(null);
        tilEmail.setErrorEnabled(false);
    }

    @Override
    public void showProgressBar() {
        btnNext.showProgress(true);
    }

    @Override
    public void hideProgressbar() {
        btnNext.showProgress(false);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_register_email;
    }
}
