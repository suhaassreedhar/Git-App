package com.suhaas.gitapp.entity;


import com.suhaas.gitapp.ActivityMethod;

public class MethodFactory {
    public static BaseNetMethod getMethod(Class<? extends BaseNetMethod> clazz) {
        if (clazz.getName().equals(ActivityMethod.class.getName())) {
            return ActivityMethod.getInstance();
//        } else if (clazz.getName().equals(RepositoriesMethod.class.getName())) {
//            return RepositoriesMethod.getInstance();
        } else {
            return null;
        }
    }
}
