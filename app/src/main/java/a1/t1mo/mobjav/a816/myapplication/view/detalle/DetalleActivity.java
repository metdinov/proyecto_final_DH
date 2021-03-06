package a1.t1mo.mobjav.a816.myapplication.view.detalle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

import a1.t1mo.mobjav.a816.myapplication.R;
import a1.t1mo.mobjav.a816.myapplication.controller.Controller;
import a1.t1mo.mobjav.a816.myapplication.controller.PeliculaController;
import a1.t1mo.mobjav.a816.myapplication.controller.SerieController;
import a1.t1mo.mobjav.a816.myapplication.model.Feature;
import a1.t1mo.mobjav.a816.myapplication.utils.FavChange;
import a1.t1mo.mobjav.a816.myapplication.utils.Listener;
import a1.t1mo.mobjav.a816.myapplication.utils.Tipo;
import a1.t1mo.mobjav.a816.myapplication.view.login.LoginActivity;

public class DetalleActivity extends AppCompatActivity
        implements FavChange, Listener<List<? extends Feature>> {

    private static final String ARGUMENT_TIPO = "Tipo";
    private static final String ARGUMENT_MENU_ID = "Menu ID";
    private static final String ARGUMENT_PAGINA = "Pagina Actual";
    private static final String ARGUMENT_POSICION = "Posicion";
    private ViewPager mViewPager;
    private DetalleAdapter mAdapter;
    private Controller mController;
    private int mPosicion;

    public static Intent getIntent(Context context, Tipo tipo, int menuId, int page, int posicion) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARGUMENT_TIPO, tipo);
        bundle.putInt(ARGUMENT_MENU_ID, menuId);
        bundle.putInt(ARGUMENT_PAGINA, page);
        bundle.putInt(ARGUMENT_POSICION, posicion);
        Intent intent = new Intent(context, DetalleActivity.class);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        Bundle bundle = getIntent().getExtras();
        int menuId = bundle.getInt(ARGUMENT_MENU_ID);
        int page = bundle.getInt(ARGUMENT_PAGINA);
        mPosicion = bundle.getInt(ARGUMENT_POSICION);
        Tipo tipo = (Tipo) bundle.getSerializable(ARGUMENT_TIPO);

        if (tipo == Tipo.PELICULAS) {
            mController = new PeliculaController(this);
            mAdapter = new DetallePeliculaAdapter(getSupportFragmentManager());
        } else {
            mController = new SerieController(this);
            mAdapter = new DetalleSerieAdapter(getSupportFragmentManager());
        }
        mController.setPaginaActual(page);

        mViewPager = (ViewPager) findViewById(R.id.viewpager_detalle);
        mViewPager.setAdapter(mAdapter);

        if (menuId == R.id.menu_favoritos_opcion_series || menuId == R.id.menu_favoritos_opcion_peliculas) {
            mController.getFavoritos(this);
        } else {
            mController.getFeatures(menuId, this);
        }

        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                /**SCALING TRANSFORMATION**/
                final float normalizedposition = Math.abs(Math.abs(position) - 1);
                page.setScaleX(normalizedposition / 2 + 0.5f);
                page.setScaleY(normalizedposition / 2 + 0.5f);
            }
        });
    }

    @Override
    public void onFavChange(int id, boolean isFav) {
        mController.setFavorito(id, isFav);
    }

    @Override
    public void favNotLogued() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void done(List<? extends Feature> param) {
        mAdapter.setFeatures(param);
        mViewPager.setCurrentItem(mPosicion);
    }
}
