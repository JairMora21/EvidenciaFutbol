package com.example.evidenciafut;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.evidenciafut.db.DbJugadores;


public class AgregarJugadorFragment extends Fragment {

    EditText txtNombre, txtApellido, txtNumero;
    Button btnRegistrar;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AgregarJugadorFragment() {

    }


    public static AgregarJugadorFragment newInstance(String param1, String param2) {

        AgregarJugadorFragment fragment = new AgregarJugadorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_agregar_jugador, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText txtNombre = view.findViewById(R.id.txtNombreAgregar);
        EditText txtApellido = view.findViewById(R.id.txtApellidoAgregar);
        EditText txtNumero = view.findViewById(R.id.txtNumeroAgregar);

        Button btnRegistrar = view.findViewById(R.id.btnReistrarAgregar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String espacio = txtNombre.getText().toString();
                if(espacio.endsWith(" ")){
                    espacio = txtNombre.getText().toString();
                } else {
                    espacio = txtNombre.getText().toString() + " ";

                }
                DbJugadores dbJugadores = new DbJugadores(AgregarJugadorFragment.this.getContext());

               long id = dbJugadores.agregarJugador(espacio,
                        txtApellido.getText().toString(),
                        Integer.parseInt(String.valueOf(txtNumero.getText())));
               if (id>0){
                   Log.i("Mensaje","Se agrego con exito");
                   txtNombre.setText("");
                   txtApellido.setText("");
                   txtNumero.setText("");
               }else {
                   Log.i("Mensaje","No se pudo");
               }
            }
        });
    }
}