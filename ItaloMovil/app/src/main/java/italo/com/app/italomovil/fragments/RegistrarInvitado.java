package italo.com.app.italomovil.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;

import italo.com.app.italomovil.R;


public class RegistrarInvitado extends Fragment {

    private Toolbar toolbar;

    public static RegistrarInvitado newInstance() {
        RegistrarInvitado f = new RegistrarInvitado();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar = (Toolbar) view.findViewById(R.id.registrarInvitadoToolbar);
        if (toolbar != null) {
            toolbar.setTitle(R.string.registrarInvitadoTitle);
            toolbar.setTitleTextColor(getResources().getColor(R.color.White));
            /**
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_48dp);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    getActivity().getSupportFragmentManager().popBackStack();

                }
            });**/
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         return inflater.inflate(R.layout.fragment_registrarinvitado, container, false);
    }
}
