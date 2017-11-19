package com.github.lexer.soma.loggedin;

import android.view.View;
import android.view.ViewGroup;
import com.example.lexer.experiment1.R;
import com.github.lexer.soma.framework.Controllers;
import com.github.lexer.soma.framework.Router;
import com.github.lexer.soma.framework.Scope;
import com.github.lexer.soma.framework.ViewController;
import com.github.lexer.soma.models.UserService;

public class LoggedInRouter extends Router {

    private final UserService userService;
    private ViewGroup rootView;

    public LoggedInRouter(Scope newScope, ViewGroup rootView) {
        super(newScope, rootView);

        userService = newScope.getService("user_service");

        this.rootView = rootView;
    }

    @Override
    public void onScopeEnter() {
        super.onScopeEnter();

        ViewController homeViewController = new HomeViewController();
        View controllerView = Controllers.createAndBind(R.layout.home, rootView, homeViewController);

        rootView.addView(controllerView);
    }
}
