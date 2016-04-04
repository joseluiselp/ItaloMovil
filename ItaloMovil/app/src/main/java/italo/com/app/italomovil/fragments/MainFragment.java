package italo.com.app.italomovil.fragments;

import italo.com.app.italomovil.MainActivity;
import italo.com.app.italomovil.R;
import italo.com.app.italomovil.adapter.ViewPagerAdapter;
import italo.com.app.italomovil.service.HttpClientHelper;
import italo.com.app.italomovil.service.database.DatabaseHelper;
import italo.com.app.italomovil.utils.AsyncImage;
import italo.com.app.italomovil.utils.Utils;
import italo.com.app.italomovil.widgets.MaterialRoundedImageView;
import italo.com.app.italomovil.widgets.SlidingTabLayout;
import italo.com.app.italomovil.widgets.dialogs.DialogLogin;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainFragment extends Fragment {

    /**
     * The main/home fragment. This fragment shouldn't be touched
     */

    private static ActionBarDrawerToggle drawerToggle;

    private static DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private static LinearLayout mDrawerLinearLayout;
    private TextView txtOpcion1, txtOpcion2, txtOpcion3, txtOpcion4, txtOpcion5;
    private ViewPager pager;
    private String titles[] = new String[]{"Inicio", "Servicios",
            "Contacto"};
    private Toolbar toolbar;
    private SlidingTabLayout slidingTabLayout;
    private static Menu menu;
    private static Resources res;
    private static Context context;

    private DatePickerDialog dialogPicker;

    private static boolean isLogin;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();

        if(DatabaseHelper.getInstance(context).getUsuario()!=null) {
            isLogin=true;
        }
        else {
            isLogin=false;
        }

        res = getResources();
        mDrawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        //mDrawerList = (ListView) view.findViewById(R.id.left_drawer);
        mDrawerLinearLayout = (LinearLayout) view.findViewById(R.id.left_drawer);


        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle(R.string.app_name);
            toolbar.setTitleTextColor(getResources().getColor(R.color.White));
            toolbar.inflateMenu(R.menu.main);
            menu = toolbar.getMenu();
            if(isLogin)
                menu.getItem(0).setIcon(res.getDrawable(R.drawable.ic_phonelink_erase_white_24dp));
            else
                menu.getItem(0).setIcon(res.getDrawable(R.drawable.ic_recent_actors_white_24dp));
            toolbar.getMenu().getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    if (!isLogin) {
                        DialogLogin dl = new DialogLogin(getContext());

                    } else {
                        afterLogout();
                    }
                    return true;
                }
            });
            // toolbar.setNavigationIcon(R.drawable.ic_ab_drawer);
        }
        pager = (ViewPager) view.findViewById(R.id.viewpager);
        slidingTabLayout = (SlidingTabLayout) view
                .findViewById(R.id.sliding_tabs);
        slidingTabLayout.setDistributeEvenly(true);
        pager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), titles));

        slidingTabLayout.setViewPager(pager);
        slidingTabLayout
                .setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
                    @Override
                    public int getIndicatorColor(int position) {
                        return Color.WHITE;
                    }
                });


        drawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerToggle.syncState();
        mDrawerLinearLayout.bringToFront();
        mDrawerLayout.setDrawerListener(drawerToggle);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        if(isLogin) {
            afterLogin();

        }
        else {
            drawerToggle.setDrawerIndicatorEnabled(false);
            MainActivity.setBottomBarVisible(View.GONE);
        }


        initDrawerOptions();


        /**
         String[] values = new
         String[]{"DEFAULT", "RED", "BLUE"};
         ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
         android.R.layout.simple_list_item_1, android.R.id.text1, values);


         mDrawerList.setAdapter(adapter);
         mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

        @Override public void onItemClick(AdapterView<?> parent, View view,
        int position, long id) {
        switch (position) {
        case 0:
        mDrawerList.setBackgroundColor
        (getResources().getColor(R.color.principal));
        toolbar.setBackgroundColor
        (getResources().getColor(R.color.principal));
        slidingTabLayout.setBackgroundColor(getResources().getColor(R.color.
        principal));
        mDrawerLayout.closeDrawer(GravityCompat.START);
        break;
        case 1:
        mDrawerList.setBackgroundColor(getResources().getColor(R.color.redcolor));
        toolbar.setBackgroundColor(getResources().getColor(R.color.redcolor));
        slidingTabLayout
        .setBackgroundColor(getResources().getColor(R.color.redcolor));
        mDrawerLayout.closeDrawer(GravityCompat.START);

        break;
        case 2:
        mDrawerList.setBackgroundColor(getResources().getColor(
        R.color.Blue));
        toolbar.setBackgroundColor(getResources().getColor(R.color.Blue));
        slidingTabLayout
        .setBackgroundColor(getResources().getColor(R.color.Blue));
        mDrawerLayout.closeDrawer(GravityCompat.START);

        break;
        }

        }
        });
         */

    }

    ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mainpage, container, false);
    }


    public static void afterLogin() {

        TextView txtNombreSocio = (TextView) mDrawerLinearLayout.findViewById(R.id.txtNombreSocio);
        txtNombreSocio.setText(DatabaseHelper.getInstance(context).getPersona().getNombresPersona() + " " +
                DatabaseHelper.getInstance(context).getPersona().getApellidosPersona());
        drawerToggle.setDrawerIndicatorEnabled(true);
        menu.getItem(0).setIcon(res.getDrawable(R.drawable.ic_phonelink_erase_white_24dp));
        MaterialRoundedImageView imgPerfil = (MaterialRoundedImageView)mDrawerLinearLayout.findViewById(R.id.imgPerfil);
        if(!Utils.checkifImageExists(DatabaseHelper.getInstance(context).getPersona().getRutaFotoCarnet())) {
            AsyncImage as = new AsyncImage(DatabaseHelper.getInstance(context).getPersona().getRutaFotoCarnet(), imgPerfil);
            as.execute();
        }
        else {
            imgPerfil.setImageBitmap(Utils.getImageFile(DatabaseHelper.getInstance(context).getPersona().getRutaFotoCarnet()));
        }
        MainActivity.setBottomBarVisible(View.VISIBLE);
        isLogin = true;

    }

    public static void afterLogout() {
        TextView txtNombreSocio = (TextView) mDrawerLinearLayout.findViewById(R.id.txtNombreSocio);
        txtNombreSocio.setText("Nulo Nulidad");
        drawerToggle.setDrawerIndicatorEnabled(false);
        DatabaseHelper.getInstance(context).dropUsuario();
        DatabaseHelper.getInstance(context).dropPersona();
        MainActivity.setBottomBarVisible(View.GONE);
        menu.getItem(0).setIcon(res.getDrawable(R.drawable.ic_recent_actors_white_24dp));
        isLogin = false;
    }

    private void initDrawerOptions() {

        txtOpcion1 = (TextView) mDrawerLinearLayout.findViewById(R.id.txtOpcion1);
        txtOpcion2 = (TextView) mDrawerLinearLayout.findViewById(R.id.txtOpcion2);


        txtOpcion1.setText("Reservar áreas");
        txtOpcion2.setText("Enviar comentario");


        txtOpcion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAreasFragment();
                mDrawerLayout.closeDrawers();
            }
        });
        txtOpcion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEnviarSugerencia();
                mDrawerLayout.closeDrawers();
            }
        });
    }

    private void openAreasFragment() {
        if (getActivity().getSupportFragmentManager().popBackStackImmediate(
                Areas.class.toString(), 0)) {
        } else {
            // Create new fragment and transaction
            Fragment fragmentAreas = new Areas();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                    .beginTransaction();

            // Replace whatever is in the fragment_container view
            // with this fragment,
            // and add the transaction to the back stack if needed
            transaction.replace(R.id.fragment_container, fragmentAreas);
            transaction.addToBackStack(fragmentAreas.getClass().toString());

            // Commit the transaction
            transaction.commit();
        }
    }

    private void openOlvideContrasenaFragment() {
        if (getActivity().getSupportFragmentManager().popBackStackImmediate(
                OlvideContrasena.class.toString(), 0)) {
        } else {
            // Create new fragment and transaction
            Fragment fragmentOlvideContrasena = new OlvideContrasena();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                    .beginTransaction();

            // Replace whatever is in the fragment_container view
            // with this fragment,
            // and add the transaction to the back stack if needed
            transaction.replace(R.id.fragment_container, fragmentOlvideContrasena);
            transaction.addToBackStack(fragmentOlvideContrasena.getClass().toString());

            // Commit the transaction
            transaction.commit();
        }
    }

    private void openEnviarSugerencia() {
        if (getActivity().getSupportFragmentManager().popBackStackImmediate(
                EnviarSugerencia.class.toString(), 0)) {
        } else {
            // Create new fragment and transaction
            Fragment fragmentEnviarSugerencia = new EnviarSugerencia();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                    .beginTransaction();

            // Replace whatever is in the fragment_container view
            // with this fragment,
            // and add the transaction to the back stack if needed
            transaction.replace(R.id.fragment_container, fragmentEnviarSugerencia);
            transaction.addToBackStack(fragmentEnviarSugerencia.getClass().toString());

            // Commit the transaction
            transaction.commit();
        }
    }

    //cambiar clave
    private void openCambiarClave() {
    }

    public void showDatePickerDialog() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        dialogPicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                System.out.println(dayOfMonth + "/" + monthOfYear + "/" + year);
                Toast.makeText(getContext(), dayOfMonth + "/" + monthOfYear + "/" + year, Toast.LENGTH_LONG);
            }
        }, year, month, day);
        dialogPicker.show();
    }

}
