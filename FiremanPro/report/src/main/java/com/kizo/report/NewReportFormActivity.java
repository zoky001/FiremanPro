package com.kizo.report;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.project.test.database.Entities.fire_intervention.Size_of_fire;
import com.project.test.database.Entities.fire_intervention.Spatial_spread;
import com.project.test.database.Entities.fire_intervention.Spreading_smoke;
import com.project.test.database.Entities.fire_intervention.Time_spread;
import com.project.test.database.Entities.fireman_patrol.Fireman;
import com.project.test.database.Entities.fireman_patrol.Fireman_patrol;
import com.project.test.database.Entities.fireman_patrol.Truck;
import com.project.test.database.Entities.fireman_patrol.Type_of_truck;
import com.project.test.database.Entities.fireman_patrol.Type_of_unit;
import com.project.test.database.Entities.report.Intervention_Type;
import com.project.test.database.Entities.report.Intervention_track;
import com.project.test.database.Entities.report.Outdoor_type;
import com.project.test.database.Entities.report.Sort_of_intervention;
import com.project.test.database.controllers.FiremanPatrolController;
import com.project.test.database.controllers.report.InterventionController;
import com.project.test.database.controllers.report.Types_all_Controller;

import java.util.ArrayList;
import java.util.List;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.fragments.BackConfirmationFragment;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;

public class NewReportFormActivity extends AppCompatActivity implements VerticalStepperForm {

    private static final String NO_SELECTED = "Odaberite..";
    Types_all_Controller types_all_controller = new Types_all_Controller();
    public static final String NEW_ALARM_ADDED = "new_alarm_added";

    // Information about the steps/fields of the form
    private static final int MAIN_INFORMATION_NUM = 0;
    private static final int DESCRIPTION_STEP_NUM = 1;
    private static final int FIRE_STEP_NUM = 2;
    private static final int MATERIAL_AND_COST_NUM = 3;
    private static final int INTERVENTION_COST_NUM = 4;
    private static final int HELP_NUM = 5;
    private static final int MEHANIZATION_NUM = 6;
    private static final int FIREMEN_NUM = 7;


    // Title step
    private EditText chooseTypeAndSort;
    private static final int MIN_CHARACTERS_TITLE = 3;
    public static final String STATE_TITLE = "Osnovne informacije";

    // Description step
    private EditText descriptionEditText;
    public static final String STATE_DESCRIPTION = "description";

    /*
    // Time step
    private TextView timeTextView;
    private TimePickerDialog timePicker;
    private Pair<Integer, Integer> time;
    public static final String STATE_TIME_HOUR = "time_hour";
    public static final String STATE_TIME_MINUTES = "time_minutes";

    // Week days step
    private boolean[] weekDays;
    private LinearLayout daysStepContent;
    public static final String STATE_WEEK_DAYS = "week_days";
    */

    private boolean confirmBack = true;
    private ProgressDialog progressDialog;
    private VerticalStepperFormLayout verticalStepperForm;

    Spinner spinnerType, spinnerSort;
    int userSelectedIndex;

    Spinner spinnerVehicle;
    EditText surfaceNumber;
    EditText superficiesNumber;
    EditText kmNumber;
    EditText clockNumber;
    EditText waterNumber;
    EditText foamNumber;
    EditText powderNumber;
    EditText co2Number;
    EditText numberOfFiremanParticipated;


    EditText navalVehicleNumber;
    EditText commandVehicleNumber;
    EditText tehnicalVehicleNumber;
    EditText automaticLadderNumber;
    EditText roadTankersNumber;
    EditText specialVehicleNumber;
    EditText transportationVehicleNumber;

    Spinner firemanSpinner;
    EditText numberOfFiremanNumber;

    String sviOdabranivatrogasci = "";
    int brojac = 0;

    LinearLayout mehanizationContent;

    Spinner spinneTypeOfUnit;

    Spinner usedTruck;

    //fire
    Spinner sizeOfFire;
    EditText destroyedSpace;
    Spinner repeatedSpinner;
    Spinner spatialSpread;
    Spinner timeSpread;
    Spinner smokeSpread;
    Spinner outdoorSpread;
    private Intervention_track intervencije;
    private EditText interventionDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_stepper_report_form);


        String s = getIntent().getStringExtra("IDintervencije");
        System.out.println("SESSION FRAGMENT_idkuce: " + s);
        int a = Integer.parseInt(getIntent().getStringExtra("IDintervencije"));
        if (a != -1) {

            intervencije = InterventionController.getInterventionByID(a);

        } else {
            intervencije = InterventionController.getInterventionByID(a);
        }

        System.out.println("SESSION FRAGMENT_idkuce: " + intervencije.getHouse().getName_owner());
        initializeActivity();
    }

    private void initializeActivity() {
        // Vertical Stepper form vars
        int colorPrimary = ContextCompat.getColor(getApplicationContext(), R.color.glavna);
        int colorPrimaryDark = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDarkREPORT);

        String[] stepsTitles = getResources().getStringArray(R.array.steps_titles);
        String[] stepsSubtitles = getResources().getStringArray(R.array.steps_subtitles);

        // Here we find and initialize the form
        verticalStepperForm = (VerticalStepperFormLayout) findViewById(R.id.vertical_stepper_form);
        VerticalStepperFormLayout.Builder.newInstance(verticalStepperForm, stepsTitles, this, this)
                //.stepsSubtitles(stepsSubtitles)
                .materialDesignInDisabledSteps(true) // false by default
                .showVerticalLineWhenStepsAreCollapsed(true) // false by default
                .primaryColor(colorPrimary)
               // .primaryDarkColor(colorPrimaryDark)
                .displayBottomNavigation(true)
                .init();

    }

    // METHODS THAT HAVE TO BE IMPLEMENTED TO MAKE THE LIBRARY WORK
    // (Implementation of the interface "VerticalStepperForm")

    @Override
    public View createStepContentView(int stepNumber) {
        // Here we generate the content view of the correspondent step and we return it so it gets
        // automatically added to the step layout (AKA stepContent)
        View view = null;
        switch (stepNumber) {
            case MAIN_INFORMATION_NUM:
                // view = createAlarmTitleStep();
                view  = createTypeAndSortStep();
                break;
            case DESCRIPTION_STEP_NUM:
                view = createUsedResourcesStep();
                break;
            case FIRE_STEP_NUM:
                view = createFireStep();
                break;
            case MATERIAL_AND_COST_NUM:
               // view = createUsedResources();
                view = createOwnerAndMaterialCostStep();
                break;
            case INTERVENTION_COST_NUM:
                view = createDescriptionStep();
                break;
            case HELP_NUM:
                view = createMehanizationStep();
                break;
            case MEHANIZATION_NUM:
                view = createInterventionCostStep();
                break;
            case FIREMEN_NUM:
                view = createFiremenStep();
        }
        return view;
    }

    @Override
    public void onStepOpening(int stepNumber) {
        switch (stepNumber) {
            case MAIN_INFORMATION_NUM:
                //VALIDIRANO i SPREMLJENO
                /*
                System.out.println("prvi step:" + interventionDescription.getText());

                if (interventionDescription.length() == 0){
                verticalStepperForm.setActiveStepAsCompleted();
                }
                else {
                    String errorMessage = "Morate upisati opis intervencije";
                    verticalStepperForm.setActiveStepAsUncompleted(errorMessage);
                }*/

            break;
            case DESCRIPTION_STEP_NUM:
                save_MAIN_INFORMATION();
                //NIJE
              /*  System.out.println("drugi step:");
*/
            break;
            case MATERIAL_AND_COST_NUM:
                //VALIDIRANO 
                verticalStepperForm.setStepAsCompleted(stepNumber);
            case INTERVENTION_COST_NUM:
                //VALIDIRANO i SPREMLJENO
                //verticalStepperForm.setStepAsCompleted(stepNumber);
            case HELP_NUM:
                //NIJE
                verticalStepperForm.setStepAsCompleted(stepNumber);
            case MEHANIZATION_NUM:
                //NIJE
                verticalStepperForm.setStepAsCompleted(stepNumber);
            case  FIREMEN_NUM:
                //NIJE
                verticalStepperForm.setStepAsCompleted(stepNumber);
            case FIRE_STEP_NUM:
                //djelomično
                verticalStepperForm.setStepAsCompleted(stepNumber);
        }
    }

    @Override
    public void sendData() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.show();
        progressDialog.setMessage(getString(R.string.vertical_form_stepper_form_sending_data_message));
        executeDataSending();
    }

    // OTHER METHODS USED TO MAKE THIS EXAMPLE WORK

    private void executeDataSending() {

        // TODO Use here the data of the form as you wish

        // Fake data sending effect
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    Intent intent = getIntent();
                    setResult(RESULT_OK, intent);
                    intent.putExtra(NEW_ALARM_ADDED, true);
                    intent.putExtra(STATE_TITLE, chooseTypeAndSort.getText().toString());
                    intent.putExtra(STATE_DESCRIPTION, descriptionEditText.getText().toString());
                    /*
                    intent.putExtra(STATE_TIME_HOUR, time.first);
                    intent.putExtra(STATE_TIME_MINUTES, time.second);
                    intent.putExtra(STATE_WEEK_DAYS, weekDays);
                    intent.putExtra(STATE_WEEK_DAYS, weekDays);
                    intent.putExtra(STATE_WEEK_DAYS, weekDays);
                    */

                    // You must set confirmBack to false before calling finish() to avoid the confirmation dialog
                    confirmBack = false;
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start(); // You should delete this code and add yours

    }

    private View createFireStep() {
        chooseTypeAndSort = new EditText(this);
        // titleEditText.setHint(R.string.form_hint_title);
        // titleEditText.setSingleLine(true);

        LayoutInflater inflate = LayoutInflater.from(getBaseContext());
        LinearLayout fireContent = (LinearLayout) inflate.inflate(R.layout.step_fire, null, false);

        addSpinnerValue(sizeOfFire, fireContent, R.id.size_of_fire, getSizeOfFireAdapter());
        addSpinnerValue(repeatedSpinner, fireContent, R.id.repeated, getSizeOfFireAdapter());
        addSpinnerValue(spatialSpread, fireContent, R.id.spatial_spread, getSpatialSpreadAdapter());
        addSpinnerValue(timeSpread, fireContent, R.id.time_spread, getTimeSpreadAdapter());
        addSpinnerValue(smokeSpread, fireContent, R.id.smoke_spread, getSmokeSpreadAdapter());
        addSpinnerValue(outdoorSpread, fireContent, R.id.outdoor_spread, getOutdoorSpreadAdapter());

        destroyedSpace = (EditText) fireContent.findViewById(R.id.destroyed_space);
        numberKeybord(destroyedSpace);
        destroyedSpace.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validate_FIRE();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return fireContent;
    }
    // -- need to add validation for spinners !!!
    private boolean validate_FIRE() {
        boolean isCorrect = false;

        String destroyed = destroyedSpace.getText().toString();

        if(destroyed.length() > 0) {
            isCorrect = true;
            verticalStepperForm.setActiveStepAsCompleted();

        } else {
            String titleErrorString = "Niste popunili sve podatke!";
            verticalStepperForm.setActiveStepAsUncompleted(titleErrorString);
        }

        return isCorrect;
    }
    private void save_FIRE(){
        if(validate_FIRE()) {
            String destroyed = destroyedSpace.getText().toString();

            // insert in database
        }
    }

    private ArrayAdapter<String> getOutdoorSpreadAdapter() {
        List<String> typeAll = new ArrayList<String>();
        Types_all_Controller type_all_controller = new Types_all_Controller();
        List<Outdoor_type> all_size_of_fire = type_all_controller.GetAllRecordsFromTable_Outdoor_type();
        for(Outdoor_type t : all_size_of_fire){
            typeAll.add(t.getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typeAll);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        return dataAdapter;
    }

    private ArrayAdapter<String> getSmokeSpreadAdapter() {
        List<String> typeAll = new ArrayList<String>();
        Types_all_Controller type_all_controller = new Types_all_Controller();
        List<Spreading_smoke> all_size_of_fire = type_all_controller.GetAllRecordsFromTable_Spreading_smoke();
        for(Spreading_smoke t : all_size_of_fire){
            typeAll.add(t.getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typeAll);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        return  dataAdapter;
    }

    private ArrayAdapter<String> getTimeSpreadAdapter() {
        List<String> typeAll = new ArrayList<String>();
        Types_all_Controller type_all_controller = new Types_all_Controller();
        List<Time_spread> all_size_of_fire = type_all_controller.GetAllRecordsFromTable_Time_spread();
        for(Time_spread t : all_size_of_fire){
            typeAll.add(t.getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typeAll);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        return  dataAdapter;
    }

    private ArrayAdapter<String> getSpatialSpreadAdapter() {
        List<String> typeAll = new ArrayList<String>();
        Types_all_Controller type_all_controller = new Types_all_Controller();
        List<Spatial_spread> all_size_of_fire = type_all_controller.GetAllRecordsFromTable_Spatial_spread();
        for(Spatial_spread s : all_size_of_fire){
            typeAll.add(s.getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typeAll);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        return  dataAdapter;
    }


    private ArrayAdapter<String> getSizeOfFireAdapter() {
        List<String> typeAll = new ArrayList<String>();
        Types_all_Controller type_all_controller = new Types_all_Controller();
        List<Size_of_fire> all_size_of_fire = type_all_controller.GetAllRecordsFromTable_Size_of_fire();
        for(Size_of_fire s : all_size_of_fire){
            typeAll.add(s.getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typeAll);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        return  dataAdapter;
    }

    private void addSpinnerValue(Spinner spinner, LinearLayout content, int id, ArrayAdapter<String> methodArray) {
        spinner = (Spinner) content.findViewById(id);
        spinner.setAdapter(methodArray);
    }



    private View createTypeAndSortStep() {
        chooseTypeAndSort = new EditText(this);
        // titleEditText.setHint(R.string.form_hint_title);
        // titleEditText.setSingleLine(true);
       // verticalStepperForm.setActiveStepAsUncompleted("Potrebno je upisani opis intervencije");
        LayoutInflater inflate = LayoutInflater.from(getBaseContext());
        final LinearLayout typeAndSortContent = (LinearLayout) inflate.inflate(R.layout.type_and_sort_of_intervention, null, false);

        spinnerSort = (Spinner) typeAndSortContent.findViewById(R.id.sort_of_intervention);
        spinnerSort.setAdapter(getSortOfInterventionAdapter());


        interventionDescription = (EditText) typeAndSortContent.findViewById(R.id.description);


        interventionDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("AFTER");
            validate_MAIN_INFORMATIONA();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
/*
        spinnerType = (Spinner) typeAndSortContent.findViewById(R.id.type_of_intervention);
        spinnerType.setAdapter(getTypeOfInterventionAdapter());
*/
        spinnerType = (Spinner) typeAndSortContent.findViewById(R.id.type_of_intervention);
        spinnerType.setVisibility(View.INVISIBLE);

        spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {


                Object item = parentView.getItemAtPosition(position);
                System.out.println("SPINNER"+ item.toString());


                spinnerType.setAdapter(getTypeOfInterventionAdapter(item.toString()));
                spinnerType.setVisibility(View.VISIBLE);

                validate_MAIN_INFORMATIONA();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                spinnerType.setVisibility(View.INVISIBLE);
                // your code here
            }
        });

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                validate_MAIN_INFORMATIONA();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return typeAndSortContent;
    }



    private boolean validate_MAIN_INFORMATIONA() {
        boolean titleIsCorrect = false;
        String title = interventionDescription.getText().toString();

        if(title.length() > 3 && !spinnerSort.getSelectedItem().toString().equals(NO_SELECTED) && !spinnerType.getSelectedItem().toString().equals(NO_SELECTED)) {
            titleIsCorrect = true;

            verticalStepperForm.setActiveStepAsCompleted();
            // Equivalent to: verticalStepperForm.setStepAsCompleted(TITLE_STEP_NUM);

        } else {
            String titleErrorString = "Potrebno je upisati opis intervencije  i odabrati vrstu i tip intervencije";
            verticalStepperForm.setActiveStepAsUncompleted(titleErrorString);
            // Equivalent to: verticalStepperForm.setStepAsUncompleted(TITLE_STEP_NUM, titleError);

        }

        return titleIsCorrect;
    }
 private void save_MAIN_INFORMATION(){
     if(validate_MAIN_INFORMATIONA()) {

         String title = interventionDescription.getText().toString();
         // insert in database
         intervencije.addDescriptionOfIntervention(title);

         if (spinnerSort.getSelectedItem().toString().equals(types_all_controller.get_FIRE_Sort_of_intervention().getName())) {

             intervencije.setThisInterventionAsFire();
             intervencije.getReports().addFireIntervention(Types_all_Controller.get_Intervention_typeByName(spinnerType.getSelectedItem().toString()));
             System.out.println("SAve first step");
         }
         if (spinnerSort.getSelectedItem().toString().equals(types_all_controller.get_TRHNICAL_Sort_of_intervention().getName())) {

             intervencije.setThisInterventionAsTehnical();
             intervencije.getReports().addTehnicalInterventionDetails(Types_all_Controller.get_Intervention_typeByName(spinnerType.getSelectedItem().toString()));
             System.out.println("SAve first step");
         }
         if (spinnerSort.getSelectedItem().toString().equals(types_all_controller.get_OTHER_Sort_of_intervention().getName())) {

             intervencije.setThisInterventionAsOther();
             intervencije.getReports().addOtherInterventionDetails(Types_all_Controller.get_Intervention_typeByName(spinnerType.getSelectedItem().toString()));
             System.out.println("SAve first step");
         }
     }
 }
    private ArrayAdapter<String> getTypeOfInterventionAdapter(String sortName) {
        List<String> typeAll = new ArrayList<String>();
        Types_all_Controller type_all_controller = new Types_all_Controller();
        List<Intervention_Type> type_of_intervention = type_all_controller.GetAllRecordsFromTable_Intervention_type();

        typeAll.add(NO_SELECTED); // first item, "unselected"
        for(Intervention_Type i : type_of_intervention){

            if (i.getSort_of_intervention().getName().equals(sortName))
            {typeAll.add(i.getName());
            }
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typeAll);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        return  dataAdapter;
    }

    private ArrayAdapter<String> getSortOfInterventionAdapter() {
        List<String> sortAll = new ArrayList<String>();
        Types_all_Controller type_all_controller = new Types_all_Controller();
        List<Sort_of_intervention> sort_of_intervention = type_all_controller.GetAllRecordsFromTable_Sort_of_intervention();
        sortAll.add(NO_SELECTED); // first item, "unselected"
        for(Sort_of_intervention s : sort_of_intervention){
            sortAll.add(s.getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sortAll);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        return  dataAdapter;
    }

    private View createUsedResourcesStep() {
        chooseTypeAndSort = new EditText(this);
        // titleEditText.setHint(R.string.form_hint_title);
        // titleEditText.setSingleLine(true);
        LayoutInflater inflate = LayoutInflater.from(getBaseContext());
        final LinearLayout vehicleContent = (LinearLayout) inflate.inflate(R.layout.step_used_resources, null, false);

        final Button prvi = new Button(this);
        prvi.setText("Dodaj resurs");

        addUsedResources(prvi, vehicleContent, vehicleContent);

        return vehicleContent;
    }


    private void addUsedResources(Button prvi, final View v, final  LinearLayout ll) {
        spinnerVehicle = (Spinner) v.findViewById(R.id.vehicle);
        spinnerVehicle.setAdapter(getVehicleAdapter());

        /*
        spinnerVehicle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                validate_USED_RESOURCES();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        spinneTypeOfUnit = (Spinner) v.findViewById(R.id.sort_of_unit);
        spinneTypeOfUnit.setAdapter(getTypeOfUnitAdapter());

        /*spinneTypeOfUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                validate_USED_RESOURCES();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        kmNumber = (EditText) v.findViewById(R.id.km);
        numberKeybord(kmNumber);

        /*kmNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validate_USED_RESOURCES();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/

        clockNumber = (EditText) v.findViewById(R.id.clock);
        numberKeybord(clockNumber);
        /*
        clockNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validate_USED_RESOURCES();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/

        numberOfFiremanParticipated = (EditText) v.findViewById(R.id.number_of_firemen_in_truck);
        numberKeybord(numberOfFiremanParticipated);
        /*
        numberOfFiremanNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validate_USED_RESOURCES();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/

        waterNumber = (EditText) v.findViewById(R.id.water);
        numberKeybord(waterNumber);
        /*
        waterNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validate_USED_RESOURCES();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/

        foamNumber = (EditText) v.findViewById(R.id.foam);
        numberKeybord(foamNumber);
        /*
        foamNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validate_USED_RESOURCES();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/

        powderNumber = (EditText) v.findViewById(R.id.powder);
        numberKeybord(powderNumber);
        /*
        powderNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validate_USED_RESOURCES();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/

        co2Number = (EditText) v.findViewById(R.id.CO2);
        numberKeybord(co2Number);
        /*
        co2Number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validate_USED_RESOURCES();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/

        ll.addView(prvi);

        LayoutInflater inflate = LayoutInflater.from(getBaseContext());
        final LinearLayout addedVehicleContent = (LinearLayout) inflate.inflate(R.layout.step_used_resources, null, false);

        LayoutInflater factory = LayoutInflater.from(this);
        final View myView = factory.inflate(R.layout.step_used_resources, null);
        Button b = new Button(this);
        b.setText("Dodaj resurs");
        addNewUsedResources(prvi, b, ll, myView);
    }
/*
    private boolean validate_USED_RESOURCES() {
        boolean correctInformation = true; //all together

        boolean correct = true;
        if(spinnerVehicle.getSelectedItem().toString().equals(NO_SELECTED)) {
            String titleErrorVehicle = "Potrebno je odabrati vozilo!";
            verticalStepperForm.setActiveStepAsUncompleted(titleErrorVehicle);
            correct = false;
        }
        if (spinneTypeOfUnit.getSelectedItem().toString().equals(NO_SELECTED)) {
            String titleErrorUnit = "Potrebno je odabrati vrstu i naziv postrojbe!";
            verticalStepperForm.setActiveStepAsUncompleted(titleErrorUnit);
            correct = false;
        }
        if(kmNumber.getText().toString().length() == 0){
            String titleErrorKm = "Potrebno je unjeti broj kilometara!";
            verticalStepperForm.setActiveStepAsUncompleted(titleErrorKm);
            correct = false;
        }
        if(clockNumber.getText().toString().length() == 0){
            String titleErrorClock = "Potrebno je unjeti broj sati!";
            verticalStepperForm.setActiveStepAsUncompleted(titleErrorClock);
            correct = false;
        }
        if(numberOfFiremanParticipated.getText().toString().length() == 0){
            String titleErrorFiremans = "Potrebno je unjeti broj vatrogasaca!";
            verticalStepperForm.setActiveStepAsUncompleted(titleErrorFiremans);
            correct = false;
        }
        if(waterNumber.getText().toString().length() == 0){
            String titleErrorWater = "Potrebno je unjeti količinu vode!";
            verticalStepperForm.setActiveStepAsUncompleted(titleErrorWater);
            correct = false;
        }
        if(foamNumber.getText().toString().length() == 0){
            String titleErrorFoam = "Potrebno je unjeti količinu pjenila!";
            verticalStepperForm.setActiveStepAsUncompleted(titleErrorFoam);
            correct = false;
        }
        if(powderNumber.getText().toString().length() == 0){
            String titleErrorPowder = "Potrebno je unjeti količinu praha!";
            verticalStepperForm.setActiveStepAsUncompleted(titleErrorPowder);
            correct = false;
        }
        if(co2Number.getText().toString().length() == 0){
            String titleErrorco2 = "Potrebno je unjeti količinu co2!";
            verticalStepperForm.setActiveStepAsUncompleted(titleErrorco2);
            correct = false;
        }

        if(correct == true ){
            verticalStepperForm.setActiveStepAsCompleted();
        } else{
            verticalStepperForm.setActiveStepAsUncompleted("Niste unjeli sve podatke!");
        }

        return correctInformation;
    }*/

    private void save_USED_RESOURCES(){
        /*if(validate_USED_RESOURCES()) {

            String km =kmNumber.getText().toString();
            String water = waterNumber.getText().toString();
            String powder = powderNumber.getText().toString();
            String foam = foamNumber.getText().toString();
            String co2 = co2Number.getText().toString();
            String numberOfFiremans = numberOfFiremanParticipated.getText().toString();
            String clock = clockNumber.getText().toString();


            /*if (spinnerVehicle.getSelectedItem().toString().equals(types_all_controller.get_naval_vehicle_type_of_truck().getType_name())) {

            }
            if (spinneTypeOfUnit.getSelectedItem().toString().equals(types_all_controller.get_JVP_type_of_unit().getName())) {


            }

            // insert in database
            intervencije.getReports().addFiremanPatrolandTruck(Integer.parseInt(numberOfFiremans),
                    Double.parseDouble(water),
                    Double.parseDouble(foam), Double.parseDouble(powder),
                    Double.parseDouble(co2), Double.parseDouble(km),
                    Double.parseDouble(clock),
                    spinnerVehicle.getSelectedItem().toString(), spinneTypeOfUnit.getSelectedItem().toString());
        }*/
    }

    private void addNewUsedResources(final Button prvi,final Button noviB,  final LinearLayout ll, final  View myView) {
        prvi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ll.removeView(prvi);
                ll.addView(myView);
                addUsedResources(noviB, myView, ll);
            }
        });
    }

    private ArrayAdapter<String> getTypeOfUnitAdapter() {
        List<String> typeAll = new ArrayList<String>();
        Types_all_Controller type_all_controller = new Types_all_Controller();
        List<Type_of_unit> type_of_intervention = type_all_controller.GetAllRecordsFromTable_Type_of_unit();
        for(Type_of_unit i : type_of_intervention){
            typeAll.add(i.getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typeAll);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        return  dataAdapter;
    }

    private ArrayAdapter<String> getVehicleAdapter() {
        List<String> vehicleAll = new ArrayList<String>();
        Types_all_Controller type_all_controller = new Types_all_Controller();
        List<Type_of_truck> type_of_truck = type_all_controller.GetAllRecordsFromTable_Type_of_truck();
        for(Type_of_truck t : type_of_truck){
            vehicleAll.add(t.getType_name());
        }

        final ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, vehicleAll);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);

        return dataAdapter2;
    }

    private void numberKeybord(EditText et) {
        et.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        et.setTransformationMethod(new NumericKeyBoardTransformationMethod());
    }

    private class NumericKeyBoardTransformationMethod extends PasswordTransformationMethod {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return source;
        }
    }

    private View createOwnerAndMaterialCostStep() {
        chooseTypeAndSort = new EditText(this);
        // titleEditText.setHint(R.string.form_hint_title);
        // titleEditText.setSingleLine(true);

        LayoutInflater inflate = LayoutInflater.from(getBaseContext());
        LinearLayout ownerAndCostContent = (LinearLayout) inflate.inflate(R.layout.step_owner_and_cost, null, false);

        surfaceNumber = (EditText) ownerAndCostContent.findViewById(R.id.surface);
        numberKeybord(surfaceNumber);
        surfaceNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validate_OWNER_AND_MATERIAL_COST();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        superficiesNumber = (EditText) ownerAndCostContent.findViewById(R.id.superficies);
        numberKeybord(superficiesNumber);
        superficiesNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validate_OWNER_AND_MATERIAL_COST();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return ownerAndCostContent;
    }


    private boolean validate_OWNER_AND_MATERIAL_COST() {
        boolean isCorrect = false;
        String surface = surfaceNumber.getText().toString();
        String superficies = superficiesNumber.getText().toString();

        if(surface.length() > 0 && superficies.length() > 0) {
            isCorrect = true;
            verticalStepperForm.setActiveStepAsCompleted();

        } else {
            String titleErrorString = "Potrebno je upisati površinu objekata i vanjskog prostora!";
            verticalStepperForm.setActiveStepAsUncompleted(titleErrorString);
        }

        return isCorrect;
    }
    private void save_OWNER_AND_MATERIAL_COST(){
        if(validate_OWNER_AND_MATERIAL_COST()) {

            String surface = surfaceNumber.getText().toString();
            String superficies = superficiesNumber.getText().toString();
            // insert in database
            intervencije.addObjectSuperficies_ha(Double.parseDouble(superficies));
            intervencije.addObjectSurface_m2(Double.parseDouble(surface));
        }
    }
    private View createInterventionCostStep() {
        chooseTypeAndSort = new EditText(this);
        // titleEditText.setHint(R.string.form_hint_title);
        // titleEditText.setSingleLine(true);

        LayoutInflater inflate = LayoutInflater.from(getBaseContext());
        LinearLayout interventionCostContent = (LinearLayout) inflate.inflate(R.layout.step_intervention_cost, null, false);

        navalVehicleNumber = (EditText) interventionCostContent.findViewById(R.id.navalVehicle);
        numberKeybord(navalVehicleNumber);
        navalVehicleNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validate_INTERVENTION_COST();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        commandVehicleNumber = (EditText) interventionCostContent.findViewById(R.id.commandVehicle);
        numberKeybord(commandVehicleNumber);
        commandVehicleNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validate_INTERVENTION_COST();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tehnicalVehicleNumber = (EditText) interventionCostContent.findViewById(R.id.tehnicalVehicle);
        numberKeybord(tehnicalVehicleNumber);
        tehnicalVehicleNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validate_INTERVENTION_COST();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        automaticLadderNumber = (EditText) interventionCostContent.findViewById(R.id.automaticLadder);
        numberKeybord(automaticLadderNumber);
        automaticLadderNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validate_INTERVENTION_COST();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        roadTankersNumber = (EditText) interventionCostContent.findViewById(R.id.roadTankers);
        numberKeybord(roadTankersNumber);
        roadTankersNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validate_INTERVENTION_COST();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        specialVehicleNumber = (EditText) interventionCostContent.findViewById(R.id.specialVehicle);
        numberKeybord(specialVehicleNumber);
        specialVehicleNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validate_INTERVENTION_COST();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        transportationVehicleNumber = (EditText) interventionCostContent.findViewById(R.id.transportationVehicle);
        numberKeybord(transportationVehicleNumber);
        transportationVehicleNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validate_INTERVENTION_COST();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return interventionCostContent;
    }
    private boolean validate_INTERVENTION_COST() {
        boolean isCorrect = false;

        String navalVehicle = navalVehicleNumber.getText().toString();
        String commandVehicle = commandVehicleNumber.getText().toString();
        String technicalVehicle = tehnicalVehicleNumber.getText().toString();
        String automaticLadder = automaticLadderNumber.getText().toString();
        String roadTanker = roadTankersNumber.getText().toString();
        String specialVehicle = specialVehicleNumber.getText().toString();
        String transportVehicle = transportationVehicleNumber.getText().toString();

        if(navalVehicle.length() > 0 && commandVehicle.length() > 0 && technicalVehicle.length() > 0
                && automaticLadder.length() > 0 && roadTanker.length() > 0 && specialVehicle.length() > 0  && transportVehicle.length() > 0) {
            isCorrect = true;
            verticalStepperForm.setActiveStepAsCompleted();

        } else {
            String titleErrorString = "Niste popunili sve podatke!";
            verticalStepperForm.setActiveStepAsUncompleted(titleErrorString);
        }

        return isCorrect;
    }
    private void save_INTERVENTION_COST(){
        if(validate_INTERVENTION_COST()) {

            String navalVehicle = navalVehicleNumber.getText().toString();
            String commandVehicle = commandVehicleNumber.getText().toString();
            String technicalVehicle = tehnicalVehicleNumber.getText().toString();
            String automaticLadder = automaticLadderNumber.getText().toString();
            String roadTanker = roadTankersNumber.getText().toString();
            String specialVehicle = specialVehicleNumber.getText().toString();
            String transportVehicle = transportationVehicleNumber.getText().toString();

            // insert in database
        }
    }

    private View createDescriptionStep() {
        descriptionEditText = new EditText(this);
        descriptionEditText.setHint(R.string.form_hint_description);
        descriptionEditText.setSingleLine(true);
        descriptionEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                verticalStepperForm.goToNextStep();
                return false;
            }
        });
        return descriptionEditText;
    }



    private View createMehanizationStep() {
        chooseTypeAndSort = new EditText(this);

        final LayoutInflater inflate = LayoutInflater.from(getBaseContext());
        mehanizationContent = (LinearLayout) inflate.inflate(R.layout.step_mehanization, null, false);

        makeMehanizationSpinnerFull(mehanizationContent, mehanizationContent);

        /*
        LayoutInflater factory = LayoutInflater.from(this);
        final View myView = factory.inflate(R.layout.step_mehanization, null);

        addVehicleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                makeMehanizationSpinnerFull(v);
                mehanizationContent.addView(myView);

            }
               /* Spinner vehicleSpinner = new Spinner(this);
                RadioGroup.LayoutParams rprms = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
                rgp.addView(vehicleSpinner, rprms);

            }

        });

/*
        RadioGroup rgp = (RadioGroup) findViewById(R.id.mehanizationRadio);
        for (int i = 0; i < mehanizationAll.toArray().length; i++)
        {
            Spinner vehicleSpinner = onNewIntent();
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(String.valueOf(mehanizationAll.get(i)));
            radioButton.setId(i);
            RadioGroup.LayoutParams rprms = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            rgp.addView(radioButton, rprms);
        }
        */
        return mehanizationContent;
    }

    private void addMoreMehanization(final Button b, final View myView, final LinearLayout ll) {
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ll.removeView(b);
                ll.addView(myView);
                makeMehanizationSpinnerFull(ll, myView);
            }
        });
    }


    private void makeMehanizationSpinnerFull(LinearLayout ll, View v) {
        usedTruck = (Spinner) v.findViewById(R.id.vehicleUsed);
        usedTruck.setAdapter(getTruckAdapter());

        EditText kmOdabrano = (EditText) v.findViewById(R.id.km);
        numberKeybord(kmOdabrano);

        final Button b = new Button(this);
        ll.addView(b);
        b.setText("noviii");

        LayoutInflater factory = LayoutInflater.from(this);
        final View myView = factory.inflate(R.layout.step_mehanization, null);

        addMoreMehanization(b, myView, ll);
    }


    private SpinnerAdapter getTruckAdapter() {
        List<String> truckAll = new ArrayList<String>();
        Types_all_Controller type_all_controller = new Types_all_Controller();
        List<Truck> all_trucks = type_all_controller.GetAllRecordsFromTable_Truck();
        for(Truck t : all_trucks){
            truckAll.add(t.getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, truckAll);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        return  dataAdapter;
    }


    private View createFiremenStep() {
        chooseTypeAndSort = new EditText(this);

        LayoutInflater inflate = LayoutInflater.from(getBaseContext());
        final LinearLayout firemenContent = (LinearLayout) inflate.inflate(R.layout.step_firemen, null, false);

        final List<String> firemanList = new ArrayList<String>();
        Types_all_Controller type_all_controller = new Types_all_Controller();
        List<Fireman> type_of_truck = type_all_controller.GetAllRecordsFromTable_Fireman();
        for(Fireman t : type_of_truck){
            firemanList.add(t.getName() + " " + t.getSurname());
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, firemanList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);

        firemanSpinner = (Spinner) firemenContent.findViewById(R.id.participatedFireman);
        firemanSpinner.setAdapter(dataAdapter2);

        final TextView ispis = (TextView) firemenContent.findViewById(R.id.all);

        firemanSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (brojac == 0) {
                }
                else {
                        String selectedItemText = (String) parent.getItemAtPosition(position);
                        firemanList.remove(parent.getItemAtPosition(position));
                        sviOdabranivatrogasci += selectedItemText + "\n";
                        ispis.setTextSize(18);
                        ispis.setText(sviOdabranivatrogasci);
                }
                brojac++;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return firemenContent;
    }


    private boolean checkTitleStep(String title) {
        boolean titleIsCorrect = false;

        if(title.length() >= MIN_CHARACTERS_TITLE) {
            titleIsCorrect = true;

            verticalStepperForm.setActiveStepAsCompleted();
            // Equivalent to: verticalStepperForm.setStepAsCompleted(TITLE_STEP_NUM);

        } else {
            String titleErrorString = getResources().getString(R.string.error_title_min_characters);
            String titleError = String.format(titleErrorString, MIN_CHARACTERS_TITLE);

            verticalStepperForm.setActiveStepAsUncompleted(titleError);
            // Equivalent to: verticalStepperForm.setStepAsUncompleted(TITLE_STEP_NUM, titleError);

        }

        return titleIsCorrect;
    }



    private View createSortOfUnitStep() {
        LayoutInflater inflate = LayoutInflater.from(getBaseContext());
        LinearLayout typeAndSortContent = (LinearLayout) inflate.inflate(R.layout.sort_of_unit_report, null, false);

        List<String> sortOfUnitAll = new ArrayList<String>();

        Types_all_Controller type_all_controller = new Types_all_Controller();
        List<Type_of_unit> sort_of_units = type_all_controller.GetAllRecordsFromTable_Sort_of_unit();
        for(Type_of_unit t : sort_of_units){
            sortOfUnitAll.add(t.getName());
        }

        RadioGroup rgp = (RadioGroup) findViewById(R.id.sortOfUnit);
        for (int i = 0; i < sortOfUnitAll.toArray().length; i++)
        {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(String.valueOf(sortOfUnitAll.get(i)));
            radioButton.setId(i);
            RadioGroup.LayoutParams rprms = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            rgp.addView(radioButton, rprms);
        }

        return typeAndSortContent;
    }


/*
    private View createAlarmTitleStep() {
        // This step view is generated programmatically
        titleEditText = new EditText(this);
        titleEditText.setHint(R.string.form_hint_title);
        titleEditText.setSingleLine(true);

        titleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkTitleStep(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        titleEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(checkTitleStep(v.getText().toString())) {
                    verticalStepperForm.goToNextStep();
                }
                return false;
            }
        });

        return titleEditText;
    }



    private View createAlarmTimeStep() {
        // This step view is generated by inflating a layout XML file
        LayoutInflater inflater = LayoutInflater.from(getBaseContext());
        LinearLayout timeStepContent =
                (LinearLayout) inflater.inflate(R.layout.step_time_layout, null, false);
        timeTextView = (TextView) timeStepContent.findViewById(R.id.time);
        timeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.show();
            }
        });
        return timeStepContent;
    }

    private View createAlarmDaysStep() {
        LayoutInflater inflater = LayoutInflater.from(getBaseContext());
        daysStepContent = (LinearLayout) inflater.inflate(
                R.layout.step_days_of_week_layout, null, false);

        String[] weekDays = getResources().getStringArray(R.array.week_days);
        for(int i = 0; i < weekDays.length; i++) {
            final int index = i;
            final LinearLayout dayLayout = getDayLayout(index);
            if(index < 5) {
                activateDay(index, dayLayout, false);
            } else {
                deactivateDay(index, dayLayout, false);
            }

            dayLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if((boolean)v.getTag()) {
                        deactivateDay(index, dayLayout, true);
                    } else {
                        activateDay(index, dayLayout, true);
                    }
                }
            });

            final TextView dayText = (TextView) dayLayout.findViewById(R.id.day);
            dayText.setText(weekDays[index]);
        }
        return daysStepContent;
    }

    private void setTimePicker(int hour, int minutes) {
        timePicker = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        setTime(hourOfDay, minute);
                    }
                }, hour, minutes, true);
    }



    private void setTime(int hour, int minutes) {
        time = new Pair<>(hour, minutes);
        String hourString = ((time.first > 9) ?
                String.valueOf(time.first) : ("0" + time.first));
        String minutesString = ((time.second > 9) ?
                String.valueOf(time.second) : ("0" + time.second));
        String time = hourString + ":" + minutesString;
        timeTextView.setText(time);
    }

    private void activateDay(int index, LinearLayout dayLayout, boolean check) {
        weekDays[index] = true;

        dayLayout.setTag(true);

        Drawable bg = ContextCompat.getDrawable(getBaseContext(),
                ernestoyaquello.com.verticalstepperform.R.drawable.circle_step_done);
        int colorPrimary = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryREPORT);
        bg.setColorFilter(new PorterDuffColorFilter(colorPrimary, PorterDuff.Mode.SRC_IN));
        dayLayout.setBackground(bg);

        TextView dayText = (TextView) dayLayout.findViewById(R.id.day);
        dayText.setTextColor(Color.rgb(255, 255, 255));

        if(check) {
        //    checkDays();
        }
    }

    private void deactivateDay(int index, LinearLayout dayLayout, boolean check) {
        weekDays[index] = false;

        dayLayout.setTag(false);

        dayLayout.setBackgroundResource(0);

        TextView dayText = (TextView) dayLayout.findViewById(R.id.day);
        int colour = ContextCompat.getColor(getBaseContext(), R.color.colorPrimaryREPORT);
        dayText.setTextColor(colour);

        if(check) {
          //  checkDays();
        }
    }
/*
    private boolean checkDays() {
        boolean thereIsAtLeastOneDaySelected = false;
        for(int i = 0; i < weekDays.length && !thereIsAtLeastOneDaySelected; i++) {
            if(weekDays[i]) {
                verticalStepperForm.setStepAsCompleted(DAYS_STEP_NUM); //INTERVENTION_COST_NUM
                thereIsAtLeastOneDaySelected = true;
            }
        }
        if(!thereIsAtLeastOneDaySelected) {
            verticalStepperForm.setStepAsUncompleted(DAYS_STEP_NUM, null); //INTERVENTION_COST_NUM
        }

        return thereIsAtLeastOneDaySelected;
    }

    private LinearLayout getDayLayout(int i) {
        int id = daysStepContent.getResources().getIdentifier(
                "day_" + i, "id", getPackageName());
        return (LinearLayout) daysStepContent.findViewById(id);
    }

    */
    // CONFIRMATION DIALOG WHEN USER TRIES TO LEAVE WITHOUT SUBMITTING

    private void confirmBack() {
        if(confirmBack && verticalStepperForm.isAnyStepCompleted()) {
            BackConfirmationFragment backConfirmation = new BackConfirmationFragment();
            backConfirmation.setOnConfirmBack(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    confirmBack = true;
                }
            });
            backConfirmation.setOnNotConfirmBack(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    confirmBack = false;
                    finish();
                }
            });
            backConfirmation.show(getSupportFragmentManager(), null);
        } else {
            confirmBack = false;
            finish();
        }
    }

    private void dismissDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        progressDialog = null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home && confirmBack) {
            confirmBack();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed(){
        confirmBack();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dismissDialog();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dismissDialog();
    }

    // SAVING AND RESTORING THE STATE

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        /*
        // Saving title field
        if(titleEditText != null) {
            savedInstanceState.putString(STATE_TITLE, titleEditText.getText().toString());
        }
*/
        // Saving description field
        if(descriptionEditText != null) {
            savedInstanceState.putString(STATE_DESCRIPTION, descriptionEditText.getText().toString());
        }

        /*
        // Saving time field
        if(time != null) {
            savedInstanceState.putInt(STATE_TIME_HOUR, time.first);
            savedInstanceState.putInt(STATE_TIME_MINUTES, time.second);
        }

        // Saving week days field
        if(weekDays != null) {
            savedInstanceState.putBooleanArray(STATE_WEEK_DAYS, weekDays);
        }
        */
        // The call to super method must be at the end here
        super.onSaveInstanceState(savedInstanceState);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

        // Restoration of title field
        if(savedInstanceState.containsKey(STATE_TITLE)) {
            String title = savedInstanceState.getString(STATE_TITLE);
           // titleEditText.setText(title);
        }

        // Restoration of description field
        if(savedInstanceState.containsKey(STATE_DESCRIPTION)) {
            String description = savedInstanceState.getString(STATE_DESCRIPTION);
            descriptionEditText.setText(description);
        }

        /*
        // Restoration of time field
        if(savedInstanceState.containsKey(STATE_TIME_HOUR)
                && savedInstanceState.containsKey(STATE_TIME_MINUTES)) {
            int hour = savedInstanceState.getInt(STATE_TIME_HOUR);
            int minutes = savedInstanceState.getInt(STATE_TIME_MINUTES);
            time = new Pair<>(hour, minutes);
            if(timePicker == null) {
            } else {
                timePicker.updateTime(hour, minutes);
            }
        }

        // Restoration of week days field
        if(savedInstanceState.containsKey(STATE_WEEK_DAYS)) {
            weekDays = savedInstanceState.getBooleanArray(STATE_WEEK_DAYS);
            if (weekDays != null) {
                for (int i = 0; i < weekDays.length; i++) {
                    if (weekDays[i]) {
                    } else {
                    }
                }
            }
        }
        */

        // The call to super method must be at the end here
        super.onRestoreInstanceState(savedInstanceState);
    }


}
