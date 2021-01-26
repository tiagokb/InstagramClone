package com.tiagokontarski.instagramclone.commons.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tiagokontarski.instagramclone.commons.utils.Colors;
import com.tiagokontarski.instagramclone.commons.utils.Drawables;
import com.tiagokontarski.instagramclone.register.presentation.fragments.presentation.RegisterPresenter;

import butterknife.ButterKnife;

public abstract class AbstractFragment<P> extends Fragment implements Views {

    protected P presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Nullable
    @Override
    public Context getContext() {
        return super.getContext();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressbar() {

    }

    @Override
    public void setStatusBarDark() {

    }

    public Drawable findDrawable(@DrawableRes int drawableId) {
        return Drawables.getDrawable(getContext(), drawableId);
    }

    public int findColor(@ColorRes int colorId) {
        return Colors.getColor(getContext(), colorId);
    }

    public abstract @LayoutRes
    int getLayout();

    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }
}
