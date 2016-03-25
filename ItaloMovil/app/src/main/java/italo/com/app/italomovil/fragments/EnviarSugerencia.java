package italo.com.app.italomovil.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import italo.com.app.italomovil.R;
import italo.com.app.italomovil.service.HttpClientHelper;
import italo.com.app.italomovil.service.database.DatabaseHelper;
import italo.com.app.italomovil.service.modelos.MTipoSugerencia;
import italo.com.app.italomovil.widgets.MaterialButton;

/**
 * Created by enrique on 09/02/16.
 */
public class EnviarSugerencia extends Fragment {

    private Toolbar toolbar;
    private TextView txtSugerencia;
    private MaterialButton btnEnviarSugerencia;
    private Spinner comboTipoSuge;
    private int tipoSelected;

    public static EnviarSugerencia newInstance() {
        EnviarSugerencia f = new EnviarSugerencia();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar = (Toolbar) view.findViewById(R.id.sugerenciaToolbar);
        if (toolbar != null) {
            toolbar.setTitle("Enviar Sugerencia");
            toolbar.setTitleTextColor(getResources().getColor(R.color.White));
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_48dp);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().getSupportFragmentManager().popBackStack();

                }
            });
        }
        txtSugerencia = (TextView) view.findViewById(R.id.txtSugerencia);

        final ArrayList<MTipoSugerencia> list = (ArrayList<MTipoSugerencia>)DatabaseHelper.getInstance(getContext()).getAllTipoSugerencia();
        String[] nombres = new String[list.size()];
        for(int i = 0; i < list.size(); i++){
            nombres[i] = list.get(i).getNombreTipoSugerencia();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, nombres);

        comboTipoSuge = (Spinner) view.findViewById(R.id.comboTipoSuge);
        comboTipoSuge.setAdapter(adapter);

        tipoSelected = -1;

        comboTipoSuge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipoSelected = position;
                System.out.println("Selecctionaste el tipo de sugerencia " + list.get(position).getNombreTipoSugerencia());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnEnviarSugerencia = (MaterialButton) view.findViewById(R.id.btnEnviarSugerencia);

        btnEnviarSugerencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String suge = txtSugerencia.getText().toString();
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                long diffDias = -1;
                long dias = -1;
                if(DatabaseHelper.getInstance(getContext()).getSugerencia()!=null){
                    try {
                        diffDias = Math.abs(df.parse(DatabaseHelper.getInstance(getContext()).getSugerencia()).getTime() - new Date().getTime());
                        dias = diffDias / (24 * 60 * 60 * 1000);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    dias = 6;
                }
                if(dias <= 5) {
                    System.out.println("Dias: " + dias);
                    Toast.makeText(getContext(), "Solo puedes hacer una sugerencia cada 5 dias", Toast.LENGTH_LONG).show();
                }
                else {
                    if (suge.length() <= 25) {
                        Toast.makeText(getContext(), "La sugerencia tiene que ser de mas de 25 caracteres", Toast.LENGTH_LONG).show();
                    } else {
                        AsyncEnviarSuge as = new AsyncEnviarSuge(list.get(tipoSelected).getIdTipoSugerencia(), suge);
                        as.execute();
                    }
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_enviarsugerencia, container, false);
    }

    class AsyncEnviarSuge extends AsyncTask<Void, Integer, Boolean>{

        private int tipoSuge;
        private String sugerencia;

        AsyncEnviarSuge(int tipoSuge, String sugerencia) {
            this.tipoSuge = tipoSuge;
            this.sugerencia = sugerencia;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            JSONObject ob;
            HashMap<String,String> map = new HashMap<String, String>();
            try {
                map.put("idTipoSugerencia", String.valueOf(tipoSuge));
                map.put("descripcionSugerencia", sugerencia);
                ob = new JSONObject(HttpClientHelper.GET("/crear/sugerencia", map));
                if(ob.getJSONObject("tipos").length()>2)
                    return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean){
                Toast.makeText(getContext(), "Sugerencia enviada con exito", Toast.LENGTH_LONG).show();
                getActivity().getSupportFragmentManager().popBackStack();
                DatabaseHelper.getInstance(getContext()).createSugerencia(new Date());
            }
            else{
                Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
            }
        }
    }
}
