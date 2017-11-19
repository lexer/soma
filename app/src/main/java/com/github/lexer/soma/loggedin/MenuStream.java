package com.github.lexer.soma.loggedin;

import com.jakewharton.rxrelay2.PublishRelay;
import io.reactivex.Observable;

public class MenuStream {

    private PublishRelay<String> menuRelay = PublishRelay.create();

    public Observable<String> observeMenu() {
        return menuRelay;
    }

    public void switchMenu(String menuItem) {
        menuRelay.accept(menuItem);
    }
}
