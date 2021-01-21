package com.tiagokontarski.instagramclone.commons;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.tiagokontarski.instagramclone.R;

public class CustomDialog extends Dialog {

    private LinearLayout dialogLinearLayout;
    private LinearLayout.LayoutParams params;

    private TextView titleView;
    private TextView[] titlesViews;

    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    public CustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom);

        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        dialogLinearLayout = findViewById(R.id.dialog_container);
        params.setMargins(30, 50, 30, 50);


    }
}
