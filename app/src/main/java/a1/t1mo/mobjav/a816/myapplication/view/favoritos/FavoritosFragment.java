package a1.t1mo.mobjav.a816.myapplication.view.favoritos;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.view.feature.FeatureFragment;
import a1.t1mo.mobjav.a816.myapplication.view.feature.pelicula.PeliculaAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritosFragment extends FeatureFragment {
    RecyclerView recyclerView;
    Escuchable escuchable;

    public FavoritosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        FavoritosAdapter peliculaAdapter = new FavoritosAdapter(bundle.getInt(ARGUMENT_GENERO));
        View view = inflater.inflate(R.layout.fragment_grilla, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_grilla);
        recyclerView.addItemDecoration(new SpacesItemDecoration(4));
        recyclerView.setHasFixedSize(true);
        peliculaAdapter.setListener(escuchable);
        recyclerView.setAdapter(peliculaAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        return view;
    }

    public static FavoritosFragment obtenerFragment(Integer tipo) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARGUMENT_GENERO, tipo);
        FavoritosFragment fragment = new FavoritosFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public CharSequence getTitulo() {
        return "Favoritos";
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        escuchable = (Escuchable) activity;
    }
}