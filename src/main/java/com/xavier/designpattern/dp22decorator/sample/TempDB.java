package com.xavier.designpattern.dp22decorator.sample;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Xavier on 2018-02-19.
 */
public class TempDB {
    private TempDB() {

    }

    public static Map<String, Double> mapMonthSaleMoney =
            new HashMap<String, Double>();

    static {
        mapMonthSaleMoney.put("Xavier", 1000.0);
        mapMonthSaleMoney.put("Javier", 2000.0);
        mapMonthSaleMoney.put("Zavier", 3000.0);
    }
}
