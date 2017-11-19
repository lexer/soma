package com.github.lexer.soma.framework;

import android.support.annotation.IdRes;
import android.view.View;

public abstract class ViewController {

    private boolean isDetaching = false;
    private boolean attached;
    private View view;

    public void onAttach() {
    }

    final void attach(View view) {
        this.view = view;
        onAttach();
        this.attached = true;
        if (this.isDetaching) {
            detach(view);
        }
    }

    final boolean attached() {
        return this.attached;
    }

    final void detach(View view) {
        this.isDetaching = true;
        if (this.attached) {
            onDetach();
            this.view = null;
            this.attached = false;
            this.isDetaching = false;
        }
    }

    public void onDetach() {
    }

    public View getView() {
        if (view == null) { throw new IllegalStateException("View accessed while ViewController is detached."); }
        return this.view;
    }

    public final <T extends View> T findView(@IdRes int viewId) {
        return (T)getView().findViewById(viewId);
    }
}