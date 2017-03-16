package com.anncode.aplicacioncontactos.vista.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anncode.recyclerviewfragments.R;
import com.anncode.aplicacioncontactos.adapter.ContactoAdaptador;
import com.anncode.aplicacioncontactos.model.Contacto;
import com.anncode.aplicacioncontactos.presentador.IRecylerViewFragmentPresenter;
import com.anncode.aplicacioncontactos.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

/**
 * Created by anahisalgado on 20/04/16.
 */
public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView{
    private ArrayList<Contacto> contactos;
    private RecyclerView rvContactos;
    private IRecylerViewFragmentPresenter presenter;
    int data_type;
    String newUser;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        rvContactos = (RecyclerView) v.findViewById(R.id.rvContactos);
        /*try{
            data_type = getArguments().getInt("data_type");
            newUser = getArguments().getString("newUser");
        }catch(Exception e){
            Log.i("ERROR CON BUNDLE", e.toString());
        }*/
        //presenter = new RecyclerViewFragmentPresenter(data_type, this, getContext());
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            Bundle bundle = getArguments();
            data_type = bundle.getInt("data_type");
            newUser = bundle.getString("newUser");
        }catch(Exception e){
            Log.i("ERROR CON BUNDLE", e.toString());
            data_type = 0;
        }
        presenter = new RecyclerViewFragmentPresenter(newUser, data_type, this, getContext());
    }

    /*
    public void inicializarDatos(){
        contactos = new ArrayList<>();
        contactos.add(new Contacto(R.drawable.candy_icon, "Anahi Salgado", "77779999", "anahi@gmail.com", likes));
        contactos.add(new Contacto(R.drawable.yammi_banana_icon, "Pedro Sanchez", "88882222", "pedro@gmail.com", likes));
        contactos.add(new Contacto(R.drawable.shock_rave_bonus_icon, "Mireya Lopez", "33331111", "mireya@gmail.com", likes));
        contactos.add(new Contacto(R.drawable.forest_mushroom_icon, "Juan Lopez", "44442222", "juan@gmail.com", likes));

    }*/



    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvContactos.setLayoutManager(llm);

    }

    @Override
    public void generarGridLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rvContactos.setLayoutManager(gridLayoutManager);
    }

    @Override
    public ContactoAdaptador crearAdaptador(ArrayList<Contacto> contactos) {
        ContactoAdaptador adaptador = new ContactoAdaptador(contactos, getActivity()  );
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(ContactoAdaptador adaptador) {
        rvContactos.setAdapter(adaptador);
    }
}
