package italo.com.app.italomovil.utils;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Utils {

    /**
     * Get The size of the screen
     */
    public static int[] getDisplayMetrics(Context context) {
        DisplayMetrics metrics = context.getResources()
                .getDisplayMetrics();
        int[] m = new int[]{metrics.widthPixels, metrics.heightPixels};
        return m;
    }

    /**
     * Convert Pixel to Dp
     */
    public static int pxToDp(int px, float scale) {

        return (int) (px * scale + 0.5f);
    }

    /**
     * Convert Dp to Pixel
     */
    public static int dpToPx(float dp, Resources resources) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                resources.getDisplayMetrics());
        return (int) px;
    }

    public static String getImageName(String path) {
        String aux = path.replace("http://localhost:8080/ClubItalo/uploadedImages/", "");
        StringTokenizer token = new StringTokenizer(aux, ".");
        aux = token.nextToken();
        System.out.println("nombre de la imagen: " + aux);
        return aux;
    }

    public static String saveToSdCard(Bitmap bitmap, String filename) {

        String stored = null;

        File sdcard = Environment.getExternalStorageDirectory();

        File folder = new File(sdcard.getAbsoluteFile(), "italo_images");//the dot makes this directory hidden to the user
        folder.mkdir();
        File file = new File(folder.getAbsoluteFile(), filename + ".jpg");
        if (file.exists())
            return stored;

        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            stored = "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stored;
    }

    public static File getImage(String imagename) {

        File mediaImage = null;
        try {
            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root);
            if (!myDir.exists())
                return null;

            mediaImage = new File(myDir.getPath() + "/italo_images/" + imagename);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mediaImage;
    }

    public static boolean checkifImageExists(String imagename) {
        Bitmap b = null;
        imagename = getImageName(imagename);
        File file = getImage("/" + imagename + ".jpg");
        String path = file.getAbsolutePath();

        if (path != null) {
            System.out.println(path);
            b = BitmapFactory.decodeFile(path);
        }
        if (b == null || b.equals("")) {
            return false;
        }
        return true;
    }

    public static Bitmap getImageFile(String imagename) {
        Bitmap b = null;
        imagename = getImageName(imagename);
        File file = getImage("/" + imagename + ".jpg");
        String path = file.getAbsolutePath();

        if (path != null)
            b = BitmapFactory.decodeFile(path);

        return b;
    }
}


