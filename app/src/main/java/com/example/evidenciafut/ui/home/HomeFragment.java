package com.example.evidenciafut.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.evidenciafut.R;
import com.example.evidenciafut.adaptadores.ListaJugadoresAdapter;
import com.example.evidenciafut.databinding.FragmentHomeBinding;
import com.example.evidenciafut.db.DbJugadores;
import com.example.evidenciafut.entidades.Jugadores;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn1 = view.findViewById(R.id.btnAgregar);
        Button btn2 = view.findViewById(R.id.btnEliminar);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.agregarJugadorFragment);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.eliminarJugadorFragment);
            }
        });

        RecyclerView listaJugadores = view.findViewById(R.id.listaJugadores);
        ArrayList<Jugadores> listaArrayJugadores;

        listaJugadores.setLayoutManager(new LinearLayoutManager(this.getContext()));
        DbJugadores dbJugadores = new DbJugadores(HomeFragment.this.getContext());
        listaArrayJugadores = new ArrayList<>();

        ListaJugadoresAdapter adapter = new ListaJugadoresAdapter(dbJugadores.mostrarJugadores());
        listaJugadores.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}