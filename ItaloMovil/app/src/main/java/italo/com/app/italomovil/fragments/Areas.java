package italo.com.app.italomovil.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import italo.com.app.italomovil.R;
import italo.com.app.italomovil.adapter.AreasAdapter;
import italo.com.app.italomovil.widgets.NonScrollListView;

/**
 * Created by root on 07/02/16.
 */
public class Areas extends Fragment {

    private Toolbar toolbar;
    private ListView listViewAreas;

    public static Areas newInstance() {
        Areas f = new Areas();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = (Toolbar) view.findViewById(R.id.areasToolbar);
        listViewAreas = (ListView) view.findViewById(R.id.listViewAreas);
        AreasAdapter adapter = new AreasAdapter(getActivity());
        listViewAreas.setAdapter(adapter);
        if (toolbar != null) {
            toolbar.setTitle(R.string.areasTitle);
            toolbar.setTitleTextColor(getResources().getColor(R.color.White));
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_48dp);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    getActivity().getSupportFragmentManager().popBackStack();

                }
            });

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_areas, container, false);
    }

}
