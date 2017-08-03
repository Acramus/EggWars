package com.grizz.menu;

/**
 * Created by Gbtank.
 */
public enum MenuAction {

    OPEN("open"),
    CLOSE("close"),
    UPGRADE("upgrade");

    private String name;

    MenuAction(String name) {
        this.name = name;
    }

    public static MenuAction getActionByName(String name) {
        for (MenuAction action : MenuAction.values()) {
            if (action.name().equalsIgnoreCase(name)) {
                return action;
            }
        }
        return null;
    }

}
