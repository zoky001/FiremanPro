package com.kizo.report;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.solver.SolverVariable;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.project.test.database.Entities.fireman_patrol.Fireman;
import com.project.test.database.Entities.fireman_patrol.Fireman_patrol;
import com.project.test.database.Entities.fireman_patrol.Type_of_truck;
import com.project.test.database.Entities.fireman_patrol.Type_of_unit;
import com.project.test.database.Entities.report.Sort_of_intervention;
import com.project.test.database.controllers.report.Types_all_Controller;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.fragments.BackConfirmationFragment;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;

public class NewReportFormActivity extends AppCompatActivity implements VerticalStepperForm {

    public static final String NEW_ALARM_ADDED = "new_alarm_added";

    // Information about the steps/fields of the form
    private static final int MAIN_INFORMATION_NUM = 0;
    private static final int DESCRIPTION_STEP_NUM = 1;
    private static final int MATERIAL_AND_COST_NUM = 2;
    private static final int INTERVENTION_COST_NUM = 3;
    private static final int HELP_NUM = 4;
    private static final int MEHANIZATION_NUM = 5;
    private static final int FIREMEN_NUM = 6;


    // Title step
    private EditText chooseTypeAndSort;
    private static final int MIN_CHARACTERS_TITLE = 3;
    public static final String STATE_TITLE = "Osnovne informacije";

    // Description step
    private EditText descriptionEditText;
    public static final String STATE_DESCRIPTION = "description";

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

    private boolean confirmBack = true;
    private ProgressDialog progressDialog;
    private VerticalStepperFormLayout verticalStepperForm;

    Spinner spinnerType, spinnerSort;
    int userSelectedIndex;

    Spinner spinnerVehicle;
    NumberPicker surfaceNumber;
    NumberPicker superficiesNumber;
    NumberPicker kmNumber;
    NumberPicker clockNumber;
    NumberPicker waterNumber;
    NumberPicker foamNumber;
    NumberPicker powderNumber;
    NumberPicker co2Number;

    NumberPicker navalVehicleNumber;
    NumberPicker commandVehicleNumber;
    NumberPicker tehnicalVehicleNumber;
    NumberPicker automaticLadderNumber;
    NumberPicker roadTankersNumber;
    NumberPicker specialVehicleNumber;
    NumberPicker transportationVehicleNumber;

    NumberPicker numberOfFiremanNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_stepper_report_form);

        initializeActivity();
    }

    private void initializeActivity() {
        // Time step vars
        time = new Pair<>(8, 30);
        setTimePicker(8, 30);

        // Week days step vars
        weekDays = new boolean[7];

        // Vertical Stepper form vars
        int colorPrimary = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryREPORT);
        int colorPrimaryDark = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDarkREPORT);
        String[] stepsTitles = getResources().getStringArray(R.array.steps_titles);
        String[] stepsSubtitles = getResources().getStringArray(R.array.steps_subtitles);

        // Here we find and initialize the form
        verticalStepperForm = (VerticalStepperFormLayout) findViewById(R.id.vertical_stepper_form);
        VerticalStepperFormLayout.Builder.newInstance(verticalStepperForm, stepsTitles, this, this)
                //.stepsSubtitles(stepsSubtitles)
                //.materialDesignInDisabledSteps(true) // false by default
                //.showVerticalLineWhenStepsAreCollapsed(true) // false by default
                .primaryColor(colorPrimary)
                .primaryDarkColor(colorPrimaryDark)
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
                view  = createTypeAndSort();
                break;
            case DESCRIPTION_STEP_NUM:
                view = createUsedResources();
                break;
            case MATERIAL_AND_COST_NUM:
               // view = createUsedResources();
                view = createOwnerAndMaterialCost();
                break;
            case INTERVENTION_COST_NUM:
                view = createInterventionCost();
                break;
            case HELP_NUM:
                view = createDescriptionStep();
                break;
            case MEHANIZATION_NUM:
                view = createMehanizationStep();
                break;
            case FIREMEN_NUM:
                view = createFiremenStep();
        }
        return view;
    }

    private View createFiremenStep() {
        chooseTypeAndSort = new EditText(this);

        LayoutInflater inflate = LayoutInflater.from(getBaseContext());
        LinearLayout firemenContent = (LinearLayout) inflate.inflate(R.layout.step_firemen, null, false);

        numberOfFiremanNumber = (NumberPicker) firemenContent.findViewById(R.id.number_of_firemen);
        numberOfFiremanNumber.setMaxValue(40);
        numberOfFiremanNumber.setMinValue(1);

        /*
        RadioGroup rgp = (RadioGroup) findViewById(R.id.firemenAll);
        for (int i = 0; i < numberOfFiremanNumber.getValue(); i++)
        {
            Spinner fireman = new Spinner(this);
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allFiremen);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
            fireman.setAdapter(dataAdapter2);
            rgp.addView(fireman);
        }

        */
        return firemenContent;

    }

    private View createMehanizationStep() {
        final LayoutInflater inflate = LayoutInflater.from(getBaseContext());
        LinearLayout mehanizationContent = (LinearLayout) inflate.inflate(R.layout.step_mehanization, null, false);

        List<String> vehicleAll = new ArrayList<String>();

        Types_all_Controller type_all_controller = new Types_all_Controller();
        List<Type_of_truck> type_of_truck = type_all_controller.GetAllRecordsFromTable_Type_of_truck();
        for(Type_of_truck t : type_of_truck){
            vehicleAll.add(t.getType_name());
        }
        Spinner usedTruck = (Spinner) mehanizationContent.findViewById(R.id.vehicleUsed);

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, vehicleAll);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        usedTruck.setAdapter(dataAdapter2);

        Button addVehicleButton = (Button) mehanizationContent.findViewById(R.id.addVehicle);

        RadioGroup rgp = (RadioGroup) mehanizationContent.findViewById(R.id.vehicleAdder);
        addVehicleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

               /* Spinner vehicleSpinner = new Spinner(this);
                RadioGroup.LayoutParams rprms = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
                rgp.addView(vehicleSpinner, rprms);
                */
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


    @Override
    public void onStepOpening(int stepNumber) {
        switch (stepNumber) {
            case MAIN_INFORMATION_NUM:
                // When this step is open, we check that the title is correct
                //checkTitleStep(chooseTypeAndSort.getText().toString());
            case DESCRIPTION_STEP_NUM:
            case MATERIAL_AND_COST_NUM:
                // As soon as they are open, these two steps are marked as completed because they
                // have default values
                verticalStepperForm.setStepAsCompleted(stepNumber);
                // In this case, the instruction above is equivalent to:
                // verticalStepperForm.setActiveStepAsCompleted();
                // break;
            case INTERVENTION_COST_NUM:
                // When this step is open, we check the days to verify that at least one is selected
                //  checkDays();
                verticalStepperForm.setStepAsCompleted(stepNumber);
                // break;
            case HELP_NUM:
                verticalStepperForm.setStepAsCompleted(stepNumber);
            case MEHANIZATION_NUM:
                verticalStepperForm.setStepAsCompleted(stepNumber);
            case  FIREMEN_NUM:
                verticalStepperForm.setStepAsCompleted(stepNumber);
        }
    }

    private View createOwnerAndMaterialCost() {
        chooseTypeAndSort = new EditText(this);
        // titleEditText.setHint(R.string.form_hint_title);
        // titleEditText.setSingleLine(true);

        LayoutInflater inflate = LayoutInflater.from(getBaseContext());
        LinearLayout ownerAndCostContent = (LinearLayout) inflate.inflate(R.layout.step_owner_and_cost, null, false);

        surfaceNumber = (NumberPicker) ownerAndCostContent.findViewById(R.id.surface);
        surfaceNumber.setMinValue(0);
        surfaceNumber.setMaxValue(100);

        superficiesNumber = (NumberPicker) ownerAndCostContent.findViewById(R.id.superficies);
        superficiesNumber.setMinValue(0);
        superficiesNumber.setMaxValue(100);

        return ownerAndCostContent;
    }

    private View createUsedResources() {
        chooseTypeAndSort = new EditText(this);
        // titleEditText.setHint(R.string.form_hint_title);
        // titleEditText.setSingleLine(true);

        LayoutInflater inflate = LayoutInflater.from(getBaseContext());
        LinearLayout vehicleContent = (LinearLayout) inflate.inflate(R.layout.step_used_resources, null, false);

        spinnerVehicle = (Spinner) vehicleContent.findViewById(R.id.vehicle);
        List<String> vehicleAll = new ArrayList<String>();

        Types_all_Controller type_all_controller = new Types_all_Controller();
        List<Type_of_truck> type_of_truck = type_all_controller.GetAllRecordsFromTable_Type_of_truck();
        for(Type_of_truck t : type_of_truck){
            vehicleAll.add(t.getType_name());
        }


        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, vehicleAll);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerVehicle.setAdapter(dataAdapter2);

        kmNumber = (NumberPicker) vehicleContent.findViewById(R.id.km);
        kmNumber.setMinValue(0);
        kmNumber.setMaxValue(100);

        clockNumber = (NumberPicker) vehicleContent.findViewById(R.id.clock);
        clockNumber.setMinValue(0);
        clockNumber.setMaxValue(100);

        /*
        numberOfFiremanNumber = (NumberPicker) vehicleContent.findViewById(R.id.number_of_firemen);
        numberOfFiremanNumber.setMinValue(0);
        numberOfFiremanNumber.setMaxValue(100);
        */

        waterNumber = (NumberPicker) vehicleContent.findViewById(R.id.water);
        waterNumber.setMinValue(0);
        waterNumber.setMaxValue(100);

        foamNumber = (NumberPicker) vehicleContent.findViewById(R.id.foam);
        foamNumber.setMinValue(0);
        foamNumber.setMaxValue(100);

        powderNumber = (NumberPicker) vehicleContent.findViewById(R.id.powder);
        powderNumber.setMinValue(0);
        powderNumber.setMaxValue(100);

        co2Number = (NumberPicker) vehicleContent.findViewById(R.id.CO2);
        co2Number.setMinValue(0);
        co2Number.setMaxValue(100);

        return vehicleContent;
    }

    private View createInterventionCost() {
        chooseTypeAndSort = new EditText(this);
        // titleEditText.setHint(R.string.form_hint_title);
        // titleEditText.setSingleLine(true);

        LayoutInflater inflate = LayoutInflater.from(getBaseContext());
        LinearLayout interventionCostContent = (LinearLayout) inflate.inflate(R.layout.step_intervention_cost, null, false);

        navalVehicleNumber = (NumberPicker) interventionCostContent.findViewById(R.id.navalVehicle);
        navalVehicleNumber.setMinValue(0);
        navalVehicleNumber.setMaxValue(100);

        commandVehicleNumber = (NumberPicker) interventionCostContent.findViewById(R.id.commandVehicle);
        commandVehicleNumber.setMinValue(0);
        commandVehicleNumber.setMaxValue(100);

        tehnicalVehicleNumber = (NumberPicker) interventionCostContent.findViewById(R.id.tehnicalVehicle);
        tehnicalVehicleNumber.setMinValue(0);
        tehnicalVehicleNumber.setMaxValue(100);

        automaticLadderNumber = (NumberPicker) interventionCostContent.findViewById(R.id.automaticLadder);
        automaticLadderNumber.setMinValue(0);
        automaticLadderNumber.setMaxValue(100);

        roadTankersNumber = (NumberPicker) interventionCostContent.findViewById(R.id.roadTankers);
        roadTankersNumber.setMinValue(0);
        roadTankersNumber.setMaxValue(100);

        specialVehicleNumber = (NumberPicker) interventionCostContent.findViewById(R.id.specialVehicle);
        specialVehicleNumber.setMinValue(0);
        specialVehicleNumber.setMaxValue(100);

        transportationVehicleNumber = (NumberPicker) interventionCostContent.findViewById(R.id.transportationVehicle);
        transportationVehicleNumber.setMinValue(0);
        transportationVehicleNumber.setMaxValue(100);

        return interventionCostContent;
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


    private View createTypeAndSort() {
        chooseTypeAndSort = new EditText(this);
       // titleEditText.setHint(R.string.form_hint_title);
        // titleEditText.setSingleLine(true);

        LayoutInflater inflate = LayoutInflater.from(getBaseContext());
        LinearLayout typeAndSortContent = (LinearLayout) inflate.inflate(R.layout.type_and_sort_of_intervention, null, false);

        spinnerSort = (Spinner) typeAndSortContent.findViewById(R.id.sort_of_intervention);
        List<String> sortAll = new ArrayList<String>();

        Types_all_Controller type_all_controller = new Types_all_Controller();
        List<Sort_of_intervention> sort_of_intervention = type_all_controller.GetAllRecordsFromTable_Sort_of_intervention();
        for(Sort_of_intervention s : sort_of_intervention){
            sortAll.add(s.getName());
        }


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sortAll);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerSort.setAdapter(dataAdapter);

        EditText inputLayout = (EditText) findViewById(R.id.description);



/*
        spinnerType = (Spinner) typeAndSortContent.findViewById(R.id.type_of_intervention);
        List<String> typeAll = new ArrayList<String>();

DAODATI TYPE OF INTERVENTION
        Types_all_Controller type_all_controller2 = new Types_all_Controller();
        List<> sort_of_intervention = type_all_controller2.GetAllRecordsFromTable_Sort_of_intervention();
        for(Sort_of_intervention s : sort_of_intervention){
            sortAll.add(s.getName());
        }

        spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                verticalStepperForm.goToNextStep();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sortAll);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerSort.setAdapter(dataAdapter);

*/

        return typeAndSortContent;
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
                    intent.putExtra(STATE_TIME_HOUR, time.first);
                    intent.putExtra(STATE_TIME_MINUTES, time.second);
                    intent.putExtra(STATE_WEEK_DAYS, weekDays);
                    intent.putExtra(STATE_WEEK_DAYS, weekDays);
                    intent.putExtra(STATE_WEEK_DAYS, weekDays);

                    // You must set confirmBack to false before calling finish() to avoid the confirmation dialog
                    confirmBack = false;
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start(); // You should delete this code and add yours

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

    */


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
*/
    private LinearLayout getDayLayout(int i) {
        int id = daysStepContent.getResources().getIdentifier(
                "day_" + i, "id", getPackageName());
        return (LinearLayout) daysStepContent.findViewById(id);
    }

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

        // Saving time field
        if(time != null) {
            savedInstanceState.putInt(STATE_TIME_HOUR, time.first);
            savedInstanceState.putInt(STATE_TIME_MINUTES, time.second);
        }

        // Saving week days field
        if(weekDays != null) {
            savedInstanceState.putBooleanArray(STATE_WEEK_DAYS, weekDays);
        }

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

        // Restoration of time field
        if(savedInstanceState.containsKey(STATE_TIME_HOUR)
                && savedInstanceState.containsKey(STATE_TIME_MINUTES)) {
            int hour = savedInstanceState.getInt(STATE_TIME_HOUR);
            int minutes = savedInstanceState.getInt(STATE_TIME_MINUTES);
            time = new Pair<>(hour, minutes);
            setTime(hour, minutes);
            if(timePicker == null) {
                setTimePicker(hour, minutes);
            } else {
                timePicker.updateTime(hour, minutes);
            }
        }

        // Restoration of week days field
        if(savedInstanceState.containsKey(STATE_WEEK_DAYS)) {
            weekDays = savedInstanceState.getBooleanArray(STATE_WEEK_DAYS);
            if (weekDays != null) {
                for (int i = 0; i < weekDays.length; i++) {
                    LinearLayout dayLayout = getDayLayout(i);
                    if (weekDays[i]) {
                        activateDay(i, dayLayout, false);
                    } else {
                        deactivateDay(i, dayLayout, false);
                    }
                }
            }
        }

        // The call to super method must be at the end here
        super.onRestoreInstanceState(savedInstanceState);
    }


}
