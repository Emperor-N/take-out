package com.phn.mytakeout.utils;

public class ThreadLocalUserContext {
    private static ThreadLocal<Long> tl = new ThreadLocal<>();

    public static void setUser(Long userId){
        tl.set(userId);
    }

    public static Long getUser(){
        return tl.get();
    }

    private static void removeUser(){
        tl.remove();
    }

}
