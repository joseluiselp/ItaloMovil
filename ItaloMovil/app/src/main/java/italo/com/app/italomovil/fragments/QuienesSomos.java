package italo.com.app.italomovil.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import italo.com.app.italomovil.R;
import italo.com.app.italomovil.adapter.NoticiasAdapter;
import italo.com.app.italomovil.service.HttpClientHelper;
import italo.com.app.italomovil.service.database.DatabaseHelper;
import italo.com.app.italomovil.service.modelos.MPublicacion;
import italo.com.app.italomovil.utils.AsyncImage;
import italo.com.app.italomovil.utils.Utils;
import italo.com.app.italomovil.widgets.MaterialRoundedImageView;


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
        ListView listaNoticias = (ListView) view.findViewById(R.id.listViewNoticias);
        ArrayList<MPublicacion> publiPublicas = (ArrayList<MPublicacion>)DatabaseHelper.getInstance(getContext()).getAllPublicaciones();
        NoticiasAdapter noticiasAdapter = new NoticiasAdapter(getActivity(), publiPublicas);
        listaNoticias.setAdapter(noticiasAdapter);

        MaterialRoundedImageView imgInicio = (MaterialRoundedImageView) view.findViewById(R.id.imgInicio);
        if(!Utils.checkifImageExists(DatabaseHelper.getInstance(getContext()).getClub().getRutaLogo())) {
            AsyncImage as = new AsyncImage(DatabaseHelper.getInstance(getContext()).getClub().getRutaLogo(), imgInicio);
            as.execute();
        }
        else {
            imgInicio.setImageBitmap(Utils.getImageFile(DatabaseHelper.getInstance(getContext()).getClub().getRutaLogo()));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         return inflater.inflate(R.layout.fragment_quienessomos, container, false);
    }
}
