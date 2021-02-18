package com.tiagokontarski.instagramclone.register.presentation.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import com.tiagokontarski.instagramclone.R;
import com.tiagokontarski.instagramclone.commons.views.AbstractFragment;
import com.tiagokontarski.instagramclone.commons.views.CustomDialog;
import com.tiagokontarski.instagramclone.commons.views.LoadingButton;
import com.tiagokontarski.instagramclone.register.presentation.RegisterView;
import com.tiagokontarski.instagramclone.register.presentation.fragments.presentation.RegisterPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterPhotoFragment extends AbstractFragment<RegisterPresenter> implements RegisterView.PhotoView {

    @BindView(R.id.register_photo_buttom_next)
    LoadingButton button;

    @BindView(R.id.register_photo_button_jump)
    AppCompatButton buttonJump;

    private RegisterPhotoFragment() {
    }

    public static RegisterPhotoFragment newInstance(RegisterPresenter presenter) {
        RegisterPhotoFragment fragment = new RegisterPhotoFragment();
        fragment.setPresenter(presenter);
        presenter.setPhotoView(fragment);

        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button.setEnabled(true);
    }

    @OnClick(R.id.register_photo_buttom_next)
    void onPictureButton() {
        createDialogConfirmation();
    }

    @OnClick(R.id.register_photo_button_jump)
    void onButtonJump() {
        presenter.onButtonJump();
    }

    private void createDialogConfirmation() {
        CustomDialog dialog = new CustomDialog.Builder(getContext())
                .setTitle(R.string.define_photo_profile)
                .addButton(v -> {
                    Log.i("teste", "teste");
                }, R.string.take_picture, R.string.search_gallery)
                .build();

        dialog.show();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_register_photo;
    }
}
