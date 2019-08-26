package com.scorer.feign.entity;

public class SchoolMenu {

    private Long menuId;
    private Long menuPid;
    private String menuTitle;
    private String path;
    private String icon;

    public SchoolMenu() {
    }

    public SchoolMenu(Long menuId, Long menuPid, String menuTitle, String path, String icon) {
        this.menuId = menuId;
        this.menuPid = menuPid;
        this.menuTitle = menuTitle;
        this.path = path;
        this.icon = icon;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getMenuPid() {
        return menuPid;
    }

    public void setMenuPid(Long menuPid) {
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
