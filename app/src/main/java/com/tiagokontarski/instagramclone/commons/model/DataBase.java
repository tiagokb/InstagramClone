package com.tiagokontarski.instagramclone.commons.model;

import android.os.Handler;
import android.provider.ContactsContract;

import java.util.HashSet;
import java.util.Set;

public class DataBase {

    private static DataBase INSTANCE;
    private static Set<UserAuth> userAuths;
    private static Set<User> users;
    private UserAuth userAuth;

    static {
        userAuths = new HashSet<>();
        users = new HashSet<>();

        User userTest = new User();
        userTest.setEmail("tiagokontarski@gmail.com");
        userTest.setUsername("tiagokontarski");
        userAuths.add(new UserAuth("tiagokontarski@gmail.com", "123456"));
        users.add(userTest);
    }

    private OnSuccessListener onSuccessListener;
    private OnFailureListener onFailureListener;
    private OnCompleteListener onCompleteListener;

    public static DataBase getInstance() {
        if (INSTANCE == null)
            INSTANCE = new DataBase();
        return INSTANCE;
    }

    public <T> DataBase addOnSuccessListener(OnSuccessListener<T> listener) {
        this.onSuccessListener = listener;
        return this;
    }

    public DataBase addOnFailureListener(OnFailureListener listener) {
        this.onFailureListener = listener;
        return this;
    }

    public DataBase addOnCompleteListener(OnCompleteListener listener) {
        this.onCompleteListener = listener;
        return this;
    }

    public DataBase login(String email, String password) {

        timeOut(() -> {
            UserAuth userAuth = new UserAuth();
            userAuth.setEmail(email);
            userAuth.setPassword(password);

            if (!userAuths.contains(userAuth)) {
                this.userAuth = null;
                onFailureListener.onFailure(new IllegalArgumentException("Usuário não encontrado"));
            } else {
                this.userAuth = userAuth;
                onSuccessListener.onSuccess(userAuth);
            }

            onCompleteListener.onComplete();
        });

        return this;
    }

    public DataBase register(String username, String email, String password) {

        timeOut(() -> {

            UserAuth userAuth = new UserAuth();
            userAuth.setEmail(email);
            userAuth.setPassword(password);

            User user = new User();
            user.setEmail(email);
            user.setUsername(username);

            boolean added = users.add(user);
            if (!added) {
                this.userAuth = null;
                onFailureListener.onFailure(new IllegalArgumentException("Usuário já cadastrado!"));
            } else {
                this.userAuth = userAuth;
                onSuccessListener.onSuccess(userAuth);
            }

            onCompleteListener.onComplete();
        });

        return this;
    }

    private void timeOut(Runnable r) {
        int TIME_OUT = 2000;
        new Handler().postDelayed(r, TIME_OUT);
    }

    public interface OnSuccessListener<T> {
        void onSuccess(T response);
    }

    public interface OnFailureListener {
        void onFailure(Exception e);
    }

    public interface OnCompleteListener {
        void onComplete();
    }
}
