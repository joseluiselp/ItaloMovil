package italo.com.app.italomovil.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import italo.com.app.italomovil.R;
import italo.com.app.italomovil.service.HttpClientHelper;
import italo.com.app.italomovil.service.database.DatabaseHelper;
import italo.com.app.italomovil.service.modelos.MArea;
import italo.com.app.italomovil.utils.AsyncImage;
import italo.com.app.italomovil.utils.Utils;
import italo.com.app.italomovil.widgets.MaterialButton;
import italo.com.app.italomovil.widgets.MaterialRoundedImageView;
import italo.com.app.italomovil.widgets.dialogs.DialogReservarArea;

/**
 * Created by root on 07/02/16.
 */
public class AreasAdapter extends BaseAdapter {

    private Activity activity;
    private static LayoutInflater inflater = null;
    private ArrayList<MArea> areas = new ArrayList<MArea>();


    public AreasAdapter(Activity a) {
        this.activity = a;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        /*
        AsyncT asyncT = new AsyncT();
        asyncT.execute();
        Toast.makeText(a.getApplicationContext(), "Solicitud Recibida", Toast.LENGTH_LONG).show();

        AsyncPost post = new AsyncPost();
        post.execute();
        Toast.makeText(a.getApplicationContext(), "Solicitud Enviada", Toast.LENGTH_LONG).show();
        */
        areas = (ArrayList)DatabaseHelper.getInstance(a.getApplicationContext()).getAllAreasByTipoReservable();
    }

    @Override
    public int getCount() {
        return areas.size();
    }

    @Override
    public Object getItem(int position) {
        return areas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.adapter_area, null);

        initViews(vi, position);
        //imageListeners(vi, position);
        buttonListeners(vi, position);
        return vi;
    }

    private void initViews(View vi, int position) {
        TextView txtNombreArea = (TextView) vi.findViewById(R.id.txtNombreArea);
        txtNombreArea.setText(areas.get(position).getNombreArea());
        MaterialRoundedImageView image = (MaterialRoundedImageView) vi.findViewById(R.id.imgArea);
        if(!Utils.checkifImageExists(areas.get(position).getRutaImagen())) {
            AsyncImage as = new AsyncImage(areas.get(position).getRutaImagen(), image);
            as.execute();
        }
        else {
            image.setImageBitmap(Utils.getImageFile(areas.get(position).getRutaImagen()));
        }

    }

    private void buttonListeners(final View vi, final int position) {
        MaterialButton btnReservar = (MaterialButton) vi.findViewById(R.id.btnReservar);
        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogReservarArea dl = new DialogReservarArea(vi.getContext(), areas.get(position));
                dl.show();
            }
        });
    }



}
