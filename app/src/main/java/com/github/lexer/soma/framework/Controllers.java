package com.github.lexer.soma.framework;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Controllers {

    public static View createAndBind(@LayoutRes int resId, ViewGroup container, ViewController controller) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());

        final View view = inflater.inflate(resId, container, false);
        bind(view, controller);
        return view;
    }

    public static void bind(View view, ViewController controller) {
        final Binding binding = new Binding(view, controller);
        view.addOnAttachStateChangeListener(binding);
    }

}