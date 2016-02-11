package italo.com.app.italomovil.widgets.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import italo.com.app.italomovil.R;
import italo.com.app.italomovil.fragments.OlvideContrasena;
import italo.com.app.italomovil.utils.Utils;
import italo.com.app.italomovil.widgets.MaterialButton;


public class DialogLogin extends Dialog {

    private EditText email, pass;
    private MaterialButton btnLogin;
    private TextView lblOlvidemiclave;

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

        lblOlvidemiclave = (TextView) findViewById(R.id.lblOlvidemiclave);
        lblOlvidemiclave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                //olvide mi clave aqui
            }
        });

        getWindow().setLayout((6 * Utils.getDisplayMetrics(context)[0]) / 7,
                (ViewGroup.LayoutParams.WRAP_CONTENT));
        show();

    }




}
