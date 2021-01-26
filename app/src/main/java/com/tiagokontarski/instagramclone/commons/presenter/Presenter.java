package com.tiagokontarski.instagramclone.commons.presenter;

import com.tiagokontarski.instagramclone.commons.model.UserAuth;

public interface Presenter {
    void onSuccess(UserAuth userAuth);

    void onError(String message);

    void onComplete();
}
