package italo.com.app.italomovil.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import italo.com.app.italomovil.R;
import italo.com.app.italomovil.adapter.NoticiasAdapter;
import italo.com.app.italomovil.service.HttpClientHelper;
import italo.com.app.italomovil.service.database.DatabaseHelper;
import italo.com.app.italomovil.service.modelos.MPublicacion;


/**
 * Created by enrique on 07/02/16.
 */
public class ConsultarNoticias extends Fragment {

    private Toolbar toolbar;

    public static ConsultarNoticias newInstance() {
        ConsultarNoticias f = new ConsultarNoticias();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar = (Toolbar) view.findViewById(R.id.consultarNoticiasToolbar);
        if (toolbar != null) {
            toolbar.setTitle("Consultar Noticias");
            toolbar.setTitleTextColor(getResources().getColor(R.color.White));
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_48dp);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    getActivity().getSupportFragmentManager().popBackStack();

                }
            });
        }
        ListView lista = (ListView) view.findViewById(R.id.listViewNoticias);
        AsyncNotiPre as = new AsyncNotiPre(lista);
        as.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_consultarnoticias, container, false);
    }

    class AsyncNotiPre extends AsyncTask<Void, Integer, ArrayList<MPublicacion>> {

        ListView lista;
        ArrayList<MPublicacion> publicacions;

        AsyncNotiPre(ListView lista) {
            publicacions = new ArrayList<MPublicacion>();
            this.lista = lista;
        }

        @Override
        protected ArrayList<MPublicacion> doInBackground(Void... params) {
            JSONObject json;
            JSONArray array = new JSONArray();
            try {
                HashMap map = new HashMap<String, String>();
                map.put("idpersona", DatabaseHelper.getInstance(getActivity().getApplicationContext()).getPersona().getIdPersona());
                json = new JSONObject(HttpClientHelper.GET("preferencias/publicaciones", map));
                array = json.getJSONArray("tipos");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (array != null && DatabaseHelper.getInstance(getActivity().getApplicationContext()).getPubliCount() != array.length()) {
                //DatabaseHelper.getInstance(getActivity().getApplicationContext()).dropPubli();
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
                        //DatabaseHelper.getInstance(getActivity().getApplicationContext()).createPublicacion(publi);
                        this.publicacions.add(publi);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<MPublicacion> mPublicacions) {
            super.onPostExecute(mPublicacions);
            NoticiasAdapter noticiasAdapter = new NoticiasAdapter(getActivity(), publicacions);
            this.lista.setAdapter(noticiasAdapter);
        }
    }
}
