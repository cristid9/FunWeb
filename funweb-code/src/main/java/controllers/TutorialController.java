package controllers;

import com.mashape.unirest.http.exceptions.UnirestException;
import factories.BidirectionalQuestionFactory;
import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pojos.Question;

import java.util.List;

@Controller
//@SessionAttributes(value = "name")/
public class TutorialController {

    @ResponseBody
    @RequestMapping(
            value = "npcQuestions/{npcId}",
            method = RequestMethod.GET
    )
    public String getNPCQuestions(
            @PathVariable Long npcId) {

        JSONArray jsonArray = null;
        try {
            jsonArray = BidirectionalQuestionFactory.newInstance(npcId);
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return jsonArray.toString();
    }
}
