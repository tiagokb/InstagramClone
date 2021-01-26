package com.tiagokontarski.instagramclone.register.presentation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.tiagokontarski.instagramclone.R;
import com.tiagokontarski.instagramclone.commons.views.AbstractActivity;
import com.tiagokontarski.instagramclone.login.presentation.LoginActivity;
import com.tiagokontarski.instagramclone.register.presentation.fragments.RegisterEmailFragment;
import com.tiagokontarski.instagramclone.register.presentation.fragments.RegisterPhotoFragment;
import com.tiagokontarski.instagramclone.register.presentation.fragments.RegisterUsernamePasswordFragment;
import com.tiagokontarski.instagramclone.register.presentation.fragments.presentation.RegisterPresenter;

public class RegisterActivity extends AbstractActivity implements RegisterView {

    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStatusBarDark();
    }

    @Override
    protected void onInject() {
        presenter = new RegisterPresenter();
        presenter.setView(this);

        showNextView(RegisterSteps.EMAIL);
    }

    @Override
    public void showNextView(RegisterSteps step) {
        Fragment frag = RegisterEmailFragment.newInstance(presenter);
        switch (step) {
            case EMAIL:
                break;
            case NAME_PASSWORD:
                frag = RegisterUsernamePasswordFragment.newInstance(presenter);
                break;
            case PHOTO:
                frag = RegisterPhotoFragment.newInstance(presenter);
                break;

        }

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        if (manager.findFragmentById(R.id.register_fragment) == null) {
            transaction.add(R.id.register_fragment, frag, step.name());
        } else {
            transaction.replace(R.id.register_fragment, frag, step.name());
            transaction.addToBackStack(step.name());
        }

        transaction.commit();
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }
}