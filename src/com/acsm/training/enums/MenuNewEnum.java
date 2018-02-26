package com.acsm.training.enums;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 19:45 2018/1/4
 */
public enum MenuNewEnum {
    BOSS_MENU(10,"BOSS_MENU"), WOD_MENU(11,"WOD_MENU"), CALENDAR_MENU(12,"CALENDAR_MENU"),
    ACTION_MENU(13,"ACTION_MENU"), OPERATION_MENU(16,"OPERATION_MENU"), MEMBER_ANALYSIS_MENU(17,"MEMBER_ANALYSIS_MENU"),
    COACH_ANALYSIS_MENU(18,"COACH_ANALYSIS_MENU"), MEMBER_MANAGE_TOP_MENU(19,"MEMBER_MANAGE_TOP_MENU"), MEMBER_MANAGE_MENU(20,"MEMBER_MANAGE_MENU"),
    TEMPLATE_MENU(21,"TEMPLATE_MENU"),
    APPOINTMENT_TOP_MENU(22,"APPOINTMENT_TOP_MENU"),APPOINTMENT_MENU(23,"APPOINTMENT_MENU"),COURSE_MENU(24,"COURSE_MENU"),
    COURSE_CALENDAR_MENU(25,"COURSE_CALENDAR_MENU"),
    COURSE_COACH_MENU(26,"COURSE_COACH_MENU"),
    MANAGE_MENU(27,"MANAGE_MENU"),
    COURSE_TYPE_MENU(28,"COURSE_TYPE_MENU"),
    MANAGE_TOP_MENU(29,"MANAGE_TOP_MENU"),
    MANAGE_ROLE_MENU(30,"MANAGE_ROLE_MENU"),
    MANAGE_EMPLOYEE_MENU(31,"MANAGE_EMPLOYEE_MENU"),WOD_SETTING_MENU(32,"WOD_SETTING_MENU");

    public final int ID;
    public final String NAME;

    MenuNewEnum(int id, String name) {
        ID = id;
        NAME = name;
    }

    public static MenuNewEnum fromName(String name) {
        for (MenuNewEnum obj : MenuNewEnum.values())
            if (obj.NAME == name)
                return obj;
        return null;
    }

    public static MenuNewEnum fromId(int id) {
        for (MenuNewEnum obj : MenuNewEnum.values())
            if (obj.ID == id)
                return obj;
        return null;
    }
}
