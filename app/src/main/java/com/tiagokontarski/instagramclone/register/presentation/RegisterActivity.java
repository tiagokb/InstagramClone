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
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.tiagokontarski.instagramclone.R;
import com.tiagokontarski.instagramclone.commons.views.AbstractActivity;
import com.tiagokontarski.instagramclone.login.presentation.LoginActivity;
import com.tiagokontarski.instagramclone.main.presentation.MainActivity;
import com.tiagokontarski.instagramclone.register.presentation.datasource.RegisterDataSource;
import com.tiagokontarski.instagramclone.register.presentation.datasource.RegisterLocalDataSource;
import com.tiagokontarski.instagramclone.register.presentation.fragments.RegisterEmailFragment;
import com.tiagokontarski.instagramclone.register.presentation.fragments.RegisterPhotoFragment;
import com.tiagokontarski.instagramclone.register.presentation.fragments.RegisterUsernamePasswordFragment;
import com.tiagokontarski.instagramclone.register.presentation.fragments.RegisterWellcomeFragment;
import com.tiagokontarski.instagramclone.register.presentation.fragments.presentation.RegisterPresenter;

import butterknife.BindView;

public class RegisterActivity extends AbstractActivity implements RegisterView {

    private RegisterPresenter presenter;

    @BindView(R.id.register_container)
    ScrollView container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStatusBarDark();
    }

    @Override
    protected void onInject() {
        RegisterDataSource dataSource = new RegisterLocalDataSource();
        presenter = new RegisterPresenter();
        presenter.setView(this);
        presenter.setDataSource(dataSource);

        showNextView(RegisterSteps.EMAIL);
    }

    @Override
    public void showNextView(RegisterSteps step) {
        Fragment frag = null;

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) container.getLayoutParams();

        switch (step) {
            case EMAIL:
                layoutParams.gravity = Gravity.BOTTOM;
                frag = RegisterEmailFragment.newInstance(presenter);
                break;
            case NAME_PASSWORD:
                layoutParams.gravity = Gravity.BOTTOM;
                frag = RegisterUsernamePasswordFragment.newInstance(presenter);
                break;
            case WELCOME:
                layoutParams.gravity = Gravity.BOTTOM;
                frag = RegisterWellcomeFragment.newInstance(presenter);
                break;
            case PHOTO:
                layoutParams.gravity = Gravity.TOP;
                frag = RegisterPhotoFragment.newInstance(presenter);
                break;
        }

        container.setLayoutParams(layoutParams);

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

    @Override
    public void onUserCreated() {
        MainActivity.launch(this);
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