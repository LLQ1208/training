package com.acsm.training.enums;

/**
 * Created by xiaobing.liu on 2017/8/15.
 */
public enum MenuEnum {
    member(1,"member"), courseOrder(2,"courseOrder"), courseContent(3,"courseContent"),
    notification(4,"notification"), role(5,"role"), coach(6,"coach"), supporter(7,"supporter"),
    courseType(8,"courseType"), coursePlan(9,"coursePlan"), config(10,"config");

    public final int ID;
    public final String NAME;

    MenuEnum(int id, String name) {
        ID = id;
        NAME = name;
    }

    public static MenuEnum fromName(String name) {
        for (MenuEnum obj : MenuEnum.values())
            if (obj.NAME == name)
                return obj;
        return null;
    }

    public static MenuEnum fromId(int id) {
        for (MenuEnum obj : MenuEnum.values())
            if (obj.ID == id)
                return obj;
        return null;
    }
}
