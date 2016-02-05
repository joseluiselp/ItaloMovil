package italo.com.app.italomovil.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import italo.com.app.italomovil.R;


public class Contacto extends Fragment {


    public static Contacto newInstance() {
        Contacto f = new Contacto();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         return inflater.inflate(R.layout.fragment_contacto, container, false);
    }
}
