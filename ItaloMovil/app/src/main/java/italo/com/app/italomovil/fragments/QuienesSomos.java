package italo.com.app.italomovil.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import italo.com.app.italomovil.R;


public class QuienesSomos extends Fragment {

    public static QuienesSomos newInstance() {
        QuienesSomos f = new QuienesSomos();
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

         return inflater.inflate(R.layout.fragment_quienessomos, container, false);
    }
}
