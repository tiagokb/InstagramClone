package com.tiagokontarski.instagramclone.login.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.tiagokontarski.instagramclone.commons.LoadingButton;
import com.tiagokontarski.instagramclone.R;

public class LoginActivity extends AppCompatActivity {

    private LoadingButton button;
    private TextInputLayout tilEmail;
    private TextInputLayout tilPass;
    private EditText etPassword;
    private EditText etEmail;
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!s.toString().isEmpty()) {
                button.setEnabled(true);
            } else {
                button.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.login_edit_text_email);
        etPassword = findViewById(R.id.login_edit_text_password);

        tilEmail = findViewById(R.id.login_edit_text_email_input);
        tilPass = findViewById(R.id.login_edit_text_password_input);

        etEmail.addTextChangedListener(watcher);
        etPassword.addTextChangedListener(watcher);

        button = findViewById(R.id.login_buttom_enter);
        button.setOnClickListener(v -> {
            button.showProgress(true);
            new Handler().postDelayed(() -> {
                button.showProgress(false);

                tilEmail.setError("O teu email ta errado irmão, qualé!!");
                tilPass.setError("A tua senha n ta bagual!");

                etEmail.setBackground(ContextCompat.getDrawable(LoginActivity.this,
                        R.drawable.edit_text_background_error));

                etPassword.setBackground(ContextCompat.getDrawable(LoginActivity.this,
                        R.drawable.edit_text_background_error));

            }, 4000);
        });
    }


}