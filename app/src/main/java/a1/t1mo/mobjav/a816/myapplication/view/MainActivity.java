package a1.t1mo.mobjav.a816.myapplication.view;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.model.GeneroPelicula;
import a1.t1mo.mobjav.a816.myapplication.model.Pelicula;

/**
 * MoonShot App
 * Proyecto Integrador
 * Curso de Desarrollo Mobile Android
 * Turno Tarde
 */

public class MainActivity extends AppCompatActivity implements PeliculaFragment.Escuchable {
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        FragmentAdapterViewPager fragmentAdapter = new FragmentAdapterViewPager(fragmentManager);

        ViewPager viewPager = (ViewPager)findViewById(R.id.activity_main_pager);

        viewPager.setAdapter(fragmentAdapter);


        NavigationView navigationView = (NavigationView) findViewById(R.id.main_navigationView);
        navigationView.setCheckedItem(R.id.menu_opcion_todas);
        fragmentManager = getSupportFragmentManager();
        PeliculaFragment peliculaFragment = PeliculaFragment.getPeliculaFragment(GeneroPelicula.TODAS.id);
        fragmentManager.beginTransaction().replace(R.id.main_contenedorDeFragment, peliculaFragment).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                onSelectItem(item);
                return true;
            }
        });

        Button button = (Button) findViewById(R.id.main_botonDrawer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_drawerLayout);
                drawer.openDrawer(Gravity.LEFT);
            }
        });
    }


    private void onSelectItem(MenuItem item) {
        if(item.getItemId() == R.id.menu_opcion_todas) {
            PeliculaFragment peliculaFragment = PeliculaFragment.getPeliculaFragment(GeneroPelicula.TODAS.id);
            fragmentManager.beginTransaction().replace(R.id.main_contenedorDeFragment, peliculaFragment).commit();

            Toast.makeText(MainActivity.this, "Se cargaron todas las peliculas", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.menu_opcion_drama) {
            PeliculaFragment peliculaFragment = PeliculaFragment.getPeliculaFragment(GeneroPelicula.DRAMA.id);
            fragmentManager.beginTransaction().replace(R.id.main_contenedorDeFragment, peliculaFragment).commit();

            Toast.makeText(MainActivity.this, "Se cargaron las peliculas de drama", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.menu_opcion_thriller) {
            PeliculaFragment peliculaFragment = PeliculaFragment.getPeliculaFragment(GeneroPelicula.THRILLER.id);
            fragmentManager.beginTransaction().replace(R.id.main_contenedorDeFragment, peliculaFragment).commit();

            Toast.makeText(MainActivity.this, "Se cargaron las peliculas de thriller", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.menu_opcion_accion) {
            PeliculaFragment peliculaFragment = PeliculaFragment.getPeliculaFragment(GeneroPelicula.ACTION.id);
            fragmentManager.beginTransaction().replace(R.id.main_contenedorDeFragment, peliculaFragment).commit();

            Toast.makeText(MainActivity.this, "Se cargaron las peliculas de accion", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClickItem(Pelicula pelicula) {
        DetalleFragment detalleFragment = DetalleFragment.getDetalleFragment(pelicula);

        fragmentManager.beginTransaction().replace(R.id.main_contenedorDeFragment, detalleFragment).addToBackStack("back").commit();
    }
}
