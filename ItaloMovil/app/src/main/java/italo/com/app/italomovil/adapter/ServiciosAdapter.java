package italo.com.app.italomovil.adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import italo.com.app.italomovil.R;
import italo.com.app.italomovil.service.HttpClientHelper;
import italo.com.app.italomovil.service.database.DatabaseHelper;
import italo.com.app.italomovil.service.modelos.MClub;
import italo.com.app.italomovil.service.modelos.MServicio;
import italo.com.app.italomovil.utils.AsyncImage;
import italo.com.app.italomovil.utils.Utils;
import italo.com.app.italomovil.widgets.MaterialRoundedImageView;

/**
 * Created by usuario on 2/13/16.
 */
public class ServiciosAdapter extends BaseAdapter {

    private Activity activity;
    private static LayoutInflater inflater = null;
    private ArrayList<MServicio> servicios = new ArrayList<MServicio>();

    public ServiciosAdapter(Activity a) {
        this.activity = a;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        servicios = (ArrayList)DatabaseHelper.getInstance(a.getApplicationContext()).getAllServicio();
    }


    @Override
    public int getCount() {
        return servicios.size();
    }

    @Override
    public Object getItem(int position) {
        return servicios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.adapter_servicio, null);

        initViews(vi, position);
        //imageListeners(vi, position);
        return vi;

    }

    private void initViews(View v, int position){
        TextView txtNombre = (TextView)v.findViewById(R.id.txtNombreServicio);
        TextView txtArea = (TextView)v.findViewById(R.id.txtAreaServicio);
        TextView txtHorario = (TextView)v.findViewById(R.id.txtHorarioServicio);
        TextView txtMensualidad = (TextView)v.findViewById(R.id.txtMensualidad);
        TextView txtMensualidadS = (TextView)v.findViewById(R.id.txtMensualidadSocio);

        txtNombre.setText(servicios.get(position).getNombreServicio());
        txtArea.setText("Localizado en: " +servicios.get(position).getIdArea().getNombreArea());

        txtHorario.setText("Horario de trabajo: "+String.valueOf(servicios.get(position).getIdHorario()));
        txtMensualidad.setText("Mensualidad: "+servicios.get(position).getPrecioMensualidad().toString());
        txtMensualidadS.setText("Mensualidad especial para socio: "+servicios.get(position).getPrecioMensualidadSocio().toString());

        MaterialRoundedImageView image = (MaterialRoundedImageView) v.findViewById(R.id.imgServicio);

        AsyncConce as = new AsyncConce(servicios.get(position).getIdConcesionario(), image);
        as.execute();
    }

    class AsyncConce extends AsyncTask<Void, Integer, String> {

        int id;
        MaterialRoundedImageView image;

        AsyncConce(int id, MaterialRoundedImageView image) {
            this.id = id;
            this.image = image;
        }

        @Override
        protected String doInBackground(Void... params) {
            JSONObject json;
            JSONArray array = null;
            try {
                json = new JSONObject(HttpClientHelper.GET("/servicioconcesionario/"+id, new HashMap<String, String>()));
                array = json.getJSONArray("tipos");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (array != null) {
                for (int i = 0; i < array.length(); i++) {
                    try {
                        JSONObject ob = array.getJSONObject(i);
                        System.out.println(ob.toString());
                        String rutalogo = (ob.getString("rutaFoto"));
                        return rutalogo;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            System.out.println("La ruta del concesionario es: "+s);
            if(!Utils.checkifImageExists(s)) {
                AsyncImage as = new AsyncImage(s, image);
                as.execute();
            }
            else {
                image.setImageBitmap(Utils.getImageFile(s));
            }
        }
    }

}