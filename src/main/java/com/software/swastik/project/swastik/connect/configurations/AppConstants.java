package com.software.swastik.project.swastik.connect.configurations;

public class AppConstants
{
    public static final String PAGE_NUMBER = "0";
    public static final String PAGE_SIZE = "20";
    public static  final String SORT_BY ="postCreatedDateTime";
    public static  final String SORT_DIRECTION ="ascending";
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    public static final String SECRET_KEY = "jwtSwastikConnectTokenKey";
    public static final long NORMAL_USER = 1001;
    public static final long ADMIN_USER   = 1002;
    public static final String NORMAL_USER_NAME = "ROLE_USER";
    public static final String ADMIN_USER_NAME   = "ROLE_ADMIN";
    public static final String AUTHORIZATION_HEADER = "Authorization";
}
