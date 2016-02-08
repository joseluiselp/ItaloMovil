package italo.com.app.italomovil.utils;



import android.content.Context;
import android.content.res.Resources;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import java.util.Calendar;

public class Utils {

	/**
	 * Get The size of the screen
	 */
	public static int[] getDisplayMetrics(Context context) {
		DisplayMetrics metrics = context.getResources()
				.getDisplayMetrics();
		int[] m = new int[] { metrics.widthPixels, metrics.heightPixels };
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

}
