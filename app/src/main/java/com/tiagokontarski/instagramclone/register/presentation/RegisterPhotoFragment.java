package com.tiagokontarski.instagramclone.register.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tiagokontarski.instagramclone.R;

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
}
