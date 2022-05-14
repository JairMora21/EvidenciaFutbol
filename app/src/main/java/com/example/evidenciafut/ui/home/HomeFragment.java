package com.example.evidenciafut.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
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
import java.util.concurrent.Executor;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    androidx.biometric.BiometricPrompt biometricPrompt;
    BiometricPrompt.PromptInfo promptInfo;
    Button huella;

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
                HuellaDigital();
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
    public void HuellaDigital(){
        BiometricManager biometricManager = BiometricManager.from(this.getContext());
        switch(biometricManager.canAuthenticate()){
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(getContext(),"No tiene huella digital",Toast.LENGTH_LONG).show();

                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(getContext(),"No funciona",Toast.LENGTH_LONG).show();

            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(getContext(),"No hay huella asignada",Toast.LENGTH_LONG).show();

        }
        Executor executor = ContextCompat.getMainExecutor(this.getContext());
        biometricPrompt = new BiometricPrompt(HomeFragment.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getContext(),"Logeo exitoso",Toast.LENGTH_LONG).show();
                // layout.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("Huella")
                .setDescription("Usa tu dedo para entrar").setDeviceCredentialAllowed(true).build();

        biometricPrompt.authenticate(promptInfo);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}