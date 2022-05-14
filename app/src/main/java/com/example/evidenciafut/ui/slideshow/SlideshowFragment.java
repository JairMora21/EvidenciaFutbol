package com.example.evidenciafut.ui.slideshow;

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
import com.example.evidenciafut.adaptadores.ListaPartidosAdapter;
import com.example.evidenciafut.databinding.FragmentSlideshowBinding;
import com.example.evidenciafut.db.DbPartidos;
import com.example.evidenciafut.entidades.Jugadores;
import com.example.evidenciafut.entidades.Partidos;
import com.example.evidenciafut.ui.home.HomeFragment;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn1 = view.findViewById(R.id.btnRegistrarPartido);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.registrarPartidoFragment);
            }
        });
        ArrayList<Partidos> listaArrayPartidos;

        RecyclerView listaPartidos = view.findViewById(R.id.listaPartidos);
        listaPartidos.setLayoutManager(new LinearLayoutManager(this.getContext()));
        DbPartidos dbPartidos = new DbPartidos(SlideshowFragment.this.getContext());
        listaArrayPartidos = new ArrayList<>();

        ListaPartidosAdapter adapter = new ListaPartidosAdapter(dbPartidos.mostrarPartidos());
        listaPartidos.setAdapter(adapter);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}