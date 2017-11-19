package com.github.lexer.soma.loggedout;

import android.view.View;
import com.example.lexer.experiment1.R;
import com.github.lexer.soma.framework.ViewController;
import com.github.lexer.soma.models.User;
import com.github.lexer.soma.models.UserService;

public class SignInViewController extends ViewController
{

    private View signInButton;
    private UserService userService;

    public SignInViewController(UserService userService) {

        this.userService = userService;
    }

    @Override
    public void onAttach() {
        super.onAttach();

        signInButton = findView(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userService.setUser(new User("John"));
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
