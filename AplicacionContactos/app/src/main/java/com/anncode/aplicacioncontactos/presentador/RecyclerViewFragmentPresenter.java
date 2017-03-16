package com.anncode.aplicacioncontactos.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.anncode.aplicacioncontactos.MainActivity;
import com.anncode.aplicacioncontactos.model.ConstructorContactos;
import com.anncode.aplicacioncontactos.model.Contacto;
import com.anncode.aplicacioncontactos.model.UserId;
import com.anncode.aplicacioncontactos.restApi.ConstantesRestApi;
import com.anncode.aplicacioncontactos.restApi.EndpointsApi;
import com.anncode.aplicacioncontactos.restApi.adapter.RestApiAdapter;
import com.anncode.aplicacioncontactos.restApi.model.ContactoResponse;
import com.anncode.aplicacioncontactos.restApi.model.UserIdResponse;
import com.anncode.aplicacioncontactos.vista.fragment.IRecyclerViewFragmentView;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by anahisalgado on 21/04/16.
 */
public class RecyclerViewFragmentPresenter implements IRecylerViewFragmentPresenter {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorContactos constructorContactos;
    private ArrayList<Contacto> contactos;
    int data_type;
    String newUser;
    UserId userId;

    public  RecyclerViewFragmentPresenter(String newUser, int data_type, IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        this.data_type = data_type;
        this.newUser = newUser;
        if(data_type == 0){
            obtenerMediosRecientes();
        }else{
            obtenerMediosRecientesNuevoUsuario(newUser);
        }

    }

    @Override
    public void obtenerContactosBaseDatos() {
        constructorContactos = new ConstructorContactos(context);
        contactos = constructorContactos.obtenerDatos();
        mostrarContactosRV();
    }

    @Override
    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<ContactoResponse> contactoResponseCall = endpointsApi.getRecentMedia();
        contactoResponseCall.enqueue(new Callback<ContactoResponse>() {
            @Override
            public void onResponse(Call<ContactoResponse> call, Response<ContactoResponse> response) {
                ContactoResponse contactoResponse = response.body();
                contactos = contactoResponse.getContactos();
                mostrarContactosRV();
            }

            @Override
            public void onFailure(Call<ContactoResponse> call, Throwable t) {
                Toast.makeText(context, "Falló la conexión", Toast.LENGTH_SHORT).show();
                Log.i("FALLÖ LA CONEXIÖN", t.toString());
            }
        });
    }

    public void obtenerMediosRecientesNuevoUsuario(String newUser) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonUserId = restApiAdapter.construyeGsonDeserializadorUserId();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonUserId);
        String url = ConstantesRestApi.KEY_GET_USER_ID +
                ConstantesRestApi.KEY_USER_NAME + newUser +
                ConstantesRestApi.KEY_ACCESS_TOKEN_SECOND_POSITION +
                ConstantesRestApi.ACCESS_TOKEN;
        Call<UserIdResponse> userIdResponseCall = endpointsApi.getUserId(url);
        //Call<UserIdResponse> userIdResponseCall = endpointsApi.getUserId();
        userIdResponseCall.enqueue(new Callback<UserIdResponse>() {
            @Override
            public void onResponse(Call<UserIdResponse> call, Response<UserIdResponse> response) {
                UserIdResponse userIdResponse = response.body();
                userId = userIdResponse.getUserId();
                loadImages();
                //mostrarContactosRV();
            }

            @Override
            public void onFailure(Call<UserIdResponse> call, Throwable t) {
                //Toast.makeText(this, "Falló la conexión", Toast.LENGTH_SHORT).show();
                Log.i("FALLÖ LA CONEXIÖN", t.toString());
            }
        });
    }

    public void loadImages(){
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        String url = ConstantesRestApi.KEY_USERS + userId.getId().toString() + "/" +
                ConstantesRestApi.KEY_MEDIA_RECENT +
                ConstantesRestApi.KEY_ACCESS_TOKEN +
                ConstantesRestApi.ACCESS_TOKEN;
        Call<ContactoResponse> contactoResponseCall = endpointsApi.getUserImages(url);
        contactoResponseCall.enqueue(new Callback<ContactoResponse>() {
            @Override
            public void onResponse(Call<ContactoResponse> call, Response<ContactoResponse> response) {
                ContactoResponse contactoResponse = response.body();
                contactos = contactoResponse.getContactos();
                mostrarContactosRV();
            }

            @Override
            public void onFailure(Call<ContactoResponse> call, Throwable t) {
                Toast.makeText(context, "Falló la conexión", Toast.LENGTH_SHORT).show();
                Log.i("FALLÖ LA CONEXIÖN", t.toString());
            }
        });

    }


    @Override
    public void mostrarContactosRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(contactos));
        iRecyclerViewFragmentView.generarGridLayout();
    }
}
