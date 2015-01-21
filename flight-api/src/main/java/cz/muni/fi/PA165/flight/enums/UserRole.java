package cz.muni.fi.PA165.flight.enums;

/**
 * User: PC
 * Date: 21. 1. 2015
 * Time: 12:02
 */
public enum UserRole{
    USER,ADMIN;

    public String getRoleString(){
        switch (this){
            case ADMIN:return "ROLE_ADMIN";
            case USER:return "ROLE_USER";
            default:return "ROLE_NONE";
        }
    }

}
