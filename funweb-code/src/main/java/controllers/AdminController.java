package controllers;


import com.mashape.unirest.http.exceptions.UnirestException;
import factories.BidirectionalOptionFactory;
import factories.BidirectionalQuestionFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pojos.Option;
import pojos.Question;

import java.util.Map;

@Controller
@SessionAttributes(value = "username")
public class AdminController {

    @RequestMapping(value="/edit_question", method = RequestMethod.GET)
    public String getPvpPage(){
        return "edit_question";
    }

    @ResponseBody
    @RequestMapping(value="/addQuestion" , method = RequestMethod.POST)
    public String addQuestion(@RequestParam (name = "value") String param){
        JSONObject objs = null;
        try {
            objs = new JSONObject(param);
            String question = (String) objs.get("enunciation");
            String optionA = (String) objs.get("optionA");
            String optionB = (String) objs.get("optionB");
            String optionC = (String) objs.get("optionC");
            String correct = (String) objs.get("correct");

            Question toBeInserted = new Question(1l, question, 10l, 10l, 10l);
            try {
                Long id = BidirectionalQuestionFactory.persist(toBeInserted);
                Option optionOne  = new Option();
                Option optionTwo  = new Option();
                Option optionThree= new Option();

                optionOne.setId(1l);
                optionTwo.setId(1l);
                optionThree.setId(1l);

                optionOne.setEnunciation(optionA);
                optionTwo.setEnunciation(optionB);
                optionThree.setEnunciation(optionC);

                optionOne.setQuestionId(id);
                optionTwo.setQuestionId(id);
                optionThree.setQuestionId(id);

                optionOne.setCorrectness(Boolean.FALSE);
                optionTwo.setCorrectness(Boolean.FALSE);
                optionThree.setCorrectness(Boolean.FALSE);

                if(correct.equals("A")){
                    optionOne.setCorrectness(Boolean.TRUE);
                }
                if(correct.equals("B")) {
                    optionTwo.setCorrectness(Boolean.TRUE);
                }
                if(correct.equals("C")) {
                    optionThree.setCorrectness(Boolean.TRUE);
                }

                BidirectionalOptionFactory.persist(optionOne);
                BidirectionalOptionFactory.persist(optionTwo);
                BidirectionalOptionFactory.persist(optionThree);
            } catch (UnirestException e) {
                e.printStackTrace();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}

