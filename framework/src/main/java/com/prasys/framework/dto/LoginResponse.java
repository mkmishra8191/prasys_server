package com.prasys.framework.dto;



public class LoginResponse {
    public String getAccess() {
        return access;
    }
    private final String access;
    public LoginResponse(String access) {
        this.access = access;
    }

}
