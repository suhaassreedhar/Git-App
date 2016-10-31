package com.suhaas.gitapp;


import com.suhaas.gitapp.entity.BaseNetMethod;
import com.suhaas.gitapp.interfaces.ActivityService;
import com.suhaas.gitapp.utils.RetrofitUtil;

import retrofit2.Retrofit;

public class ActivityMethod extends BaseNetMethod {
    private Retrofit retrofit;

    private ActivityService service;

    private static class Nested {
        static ActivityMethod instance = new ActivityMethod();
    }

    private ActivityMethod() {
        retrofit = RetrofitUtil.initRetrofit(Api.GitHubApi);

        service = retrofit.create(ActivityService.class);
    }

    public static ActivityMethod getInstance() {
        return Nested.instance;
    }
}
