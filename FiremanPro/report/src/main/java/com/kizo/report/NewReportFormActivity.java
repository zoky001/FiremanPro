package com.kizo.report;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.test.suitebuilder.annotation.Smoke;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.Surface;
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
import com.project.test.database.Entities.fireman_patrol.Truck;
import com.project.test.database.Entities.fireman_patrol.Type_of_truck;
import com.project.test.database.Entities.fireman_patrol.Type_of_unit;
import com.project.test.database.Entities.report.Intervention_Type;
import com.project.test.database.Entities.report.Outdoor_type;
import com.project.test.database.Entities.report.Sort_of_intervention;
import com.project.test.database.controllers.report.Types_all_Controller;

import java.util.ArrayList;
import java.util.List;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.fragments.BackConfirmationFragment;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;

public class NewReportFormActivity extends AppCompatActivity implements VerticalStepperForm {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_stepper_report_form);

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



        return fireContent;
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

    @Override
    public void onStepOpening(int stepNumber) {
        switch (stepNumber) {
            case MAIN_INFORMATION_NUM:
            case DESCRIPTION_STEP_NUM:
            case MATERIAL_AND_COST_NUM:
                verticalStepperForm.setStepAsCompleted(stepNumber);
            case INTERVENTION_COST_NUM:
                verticalStepperForm.setStepAsCompleted(stepNumber);
            case HELP_NUM:
                verticalStepperForm.setStepAsCompleted(stepNumber);
            case MEHANIZATION_NUM:
                verticalStepperForm.setStepAsCompleted(stepNumber);
            case  FIREMEN_NUM:
                verticalStepperForm.setStepAsCompleted(stepNumber);
            case FIRE_STEP_NUM:
                verticalStepperForm.setStepAsCompleted(stepNumber);
        }
    }

    private View createTypeAndSortStep() {
        chooseTypeAndSort = new EditText(this);
        // titleEditText.setHint(R.string.form_hint_title);
        // titleEditText.setSingleLine(true);

        LayoutInflater inflate = LayoutInflater.from(getBaseContext());
        LinearLayout typeAndSortContent = (LinearLayout) inflate.inflate(R.layout.type_and_sort_of_intervention, null, false);

        spinnerSort = (Spinner) typeAndSortContent.findViewById(R.id.sort_of_intervention);
        spinnerSort.setAdapter(getSortOfInterventionAdapter());

        EditText inputLayout = (EditText) findViewById(R.id.description);

        spinnerType = (Spinner) typeAndSortContent.findViewById(R.id.type_of_intervention);
        spinnerType.setAdapter(getTypeOfInterventionAdapter());


        spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        return typeAndSortContent;
    }

    private ArrayAdapter<String> getTypeOfInterventionAdapter() {
        List<String> typeAll = new ArrayList<String>();
        Types_all_Controller type_all_controller = new Types_all_Controller();
        List<Intervention_Type> type_of_intervention = type_all_controller.GetAllRecordsFromTable_Intervention_type();
        for(Intervention_Type i : type_of_intervention){
            typeAll.add(i.getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typeAll);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        return  dataAdapter;
    }

    private ArrayAdapter<String> getSortOfInterventionAdapter() {
        List<String> sortAll = new ArrayList<String>();
        Types_all_Controller type_all_controller = new Types_all_Controller();
        List<Sort_of_intervention> sort_of_intervention = type_all_controller.GetAllRecordsFromTable_Sort_of_intervention();
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

        spinneTypeOfUnit = (Spinner) v.findViewById(R.id.sort_of_unit);
        spinneTypeOfUnit.setAdapter(getTypeOfUnitAdapter());

        kmNumber = (EditText) v.findViewById(R.id.km);
        numberKeybord(kmNumber);

        clockNumber = (EditText) v.findViewById(R.id.clock);
        numberKeybord(clockNumber);

        numberOfFiremanParticipated = (EditText) v.findViewById(R.id.number_of_firemen_in_truck);
        numberKeybord(numberOfFiremanParticipated);

        waterNumber = (EditText) v.findViewById(R.id.water);
        numberKeybord(waterNumber);

        foamNumber = (EditText) v.findViewById(R.id.foam);
        numberKeybord(foamNumber);

        powderNumber = (EditText) v.findViewById(R.id.powder);
        numberKeybord(powderNumber);

        co2Number = (EditText) v.findViewById(R.id.CO2);
        numberKeybord(co2Number);

        ll.addView(prvi);

        LayoutInflater inflate = LayoutInflater.from(getBaseContext());
        final LinearLayout addedVehicleContent = (LinearLayout) inflate.inflate(R.layout.step_used_resources, null, false);

        LayoutInflater factory = LayoutInflater.from(this);
        final View myView = factory.inflate(R.layout.step_used_resources, null);
        Button b = new Button(this);
        b.setText("Dodaj resurs");
        addNewUsedResources(prvi, b, ll, myView);
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

        superficiesNumber = (EditText) ownerAndCostContent.findViewById(R.id.superficies);
        numberKeybord(superficiesNumber);

        return ownerAndCostContent;
    }


    private View createInterventionCostStep() {
        chooseTypeAndSort = new EditText(this);
        // titleEditText.setHint(R.string.form_hint_title);
        // titleEditText.setSingleLine(true);

        LayoutInflater inflate = LayoutInflater.from(getBaseContext());
        LinearLayout interventionCostContent = (LinearLayout) inflate.inflate(R.layout.step_intervention_cost, null, false);

        navalVehicleNumber = (EditText) interventionCostContent.findViewById(R.id.navalVehicle);
        numberKeybord(navalVehicleNumber);

        commandVehicleNumber = (EditText) interventionCostContent.findViewById(R.id.commandVehicle);
        numberKeybord(commandVehicleNumber);

        tehnicalVehicleNumber = (EditText) interventionCostContent.findViewById(R.id.tehnicalVehicle);
        numberKeybord(tehnicalVehicleNumber);

        automaticLadderNumber = (EditText) interventionCostContent.findViewById(R.id.automaticLadder);
        numberKeybord(automaticLadderNumber);

        roadTankersNumber = (EditText) interventionCostContent.findViewById(R.id.roadTankers);
        numberKeybord(roadTankersNumber);

        specialVehicleNumber = (EditText) interventionCostContent.findViewById(R.id.specialVehicle);
        numberKeybord(specialVehicleNumber);

        transportationVehicleNumber = (EditText) interventionCostContent.findViewById(R.id.transportationVehicle);
        numberKeybord(transportationVehicleNumber);

        return interventionCostContent;
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
