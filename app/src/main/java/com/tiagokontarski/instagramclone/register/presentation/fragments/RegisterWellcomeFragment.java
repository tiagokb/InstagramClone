package com.tiagokontarski.instagramclone.register.presentation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tiagokontarski.instagramclone.R;
import com.tiagokontarski.instagramclone.commons.presenter.Presenter;
import com.tiagokontarski.instagramclone.commons.views.AbstractFragment;
import com.tiagokontarski.instagramclone.commons.views.LoadingButton;
import com.tiagokontarski.instagramclone.register.presentation.RegisterView;
import com.tiagokontarski.instagramclone.register.presentation.fragments.presentation.RegisterPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterWellcomeFragment extends AbstractFragment<RegisterPresenter> implements RegisterView.WelcomeView {

    @BindView(R.id.register_wellcome_text_view_wellcome)
    TextView welcomeTextView;

    @BindView(R.id.register_wellcome_buttom_next)
    LoadingButton button;

    private RegisterWellcomeFragment() {
    }

    public static RegisterWellcomeFragment newInstance(RegisterPresenter presenter) {
        RegisterWellcomeFragment frag = new RegisterWellcomeFragment();
        frag.setPresenter(presenter);
        presenter.setWelcomeView(frag);

        return frag;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button.setEnabled(true);
        setWelcomeUsername();
    }

    public void setWelcomeUsername() {
        welcomeTextView.setText(getContext().getString(R.string.welcome_to_instagram, presenter.getUsername()));
    }

    @OnClick(R.id.register_wellcome_buttom_next)
    void onClickNextButton(){
        presenter.launchPhotoFragment();
    }

    @Override
    public void showProgressBar() {
        button.showProgress(true);
        button.setEnabled(false);
    }

    @Override
    public void hideProgressbar() {
        button.showProgress(false);
        button.setEnabled(true);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_register_wellcome;
    }
}
