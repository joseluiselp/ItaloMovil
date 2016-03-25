package italo.com.app.italomovil.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import italo.com.app.italomovil.adapter.MainListFragmentAdapter;
import italo.com.app.italomovil.fragments.Contacto;
import italo.com.app.italomovil.fragments.Prueba;
import italo.com.app.italomovil.fragments.QuienesSomos;
import italo.com.app.italomovil.fragments.Servicios;

public class ViewPagerAdapter extends FragmentPagerAdapter {

	//For each new page this number must be updated
    final int PAGE_COUNT = 3;
    private String titles[] ;

    public ViewPagerAdapter(FragmentManager fm, String[] titles2) {
        super(fm);
        titles=titles2;
    }

    /**
     * all the calls are the same because i dont have another kind of posts menus
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return QuienesSomos.newInstance();
            case 1:
                return Servicios.newInstance();
            case 2:
                return Contacto.newInstance();


        }
        return null;
    }

    @Override
	public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

}