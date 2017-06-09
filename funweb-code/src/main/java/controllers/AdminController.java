package controllers;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@SessionAttributes(value = "username")
public class AdminController {
    @ResponseBody
    @RequestMapping(value="/addQuestion" , method = RequestMethod.POST)
    public String addQuestion(@RequestParam (name = "value") String param){
        JSONObject objs = null;
        try {
            objs = new JSONObject(param);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*
        TODO : add question to data base.
               get id of the new inserted question
               add options
         */
        return null;
    }

}

