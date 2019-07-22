package com.scorer.client.entity;

public class Menu {

    private Integer menuId;
    private Integer menuPid;
    private String menuTitle;
    private String path;
    private String icon;

    public Menu() {
    }

    public Menu(Integer menuId, Integer menuPid, String menuTitle, String path, String icon) {
        this.menuId = menuId;
        this.menuPid = menuPid;
        this.menuTitle = menuTitle;
        this.path = path;
        this.icon = icon;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getMenuPid() {
        return menuPid;
    }

    public void setMenuPid(Integer menuPid) {
        this.menuPid = menuPid;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
