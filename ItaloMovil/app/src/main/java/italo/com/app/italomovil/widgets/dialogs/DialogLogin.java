package italo.com.app.italomovil.widgets.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import italo.com.app.italomovil.R;
import italo.com.app.italomovil.utils.Utils;
import italo.com.app.italomovil.widgets.MaterialButton;


public class DialogLogin extends Dialog {

    private EditText email, pass;
    private MaterialButton btnLogin;

    public DialogLogin(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(true);

        this.setContentView(R.layout.dialog_login);
        email = (EditText) findViewById(R.id.txtCorreoElectronico);
        pass = (EditText) findViewById(R.id.txtContrasena);

        btnLogin = (MaterialButton) findViewById(R.id.btnIngresar);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //login aqui

            }
        });

        getWindow().setLayout((6 * Utils.getDisplayMetrics(context)[0]) / 7,
                (ViewGroup.LayoutParams.WRAP_CONTENT));
        show();

    }



}
