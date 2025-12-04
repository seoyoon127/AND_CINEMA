package com.example.movieapplication.data;

import java.util.HashMap;
import java.util.Map;

public class MovieCost {
    private static Map<String, Integer> costMap = new HashMap<>();
    public static Map<String, Integer> getMap(){
        costMap.put("성인", 14000);
        costMap.put("청소년", 11000);
        costMap.put("경로", 7000);
        costMap.put("장애인", 5000);
        return costMap;
    }
}
