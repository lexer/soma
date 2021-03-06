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
    private Scope scope;
    private ViewGroup rootView;
    private View controllerView;

    public LoggedInRouter(Scope scope, ViewGroup rootView) {
        super(scope, rootView);

        userService = scope.getService("user_service");
        this.scope = scope;

        this.rootView = rootView;
    }

    @Override
    public void onScopeEnter() {
        super.onScopeEnter();

        ViewController homeViewController = new HomeViewController();
        controllerView = Controllers.createAndBind(R.layout.home, rootView, homeViewController);

        rootView.addView(controllerView);

        MenuRouter menuRouter = new MenuRouter(scope, rootView);
        scope.addService("menu_router", menuRouter);
    }

    @Override
    public void onScopeExit() {
        super.onScopeExit();

        rootView.removeView(controllerView);
    }
}
