package italo.com.app.italomovil.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import italo.com.app.italomovil.R;
import italo.com.app.italomovil.service.HttpClientHelper;
import italo.com.app.italomovil.service.database.DatabaseHelper;
import italo.com.app.italomovil.widgets.MaterialButton;


public class RegistrarInvitado extends Fragment {

    private Toolbar toolbar;
    private CheckBox tieneCedula;
    private EditText txtCedula, txtNombres, txtApellidos;
    private TextView txtDisponibilidad;
    private DatePicker datePicker;
    private Date fechaInv;
    private int timescalled=1;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private MaterialButton btnRegistrarInv;
    private String personaEncontrada;

    public static RegistrarInvitado newInstance() {
        RegistrarInvitado f = new RegistrarInvitado();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar = (Toolbar) view.findViewById(R.id.registrarInvitadoToolbar);
        if (toolbar != null) {
            toolbar.setTitle(R.string.registrarInvitadoTitle);
            toolbar.setTitleTextColor(getResources().getColor(R.color.White));
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_48dp);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    getActivity().getSupportFragmentManager().popBackStack();

                }
            });
        }

        txtCedula = (EditText) view.findViewById(R.id.txtCedulaInvitado);

        tieneCedula = (CheckBox) view.findViewById(R.id.tieneCedula);
        tieneCedula.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    txtCedula.setEnabled(false);
                }
                else
                    txtCedula.setEnabled(true);
            }
        });

        txtCedula.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && txtCedula.getText().length()>0){
                    AsyncBuscarPersona as = new AsyncBuscarPersona(txtCedula.getText().toString());
                    as.execute();
                }
            }
        });

        txtNombres = (EditText) view.findViewById(R.id.txtNombresInvitados);
        txtApellidos = (EditText) view.findViewById(R.id.txtApellidosInvitados);





        txtDisponibilidad = (TextView) view.findViewById(R.id.txtDisponibilidadInv);

        datePicker = (DatePicker) view.findViewById(R.id.datePickerInvitacion);
        datePicker.setCalendarViewShown(false);
        datePicker.setSpinnersShown(true);

        initDataPicker();



        btnRegistrarInv = (MaterialButton) view.findViewById(R.id.btnRegistrarInvitado);
        btnRegistrarInv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cedulaInv = txtCedula.getText().toString();
                String nombres = txtNombres.getText().toString();
                String apellidos = txtApellidos.getText().toString();
                if((cedulaInv.length()<=0 && !tieneCedula.isChecked()) || nombres.length()<=0 || apellidos.length()<=0){
                    Toast.makeText(getContext(), "Rellena todo los campos!!", Toast.LENGTH_SHORT);
                }
                else {
                    AsyncInvi as = new AsyncInvi(nombres, apellidos, cedulaInv,
                            personaEncontrada, String.valueOf(DatabaseHelper.getInstance(getContext()).getPersona().
                            getIdPersona()), dateFormat.format(fechaInv));
                    as.execute();
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         return inflater.inflate(R.layout.fragment_registrarinvitado, container, false);
    }

    private void initDataPicker() {


        Calendar c = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        fechaInv = c.getTime();
        final String idPersonaSocio = String.valueOf(DatabaseHelper.getInstance(getContext()).getPersona().getIdPersona());
        final AsyncDispInv as = new AsyncDispInv(idPersonaSocio, fechaInv);
        as.execute();
        datePicker.init(year,month,day,new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                timescalled++;
                if(timescalled%2!=0) {
                    timescalled=1;
                    try {
                        fechaInv = dateFormat.parse(dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                        System.out.println("Esta es la fecha: " + fechaInv);
                        final AsyncDispInv as = new AsyncDispInv(idPersonaSocio, fechaInv);
                        as.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println(dayOfMonth + "/" + monthOfYear + "/" + year);
                }
            }
        });
    }

    class AsyncDispInv extends AsyncTask<Void, Integer, Integer> {

        private String idSocio;
        private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        private Date fechaIn;

        AsyncDispInv(String idsocio, Date fechain) {
            this.idSocio = idsocio;
            this.fechaIn = fechain;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtDisponibilidad.setText("Cantidad de Invitados disponibles: Calculando...");
        }

        @Override
        protected Integer doInBackground(Void... params) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("idPersonaSocio",idSocio);
            map.put("mes", String.valueOf(fechaIn.getMonth()+1));
            map.put("anno", String.valueOf(fechaIn.getYear()+1900));
            JSONObject ob = new JSONObject();
            int max = DatabaseHelper.getInstance(getContext()).getClub().getCantidadMaxInvitados();
            int disp = -1;
            try {
                ob = new JSONObject(HttpClientHelper.GET("/dispInvi/socio",map));
                disp = max - ob.getJSONArray("tipos").length();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            System.out.println("Cantidad de invitados disponibles: " + disp);

            return disp;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            if(integer <= 0)
                txtDisponibilidad.setText("No puedes invitar mas este mes");
            else{
                txtDisponibilidad.setText("Cantidad de invitados disponibles: " + integer);
            }
        }
    }

    class AsyncInvi extends AsyncTask<Void, Integer, Boolean> {

        private String nombres, apellidos, cedulaInv, idPersona, idSocio, fechaInv;

        AsyncInvi(String nombres, String apellidos, String cedulaInv, String idPersona, String idSocio, String fecha) {
            this.nombres = nombres;
            this.apellidos = apellidos;
            this.cedulaInv = cedulaInv;
            this.idPersona = idPersona;
            this.idSocio = idSocio;
            this.fechaInv = fecha;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("nombresPersona", nombres);
            map.put("apellidosPersona", apellidos);
            map.put("cedulaPersona", cedulaInv);
            if(idPersona == null)
                idPersona = "-1";
            else
                map.put("idPersona", idPersona);
            map.put("idPersonaSocio", idSocio);
            map.put("fechaInvitacion", fechaInv);
            try {
                JSONObject ob = new JSONObject(HttpClientHelper.GET("/crear/invitado", map));
                if(ob.getJSONObject("tipos").length()>2){
                    return true;
                }
                return false;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean){
                Toast.makeText(getContext(), "Invitado Creado", Toast.LENGTH_LONG).show();
                getActivity().getSupportFragmentManager().popBackStack();
            }
        }
    }

    class AsyncBuscarPersona extends AsyncTask<Void, Integer, String> {

        private String nombres, apellidos, cedulaInv;

        AsyncBuscarPersona(String c) {
            this.cedulaInv = c;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getContext(), "Buscando la persona en la Base de datos", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(Void... params) {
            JSONObject ob;
            try {
                ob = new JSONObject(HttpClientHelper.GET("/cedula/persona/"+cedulaInv, new HashMap<String, String>()));
                JSONArray array = ob.getJSONArray("tipos");
                if(array.length()>0){
                    ob = array.getJSONObject(0);
                    nombres = ob.getString("nombresPersona");
                    apellidos = ob.getString("apellidosPersona");
                    cedulaInv = ob.getString("cedulaPersona");
                    return ob.getString("idPersona");
                }
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s!=null){
                txtNombres.setText(nombres);
                txtApellidos.setText(apellidos);
                personaEncontrada = s;
                Toast.makeText(getContext(), "Persona Encontrada", Toast.LENGTH_LONG).show();
            }
            else {
                personaEncontrada = null;
                Toast.makeText(getContext(), "Persona No Encontrada, Sus datos se guardaran!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
