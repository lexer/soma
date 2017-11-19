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

    public LoggedOutRouter(Scope newScope, ViewGroup rootView) {
        super(newScope, rootView);

        userService = newScope.getService("user_service");

        this.rootView = rootView;
    }

    @Override
    public void onScopeEnter() {
        super.onScopeEnter();

        ViewController signViewController = new SignInViewController(userService);
        View controllerView = Controllers.createAndBind(R.layout.sigin, rootView, signViewController);

        rootView.addView(controllerView);
    }
}
