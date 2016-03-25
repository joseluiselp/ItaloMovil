package italo.com.app.italomovil.service;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;


public class HttpClientHelper {
    //Tag para identificar en el Log
    private static final String TAG = "HttpClientHelper";
    private static final String IP = "192.168.1.106";
    private static final String ruta= "http://"+IP+":8080/servicios/api/";

    public static String getIp() {
        return IP;
    }

    public static String GET(String metodo, HashMap<String, String> params){
        HttpURLConnection httpConnection = null;
        BufferedReader bufferedReader = null;
        StringBuilder response = new StringBuilder();

        if (params.size()>0) {
            metodo = metodo + "/?";

            Iterator it = params.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry e = (Map.Entry) it.next();
                System.out.println(e.getKey() + "" + e.getValue());
                metodo += e.getKey() + "=" + e.getValue()+"&";
            }

            metodo = metodo.substring(0,metodo.length()-1);
        }


        try {
            String uri = (ruta+metodo).replace(" ", "(8:::D)");
            URL url = new URL(uri);
            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setDoInput(true);
            httpConnection.setRequestProperty("Content-Type", "application/json");

            httpConnection.connect();
            System.out.println(uri);

            return obtenerRespuesta(httpConnection);

        } catch (Exception e) {
            Log.e(TAG, "GET error: " + Log.getStackTraceString(e));
            return null;

        }finally {
            if(httpConnection != null){
                httpConnection.disconnect();
            }
        }
    }

    public static String POST(String metodo, HashMap<String, String> params){
        HttpURLConnection httpConnection = null;
        BufferedReader bufferedReader = null;
        StringBuilder response = new StringBuilder();

        if (params.size()>0) {
            metodo = metodo + "/?";

            Iterator it = params.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry e = (Map.Entry) it.next();
                System.out.println(e.getKey() + "" + e.getValue());
                metodo += e.getKey() + "=" + e.getValue()+"&";
            }

            metodo = metodo.substring(0,metodo.length()-1);
        }


        try {
            URL url = new URL(ruta+metodo);
            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("POST");
            httpConnection.setDoInput(true);
            httpConnection.setRequestProperty("Content-Type", "application/json");

            httpConnection.connect();
            return obtenerRespuesta(httpConnection);

        } catch (Exception e) {
            Log.e(TAG, "GET error: " + Log.getStackTraceString(e));
            return null;

        }finally {
            if(httpConnection != null){
                httpConnection.disconnect();
            }
        }
    }

    public static String POST(HashMap<String, String> params){

        HttpURLConnection httpConnection = null;
        BufferedReader bufferedReader = null;
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(ruta);
            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setReadTimeout(10000);
            httpConnection.setConnectTimeout(15000);
            httpConnection.setRequestMethod("POST");
            httpConnection.setDoInput(true);
            httpConnection.setDoOutput(true);

            OutputStream os = httpConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            writer.write(getPostDataString(params));
            writer.flush();
            writer.close();
            os.close();

            httpConnection.connect();

            return obtenerRespuesta(httpConnection);

        } catch (Exception e) {
            Log.e(TAG, "Post error: " + Log.getStackTraceString(e));
            return null;

        }finally {
            if(httpConnection != null){
                httpConnection.disconnect();
            }
        }

    }

    private static String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        String ver = result.toString();

        return ver;
    }

    private static String obtenerRespuesta(HttpURLConnection connection) throws IOException {

        BufferedReader bufferedReader = null;
        StringBuilder response = new StringBuilder();

        int responseCode= connection.getResponseCode();

        if (responseCode == HttpsURLConnection.HTTP_CREATED || responseCode == HttpsURLConnection.HTTP_OK) {

            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = bufferedReader.readLine()) != null){
                response.append(line);
            }

            Log.d(TAG, "response code: " + String.valueOf(connection.getResponseCode()));
            Log.d(TAG, "JSON response: " + response.toString());

        }else return null;

        return response.toString(); //or JSONArray(response.toString());
    }

}
