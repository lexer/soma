package com.github.lexer.soma;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import com.example.lexer.experiment1.R;
import com.github.lexer.soma.framework.Scope;
import com.github.lexer.soma.models.UserService;

public class MainActivity extends AppCompatActivity {

    FrameLayout rootView;
    private Scope rootScope;
    private Scope activityScope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootView = (FrameLayout) findViewById(R.id.root_view);
        rootScope = getApp().getRootScope();

        UserService userService = new UserService();
        rootScope.addService("user_service", userService);

        activityScope = rootScope.createChild("activity");

        RootRouter rootRouter = new RootRouter(activityScope, rootView);

        activityScope.addService("root_router", rootRouter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        activityScope.destroy();
    }

    private App getApp() {
        return (App)getApplication();
    }
}
