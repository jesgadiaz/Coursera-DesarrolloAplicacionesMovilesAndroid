package com.anncode.aplicacioncontactos.restApi;

import com.anncode.aplicacioncontactos.restApi.model.ContactoResponse;
import com.anncode.aplicacioncontactos.restApi.model.UserIdResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by rodomualdo on 15/03/2017.
 */

public interface EndpointsApi {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<ContactoResponse> getRecentMedia();

    //@GET(ConstantesRestApi.URL_GET_USER_ID)
    //Call<UserIdResponse> getUserId();

    @GET
    Call<UserIdResponse> getUserId(@Url String url);

    @GET
    Call<ContactoResponse> getUserImages(@Url String url);
}
