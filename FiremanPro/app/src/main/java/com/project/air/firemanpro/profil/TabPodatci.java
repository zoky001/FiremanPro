package com.project.air.firemanpro.profil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.air.firemanpro.R;
import com.project.test.database.Entities.House;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Zoran on 27.10.2017..
 */

public class TabPodatci extends Fragment {

    @BindView(R.id.txtHouseOwner)
    TextView txtHouseOwner;

    @BindView(R.id.txtHousePlace)
    TextView txtHousePlace;

    @BindView(R.id.txtHouseAddress)
    TextView txtHouseAddress;

    @BindView(R.id.txtHouseTeenants)
    TextView txtHouseTeenants;

    @BindView(R.id.txtHouseFloors)
    TextView txtHouseFloors;


    @BindView(R.id.data_house_nmb_children)
    TextView txtHouseNumChild;

    @BindView(R.id.data_house_yrs_children)
    TextView txtHouseYearsChild;

    @BindView(R.id.data_house_nmb_adults)
    TextView txtHouseNumAdults;

    @BindView(R.id.data_house_yrs_adults)
    TextView txtHouseYearsAdults;

    @BindView(R.id.data_house_nmb_elders)
    TextView txtHouseNumElders;

    @BindView(R.id.data_house_yrs_elders)
    TextView txtHouseYearsElders;

    @BindView(R.id.data_house_disability)
    TextView txtHouseDisability;

    @BindView(R.id.data_house_power_supply)
    TextView txtHousePowerSupply;

    @BindView(R.id.data_house_gas_connection)
    TextView txtHouseGasConnection;

    @BindView(R.id.data_house_type_of_heating)
    TextView txtHouseTypeHeating;

    @BindView(R.id.data_house_gas_bottle)
    TextView txtHouseGasBottle;

    @BindView(R.id.data_house_number_of_gas_bottle)
    TextView txtHouseNumGasBottle;

    @BindView(R.id.data_house_type_of_roof)
    TextView txtHouseTypeRoof;

    @BindView(R.id.data_house_hydrant_distance)
    TextView txtHouseHydrantDistance;

    @BindView(R.id.data_house_high_risk_object)
    TextView txtHouseHRO;

    @BindView(R.id.data_house_HRO_type_of_roof)
    TextView txtHouseHROroof;

    @BindView(R.id.data_house_HRO_power_supply)
    TextView txtHouseHROpowerSupply;

    @BindView(R.id.data_house_HRO_content)
    TextView txtHouseHROContent;

    @BindView(R.id.data_house_HRO_animals)
    TextView txtHouseHROAnimals;

    @BindView(R.id.data_house_telNumber)
    TextView txtHouseTEl;



    @BindView(R.id.data_house_mobNumber)
    TextView txtHouseMOB;

    House house;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        System.out.println("Tabpodatci");
        View rootView = inflater.inflate(R.layout.tab_podatci_, container, false);
        ButterKnife.bind(this, rootView);

        String s = getArguments().getString("IDkuce");
        System.out.println("SESSION FRAGMENT_idkuce: "+s);
        int a = Integer.parseInt(getArguments().getString("IDkuce"));
        if (a != -1){

            house = ProfilController.getHouse(a);

        }
        else {
            house = ProfilController.getFirstHouse();
        }

        //set contetn of table
        txtHouseOwner.setText(house.getSurname_owner()+" "+house.getName_owner());

        txtHousePlace.setText(house.getPlaceName());

        txtHouseAddress.setText(house.getAddress());

      txtHouseTeenants.setText(String.valueOf(house.getNumber_of_tenants()));

        txtHouseFloors.setText(house.getList_of_floors());


      txtHouseNumChild.setText(String.valueOf(house.getNumber_of_children()));

       txtHouseYearsChild.setText(house.getYear_children());

       txtHouseNumAdults.setText(String.valueOf(house.getNumber_of_adults()));

        txtHouseYearsAdults.setText(house.getYears_adults());

        txtHouseNumElders.setText(String.valueOf(house.getNumber_of_powerless_and_elders()));

        txtHouseYearsElders.setText(house.getYears_powerless_elders());

        txtHouseDisability.setText(house.isDisability_person()?"DA":"NE");

        txtHousePowerSupply.setText(house.isHRO_power_supply()?"DA":"NE");

        txtHouseGasConnection.setText(house.isGas_connection()?"DA":"NE");

        txtHouseTypeHeating.setText(house.getType_of_heating());

       txtHouseGasBottle.setText(house.isGas_bottle()?"DA":"NE");

       txtHouseNumGasBottle.setText(String.valueOf(house.getNumber_of_gas_bottle()));

        txtHouseTypeRoof.setText(house.getType_of_roof());

       txtHouseHydrantDistance.setText(String.valueOf(house.getHydrant_distance()));

        txtHouseHRO.setText(house.isHigh_risk_object()?"DA":"NE");

        txtHouseHROroof.setText(house.getHRO_type_of_roof());

        txtHouseHROpowerSupply.setText(house.isHRO_power_supply()?"DA":"NE");

       txtHouseHROContent.setText(house.getHRO_content());

        txtHouseHROAnimals.setText(house.isHRO_animals()?"DA":"NE");

        txtHouseTEl.setText(house.getTelNumber());

       txtHouseMOB.setText(house.getMobNumber());


return rootView;
    }
}