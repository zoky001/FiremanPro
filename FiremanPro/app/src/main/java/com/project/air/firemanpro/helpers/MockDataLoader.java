package com.project.air.firemanpro.helpers;

import com.project.air.firemanpro.DbResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikol on 21.10.2017..
 */

public class MockDataLoader {
    public static List<DbResult> getDemoData(){
        List<DbResult> resItems = new ArrayList<>();
        resItems.add(new DbResult("Ivo", "Ivić", "Petra Preradovića 28A, Varaždin"));
        resItems.add(new DbResult("Pero", "Perić", "Frankopanska 26"));
        resItems.add(new DbResult("Ana", "Anić", "Lošinjska 134"));
        return resItems;
    }
}
