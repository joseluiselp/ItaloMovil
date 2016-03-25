package italo.com.app.italomovil.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.io.Serializable;

import italo.com.app.italomovil.R;
import italo.com.app.italomovil.adapter.ServiciosAdapter;

/**
 * Created by usuario on 2/13/16.
 */
public class Servicios extends Fragment implements Serializable{

    private Toolbar toolbar;
    private ListView listViewServicios;

    public static Servicios newInstance() {
        Servicios f = new Servicios();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //toolbar = (Toolbar) view.findViewById(R.id.serviciosToolbar);
        listViewServicios = (ListView) view.findViewById(R.id.listViewServicios);
        ServiciosAdapter adapter = new ServiciosAdapter(getActivity());
        listViewServicios.setAdapter(adapter);
        /*
        if(toolbar!=null){
            toolbar.setTitle("Servicios del Club");
            toolbar.setTitleTextColor(getResources().getColor(R.color.White));
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_48dp);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    getActivity().getSupportFragmentManager().popBackStack();

                }
            });
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_servicios, container, false);
    }
}
