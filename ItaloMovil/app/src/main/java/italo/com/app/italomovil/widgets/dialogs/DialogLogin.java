package italo.com.app.italomovil.widgets.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringBufferInputStream;
import java.sql.Date;
import java.util.HashMap;

import italo.com.app.italomovil.R;
import italo.com.app.italomovil.fragments.MainFragment;
import italo.com.app.italomovil.fragments.OlvideContrasena;
import italo.com.app.italomovil.service.HttpClientHelper;
import italo.com.app.italomovil.service.database.DatabaseHelper;
import italo.com.app.italomovil.service.modelos.MPersona;
import italo.com.app.italomovil.service.modelos.MUsuario;
import italo.com.app.italomovil.utils.Utils;
import italo.com.app.italomovil.widgets.MaterialButton;
import italo.com.app.italomovil.widgets.MaterialRoundedImageView;


public class DialogLogin extends Dialog {

    private EditText email, pass;
    private MaterialButton btnLogin;
    private TextView lblOlvidemiclave;

    public DialogLogin(final Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(true);

        this.setContentView(R.layout.dialog_login);
        email = (EditText) findViewById(R.id.txtCorreoElectronico);
        pass = (EditText) findViewById(R.id.txtContrasena);

        View.OnFocusChangeListener listener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    btnLogin.setButtonColor(getContext().getResources().getColor(R.color.yellowcolor));
                    btnLogin.setText("Ingresar");
                }
            }
        };

        email.setOnFocusChangeListener(listener);
        pass.setOnFocusChangeListener(listener);

        btnLogin = (MaterialButton) findViewById(R.id.btnIngresar);
        btnLogin.setText("Ingresar");
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().length()>0 && pass.getText().length()>0) {
                    AsyncLogin as = new AsyncLogin();
                    as.execute();
                }
                else{
                    Toast.makeText(context, "Porfavor rellena todos los campos", Toast.LENGTH_SHORT);
                }

            }
        });

        getWindow().setLayout((6 * Utils.getDisplayMetrics(context)[0]) / 7,
                (ViewGroup.LayoutParams.WRAP_CONTENT));
        show();

    }



    class AsyncLogin extends AsyncTask<Void, Integer, String> {

        @Override
        protected String doInBackground(Void... params) {
            String login = email.getText().toString();
            String contrase = pass.getText().toString();
            JSONObject json = new JSONObject();
            JSONArray array = new JSONArray();
            try {
                json = new JSONObject(HttpClientHelper.GET("/usuario", new HashMap<String, String>()));
                array = json.getJSONArray("tipos");
            } catch (Exception e) {
                e.printStackTrace();
            }
            for(int i = 0; i < array.length(); i++){
                try {
                    JSONObject ob = array.getJSONObject(i);
                    System.out.println(ob.toString());
                    String l = ob.getString("login");
                    String p = ob.getString("password");
                    if(l.equals(login) && p.equals(contrase)) {
                        String preg = ob.getString("preguntaSecreta");
                        String resp = ob.getString("respuestaSecreta");
                        int idu = ob.getInt("idUsuario");
                        int idp = ob.getInt("idPersona");
                        MUsuario usuario = new MUsuario(idu, idp, l, p, preg, resp);
                        DatabaseHelper.getInstance(getContext()).login(usuario);
                        createPersona(idp);
                        return null;
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            btnLogin.setText("Ingresando...");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(DatabaseHelper.getInstance(getContext()).getPersona()!=null) {
                MainFragment.afterLogin();
                dismiss();
            }
            else{
                btnLogin.setText("Error!!");
                btnLogin.setButtonColor(getContext().getResources().getColor(R.color.Red));
                //dismiss();
            }
        }

        private void createPersona(int id) {
            JSONObject jsonp = new JSONObject();
            JSONArray arrayp = new JSONArray();
            try {
                jsonp = new JSONObject(HttpClientHelper.GET("/persona/"+id, new HashMap<String, String>()));
                arrayp = jsonp.getJSONArray("tipos");
            } catch (Exception e) {
                e.printStackTrace();
            }
            JSONObject obp = null;
            try {
                obp = arrayp.getJSONObject(0);
                System.out.println(obp.toString());
                String nombres = obp.getString("nombresPersona");
                String apellidos = obp.getString("apellidosPersona");
                String email = obp.getString("emailPersona");
                String rutafoto = obp.getString("rutaFotoCarnet");
                String cedula = obp.getString("cedulaPersona");
                Date fechanac = Date.valueOf(obp.getString("fechaNacimiento"));
                MPersona persona = new MPersona(id, "V", Integer.parseInt(cedula), email, nombres, apellidos, "", "", "", fechanac, "", "", rutafoto);
                DatabaseHelper.getInstance(getContext()).createPersona(persona);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }




}
