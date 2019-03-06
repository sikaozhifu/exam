package com.school.utils;

public class RoleUtil {
    enum Role{
        STUDENT(0,"学生"),
        TEACHER(1,"教师")
        ;
        private Integer value;
        private String desc;

        Role() {
        }

        Role(Integer value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
    public static Integer getRole(String info){
        if (info.contains("教师")||info.contains("教")||info.contains("师")){
            return Role.TEACHER.getValue();
        }
        if (info.contains("学生")||info.contains("学")||info.contains("生")){
            return Role.TEACHER.getValue();
        }
        return null;
    }
}
