package com.tiagokontarski.instagramclone.commons.presenter;

import com.tiagokontarski.instagramclone.commons.model.UserAuth;

public interface Presenter<T> {
    void onSuccess(T response);

    void onError(String message);

    void onComplete();
}
