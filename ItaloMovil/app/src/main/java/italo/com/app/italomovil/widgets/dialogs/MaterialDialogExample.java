package italo.com.app.italomovil.widgets.dialogs;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import italo.com.app.italomovil.R;
import italo.com.app.italomovil.utils.Utils;
import italo.com.app.italomovil.widgets.MaterialButton;

public class MaterialDialogExample extends Dialog {

	/**
	 * This class is fully editable. The dialogs are explained below. 
	 */
	private ImageView header;
	private TextView text;
	private MaterialButton button;
	
	//in case of 2 buttons dialog
	private MaterialButton button2;
	
	//in case of Loading dialog
	private ImageView loading;

	/**
	 * Method to create a single dialog
	 * 
	 * @param context -> the context of the app
	 * @param i -> 1 for single one button dialog, 2 for loading screen and 3 for 2 button dialog
	 * @param message -> the message(in case of 2 the text is alway, Loading Please Wait...)
	 * @param type -> in case of i=1, if 1 green and accept button, 2 red and error button
	 */
	public MaterialDialogExample(Context context, int i, String message, int type) {
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setCancelable(false);
		switch (i) {
		case 1: {
			this.setContentView(R.layout.dialogonebutton);
			header = (ImageView) findViewById(R.id.dialogOneButtonHeader);
			text = (TextView) findViewById(R.id.dialogOneButtonText);
			button = (MaterialButton) findViewById(R.id.uniqueButton);
			switch (type) {
			case 1: {
				setAsSuccessMessage(message);
				break;
			}
			default: {
				setAsErrorMessage(message);
				break;
			}
			}
			setButtonListener();
			break;
		}
		
		case 2: {
			this.setContentView(R.layout.dialogpleasewait);
			header = (ImageView) findViewById(R.id.dialogPleaseWaitHeader);
			text = (TextView) findViewById(R.id.dialogPleaseWaitText);
			loading = (ImageView) findViewById(R.id.dialogPleaseWaitImage);
			setCancelable(true);
			initLoadingDialog();
			break;
		}

		default: {
			this.setContentView(R.layout.dialogtwobuttons);
			header = (ImageView) findViewById(R.id.dialogTwoButtonHeader);
			text = (TextView) findViewById(R.id.dialogTwoButtonText);
			button = (MaterialButton) findViewById(R.id.acceptButton);
			button2 = (MaterialButton) findViewById(R.id.declineButton);
			initTwoButtonDialog(message);
			setButtonListener();
			break;
		}

		}
		
		getWindow().setLayout((6 * Utils.getDisplayMetrics(context)[0]) / 7,
				(LayoutParams.WRAP_CONTENT));

	}
	
	private void initLoadingDialog() {
		header.setBackgroundResource(R.color.principal);
		header.setImageResource(R.drawable.ic_warning_white_48dp);
		text.setText("Please Wait...");
		AnimationDrawable ad = (AnimationDrawable)loading.getBackground();
		ad.start();
	}
	
    private void initTwoButtonDialog(String message) {
    	header.setBackgroundResource(R.color.material_grey_600);
		header.setImageResource(R.drawable.ic_warning_white_48dp);
		button.setButtonColor(getContext().getResources().getColor(R.color.yellowcolor));
		button2.setButtonColor(getContext().getResources().getColor(R.color.redcolor));
		text.setText(message);
    }

	private void setAsErrorMessage(String message) {
		header.setBackgroundResource(R.color.redcolor);
		header.setImageResource(R.drawable.ic_warning_white_48dp);
		button.setButtonColor(getContext().getResources().getColor(R.color.redcolor));
		//button.setImageResource(R.drawable.ic_highlight_off_white_48dp);
		text.setText(message);
	}

	private void setAsSuccessMessage(String message) {
		header.setBackgroundResource(R.color.principal);
		header.setImageResource(R.drawable.ic_warning_white_48dp);
		button.setButtonColor(getContext().getResources().getColor(
				R.color.yellowcolor));
		//button.setImageResource(R.drawable.ic_done_white_48dp);
		text.setText(message);
	}

	private void setButtonListener() {
		if (button2 == null) {
			button.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					dismiss();

				}
			});
		} else {
			button.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					dismiss();

				}
			});
			button2.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					dismiss();

				}
			});
		}

	}

}
