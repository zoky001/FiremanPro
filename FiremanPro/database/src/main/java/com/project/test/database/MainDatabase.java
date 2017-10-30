package com.project.test.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Ivica on 21.10.2017..
 */
@Database(name = MainDatabase.NAME, version = MainDatabase.VERSION)

public class MainDatabase {

    public static final String NAME = "FiremanProDatabase";
    public static final int VERSION = 2;


}
