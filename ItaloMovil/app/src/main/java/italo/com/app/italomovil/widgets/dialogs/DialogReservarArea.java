package italo.com.app.italomovil.widgets.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.NumberPicker;
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
import italo.com.app.italomovil.service.modelos.MArea;
import italo.com.app.italomovil.widgets.MaterialButton;

/**
 * Created by root on 08/02/16.
 */
public class DialogReservarArea extends Dialog {

    private DatePicker datePicker;
    private NumberPicker numberPicker;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private MaterialButton btnGuardar, btnCancelar;
    private TextView txtTitulo, txtDisp;
    private int inviEsti, year, month, day, idSocio, timescalled=1;
    private Date fechaAlq;

    public DialogReservarArea(Context context, final MArea area) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);

        this.setContentView(R.layout.dialog_reservararea);

        AsyncT at = new AsyncT();
        at.execute();

        txtTitulo = (TextView) findViewById(R.id.txtReservaTitle);
        numberPicker = (NumberPicker) findViewById(R.id.nbrCantidadInvitados);
        btnCancelar = (MaterialButton) findViewById(R.id.btnCancelar);
        btnGuardar = (MaterialButton) findViewById(R.id.btnGuardarReserva);
        datePicker = (DatePicker) findViewById(R.id.datePickerReserva);
        txtDisp = (TextView) findViewById(R.id.txtDisponibilidad);
        initDataPicker(area);



        txtTitulo.setText("Reservar " + area.getNombreArea());

        numberPicker.setMaxValue(100);
        numberPicker.setMinValue(0);
        numberPicker.setValue(15);

        datePicker.setCalendarViewShown(false);
        datePicker.setSpinnersShown(true);


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inviEsti = numberPicker.getValue();
                if(txtDisp.getText().equals("DISPONIBLE PARA ESTA FECHA")) {
                    AsyncPost as = new AsyncPost(area, fechaAlq, dateFormat);
                    as.execute();
                }
                else{
                    Toast.makeText(getContext(), "ÁREA NO DISPONIBLE PARA ESTA FECHA", Toast.LENGTH_SHORT).show();
                }


            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    private void initDataPicker(final MArea a) {


        Calendar c = Calendar.getInstance();

        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        fechaAlq = c.getTime();
        final AsyncDisp as = new AsyncDisp(a,fechaAlq,dateFormat);
        as.execute();
        datePicker.init(year,month,day,new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                timescalled++;
                if(timescalled%2!=0) {
                    timescalled=1;
                    try {
                        fechaAlq = dateFormat.parse(dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                        System.out.println("Esta es la fecha: " + fechaAlq);
                        final AsyncDisp as = new AsyncDisp(a,fechaAlq,dateFormat);
                        as.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println(dayOfMonth + "/" + monthOfYear + "/" + year);
                }
            }
        });
    }

    class AsyncDisp extends AsyncTask<Void, Boolean, Boolean> {

        MArea a;
        Date fechaAlq;
        SimpleDateFormat dateFormat;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtDisp.setText("Chequeando Disponibilidad...");
            txtDisp.setTextColor(getContext().getResources().getColor(R.color.yellowcolor));
        }

        AsyncDisp(MArea a, Date fechaAlq, SimpleDateFormat dateFormat) {
            this.a = a;
            this.fechaAlq = fechaAlq;
            this.dateFormat = dateFormat;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("idArea", String.valueOf(a.getIdArea()));
            map.put("fechaCalendario", dateFormat.format(this.fechaAlq));
            try {
                JSONObject ob = new JSONObject(HttpClientHelper.GET("/solicitudalquiler/disponibilidad", map));
                if(ob.getString("tipos").length()>2){
                    return false;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean b) {
            super.onPostExecute(b);
            if(b){
                txtDisp.setText("DISPONIBLE PARA ESTA FECHA");
                txtDisp.setTextColor(getContext().getResources().getColor(R.color.Green));

            }
            else{
                txtDisp.setText("NO DISPONIBLE PARA ESTA FECHA");
                txtDisp.setTextColor(getContext().getResources().getColor(R.color.Red));
            }

        }
    }

    class AsyncT extends AsyncTask<Void, Boolean, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            idSocio = -1;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                JSONObject ob = new JSONObject(HttpClientHelper.GET("/socio/" + DatabaseHelper.getInstance(getContext()).getUsuario().getIdPersona(),
                        new HashMap<String, String>()));
                idSocio = ob.getJSONObject("tipo").getInt("idSocio");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            System.out.println("Done Socio " +idSocio);
        }
    }

    class AsyncPost extends AsyncTask<Void, Boolean, Void> {

        MArea a;
        Date fechaAlq;
        SimpleDateFormat dateFormat;

        AsyncPost(MArea a, Date fechaAlq, SimpleDateFormat dateFormat) {
            this.a = a;
            this.fechaAlq = fechaAlq;
            this.dateFormat = dateFormat;
        }

        @Override
        protected Void doInBackground(Void... params) {
            int idcal = -1;
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("fechaCompletaInicio", this.dateFormat.format(this.fechaAlq));
            map.put("fechaCompletaFin", this.dateFormat.format(this.fechaAlq));
            try {
                JSONObject ob = new JSONObject(HttpClientHelper.GET("/crear/calendario", map));
                idcal = ob.getJSONObject("tipos").getInt("idCalendario");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            map.clear();

            map.put("idArea", String.valueOf(a.getIdArea()));
            map.put("idSocio", String.valueOf(idSocio));
            map.put("idCalendario", String.valueOf(idcal));
            map.put("fechaSolicitudAlquiler", this.dateFormat.format(this.fechaAlq));
            map.put("fechaAlquiler", this.dateFormat.format(this.fechaAlq));
            map.put("invitadosEstimados", String.valueOf(inviEsti));
            try {
                JSONObject ob = new JSONObject(HttpClientHelper.GET("/crear/solicitudalquiler", map));
                System.out.println(ob);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getContext(), "Solicitud Creada Exitosamente, Tiene 1 dia de validez", Toast.LENGTH_LONG).show();
            dismiss();
            System.out.println("Done");
        }
    }
}
