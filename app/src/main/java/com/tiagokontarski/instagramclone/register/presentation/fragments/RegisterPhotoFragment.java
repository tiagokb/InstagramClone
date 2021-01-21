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
import com.tiagokontarski.instagramclone.commons.CustomDialog;

public class RegisterPhotoFragment extends Fragment {
    public RegisterPhotoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_photo, container, false);
        //TODO change ScrollView layout_gravity to Top

        return view;
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
}
