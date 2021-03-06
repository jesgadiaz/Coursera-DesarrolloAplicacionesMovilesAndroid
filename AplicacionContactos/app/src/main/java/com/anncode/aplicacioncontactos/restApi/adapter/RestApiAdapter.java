package com.anncode.aplicacioncontactos.restApi.adapter;

import com.anncode.aplicacioncontactos.restApi.ConstantesRestApi;
import com.anncode.aplicacioncontactos.restApi.EndpointsApi;
import com.anncode.aplicacioncontactos.restApi.deserializador.ContactoDeserializador;
import com.anncode.aplicacioncontactos.restApi.deserializador.UserIdDeserializador;
import com.anncode.aplicacioncontactos.restApi.model.ContactoResponse;
import com.anncode.aplicacioncontactos.restApi.model.UserIdResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rodomualdo on 15/03/2017.
 */

public class RestApiAdapter {

    public EndpointsApi establecerConexionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndpointsApi.class);
    }

    public Gson construyeGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ContactoResponse.class, new ContactoDeserializador());
        return gsonBuilder.create();
    }
    public Gson construyeGsonDeserializadorUserId(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(UserIdResponse.class, new UserIdDeserializador());
        return gsonBuilder.create();
    }
}
