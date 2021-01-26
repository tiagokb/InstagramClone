package com.tiagokontarski.instagramclone.commons.views;

import android.content.Context;

import androidx.annotation.ColorRes;

public interface Views {

    Context getContext();

    void showProgressBar();

    void hideProgressbar();

    void setStatusBarDark();
}
