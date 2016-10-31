package com.suhaas.gitapp.interfaces;


import com.suhaas.gitapp.Api;
import com.suhaas.gitapp.entity.AppAuthorizationBean;
import com.suhaas.gitapp.entity.AuthorizationRequest;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import rx.Observable;

public interface OverviewService {
    @PUT("/authorizations/clients/" + Api.CLIENT_ID)
    Observable<AppAuthorizationBean> login(@Header("Authorization") String auth,
                                           @Body AuthorizationRequest request);
}
