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

    public static String makeStat(long surveyAmount, List<Object[]> list1, List<Object[]> list2){
        StringBuilder json = new StringBuilder("{\"surveyAmount\":");

        json.append(surveyAmount);
        json.append(", \"surveyRanking\": {");
        Double surveyAvg = 0.0;
        Double answerAvg = 0.0;
        for(Object[] obj : list1) {
            Long avg = (Long)obj[0];
            Integer key = (Integer)obj[1];
            surveyAvg += avg;
            json.append("\""+key+"\":"+ avg+",");
        }
        json.setLength(json.length()-1);
        json.append("},\"answerRanking\":{");
        for(Object[] obj : list2) {
            Integer key = (Integer)obj[0];
            Long avg = (Long)obj[1];
            answerAvg += avg;
            json.append("\""+key+"\":"+ avg+",");
        }

        answerAvg /= list2.size();
        surveyAvg /= list1.size();

        json.setLength(json.length()-1);
        json.append("},\"answerAvg\":");
        json.append(answerAvg);

        json.append(",\"surveyAvg\":");
        json.append(surveyAvg);
        json.append("}");
        return json.toString();
    }


}
