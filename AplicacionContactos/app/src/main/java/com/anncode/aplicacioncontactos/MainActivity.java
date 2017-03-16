package com.anncode.aplicacioncontactos;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.anncode.aplicacioncontactos.adapter.PageAdapter;
import com.anncode.aplicacioncontactos.configuser.ConfigUserActivity;
import com.anncode.aplicacioncontactos.model.Contacto;
import com.anncode.aplicacioncontactos.model.UserId;
import com.anncode.aplicacioncontactos.restApi.ConstantesRestApi;
import com.anncode.aplicacioncontactos.restApi.EndpointsApi;
import com.anncode.aplicacioncontactos.restApi.adapter.RestApiAdapter;
import com.anncode.aplicacioncontactos.restApi.model.ContactoResponse;
import com.anncode.aplicacioncontactos.restApi.model.UserIdResponse;
import com.anncode.aplicacioncontactos.vista.fragment.IRecyclerViewFragmentView;
import com.anncode.aplicacioncontactos.vista.fragment.PerfilFragment;
import com.anncode.aplicacioncontactos.vista.fragment.RecyclerViewFragment;
import com.anncode.recyclerviewfragments.R;
import com.google.gson.Gson;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private static final String KEY_EXTRA_NAME = "name";
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static int REQUEST_CODE = 0;
    private String newUser;
    private UserId userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        Log.e("MainActivity", "onCreate");
        setUpViewPager();


        if (toolbar != null){
            setSupportActionBar(toolbar);
        }

    }

    private ArrayList<Fragment> agregarFragments(){
        Log.e("MainActivity", "agregarFragments");
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());
        return fragments;
    }

    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_contacts);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_action_name);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                //startActivity(new Intent(this, About.class));
                return true;
            case R.id.contact:
                //startActivity(new Intent(this, Help.class));
                return true;
            case R.id.config:
                Intent intent=new Intent(MainActivity.this,ConfigUserActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE)
        {
            newUser = data.getStringExtra("newUser");
            obtenerUserId(newUser);
        }
    }

    public void obtenerUserId(String newUser) {
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
                setUpViewPager();
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
                ArrayList<Contacto> contactos = contactoResponse.getContactos();
                /*
                int index = viewPager.getCurrentItem();
                PageAdapter adapter = ((PageAdapter) viewPager.getAdapter());
                //RecyclerViewFragment recyclerViewFragment = (RecyclerViewFragment) adapter.getRegisteredFragment(index);
                Fragment f = adapter.getItem(viewPager.getCurrentItem());
                f.onCreate();
                */
                //mostrarContactosRV();
            }

            @Override
            public void onFailure(Call<ContactoResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Falló la conexión", Toast.LENGTH_SHORT).show();
                Log.i("FALLÖ LA CONEXIÖN", t.toString());
            }
        });

    }

}
