package com.example.lab11;

import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@RestController
public class WebController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private AnswerRepository answerRepository;



    @PostMapping(value = "/api/user", consumes = "application/json")
    public String addUser(@RequestBody String jsonpObject){
        Gson gson = new Gson();

        SurveyUser surveyUser = gson.fromJson(jsonpObject, SurveyUser.class);

        userRepository.save(surveyUser);

        return gson.toJson(surveyUser);
    }

    @PostMapping(value = "/api/survey", consumes = "application/json")
    public String addSurvey(@RequestBody String jsonpObject){
        Gson gson = new Gson();

        Survey survey = gson.fromJson(jsonpObject, Survey.class);

        surveyRepository.save(survey);

        return gson.toJson(survey);
    }

    @GetMapping(value = "/api/survey")
    public ResponseEntity<List<Survey>> showSurvey() {

        List<Survey> survey = surveyRepository.findAll();
        return ResponseEntity.ok().body(survey);

    }

    @GetMapping(value = "/api/survey/{id}")
    public ResponseEntity<List<Survey>> showSurvey(@PathVariable int id) {

        List<Survey> survey = surveyRepository.findAll().stream().filter(s -> s.getUserID() == id).collect(Collectors.toList());
        return ResponseEntity.ok().body(survey);

    }
    @DeleteMapping(value = "/api/survey/{id}")
    public void deleteSurvey(@PathVariable int id) {

        List<Answer> answers = answerRepository.findAll().stream().filter(a -> a.getSurveyID() == id).collect(Collectors.toList());
        Survey survey = surveyRepository.findById(id).get();
        surveyRepository.delete(survey);
        for(Answer answer : answers){
            answerRepository.delete(answer);
        }

    }

    @PostMapping(value = "/api/answer", consumes = "application/json")
    public String addAnswer(@RequestBody String jsonpObject){
        Gson gson = new Gson();

        Answer answer = gson.fromJson(jsonpObject, Answer.class);

        answerRepository.save(answer);

        return gson.toJson(answer);
    }

    @GetMapping(value = "/api/answer/{id}")
    public String getAnswer(@PathVariable int id) {
        Gson gson = new Gson();
        Answer answer = answerRepository.findById(id).get();

        return gson.toJson(answer);
    }

    @GetMapping(value= "api/stats/user/{id}")
    public String getUserStats(@PathVariable int id){

        Session session = sessionFactory.openSession();
        String q ="select count(*) from survey";
        Query query = session.createQuery(q);
        int surveyAmount = query.getFetchSize();

//        int surveyAmount = surveyRepository.findAll().stream().filter(a -> a.getUserID() == id).collect(Collectors.toList()).size();



        List<Answer> answers = answerRepository.findAll().stream().filter(a -> a.getUserID() == id).collect(Collectors.toList());
        Map<Integer, Double> avgAnswer = answers.stream().collect(Collectors.groupingBy(Answer::getSurveyID, Collectors.averagingInt(Answer::getRating)));
        Map<Integer, Long> amountAnswer = answers.stream().collect(Collectors.groupingBy(Answer::getSurveyID, Collectors.counting()));
        StringBuilder json = new StringBuilder("{\"userid\":");
        json.append(surveyAmount);
        json.append(", \"avg_answers\": [");
        for(Map.Entry<Integer, Double> entry : avgAnswer.entrySet()) {
            Integer key = entry.getKey();
            Double value = entry.getValue();
            json.append("\""+key+"\":"+ value+",");

        }
        json.append("],\"answerAmount\":[");
        for(Map.Entry<Integer, Long> entry : amountAnswer.entrySet()) {
            Integer key = entry.getKey();
            Long value = entry.getValue();
            json.append("\""+key+"\":"+ value+",");
        }
        json.append("]}");

        return json.toString();

    }

}
