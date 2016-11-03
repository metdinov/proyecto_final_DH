package a1.t1mo.mobjav.a816.myapplication.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;

import a1.t1mo.mobjav.a816.myapplication.model.GeneroPelicula;
import a1.t1mo.mobjav.a816.myapplication.model.GeneroSerie;

/**
 * Created by dh-mob-tt on 31/10/16.
 */

public class AdapterViewPagerFragment extends FragmentStatePagerAdapter {

    private Integer categoryIDFragmentPeliculas;
    private Integer categoryIDFragmentSeries;
    private ArrayList<Fragment> listaDeFragments;
    private Fragment fragmentaA;
    private Fragment fragmentaB;

    public AdapterViewPagerFragment(FragmentManager fm) {
        super(fm);
        listaDeFragments = new ArrayList<>();
        categoryIDFragmentPeliculas = GeneroPelicula.TODAS.id;
        categoryIDFragmentSeries = GeneroSerie.TODAS.id;
        updateFragments();
    }

    public void changeCategory(Integer idPeliculas, Integer idSeries) {
        if(categoryIDFragmentPeliculas != 0) {
            categoryIDFragmentPeliculas = idPeliculas;
        }
        if(categoryIDFragmentSeries != 0) {
            categoryIDFragmentSeries = idSeries;
        }

        updateFragments();
        Log.d("DEBUGGER", "LLEGADO A CHANGE CATEGORY");
    }

    private void updateFragments() {
        Log.d("DEBUGGER", "LLEGADO A UPDATE FRAGMENTS");
        listaDeFragments.clear();
        fragmentaA = PeliculaFragment.obtenerFragment(categoryIDFragmentPeliculas);
        fragmentaB = SerieFragment.obtenerFragment(categoryIDFragmentSeries);
        listaDeFragments.add(fragmentaA);
        listaDeFragments.add(fragmentaB);
        notifyDataSetChanged();
    }



    @Override
    public Fragment getItem(int position) {
        return listaDeFragments.get(position);
    }

    @Override
    public int getCount() {
        return listaDeFragments.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
