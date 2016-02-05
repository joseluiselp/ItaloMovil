package italo.com.app.italomovil.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentEjemplo extends Fragment {


    public static FragmentEjemplo newInstance() {
        FragmentEjemplo f = new FragmentEjemplo();
        Bundle b = new Bundle();
        f.setArguments(b);
        return f;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
/**

        listView = (NonScrollListView) view.findViewById(R.id.listViewComments);
        postAdapter = new PostDetailAdapter(getActivity(), position);
        listView.setAdapter(postAdapter);
        TextView postname = (TextView) view.findViewById(R.id.postTitle);
        postname.setText(post.getPostTitle());
        TextView postplace = (TextView) view.findViewById(R.id.postPlace);
        postplace.setText(post.getPlace());
        TextView postdescrip = (TextView) view.findViewById(R.id.postDescription);
        postdescrip.setText(post.getDescription());

        toolbar = (Toolbar) view.findViewById(R.id.detailPostToolbar);
        if (toolbar != null) {
            toolbar.setTitle(post.getPostTitle());
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_48dp);
        }
        toolbar.setNavigationOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
                MainActivity.setBottomBarVisible(View.VISIBLE);

            }
        });
    */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return null;
       // return inflater.inflate(R.layout.postpage, container, false);
    }

}
