package italo.com.app.italomovil.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import italo.com.app.italomovil.R;
import italo.com.app.italomovil.widgets.MaterialButton;


public class Prueba extends Fragment {

    private MaterialButton btnDialog, btnFragment;

    public static Prueba newInstance() {
        Prueba f = new Prueba();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnDialog = (MaterialButton) view.findViewById(R.id.prueba_dialog);
        btnFragment = (MaterialButton) view.findViewById(R.id.prueba_fragment);

        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**Para los dialogs se hace de la siguiente manera
                    Primero se crea el objeto dialog, ejemplo:
                    DialogLogin dl = new DialogLogin(getContext());

                    Luego se llama al metodo .show() para mostrarlo
                    dl.show();

                    Cabe destacar que para que esto funcione tiene que estar bien
                    programado el dialog en su clase (Ver Ejemplo DialogLogin)

                 */
             }
        });

        btnFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**Para los fragments se hace de la siguiente manera
                 Primero se comprueba que este fragment no se haya abierto antes, ejemplo:

                 if (getSupportFragmentManager().popBackStackImmediate(
                    RegistrarInvitado.class.toString(), 0)) {
                    }
                 else {

                 Si el fragment no esta en la pila se crea uno nuevo, asi:

                 Fragment registrarInvitado = new RegistrarInvitado();
                 FragmentTransaction transaction = getSupportFragmentManager()
                 .beginTransaction();

                 Luego de creado se procede a reemplazar el fragment_container con el nuevo
                 Fragment

                 transaction.replace(R.id.fragment_container, registrarInvitado);

                 Y se añade a la pila

                 transaction.addToBackStack(registrarInvitado.getClass().toString());

                 Se comienza la inicializacion del mismo, y listo.

                 transaction.commit();
                 }

                 Cabe destacar que para que esto funcione tiene que estar bien
                 programado el fragment en su clase (Ver Ejemplo RegistrarInvitado)

                 */
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         return inflater.inflate(R.layout.prueba, container, false);
    }

}
