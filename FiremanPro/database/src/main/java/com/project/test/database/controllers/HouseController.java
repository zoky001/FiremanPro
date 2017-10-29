package com.project.test.database.controllers;

import com.project.test.database.Entities.House;
import com.project.test.database.Entities.House_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

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
        return house;
    }
}
