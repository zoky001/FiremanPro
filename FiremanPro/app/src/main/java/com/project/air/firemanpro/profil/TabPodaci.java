package com.project.air.firemanpro.profil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kizo.core_module.tab_profile.ITabFragment;
import com.kizo.core_module.tab_profile.TabFragment;
import com.project.air.firemanpro.R;
import com.project.test.database.Entities.House;
import com.project.test.database.controllers.HouseController;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Tab koji se učitava u obliku fragmenta unutar ProfilNewActivity
 * <p>
 * Sadrži sve podatke o odabranoj kući.
 * <p>
 * Prilikom kreiranja potrebno je prosljediti id koristeći Bundle, "IDkuce" = id odabrane kuce
 * <p>
 * <p>
 * Created by Zoran on 27.10.2017..
 */

public class TabPodaci extends TabFragment {

    @BindView(R.id.txtHouseOwnerName)
    TextView txtHouseOwnerName;

    @BindView(R.id.txtHousePost)
    TextView txtHousePost;

    @BindView(R.id.txtHouseAddress)
    TextView txtHouseAddress;

    @BindView(R.id.txtHousePlace)
    TextView txtHousePlace;

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

    @BindView(R.id.data_house_latitude)
    TextView txtLatitude;

    @BindView(R.id.data_house_longitude)
    TextView txtLongitude;

    @BindView(R.id.data_house_telNumber)
    TextView txtHouseTEl;


    @BindView(R.id.data_house_mobNumber)
    TextView txtHouseMOB;

    House house;

    /**
     * Prilikom kriranja View-a se iz argumenata/Bundle dohvaća ID odabrane kuće.
     * Pomoću navedenog ID-a se dohvaća kuća iu baze podataka, te pohranjuje u varijablu "house" za daljnje korištenje.
     *
     * @author Zoran Hrnčić
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        System.out.println("Tabpodaci");
        View rootView = inflater.inflate(R.layout.tab_podaci_, container, false);
        ButterKnife.bind(this, rootView);

        try {
            int a = Integer.parseInt(getArguments().getString("IDkuce"));
            house = HouseController.getHouse(a);

            //set contetn of table
            fillTableWithContent();

        } catch (Exception e) {
            System.out.println("EXCEPTON: " + e.getMessage());
        }

        return rootView;
    }

    /**
     * popounjavanje tablice sa podatcima iz baze podataka.
     */
    private void fillTableWithContent() {
        try {
            //set contetn of table
            txtHouseOwnerName.setText(house.getSurname_owner() + " " + house.getName_owner());

            txtHousePost.setText(house.getAddress().getPost().getPostal_code() + " " + house.getAddress().getPost().getName());

            txtHouseAddress.setText(house.getAddress().getStreetNameIfExist() + " " + house.getAddress().getStreetNumber());

            txtHousePlace.setText(house.getPlaceName());

            txtHouseTeenants.setText(String.valueOf(house.getNumber_of_tenants()));

            txtHouseFloors.setText(house.getList_of_floors());


            txtHouseNumChild.setText(String.valueOf(house.getNumber_of_children()));

            txtHouseYearsChild.setText(house.getYear_children());

            txtHouseNumAdults.setText(String.valueOf(house.getNumber_of_adults()));

            txtHouseYearsAdults.setText(house.getYears_adults());

            txtHouseNumElders.setText(String.valueOf(house.getNumber_of_powerless_and_elders()));

            txtHouseYearsElders.setText(house.getYears_powerless_elders());

            txtHouseDisability.setText(house.isDisability_person() ? "DA" : "NE");

            txtHousePowerSupply.setText(house.isHRO_power_supply() ? "DA" : "NE");

            txtHouseGasConnection.setText(house.isGas_connection() ? "DA" : "NE");

            txtHouseTypeHeating.setText(house.getType_of_heating());

            txtHouseGasBottle.setText(house.isGas_bottle() ? "DA" : "NE");

            txtHouseNumGasBottle.setText(String.valueOf(house.getNumber_of_gas_bottle()));

            txtHouseTypeRoof.setText(house.getType_of_roof());

            txtHouseHydrantDistance.setText(String.valueOf(house.getHydrant_distance()));

            txtHouseHRO.setText(house.isHigh_risk_object() ? "DA" : "NE");

            txtHouseHROroof.setText(house.getHRO_type_of_roof());

            txtHouseHROpowerSupply.setText(house.isHRO_power_supply() ? "DA" : "NE");

            txtHouseHROContent.setText(house.getHRO_content());

            txtHouseHROAnimals.setText(house.isHRO_animals() ? "DA" : "NE");
            txtLatitude.setText(String.valueOf(house.getAddress().getLatitude()));
            txtLongitude.setText(String.valueOf(house.getAddress().getLongitude()));

            txtHouseTEl.setText(house.getTelNumber());

            txtHouseMOB.setText(house.getMobNumber());
        } catch (Exception e) {
            System.out.println("EXCEPTON: " + e.getMessage());
        }
    }

    /**
     * omogućuje učitavanje ovog fragmenta u metodu interface-a
     *
     * @param iTabFragment imaplementacija interface-a ITabFragment
     */
    @Override
    public void loadFrag(ITabFragment iTabFragment) {
        super.loadFrag(iTabFragment);
        iTabFragment.getFragment(this);
    }
}