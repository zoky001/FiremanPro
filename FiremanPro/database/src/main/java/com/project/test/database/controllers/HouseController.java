package com.project.test.database.controllers;

import com.project.test.database.Entities.House;
import com.project.test.database.Entities.House_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Zoran on 24.10.2017..
 */

public class HouseController {



    public static House getHouse (int idHouse){
       List<House> house = SQLite.select().from(House.class).where(House_Table.id_house.is(idHouse)).queryList();


        return house.get(0);

    }

    public static House getFirstHouse (){
        List<House> house = SQLite.select().from(House.class).queryList();


        return house.get(0);

    }

   public static List<House> getAllHouseRecords(){


       List<House> house = SQLite.select().from(House.class).queryList();


       return house;
   }
    public static List<House> serachByNameAndSurnameQuery(String text){

        List<House> house = SQLite.select().from(House.class).where(House_Table.name_owner.like("%"+text+"%")).or(House_Table.surname_owner.like("%"+text+"%")).or(House_Table.address.like("%"+text+"%")).queryList();
        //Checking if string contains space for split
        if (text.contains(" ")) {
           List<String> splitedStrings = Arrays.asList(text.split(" "));
           List<House> house1 = SQLite.select().from(House.class).where(House_Table.name_owner.is(splitedStrings.get(0))).and(House_Table.surname_owner.is(splitedStrings.get(1))).queryList();
           for (int i = 0; i < house1.size(); i++) {
               if (!house.contains(house1.get(i))) {
                   house.add(house1.get(i));
               }
           }
       }
        return house;

    }


}
