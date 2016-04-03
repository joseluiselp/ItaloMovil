package italo.com.app.italomovil.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;

import italo.com.app.italomovil.service.HttpClientHelper;
import italo.com.app.italomovil.widgets.MaterialButton;
import italo.com.app.italomovil.widgets.MaterialRoundedImageView;

/**
 * Created by root on 21/03/16.
 */
public class AsyncImage extends AsyncTask<Void, Integer, Bitmap> {

    MaterialRoundedImageView image;
    String url;

    public AsyncImage(String url, MaterialRoundedImageView image) {
        this.url = url;
        this.image = image;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        System.out.println("Preparando url: " + url);

    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        if(url.length()>10) {
            if(!Utils.checkifImageExists(url)) {
                Bitmap image = DownloadImage(url.replace("localhost", HttpClientHelper.getIp()));
                Utils.saveToSdCard(image, Utils.getImageName(url));
                return image;
            }
            else {
                return Utils.getImageFile(url);
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(bitmap==null) {
            System.out.println("Error imagen null");
        }
        else {
            System.out.println("Imagen Cargada de: " + url);
            image.setImageBitmap(bitmap);
        }
    }

    private Bitmap DownloadImage(String URL) {
        Bitmap bitmap = null;
        InputStream in;
        try {
            in = OpenHttpConnection(URL);
            System.out.println("URL: "+URL);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return bitmap;
    }

    private InputStream OpenHttpConnection(String urlString) throws IOException {
        InputStream in = null;

        int response;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");

        try {
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK)
                in = httpConn.getInputStream();

        } catch (Exception ex) {
            throw new IOException("Error connecting");
        }
        return in;
    }
}
