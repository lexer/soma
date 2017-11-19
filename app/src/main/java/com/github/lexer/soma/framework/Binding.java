package com.github.lexer.soma.framework;

import android.view.View;

class Binding implements View.OnAttachStateChangeListener {

    private final View view;
    private final ViewController controller;

    Binding(View view, ViewController controller) {
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void onViewAttachedToWindow(View v) {
        controller.attach(view);
    }

    @Override
    public void onViewDetachedFromWindow(View v) {
        controller.detach(view);
    }

    public View getView() {
        return view;
    }

    public ViewController getController() {
        return controller;
    }
}