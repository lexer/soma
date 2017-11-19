package com.github.lexer.soma.loggedin;

import android.view.View;
import android.view.ViewGroup;
import com.example.lexer.experiment1.R;
import com.github.lexer.soma.framework.Controllers;
import com.github.lexer.soma.framework.Router;
import com.github.lexer.soma.framework.Scope;
import com.github.lexer.soma.framework.ViewController;
import com.github.lexer.soma.loggedin.settings.SettingsRouter;
import io.reactivex.disposables.CompositeDisposable;

public class MenuRouter extends Router {

    private final ViewGroup secondaryContent;
    private Scope scope;
    private ViewGroup rootView;
    private View controllerView;

    private final MenuStream menuStream;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ViewGroup navigationContainer;

    private Scope secondaryScope;

    public MenuRouter(Scope scope, ViewGroup rootView) {
        super(scope, rootView);
        this.scope = scope;
        this.rootView = rootView;
        navigationContainer = rootView.findViewById(R.id.navigation);
        secondaryContent = rootView.findViewById(R.id.secondary_content);
        this.menuStream = new MenuStream();
    }

    @Override
    public void onScopeEnter() {
        super.onScopeEnter();

        ViewController menuViewController = new MenuViewController(menuStream);
        controllerView = Controllers.createAndBind(R.layout.menu, navigationContainer, menuViewController);

        navigationContainer.addView(controllerView);

        compositeDisposable.add(menuStream.observeMenu()
                .subscribe(m -> {
                    onMenuSelected(m);
                }));
    }

    private void onMenuSelected(String m) {
        if (m.equals("settings")) {
            secondaryScope = scope.createChild("settings_scope");
            Router secondaryContentRouter = new SettingsRouter(secondaryScope, secondaryContent);
            secondaryScope.addService("setting_router", secondaryContentRouter);
        }
    }

    @Override
    public void onScopeExit() {
        super.onScopeExit();

        rootView.removeView(controllerView);
    }
}
