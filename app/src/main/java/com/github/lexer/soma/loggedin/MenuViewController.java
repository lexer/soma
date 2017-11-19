package com.github.lexer.soma.loggedin;

import android.view.View;
import com.example.lexer.experiment1.R;
import com.github.lexer.soma.framework.ViewController;

public class MenuViewController extends ViewController {

    private View settingMenuItem;
    private MenuStream menuStream;

    public MenuViewController(MenuStream menuStream) {
        this.menuStream = menuStream;
    }

    @Override
    public void onAttach() {
        super.onAttach();

        settingMenuItem = findView(R.id.settings_menu_item);

        settingMenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuStream.switchMenu("settings");
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
