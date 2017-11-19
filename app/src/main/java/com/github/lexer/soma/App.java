package com.github.lexer.soma;

import android.app.Application;
import com.github.lexer.soma.framework.Scope;

public class App extends Application {

    private Scope rootScope;

    @Override
    public void onCreate() {
        super.onCreate();

         rootScope = Scope.createRootScope();
    }

    public Scope getRootScope() {
        return rootScope;
    }
}
