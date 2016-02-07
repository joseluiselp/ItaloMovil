package italo.com.app.italomovil.fragments;

import italo.com.app.italomovil.MainActivity;
import italo.com.app.italomovil.R;
import italo.com.app.italomovil.adapter.ViewPagerAdapter;
import italo.com.app.italomovil.widgets.SlidingTabLayout;
import italo.com.app.italomovil.widgets.dialogs.DialogLogin;

import android.graphics.Color;
import android.os.Bundle;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainFragment extends Fragment {

    /**
     * The main/home fragment. This fragment shouldn't be touched
     */

    private ActionBarDrawerToggle drawerToggle;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private LinearLayout mDrawerLinearLayout;
    private TextView txtOpcion1,txtOpcion2,txtOpcion3,txtOpcion4;
    private ViewPager pager;
    private String titles[] = new String[]{"Quienes somos", "Noticias",
            "Contacto", "Prueba"};
    private Toolbar toolbar;
    private SlidingTabLayout slidingTabLayout;
    private Menu menu;

    private boolean isLogin = false;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDrawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        //mDrawerList = (ListView) view.findViewById(R.id.left_drawer);
        mDrawerLinearLayout = (LinearLayout) view.findViewById(R.id.left_drawer);


        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle(R.string.app_name);
            toolbar.setTitleTextColor(getResources().getColor(R.color.White));
            toolbar.inflateMenu(R.menu.main);
            updateMenu();
            toolbar.getMenu().getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    if (!isLogin) {
                        DialogLogin dl = new DialogLogin(getContext());
                        isLogin = true;
                        updateMenu();
                    } else {
                        isLogin = false;
                        updateMenu();
                    }
                    return true;
                }
            });
            // toolbar.setNavigationIcon(R.drawable.ic_ab_drawer);
        }
        pager = (ViewPager) view.findViewById(R.id.viewpager);
        slidingTabLayout = (SlidingTabLayout) view
                .findViewById(R.id.sliding_tabs);
        pager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), titles));

        slidingTabLayout.setViewPager(pager);
        slidingTabLayout
                .setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
                    @Override
                    public int getIndicatorColor(int position) {
                        return Color.WHITE;
                    }
                });


        drawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout,toolbar , R.string.app_name, R.string.app_name);
        drawerToggle.syncState();
        mDrawerLayout.setDrawerListener(drawerToggle);
        mDrawerLinearLayout.bringToFront();

        initDrawerOptions();




        /**
        String[] values = new
                String[]{"DEFAULT", "RED", "BLUE"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
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

    public void updateMenu() {

        menu = toolbar.getMenu();
        if (isLogin) {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_phonelink_erase_white_24dp));
            MainActivity.setBottomBarVisible(View.VISIBLE);
        } else {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_recent_actors_white_24dp));
            MainActivity.setBottomBarVisible(View.GONE);
        }
    }

    private void initDrawerOptions() {

        txtOpcion1 = (TextView)mDrawerLinearLayout.findViewById(R.id.txtOpcion1);
        txtOpcion2 = (TextView)mDrawerLinearLayout.findViewById(R.id.txtOpcion2);
        txtOpcion3 = (TextView)mDrawerLinearLayout.findViewById(R.id.txtOpcion3);
        txtOpcion4 = (TextView)mDrawerLinearLayout.findViewById(R.id.txtOpcion4);

        txtOpcion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        txtOpcion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        txtOpcion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        txtOpcion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

}