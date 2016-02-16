package italo.com.app.italomovil.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.io.Serializable;

import italo.com.app.italomovil.R;
import italo.com.app.italomovil.adapter.ServiciosAdapter;

/**
 * Created by usuario on 2/13/16.
 */
public class Servicios extends Fragment implements Serializable{

    public static Servicios newInstance() {
        Servicios f = new Servicios();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ExpandableListView elvServicios = (ExpandableListView) view.findViewById(R.id.expandableListViewServicios);
        ServiciosAdapter adapter = new ServiciosAdapter(getActivity());
        elvServicios.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_servicios, container, false);
    }
}
