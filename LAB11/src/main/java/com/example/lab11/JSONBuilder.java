package com.example.lab11;

import java.util.List;

public class JSONBuilder {
    public static String makeUserStat(long surveyAmount, List<Object[]> list1){
        StringBuilder json = new StringBuilder("{\"userid\":");
        StringBuilder jsonHelper = new StringBuilder();
        json.append(surveyAmount);
        json.append(", \"avg_answers\": {");
        for(Object[] obj : list1) {
            Integer key = (Integer)obj[0];
            Double avg = (Double)obj[1];
            Long count = (Long)obj[2];
            json.append("\""+key+"\":"+ avg+",");
            jsonHelper.append("\""+key+"\":"+ count+",");

        }
        json.setLength(json.length()-1);
        jsonHelper.setLength(jsonHelper.length()-1);
        json.append("},\"answerAmount\":{");
        json.append(jsonHelper);
        json.append("}}");
        return json.toString();
    }
}
