package com.example.lab11;

import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import java.lang.reflect.Array;
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

    private SessionFactory sessionFactory;

    @Autowired
    public WebController(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }




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
        Query query = session.createQuery("select count(*) from com.example.lab11.Survey s where s.userID = :id");
        query.setParameter("id", id);
        Long surveyAmount = (Long) query.getSingleResult();
        Query query1 = session.createQuery("select a.surveyID, avg(a.rating), count(a.answerID) from com.example.lab11.Answer a where a.userID = :id GROUP BY a.surveyID");
        query1.setParameter("id", id);
        List<Object[]> list1 = (List<Object[]>) query1.list();
        return JSONBuilder.makeUserStat(surveyAmount, list1);

    }

    @GetMapping(value= "api/stats")
    public String getStats(){

        Session session = sessionFactory.openSession();
        Query query1 = session.createQuery("select count(s.surveyId), s.userID from com.example.lab11.Survey s GROUP BY s.userID ORDER BY count(s.surveyId) DESC");
        List<Object[]> list1 = (List<Object[]>) query1.list();
        Query query2 = session.createQuery("select a.userID, count(a.answerID) from com.example.lab11.Answer a GROUP BY a.userID ORDER BY count(a.answerID) DESC");
        List<Object[]> list2 = (List<Object[]>) query2.list();
        Query query3 = session.createQuery("select count(*) from com.example.lab11.Survey s");
        Long surveyAmount = (Long) query3.getSingleResult();




//        List<Object[]> list1 = (List<Object[]>) query1.list();
        return JSONBuilder.makeStat(surveyAmount,list1,list2);

    }
}
