package com.github.lexer.soma.loggedin.settings;

import android.view.View;
import com.example.lexer.experiment1.R;
import com.github.lexer.soma.framework.ViewController;
import com.github.lexer.soma.models.User;
import com.github.lexer.soma.models.UserService;

public class SettingsViewController extends ViewController {

    private UserService userService;
    private View logoutButton;

    public SettingsViewController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAttach() {
        super.onAttach();

        logoutButton = findView(R.id.logout_button);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userService.setUser(User.EMPTY);
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
