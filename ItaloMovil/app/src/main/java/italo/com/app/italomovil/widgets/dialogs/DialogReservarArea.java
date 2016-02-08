package italo.com.app.italomovil.widgets.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Calendar;

import italo.com.app.italomovil.R;
import italo.com.app.italomovil.widgets.MaterialButton;

/**
 * Created by root on 08/02/16.
 */
public class DialogReservarArea extends Dialog {

    private DatePicker datePicker;
    private NumberPicker numberPicker;
    private MaterialButton btnGuardar, btnCancelar;
    private TextView txtTitulo;
    private int year, month, day, timescalled=1;

    public DialogReservarArea(Context context, String area) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);

        this.setContentView(R.layout.dialog_reservararea);

        txtTitulo = (TextView) findViewById(R.id.txtReservaTitle);
        numberPicker = (NumberPicker) findViewById(R.id.nbrCantidadInvitados);
        btnCancelar = (MaterialButton) findViewById(R.id.btnCancelar);
        btnGuardar = (MaterialButton) findViewById(R.id.btnGuardarReserva);
        datePicker = (DatePicker) findViewById(R.id.datePickerReserva);

        initDataPicker();



        txtTitulo.setText("Reservar " + area);

        numberPicker.setMaxValue(100);
        numberPicker.setMinValue(0);
        numberPicker.setValue(15);

        datePicker.setCalendarViewShown(false);
        datePicker.setSpinnersShown(true);


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aplicar guardar aqui
                dismiss();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    private void initDataPicker() {


        Calendar c = Calendar.getInstance();

        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        datePicker.init(year,month,day,new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                timescalled++;
                if(timescalled%2!=0) {
                    timescalled=1;
                    System.out.println(dayOfMonth + "/" + monthOfYear + "/" + year);
                }
            }
        });
    }
}
