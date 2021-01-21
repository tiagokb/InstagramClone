package com.tiagokontarski.instagramclone.commons;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.tiagokontarski.instagramclone.R;

public class CustomDialog extends Dialog {

    private LinearLayout dialogLinearLayout;
    private LinearLayout.LayoutParams params;

    private TextView titleView;
    private TextView[] titlesViews;
    private int titleId;

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
        titleView = findViewById(R.id.dialog_title);
        params.setMargins(30, 50, 30, 50);
    }

    @Override
    public void setTitle(int titleId) {
        this.titleId = titleId;
    }

    @Override
    public void show() {
        super.show();

        titleView.setText(titleId);
        for (TextView textView : titlesViews) {
            dialogLinearLayout.addView(textView, params);
        }
    }

    private void addButton(final View.OnClickListener listener, @StringRes int[] texts) {
        titlesViews = new TextView[texts.length];
        for (int i = 0; i < texts.length; i++){
            TextView tv = new TextView(new ContextThemeWrapper(getContext(), R.style.InstaTextViewBaseDialog), null, 0);
            tv.setId(texts[i]);
            tv.setText(texts[i]);
            tv.setOnClickListener(v -> {
                listener.onClick(v);
                dismiss();
            });
            titlesViews[i] = tv;
        }
    }

    public static class Builder {

        private final Context context;
        private int titleId;
        private View.OnClickListener listener;
        private int[] texts;

        public Builder(Context context){
            this.context = context;
        }

        public Builder setTitle(@StringRes int titleId){
            this.titleId = titleId;
            return this;
        }

        public Builder addButton(View.OnClickListener listener, @StringRes int... texts){
            this.listener = listener;
            this.texts = texts;
            return this;
        }

        public CustomDialog build(){
            CustomDialog dialog = new CustomDialog(context);
            dialog.setTitle(titleId);
            dialog.addButton(listener, texts);
            return dialog;
        }
    }


}

