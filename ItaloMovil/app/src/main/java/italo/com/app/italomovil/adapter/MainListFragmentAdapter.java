package italo.com.app.italomovil.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import italo.com.app.italomovil.R;
import italo.com.app.italomovil.widgets.refresh_layout.RefreshLayout;

public class MainListFragmentAdapter extends Fragment {


    private ListView listView;

    private RefreshLayout layout;

    public static MainListFragmentAdapter newInstance() {
        MainListFragmentAdapter f = new MainListFragmentAdapter();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.page, container, false);


        listView = (ListView) rootView.findViewById(R.id.listView);
        //postAdapter = new PostAdapter(getActivity());
        //listView.setAdapter(postAdapter);
        layout = (RefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        layout.setOnRefreshListener(new RefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                layout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    	/* Here is where you need to handle the update of the data of each post.
                    	 * Ask Jose Luis in case of any question
                    	 */

                        //postAdapter.notifyDataSetChanged();
                        layout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        return rootView;
    }
}