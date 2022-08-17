package com.revature.consoleUI;

import com.revature.services.ConsoleService;

public abstract class View {
    protected String viewName;
    protected ConsoleService consoleService;


    public String getViewName() {
        return viewName;
    }

    public abstract void renderView();
}