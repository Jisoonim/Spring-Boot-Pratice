package org.zerock.day2.dto;

/**
 * StringMsg
 */
public enum StringMsg {
    
    SUCCESS("success"), FAIL("fail");

    private String msg;

    StringMsg(String msg){
        this.msg = msg;
    }

    
}