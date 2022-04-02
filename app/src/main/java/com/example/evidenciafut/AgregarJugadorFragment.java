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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgregarJugadorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgregarJugadorFragment extends Fragment {

    EditText txtNombre, txtApellido, txtNumero;
    Button btnRegistrar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AgregarJugadorFragment() {
        // Required empty public constructor
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
                DbJugadores dbJugadores = new DbJugadores(AgregarJugadorFragment.this.getContext());
               long id = dbJugadores.agregarJugador(txtNombre.getText().toString(),
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