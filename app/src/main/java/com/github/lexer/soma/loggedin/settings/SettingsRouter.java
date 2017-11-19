package com.github.lexer.soma.loggedin.settings;

import android.view.View;
import android.view.ViewGroup;
import com.example.lexer.experiment1.R;
import com.github.lexer.soma.framework.Controllers;
import com.github.lexer.soma.framework.Router;
import com.github.lexer.soma.framework.Scope;
import com.github.lexer.soma.framework.ViewController;
import com.github.lexer.soma.models.UserService;

public class SettingsRouter extends Router {

    private final UserService userService;
    private ViewGroup rootView;
    private View controllerView;

    public SettingsRouter(Scope scope, ViewGroup rootView) {
        super(scope, rootView);
        this.rootView = rootView;

        userService = scope.getService("user_service");
    }

    @Override
    public void onScopeEnter() {
        super.onScopeEnter();

        ViewController viewController = new SettingsViewController(userService);
        controllerView = Controllers.createAndBind(R.layout.settings, rootView, viewController);

        rootView.addView(controllerView);
    }

    @Override
    public void onScopeExit() {
        super.onScopeExit();

        rootView.removeView(controllerView);
    }
}
