package com.github.lexer.soma.models;

import com.jakewharton.rxrelay2.BehaviorRelay;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class UserService {

    BehaviorRelay<User> userRelay = BehaviorRelay.create();

    public UserService() {
        userRelay.accept(User.EMPTY);
    }

    public void setUser(User user) {
        userRelay.accept(user);
    }

    public Observable<User> observeUser() {
        return userRelay;
    }

    public Observable<Boolean> isLoggedIn() {
        return userRelay.map(new Function<User, Boolean>() {
            @Override
            public Boolean apply(User user) throws Exception {
                return user != User.EMPTY;
            }
        });
    }
}
