package com.tiagokontarski.instagramclone.commons.model;

import android.os.Handler;
import android.provider.ContactsContract;

import java.util.HashSet;
import java.util.Set;

public class DataBase {

    private static DataBase INSTANCE;
    private static Set<UserAuth> userAuths;
    private UserAuth userAuth;

    static {
        userAuths = new HashSet<>();
        userAuths.add(new UserAuth("user1@gmail.com", "1"));
        userAuths.add(new UserAuth("user2@gmail.com", "2"));
        userAuths.add(new UserAuth("user3@gmail.com", "3"));
        userAuths.add(new UserAuth("user4@gmail.com", "4"));
        userAuths.add(new UserAuth("user5@gmail.com", "5"));
        userAuths.add(new UserAuth("user6@gmail.com", "6"));
        userAuths.add(new UserAuth("user7@gmail.com", "7"));
        userAuths.add(new UserAuth("user8@gmail.com", "8"));
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

            if (userAuths.contains(userAuth)) {
                this.userAuth = null;
                onFailureListener.onFailure(new IllegalArgumentException("Usuário já cadastrado"));
            } else {
                this.userAuth = userAuth;
                User user = new User(username, userAuth);
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
