package italo.com.app.italomovil.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import italo.com.app.italomovil.R;
import italo.com.app.italomovil.widgets.MaterialButton;
import italo.com.app.italomovil.widgets.dialogs.DialogReservarArea;

/**
 * Created by root on 07/02/16.
 */
public class AreasAdapter extends BaseAdapter {

    private Activity activity;
    private static LayoutInflater inflater = null;
    private String [] areas = new String[]{"Piscina", "Caney",
            "Restaurante", "Churuata"};


    public AreasAdapter(Activity a) {
        this.activity = a;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return areas.length;
    }

    @Override
    public Object getItem(int position) {
        return areas[position];
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
        txtNombreArea.setText(areas[position]);
    }

    private void buttonListeners(final View vi, final int position) {
        MaterialButton btnReservar = (MaterialButton) vi.findViewById(R.id.btnReservar);
        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogReservarArea dl = new DialogReservarArea(vi.getContext(), areas[position]);
                dl.show();
            }
        });
    }
}
