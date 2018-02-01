package com.project.test.database.firebaseEntities;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.name;
import static android.R.attr.type;

/**
 * Created by Zoran on 26.1.2018..
 */

@IgnoreExtraProperties
public class Cost {

    public Double Cost_apsorbent;
    public Double AutomaticLadder;
    public Double Co2;
    public Double commandVehicle;
    public Double fireExtinguisher;
    public Double fireFighter;
    public Double foam;
    public Double insurance;
    public Double navalVehicle;
    public Double powerPumpClock;
    public Double roadTankers;
    public Double specialVehicle;
    public Double tehnicalVehicle;
    public Double transportationVehicle;

    public Cost() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Cost(Double cost_apsorbent, Double automaticLadder, Double co2, Double commandVehicle, Double fireExtinguisher, Double fireFighter, Double foam, Double insurance, Double navalVehicle, Double powerPumpClock, Double roadTankers, Double specialVehicle, Double tehnicalVehicle, Double transportationVehicle) {

        Cost_apsorbent = cost_apsorbent;
        this.AutomaticLadder = automaticLadder;
        this.Co2 = co2;
        this.commandVehicle = commandVehicle;
        this.fireExtinguisher = fireExtinguisher;
        this.fireFighter = fireFighter;
        this.foam = foam;
        this.insurance = insurance;
        this.navalVehicle = navalVehicle;
        this.powerPumpClock = powerPumpClock;
        this.roadTankers = roadTankers;
        this.specialVehicle = specialVehicle;
        this.tehnicalVehicle = tehnicalVehicle;
        this.transportationVehicle = transportationVehicle;

    }
    @Exclude
    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();
        result.put("Cost_apsorbent", Cost_apsorbent);
        result.put("AutomaticLadder" , AutomaticLadder);
        result.put("Co2", Co2);

        result.put("commandVehicle", commandVehicle);
        result.put("fireExtinguisher" , fireExtinguisher);
        result.put("fireFighter", fireFighter);
        result.put("foam", foam);
        result.put("insurance" , insurance);
        result.put("navalVehicle", navalVehicle);
        result.put("powerPumpClock", powerPumpClock);
        result.put("roadTankers" , roadTankers);
        result.put("specialVehicle", specialVehicle);
        result.put("tehnicalVehicle" , tehnicalVehicle);
        result.put("transportationVehicle", transportationVehicle);


        return result;

    }
}