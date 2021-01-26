package com.tiagokontarski.instagramclone.register.presentation.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tiagokontarski.instagramclone.R;
import com.tiagokontarski.instagramclone.commons.views.AbstractFragment;
import com.tiagokontarski.instagramclone.commons.views.CustomDialog;
import com.tiagokontarski.instagramclone.register.presentation.RegisterView;
import com.tiagokontarski.instagramclone.register.presentation.fragments.presentation.RegisterPresenter;

public class RegisterPhotoFragment extends AbstractFragment<RegisterPresenter> implements RegisterView.PhotoView {
    private RegisterPhotoFragment() {
    }

    public static RegisterPhotoFragment newInstance(RegisterPresenter presenter) {
        RegisterPhotoFragment fragment = new RegisterPhotoFragment();

        fragment.setPresenter(presenter);
        presenter.setPhotoView(fragment);


        return fragment;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_register_photo;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CustomDialog dialog = new CustomDialog.Builder(getContext())
                .setTitle(R.string.define_photo_profile)
                .addButton(v -> {
                    Log.i("teste", "teste");
                }, R.string.take_picture, R.string.search_gallery)
                .build();

        dialog.show();
    }

    @Override
    public void onFailureForm(String nameError, String passwordError, String matchingPasswordError) {
    }
}
