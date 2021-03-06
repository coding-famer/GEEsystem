package com.vcampus.net;

import com.vcampus.entity.*;

/**
 * 会话控制信息
 *
 */

public class Session {
    private Student student = null;
    private Admin admin = null;
    private UserType userType = null;
    private IOnCampus onCampus = null;

    public Student getStudent() {
        return student;
    }

    public Admin getAdmin() {
        return admin;
    }

    public UserType getUserType() {
        return userType;
    }

    public IOnCampus getPersonBelongsToSchool(){
        switch(userType){
            case STUDENT:
                return student;
            default:
                return null;
        }
    }

    public Session() {}

    public Session(Student student) {
        this.student = student;
        this.userType = UserType.STUDENT;
    }


    public Session(Admin admin) {
        this.admin = admin;
        this.userType = UserType.ADMIN;
    }

    @Override
    public String toString() {
        return "Session [student=" + student + ",  admin=" + admin + ", userType=" + userType
                + "]";
    }

}
