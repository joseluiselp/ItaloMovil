package italo.com.app.italomovil.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ListView;

public class NonScrollListView extends ListView {

	/* Do not touch this class, it is use to handle some important views.
	 * There's nothing to modify here.
	 */
	
    public NonScrollListView(Context context) {
        super(context);
    }
    public NonScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public NonScrollListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int heightMeasureSpec_custom = MeasureSpec.makeMeasureSpec(
                    Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec_custom);
            ViewGroup.LayoutParams params = getLayoutParams();
            params.height = getMeasuredHeight();    
    }
}
