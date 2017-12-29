package com.huak.auth.model.vo;

/**
 * Created by MR-BIN on 2017/5/10.
 */
public class MenuVo {

    private String id;
    private String menuName;
    private String pMenuId;
    private Byte menuType;
    private Byte type;
    private String open;

    public MenuVo(String id, String menuName, String pMenuId, Byte menuType, Byte type,String open) {
        this.id = id;
        this.menuName = menuName;
        this.pMenuId = pMenuId;
        this.menuType = menuType;
        this.type = type;
        this.open = open ;
    }

    public MenuVo() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }


    public String getpMenuId() {
        return pMenuId;
    }

    public void setpMenuId(String pMenuId) {
        this.pMenuId = pMenuId == null ? null : pMenuId.trim();
    }

    public Byte getMenuType() {
        return menuType;
    }

    public void setMenuType(Byte menuType) {
        this.menuType = menuType;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getOpen(){
        return this.open;
    }

    public void setOpen(String open){
        this.open = open ;
    }


}