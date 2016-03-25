package italo.com.app.italomovil;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.HashMap;

import italo.com.app.italomovil.service.HttpClientHelper;
import italo.com.app.italomovil.service.database.DatabaseHelper;
import italo.com.app.italomovil.service.modelos.MArea;
import italo.com.app.italomovil.service.modelos.MClub;
import italo.com.app.italomovil.service.modelos.MPublicacion;
import italo.com.app.italomovil.service.modelos.MServicio;
import italo.com.app.italomovil.service.modelos.MTipoSugerencia;


public class Splash extends ActionBarActivity {
    public static final int segundos = 8;
    public static final int milisegundos = segundos * 1000;
    private ProgressBar progreso;
    public static final int delay = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        AsyncLoad as = new AsyncLoad();
        progreso = (ProgressBar) findViewById(R.id.progressBar);
        as.setProgressBar(progreso);
        as.execute();
        //progreso.setMax(maximoProgreso());
        //empezarAnimacion();

    }

    public void empezarAnimacion() {
        new CountDownTimer(milisegundos, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                progreso.setProgress(establecerProgreso(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                Intent nuevoFormulario = new Intent(Splash.this, MainActivity.class);
                startActivity(nuevoFormulario);
                finish();
            }
        }.start();
    }

    public int establecerProgreso(long miliseconds) {
        return (int) (milisegundos - miliseconds) / 1000;
    }

    public int maximoProgreso() {
        return segundos - delay;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class AsyncLoad extends AsyncTask<Void, Integer, Void> {

        ProgressBar bar;

        @Override
        protected Void doInBackground(Void... params) {
            JSONObject json = new JSONObject();
            JSONArray array = new JSONArray();
            try {
                json = new JSONObject(HttpClientHelper.GET("/area", new HashMap<String, String>()));
                array = json.getJSONArray("tipos");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (array != null && DatabaseHelper.getInstance(getApplicationContext()).getAreaCount() != array.length()) {
                DatabaseHelper.getInstance(getApplicationContext()).dropArea();
                for (int i = 0; i < array.length(); i++) {
                    try {
                        JSONObject ob = array.getJSONObject(i);
                        System.out.println(ob.toString());
                        int id = ob.getInt("idArea");
                        int idtipo = ob.getInt("idTipoArea");
                        float precio = (float) (ob.getDouble("precioReserva"));
                        boolean reservable = ob.getBoolean("esReservable");
                        boolean concesionable = ob.getBoolean("esConcesionable");
                        String descrip = (ob.getString("descripcionArea"));
                        String nombre = (ob.getString("nombreArea"));
                        Date fechaina = Date.valueOf(ob.getString("fechaInaguracionArea"));
                        Date fechaCrea = Date.valueOf(ob.getString("fechaCreacionArea"));
                        String lugar = ob.getString("lugar");
                        String rutaImg = ob.getString("rutaFoto");
                        MArea area = new MArea(id, idtipo, nombre, descrip, lugar, fechaina, fechaCrea, precio, reservable, concesionable, rutaImg);
                        DatabaseHelper.getInstance(getApplicationContext()).createArea(area);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            publishProgress(20);
            try {
                json = new JSONObject(HttpClientHelper.GET("/servicio", new HashMap<String, String>()));
                array = json.getJSONArray("tipos");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (array != null && DatabaseHelper.getInstance(getApplicationContext()).getServicioCount() != array.length()) {
                DatabaseHelper.getInstance(getApplicationContext()).dropServicio();
                for (int i = 0; i < array.length(); i++) {
                    try {
                        JSONObject ob = array.getJSONObject(i);
                        System.out.println(ob.toString());
                        int idc = (ob.getInt("idServicio"));
                        String nombre = (ob.getString("nombreServicio"));
                        double precio = (ob.getDouble("precioMensualidad"));
                        double preciosoc = (ob.getDouble("precioMensualidadSocio"));
                        int idcalen = (ob.getInt("idCalendario"));
                        int idhor = (ob.getInt("idHorario"));
                        MArea area = DatabaseHelper.getInstance(getApplicationContext()).getArea(ob.getInt("idArea"));
                        //String rutafoto = ob.getString("rutaFoto");
                        int idcon = ob.getInt("idConcesionario");

                        MServicio servicio = new MServicio(idc,area,"",nombre,precio,preciosoc,idcalen,idhor,idcon);
                        DatabaseHelper.getInstance(getApplicationContext()).createServicio(servicio);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            publishProgress(20);
            try {
                json = new JSONObject(HttpClientHelper.GET("/publicaciones", new HashMap<String, String>()));
                array = json.getJSONArray("tipos");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (array != null && DatabaseHelper.getInstance(getApplicationContext()).getPubliCount() != array.length()) {
                DatabaseHelper.getInstance(getApplicationContext()).dropPubli();
                for (int i = 0; i < array.length(); i++) {
                    try {
                        JSONObject ob = array.getJSONObject(i);
                        System.out.println(ob.toString());
                        int idp = (ob.getInt("idPublicacion"));
                        String titulo = (ob.getString("tituloPublicacion"));
                        String contenido = (ob.getString("contenidoPublicacion"));
                        int tipop = (ob.getInt("idTipoPublicacion"));
                        Date fi = (Date.valueOf(ob.getString("fechaInicio")));
                        Date fin = (Date.valueOf(ob.getString("fechaFin")));
                        String rutaFoto = ob.getString("rutaFoto");
                        MPublicacion publi = new MPublicacion(idp, tipop, titulo, contenido, fi, fin, 5, fi, rutaFoto);
                        DatabaseHelper.getInstance(getApplicationContext()).createPublicacion(publi);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            publishProgress(20);
            try {
                json = new JSONObject(HttpClientHelper.GET("/Club", new HashMap<String, String>()));
                array = json.getJSONArray("tipos");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (array != null) {
                DatabaseHelper.getInstance(getApplicationContext()).dropClub();
                for (int i = 0; i < array.length(); i++) {
                    try {
                        JSONObject ob = array.getJSONObject(i);
                        System.out.println(ob.toString());
                        int idc = ob.getInt("idClub");
                        String nombre = (ob.getString("nombreClub"));
                        String mision = (ob.getString("mision"));
                        String vision = (ob.getString("vision"));
                        String historia = (ob.getString("historia"));
                        String direccion = (ob.getString("direccion"));
                        String email = (ob.getString("email"));
                        String nrotelef = (ob.getString("nroTelefono"));
                        String rutalogo = (ob.getString("rutaLogo"));
                        int maxInvi = (ob.getInt("cantidadMaxInvitados"));
                        MClub club = new MClub(idc, nombre, email, mision, vision, direccion, historia, rutalogo, nrotelef, maxInvi);
                        DatabaseHelper.getInstance(getApplicationContext()).createClub(club);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
            publishProgress(20);
            try {
                json = new JSONObject(HttpClientHelper.GET("/greeting", new HashMap<String, String>()));
                array = json.getJSONArray("tipos");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (array != null) {
                DatabaseHelper.getInstance(getApplicationContext()).dropTipoSugerencia();
                for (int i = 0; i < array.length(); i++) {
                    try {
                        JSONObject ob = array.getJSONObject(i);
                        System.out.println(ob.toString());
                        int idc = ob.getInt("idTipoSugerencia");
                        String nombre = (ob.getString("nombreTipoSugerencia"));
                        String descrip = (ob.getString("descripcionTipoSugerencia"));

                        MTipoSugerencia mts = new MTipoSugerencia(idc, nombre , descrip);
                        DatabaseHelper.getInstance(getApplicationContext()).createTipoSugerencia(mts);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
            publishProgress(20);

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (this.bar != null) {
                bar.setProgress(values[0]);
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            System.out.println("Areas Creadas: " + DatabaseHelper.getInstance(getApplicationContext()).getAreaCount());
            System.out.println("Publicaciones Creadas: " + DatabaseHelper.getInstance(getApplicationContext()).getPubliCount());
            System.out.println("Club Creado");
            Intent nuevoFormulario = new Intent(Splash.this, MainActivity.class);
            startActivity(nuevoFormulario);
            finish();
            super.onPostExecute(aVoid);
        }

        public void setProgressBar(ProgressBar bar) {
            this.bar = bar;
        }
    }
}
