package italo.com.app.italomovil.adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import italo.com.app.italomovil.R;

/**
 * Created by usuario on 2/13/16.
 */
public class ServiciosAdapter extends BaseExpandableListAdapter {

    private Activity activity;
    private List<ModelView> modelViews;
    private View view;
    private View viewChield;
    Integer[] imageIDs=new Integer[3];

    public ServiciosAdapter(Activity a) {
        llenarLista();
        this.activity = a;
    }

    @Override
    public int getGroupCount() {
        return this.modelViews.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return this.modelViews.get(groupPosition).id;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        view = null;
        LayoutInflater inflator = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            imageIDs[0] = R.drawable.futbol;
            imageIDs[1] = R.drawable.natacion;
            imageIDs[2] = R.drawable.salon_de_orquestas_;
            view = inflator.inflate(R.layout.adapter_servicio, parent, false);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.textServicio = (TextView) view.findViewById(R.id.txtNombreServicio);
            viewHolder.arrow_right = (ImageView) view.findViewById(R.id.imgContraido);
            viewHolder.arrow_down = (ImageView) view.findViewById(R.id.imgExpandido);
            viewHolder.imgServicio = (ImageView) view.findViewById(R.id.imgServicio);
            view.setTag(viewHolder);
        } else {
            view = convertView;
        }
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.textServicio.setText(this.modelViews.get(groupPosition).textServicio);
        try {
            viewHolder.imgServicio.setImageResource(imageIDs[groupPosition]);
            //viewHolder.imgServicio.setImageDrawable(getAssetImage(activity,this.modelViews.get(groupPosition).imgServicio));
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        if (isExpanded) {
            viewHolder.arrow_right.setVisibility(View.VISIBLE);
            viewHolder.arrow_down.setVisibility(View.GONE);
        } else {
            viewHolder.arrow_right.setVisibility(View.GONE);
            viewHolder.arrow_down.setVisibility(View.VISIBLE);
        }
        return view;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return this.modelViews.get(groupPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        viewChield = null;
        LayoutInflater inflator = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            viewChield = inflator.inflate(R.layout.adapter_servicio_detalle, parent, false);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.textAreaServicio = (TextView) viewChield.findViewById(R.id.textViewAreaServicio);
            viewHolder.textoHorario = (TextView) viewChield.findViewById(R.id.textViewHorario);
            viewHolder.textoHora = (TextView) viewChield.findViewById(R.id.textViewHora);
            viewHolder.textResponsable = (TextView) viewChield.findViewById(R.id.textViewResponsable);
            viewChield.setTag(viewHolder);
        } else {
            viewChield = convertView;
        }
        ViewHolder viewHolder = (ViewHolder) viewChield.getTag();
        viewHolder.textAreaServicio.setText(this.modelViews.get(groupPosition).textAreaServicio);
        viewHolder.textoHorario.setText(this.modelViews.get(groupPosition).textoHorario);
        viewHolder.textoHora.setText(this.modelViews.get(groupPosition).textoHora);
        viewHolder.textResponsable.setText(this.modelViews.get(groupPosition).textResponsable);
        return viewChield;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    static class ViewHolder {
        protected TextView textServicio;
        protected TextView textAreaServicio;
        protected TextView textoHorario;
        protected TextView textoHora;
        protected TextView textResponsable;
        protected ImageView imgServicio;
        protected ImageView arrow_right;
        protected ImageView arrow_down;
    }

    static class ModelView {
        protected int id;
        protected String textServicio;
        protected String textAreaServicio;
        protected String textoHorario;
        protected String textoHora;
        protected String textResponsable;
        protected String imgServicio;
    }

    private void llenarLista() {
        this.modelViews = new ArrayList<>();

        ModelView modelView1 = new ModelView();
        modelView1.id = 1;
        modelView1.textServicio = "Academia de futbol";
        modelView1.textAreaServicio = "Cancha de usos múltiples";
        modelView1.textoHorario = "Miércoles y Viernes";
        modelView1.textoHora = "8:00 pm a 10:00 pm";
        modelView1.textResponsable = "Francesco Grossale";
        modelView1.imgServicio = "futbol.jpg";
        this.modelViews.add(modelView1);

        ModelView modelView2 = new ModelView();
        modelView2.id = 2;
        modelView2.textServicio = "Academia de natacion";
        modelView2.textAreaServicio = "Piscina";
        modelView2.textoHorario = "Lunes y Viernes";
        modelView2.textoHora = "8:00 am a 6:00 pm";
        modelView2.textResponsable = "Rosycler Lefante";
        modelView2.imgServicio = "natacion.jpg";
        this.modelViews.add(modelView2);

        ModelView modelView3 = new ModelView();
        modelView3.id = 3;
        modelView3.textServicio = "Academia de musica";
        modelView3.textAreaServicio = "Salon de orquestas";
        modelView3.textoHorario = "Martes y jueves";
        modelView3.textoHora = "10:00 am a 5:00 pm";
        modelView3.textResponsable = "Francesco Grossale";
        modelView3.imgServicio = "salon_de_orquestas_.jpg";
        this.modelViews.add(modelView3);
    }

    public static Drawable getAssetImage(Context context, String filenameAndExtension) throws IOException {
        AssetManager assets = context.getResources().getAssets();
        InputStream buffer = new BufferedInputStream((assets.open("drawable/" + filenameAndExtension)));
        Bitmap bitmap = BitmapFactory.decodeStream(buffer);
        return new BitmapDrawable(context.getResources(), bitmap);
    }
}