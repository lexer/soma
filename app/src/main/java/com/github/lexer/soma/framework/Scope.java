package com.github.lexer.soma.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Scope {

    private Scope parentScope;

    private HashMap<String, Object> services = new HashMap<>();

    private List<Scope> children = new ArrayList<>();
    private String name;

    private Scope(Scope parent, String name) {
        parentScope = parent;
        this.name = name;
    }

    public static Scope createRootScope() {
        return new Scope(new RootScope(), "root");
    }

    public Scope createChild(String name) {
        Scope scope = new Scope(this, name);

        this.children.add(parentScope);

        return scope;
    }

    public void destroy() {
        for (Scope scope : children) {
            scope.destroy();
        }

        for (Object service : services.values()) {
            if (service instanceof Scoped) {
                Scoped scoped = (Scoped) service;
                scoped.onScopeExit();
            }
        }

        services.clear();
        children.clear();
    }

    public <T> T getService(String service) {
        if (services.containsKey(service)) {
            return (T) services.get(service);
        } else {
            return parentScope.getService(service);
        }
    }

    public void addService(String name, Object service) {
        services.put(name, service);

        if (service instanceof Scoped) {
            ((Scoped) service).onScopeEnter();
        }
    }

    public String getName() {
        return name;
    }

    private static class RootScope extends Scope {

        RootScope() {
            super(null, "root_root");
        }

        @Override
        public <T> T getService(String service) {
            return null;
        }
    }
}
