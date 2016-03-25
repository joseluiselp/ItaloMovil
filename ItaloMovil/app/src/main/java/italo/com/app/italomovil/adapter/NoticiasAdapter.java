package italo.com.app.italomovil.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import italo.com.app.italomovil.R;
import italo.com.app.italomovil.service.HttpClientHelper;
import italo.com.app.italomovil.service.modelos.MPublicacion;
import italo.com.app.italomovil.utils.AsyncImage;
import italo.com.app.italomovil.utils.Utils;
import italo.com.app.italomovil.widgets.MaterialRoundedImageView;

/**
 * Created by root on 17/03/16.
 */
public class NoticiasAdapter extends BaseAdapter {

    private Activity activity;
    private static LayoutInflater inflater = null;
    private ArrayList<MPublicacion> publi = new ArrayList<MPublicacion>();

    public NoticiasAdapter(Activity a, ArrayList<MPublicacion> notic) {
        this.activity = a;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.publi = notic;
    }

    @Override
    public int getCount() {
        return publi.size();
    }

    @Override
    public Object getItem(int position) {
        return publi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.adapter_noticias, null);

        initViews(vi, position);
        //imageListeners(vi, position);
        return vi;
    }

    private void initViews(View vi, int pos) {
        TextView txtTituloNoticia = (TextView) vi.findViewById(R.id.txtNombreNoticia);
        TextView txtNoticia = (TextView) vi.findViewById(R.id.txtDescripNoticia);
        TextView txtFecha = (TextView) vi.findViewById(R.id.txtFecha);
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        txtFecha.setText(df.format(publi.get(pos).getFechaInicio()));
        txtTituloNoticia.setText(publi.get(pos).getTituloPublicacion());
        txtNoticia.setText(publi.get(pos).getContenidoPublicacion());
        MaterialRoundedImageView imgNoticia = (MaterialRoundedImageView) vi.findViewById(R.id.imgNoticia);
        if(!Utils.checkifImageExists(publi.get(pos).getRutaImagen())) {
            AsyncImage as = new AsyncImage(publi.get(pos).getRutaImagen(), imgNoticia);
            as.execute();
        }
        else {
            imgNoticia.setImageBitmap(Utils.getImageFile(publi.get(pos).getRutaImagen()));
        }
    }
}
