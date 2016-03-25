package italo.com.app.italomovil;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import italo.com.app.italomovil.fragments.MainFragment;
import italo.com.app.italomovil.fragments.RegistrarInvitado;
import italo.com.app.italomovil.fragments.ConsultarNoticias;
import italo.com.app.italomovil.utils.Utils;


public class MainActivity extends FragmentActivity {

    private boolean doubleBackToExitPressedOnce = false;
    private static LinearLayout bottomBar;
    private static FrameLayout fl;
    private static float scale;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // set the scale of the device
        scale = getResources().getDisplayMetrics().density;

        // create the old bottom bar
        // rg = (RadioGroup) findViewById(R.id.radioGroupBar);
        // setBottomBarListeners(rg);

        // create the new bottom bar
        bottomBar = (LinearLayout) findViewById(R.id.linearLayoutBottomBar);
        setBottomBarListeners();

        // create the framelayout to handle the paddingbottom when bottombar is
        // no needed
        fl = (FrameLayout) findViewById(R.id.fragment_container);

        // Check that the activity is using the layout version with the
        // fragment_container FrameLayout
        if (fl != null) {
            // if we are being restored from a previous state, then we dont need
            // to do anything and should
            // return or else we could end up with overlapping fragments.
            if (savedInstanceState != null)
                return;

            // Create an instance of main fragment
            MainFragment mainFrag = new MainFragment();

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction().add(
                    R.id.fragment_container, mainFrag);
            ft.addToBackStack(mainFrag.getClass().toString());
            ft.commit();
        }
    }

    /**
     * Set what happens when a button(Textview in this case) is clicked.
     */
    private void setBottomBarListeners() {

        final TextView home = (TextView) findViewById(R.id.btnHome);
        home.setCompoundDrawablesWithIntrinsicBounds(0,
                R.drawable.ic_home_white_36dp, 0, R.drawable.radio_group_tab);
        final TextView registrarInvitado = (TextView) findViewById(R.id.btnRegistrarInvitado);
        final TextView notification = (TextView) findViewById(R.id.btnNotification);
        final TextView settings = (TextView) findViewById(R.id.btnSettings);
        home.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                home.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.ic_home_white_36dp, 0,
                        R.drawable.radio_group_tab);
                registrarInvitado.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.ic_loupe_white_36dp, 0, 0);
                notification.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.ic_notifications_active_white_36dp, 0, 0);
                settings.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.ic_settings_applications_white_36dp, 0, 0);
                openHomeFragment();
            }
        });

        registrarInvitado.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                home.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.ic_home_white_36dp, 0, 0);
                registrarInvitado.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.ic_loupe_white_36dp, 0,
                        R.drawable.radio_group_tab);
                notification.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.ic_notifications_active_white_36dp, 0, 0);
                settings.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.ic_settings_applications_white_36dp, 0, 0);
                openRegistrarInvitadoFragment();
            }
        });

        notification.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                home.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.ic_home_white_36dp, 0, 0);
                registrarInvitado.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.ic_loupe_white_36dp, 0, 0);
                notification.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.ic_notifications_active_white_36dp, 0,
                        R.drawable.radio_group_tab);
                settings.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.ic_settings_applications_white_36dp, 0, 0);
                //openNotificationFragment();
                openConsultarInvitadosFragment();
            }
        });

        settings.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                home.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.ic_home_white_36dp, 0, 0);
                registrarInvitado.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.ic_loupe_white_36dp, 0, 0);
                notification.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.ic_notifications_active_white_36dp, 0, 0);
                settings.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.ic_settings_applications_white_36dp, 0,
                        R.drawable.radio_group_tab);
                //openSettingFragment();
            }
        });
    }

    /**
     * This method is for the old bottom bar. Do not delete it until
     * the new bar is well tested.
     *
     * @param rg
     */
    private void setBottomBarListeners(RadioGroup rg) {
        rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton rb = (RadioButton) findViewById(checkedId);
                rb.setChecked(true);

                if (checkedId == R.id.btnSettings) {
                    /**
                     if (getSupportFragmentManager().popBackStackImmediate(
                     ConfigFragment.class.toString(), 0)) {
                     } else {
                     // Create new fragment and transaction
                     Fragment configFragment = new ConfigFragment();
                     FragmentTransaction transaction = getSupportFragmentManager()
                     .beginTransaction();

                     // Replace whatever is in the fragment_container view
                     // with this fragment,
                     // and add the transaction to the back stack if needed
                     transaction.replace(R.id.fragment_container,
                     configFragment);
                     RadioButton rd = (RadioButton) findViewById(R.id.btnSettings);
                     rd.setChecked(true);
                     transaction.addToBackStack(configFragment.getClass()
                     .toString());

                     // Commit the transaction
                     transaction.commit();
                     } */
                }
                if (checkedId == R.id.btnHome) {
                    if (getSupportFragmentManager().popBackStackImmediate(
                            MainFragment.class.toString(), 0)) {

                    }
                }
                if (checkedId == R.id.btnRegistrarInvitado) {
                    /**
                     // Create new fragment and transaction
                     Fragment uploadFragment = new UploadFragment();
                     FragmentTransaction transaction = getSupportFragmentManager()
                     .beginTransaction();

                     // Replace whatever is in the fragment_container view
                     // with this fragment,
                     // and add the transaction to the back stack if needed
                     transaction
                     .replace(R.id.fragment_container, uploadFragment);
                     RadioButton rd = (RadioButton) findViewById(R.id.btnNewPost);
                     rd.setChecked(true);
                     transaction.addToBackStack(uploadFragment.getClass()
                     .toString());

                     // Commit the transaction
                     transaction.commit();
                     */
                }
                if (checkedId == R.id.btnNotification) {
                    /**
                     // Create new fragment and transaction
                     Fragment notificationFragment = new NotificationFragment();
                     FragmentTransaction transaction = getSupportFragmentManager()
                     .beginTransaction();

                     // Replace whatever is in the fragment_container view
                     // with this fragment,
                     // and add the transaction to the back stack if needed
                     transaction.replace(R.id.fragment_container,
                     notificationFragment);
                     RadioButton rd = (RadioButton) findViewById(R.id.btnNotification);
                     rd.setChecked(true);

                     transaction.addToBackStack(notificationFragment.getClass()
                     .toString());

                     // Commit the transaction
                     transaction.commit();
                     */
                }

            }
        });
    }


    /**
     * To print out the "double tap back button to exit" dialog
     *
     * @param text
     */
    public void makeToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * Method to handle the double tap back to exit
     */
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_MAIN);
            i.addCategory(Intent.CATEGORY_HOME);
            this.startActivity(i);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        makeToast("Please click BACK again to exit");

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    // Set on and off the visibility of the bottom bar
    public static void setBottomBarVisible(int flag) {
        MainActivity.bottomBar.setVisibility(flag);
        if (flag == View.GONE || flag == View.INVISIBLE) {
            fl.setPadding(0, 0, 0, 0);
        } else
            fl.setPadding(0, 0, 0, Utils.pxToDp(45, scale));
    }

    private void openHomeFragment() {
        if (getSupportFragmentManager().popBackStackImmediate(
                MainFragment.class.toString(), 0)) {

        }
    }

    /**
     * To create or reopen each one of the fragments associated to
     * the buttom bar.
     *
     * openSettingFragment()
     * openNewPostFragment()
     * openNotificationFragment()
     * openHomeFragment()
     */
/**
 private void openSettingFragment() {
 if (getSupportFragmentManager().popBackStackImmediate(
 ConfigFragment.class.toString(), 0)) {
 } else {
 // Create new fragment and transaction
 Fragment configFragment = new ConfigFragment();
 FragmentTransaction transaction = getSupportFragmentManager()
 .beginTransaction();

 // Replace whatever is in the fragment_container view
 // with this fragment,
 // and add the transaction to the back stack if needed
 transaction.replace(R.id.fragment_container, configFragment);
 transaction.addToBackStack(configFragment.getClass().toString());

 // Commit the transaction
 transaction.commit();
 }
 }

 private void openHomeFragment() {
 if (getSupportFragmentManager().popBackStackImmediate(
 MainFragment.class.toString(), 0)) {

 }
 }

 private void openNotificationFragment() {
 if (getSupportFragmentManager().popBackStackImmediate(
 ConfigFragment.class.toString(), 0)) {
 } else {
 // Create new fragment and transaction
 Fragment notificationFragment = new NotificationFragment();
 FragmentTransaction transaction = getSupportFragmentManager()
 .beginTransaction();

 // Replace whatever is in the fragment_container view
 // with this fragment,
 // and add the transaction to the back stack if needed
 transaction.replace(R.id.fragment_container, notificationFragment);
 transaction.addToBackStack(notificationFragment.getClass()
 .toString());

 // Commit the transaction
 transaction.commit();
 }
 }

 private void openNewPostFragment() {
 if (getSupportFragmentManager().popBackStackImmediate(
 ConfigFragment.class.toString(), 0)) {
 } else {
 // Create new fragment and transaction
 Fragment uploadFragment = new UploadFragment();
 FragmentTransaction transaction = getSupportFragmentManager()
 .beginTransaction();

 // Replace whatever is in the fragment_container view
 // with this fragment,
 // and add the transaction to the back stack if needed
 transaction.replace(R.id.fragment_container, uploadFragment);
 transaction.addToBackStack(uploadFragment.getClass().toString());

 // Commit the transaction
 transaction.commit();
 }
 }
 */

private void openRegistrarInvitadoFragment() {
    if (getSupportFragmentManager().popBackStackImmediate(
            RegistrarInvitado.class.toString(), 0)) {
    } else {
        // Create new fragment and transaction
        Fragment registrarInvitado = new RegistrarInvitado();
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();

        // Replace whatever is in the fragment_container view
        // with this fragment,
        // and add the transaction to the back stack if needed
        transaction.replace(R.id.fragment_container, registrarInvitado);
        transaction.addToBackStack(registrarInvitado.getClass().toString());

        // Commit the transaction
        transaction.commit();
    }
}

    private void openConsultarInvitadosFragment() {
        if (getSupportFragmentManager().popBackStackImmediate(
                ConsultarNoticias.class.toString(), 0)) {
        } else {
            // Create new fragment and transaction
            Fragment consultarInvitado = new ConsultarNoticias();
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();

            // Replace whatever is in the fragment_container view
            // with this fragment,
            // and add the transaction to the back stack if needed
            transaction.replace(R.id.fragment_container, consultarInvitado);
            transaction.addToBackStack(consultarInvitado.getClass().toString());

            // Commit the transaction
            transaction.commit();
        }
    }
}
