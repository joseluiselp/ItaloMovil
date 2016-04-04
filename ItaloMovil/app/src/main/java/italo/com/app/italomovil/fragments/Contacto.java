package italo.com.app.italomovil.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import italo.com.app.italomovil.R;
import italo.com.app.italomovil.service.database.DatabaseHelper;
import italo.com.app.italomovil.service.modelos.MClub;
import italo.com.app.italomovil.widgets.MaterialRoundedImageView;


public class Contacto extends Fragment {

    private TextView txtDireccion, txtCorreo, txtTelef;
    private MaterialRoundedImageView mapa;

    public static Contacto newInstance() {
        Contacto f = new Contacto();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MClub club = DatabaseHelper.getInstance(getContext()).getClub();
        txtDireccion = (TextView) view.findViewById(R.id.txtDireccionClub);
        txtCorreo = (TextView) view.findViewById(R.id.txtCorreoClub);
        txtTelef = (TextView) view.findViewById(R.id.txtTelefonoClub);

        txtDireccion.setText(club.getDireccion());
        txtCorreo.setText(club.getEmail());
        txtTelef.setText(club.getNroTelefono());

        mapa = (MaterialRoundedImageView) view.findViewById(R.id.mapa);
        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.co.ve/maps/place/Club+Italo+Venezolano/@10.0868332,-69.2815806,17z/data=!3m1!4b1!4m2!3m1!1s0x8e875d64f0a1da05:0x76e25e7395b303f1"));
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         return inflater.inflate(R.layout.fragment_contacto, container, false);
    }
}
