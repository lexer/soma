package com.github.lexer.soma.loggedout;

import android.view.View;
import android.view.ViewGroup;
import com.example.lexer.experiment1.R;
import com.github.lexer.soma.framework.Controllers;
import com.github.lexer.soma.framework.Router;
import com.github.lexer.soma.framework.Scope;
import com.github.lexer.soma.framework.ViewController;
import com.github.lexer.soma.models.UserService;

public class LoggedOutRouter extends Router {

    private final UserService userService;
    private ViewGroup rootView;
    private View controllerView;

    public LoggedOutRouter(Scope newScope, ViewGroup rootView) {
        super(newScope, rootView);

        userService = newScope.getService("user_service");

        this.rootView = rootView;
    }

    @Override
    public void onScopeEnter() {
        super.onScopeEnter();

        ViewController signViewController = new SignInViewController(userService);
        controllerView = Controllers.createAndBind(R.layout.sigin, rootView, signViewController);

        rootView.addView(controllerView);
    }

    @Override
    public void onScopeExit() {
        super.onScopeExit();

        rootView.removeView(controllerView);
    }
}
