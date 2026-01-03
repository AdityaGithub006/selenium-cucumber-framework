package com.aditya.framework.enums;

public enum EnvType {
    QA, STAGING, PROD;
    public static EnvType from(String s){
        return EnvType.valueOf(s.trim().toUpperCase());
    }
}
