package com.project.air.firemanpro;


import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kizo.core_module.DataLoadedListener;
import com.kizo.core_module.DataLoader;
import com.kizo.web_services.AirWebServiceHandler;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.project.air.firemanpro.loaders.WsDataLoader;
import com.project.test.database.Entities.House;
import com.project.test.database.Entities.Photos;
import com.project.test.database.Entities.Settings;
import com.project.test.database.controllers.PhotosController;
import com.project.test.database.helper.MockData;
import com.project.test.database.imageSaver.ImageSaver;
import com.project.test.database.imageSaver.SaveResourceImage;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends AppCompatPreferenceActivity {
    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */
    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

            if (preference instanceof ListPreference) {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list.
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                // Set the summary to reflect the new value.
                preference.setSummary(
                        index >= 0
                                ? listPreference.getEntries()[index]
                                : null);

            } else if (preference instanceof RingtonePreference) {
                // For ringtone preferences, look up the correct display value
                // using RingtoneManager.
                if (TextUtils.isEmpty(stringValue)) {
                    // Empty values correspond to 'silent' (no ringtone).
                    preference.setSummary(R.string.pref_ringtone_silent);

                } else {
                    Ringtone ringtone = RingtoneManager.getRingtone(
                            preference.getContext(), Uri.parse(stringValue));

                    if (ringtone == null) {
                        // Clear the summary if there was a lookup error.
                        preference.setSummary(null);
                    } else {
                        // Set the summary to reflect the new ringtone display
                        // name.
                        String name = ringtone.getTitle(preference.getContext());
                        preference.setSummary(name);
                    }
                }

            } else {
                // For all other preferences, set the summary to the value's
                // simple string representation.
                preference.setSummary(stringValue);
            }
            return true;
        }
    };

    /**
     * Helper method to determine if the device has an extra-large screen. For
     * example, 10" tablets are extra-large.
     */
    private static boolean isXLargeTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    /**
     * Binds a preferencfe's summary to its value. More specifically, when the
     * preference's value is changed, its summary (line of text below the
     * preference title) is updated to reflect the value. The summary is also
     * immediately updated upon calling this method. The exact display format is
     * dependent on the type of preference.
     *
     * @see #sBindPreferenceSummaryToValueListener
     */
    private static void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        // Trigger the listener immediately with the preference's
        // current value.
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setupActionBar();
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {

        getLayoutInflater().inflate(R.layout.tolbar_settings, (ViewGroup) findViewById(android.R.id.content));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int horizontalMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
        int verticalMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
        int topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (int) getResources().getDimension(R.dimen.activity_vertical_margin) + 30, getResources().getDisplayMetrics());
        ListView listView = getListView();

        listView.setPadding(horizontalMargin, topMargin, horizontalMargin, verticalMargin);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);


        }
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            if (!super.onMenuItemSelected(featureId, item)) {
                NavUtils.navigateUpFromSameTask(this);
            }
            return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onIsMultiPane() {
        return isXLargeTablet(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_headers, target);
    }

    /**
     * This method stops fragment injection in malicious applications.
     * Make sure to deny any unknown fragments here.
     */
    protected boolean isValidFragment(String fragmentName) {
        return PreferenceFragment.class.getName().equals(fragmentName)
                || GeneralPreferenceFragment.class.getName().equals(fragmentName)
                || DataSyncPreferenceFragment.class.getName().equals(fragmentName);
    }

    /**
     * Ovaj fragment prikazuje glavne posatvke aplikacije (Email na koji se šalje report, Naziv vatrogasne postrojbe..)
     * Omogućujeizmjenu podataka.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class GeneralPreferenceFragment extends PreferenceFragment {

        EditTextPreference emailToSendPref;
        EditTextPreference patrolNamePref;


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_general);
            setHasOptionsMenu(true);


            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
            bindPreferenceSummaryToValue(findPreference("emailToSend"));
            bindPreferenceSummaryToValue(findPreference("patrolName"));

            patrolNamePref = (EditTextPreference) findPreference("patrolName");
            patrolNamePref.setText(Settings.getSettings().getPatrolName());


            patrolNamePref.setSummary(Settings.getSettings().getPatrolName());

            //nakon promjene vrijednost, ista se pohranjeuje u bazu podataka
            patrolNamePref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {

                    Settings settings = Settings.getSettings();//
                    settings.setPatrolName(newValue.toString());
                    settings.save();
                    patrolNamePref.setText(Settings.getSettings().getPatrolName());
                    patrolNamePref.setSummary(Settings.getSettings().getPatrolName());

                    return false;
                }
            });

            emailToSendPref = (EditTextPreference) findPreference("emailToSend");
            emailToSendPref.setText(Settings.getSettings().getEmailToSendReport());
            emailToSendPref.setSummary(Settings.getSettings().getEmailToSendReport());
            //nakon promjene vrijednost, ista se pohranjeuje u bazu podataka

            emailToSendPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {

                    Settings settings = Settings.getSettings();//
                    settings.setEmailToSendReport(newValue.toString());

                    settings.save();
                    emailToSendPref.setText(Settings.getSettings().getEmailToSendReport());
                    emailToSendPref.setSummary(Settings.getSettings().getEmailToSendReport());

                    return false;
                }
            });
        }

        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            int horizontalMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
            int verticalMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
            int topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (int) getResources().getDimension(R.dimen.activity_vertical_margin) + 30, getResources().getDisplayMetrics());

            View lv = getView().findViewById(android.R.id.list);


            if (lv != null)
                lv.setPadding(horizontalMargin, topMargin, horizontalMargin, verticalMargin);


        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

    }

    /**
     * This fragment shows notification preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */


    /**
     * This fragment shows data and sync preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class DataSyncPreferenceFragment extends PreferenceFragment implements DataLoadedListener {
        ProgressDialog progress;
        List<Photos> photoses;
        Integer loadedPhotos = 0;
        Preference syncNowPref;
        Preference webServiceAddressPref;
        Preference lastSyncPref;

        /**
         * Po kreiranju fragmenta se popunjavaju svi podatci o sinkroniziranju sa podatcima dohvaćenim iz baze podata.
         *
         * @param savedInstanceState
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_data_sync);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.


            syncNowPref = findPreference("syncKey");

            syncNowPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    //open browser or intent here

                    loadFromService();

                    return true;
                }
            });


            webServiceAddressPref = findPreference("webServiceAddressPref");

            bindPreferenceSummaryToValue(webServiceAddressPref);


            webServiceAddressPref.setSummary(Settings.getSettings().getWebServicesAddress());

            // korisit je u slučaju da se korisniku omogući promjena web adrese Web Servisa
            webServiceAddressPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    //Settings.getSettings().setWebServicesAddress(newValue.toString());
                    return false;
                }
            });


            lastSyncPref = findPreference("lastSyncPref");
            bindPreferenceSummaryToValue(lastSyncPref);
            lastSyncPref.setSummary(android.text.format.DateFormat.format("dd. MM. yyyy hh:mm:ss ", Settings.getSettings().getLastSync()));


        }

        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            int horizontalMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
            int verticalMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
            int topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (int) getResources().getDimension(R.dimen.activity_vertical_margin) + 30, getResources().getDisplayMetrics());

            View lv = getView().findViewById(android.R.id.list);


            if (lv != null)
                lv.setPadding(horizontalMargin, topMargin, horizontalMargin, verticalMargin);


        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

        /**
         * Pokreće se povezivanje sa WS te dohvaćanje podataka i pohranjivanje istih u bazu podataka.
         */
        private void loadFromService() {

            System.out.println("Povezivanje sa web servisom..");

            // provjera postojanja veze sa web servisom
            // TIMEOUT: 10sec
            new TestConnestion().execute(Settings.getSettings().getWebServicesAddress(), "10000");

            progress = new ProgressDialog(getActivity());
            progress.setTitle("Preuzimanje podataka sa web servisa");
            progress.setMessage("Povezivanje sa serverom...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog


            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progress.setMax(1);
            progress.show();

            DataLoader dataLoader;

            dataLoader = new WsDataLoader();

            dataLoader.loadData(this);

        }


        /**
         * Nakon usjpješnog dohvačanja svih podataka sa udaljenog webservisa i uspješnog spremanja zapisa u bazu podataka,
         * ova metoda kontrolira progress bar, te pokreće dohvaćanje/preuzimanje svim slika sa web mjesta.
         */
        @Override
        public void onDataLoaded() {

            System.out.println("Data is here... ");

            progress.setMessage("Dohvaćeni podatci");

            PhotosController photosController = new PhotosController();

            photoses = photosController.GetAllRecordsFromTable();

            progress.setProgress(1);
            progress.setMax(photoses.size());


            loadedPhotos = 0;

            SaveResourceImage saveResourceImage = new SaveResourceImage(getActivity(), simpleImageLoadingListener);

            saveResourceImage.SaveAllPhotoFromUrlToInternalStorage();

            progress.setTitle("Preuzimanje slika");
            progress.setMessage("Dohvaćanje slika...");

        }


        SimpleImageLoadingListener simpleImageLoadingListener = new SimpleImageLoadingListener() {

            /**
             * Nakon uspješnog pruzimanja slike ona se pohranjuje u memoriju uređaja, a naziv se pohranjuje u bazu podataka.
             * @param imageUri
             * @param view
             * @param loadedImage
             */
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                String name = SaveResourceImage.sha256(imageUri);

                System.out.println("URL_SLIKE: spremljan slika___:" + imageUri);
                // loaded bitmap is here (loadedImage)
                //Save the bitmap or do something with it here
                Bitmap scal = Bitmap.createScaledBitmap(loadedImage, 270, 200, false);
                new ImageSaver(getActivity()).
                        setFileName(name + ".png").
                        setDirectoryName("Images").
                        save(scal);

                oneMoreDownloadedPicture(true, imageUri);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                super.onLoadingFailed(imageUri, view, failReason);
                System.out.println("URL_SLIKE: failed___:" + imageUri);
                oneMoreDownloadedPicture(false, imageUri);

            }
        };


        /**
         * Provjera se da li su preuzete sve slike, odnosno kontrolira progress bar.
         * Po završetku preuzimanja svih slika javlja poruku o uspjehu i zatvara progress bar.
         *
         * @param success
         * @param uri
         */
        private void oneMoreDownloadedPicture(boolean success, String uri) {

            if (success) {

                progress.setMessage("Uspješno dohvaćena slika sa adrese: \n \n  " + uri);
            } else {
                progress.setMessage("Neuspješno dohvaćenje slika sa adrese: \n \n " + uri);

            }
            if (++loadedPhotos <= photoses.size()) {

                progress.setProgress(loadedPhotos);

            }

            if (loadedPhotos == photoses.size()) {
                java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());
                Settings settings = Settings.getSettings();
                settings.setLastSync(CurrentDate);
                settings.save();

                lastSyncPref.setSummary(android.text.format.DateFormat.format("dd. MM. yyyy hh:mm:ss ", Settings.getSettings().getLastSync()));


                // To dismiss the dialog
                progress.setMessage("Završeno dohvaćanje slika!!");
                progress.dismiss();

                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setMessage("Uspješno dohavećeni podatci sa web servisa!");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "U redu",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });


                AlertDialog alert11 = builder1.create();
                alert11.show();

            }

        }


        /**
         * Klasa koja na pozadinskoj dretvi testira postoji li veza prema Web Servisu
         */
        class TestConnestion extends AsyncTask<String, Void, Boolean> {


            /**
             * Pokušava uspostaviti vezu sa serverom prema prosljeđenom URL i vremenu čekanja (Time out)
             * <p>
             * Ako NE POSTOJI  veza javlja poruku u dialogu! Zatvara progress bar!!
             * <p>
             * Ako POSTOJI veza, onda ne radi ništa.
             *
             * @param url [0] URL adresa [1] timeout
             * @return [TRUE] ako postoji veza [FALSE] ako ne postoji veza
             */
            @Override
            protected Boolean doInBackground(String... url) {
                try {
                    URL myUrl = new URL(url[0]);
                    URLConnection connection = myUrl.openConnection();
                    connection.setConnectTimeout(Integer.valueOf(url[1]));
                    connection.connect();
                    System.out.println("HOST: dostupan");
                    return true;
                } catch (Exception e) {

                    System.out.println("HOST: NE dostupan");
                    return false;
                } finally {

                }
            }

            protected void onPostExecute(Boolean feed) {

                if (feed) {
                    progress.setMessage("Uspješno povezan sa serverom!!");

                    System.out.println("HOST:  dostupan");
                } else {
                    progress.setMessage("Neuspješno povezan sa serverom!!");


                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setMessage("Problem kod povezivanja sa web servisom.");
                    builder1.setCancelable(true);


                    builder1.setPositiveButton(
                            "U redu.",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    progress.dismiss();
                                }
                            });


                    AlertDialog alert11 = builder1.create();
                    alert11.show();


                    System.out.println("HOST: NE dostupan");
                }

            }
        }


    }
}
