package com.github.lexer.soma;

import android.widget.FrameLayout;
import com.github.lexer.soma.framework.Router;
import com.github.lexer.soma.framework.Scope;
import com.github.lexer.soma.loggedin.LoggedInRouter;
import com.github.lexer.soma.loggedout.LoggedOutRouter;
import com.github.lexer.soma.models.UserService;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

public class RootRouter extends Router {

    private final UserService userService;
    private FrameLayout rootView;
    private final Scope scope;
    private Disposable subscription = Disposables.empty();

    private Scope childScope;

    public RootRouter(Scope rootScope, FrameLayout rootView) {
        super(rootScope, rootView);

        this.scope = rootScope;
        userService = rootScope.getService("user_service");
        this.rootView = rootView;
    }

    @Override
    public void onScopeEnter() {
        subscription = userService.isLoggedIn().subscribe(loggedIn -> {
            onLoggedIn(loggedIn);
        });
    }

    private void onLoggedIn(Boolean loggedIn) {
        if (loggedIn) {
            if (childScope == null || childScope.getName() != "logged_in") {
                Scope newScope = scope.createChild("logged_in");

                scope.addService("logged_in_router", new LoggedInRouter(newScope, rootView));

                if (childScope != null) {
                    childScope.destroy();
                }

                childScope = newScope;
            }
        } else {
            if (childScope == null || childScope.getName() != "logged_out") {
                Scope newScope = scope.createChild("logged_out");

                scope.addService("logged_out_router", new LoggedOutRouter(newScope, rootView));

                if (childScope != null) {
                    childScope.destroy();
                }

                childScope = newScope;
            }
        }
    }

    @Override
    public void onScopeExit() {
        subscription.dispose();
    }
}
